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
	width: 96%;
	height: 6vh;
	background-color: white;
	margin-left: 1%;
	margin-right: 5%;
	margin-top: 1.8%;
	border: 1px solid #ccc;
	border-radius: 20px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 20px;
	font-size: 30px;
	font-weight: bold;
}
/* ul의 기본 스타일 제거 */
#menubar {
	list-style: none;
	padding: 0;
	margin: 0;
	display: flex;
	justify-content: center;
	align-items: center;
}

#menubar li {
	margin: 0 40px;
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
	border-radius: 10px;
}

/* 하얀 박스 스타일 */
.content-box {
	width: 96%;
	height: 70vh;
	background-color: white;
	margin-left: 1%;
	margin-right: 5%;
	margin-top: 3%;
	border: 1px solid #ccc;
	border-radius: 20px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	position: relative;
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

.top-content-box {
	background-color: white;
}

#pagebutton {
	display: flex;
	justify-content: center;
	margin-top: 2%;
}

#pagebutton a {
	color: black;
	text-decoration: none;
	font-size: 20px;
	margin: 0 10px;
}

#pagebutton strong {
	font-size: 20px;
	color: black;
}

tbody tr:hover {
	cursor: pointer;
}
</style>

</head>

<body>
	<!-- 서브헤더 JSP 임포트 -->
	<c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

	<!-- 상단 메뉴바 -->
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

	<!-- 필터와 테이블 영역 -->
	<div class="content-box">

		<div class="content-title">영업관리 | 거래처관리</div>
		<form action="/moduerp/accountFilter.do">
			<!-- 필터 박스 -->
			<div class="filter-box">
				<select name="filterOption" id="filterOption">
					<option disabled selected>옵션 선택</option>
					<option value="accountName"
						${option == 'accountName' ? 'selected' : ''}>거래처 이름</option>
					<option value="businessNumber"
						${option == 'businessNumber' ? 'selected' : ''}>사업자번호</option>
					<option value="bossName" ${option == 'bossName' ? 'selected' : ''}>대표자
						이름</option>
				</select> <input type="text" name="filterText" id="filterText"
					placeholder="내용 입력" value="${filterText != null ? filterText : ''}" />

				<button type="submit" class="btn">조회</button>
				<button type="button" class="btn"
					onclick="window.location.href='account.do';">초기화</button>
			</div>
		</form>


		<!-- 테이블 -->
		<table>
			<thead>
				<tr>
					<th>순번</th>
					<th>거래처 번호</th>
					<th>거래처 이름</th>
					<th>업태</th>
					<th>대표자 이름</th>
					<th>사업자번호</th>
					<th>거래처 주소</th>
					<th>거래처 전화번호</th>
					<th>거래처 이메일</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="account" items="${accountList}" varStatus="status">
					<tr
						onclick="window.location.href='getAccountDetails.do?accountNo=${account.accountNo}'">
						<td>${(currentPage - 1) * 10 + (status.index + 1)}</td>

						<td>${account.accountNo}</td>
						<td>${account.accountName}</td>
						<td>${account.businessType}</td>
						<td>${account.bossName}</td>
						<td>${account.businessNumber}</td>
						<td>${account.accountAddress}</td>
						<td>${account.accountPhone}</td>
						<td>${account.email}</td>
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
						</c:when>
						<c:otherwise>
							<a
								href="account.do?page=${i}&filterOption=${option}&filterText=${filterText}&startDate=${startDate}&endDate=${endDate}">
								${i} </a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
		</div>

		<!-- 등록 버튼 -->
		<div class="btn-group">
			<a href="accountCreate.do"><button class="btn blue">등록</button></a>
		</div>

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
