package com.e3i3.moduerp.TossPayment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
public class TossPaymentController {

    // 메인 페이지로 이동 (card_registration.jsp를 렌더링)
    @RequestMapping("payment.do")
    public String forwardMain() {
        return "regular_payment/card_registration";
    }

    // 카드 등록하기 클릭 시 regular_payment.jsp로 이동
    @RequestMapping("regularPayment.do")
    public String forwardToRegularPayment() {
        return "regular_payment/regular_payment";
    }

    // 카드 등록 성공 시 빌링키 발급 처리
    @RequestMapping("regularPayment/success.do")
    public ModelAndView billingSuccess(@RequestParam("customerKey") String customerKey,
                                       @RequestParam("authKey") String authKey) {
        // 빌링키 발급 API 호출
        String responseBody = issueBillingKey(customerKey, authKey);

        // billing_success.jsp로 응답 본문과 함께 이동
        ModelAndView mav = new ModelAndView("regular_payment/billing_success");
        mav.addObject("responseBody", responseBody); // 응답 데이터 JSP로 전달
        return mav;
    }

    // 카드 등록 실패 시 실패 페이지로 이동
    @RequestMapping("regularPayment/fail.do")
    public String billingFail(@RequestParam("code") String errorCode,
                              @RequestParam("message") String errorMessage) {
        return "regular_payment/billing_fail";
    }

    // 빌링키 발급 API 호출 메서드
    private String issueBillingKey(String customerKey, String authKey) {
        try {
            // HttpClient 생성
            HttpClient client = HttpClient.newHttpClient();

            // 요청 본문 설정
            String requestBody = String.format("{\"authKey\":\"%s\", \"customerKey\":\"%s\"}", authKey, customerKey);

            // API 요청 생성
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/billing/authorizations/issue"))
                .header("Authorization", "Basic dGVzdF9za195WnFta0tlUDhnTmI0UnpYUDBHWXJiUVJ4QjlsOg==") // Base64 인코딩된 시크릿 키
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

            // API 호출 및 응답 받기
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 응답 상태 코드 확인 및 응답 본문 반환
            if (response.statusCode() == 200) {
                return response.body();  // 응답 본문을 반환 (JSON 형식)
            } else {
                throw new RuntimeException("Failed to issue billing key: " + response.statusCode());
            }

        } catch (Exception e) {
            throw new RuntimeException("Billing key request failed: " + e.getMessage(), e);
        }
    }
}
