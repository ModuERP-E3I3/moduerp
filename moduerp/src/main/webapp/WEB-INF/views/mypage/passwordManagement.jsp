<!-- /WEB-INF/views/content/passwordManagement.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 비밀번호 변경 폼 -->
<form action="${pageContext.request.contextPath}/updatePassword.do" method="post">
    <label for="currentPassword">현재 비밀번호:</label><br>
    <input type="password" id="currentPassword" name="currentPassword" required><br><br>
    
    <label for="newPassword">새 비밀번호:</label><br>
    <input type="password" id="newPassword" name="newPassword" required><br><br>
    
    <label for="confirmPassword">비밀번호 확인:</label><br>
    <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>
    
    <button type="submit">비밀번호 변경</button>
</form>
