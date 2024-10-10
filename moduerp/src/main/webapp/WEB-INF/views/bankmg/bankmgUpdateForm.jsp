<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>erpMain</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/bankmg/update" method="post">
        <input type="hidden" name="bankId" value="${bankmg.bankId}"/>
        <label>은행명:</label> <input type="text" name="bankName" value="${bankmg.bankName}"/><br/>
        <label>계좌번호:</label> <input type="text" name="bankNumber" value="${bankmg.bankNumber}"/><br/>
        <label>계좌 소유자:</label> <input type="text" name="bankHolder" value="${bankmg.bankHolder}"/><br/>
        <label>잔액:</label> <input type="text" name="balance" value="${bankmg.balance}"/><br/>
        <label>거래 금액:</label> <input type="text" name="transactionPrice" value="${bankmg.transactionPrice}"/><br/>
        <label>거래 구분:</label> <input type="text" name="transactionType" value="${bankmg.transactionType}"/><br/>
        <label>거래 일자:</label> <input type="date" name="transactionDate" value="${bankmg.transactionDate}"/><br/>
        <button type="submit">수정</button>
    </form>
</body>
</html>