<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
  <title>�����ϱ�</title>
  <meta charset="utf-8" />
  <!-- �佺���̸��� ����â SDK �߰� -->
  <script src="https://js.tosspayments.com/v1/payment"></script>
</head>
<body>
  <script>
    // ------ Ŭ���̾�Ʈ Ű�� ��ü �ʱ�ȭ ------
    var clientKey = "test_ck_DpexMgkW36xKMDndJL4NrGbR5ozO";
    var tossPayments = TossPayments(clientKey);
    tossPayments
      .requestBillingAuth("ī��", {
        customerKey: 'TTczElMVwlKdjksNlMS7r',
        successUrl: "http://localhost:8080/moduerp/regularPayment/success.do", // ���� ��η� ����
        failUrl: "http://localhost:8080/moduerp/regularPayment/fail.do", // ���� ��η� ����
      })
      .catch(function (error) {
        if (error.code === "USER_CANCEL") {
          // ���� ���� ����â�� �ݾ��� �� ���� ó��
        }
      });
  </script>
</body>
</html>
