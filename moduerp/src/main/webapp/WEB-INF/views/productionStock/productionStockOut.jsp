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
</style>

</head>

<body>
	<!-- 서브헤더 JSP 임포트 -->
	<c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

	<!-- 위에 하얀 박스  -->
	<div class="top-content-box">
		<ul id="menubar">
			<li><a href="productionStockIn.do"><i
					class="fas fa-bullhorn"></i> 생산 입고</a></li>
			<li><a href="productionStockOut.do"><i
					class="fas fa-clipboard"></i> 생산 출고</a></li>
			<!-- 수정 -->
			<li><a href="productionWorkorder.do"><i class="fas fa-code"></i>
					작업지시서</a></li>
			<!-- 수정 -->
			<li><a href="productionQuality.do"><i class="fas fa-plug"></i>
					품질관리</a></li>
			<!-- 수정 -->
		</ul>
	</div>

	<!-- 하얀 큰 박스 -->
	<div class="content-box">

		<div class="content-title">생산관리 | 생산출고</div>

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
		<table>
			<thead>
				<tr>
					<th>순번</th>
					<th>제품명</th>
					<th>최종 출고 일자</th>
					<th>총 출고 수량</th>
					<th>재고 수량</th>
					<th>최종 출고 장소</th>
					<th>최종 출고 단가</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${itemList}" varStatus="status">
					<tr
						onclick="window.location.href='getProductionOutDetails.do?itemCode=${item.itemCode}'">
						<td>${(currentPage - 1) * 10 + (status.index + 1)}</td>
						<td>${item.itemName}</td>
						<td><fmt:formatDate value="${item.createdOutAt}"
								pattern="yyyy-MM-dd" /></td>
						<td>${item.stockOut}</td>
						<td>${item.stock}</td>
						<td>${item.stockOutPlace}</td>
						<td>${item.outPrice}</td>
					</tr>
				</c:forEach>
			</tbody>


		</table>
		<!-- 페이지 버튼 -->
		<div id="pagebutton">
			<c:if test="${totalPages > 1}">
				<c:forEach var="i" begin="1" end="${totalPages}">
					<c:choose>
						<c:when test="${i == currentPage}">
							<strong>${i}</strong>
							<!-- 현재 페이지는 강조 -->
						</c:when>
						<c:otherwise>
							<a href="productionStockOut.do?page=${i}">${i}</a>
							<!-- 페이지 링크 -->
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
		</div>

		<!-- 버튼 그룹 -->
		<div class="btn-group">
			<a href="productionStockOutCreate.do"><button class="btn blue">등록</button></a>
		</div>

	</div>
</body>
<script>
    const activeMenu = "productionStockIn";

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
