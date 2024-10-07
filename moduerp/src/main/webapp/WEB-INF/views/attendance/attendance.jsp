<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출퇴근</title>

<!-- FullCalendar CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.css"
	rel="stylesheet">

<style type="text/css">
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

/* ul의 기본 스타일 제거 */
#menubar {
	list-style: none; /* 기본 list-style 없애기 */
	padding: 0;
	margin: 0;
	display: flex; /* li를 가로로 배치하기 위해 flexbox 사용 */
	justify-content: center;
	align-items: center;
}

#menubar li {
	margin: 0 40px; /* li 간의 간격 추가 */
}

#menubar li a {
	color: black;
	text-decoration: none;
	font-size: 16px;
	display: block;
	padding: 10px 20px;
	transition: background 0.3s ease;
}

#menubar li a:hover {
	background-color: #f4f4f4;
	border-radius: 10px; /* 호버 시 살짝 둥근 배경 */
}

/* 하얀 박스 스타일 */
.content-box {
	width: 96%; /* 화면에 가득 차지 않게 */
	height: 70vh; /* 화면 높이의 78% */
	background-color: white;
	margin-left: 1%;
	margin-right: 5%;
	margin-top: 5%;
	border: 1px solid #ccc;
	border-radius: 20px; /* 박스 둥글게 */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
	position: relative;
	padding: 20px; /* 내부 여백 추가 */
	display: flex;
	justify-content: space-between;
	align-items: flex-start;
}

/* 제목 스타일 */
.content-title {
	position: absolute;
	top: -40px;
	left: 20px;
	font-size: 24px;
	color: white;
	font-weight: bold;
}

/* 왼쪽 패널 스타일 */
.left-panel {
	width: 35%; /* 패널 너비 조정 */
	display: flex;
	flex-direction: column;
	justify-content: center;
	margin-right: 20px; /* 오른쪽 마진 추가하여 간격 확보 */
}

.user-info {
	text-align: left; /* 왼쪽 정렬 */
	margin-bottom: 20px;
	font-size: 30px;
	padding: 20px;
}

/* 버튼 스타일 조정 */
.attendance-btns {
	display: flex;
	justify-content: space-between;
	width: 100%; /* 버튼 컨테이너 너비 100%로 변경 */
	margin-bottom: 10px; /* 아래 여백 조정 */
}

.attendance-btns button {
	width: 48%; /* 버튼 너비를 더 넓게 설정하고 간격을 최소화 */
	height: 60px; /* 버튼 높이 설정 */
	border: none;
	border-radius: 10px;
	cursor: pointer;
	font-size: 16px;
	margin: 0; /* 버튼 사이의 간격을 제거 */
}

.clock-in {
	background-color: #4CAF50;
	color: white;
}

.clock-out {
	background-color: #f44336;
	color: white;
}

/* 근무 시간 스타일 */
.work-time {
	font-size: 24px;
	margin-top: 10px;
	text-align: center; /* 가운데 정렬 */
}

.work-time p {
	margin: 0; /* 단락 간 간격 제거 */
	padding: 0;
}

.work-time #workTime {
	font-size: 24px;
	font-weight: bold;
}

/* 오른쪽 패널 및 캘린더 스타일 */
.right-panel {
	width: 100%;
	padding-left: 20px; /* 왼쪽 패딩 추가하여 간격 확보 */
}

