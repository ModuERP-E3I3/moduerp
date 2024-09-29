<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
  <title>결제하기</title>
  <meta charset="utf-8" />
  <!-- 토스페이먼츠 결제창 SDK 추가 -->
  <script src="https://js.tosspayments.com/v1/payment"></script>
</head>
<body>
  <script>
    // ------ 클라이언트 키로 객체 초기화 ------
    var clientKey = "test_ck_DpexMgkW36xKMDndJL4NrGbR5ozO";
    var tossPayments = TossPayments(clientKey);
    tossPayments
      .requestBillingAuth("카드", {
        customerKey: 'TTczElMVwlKdjksNlMS7r',
        successUrl: "http://localhost:8080/moduerp/regularPayment/success.do", // 절대 경로로 변경
        failUrl: "http://localhost:8080/moduerp/regularPayment/fail.do", // 절대 경로로 변경
      })
      .catch(function (error) {
        if (error.code === "USER_CANCEL") {
          // 결제 고객이 결제창을 닫았을 때 에러 처리
        }
      });
  </script>
</body>
</html>
