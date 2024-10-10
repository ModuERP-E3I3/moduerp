package com.e3i3.moduerp.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.e3i3.moduerp.notice.model.dto.Notice;
import com.e3i3.moduerp.notice.model.service.NoticeService;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/noticeList.do")
    public String noticeList(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 20; // 한 페이지에 보여줄 게시물 수
        int totalNotices = noticeService.getNoticeCount(); // 전체 공지사항 수
        int totalPages = (int) Math.ceil((double) totalNotices / pageSize); // 전체 페이지 수

        List<Notice> noticeList = noticeService.getNoticeList(page, pageSize); // 공지사항 리스트 가져오기

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "notice/noticeList"; // noticeList.jsp
    }

    // 공지사항 상세 보기
    @RequestMapping("/noticeDetail.do")
    public String noticeDetail(@RequestParam("noticeId") int noticeId, Model model) {
        Notice notice = noticeService.getNoticeById(noticeId);  // noticeId로 공지사항 정보 가져오기
        model.addAttribute("notice", notice);  // notice라는 이름으로 모델에 데이터 추가
        return "notice/noticeDetail";  // noticeDetail.jsp로 이동
    }
    
    // 공지사항 등록 폼 이동
    @RequestMapping("/noticeWriteForm.do")
    public String noticeWriteForm() {
        return "notice/noticeWriteForm";  // 공지사항 작성 폼으로 이동
    }

    // 공지사항 등록 처리
    @RequestMapping("/noticeWrite.do")
    public String noticeWrite(Notice notice) {
        noticeService.insertNotice(notice);  // 공지사항 저장
        return "redirect:/noticeList.do";  // 저장 후 공지사항 목록으로 이동
    }
    
    // 공지사항 수정 폼 이동
    @RequestMapping("/noticeUpdateForm.do")
    public String noticeUpdateForm(@RequestParam("noticeId") int noticeId, Model model) {
        Notice notice = noticeService.getNoticeById(noticeId);  // 공지사항 데이터 가져오기
        model.addAttribute("notice", notice);  // 데이터를 모델에 추가
        return "notice/noticeUpdateForm";  // 수정 폼으로 이동
    }

    // 공지사항 수정 처리
    @RequestMapping("/noticeUpdate.do")
    public String noticeUpdate(Notice notice) {
        noticeService.updateNotice(notice);  // 공지사항 수정 처리
        return "redirect:/noticeList.do";  // 수정 후 공지사항 목록으로 이동
    }
    
    // 공지사항 삭제 처리
    @RequestMapping("/noticeDelete.do")
    public String noticeDelete(@RequestParam("noticeId") int noticeId) {
        noticeService.deleteNotice(noticeId);  // 공지사항 삭제
        return "redirect:/noticeList.do";  // 삭제 후 공지사항 목록으로 이동
    }
}
