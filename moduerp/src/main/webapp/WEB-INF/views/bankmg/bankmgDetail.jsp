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
<h2>계좌 상세 정보</h2>
    <table border="1">
        <tr>
            <th>계좌명</th>
            <td>${bankmg.bankName}</td>
        </tr>
        <tr>
            <th>계좌번호</th>
            <td>${bankmg.bankNumber}</td>
        </tr>
        <tr>
            <th>계좌 소유자</th>
            <td>${bankmg.bankHolder}</td>
        </tr>
        <tr>
            <th>잔액</th>
            <td>${bankmg.balance}</td>
        </tr>
        <tr>
            <th>거래 금액</th>
            <td>${bankmg.transactionPrice}</td>
        </tr>
        <tr>
            <th>거래 구분</th>
            <td>${bankmg.transactionType}</td>
        </tr>
        <tr>
            <th>거래 일자</th>
            <td>${bankmg.transactionDate}</td>
        </tr>
    </table>
    
    <!-- 수정 및 삭제 버튼 -->
    <button class="btn green" onclick="location.href='${pageContext.request.contextPath}/bankmg/updateForm?bankId=${bankmg.bankId}'">수정</button>
    <button class="btn red" onclick="if(confirm('정말로 삭제하시겠습니까?')) location.href='${pageContext.request.contextPath}/bankmg/delete?bankId=${bankmg.bankId}'">삭제</button>
</body>
</html>