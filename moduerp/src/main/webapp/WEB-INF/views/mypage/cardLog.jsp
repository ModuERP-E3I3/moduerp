
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/content/cardManagement.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카드</title>
<style type="text/css">
#cardTable {
	margin-top: 2%;
}

.btn_group {
	text-align: right;
	margin-top: 2%;
}

#deleteCard {
	padding: 8px 16px;
	margin-left: 5px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 17px;
	background: red;
	font-weight: bold;
	color: white;
}

#createCard {
	padding: 8px 16px;
	margin-left: 5px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 17px;
	background: blue;
	font-weight: bold;
	color: white;
}
</style>

</head>
<body>
	<hr>

	<div id="cardTable">
		<h3>결제 내역</h3>
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>주문 번호</th>
				<th>구매 목록</th>
				<th>카드사</th>
				<th>카드 번호</th>
				<th>카드 종류</th>
				<th>소유자 유형</th>
				<th>총 결제 금액</th>
				<th>공급가액</th>
				<th>부가가치세</th>
				<th>결제 요청 시각</th>
				<th>결제 승인 시각</th>
				<th>결제 상태</th>

			</tr>


			<c:forEach var="log" items="${payLog}">
				<tr>
					<td>${log.orderId}</td>
					<td>${log.details}</td>
					<td>${log.acquirerCode}</td>
					<td>${log.cardNumber}</td>
					<td>${log.cardType}</td>
					<td>${log.ownerType}</td>
					<td>${log.totalAmount}</td>
					<td>${log.suppliedAmount}</td>
					<td>${log.vat}</td>
					<td><fmt:formatDate value="${log.requestedAt}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${log.approvedAt}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${log.status}</td>
				</tr>
			</c:forEach>

		</table>
	</div>

</body>
</html>