<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>보낸 이메일</title>
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

        .email-list {
            list-style: none;
            padding: 0;
        }

        .email-item {
            border-bottom: 1px solid #ccc;
            padding: 10px 0;
        }

        .email-item:last-child {
            border-bottom: none;
        }

        /* 드롭다운 메뉴 스타일 */
        .dropdown-menu {
            display: none; /* 기본적으로 숨김 */
            position: absolute; /* 위치 지정 */
            background-color: white;
            border: 1px solid #ccc;
            z-index: 1;
            margin-top: 5px; /* 상단 여백 */
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
    </style>
</head>

<body>
    <!-- 서브헤더 JSP 임포트 -->
    <c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

    <!-- 위에 하얀 박스  -->
    <div class="top-content-box">
        <ul id="menubar">
            <li><a href="attendance.do"><i class="fas fa-bullhorn"></i> 출퇴근</a></li>
            <li><a href="leave.do"><i class="fas fa-clipboard"></i> 휴 가</a></li>
            <li>
                <a href="javascript:void(0);" class="active" onclick="toggleDropdown(this);"><i class="fas fa-clipboard"></i> 이메일</a>
                <ul class="dropdown-menu">
                    <li><a href="email/list.do" class="dropdown-item" onclick="hideDropdown()">전체 이메일</a></li>
                    <li><a href="inbox.do" class="dropdown-item" onclick="hideDropdown()">받은 이메일</a></li>
                    <li><a href="sent.do" class="dropdown-item" onclick="hideDropdown()">보낸 이메일</a></li>
                </ul>
            </li>
        </ul>
    </div>

    <!-- 하얀 큰 박스 -->
    <div class="content-box">
        <div class="content-title">보낸 이메일</div>

        <!-- 이메일 리스트 -->
        <ul class="email-list">
            <c:forEach var="email" items="${emails}">
                <li class="email-item"><strong>수신자:</strong> ${email.recipientEmail} <br>
                    <strong>제목:</strong> ${email.subject} <br>
                    <strong>보낸 날짜:</strong> ${email.sentDate} <br>
                    <a href="view.do?emailId=${email.emailId}">자세히 보기</a>
                </li>
            </c:forEach>
        </ul>

        <!-- 메일쓰기 버튼 -->
        <div class="btn-group" style="text-align: right; margin-top: 20px;">
            <a href="send.do" class="btn blue">메일쓰기</a>
        </div>
    </div>

    <script>
        function toggleDropdown(element) {
            const dropdown = element.nextElementSibling; // 드롭다운 메뉴
            if (dropdown.style.display === "block") {
                dropdown.style.display = "none"; // 이미 열려있으면 닫기
            } else {
                dropdown.style.display = "block"; // 열기
            }
        }

        function hideDropdown() {
            const dropdowns = document.querySelectorAll('.dropdown-menu');
            dropdowns.forEach(dropdown => {
                dropdown.style.display = "none"; // 모든 드롭다운 닫기
            });
        }

        // 페이지 로드 시 드롭다운을 자동으로 닫기
        window.onclick = function(event) {
            if (!event.target.matches('.active')) {
                hideDropdown();
            }
        }
    </script>
</body>
</html>
