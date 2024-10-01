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

    // 공지사항 리스트
    @RequestMapping("/noticeList.do")
    public String noticeList(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 20;
        int totalNotices = noticeService.getNoticeCount();
        int totalPages = (int) Math.ceil((double) totalNotices / pageSize);
        List<Notice> noticeList = noticeService.getNoticeList(page, 20);

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "notice/noticeList"; // noticeList.jsp
    }

    // 공지사항 상세 보기
    @RequestMapping("/noticeDetail.do")
    public String noticeDetail(@RequestParam("noticeId") int noticeId, Model model) {
        Notice notice = noticeService.getNoticeById(noticeId);
        model.addAttribute("notice", notice);
        return "notice/noticeDetail"; // noticeDetail.jsp
    }
}