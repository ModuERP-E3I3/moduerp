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

.material-type-input {
	margin-bottom: 10px;
}
</style>

</head>

<body>
	<c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

	<div class="top-content-box">
		<ul id="menubar">
			<li><a href="buyStockIn.do"><i class="fas fa-bullhorn"></i>구매
					입고</a></li>
			<li><a href="buyStockOut.do"><i class="fas fa-bullhorn"></i>구매
					출고</a></li>
			<li><a href="delivery.do"><i class="fa-solid fa-truck"></i>배송
					조회</a></li>
		</ul>
	</div>

	<div class="content-box">
		<c:if test="${not empty itemDetails}">
			<div class="content-title">구매관리 | 배송조회 |
				${itemDetails.itemName}</div>

			<!-- 첫 번째 테이블 -->
			<table>
				<thead>
					<tr>
						<th>입고 날짜</th>
						<th>재고명</th>
						<th>재고 수량</th>
						<th>입고 장소</th>
						<th>입고 단가</th>
						<th>직원명</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${itemDetails.createdAt}</td>
						<td>${itemDetails.itemName}</td>
						<td>${itemDetails.stockIn}</td>
						<td>${itemDetails.stockPlace}</td>
						<td>${itemDetails.inPrice}</td>
						<td>${itemDetails.iDirector}</td>
					</tr>
				</tbody>
			</table>

			<!-- 두 번째 테이블: deliveryDetails가 존재하고 deliveryCompanyName이 비어있지 않을 때만 보여줌 -->
			<c:if test="${not empty deliveryDetails and not empty deliveryCompanyName}">
				<form action="/moduerp/deliveryDetails.do" method="POST">
					<input type="hidden" id="itemCode" name="itemCode"
						value="${itemDetails.itemCode}">
					<table>
						<thead>
							<tr>
								<th>택배규격</th>
								<th>수취인</th>
								<th>수신자번호</th>
								<th>수신자주소</th>
								<th>운송장번호</th>
								<th>배송업체</th>
								<th>배송조회</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${deliveryDetails.spec}</td>
								<td>${deliveryDetails.recipient}</td>
								<td>${deliveryDetails.receiverId}</td>
								<td>${deliveryDetails.address}</td>
								<td>${deliveryDetails.waybill}</td>
								<td>${deliveryCompanyName}</td>
								<td>
									<button type="button" class="btn blue" id="trackingBtn">배송조회</button>
								</td>
							</tr>
						</tbody>
					</table>
					<!-- 버튼 그룹 -->
					<div class="btn-group">
						<button type="button" class="btn green"
							onclick="window.location.href='deliveryDetailUpdate.do?itemCode=${itemDetails.itemCode}&deliveryId=${deliveryDetails.deliveryId}'">수정</button>
						<button type="button" class="btn red" onclick="openDeleteModal()">삭제</button>
					</div>
				</form>
			</c:if>
			
			<!-- 세 번째 테이블: deliveryDetails가 비어있거나 deliveryCompanyName이 비어있을 때 보여줌 -->
			<c:if test="${empty deliveryDetails or empty deliveryCompanyName}">
				<form action="/moduerp/deliveryCreate.do" method="POST">
					<input type="hidden" name="inDate"
						value="<%=java.time.LocalDate.now()%>"> 
					<input type="hidden" id="itemCode" name="itemCode"
						value="${itemDetails.itemCode}">
					<table>
						<thead>
							<tr>
								<th>택배규격</th>
								<th>수취인</th>
								<th>수신자번호</th>
								<th>수신자주소</th>
								<th>운송장번호</th>
								<th>배송업체</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="text" id="spec" name="spec"
									placeholder="극세사" required></td>
								<td><input type="text" id="recipient" name="recipient"
									placeholder="수신인" required></td>
								<td><input type="text" id="receiverId" name="receiverId"
									placeholder="수신자 번호" required></td>
								<td><input type="text" id="address" name="address"
									placeholder="주소" required></td>
								<td><input type="text" id="waybill" name="waybill"
									placeholder="운송장번호" required></td>
								<td><select id="deliveryCompany" name="deliveryCompany" required>
										<option value="">택배 업체 선택</option>
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
								</select></td>
							</tr>
						</tbody>
					</table>
					<!-- 버튼 그룹 -->
					<div class="btn-group">
						<button class="btn blue">등록</button>
					</div>
				</form>
			</c:if>
		</c:if>
	</div>
</body>

<!-- 배송조회 api -->
<script>
    document.getElementById('trackingBtn').addEventListener('click', function() {
        var apiKey = 'zeByxJfH1aBU4Ff1R0Xe7w';
        
        // t_code와 운송장 번호(waybill) 자동으로 설정
        var tCode = '${deliveryCompanyName}'; // 배송 업체
        var tInvoice = '${deliveryDetails.waybill}'; // 운송장 번호
        
        console.log('API Key:', apiKey);
        console.log('Delivery Company Code:', tCode);
        console.log('Waybill Number:', tInvoice);
        
        // URL 생성
        var url = `https://info.sweettracker.co.kr/tracking/4?t_key=${apiKey}&t_code=${tCode}&t_invoice=${tInvoice}`;
        
        // 새로운 페이지로 이동
        window.location.href = url;
    });
</script>
</html>