.calendar-box {
	height: 500px; /* 높이를 픽셀 단위로 고정 설정 */
	border: 1px solid #ddd;
	border-radius: 15px;
	padding: 20px;
	margin-bottom: 20px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

/* 남은 연차, 결재 대기, 근태 신청을 한 줄로 표시하는 스타일 */
.info-row {
	display: flex;
	justify-content: space-around; /* 각 요소가 동일한 간격을 가지도록 조정 */
	margin-bottom: 20px;
}

.info-box {
	width: 30%; /* 너비를 줄여서 한 줄에 세 박스가 자연스럽게 배치되도록 설정 */
	padding: 10px; /* 내부 여백 조정 */
	text-align: center;
	border: 1px solid #ddd;
	border-radius: 10px;
	cursor: pointer;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	background-color: #fff;
	transition: background-color 0.3s ease;
}

.info-box:hover {
	background-color: #f4f4f4;
}

.info-label {
	font-size: 14px;
	font-weight: 500;
}

.info-value {
	font-size: 12px;
	margin-top: 5px;
	color: #555;
}

.info-link {
	text-decoration: none;
	color: #007BFF;
	font-size: 12px;
	margin-top: 8px;
	display: inline-block;
}
</style>

<!-- FullCalendar JS -->
<script
	src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js"></script>


</head>

<body>
	<!-- 서브헤더 JSP 임포트 -->
	<c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

	<!-- 위에 하얀 박스 -->
	<div class="top-content-box">
		<ul id="menubar">
			<li><a href="<c:url value='/attendance.do' />"><i
					class="fas fa-bullhorn"></i> 출퇴근</a></li>
			<li><a href="<c:url value='/leave.do' />"><i
					class="fas fa-clipboard"></i> 휴 가</a></li>
			<li><a href="<c:url value='/email/inbox.do' />"> <i
					class="fas fa-envelope"></i> 이메일
			</a></li>
		</ul>
	</div>

	<!-- 메인 콘텐츠 박스 -->
	<div class="content-box">
	<div class="content-title">출퇴근 관리</div>
		<!-- 왼쪽 출퇴근 관리 -->
		<div class="left-panel">
			<div class="user-info">
				<c:out value="${userName}" />
				님,<br>안녕하세요.
			</div>
			<div class="attendance-btns">
				<button class="clock-in" id="clockInBtn">출근</button>
				<button class="clock-out" id="clockOutBtn">퇴근</button>
			</div>
			<div class="work-time">
				<p>오늘의 근무시간:</p>
				<p id="workTime">00h 00m</p>
			</div>
		</div>

		<!-- 오른쪽 패널 -->
		<div class="right-panel">
			<!-- 남은 연차, 결재 대기, 근태 신청이 한 줄로 표시되는 영역 -->
			<div class="info-row">
				<!-- 남은 연차 -->
				<div class="info-box">
					<div class="info-label">남은 연차</div>
					<div class="info-value">12/15</div>
					<a href="<c:url value='/leave/apply.do' />" class="info-link">신청하기</a>
				</div>
				<!-- 결재 대기 -->
				<div class="info-box">
					<div class="info-label">결재 대기</div>
					<div class="info-value">5건</div>
					<a href="<c:url value='/approval/waiting.do' />" class="info-link">결재하기</a>
				</div>
				<!-- 근태 신청 -->
				<div class="info-box">
					<div class="info-label">근태 신청</div>
					<div class="info-value">-</div>
					<a href="<c:url value='/attendance/apply.do' />" class="info-link">신청하기</a>
				</div>
			</div>

			<div class="calendar-box">
				<div id="calendar" style="height: 100%; overflow: auto;"></div>
			</div>
		</div>
	</div>
</body>

<script>
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');

		if (calendarEl) {
			var calendar = new FullCalendar.Calendar(calendarEl, {
				initialView : 'dayGridMonth',
				headerToolbar : {
					left : 'prev,next today',
					center : 'title',
					right : 'dayGridMonth,timeGridWeek,timeGridDay'
				},
				height : '100%'
			});
			calendar.render();
		} else {
			console.error("캘린더 요소를 찾을 수 없습니다.");
		}
	});

	// 날짜와 시간 업데이트 함수
	function updateDateTime() {
		const now = new Date();
		const formattedDateTime = now.toLocaleString('ko-KR', {
			weekday : 'short',
			year : 'numeric',
			month : 'numeric',
			day : 'numeric',
			hour : '2-digit',
			minute : '2-digit'
		});
		document.getElementById('current-datetime').innerText = formattedDateTime;
	}
</script>

</html>
