<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>noticeDetail</title>
</head>
<body>

<!-- 메뉴바 임포트 -->
<c:import url="/WEB-INF/views/common/menubar.jsp" />

<h1>공지사항 상세 보기</h1>

<div>
    <h3>${notice.title}</h3>
    <p>작성자: ${notice.writer}</p>
    <p>작성일: ${notice.createdDate}</p>
    <hr>
    <p>${notice.content}</p>
</div>

<a href="noticeList.do">목록으로 돌아가기</a>

<!-- 푸터 임포트 -->
<c:import url="/WEB-INF/views/common/footer.jsp" />

</body>
</html>