<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AdminRefund</title>
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
	margin-left: 10%;
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

.btn {
	padding: 8px 16px;
	margin-left: 5px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
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

#refundText {
	font-weight: bold;
	font-size: 20px;
	margin-bottom: 3px;
}
</style>
</head>
<body>
	<!-- 필터 박스 -->
	<form action="/moduerp/adminFilter.do"></form>

	<c:import url="/WEB-INF/views/common/menubar.jsp" />

	<h1>관리자 페이지</h1>


	<c:import url="/WEB-INF/views/common/adminMenubar.jsp" />
	<div class="container">
		<h2>환불 현황</h2>

		<div class="table-container">
			<div class="scrollable-table">
				<div id="refundText">환불 신청 완료</div>
				<table>
					<thead>
						<tr>
							<th>사업자번호</th>
							<th>주문번호</th>
							<th>환불 금액</th>
							<th>환불 상태</th>
							<th>환불 사유</th>
							<th>환불 요청 날짜</th>
							<th>환불 요청자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="refundApply" items="${refundApply}">
							<tr
								onclick="window.location.href='refundUpdate.do?refundId=${refundApply.refundId}'">
								<td>${refundApply.bizNumber}</td>
								<td>${refundApply.orderId}</td>
								<td>${refundApply.refundPrice}</td>
								<td>${refundApply.refundStatus}</td>
								<td>${refundApply.refundReason}</td>
								<td>${refundApply.refundDate}</td>
								<td>${refundApply.name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<br>
				<div id="refundText">환불 진행중</div>
				<table>
					<thead>
						<tr>
							<th>사업자번호</th>
							<th>주문번호</th>
							<th>환불 금액</th>
							<th>환불 상태</th>
							<th>환불 사유</th>
							<th>환불 요청 날짜</th>
							<th>환불 요청자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="refunding" items="${refunding}">
							<tr
								onclick="window.location.href='refundUpdate.do?refundId=${refunding.refundId}'">
								<td>${refunding.bizNumber}</td>
								<td>${refunding.orderId}</td>
								<td>${refunding.refundPrice}</td>
								<td>${refunding.refundStatus}</td>
								<td>${refunding.refundReason}</td>
								<td>${refunding.refundDate}</td>
								<td>${refunding.name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<br>
				<div id="refundText">환불 완료</div>
				<table>
					<thead>
						<tr>
							<th>사업자번호</th>
							<th>주문번호</th>
							<th>환불 금액</th>
							<th>환불 상태</th>
							<th>환불 사유</th>
							<th>환불 요청 날짜</th>
							<th>환불 요청자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="refundComplete" items="${refundComplete}">
							<tr
								onclick="window.location.href='refundUpdate.do?refundId=${refundComplete.refundId}'">
								<td>${refundComplete.bizNumber}</td>
								<td>${refundComplete.orderId}</td>
								<td>${refundComplete.refundPrice}</td>
								<td>${refundComplete.refundStatus}</td>
								<td>${refundComplete.refundReason}</td>
								<td>${refundComplete.refundDate}</td>
								<td>${refundComplete.name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</div>



	<!-- 푸터 임포트 -->
	<footer>
		<c:import url="/WEB-INF/views/common/footer.jsp" />
	</footer>

</body>
</html>
