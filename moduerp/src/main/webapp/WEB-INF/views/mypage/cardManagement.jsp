
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
	margin-left: 40%;
	margin-top: 5%;
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
		<h3>등록된 카드</h3>
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>카드사</th>
				<th>카드 번호</th>
				<th>등록 날짜</th>
			</tr>


			<tr>
				<td>${cardInfo.cardCompany}카드</td>
				<td>${cardInfo.cardNumber}</td>

				<td><fmt:formatDate value="${cardInfo.createdAt}"
						pattern="yyyy-MM-dd" /></td>
				<%-- <td>${card.cardBillingId}</td> --%>
			</tr>

		</table>
	</div>
	<div class="btn_group">
		<c:choose>
			<c:when test="${not empty cardInfo}">
				<button id="deleteCard"
					onclick="window.location.href='deleteCardBilling.do?cardBillingId=${cardInfo.cardBillingId}';">카드
					삭제하기</button>
			</c:when>
			<c:otherwise>
				<button id="createCard"
					onclick="window.location.href='regularPayment.do';">카드
					등록하기</button>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
