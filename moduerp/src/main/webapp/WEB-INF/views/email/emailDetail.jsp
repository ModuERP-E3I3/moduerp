<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 상세 보기</title>

<style type="text/css">
/* 기존 스타일 */
.top-content-box {
    width: 96%;
    height: 6vh;
    background-color: white;
    margin-left: 1%;
    margin-right: 5%;
    margin-top: 1.8%;
    border: 1px solid #ccc;
    border-radius: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 20px;
    font-size: 30px;
    font-weight: bold;
}

#menubar {
    list-style: none;
    padding: 0;
    margin: 0;
    display: flex;
    justify-content: center;
    align-items: center;
}

#menubar li {
    margin: 0 40px;
    position: relative;
}

#menubar li a {
    color: black;
    text-decoration: none;
    font-size: 16px;
    display: block;
    padding: 10px 20px;
    transition: background 0.3s ease;
}

#menubar li a:hover, #menubar li a.active {
    background-color: #f4f4f4;
    border-radius: 10px;
}

.content-box {
    width: 96%;
    background-color: white;
    margin-left: 1%;
    margin-right: 5%;
    margin-top: 5%;
    border: 1px solid #ccc;
    border-radius: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    position: relative;
    padding: 20px;
}

.content-title {
    position: absolute;
    top: -40px;
    left: 20px;
    font-size: 24px;
    color: white;
    font-weight: bold;
}

/* 이메일 상세 보기 관련 스타일 */
.email-detail {
    margin: 20px auto;  /* 전체적인 마진 조정 */
    padding: 20px;
}

.email-detail .subject {
    font-size: 30px;
    font-weight: bold;
    margin-bottom: 15px;  /* 제목과 다른 요소 간격 */
    color: #333;
}

.email-detail .sender-recipient {
    font-size: 16px;
    margin-bottom: 20px;
}

.email-detail .sender-recipient label {
    font-weight: bold;
    color: #555;
    margin-right: 5px;
}

.email-detail .sent-date {
    font-size: 14px;
    color: #777;
    margin-bottom: 20px;
}

.email-detail .email-body {
    padding: 15px;
    background-color: #ffffff;
    border: 1px solid #ddd;
    border-radius: 10px;
    line-height: 1.6;
    margin-bottom: 15px;
    white-space: pre-wrap;  /* 줄바꿈과 공백 유지 */
}
/* 본문 내의 텍스트 스타일 */
.email-body p {
    margin: 0 0 15px 0;
}

.email-body code {
    display: block;
    background-color: #f4f4f4;
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #e1e1e1;
    font-family: 'Courier New', Courier, monospace;
    margin: 10px 0;
}
.email-body pre {
    background-color: #f4f4f4;
    padding: 15px;
    border-radius: 5px;
    border: 1px solid #e1e1e1;
    overflow: auto;
    white-space: pre-wrap;
    font-family: 'Courier New', Courier, monospace;
    line-height: 1.5;
}

/* 첨부 파일 링크 스타일 */
.email-detail .attachment {
    margin-top: 20px;
    margin-bottom: 20px;
}

.email-detail .attachment a {
     display: inline-block;
    padding: 5px 10px;
    background-color: #e7f3fe;
    color: #007bff;
    text-decoration: none;
    border-radius: 5px;
    border: 1px solid #cce5ff;
}

.email-detail .attachment a:hover {
     background-color: #cce5ff;
    border: 1px solid #b3d7ff;
}

/* 버튼 스타일 */
.btn-group {
    text-align: right;
    margin-top: 30px;
}

