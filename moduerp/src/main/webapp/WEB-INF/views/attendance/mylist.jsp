<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>근태신청 목록</title>
<script type="text/javascript">
    var baseUrl = '<c:url value="/" />';
</script>
<style type="text/css">
.top-content-box {
	width: 98%; /* 화면에 가득 차지 않게 */
	/* height: 6vh; */
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
	min-height: 99.3px; /* 최소 높이 설정 */
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

.content-box {
    width: 98%; /* 화면에 가득 차지 않게 */
    height: 70vh;
    background-color: white;
    margin-left: 1%;
    margin-right: 5%;
    margin-top: 0; /* 겹치도록 상단 마진 조정 */
    border: 1px solid #ccc;
    border-radius: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    position: relative; /* z-index가 제대로 작동하도록 설정 */
    padding: 20px;
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

tr:hover {
	background-color: #f5f5f5;
}

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

ul.nav {
	margin-bottom: 0; /* 하단 여백 제거 */
}
/* 탭 스타일 */
.nav-link {
    background-color: white !important; /* 기본 탭 배경색 */
    border: 1px solid #ddd; /* 테두리 설정 */
    border-radius: 5px 5px 0 0; /* 탭 둥근 모서리 */
    margin-right: 2px; /* 탭 간격 */
    color: black !important; /* 기본 글자색 */
}

.nav-link.active, .nav-item.dropdown .nav-link.active {
    background-color: #007bff !important; /* 활성화된 탭의 배경색 */
    color: white !important; /* 활성화된 탭의 글자색 */
    border-bottom: none; /* 활성화된 탭의 하단 테두리 제거 */
}

.dropdown-item {
    color: black; /* 기본 글자색 */
    transition: background 0.3s ease; /* 호버 효과 */
}


.dropdown-item:focus,
.dropdown-item:active {
    background-color: white !important;
    color: black !important;
    outline: none;
}




/* 드롭다운 메뉴 스타일 */
.dropdown-menu {
    background-color: white; /* 배경색 */
    border: 1px solid #ddd; /* 테두리 설정 */
    border-radius: 5px; /* 둥근 모서리 */
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
}
.nav-item {
    margin-bottom: -1px; /* 탭 하단 여백 제거 */
}

</style>
</head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js">

</script>

<script type="text/javascript">
function renderTableRows(documents, containerId) {
    var tableRows = "";
    documents.forEach(function(document) {
        tableRows += "<tr>" +
            "<td>" + document.applicationType + "</td>" +
            "<td>" + document.startDate.split(" ")[0] + "</td>" +
            "<td>" + document.endDate.split(" ")[0] + "</td>" +
            "<td>" + document.status + "</td>" +
            "<td>" + document.approverName + "</td>" +
            "<td><a href='" + baseUrl + "attendanceDocument/detail/" + document.attendancerequestId + ".do' class='button'>상세보기</a></td>" +
            "</tr>";
    });
    $('#' + containerId + ' tbody').html(tableRows);
}

function loadDataWithCondition(url, containerId) {
    $.ajax({
        url: url,
        dataType: "json",  // JSON 데이터임을 명시
        success: function(response) {
            renderTableRows(response, containerId);  // 데이터를 테이블로 렌더링
        },
        error: function() {
            console.error('데이터 로드 중 오류 발생');
        }
    });
}

//5초마다 각 컨테이너에 대해 AJAX 요청
setInterval(function() {
    loadDataWithCondition('<c:url value="/data/pendingApprovalRequests.do" />', 'pendingApprovalRequests-container');
    loadDataWithCondition('<c:url value="/data/approvedOrRejected_r.do" />', 'approvedOrRejected_r-container');
    loadDataWithCondition('<c:url value="/data/notYetProcessed.do" />', 'notYetProcessed-container');
    loadDataWithCondition('<c:url value="/data/approvedOrRejected.do" />', 'approvedOrRejected-container');
}, 5000);

</script>

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

<ul class="nav nav-tabs" style="margin-top: 0.9%; margin-left: 1%; width: 97%; position: relative; z-index: 10;">
    <!-- 결재 요청 드롭다운 -->
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle active" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">
            결재 요청
        </a>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item active" id="req-waiting-tab" data-bs-toggle="tab" href="#req-waiting">결재 요청 대기</a></li>
            <li><a class="dropdown-item" id="req-completed-tab" data-bs-toggle="tab" href="#req-completed">결재 요청 완료</a></li>
        </ul>
    </li>

    <!-- 결재 문서 드롭다운 -->
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">
            결재 문서
        </a>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item" id="doc-waiting-tab" data-bs-toggle="tab" href="#doc-waiting">결재 문서 대기</a></li>
            <li><a class="dropdown-item" id="doc-completed-tab" data-bs-toggle="tab" href="#doc-completed">결재 문서 완료</a></li>
        </ul>
    </li>
</ul>

<!-- 탭 내용 -->
<div class="tab-content">
    <!-- 결재 요청 대기 -->
    <div class="tab-pane fade show active" id="req-waiting">
        <div class="content-box">
            <c:choose>
                <c:when test="${empty pendingApprovalRequests}">
                    <div class="empty-message-box" style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); text-align: center;">
                        <p style="font-size: 24px; color: #333; font-weight: bold;">승인할 결재 요청이 없습니다.</p>
                    </div>
                </c:when>
                
                <c:otherwise>
                <div id="pendingApprovalRequests-container">
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
                        	
                            <c:forEach var="request" items="${pendingApprovalRequests}">
                                <tr>
                                    <td>${request.applicationType}</td>
                                    <td>${request.startDate.split(" ")[0]}</td>
                                    <td>${request.endDate.split(" ")[0]}</td>
                                    <td>${request.status}</td>
                                    <td>${request.approverName}</td>
                                    <td>
                                        <a href="<c:url value='/attendanceDocument/detail/${request.attendancerequestId}.do' />" class="button">상세보기</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    </div>
                </c:otherwise>
                
                
                
            </c:choose>
        </div>
    </div>

    <!-- 결재 요청 완료 -->
    <div class="tab-pane fade" id="req-completed">
        <div class="content-box">
            <c:choose>
                <c:when test="${empty approvedOrRejected_r}">
                    <div class="empty-message-box" style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); text-align: center;">
                        <p style="font-size: 24px; color: #333; font-weight: bold;">승인한 결재 요청이 없습니다.</p>
                    </div>
                </c:when>
                <c:otherwise>
                  <div id="approvedOrRejected_r-container">
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
                      
                            <c:forEach var="request" items="${approvedOrRejected_r}">
                                <tr>
                                    <td>${request.applicationType}</td>
                                    <td>${request.startDate.split(" ")[0]}</td>
                                    <td>${request.endDate.split(" ")[0]}</td>
                                    <td>${request.status}</td>
                                    <td>${request.approverName}</td>
                                    <td>
                                        <a href="<c:url value='/attendanceDocument/detail/${request.attendancerequestId}.do' />" class="button">상세보기</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                   </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!-- 결재 문서 대기 -->
    <div class="tab-pane fade" id="doc-waiting">
        <div class="content-box">
