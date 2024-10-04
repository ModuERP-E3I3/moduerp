<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 상세 보기</title>
<style type="text/css">
/* 기존 CSS 스타일을 여기에 추가하세요. */
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
    position: relative; /* 드롭다운 메뉴를 위해 position 추가 */
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

/* 제목 스타일 */
.content-title {
	position: absolute;
	top: -40px;
	left: 20px;
	font-size: 24px;
	color: white;
	font-weight: bold;
}

.email-detail {
    padding: 10px;
}

.email-detail label {
    font-weight: bold;
    display: block;
    margin-top: 10px;
}

.email-detail span {
    display: block;
    margin-top: 5px;
}

/* 버튼 스타일 */
.btn-group {
    text-align: right;
    margin-top: 20px;
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
    position: absolute; /* 트리거 요소를 기준으로 위치 지정 */
    top: 100%; /* 트리거 요소 바로 아래에 위치 */
    left: 0; /* 트리거 요소의 왼쪽에 맞춤 */
    background-color: white;
    border: 1px solid #ccc;
    z-index: 1;
    border-radius: 5px; /* 둥근 모서리 */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
    list-style: none; /* 동그라미 제거 */
    padding: 0; /* 여백 제거 */
    white-space: nowrap; /* 한 줄로 표시 */
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

/* 날짜 레이블 스타일 */
.date-label {
    font-weight: bold;
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
                <a href="javascript:void(0);" class="active" onclick="toggleDropdown(this);"><i class="fas fa-clipboard"></i> 이메일</a>
                <ul class="dropdown-menu">
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
            <label>발신자:</label> <span>${email.senderEmail}</span>
            <label>수신자:</label> <span>${email.recipientEmail}</span>
            <label>제목:</label> <span>${email.subject}</span>
            <label>내용:</label> <span>${email.body}</span>

            <!-- 날짜 레이블 조건부 표시 -->
            <c:choose>
                <c:when test="${email.senderEmail == sessionScope.email}">
                    <label class="date-label">보낸 날짜:</label>
                </c:when>
                <c:otherwise>
                    <label class="date-label">받은 날짜:</label>
                </c:otherwise>
            </c:choose>

            <!-- 날짜 형식 지정 (분 단위까지, 한국어 로케일) -->
            <fmt:setLocale value="ko_KR" />
            <fmt:formatDate value="${email.sentDate}" pattern="yyyy년 MM월 dd일 (E) a hh:mm" />
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
        function toggleDropdown(element) {
            const dropdown = element.nextElementSibling; // 드롭다운 메뉴
            dropdown.classList.toggle('active'); // active 클래스 토글
        }

        function hideDropdown() {
            const dropdowns = document.querySelectorAll('.dropdown-menu');
            dropdowns.forEach(dropdown => {
                dropdown.classList.remove('active'); // active 클래스 제거
            });
        }

        // 페이지 로드 시 드롭다운을 자동으로 닫기
        window.onclick = function(event) {
            if (!event.target.matches('.active') && !event.target.closest('.dropdown-menu')) {
                hideDropdown();
            }
        }
    </script>
</body>
</html>
