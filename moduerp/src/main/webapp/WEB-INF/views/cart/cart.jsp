<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>

<style type="text/css">

/* 전체 페이지 기본 스타일 */
body, html {
	margin-top: 2%;
	font-family: 'Helvetica Neue', Arial, sans-serif;
	padding: 0;
	background-color: #f4f4f4;
}

/* 구분선 스타일 */
hr {
	border: none;
	border-top: 1px solid #ddd;
	margin: 40px 0;
}

/* 이미지 컨테이너 스타일 */
div#image-container {
	flex-direction: column; /* 상하 정렬 */
	align-items: center;
	background-color: white;
	box-shadow: 0 12px 24px rgba(0, 0, 0, 0.2); /* 입체감 설정 */
	border-radius: 20px;
	margin: 40px auto; /* 페이지 중앙 정렬 */
	width: 100%;
	height: 700px;
	max-width: 2400px;
}

div.image-wrapper {
	position: relative;
	text-align: center;
}

/* 테이블 스타일 */
table {
	width: 90%;
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
}

.btn {
	padding: 8px 16px;
	margin-left: 5px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.btn {
	text-align: right; /* 오른쪽 정렬 */
	padding: 8px 16px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.btn_deleteAll {
	background-color: red;
	color: white;
	padding: 8px 16px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.btn_deleteCheck {
	background-color: blue;
	color: white;
	padding: 8px 16px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.btn_cart {
	background-color: #4CAF50;
	color: white;
	padding: 8px 16px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.left-group {
	text-align: left;
}

.right-group {
	text-align: right;
}

.moduleUseAgeY {
	margin-top: 1%;
	font-weight: bold;
	font-size: 20px;
}

.moduleUseAgeN {
	margin-top: 3%;
	font-weight: bold;
	font-size: 20px;
}

.moduleTable {
	margin: 0 auto; /* 좌우 가운데 정렬 */
	width: 90%; /* 테이블의 너비를 90%로 설정 */
}

.moduleTableScroll {
	max-height: 630px; /* 원하는 높이 설정 */
	overflow-y: auto; /* 스크롤 활성화 */
	margin: 0 auto; /* 좌우 가운데 정렬 */
	width: 90%; /* 테이블의 너비를 90%로 설정 */
}
</style>

<script type="text/javascript">
	function movePage() {
		location.href = "loginPage.do";
	}
</script>

</head>
<body>

	<form id="cartForm" method="post">
		<!-- 서브 이미지 컨테이너 -->
		<div id="image-container">

			<!-- 모듈 리스트 출력 -->
			<div class="moduleTable">
				<div class="moduleTableScroll">

					<table>
						<thead>
							<tr>
								<th></th>
								<th>종류</th>
								<th>모듈 이름</th>
								<th>모듈 가격</th>
								<th>모듈 설명</th>
								<th>모듈 버전</th>

							</tr>
						</thead>
						<tbody>
							<c:if test="${not empty modules}">

								<!-- 그룹웨어 카운트 변수 초기화 -->
								<c:set var="groupwareCount" value="0" />

								<!-- 그룹웨어 조건 만족 횟수 카운트 -->
								<c:forEach var="module" items="${modules}">
									<c:if test="${module.moduleType == '그룹웨어'}">
										<c:set var="groupwareCount" value="${groupwareCount + 1}" />
									</c:if>
								</c:forEach>

								<!-- 그룹웨어 데이터 출력 -->
								<c:forEach var="module" items="${modules}" varStatus="status">
									<c:if test="${module.moduleType == '그룹웨어'}">
										<tr>
											<td><input type="checkbox" name="selectedModules"
												value="${module.moduleGrade}"></td>
											<!-- 첫 번째 그룹웨어일 때만 rowspan 적용 -->
											<c:if test="${status.index == 0}">
												<td rowspan="${groupwareCount}">${module.moduleType}</td>
											</c:if>

											<td>${module.moduleName}</td>
											<td>${module.modulePrice}</td>
											<td>${module.moduleDesc}</td>
											<td>${module.moduleVer}</td>
										</tr>
									</c:if>
								</c:forEach>



								<!-- 생산 모듈 카운트 및 첫 번째 표시 변수 초기화 -->
								<c:set var="productionCount" value="0" />
								<c:set var="firstProductionRendered" value="false" />

								<!-- 생산 모듈 조건 만족 횟수 카운트 -->
								<c:forEach var="module" items="${modules}">
									<c:if test="${module.moduleType == '생산'}">
										<c:set var="productionCount" value="${productionCount + 1}" />
									</c:if>
								</c:forEach>

								<!-- 생산 모듈 데이터 출력 -->
								<c:forEach var="module" items="${modules}">
									<c:if test="${module.moduleType == '생산'}">
										<tr>
											<td><input type="checkbox" name="selectedModules"
												value="${module.moduleGrade}"></td>

											<!-- 첫 번째 생산 모듈일 때만 rowspan 적용 -->
											<c:if test="${!firstProductionRendered}">
												<td rowspan="${productionCount}">${module.moduleType}</td>
												<c:set var="firstProductionRendered" value="true" />
											</c:if>

											<td>${module.moduleName}</td>
											<td>${module.modulePrice}</td>
											<td>${module.moduleDesc}</td>
											<td>${module.moduleVer}</td>
										</tr>
									</c:if>
								</c:forEach>




								<!-- 영업 모듈 카운트 및 첫 번째 표시 변수 초기화 -->
								<c:set var="salesCount" value="0" />
								<c:set var="firstSalesRendered" value="false" />

								<!-- 영업 모듈 조건 만족 횟수 카운트 -->
								<c:forEach var="module" items="${modules}">
									<c:if test="${module.moduleType == '영업'}">
										<c:set var="salesCount" value="${salesCount + 1}" />
									</c:if>
								</c:forEach>

								<!-- 영업 모듈 데이터 출력 -->
								<c:forEach var="module" items="${modules}">
									<c:if test="${module.moduleType == '영업'}">
										<tr>
											<td><input type="checkbox" name="selectedModules"
												value="${module.moduleGrade}"></td>

											<!-- 첫 번째 영업 모듈일 때만 rowspan 적용 -->
											<c:if test="${!firstSalesRendered}">
												<td rowspan="${salesCount}">${module.moduleType}</td>
												<c:set var="firstSalesRendered" value="true" />
											</c:if>

											<td>${module.moduleName}</td>
											<td>${module.modulePrice}</td>
											<td>${module.moduleDesc}</td>
											<td>${module.moduleVer}</td>
										</tr>
									</c:if>
								</c:forEach>


								<!-- 구매 모듈 카운트 및 첫 번째 표시 변수 초기화 -->
								<c:set var="purchaseCount" value="0" />
								<c:set var="firstPurchaseRendered" value="false" />

								<!-- 구매 모듈 조건 만족 횟수 카운트 -->
								<c:forEach var="module" items="${modules}">
									<c:if test="${module.moduleType == '구매'}">
										<c:set var="purchaseCount" value="${purchaseCount + 1}" />
									</c:if>
								</c:forEach>

								<!-- 구매 모듈 데이터 출력 -->
								<c:forEach var="module" items="${modules}">
									<c:if test="${module.moduleType == '구매'}">
										<tr>
											<td><input type="checkbox" name="selectedModules"
												value="${module.moduleGrade}"></td>

											<!-- 첫 번째 구매 모듈일 때만 rowspan 적용 -->
											<c:if test="${!firstPurchaseRendered}">
												<td rowspan="${purchaseCount}">${module.moduleType}</td>
												<c:set var="firstPurchaseRendered" value="true" />
											</c:if>

											<td>${module.moduleName}</td>
											<td>${module.modulePrice}</td>
											<td>${module.moduleDesc}</td>
											<td>${module.moduleVer}</td>
										</tr>
									</c:if>
								</c:forEach>


								<!-- 차량 모듈 카운트 및 첫 번째 표시 변수 초기화 -->
								<c:set var="vehicleCount" value="0" />
								<c:set var="firstVehicleRendered" value="false" />

								<!-- 차량 모듈 조건 만족 횟수 카운트 -->
								<c:forEach var="module" items="${modules}">
									<c:if test="${module.moduleType == '차량'}">
										<c:set var="vehicleCount" value="${vehicleCount + 1}" />
									</c:if>
								</c:forEach>

								<!-- 차량 모듈 데이터 출력 -->
								<c:forEach var="module" items="${modules}">
									<c:if test="${module.moduleType == '차량'}">
										<tr>
											<td><input type="checkbox" name="selectedModules"
												value="${module.moduleGrade}"></td>

											<!-- 첫 번째 차량 모듈일 때만 rowspan 적용 -->
											<c:if test="${!firstVehicleRendered}">
												<td rowspan="${vehicleCount}">${module.moduleType}</td>
												<c:set var="firstVehicleRendered" value="true" />
											</c:if>

											<td>${module.moduleName}</td>
											<td>${module.modulePrice}</td>
											<td>${module.moduleDesc}</td>
											<td>${module.moduleVer}</td>
										</tr>
									</c:if>
								</c:forEach>

								
								.<!-- 회계 모듈 카운트 및 첫 번째 표시 변수 초기화 -->
								<c:set var="accountingCount" value="0" />
								<c:set var="firstAccountingRendered" value="false" />

								<!-- 회계 모듈 조건 만족 횟수 카운트 -->
								<c:forEach var="module" items="${modules}">
									<c:if test="${module.moduleType == '회계'}">
										<c:set var="accountingCount" value="${accountingCount + 1}" />
									</c:if>
								</c:forEach>

								<!-- 회계 모듈 데이터 출력 -->
								<c:forEach var="module" items="${modules}">
									<c:if test="${module.moduleType == '회계'}">
										<tr>
											<td><input type="checkbox" name="selectedModules"
												value="${module.moduleGrade}"></td>

											<!-- 첫 번째 회계 모듈일 때만 rowspan 적용 -->
											<c:if test="${!firstAccountingRendered}">
												<td rowspan="${accountingCount}">${module.moduleType}</td>
												<c:set var="firstAccountingRendered" value="true" />
											</c:if>

											<td>${module.moduleName}</td>
											<td>${module.modulePrice}</td>
											<td>${module.moduleDesc}</td>
											<td>${module.moduleVer}</td>
										</tr>
									</c:if>
								</c:forEach>

							</c:if>

							<c:if test="${empty modules}">
								<tr>
									<td colspan="6">장바구니가 비어 있습니다.</td>
								</tr>
							</c:if>
						</tbody>

					</table>

				</div>
				<!-- 버튼 그룹 -->
				<div class="btn-group">
					<div class="left-group">
						<button type="button" class="btn_deleteAll"
							onclick="deleteAllItems()">전체 항목 삭제하기</button>
						<button type="button" class="btn_deleteCheck"
							onclick="submitSelectedItems()">선택 항목 삭제하기</button>
					</div>
					<div class="right-group">
						<button type="button" class="btn_cart">결제하기</button>
					</div>
				</div>
			</div>
		</div>


	</form>



	<!-- 메뉴바 임포트 -->
	<c:import url="/WEB-INF/views/common/menubar.jsp" />



	<!-- 푸터 임포트 -->
	<c:import url="/WEB-INF/views/common/footer.jsp" />

</body>
<script type="text/javascript">

function deleteAllItems() {
    // 특정 매핑 주소로 이동
    location.href = 'deleteAllItems.do';
}

//선택된 모듈 삭제 요청
function submitSelectedItems() {
    const form = document.getElementById('cartForm');
    const selectedModules = Array.from(document.querySelectorAll('input[name="selectedModules"]:checked'))
        .map(checkbox => checkbox.value);

    if (selectedModules.length === 0) {
        alert("삭제할 항목을 선택하세요.");
        return;
    }

    // FormData 객체 생성 및 데이터 추가
    const formData = new FormData();
    selectedModules.forEach(module => formData.append('selectedModules', module));

    // AJAX 요청 보내기
    fetch('cartSelectDelete.do', {
        method: 'POST',
        body: formData
    })
    .then(response => response.text())
    .then(result => {
        alert("선택된 항목이 삭제되었습니다.");
        location.reload();  // 페이지 새로고침
    })
    .catch(error => console.error('Error:', error));
}
</script>


</html>

