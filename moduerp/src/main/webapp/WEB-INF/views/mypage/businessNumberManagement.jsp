<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/content/businessNumberManagement.jsp -->

<!-- 사업자번호 업데이트 폼 -->
<form action="updateBusinessNumber.do" method="post">
    <label for="businessNumber">사업자번호:</label>
    <input type="text" id="businessNumber" name="businessNumber" value="${businessNumber}" required>
    
    <button type="submit">사업자번호 업데이트</button>
</form>

<hr>

<!-- 현재 사업자번호 표시 (예시) -->
<h3>현재 등록된 사업자번호</h3>
<p>${businessNumber}</p>

<hr>

<!-- 사업자번호 삭제 폼 (예시) -->
<form action="deleteBusinessNumber.do" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');">
    <button type="submit">사업자번호 삭제</button>
</form>
