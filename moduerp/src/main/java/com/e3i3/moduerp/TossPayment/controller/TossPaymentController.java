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

    // ���� �������� �̵� (card_registration.jsp�� ������)
    @RequestMapping("payment.do")
    public String forwardMain() {
        return "regular_payment/card_registration";
    }

    // ī�� ����ϱ� Ŭ�� �� regular_payment.jsp�� �̵�
    @RequestMapping("regularPayment.do")
    public String forwardToRegularPayment() {
        return "regular_payment/regular_payment";
    }

    // ī�� ��� ���� �� ����Ű �߱� ó��
    @RequestMapping("regularPayment/success.do")
    public ModelAndView billingSuccess(@RequestParam("customerKey") String customerKey,
                                       @RequestParam("authKey") String authKey) {
        // ����Ű �߱� API ȣ��
        String responseBody = issueBillingKey(customerKey, authKey);

        // billing_success.jsp�� ���� ������ �Բ� �̵�
        ModelAndView mav = new ModelAndView("regular_payment/billing_success");
        mav.addObject("responseBody", responseBody); // ���� ������ JSP�� ����
        return mav;
    }

    // ī�� ��� ���� �� ���� �������� �̵�
    @RequestMapping("regularPayment/fail.do")
    public String billingFail(@RequestParam("code") String errorCode,
                              @RequestParam("message") String errorMessage) {
        return "regular_payment/billing_fail";
    }

    // ����Ű �߱� API ȣ�� �޼���
    private String issueBillingKey(String customerKey, String authKey) {
        try {
            // HttpClient ����
            HttpClient client = HttpClient.newHttpClient();

            // ��û ���� ����
            String requestBody = String.format("{\"authKey\":\"%s\", \"customerKey\":\"%s\"}", authKey, customerKey);

            // API ��û ����
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tosspayments.com/v1/billing/authorizations/issue"))
                .header("Authorization", "Basic dGVzdF9za195WnFta0tlUDhnTmI0UnpYUDBHWXJiUVJ4QjlsOg==") // Base64 ���ڵ��� ��ũ�� Ű
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

            // API ȣ�� �� ���� �ޱ�
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // ���� ���� �ڵ� Ȯ�� �� ���� ���� ��ȯ
            if (response.statusCode() == 200) {
                return response.body();  // ���� ������ ��ȯ (JSON ����)
            } else {
                throw new RuntimeException("Failed to issue billing key: " + response.statusCode());
            }

        } catch (Exception e) {
            throw new RuntimeException("Billing key request failed: " + e.getMessage(), e);
        }
    }
}
