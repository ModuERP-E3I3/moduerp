<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<style>
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: column;
}

body {
	flex-grow: 1;
}

.container {
	flex-grow: 1;
	display: flex;
	flex-direction: column;
	padding: 20px;
	background-color: white;
}

.table-container {
	width: 100%; /* 너비 100%로 설정 */
	margin-top: 20px;
}

.scrollable-table {
	max-height: 480px;
	overflow-y: auto;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #f1f1f1;
}

.btn {
	padding: 8px 16px;
	margin-left: 5px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
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

.filter-options {
	margin-bottom: 10px;
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

footer {
	background-color: #f1f1f1;
	padding: 10px;
	text-align: center;
	position: relative;
	width: 100%;
	bottom: 0;
	clear: both;
}
</style>
</head>
<body>
	<!-- 필터 박스 -->
	<form action="/moduerp/adminFilter.do"></form>

	<!-- 메뉴바 임포트 -->
	<c:import url="/WEB-INF/views/common/menubar.jsp" />

	<h1>관리자 페이지</h1>


	<div class="container">
		<h2>회원가입 현황 데이터 테이블</h2>
		
		<!-- 필터 박스 -->
		<form action="/moduerp/adminFilter.do">
			<div class="filter-box">
				<select name="filterOption" id="filterOption">
					<option disabled selected>옵션 선택</option>
					<option value="bizNumber"
						${option == '"bizNumber"' ? 'selected' : ''}>사업자 번호</option>
					<option value="empName" ${option == '"empName"' ? 'selected' : ''}>사원명</option>
					<option value="empEmail"
						${option == '"empEmail"' ? 'selected' : ''}>사원 이메일</option>
					<option value="uuid" ${option == '"uuid"' ? 'selected' : ''}>UUID</option>
				</select> <input type="date" name="startDate" id="startDate"
					value="${startDate != null && startDate.length() >= 10 ? startDate.substring(0, 10) : ''}" />
				<input type="date" name="endDate" id="endDate"
					value="${endDate != null && endDate.length() >= 10 ? endDate.substring(0, 10) : ''}" />
				<input type="text" name="filterText" id="filterText"
					placeholder="내용 입력" value="${filterText != null ? filterText : ''}" />
				<button type="submit" class="btn">조회</button>
				<button type="button" class="btn"
					onclick="window.location.href='admin.do';">초기화</button>
			</div>
		</form>

		<div class="table-container">
			<div class="scrollable-table">
				<table>
					<thead>
						<tr>
							<th>사업자번호</th>
							<th>사원명</th>
							<th>사원 이메일</th>
							<th>UUID</th>
							<th>가입일자</th>
							<th>마지막 로그인 시기</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="admin" items="${adminList}">
							<tr>
								<td>${admin.bizNumber}</td>
								<td>${admin.empName}</td>
								<td>${admin.empEmail}</td>
								<td>${admin.uuid}</td>
								<td>${admin.registrationDate}</td>
								<td>${admin.lastLoginLocation}</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
		</div>
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
							<a
								href="admin.do?page=${i}&filterOption=${option}&filterText=${filterText}&startDate=${startDate}&endDate=${endDate}">${i}</a>
							<!-- 페이지 링크 -->
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
		</div>
	</div>

	<script>
		// 전체 선택 체크박스 동작
		document.getElementById('selectAll').addEventListener('change', function() {
			const checkboxes = document.querySelectorAll('.delete-checkbox');
			checkboxes.forEach(checkbox => {
				checkbox.checked = this.checked;
			});
		});
	</script>

	<!-- 푸터 임포트 -->
	<footer>
		<c:import url="/WEB-INF/views/common/footer.jsp" />
	</footer>

</body>
</html>
