<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/content/cardManagement.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>빌링키 발급 성공</title>
<style type="text/css">
body, html {
	height: 100%;
	margin: 0;
	display: flex;
	justify-content: center; /* 좌우 가운데 정렬 */
	align-items: center; /* 상하 가운데 정렬 */
}

.container {
	text-align: center;
}

.btn {
	background-color: #4CAF50;
	color: white;
	padding: 5px 12px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 20px;
}

.logo{
	color: #4CAF50;
}
</style>

</head>
<body>
	<div class="container">
		<h1 class="logo">ModuERP</h1>
		<h2>결제 성공!</h2>
		<button class="btn" onclick="window.location.href='http://localhost:8080/moduerp/main.do';">메인페이지로 돌아가기</button>
	</div>
</body>
</html>
