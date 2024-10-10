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
 	<h2>은행 계좌 등록</h2>
    <form action="bankAddForm.do" method="post">
        <label>은행명:</label> 
        <input type="text" name="bankName" required/><br/>
        
        <label>계좌번호:</label> 
        <input type="text" name="bankNumber" required/><br/>
        
        <label>계좌 소유자:</label> 
        <input type="text" name="bankHolder" required/><br/>
        
        <label>잔액:</label> 
        <input type="number" name="balance" required/><br/>
        
        <label>거래 금액:</label> 
        <input type="number" name="transactionPrice" required/><br/>
        
        <label>거래 구분:</label> 
        <input type="text" name="transactionType" required/><br/>
        
        <label>거래 일자:</label> 
        <input type="date" name="transactionDate" required/><br/>
        
        <button type="submit">등록</button>
    </form>
</body>
</html>