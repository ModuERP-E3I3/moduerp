<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/content/cardManagement.jsp -->

<!-- 카드 등록 폼 -->
<form action="addCard.do" method="post">
    <label for="cardNumber">카드 번호:</label>
    <input type="text" id="cardNumber" name="cardNumber" required>
    
    <label for="cardHolder">카드 소유자:</label>
    <input type="text" id="cardHolder" name="cardHolder" required>
    
    <label for="expiryDate">만료 날짜:</label>
    <input type="month" id="expiryDate" name="expiryDate" required>
    
    <button type="submit">카드 등록</button>
</form>

<hr>

<!-- 카드 목록 표시 (예시) -->
<h3>등록된 카드 목록</h3>
<table border="1" cellpadding="10" cellspacing="0">
    <tr>
        <th>카드 번호</th>
        <th>카드 소유자</th>
        <th>만료 날짜</th>
        <th>액션</th>
    </tr>
    <!-- 카드 목록을 동적으로 생성 -->
    <c:forEach var="card" items="${cards}">
        <tr>
            <td>${card.cardNumber}</td>
            <td>${card.cardHolder}</td>
            <td>${card.expiryDate}</td>
            <td>
                <a href="editCard.do?cardId=${card.id}">수정</a> |
                <a href="deleteCard.do?cardId=${card.id}" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
            </td>
        </tr>
    </c:forEach>
</table>
