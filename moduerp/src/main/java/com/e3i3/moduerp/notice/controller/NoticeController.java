package com.e3i3.moduerp.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.e3i3.moduerp.employee.model.service.EmployeeService;
import com.e3i3.moduerp.notice.model.dto.Notice;
import com.e3i3.moduerp.notice.model.service.NoticeService;

import java.io.File;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	// 필드 추가
	@Value("${email.upload.dir}")
	private String uploadDir;

	@GetMapping("/notice/list.do")
	public String showNoticeList(Model model, @RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "keyword", required = false) String keyword) {
		  List<Notice> noticeList;

	        // 검색어와 카테고리가 있을 경우 필터링
	        if (keyword != null && !keyword.isEmpty()) {
	            noticeList = noticeService.searchNotices(category, keyword);
	        } else {
	            // 검색어가 없으면 전체 리스트를 반환
	            noticeList = noticeService.getAllNotices();
	        }

	        model.addAttribute("noticeList", noticeList);
		return "notice/noticeList";
	}

	@GetMapping("/notice/view/{noticeId}.do")
	public String viewNotice(@PathVariable("noticeId") String noticeId, Model model) {
		Notice notice = noticeService.getNoticeById(noticeId);
		model.addAttribute("notice", notice);
		return "notice/noticeDetail";
	}

	@GetMapping("/notice/form.do")
	public String showAddForm() {
		return "notice/noticeForm";
	}

	@PostMapping("/notice/add.do")
	public String addNotice(@RequestParam("title") String title, @RequestParam("body") String body,
			@RequestParam(value = "attachment", required = false) MultipartFile file, RedirectAttributes model,
			HttpSession session) {

		// 1. 로그인 유저의 이메일 주소와 UUID, 사업자번호 가져오기
		String loginUUID = (String) session.getAttribute("uuid");

		// 현재 날짜 설정
		Date currentDate = new Date(System.currentTimeMillis());

		// 체인 방식으로 Notice 객체 생성
		Notice notice = new Notice().setNoticeId(java.util.UUID.randomUUID().toString()).setTitle(title).setBody(body)
				// .setAttachment(file) // Nullable
				.setNoticeDate(currentDate);

		// 파일 첨부 처리 로직
		if (!file.isEmpty()) {
			try {
				// 원본 파일명 가져오기
				String originalFilename = file.getOriginalFilename();

				if (originalFilename == null) {
					model.addFlashAttribute("message", "파일명 정보를 가져올 수 없습니다.");
					return "notice/noticeForm";
				}

				// 파일명에 ".." 포함 여부 확인 (디렉토리 트래버설 방지)
				if (originalFilename.contains("..")) {
					model.addFlashAttribute("message", "유효하지 않은 파일명입니다.");
					return "notice/noticeForm";
				}

				// 새로운 파일명 생성 (첨부한 사람의 UUID + "_" + 파일명)
				String newFilename = loginUUID + "_" + originalFilename;

				// 파일 저장 경로 설정 시 File.separator 사용
				String uploadDir = this.uploadDir.replace("/", File.separator).replace("\\", File.separator);

				// 로그 추가 (디버깅용)
				System.out.println("Upload Directory: " + uploadDir);

				// 디렉토리가 존재하지 않으면 생성
				File dir = new File(uploadDir);
				if (!dir.exists()) {
					boolean dirsCreated = dir.mkdirs();
					if (!dirsCreated) {
						model.addFlashAttribute("message", "파일 저장 디렉토리를 생성할 수 없습니다.");
						return "notice/noticeForm";
					}
				}

				// 파일 저장 경로를 File 객체로 생성
				File serverFile = new File(uploadDir + File.separator + newFilename);

				// 경로 로그 출력 (확인용)
				System.out.println("통일된 파일 저장 경로: " + serverFile.getAbsolutePath());

				// 파일 저장
				file.transferTo(serverFile);

				// 파일 저장 경로 확인
				if (serverFile.exists()) {
					System.out.println("파일이 성공적으로 저장되었습니다: " + serverFile.getAbsolutePath());
				} else {
					System.out.println("파일 저장에 실패했습니다.");
				}

				// 파일 경로가 Windows 또는 Linux 환경에 따라 다르게 처리될 수 있으므로,
				String normalizedPath = serverFile.getAbsolutePath().replace("\\", "/");
				System.out.println("정규화된 파일 경로: " + normalizedPath);

				// attachmentPath 설정 (파일명만 저장)
				notice.setAttachment(newFilename);
			} catch (Exception e) {
				e.printStackTrace();
				model.addFlashAttribute("message", "파일 업로드 실패: " + e.getMessage());
				return "notice/noticeForm";
			}
		} else {
			notice.setAttachment(null); // 파일 첨부하지 않은 경우
		}

		noticeService.addNotice(notice);
		return "redirect:/notice/list.do";
	}

	@GetMapping("/notice/edit/{noticeId}.do")
	public String showEditForm(@PathVariable("noticeId") String noticeId, Model model) {
		Notice notice = noticeService.getNoticeById(noticeId);
		model.addAttribute("notice", notice);
		return "notice/noticeForm";
	}

	@PostMapping("/notice/edit.do")
	public String editNotice(@RequestParam("noticeId") String noticeId, @RequestParam("title") String title,
			@RequestParam("body") String body, @RequestParam(value = "attachment", required = false) String attachment,
			@RequestParam("noticeDate") Date noticeDate) {
		// 체인 방식으로 Notice 객체 수정
		Notice notice = new Notice().setNoticeId(noticeId).setTitle(title).setBody(body).setAttachment(attachment) // Nullable
				.setNoticeDate(noticeDate);

		noticeService.updateNotice(notice);
		return "redirect:/notice/list.do";
	}

	@PostMapping("/notice/delete/{noticeId}.do")
	public String deleteNotice(@PathVariable("noticeId") String noticeId) {
		noticeService.removeNotice(noticeId);
		return "redirect:/notice/list.do";
	}
}
