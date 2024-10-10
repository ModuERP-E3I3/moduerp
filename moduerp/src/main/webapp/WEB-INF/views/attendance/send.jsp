<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url value="/attendanceRequest/submit.do" var="submitUrl" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>근태 요청서 작성</title>
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
</style>
</head>

<script type="text/javascript">
function toggleTimeFields() {
    // 시간 필드 활성화 (모든 신청 유형에 대해 입력 가능)
    var startTimeField = document.getElementById("startTime");
    var endTimeField = document.getElementById("endTime");

    // 비활성화 해제
    startTimeField.disabled = false;
    endTimeField.disabled = false;
}
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        // 드롭다운에서 선택하면 approver 필드에 값 설정
        $('#approverSelect').change(function() {
            var selectedApprover = $(this).val(); // 선택된 결재자 값
            $('#approver').val(selectedApprover);  // approver 필드에 설정
        });

    
    // 폼 제출 전 유효성 검사
    $("form").submit(function(event) {
            var startDate = $('#startDate').val();
            var endDate = $('#endDate').val();
            var approver = $('#approver').val();

            // 유효성 검사 조건
            if (applicationType === "" || startDate === "" || endDate === "" || approver === "") {
                alert("신청 유형, 시작 날짜, 종료 날짜, 그리고 결재자를 모두 입력해야 합니다.");
                event.preventDefault(); // 폼 제출 방지
                return;
        }
        
    });
    
    // 임시 저장 버튼 클릭 시 status를 임시저장으로 설정
    $('#saveBtn').click(function() {
        $('#status').val("임시저장"); // 임시 저장 상태로 설정
        $('form').attr('action', '<c:url value="/attendanceRequest/save.do" />'); // 폼 액션 URL 변경
        //event.preventDefault(); // 기본 폼 제출 방지
        $('form').submit(); // 폼 제출
    });

    // 제출 버튼 클릭 시 status를 제출완료로 설정
    $('#submitBtn').click(function() {
        $('#status').val("제출완료"); // 제출 상태로 설정
    });
    
    });

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
					class="fas fa-clipboard"></i> 문서통합</a></li>
			<li><a href="<c:url value='/email/inbox.do' />"> <i
					class="fas fa-envelope"></i> 이메일
			</a></li>
		</ul>
	</div>

	<!-- 메인 콘텐츠 박스 -->
	<div class="content-box">
		<div class="content-title">근태신청</div>
		<!-- 근태 요청서 작성 폼 -->
		
		<form:form method="post" action="${submitUrl}" modelAttribute="attendanceRequest" oninput="toggleTimeFields()">
		<!-- JSP의 숨겨진 필드로 attendancerequestId 전달 -->
		<form:input path="attendancerequestId" type="hidden" />
    <table>
        <tr>
            <td>*신청 유형:</td>
            <td>
                <form:select path="applicationType" id="applicationType" onchange="toggleTimeFields()">
                    <form:option value="">신청 유형을 선택하세요</form:option>
                    <!-- 기본 선택 옵션 추가 -->
                    <form:option value="연차">연차</form:option>
                    <form:option value="병가">병가</form:option>
                    <form:option value="조퇴">조퇴</form:option>
                    <form:option value="반차">반차</form:option>
                    <form:option value="외근">외근</form:option>
                    <form:option value="출장">출장</form:option>
                </form:select>
            </td>
        </tr>
<tr>
    <td>*시작 날짜:</td>
    <td><form:input path="startDate" type="date" id="startDate" /></td>
</tr>
<tr>
    <td>*종료 날짜:</td>
    <td><form:input path="endDate" type="date" id="endDate" /></td>
</tr>

        <tr>
            <td>시작 시간 (선택 사항):</td>
            <td><form:input path="startTime" type="time" id="startTime" /></td>
        </tr>
        <tr>
            <td>종료 시간 (선택 사항):</td>
            <td><form:input path="endTime" type="time" id="endTime" /></td>
        </tr>
        <tr>
            <td>근태 사유:</td>
            <td><form:textarea path="reason" rows="4" cols="50"></form:textarea></td>
        </tr>
        <tr>
            <td>첨부 파일:</td>
            <td><input type="file" name="attachment" /></td>
        </tr>

        <!-- 결재자 선택 드롭다운 -->
        <tr>
            <td>*결재자:</td>
            <td>
                <!-- 결재자 드롭다운 메뉴 생성 -->
                <form:select path="approver" id="approverSelect" onchange="updateApproverHiddenField()">
                    <form:option value="">결재자를 선택하세요</form:option>
                    <c:forEach var="employee" items="${employees}">
                        <!-- 직원 이름과 직책을 결합하여 표시 -->
                        <form:option value="${employee.uuid}">${employee.empName}(${employee.jobId})</form:option>
                    </c:forEach>
                </form:select>
            </td>
        </tr>

        <tr>
            <td>비고:</td>
            <td><form:textarea path="remarks" rows="2" cols="50"></form:textarea></td>
        </tr>
        <tr>
            <td>연락처:</td>
            <td><form:input path="contact" /></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="제출" id="submitBtn" />
                <button type="button" id="saveBtn" onclick="setStatusAndSubmit('saved')">임시 저장</button>
            </td>
        </tr>
    </table>

    <!-- 숨겨진 status와 isApproved 필드 추가 -->
    <input type="hidden" id="status" name="status" value="제출완료" />
    <input type="hidden" id="isApproved" name="isApproved" value="N" />

</form:form>
	</div>
</body>

<script>
	
</script>

</html>
