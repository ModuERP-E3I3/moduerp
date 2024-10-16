<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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

/* Modal Styles */
#delete-modal {
	display: none; /* 초기에는 보이지 않도록 설정 */
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%; /* 전체 화면 너비 */
	height: 100%; /* 전체 화면 높이 */
	background-color: rgba(0, 0, 0, 0.5); /* 배경 반투명 */
	display: flex; /* 플렉스 박스를 사용하여 중앙 정렬 */
}

.modal-content {
	background-color: #fff;
	padding: 20px;
	border-radius: 5px;
	text-align: center;
	width: 300px; /* 원하는 너비 */
	position: relative;
	margin: auto; /* 중앙 정렬을 위한 마진 */
	margin-top: 20%;
}

.modal-content h2 {
	margin-bottom: 20px;
}

.modal-content button {
	padding: 10px 20px;
	margin: 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.modal-content .go-delete {
	background-color: red;
	color: #fff;
}

.modal-content .stay-page {
	background-color: gray;
	color: #fff;
}
</style>

</head>

<body>
	<!-- 서브헤더 JSP 임포트 -->
	<c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

	<!-- 위에 하얀 박스  -->
	<div class="top-content-box">
		<ul id="menubar">

		 <li><a href="purchaseOrders.do"><i class="fas fa-bullhorn"></i> 발주서 관리</a></li>
		 <li><a href="buyStockIn.do"><i class="fa-solid fa-bag-shopping"></i> 구매 입고</a></li>
         <li><a href="buyStockOut.do"><i class="fa-solid fa-truck-ramp-box"></i> 구매 출고</a></li>
         <li><a href="delivery.do"><i class="fa-solid fa-truck"></i> 배송 조회</a></li>

		</ul>
	</div>

	<!-- 하얀 큰 박스 -->
	<div class="content-box">

		<div class="content-title">구매관리 | 발주서관리 |
			${purchaseOrderDetail.puItemName}</div>

		<!-- 테이블 -->
		<table>
			<thead>
				<tr>
					<th>거래처명</th>
					<th>품명</th>
					<th>수량</th>
					<th>발주금액</th>
					<th>납품일</th>
					<th>담당자명</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${purchaseOrderDetail.accountName}</td>
					<td>${purchaseOrderDetail.puItemName}</td>
					<td>${purchaseOrderDetail.quantity}</td>
					<td>${purchaseOrderDetail.supplyPrice}</td>
					<td><fmt:formatDate value="${purchaseOrderDetail.deliveryDate}" pattern="yyyy-MM-dd" /></td>
					<td>${purchaseOrderDetail.oDirector}</td>
				</tr>
			</tbody>
		</table>

		<!-- 버튼 그룹 -->
		<div class="btn-group">
			<button class="btn red" onclick="openDeleteModal()">삭제</button>
			<a
				href="purchaseOrderDetailUpdate.do?orderId=${purchaseOrderDetail.orderId}">
				<button class="btn green">수정</button>
			</a>
		</div>

	</div>

	<!-- 삭제 확인 모달 -->
	<div id="delete-modal" style="display: none;">
		<div class="modal-content">
			<h2>정말로 삭제하시겠습니까?</h2>
			<p>삭제된 데이터는 복구할 수 없습니다.</p>
			<form action="deletePurchaseOrder.do" method="POST">
				<input type="hidden" name="orderId"
					value="${purchaseOrderDetail.orderId}">
				<!-- orderId를 숨겨진 필드로 전달 -->
				<button type="submit" class="go-delete">삭제</button>
				<button type="button" class="stay-page" onclick="closeDeleteModal()">취소</button>
			</form>
		</div>
	</div>

</body>

<script type="text/javascript">
function openDeleteModal() {
    document.getElementById('delete-modal').style.display = 'block';
}

function closeDeleteModal() {
    document.getElementById('delete-modal').style.display = 'none';
}
</script>

<script>
    const activeMenu = "purchaseOrders";

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
