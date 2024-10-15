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

#pagebutton {
	display: flex;
	justify-content: center;
	margin-top: 2%; /* 위쪽 여백 추가 */
}

#pagebutton a {
	color: black; /* 글자 색상 검은색 */
	text-decoration: none; /* 밑줄 제거 */
	font-size: 20px; /* 글자 크기 증가 */
	margin: 0 10px; /* 페이지 버튼 간격 조정 */
}

#pagebutton strong {
	font-size: 20px; /* 현재 페이지 강조 글자 크기 증가 */
	color: black; /* 강조 색상 검은색 유지 */
}

tbody tr:hover {
	cursor: pointer;
}
</style>

</head>

<body>
	<c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

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

	<div class="content-box">
		<div class="content-title">구매관리 | 배송조회 | ${itemDetails.itemName}</div>

		<form action="/moduerp/deliveryFilter.do">
			<!-- 필터 박스 -->
			<div class="filter-box">
				<select name="filterOption" id="filterOption">
					<option disabled selected>옵션 선택</option>
					<option value="itemName">제품명</option>
					<option value="stockPlace">장소</option>
					<option value="iDirector">담당자</option>
				</select> <input type="date" name="startDate" id="startDate" /> <input
					type="date" name="endDate" id="endDate" /> <input type="text"
					name="filterText" id="filterText" placeholder="내용 입력" />
				<button type="submit" class="btn">조회</button>
				<button type="button" class="btn"
					onclick="window.location.href='delivery.do';">초기화</button>
			</div>
		</form>

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


		<!-- 두 번째 테이블 -->
		<!-- 테이블 -->
		<form action="/moduerp/deliveryDetails.do" method="POST">
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
					<td>${deliveryDetails.spac}</td>
					<td>${deliveryDetails.recipient}</td>
					<td>${deliveryDetails.receiverId}</td>
					<td>${deliveryDetails.address}</td>
					<td>${deliveryDetails.waybill}</td>
					<td>${deliveryDetails.deliveryCompany}</td>
				</tr>

			</tbody>

		</table>
		</form>


		<!-- 세 번째 테이블 -->
		<form action="/moduerp/deliveryCreate.do" method="POST">
			<input type="hidden" name="inDate"
				value="<%=java.time.LocalDate.now()%>">
			<input type="hidden" id="itemCode" name="itemCode" value="${itemDetails.itemCode}">
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
							placeholder="극소형,소형,중형,대형,극대형" style="width: 175px;"></td>
						<td><input type="text" name="recipient" placeholder="수취인" /></td>
						<td><input type="text" name="receiverId" placeholder="수신자 번호" /></td>
						<td><input type="text" name="address" placeholder="수신자 주소"
							style="width: 255px;" /></td>
						<td><input type="text" name="waybill" placeholder="운송장번호" /></td>
						<td><input type="text" id="deliveryCompany"
							name="deliveryCompany" list="deliveryCompanyCode"
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

			<div class="btn-group">
				<button type="submit" class="btn blue">등록 완료</button>
			</div>
		</form>

	</div>

	<!-- 삭제 확인 모달 -->
	<div id="delete-modal" style="display: none;">
		<div class="modal-content">
			<h2>정말로 삭제하시겠습니까?</h2>
			<p>삭제된 데이터는 복구할 수 없습니다.</p>
			<form action="deleteDelivery.do" method="POST">
				<input type="hidden" name="deliveryCode" value="${deliveryDetails.itemCode}">
				<button type="submit" class="go-delete">삭제</button>
				<button type="button" class="stay-page" onclick="closeDeleteModal()">취소</button>
			</form>
		</div>
	</div>



	<script type="text/javascript">
		function openDeleteModal() {
			document.getElementById('delete-modal').style.display = 'block';
		}

		function closeDeleteModal() {
			document.getElementById('delete-modal').style.display = 'none';
		}
	</script>
</body>
</html>