.btn {
    padding: 10px 20px;
    background-color: #007BFF;
    color: white;
    text-decoration: none;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.btn:hover {
    background-color: #0056b3;
}

/* 드롭다운 메뉴 스타일 */
.dropdown-menu {
	display: none; /* 기본적으로 숨김 */
	position: absolute;
	top: 100%; /* 부모 요소의 바로 아래에 위치 */
	left: 0; /* 부모 요소의 왼쪽에 맞춤 */
	background-color: white;
	border: 1px solid #ccc;
	z-index: 1;
	margin-top: 5px; /* 상단 여백 */
	border-radius: 5px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	list-style: none;
	padding: 0;
	white-space: nowrap;
}

/* 드롭다운 메뉴 항목 스타일 */
.dropdown-item {
	padding: 10px 20px; /* 내부 여백 */
	text-decoration: none; /* 밑줄 제거 */
	color: black; /* 글자 색상 */
	display: block; /* 블록 형태 */
}

.dropdown-item:hover {
	background-color: #f4f4f4; /* 호버 시 배경색 변경 */
}

/* 드롭다운 메뉴 보이기 및 숨기기 클래스 */
.show {
    display: block;
}
</style>
</head>

<body>
    <!-- 서브헤더 JSP 임포트 -->
    <c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

    <!-- 위에 하얀 박스 -->
    <div class="top-content-box">
        <ul id="menubar">
            <li><a href="<c:url value='/attendance.do' />"><i class="fas fa-bullhorn"></i> 출퇴근</a></li>
            <li><a href="<c:url value='/leave.do' />"><i class="fas fa-clipboard"></i> 휴 가</a></li>
            <li>
                <a href="javascript:void(0);" class="active" onclick="toggleDropdown(this);"><i class="fas fa-envelope"></i> 이메일</a>
                <ul class="dropdown-menu">
               	    <li><a href="<c:url value='/email/send.do' />" class="dropdown-item" onclick="hideDropdown()">메일 쓰기</a></li>
                    <li><a href="<c:url value='/email/list.do' />" class="dropdown-item" onclick="hideDropdown()">전체 이메일</a></li>
                    <li><a href="<c:url value='/email/inbox.do' />" class="dropdown-item" onclick="hideDropdown()">받은 이메일</a></li>
                    <li><a href="<c:url value='/email/sent.do' />" class="dropdown-item" onclick="hideDropdown()">보낸 이메일</a></li>
                </ul>
            </li>
        </ul>
    </div>

    <!-- 하얀 큰 박스 -->
    <div class="content-box">
        <div class="content-title">이메일 상세 보기</div>

<!-- 이메일 상세 정보 -->
<div class="email-detail">
    <!-- 제목 -->
    <div class="subject">
        ${email.subject}
    </div>

    <!-- 발신자 및 수신자 정보 -->
    <div class="sender-recipient">
        <label>보낸사람: </label> ${email.senderName} &lt;${email.senderEmail}&gt;<br/>
        <label>받은사람: </label> ${email.recipientName} &lt;${email.recipientEmail}&gt;<br/>
    </div>

    <!-- 보낸/받은 날짜 -->
    <div class="sent-date">
        <fmt:setLocale value="ko_KR" />
        <fmt:formatDate value="${email.sentDate}" pattern="yyyy.MM.dd (E) a hh:mm" />
    </div>

	<c:if test="${not empty email.attachmentPath}">
   		 <div class="attachment">
      			  <label>첨부 파일:</label><br>
       			 <c:set var="encodedFileName" value="${fn:replace(email.attachmentPath, ' ', '%20')}" />
       			 <c:set var="originalFileName" value="${fn:substringAfter(email.attachmentPath, '_')}" />
       			 <a href="<c:url value='/resources/templates/email_files/${encodedFileName}' />" target="_blank" download="${originalFileName}">
          			  ${originalFileName}
        		</a>
   		 </div>
	</c:if>


    
    <!-- 이메일 내용 -->
    <div class="email-body">
         <c:out value="${email.body}" escapeXml="false" />
    </div>
</div>

        <!-- 뒤로가기 버튼 -->
        <div class="btn-group">
            <button type="button" class="btn" onclick="goBack()">뒤로가기</button>
        </div>
    </div>

    <script>
        function goBack() {
            window.history.back();
        }

        // 드롭다운 메뉴를 토글하는 함수
        function toggleDropdown(element) {
            const dropdownMenu = element.nextElementSibling;
            dropdownMenu.classList.toggle('show'); // 드롭다운 메뉴의 show 클래스를 토글
        }

        // 드롭다운 메뉴를 숨기는 함수
        function hideDropdown() {
            const dropdowns = document.querySelectorAll('.dropdown-menu');
            dropdowns.forEach(dropdown => {
                dropdown.classList.remove('show'); // 모든 드롭다운 메뉴의 show 클래스 제거
            });
        }

        // 페이지 외부 클릭 시 드롭다운 메뉴 닫기
        window.onclick = function(event) {
            if (!event.target.matches('.active') && !event.target.closest('.dropdown-menu')) {
                hideDropdown();
            }
        }
    </script>
</body>
</html>
