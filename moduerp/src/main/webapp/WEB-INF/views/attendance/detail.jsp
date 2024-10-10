<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

/*추가 css*/
body {
	font-family: Arial, sans-serif;
}

.detail-container {
	width: 60%;
	margin: 50px auto;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	background-color: #f9f9f9;
}

.detail-title {
	text-align: center;
	font-size: 24px;
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 12px;
	text-align: left;
}

th {
	background-color: #f4f4f4;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

.button-container {
	text-align: center;
	margin-top: 30px;
}

.button-container button {
	padding: 10px 20px;
	margin: 5px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.button-container .edit-button {
	background-color: #4CAF50;
	color: white;
}

.button-container .cancel-button {
	background-color: #f44336;
	color: white;
}

.button-container .back-button {
	background-color: #bbb;
}
</style>
</head>

</script>
<body>
	<!-- 서브헤더 JSP 임포트 -->
	<c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

	<!-- 위에 하얀 박스 -->
	<div class="top-content-box">
		<ul id="menubar">
			<li><a href="<c:url value='/attendance.do' />"><i
					class="fas fa-bullhorn"></i> 출퇴근</a></li>
			<li><a href="<c:url value='/leave.do' />"><i
					class="fas fa-clipboard"></i> 근태문서</a></li>
			<li><a href="<c:url value='/email/inbox.do' />"> <i
					class="fas fa-envelope"></i> 이메일
			</a></li>
		</ul>
	</div>

	<!-- 메인 콘텐츠 박스 -->
	<div class="content-box">
		<div class="content-title">근태</div>
		<table>
			<tr>
				<th>신청 유형</th>
				<td>${request.applicationType}</td>
			</tr>
			<tr>
				<th>신청자</th>
				<td>${request.requesterName}</td>
			</tr>
			<tr>
				<th>시작 날짜</th>
				<td><fmt:formatDate value="${request.startDate}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>종료 날짜</th>
				<td><fmt:formatDate value="${request.endDate}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
		<tr>
    <th>시작 시간</th>
    <td>
        <c:choose>
            <c:when test="${request.startTime != null}">
                <fmt:formatDate value="${request.startTimeAsDate}" pattern="HH:mm" />
            </c:when>
            <c:otherwise>없음</c:otherwise>
        </c:choose>
    </td>
</tr>
<tr>
    <th>종료 시간</th>
    <td>
        <c:choose>
            <c:when test="${request.endTime != null}">
                <fmt:formatDate value="${request.endTimeAsDate}" pattern="HH:mm" />
            </c:when>
            <c:otherwise>없음</c:otherwise>
        </c:choose>
    </td>
</tr>

			<tr>
				<th>근태 사유</th>
				<td>${request.reason}</td>
			</tr>
			<tr>
				<th>첨부 파일</th>
				<td><c:choose>
						<c:when test="${request.attachment != null}">
							<a href="/attachments/${request.attachment}">${request.attachment}</a>
						</c:when>
						<c:otherwise>첨부 파일 없음</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<th>결재자</th>
				<td>${request.approverName}</td>
			</tr>
			<tr>
				<th>비고</th>
				<td>${request.remarks}</td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>${request.contact}</td>
			</tr>
			<tr>
				<th>상태</th>
				<td>${request.status}</td>
			</tr>
			<tr>
				<th>승인 여부</th>
				<td><c:choose>
						<c:when test="${request.isApproved == 'Y'}">승인됨</c:when>
						<c:otherwise>미승인</c:otherwise>
					</c:choose></td>
			</tr>
		</table>

		<div class="button-container">
			<button class="edit-button"
				onclick="location.href='<c:url value="/attendanceRequest/edit/${request.attendancerequestId}.do"/>'">수정하기</button>
			<button class="cancel-button"
				onclick="location.href='<c:url value="/attendanceRequest/cancel/${request.attendancerequestId}.do"/>'">신청
				취소</button>
			<button class="back-button"
				onclick="location.href='<c:url value="/attendanceRequest/mylist.do"/>'">목록으로
				돌아가기</button>
		</div>



	</div>
</body>

<script>
	
</script>

</html>
