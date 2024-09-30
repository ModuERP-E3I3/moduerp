<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>erpMain</title>

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
        margin-top: 3%;
        border: 1px solid #ccc;
        border-radius: 20px; /* 박스 둥글게 */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
        position: relative;
        padding: 20px; /* 내부 여백 추가 */
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

    
    .top-content-box{
    	background-color: white;
    	
    }

</style>

</head>

<body>
    <!-- 서브헤더 JSP 임포트 -->
    <c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

	<!-- 위에 하얀 박스  -->
	<div class="top-content-box">
	    <ul id="menubar">
	        <li><a href="productionStockIn.do"><i class="fas fa-bullhorn"></i> 구매 입고</a></li>
	        <li><a href="productionStockOut.do"><i class="fas fa-clipboard"></i> 구매 출고</a></li> <!-- 수정 -->
	        <li><a href="productionWorkorder.do"><i class="fas fa-code"></i> 작업지시서</a></li> <!-- 수정 -->
	        <li><a href="productionQuality.do"><i class="fas fa-plug"></i> 품질관리</a></li> <!-- 수정 -->
	    </ul>
	</div>
	
    <!-- 하얀 큰 박스 -->
    <div class="content-box">




    </div>
</body>

<script>
    const activeMenu = "buyStockIn";

    document.addEventListener('DOMContentLoaded', function() {
        const menuItems = document.querySelectorAll('nav.side ul li a');
        menuItems.forEach(item => {
            if (item.href.includes(activeMenu)) {
                item.classList.add('active');
            }
        });
    });
</script>


</html>
