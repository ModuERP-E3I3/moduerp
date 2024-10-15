<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

.top-content-box {
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
			<li><a href="buyStockIn.do"><i class="fas fa-bullhorn"></i>
					구매 입고</a></li>
			<li><a href="buyStockOut.do"><i class="fas fa-bullhorn"></i>
					구매 출고</a></li>
			<li><a href="delivery.do"><i class="fa-solid fa-truck"></i>
					배송 조회</a></li>

		</ul>
	</div>

	<!-- 하얀 큰 박스 -->
	<div class="content-box">

		<div class="content-title">구매관리 | 배송조회 | 운송장 등록</div>



		<!-- 테이블 -->
		<!-- 테이블 -->
		<form action="/moduerp/deliveryCreate.do" method="POST">
			<!-- 숨겨진 필드로 현재 날짜를 설정 -->
			<input type="hidden" name="inDate"
				value="<%=java.time.LocalDate.now()%>">

			<table>
				<thead>
					<tr>
						<th>택배규격</th>
						<th>수신자번호</th>
						<th>수신자주소</th>
						<th>수취인</th>
						<th>운송장번호</th>
						<th>배송업체</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" id="spec" name="spec"></td>
						<td><input type="text" name="receiverId" /></td>
						<td><input type="text" name="address" placeholder="수신자 주소" /></td>
						<td><input type="text" name="recipient" placeholder="수취인" /></td>
						<td><input type="text" name="waybill" placeholder="운송장번호" /></td>
						<td><input type="text" id="deliveryCompany" name="deliveryCompany" list="deliveryCompanyCode" 
						placeholder="택배사 선택" required> <datalist
								id="deliveryCompanyCode">
								<option value="01">우체국택배</option>
								<option value="04">CJ대한통운</option>
								<option value="05">한진택배</option>
								<option value="06">로젠택배</option>
								<option value="08">롯데택배</option>
								<option value="94">카카오 T 당일배송</option>
								<option value="95">큐익스프레스</option>
								<option value="11">일양로지스</option>
								<option value="22">대신택배</option>
								<option value="23">경동택배</option>
								<option value="24">GS Postbox 택배</option>
								<option value="46">CU편의점택배</option>
							</datalist></td>

					</tr>
				</tbody>
			</table>

			<!-- 버튼 그룹 -->
			<div class="btn-group">
				<button type="submit" class="btn blue">등록 완료</button>
			</div>
		</form>



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
