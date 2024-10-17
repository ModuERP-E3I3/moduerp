
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

/* thead 스타일 */
thead {
	background-color: #d3d3d3; /* 라이트 그레이 색상 */
	font-size: 15px;
}

tbody {
	font-size: 14px;
}

th {
	white-space: nowrap; /* 줄바꿈 방지 */
}

#refundBtn {
	padding: 4px 8px;
	margin-left: 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 14px;
	background: red;
	font-weight: bold;
	color: white;
}

#cancelRefundBtn {
	padding: 4px 8px;
	margin-left: 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 14px;
	background: blue;
	font-weight: bold;
	color: white;
}
</style>

</head>
<body>
	<hr>
	<form id="refundForm" method="POST">
		<div id="cardTable">
			<h3>결제 내역</h3>
			<table border="1" cellpadding="10" cellspacing="0">
				<thead>
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
						<th>환불 상태</th>
						<th>환불 신청</th>

					</tr>
				</thead>

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
						<td>${log.refundStatus}</td>
						<td><c:choose>

								<c:when test="${log.refundStatus == null}">
									<button type="button" id="refundBtn"
										onclick="submitRefundForm('${log.logId}')">
										환불<br>신청
									</button>
								</c:when>

								<c:otherwise>
									<button type="button" id="cancelRefundBtn"
										onclick="cancelRefund('${log.orderId}', '${log.logId}')">
										환불<br>취소
									</button>
								</c:otherwise>
							</c:choose></td>

					</tr>
				</c:forEach>

			</table>
		</div>
	</form>
</body>

<script type="text/javascript">
	function submitRefundForm(logId) {
		var form = document.getElementById("refundForm");
		form.action = "/moduerp/refund.do"; // 여기서 action 주소 설정
		var input = document.createElement("input");
		input.type = "hidden";
		input.name = "logId";
		input.value = logId;
		form.appendChild(input);
		form.submit();
	}

	function cancelRefund(orderId, logId) {
		var form = document.getElementById("refundForm");
		form.action = "/moduerp/cancelRefund.do"; // 환불 취소 action

		// orderId를 hidden 필드로 추가
		var orderInput = document.createElement("input");
		orderInput.type = "hidden";
		orderInput.name = "orderId";
		orderInput.value = orderId;
		form.appendChild(orderInput);

		// logId를 hidden 필드로 추가
		var logInput = document.createElement("input");
		logInput.type = "hidden";
		logInput.name = "logId";
		logInput.value = logId;
		form.appendChild(logInput);

		form.submit();
	}
</script>
</html>