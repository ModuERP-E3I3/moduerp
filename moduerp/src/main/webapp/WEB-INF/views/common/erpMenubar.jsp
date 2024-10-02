<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERPMenubar</title>

<style type="text/css">
body {
    margin-left: 180px; /* 왼쪽 사이드바 공간 확보 */
    font-family: 'Arial', sans-serif;
    background-color: #223D38;
}

/* 왼쪽 사이드바 */
nav.side {
    font-weight: bold;
    width: 180px;
    height: 100vh;
    position: fixed;
    top: 0;
    left: 0;
    background-color: #217770; /* 기본 색상 */
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    padding-top: 20px;
}

nav.side ul {
    list-style: none;
    padding: 0;
    margin: 0;
    width: 100%;
}

nav.side ul li {
    width: 100%;
}

nav.side ul li a {
    display: block;
    color: white;
    text-decoration: none;
    padding: 15px;
    transition: background-color 0.3s ease;
    font-size: 16px;
    text-align: left;
}

nav.side ul li a i {
        margin-right: 10px; /* 아이콘과 텍스트 사이 간격 */
    }

nav.side ul li a:hover {
    background-color: #223D38; /* 호버 시 색상 */
    border-top-left-radius: 20px; /* 왼쪽 상단만 둥글게 */
    border-bottom-left-radius: 20px; /* 왼쪽 하단만 둥글게 */
    border
}




/* 클릭된 항목에 적용할 active 클래스 */
nav.side ul li a.active {
    background-color: #223D38; /* 호버 시 색상과 동일하게 설정 */
    border-top-left-radius: 20px; /* 왼쪽 상단만 둥글게 */
    border-bottom-left-radius: 20px; /* 왼쪽 하단만 둥글게 */
}

/* 메인 콘텐츠 */
.main-content {
    padding: 20px;
    margin-left: 200px; /* 사이드바 공간만큼 확보 */
}
</style>

<!-- Font Awesome for icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

</head>
<body>

<!-- 왼쪽 사이드바 -->
<nav class="side">
    <ul>
        <li><a href="erpMain.do"><i class="fas fa-home"></i>ModuERP</a></li>
        <li><a href="hr.do"><i class="fas fa-bullhorn"></i>인사 관리</a></li>
        <li><a href="productionStockIn.do"><i class="fas fa-clipboard"></i>생산 관리</a></li>
        <li><a href="buyStockIn.do"><i class="fas fa-code"></i>구매 관리</a></li>
        <li><a href="account.do"><i class="fas fa-plug"></i>영업/판매 관리</a></li>
        <li><a href="bankmg.do"><i class="fas fa-plug"></i>회계 관리</a></li>
        <li><a href="car"><i class="fas fa-plug"></i>차량 관리</a></li>
        <li><a href="attendance.do"><i class="fas fa-plug"></i>그룹 웨어</a></li>
        <li><a href="main.do"><i class="fas fa-home"></i>Homepage</a></li>
        <li><a href="logout"><i class="fas fa-sign-out-alt"></i>로그아웃</a></li>
    </ul>
</nav>

<script>
document.addEventListener('DOMContentLoaded', function() {
    const menuItems = document.querySelectorAll('nav.side ul li a');
    const currentUrl = window.location.href;

    menuItems.forEach(item => {
        // 현재 URL이 메뉴 항목의 href와 일치하는 경우 active 클래스를 추가
        if (currentUrl.includes(item.getAttribute('href'))) {
            item.classList.add('active');
        }

        item.addEventListener('click', function() {
            // 모든 항목에서 active 클래스 제거
            menuItems.forEach(i => i.classList.remove('active'));
            
            // 클릭된 항목에 active 클래스 추가
            this.classList.add('active');
        });
    });
});

</script>

</body>
</html>
