<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
<meta charset="UTF-8">
<title>구매하지 않은 모듈</title>

<!-- FullCalendar CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap"
	rel="stylesheet">
<style type="text/css">
body {
	font-family: 'Roboto', sans-serif;
}

.top-content-box {
	width: 96%; /* 화면에 가득 차지 않게 */
	height: 6vh;
	background-color: white;
	margin-left: 1%;
	margin-right: 5%;
	margin-top: 1.8%;
	border: 1px solid #ccc;
	border-radius: 20px; /* 박스 둥글게 */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
	display: flex;
	justify-content: center; /* 수평 중앙 정렬 */
	align-items: center; /* 수직 중앙 정렬 */
	padding: 20px;
	font-size: 30px;
	font-weight: bold;
}

/* 하얀 박스 스타일 */
.content-box {
	width: 96%; /* 화면에 가득 차지 않게 */
	height: auto; /* 화면 높이의 78% */
	background-color: white;
	margin-left: 1%;
	margin-right: 5%;
	margin-top: 3%;
	border: 1px solid #ccc;
	border-radius: 20px; /* 박스 둥글게 */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
	position: relative;
	padding: 20px; /* 내부 여백 추가 */
	display: flex;
	justify-content: space-between;
	align-items: flex-start;
}

.content-box {
	display: flex;
	justify-content: center; /* 좌우 가운데 정렬 */
	align-items: center; /* 상하 가운데 정렬 */
	height: 500px; /* 높이 설정 */
}

.centered-content {
	text-align: center; /* 텍스트 가운데 정렬 */
}

#errorText {
	font-weight: bold;
	font-size: 30px;
	color: red;
}

#backBtn {
	margin-top: 5%;
	padding: 10px 20px;
	margin-left: 5px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 20px;
	background: #217770;
	font-weight: bold;
	color: white;
}
</style>



</head>

<body>
	<!-- 서브헤더 JSP 임포트 -->
	<c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

	<!-- 위에 하얀 박스 -->
	<div class="top-content-box"></div>

	<!-- 메인 콘텐츠 박스 -->
	<div class="content-box">
		<div class="centered-content">
			<div id="errorText">구매하지 않은 모듈입니다.</div>
			<div>
				<button id="backBtn" onclick="goBack()">되돌아가기</button>
			</div>
		</div>

	</div>
</body>

<script>
	function goBack() {
		window.location.href = document.referrer; // 이전 페이지로 이동 후 새로고침
	}
</script>

</html>