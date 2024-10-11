<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			<li><a href="account.do"><i class="fas fa-bullhorn"></i>
					거래처관리</a></li>
			<li><a href="salesStockIn.do"><i class="fas fa-clipboard"></i>
					영업 입고</a></li>
			<li><a href="salesStockOut.do"><i class="fas fa-code"></i>
					영업 출고</a></li>
		</ul>
	</div>

	<div class="content-box">
		<div class="content-title">영업관리 | 영업입고 | ${itemDetails.itemName}
			수정하기</div>

		<form action="/moduerp/updateSalesStockSubOut.do" method="POST">
			<input type="hidden" name="itemCode" value="${itemDetails.itemCode}" />
			<input type="hidden" name="sStockOutId"
				value="${salesStockOutDetails.sStockOutId}" />
			<table>
				<thead>
					<tr>
						<th>제품명</th>
						<th>제품 설명</th>
						<th>출고 날짜</th>
						<th>수정 날짜</th>
						<th>출고 수량</th>
						<th>출고 가격</th>
						<th>출고 장소</th>
						<th>자재 종류</th>
						<th>출고 담당자</th>
						<th>지급 상태</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${itemDetails.itemName}</td>
						
						<td>${itemDetails.itemDesc}</td>
						
						<td><fmt:formatDate
								value="${salesStockOutDetails.sStockOutDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
								
						<td><fmt:formatDate
								value="${salesStockOutDetails.sStockOutUpdate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
								
						<td><input type="number" name="stockOut"
							value="${salesStockOutDetails.sStockOutQty}" required /></td>
							
						<td><input type="number" name="outPrice"
							value="${salesStockOutDetails.sStockOutPrice}" step="0.01"
							required /></td>
							
						<td><input list="stockPlaces" name="stockPlace"
							value="${salesStockOutDetails.sStockOutPlace}"
							placeholder="보관장소 선택" required /> <datalist id="stockPlaces">
								<c:forEach var="stockPlace" items="${stockPlaces}">
									<option value="${stockPlace}"></option>
								</c:forEach>
							</datalist></td>
							
						<td>${itemDetails.itemList}</td>
						
						<td>${salesStockOutDetails.oDirector}</td>
						
						<td><select name="paymentStatus">
								<option value="Y"
									<c:if test="${salesStockOutDetails.paymentStatus == 'Y'}">selected</c:if>>Yes</option>
								<option value="N"
									<c:if test="${salesStockOutDetails.paymentStatus == 'N'}">selected</c:if>>No</option>
						</select></td>
						
					</tr>
				</tbody>
			</table>

			<div class="btn-group">
				<button type="submit" class="btn green">수정 완료</button>
			</div>
		</form>

	</div>
</body>


<script>
    const activeMenu = "account";

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
