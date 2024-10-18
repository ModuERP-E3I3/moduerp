<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Navigation Menu</title>

<style type="text/css">
body {
	margin-top: 60px; /* 네비게이션 바 높이만큼 상단 여백 설정 */
	font-family: 'Arial', sans-serif;
}

/* 상단 메뉴바 */
nav {
	padding: 8px;
	width: 100%; /* 메뉴바 너비 */
	position: fixed;
	top: 0;
	left: 0;
	height: 60px;
	background: white;
	color: black;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
	display: flex; /* nav 안의 요소들을 한 줄에 배치 */
	justify-content: center; /* 자식 요소를 중앙으로 정렬 */
	align-items: center;
}

.nav-wrapper {
	width: 60%; /* 네비게이션 바의 60% 너비 */
	display: flex;
	align-items: center;
}

/* 메뉴바 스타일 */
#menubar {
	list-style: none;
	padding: 0;
	margin: 0;
	display: flex;
	flex-wrap: nowrap; /* 메뉴 항목이 한 줄에 유지되도록 설정 */
	margin-left: auto; /* 메뉴바를 오른쪽으로 밀어냄 */
	align-items: center;
	height: 100%;
}

nav ul li {
	margin: 0 9px; /* 메뉴 항목 간격 조정 */
}

nav ul li a {
	color: black;
	text-decoration: none;
	padding: 10px 15px;
	display: block;
	font-size: 14px; /* 텍스트 크기 */
	position: relative; /* 밑줄에 대한 상대 위치 설정 */
	transition: background 0.3s ease, transform 0.3s ease;
	/* 호버 효과 애니메이션 */
	padding-bottom: 8px; /* 텍스트와 밑줄 사이에 공간 추가 */
}

nav ul li a::after {
	content: '';
	position: absolute;
	width: 0;
	height: 3px;
	background: black; /* 밑줄 색상 */
	left: 0;
	bottom: -5px; /* 텍스트 아래로 밑줄 이동 */
	transition: width 0.3s ease; /* 밑줄 애니메이션 */
}

nav ul li a:hover::after {
	width: 100%; /* 호버 시 밑줄이 왼쪽에서 오른쪽으로 그어짐 */
}

nav ul li a i {
	margin-right: 10px; /* 아이콘과 텍스트 간격 */
}

#logout-button {
	width: 15px;
	height: 20px;
	margin: 10px;
	background: #E74C3C; /* 로그아웃 버튼 색 */
	border-radius: 50%;
	display: flex;
	justify-content: center;
	align-items: center;
	color: white;
	text-decoration: none;
	transition: background 0.3s ease;
}

#logout-button:hover {
	background: #C0392B; /* 호버 시 버튼 색상 변경 */
}

nav .moduerp-logo {
	font-size: 24px; /* ModuERP 글씨 크기 */
	font-weight: bold;
	color: black;
	margin-right: 20px; /* 메뉴바와 로고 거리 */
	padding-left: 20px;
	text-decoration: none;
}

/* 모달 창 스타일 */
.modal {
	display: none; /* 기본적으로 숨김 */
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	justify-content: center;
	align-items: center;
}

.modal-content {
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
	width: 300px;
	text-align: center;
}

.modal-content button {
	margin-top: 20px;
	padding: 10px;
	width: 100%;
	border: none;
	background-color: navy;
	color: white;
	font-size: 16px;
	cursor: pointer;
}

.modal-content button:hover {
	background-color: #444;
}

.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
	cursor: pointer;
}

.close:hover, .close:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}

@media screen and (max-width: 768px) {
	.nav-wrapper {
		width: 90%; /* 모바일에서는 너비를 90%로 확장 */
	}
	nav ul li {
		margin: 0 10px; /* 모바일에서 메뉴 항목 간격 줄이기 */
	}
	nav ul li a {
		font-size: 12px; /* 모바일에서 텍스트 크기 줄이기 */
		padding: 8px 10px; /* 패딩 조정 */
		padding-bottom: 5px; /* 텍스트와 밑줄 사이에 공간 추가 */
	}
	nav ul li a::after {
		bottom: -4px; /* 모바일에서 밑줄 위치 조정 */
	}
}
</style>

<!-- Font Awesome for icons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<script type="text/javascript">
	// 로그인 모달 열기
	function openLoginModal() {
		document.getElementById("loginModal").style.display = "flex";
	}

	// 로그인 모달 닫기
	function closeLoginModal() {
		document.getElementById("loginModal").style.display = "none";
	}

	// 모달 외부 클릭 시 닫기
	window.onclick = function(event) {
		var modal = document.getElementById("loginModal");
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
</script>

</head>
<body>
	<%-- <p>Login UUID: ${param.loginUUID}</p>
<p>Admin UUID: ${param.adminUUID}</p> --%>

	<nav>
    <div class="nav-wrapper">
        <a href="<c:url value='/main.do' />" class="moduerp-logo">ModuERP</a>
        <ul id="menubar">
            <li><a href="<c:url value='/notice/list.do' />">공지사항</a></li>
            <li><a href="<c:url value='/buyModule.do' />">구매</a></li>
            <li><a href="<c:url value='/main.do' />">체험페이지</a></li>
            <li><a href="<c:url value='/qna.do' />">고객서비스</a></li>
            <li><a href="<c:url value='/main.do' />">회사소개</a></li>

            <c:choose>
                <%-- 관리자일 때 관리자페이지 테스트용 표시 --%>
                <c:when test="${not empty sessionScope.uuid and sessionScope.uuid eq sessionScope.adminUUID}">
                    <li><a href="<c:url value='/admin.do' />">관리자페이지</a></li>
                </c:when>

                <%-- 사장님(CEO)일 때 마이페이지 표시 --%>
                <c:when test="${not empty sessionScope.uuid and sessionScope.departmentId eq 'ceo-dpt'}">
                    <li><a href="<c:url value='/passwordManagement.do' />">마이페이지</a></li>
                </c:when>

                <c:otherwise>
                    <%-- 일반 직원이 로그인하면 아무것도 출력하지 않음 --%>
                </c:otherwise>
            </c:choose>

            <li><a href="<c:url value='/erpMain.do' />">ERP</a></li>
            <li><a href="<c:url value='/forwardCart.do' />">장바구니</a></li>
        </ul>
    </div>
</nav>


	<!-- 로그인 모달 -->
	<div id="loginModal" class="modal">
		<div class="modal-content">
			<span class="close" onclick="closeLoginModal()">&times;</span>
			<h2>로그인</h2>
			<form action="login.do" method="post">
				<input type="text" name="userId" placeholder="아이디" required><br>
				<br> <input type="password" name="userPwd" placeholder="비밀번호"
					required><br>
				<br>
				<button type="submit">로그인</button>
			</form>
			<br>
			<button onclick="location.href='enrollPage.do'">회원가입</button>
		</div>
	</div>

</body>
</html>
