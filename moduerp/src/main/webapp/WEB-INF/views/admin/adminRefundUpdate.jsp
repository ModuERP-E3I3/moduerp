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

#btn {
	text-align: right;
	margin-right: 2%;
	margin-top: 3%;
}

#refundCompleteBtn {
	background-color: #4CAF50;
	color: white;
	padding: 8px 16px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 18px;
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
		<h2>환불 수정</h2>
		<form action="/moduerp/adminRefundUpdate.do" method="POST">
			<div class="table-container">

				<div class="scrollable-table">

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

							<tr>
								<td>${refundDetail.bizNumber}</td>
								<td>${refundDetail.orderId}</td>
								<td>${refundDetail.refundPrice}</td>
								<td><select name="refundStatus">
										<option value="환불 신청 완료"
											${refundDetail.refundStatus == '환불 신청 완료' ? 'selected' : ''}>환불
											신청 완료</option>
										<option value="환불 진행 중"
											${refundDetail.refundStatus == '환불 진행 중' ? 'selected' : ''}>환불
											진행 중</option>
										<option value="환불 완료"
											${refundDetail.refundStatus == '환불 완료' ? 'selected' : ''}>환불
											완료</option>
								</select></td>

								<td>${refundDetail.refundReason}</td>
								<td>${refundDetail.refundDate}</td>
								<td>${refundDetail.name}</td>
							</tr>

						</tbody>
					</table>
					<div id="btn">
						<input type="hidden" name="refundId" value="${refundDetail.refundId}">
						<input type="hidden" name="orderId" value="${refundDetail.orderId}">
						<button type="submit" id="refundCompleteBtn">환불 수정 완료</button>
					</div>


				</div>
			</div>
		</form>

	</div>


	<!-- 푸터 임포트 -->
	<footer>
		<c:import url="/WEB-INF/views/common/footer.jsp" />
	</footer>
</body>
</html>