<c:choose>
    <c:when test="${notYetProcessed == null || empty notYetProcessed}">
        <div class="empty-message-box" style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); text-align: center;">
            <p style="font-size: 24px; color: #333; font-weight: bold;">승인받을 결재 문서가 없습니다.</p>
            <button class="apply-button" onclick="location.href='<c:url value='/attendanceDocument/send.do' />'" style="margin-top: 20px; padding: 10px 20px; font-size: 16px; border: none; background-color: #4CAF50; color: white; border-radius: 5px; cursor: pointer;">
                근태 신청하기
            </button>
        </div>
    </c:when>
    <c:otherwise>
     <div id="notYetProcessed-container">
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
           
                <c:forEach var="document" items="${notYetProcessed}">
                        <tr>
                            <td>${document.applicationType}</td>
                            <td>${document.startDate.split(" ")[0]}</td>
                            <td>${document.endDate.split(" ")[0]}</td>
                            <td>${document.status}</td>
                            <td>${document.approverName}</td>
                            <td>
                                <a href="<c:url value='/attendanceDocument/detail/${document.attendancerequestId}.do' />" class="button">상세보기</a>
                            </td>
                        </tr>
                </c:forEach>
            </tbody>
        </table>
                </div>
    </c:otherwise>
</c:choose>

        </div>
    </div>

    <!-- 결재 문서 완료 -->
    <div class="tab-pane fade" id="doc-completed">
    <div class="content-box">
        <c:choose>
            <c:when test="${approvedOrRejected == null || empty approvedOrRejected}">
                <div class="empty-message-box" style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); text-align: center;">
                    <p style="font-size: 24px; color: #333; font-weight: bold;">승인받은 결재 문서가 없습니다.</p>
                </div>
            </c:when>
            <c:otherwise>
            <div id="approvedOrRejected-container">
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
                        <c:forEach var="document" items="${approvedOrRejected}">
                                <tr>
                                    <td>${document.applicationType}</td>
                                    <td>${document.startDate.split(" ")[0]}</td>
                                    <td>${document.endDate.split(" ")[0]}</td>
                                    <td>${document.status}</td>
                                    <td>${document.approverName}</td>
                                    <td>
                                        <a href="<c:url value='/attendanceDocument/detail/${document.attendancerequestId}.do' />" class="button">상세보기</a>
                                    </td>
                                </tr>
                        </c:forEach>
                    </tbody>
                </table>
                        </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

</div>


<script>
document.querySelectorAll('.dropdown-item').forEach(item => {
    item.addEventListener('mousedown', (e) => {
        // 클릭 시 스타일 초기화
        setTimeout(() => {
            e.target.style.backgroundColor = "white";  // 배경색 초기화
            e.target.style.color = "black";  // 글자색 초기화
            e.target.classList.remove("active"); // 'active' 클래스 제거
        }, 100);
    });
});

</script>




</body>

</html>
