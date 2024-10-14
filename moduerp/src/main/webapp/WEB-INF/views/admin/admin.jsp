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

.filter-options {
	margin-bottom: 10px;
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

	<!-- 메뉴바 임포트 -->
	<c:import url="/WEB-INF/views/common/menubar.jsp" />

	<h1>관리자 페이지</h1>

	<div class="container">
		<h2>회원가입 현황 데이터 테이블</h2>

		<form id="deleteForm"
			action="${pageContext.request.contextPath}/deleteAdmins"
			method="post">
			<button id="deleteButton" type="submit">삭제</button>

			<div class="table-container">
				<div class="scrollable-table">
					<table>
						<thead>
							<tr>
								<th><input type="checkbox" id="selectAll"></th>
								<!-- 전체 선택 체크박스 -->
								<th>가입일자</th>
								<th>가입자 UUID</th>
								<th>가입자 Email</th>
								<th>마지막 로그인 시간</th>
								<th>탈퇴일자</th>
								<th>탈퇴 사유</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="admin" items="${adminList}">
								<tr>
									<td><input type="checkbox" class="delete-checkbox"
										name="uuids[]" value="${admin.uuid}"></td>
									<td>${admin.registrationDate}</td>
									<td>${admin.uuid}</td>
									<td>${admin.empEmail}</td>
									<td>${admin.lastLoginTime}</td>
									<td>${admin.deletedAt}</td>
									<td>${admin.deletedExcuse}</td>
								</tr>
							</c:forEach>
						</tbody>

					</table>
				</div>
			</div>
		</form>
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
