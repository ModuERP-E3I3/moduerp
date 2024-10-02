package com.e3i3.moduerp.email.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.e3i3.moduerp.email.model.dto.Email;
import com.e3i3.moduerp.email.model.service.EmailService;

@Controller
public class EmailController {
    @Autowired
    private EmailService emailService;

    // 이메일 전송 페이지로 이동
    @GetMapping("/send.do")
    public String sendEmailPage() {
        return "email/sendEmail"; // 전송 페이지 뷰 이름
    }

    // 이메일 전송 처리
    @PostMapping("/sending.do")
    public String sendEmail(Email email, HttpSession session, Model model) {
        // 로그인 유저의 이메일 주소 가져오기
        String senderEmail = (String) session.getAttribute("email");
        email.setSenderEmail(senderEmail);
        
        emailService.insertEmail(email);
        model.addAttribute("message", "이메일이 전송되었습니다.");
        
        return "redirect:/email/sent"; // 전송 완료 페이지
    }

    // 받은 이메일 목록 조회
    @GetMapping("/inbox.do")
    public String inbox(Model model, HttpSession session) {
        String recipientEmail = (String) session.getAttribute("email");
        List<Email> emails = emailService.selectEmailsByRecipient(recipientEmail);
        model.addAttribute("emails", emails);
        
        return "email/inbox"; // 받은 편지함 페이지 뷰 이름
    }

    // 보낸 이메일 목록 조회
    @GetMapping("/sent.do")
    public String sent(Model model, HttpSession session) {
        String senderEmail = (String) session.getAttribute("email");
        List<Email> emails = emailService.selectEmailsBySender(senderEmail);
        model.addAttribute("emails", emails);
        
        return "email/sent"; // 보낸 편지함 페이지 뷰 이름
    }
    
    // 이메일 전체 페이지
    @GetMapping("/email/list.do")
    public String emailListPage(HttpSession session, Model model) {
    	String userEmailUUID = (String) session.getAttribute("email"); // 세션에서 UUID 가져오기
        String userEmail = userEmailUUID != null ? userEmailUUID.toString() : null; // UUID를 String으로 변환

        List<Email> emails = emailService.selectEmailsByUser(userEmail); // 로그인 유저의 이메일로 받은/보낸 이메일 조회
        model.addAttribute("emails", emails);
        return "email/emailList"; // 이메일 목록을 보여주는 JSP 페이지 이름
    }
}
