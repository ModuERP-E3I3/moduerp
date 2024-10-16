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
    position: relative; /* 화면의 절대 위치로 변경 */
    top: 0.1%; /* 위쪽에서 20% 지점에 배치, 필요 시 조정 가능 */
    background-color: white;
    margin-left: 1%;
    margin-right: 1%;
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

.point {
	width: 14%;
	background-color: #fff;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	float: left; /* point를 맵 오른쪽으로 배치 */
	margin-left: 20px; /* 맵과 point 사이 간격 */
}

.point ul {
	padding: 0;
	margin: 0;
}

.point li {
	list-style: none;
	margin-bottom: 5px;
	display: flex;
	align-items: center;
	font-weight: 800;
}

.point li img {
	margin-right: 10px;
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
    width: 96%; /* 너비를 필요에 따라 조정 가능 */
    height: 70vh; /* 화면 높이의 70% */
    background-color: white;
    margin-left: 1%;
    margin-right: 1%;
    margin-top: 3%;
    border: 1px solid #ccc;
    border-radius: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    position: relative; /* 화면의 절대 위치로 변경 */
    right: 0; /* 화면의 오른쪽에 위치 */
    top: 20%; /* 위쪽에서 20% 지점에 배치, 필요 시 조정 가능 */
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

    /* 테이블 스타일 */
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th, td {
        border: 1px solid #ccc;
        padding: 10px;
        text-align: center;
    }

    th {
        background-color: #f4f4f4;
        font-weight: bold;
    }

    /* 버튼 스타일 */
    .btn-group {
        margin-top: 20px;
        text-align: right;
    }

    .btn {
        padding: 8px 16px;
        margin-left: 5px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .btn.red {
        background-color: red;
        color: white;
    }

    .btn.green {
        background-color: green;
        color: white;
    }

    .btn.blue {
        background-color: blue;
        color: white;
    }

    .filter-box {
        margin-bottom: 20px;
    }

    .filter-box input, .filter-box select {
        padding: 8px;
        margin-right: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    .filter-box input[type="date"] {
        width: 160px;
    }
    
    .top-content-box{
    	background-color: white;
    	
    }
    #startaddr{
    	color : black;
    	font-weight : 700;
    	background-color : white;
    	width : 100px;
    	height : 4%;
    	text-align : right;
    	position : relative;
    	left : 37.8%;
    	/* top : 74.2%; */
    	top: 50%;
    }

</style>

</head>

<body>
    <!-- 서브헤더 JSP 임포트 -->
    <c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

	<!-- 위에 하얀 박스  -->
	<div class="top-content-box">
	    <ul id="menubar">
	        <li><a href="carRes.do"><i class="fa-solid fa-car-side"></i> 차량 예약</a></li>
	        <li><a href="carMgt.do"><i class="fa-solid fa-list-check"></i> 차량 결제 관리</a></li>
	        <li><a href="map.do"><i class="fa-solid fa-signs-post"></i> 도로 교통 / 경로 조회</a></li>
	    </ul>
	</div>
	
    <!-- 하얀 큰 박스 -->
    <div class="content-box">

        <div class="content-title">차량 관리 | 도로 교통 및 경로 조회</div>

        <div id="maphtml">
        	<div class="point">
			<ul>
				<li><img src="/moduerp/resources/images/pointgreen.png"
				alt="pointgreen"> 원활</li>
				<li><img src="/moduerp/resources/images/pointyellow.png"
				alt="pointyellow"> 보통</li>
				<li><img src="/moduerp/resources/images/pointorange.png"
				alt="pointorange"> 혼잡</li>
				<li><img src="/moduerp/resources/images/pointred.png"
				alt="pointred"> 포화</li>
			</ul>
			</div>
			<c:import url="mapapi.html" charEncoding="UTF-8" />
		</div>
		
		
        

    </div>
</body>

<script>
    const activeMenu = "carRes";

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
