<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출퇴근</title>

<!-- FullCalendar CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
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
	width: 45%; /* 패널 너비 조정 */
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

.current-datetime {
	padding: 20px;
}

/* 버튼 스타일 조정 */
.attendance-btns {
	display: flex;
	justify-content: space-between;
	width: 100%; /* 버튼 컨테이너 너비 100%로 변경 */
	margin-bottom: 10px; /* 아래 여백 조정 */
	margin-left: 20px;
}

.attendance-btns form {
	flex: 1; /* 폼을 균등하게 배치 */
	margin: 0 5px; /* 폼 간의 간격 추가 */
}

.attendance-btns button {
	width: 100%; /* 버튼을 폼의 너비에 맞게 확장 */
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
	font-size: 30px;
	margin-top: 10px;
	text-align: center; /* 가운데 정렬 */
	padding: 20px;
}

.work-time p {
	margin: 0; /* 단락 간 간격 제거 */
	padding: 0;
}

.work-time #workTime {
	font-size: 39px;
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
.indent {
    margin-left: 20px;
}

@media (max-width: 768px) {
    .content-box {
        flex-direction: column;
    }
    .attendance-btns {
        flex-direction: column;
    }
    .info-row {
        flex-direction: column;
    }
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
			<li><a href="<c:url value='/attendanceDocument/mylist.do' />"><i
					class="fas fa-clipboard"></i> 근태문서</a></li>
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
			<!-- 초기값 제거 -->
			<div class="current-datetime" id="current-datetime"></div>
			<div class="user-info">
				<strong> <c:out value="${userName}" /> 님,<br>안녕하세요.
				</strong>
			</div>

			<!-- 성공 메시지 표시 -->
			<c:if test="${not empty success}">
				<div style="color: green;margin-left: 20px ">${success}</div>
			</c:if>

			<!-- 오류 메시지 표시 -->
			<c:if test="${not empty error}">
				<div style="color: red; margin-left: 20px"">
					<strong>${error}</strong>
				</div>
			</c:if>

			<div class="attendance-btns">
				<!-- 출근 버튼 -->
				<form action="<c:url value='/attendance/clockIn.do' />"
					method="post" style="display: inline;">
					<button type="submit" class="clock-in" id="clockInBtn">출근</button>
				</form>
				<!-- 퇴근 버튼 -->
				<form action="<c:url value='/attendance/clockOut.do' />"
					method="post" style="display: inline;">
					<button type="submit" class="clock-out" id="clockOutBtn">퇴근</button>
				</form>
			</div>



			<!-- 출근/퇴근 상태 표시 -->
			<c:choose>
				<c:when test="${not empty attendance}">
					<p class="indent">
						출근 시간:
						<fmt:formatDate value="${attendance.clockInTime}"
							pattern="yyyy-MM-dd(E) a hh:mm" />
					</p>
					<c:if test="${empty attendance.clockOutTime}">
						<p class="indent">퇴근 시간: 아직 퇴근하지 않으셨습니다.</p>
					</c:if>
					<c:if test="${not empty attendance.clockOutTime}">
						<p class="indent">
							퇴근 시간:
							<fmt:formatDate value="${attendance.clockOutTime}"
								pattern="yyyy-MM-dd(E) a hh:mm" />
						</p>
						<p class="indent">초과 근무 시간: ${attendance.formattedOvertime}</p>
					</c:if>

				</c:when>
				<c:otherwise>
					<p style="padding-left: 20px">오늘 출근 기록이 없습니다.</p>
				</c:otherwise>
			</c:choose>

			<div class="work-time">
				<p>오늘의 근무시간:</p>
				<p id="workTime">${attendance.formattedTotWorkHrs}</p>
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
					<a href="<c:url value='/leave/leaveRequest.do' />" class="info-link">신청하기</a>
				</div>
				<!-- 결재 대기 -->
				<div class="info-box">
					<div class="info-label">결재 대기</div>
					<div class="info-value">5건</div>
					<a href="<c:url value='/approval/approvalInbox.do' />" class="info-link">결재하기</a>
				</div>
				<!-- 근태 신청 -->
				<div class="info-box">
					<div class="info-label">근태 신청</div>
					<div class="info-value">-</div>
					<a href="<c:url value='/attendanceDocument/send.do' />" class="info-link">신청하기</a>
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
		// FullCalendar 초기화
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

		// 현재 날짜와 시간을 실시간으로 업데이트하는 함수
		function updateDateTime() {
			const now = new Date();

			const year = now.getFullYear();
			const month = String(now.getMonth() + 1).padStart(2, '0');
			const date = String(now.getDate()).padStart(2, '0');
			const dayNames = ['일', '월', '화', '수', '목', '금', '토'];
			const day = dayNames[now.getDay()];

			let hours = now.getHours();
			const minutes = String(now.getMinutes()).padStart(2, '0');
			const period = hours >= 12 ? '오후' : '오전';
			if (hours > 12) {
				hours -= 12;
			} else if (hours === 0) {
				hours = 12;
			}
			hours = String(hours).padStart(2, '0');

			/* const formattedDateTime = `${year}-${month}-${date}(${day}) ${period} ${hours}:${minutes}`; */
			// 수정된 코드 (EL 충돌 방지)
			const formattedDateTime = year + '-' + month + '-' + date + '(' + day + ') ' + period + ' ' + hours + ':' + minutes;
			
			document.getElementById('current-datetime').innerText = formattedDateTime;

			// 콘솔 로그로 디버깅
			console.log("현재 시간 업데이트:", formattedDateTime);
		}

		// 근무 시간을 계산하는 함수
		function calculateWorkTime() {
			const clockInElem = document.getElementById('clock-in-time');
			const clockOutElem = document.getElementById('clock-out-time');
			const workTimeElem = document.getElementById('workTime');

			if (!clockInElem || !clockOutElem || !workTimeElem) return;

			const clockInText = clockInElem.innerText;
			const clockOutText = clockOutElem.innerText;

			// 출근 시간과 퇴근 시간이 모두 존재할 때만 계산
			if (clockInText.includes('출근 시간:') && clockOutText.includes('퇴근 시간:')) {
				const clockInTimeStr = clockInText.split('출근 시간: ')[1];
				const clockOutTimeStr = clockOutText.split('퇴근 시간: ')[1];

				// 시간을 Date 객체로 변환 (24시간 형식)
				const today = new Date();
				const [inHour, inMinute] = clockInTimeStr.split(':').map(Number);
				const [outHour, outMinute] = clockOutTimeStr.split(':').map(Number);

				const clockInDate = new Date(today.getFullYear(), today.getMonth(), today.getDate(), inHour, inMinute);
				const clockOutDate = new Date(today.getFullYear(), today.getMonth(), today.getDate(), outHour, outMinute);

				// 만약 퇴근 시간이 출근 시간보다 이른 경우 (예: 야간 근무), 퇴근 시간을 다음 날로 간주
				if (clockOutDate < clockInDate) {
					clockOutDate.setDate(clockOutDate.getDate() + 1);
				}

				const diffMs = clockOutDate - clockInDate;
				if (diffMs > 0) {
					const diffHrs = Math.floor(diffMs / (1000 * 60 * 60));
					const diffMins = Math.floor((diffMs % (1000 * 60 * 60)) / (1000 * 60));
					workTimeElem.innerText = `${diffHrs}h ${diffMins}m`;

					// 콘솔 로그로 디버깅
					console.log("근무 시간 계산:", `${diffHrs}h ${diffMins}m`);
				} else {
					workTimeElem.innerText = `00h 00m`;

					// 콘솔 로그로 디버깅
					console.log("근무 시간 초기화: 00h 00m");
				}
			} else {
				workTimeElem.innerText = `00h 00m`;

				// 콘솔 로그로 디버깅
				console.log("근무 시간 초기화: 00h 00m");
			}
		}

		// 현재 날짜와 시간 업데이트
		updateDateTime();
		setInterval(updateDateTime, 60000); // 매분 업데이트
		calculateWorkTime();
	});
</script>

</html>
