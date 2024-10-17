<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ModuERP</title>

<style type="text/css">

/* 전체 페이지 기본 스타일 */
body, html {
	margin-top: 2%;
	font-family: 'Helvetica Neue', Arial, sans-serif;
	padding: 0;
}

/* 구분선 스타일 */
hr {
	border: none;
	border-top: 1px solid #ddd;
	margin: 40px 0;
}

/* 이미지 컨테이너 스타일 */
div#container {
	display: flex;
	justify-content: center; /* 중앙 정렬 */
	align-items: center;
	gap: 200px; /* 이미지 사이 간격 */
	background-color: white;
	border-radius: 20px;
	margin: 40px auto; /* 페이지 중앙 정렬 */
	width: 100%;
	height: 700px;
	max-width: 2400px;
}

/* refundContainer 스타일 수정 */
#refundContainer {
	width: 80%; /* 좌우 80% 차지 */
	border: 1px solid black; /* 테두리 두께 1, 색상 검은색 */
	height: 100%; /* 부모 컨테이너 높이 차지 */
	box-sizing: border-box; /* 테두리가 컨테이너 내부에서 적용되도록 설정 */
}

#subContainer {
	display: flex;
	justify-content: center; /* 수평 가운데 정렬 */
	align-items: center; /* 수직 가운데 정렬 */
	height: 100%; /* 부모 높이 모두 차지 */
	gap: 10%;
}

#refundReseonTextArea {
	border: 2px solid black; /* 테두리 두께 2px, 색상 검은색 */
	width: 400px;
	height: 300px;
}

#btn {
	margin-top: -10%;
	text-align: right;
	margin-right: 2%;
}

#refundBtn {
	padding: 8px 16px;
	margin-left: 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 18px;
	background: red;
	font-weight: bold;
	color: white;
}
</style>

<script type="text/javascript">
	function movePage() {
		location.href = "loginPage.do";
	}
</script>

</head>
<body>

	<form action="/moduerp/refundInsert.do" method="POST">
		<!-- 서브 이미지 컨테이너 -->
		<div id="container">

			<div id="refundContainer">
				<div>환불 요청</div>
				<div id="subContainer">
					<table id="payLogDetail" border="1" cellpadding="10"
						cellspacing="0">
						<tr>
							<th>주문 번호</th>
							<td>${payLogDTO.orderId}</td>
						</tr>
						<tr>
							<th>구매 목록</th>
							<td>${payLogDTO.details}</td>
						</tr>
						<tr>
							<th>카드사</th>
							<td>${payLogDTO.acquirerCode}</td>
						</tr>
						<tr>
							<th>카드 번호</th>
							<td>${payLogDTO.cardNumber}</td>
						</tr>
						<tr>
							<th>카드 종류</th>
							<td>${payLogDTO.cardType}</td>
						</tr>
						<tr>
							<th>소유자 유형</th>
							<td>${payLogDTO.ownerType}</td>
						</tr>
						<tr>
							<th>총 결제 금액</th>
							<td>${payLogDTO.totalAmount}</td>
						</tr>
						<tr>
							<th>결제 상태</th>
							<td>${payLogDTO.status}</td>
						</tr>
					</table>

					<div id="refundReseon">
						<textarea name="refundReseon" id="refundReseonTextArea" rows="20" cols="40"
							placeholder="환불 사유를 입력하세요."></textarea>
					</div>

				</div>

				<div id="btn">
					<input type="hidden" name="logId" value="${payLogDTO.logId}">
					<button type="submit" id="refundBtn">환불 신청</button>
					
				</div>
			</div>
		</div>
	</form>


	<!-- 메뉴바 임포트 -->
	<c:import url="/WEB-INF/views/common/menubar.jsp" />



	<!-- 푸터 임포트 -->
	<c:import url="/WEB-INF/views/common/footer.jsp" />

</body>
</html>
