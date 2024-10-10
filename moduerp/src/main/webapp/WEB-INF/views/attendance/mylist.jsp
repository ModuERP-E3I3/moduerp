<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>근태신청 목록</title>
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


/* 추가한 css */
 table {
        width: 100%;
        border-collapse: collapse;
    }
    table, th, td {
        border: 1px solid #ddd;
    }
    th, td {
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
    tr:hover {background-color: #f5f5f5;}
    .button {
        background-color: #4CAF50;
        border: none;
        color: white;
        padding: 5px 10px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 14px;
        margin: 4px 2px;
        cursor: pointer;
    }
    
    
    
    
    
    
    /* 테이블 스타일 */
.attendance-table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

.attendance-table th, .attendance-table td {
    border: 1px solid #ddd;
    padding: 10px;
    text-align: center;
}

.attendance-table th {
    background-color: #f4f4f4;
}

/* 빈 메시지 박스 스타일 */
.empty-message-box {
    text-align: center;
    padding: 50px;
    margin-top: 30px;
    background-color: #fafafa;
    border: 1px solid #ddd;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
}

.empty-message-box p {
    font-size: 18px;
    color: #333;
}

/* 버튼 스타일 */
.apply-button {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
    border-radius: 5px;
    margin-top: 20px;
    transition: background 0.3s ease;
}

.apply-button:hover {
    background-color: #0056b3;
}
    
    
</style>
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
					class="fas fa-clipboard"></i> 문서통합</a></li>
			<li><a href="<c:url value='/email/inbox.do' />"> <i
					class="fas fa-envelope"></i> 이메일
			</a></li>
		</ul>
	</div>

	<!-- 메인 콘텐츠 박스 -->
	<div class="content-box">
		<div class="content-title">내 근태 신청서 목록</div>

<c:choose>
    <%-- 1. 조회 결과가 없을 때 --%>
    <c:when test="${empty requests}">
         <%-- 중앙 정렬된 메시지와 버튼을 포함한 스타일 설정 --%>
        <div class="empty-message-box" style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); text-align: center;">
            <p style="font-size: 24px; color: #333; font-weight: bold;">조회할 근태 문서가 없습니다.</p>
            <button class="apply-button" onclick="location.href='<c:url value='/attendanceRequest/send.do' />'" style="margin-top: 20px; padding: 10px 20px; font-size: 16px; border: none; background-color: #4CAF50; color: white; border-radius: 5px; cursor: pointer;">
                근태 신청하기
            </button>
        </div>
    </c:when>

    <%-- 2. 조회 결과가 있을 때 --%>
    <c:otherwise>
        <table class="attendance-table">
            <thead>
                <tr>
                    <th>신청 유형</th>
                    <th>시작 날짜</th>
                    <th>종료 날짜</th>
                    <th>상태</th>
                    <th>결재자</th>
                    <th>상세보기</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="request" items="${requests}">
                    <tr>
                        <td>${request.applicationType}</td>
                        <td><fmt:formatDate value="${request.startDate}" pattern="yyyy-MM-dd" /></td>
                        <td><fmt:formatDate value="${request.endDate}" pattern="yyyy-MM-dd" /></td>
                        <td>${request.status}</td>
                        <td>${request.approverName}</td>
                        <td>
                            <a href="<c:url value='/attendanceRequest/detail/${request.attendancerequestId}.do' />" class="button">상세보기</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

    

	</div>
</body>

</html>
