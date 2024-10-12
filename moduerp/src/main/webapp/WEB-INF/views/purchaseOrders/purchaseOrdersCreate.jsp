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
	<!-- 서브헤더 JSP 임포트 -->
	<c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

	<!-- 위에 하얀 박스  -->
	<div class="top-content-box">
		<ul id="menubar">

			<li><a href="purchaseOrders.do"><i class="fas fa-bullhorn"></i>
					발주서 관리</a></li>
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

		<div class="content-title">구매 관리 | 발주서관리 | 신규 등록</div>

		<!-- 필터 박스 -->
		<div class="filter-box">
			<select>
				<option>조회기간</option>
			</select> <input type="date" /> <input type="date" /> <select>
				<option>품목 선택</option>
			</select> <input type="text" placeholder="내용 입력" />
			<button class="btn">조회</button>
		</div>

		<!-- 테이블 -->
		<form action="/moduerp/purchaseOrderCreate.do" method="POST">
			<table>
				<thead>
					<tr>
						<th>거래처 코드</th>
						<th>품명</th>
						<th>수량</th>
						<th>공급가</th>
						<th>납품일</th>
						<th>담당자명</th>
					</tr>
				</thead>
				<tbody>
					<tr>
					
						<!-- 거래처 코드 드롭다운 리스트 -->
						<td><select name="accountNo" onchange="setAccountName(this)">
								<option value="" disabled selected>거래처 선택</option>
								<c:forEach var="account" items="${accountNames}">
									<option value="${account.ACCOUNTNO}"
										data-name="${account.ACCOUNTNAME}">${account.ACCOUNTNAME}</option>
								</c:forEach>
						</select> <input type="hidden" name="accountName" id="accountNameField" />
							<!-- 올바른 위치로 이동 --></td>
							
						<td><input type="text" name="puItemName"
							placeholder="발주할 품명 입력" /></td>
						<td><input type="number" name="quantity" placeholder="수량 입력" /></td>
						<td><input type="number" name="supplyPrice"
							placeholder="공급가 입력" /></td>
						<td><input type="date" name="deliveryDate" /></td>
						<td><input type="text" name="mgrName" placeholder="담당자명 입력" /></td>
			
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
function addMaterialType() {
    const container = document.getElementById('materialTypeContainer');
    const newInputDiv = document.createElement('div');
    newInputDiv.className = 'material-type-input';
    newInputDiv.innerHTML = `
        <input list="materialTypes" name="materialType" placeholder="자재 종류 입력" />
        <datalist id="materialTypes">
            <c:forEach var="itemName" items="${itemNames}">
                <option value="${itemName}"></option>
            </c:forEach>
        </datalist>
        <button type="button" class="remove-btn" onclick="removeMaterialType(this)">삭제</button>
    `;
    container.appendChild(newInputDiv);
}

function removeMaterialType(button) {
    const inputDiv = button.parentElement;
    inputDiv.remove();
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


<!-- AccountName -->
<script>
function setAccountName(select) {
    const accountName = select.options[select.selectedIndex].getAttribute('data-name');
    document.getElementById('accountNameField').value = accountName;
}
</script>



</html>
