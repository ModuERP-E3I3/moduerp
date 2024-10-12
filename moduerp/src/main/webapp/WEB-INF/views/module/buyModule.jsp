<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ModuERP-BuyModule-List</title>

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
	text-align: right;
}

.btn {
	padding: 8px 16px;
	margin-left: 5px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.btn.blue {
	background-color: blue;
	color: white;
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

/* 버튼 스타일 */
.btn {
	text-align: right; /* 오른쪽 정렬 */
	padding: 8px 16px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.btn.blue {
	background-color: blue;
	color: white;
	margin-right: -5%;
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

	<!-- 서브 이미지 컨테이너 -->
	<div id="image-container">

		<!-- 모듈 리스트 출력 -->
		<div class="moduleTable">
			<div class="moduleTableScroll">

				<table>
					<thead>
						<tr>
							<th></th>
							<th>체크</th>
							<th>모듈 이름</th>
							<th>모듈 가격</th>
							<th>모듈 설명</th>
							<th>모듈 버전</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="moduleGroupCount"
							value="${fn:length(moduleListGroup)}" />
						<c:forEach var="moduleListGroup" items="${moduleListGroup}"
							varStatus="status">
							<tr>
								<!-- 첫 번째 반복에서만 rowspan이 적용된 <td>를 출력 -->
								<c:if test="${status.index == 0}">
									<td rowspan="${moduleGroupCount}">그룹웨어</td>
								</c:if>
								<td><input type="checkbox" class="moduleCheckboxGroup"
									value="${moduleListGroup.moduleGrade}"
									onclick="syncCheckboxesGroup(this)"> <input name=""
									value="${moduleListGroup.moduleId }"></td>

								<td>${moduleListGroup.moduleName}</td>
								<td>${moduleListGroup.modulePrice}</td>
								<td>${moduleListGroup.moduleDesc}</td>
								<td>${moduleListGroup.moduleVer}</td>
							</tr>
						</c:forEach>

						<c:set var="moduleProductionCount"
							value="${fn:length(moduleListProduction)}" />
						<c:forEach var="moduleListProduction"
							items="${moduleListProduction}" varStatus="status">
							<tr>
								<!-- 첫 번째 반복에서만 rowspan이 적용된 <td>를 출력 -->
								<c:if test="${status.index == 0}">
									<td rowspan="${moduleProductionCount}">생산</td>
								</c:if>
								<td><input type="checkbox" class="moduleCheckboxProduction"
									value="${moduleListProduction.moduleGrade}"
									onclick="syncCheckboxesProduction(this)"> <input
									name="" value="${moduleListProduction.moduleId}"></td>
								<td>${moduleListProduction.moduleName}</td>
								<td>${moduleListProduction.modulePrice}</td>
								<td>${moduleListProduction.moduleDesc}</td>
								<td>${moduleListProduction.moduleVer}</td>
							</tr>
						</c:forEach>

						<c:set var="moduleBuyCount" value="${fn:length(moduleListBuy)}" />
						<c:forEach var="moduleListBuy" items="${moduleListBuy}"
							varStatus="status">
							<tr>
								<!-- 첫 번째 반복에서만 rowspan이 적용된 <td>를 출력 -->
								<c:if test="${status.index == 0}">
									<td rowspan="${moduleBuyCount}">구매</td>
								</c:if>
								<td><input type="checkbox" class="moduleCheckboxBuy"
									value="${moduleListBuy.moduleGrade}"
									onclick="syncCheckboxesBuy(this)"> <input name=""
									value="${moduleListBuy.moduleId }"> </td>
								<td>${moduleListBuy.moduleName}</td>
								<td>${moduleListBuy.modulePrice}</td>
								<td>${moduleListBuy.moduleDesc}</td>
								<td>${moduleListBuy.moduleVer}</td>
							</tr>
						</c:forEach>

						<c:set var="moduleSalesCount"
							value="${fn:length(moduleListSales)}" />
						<c:forEach var="moduleListSales" items="${moduleListSales}"
							varStatus="status">
							<tr>
								<!-- 첫 번째 반복에서만 rowspan이 적용된 <td>를 출력 -->
								<c:if test="${status.index == 0}">
									<td rowspan="${moduleSalesCount}">영업</td>
								</c:if>
								<td><input type="checkbox" class="moduleCheckboxSales"
									value="${moduleListSales.moduleGrade}"
									onclick="syncCheckboxesSales(this)"> <input name=""
									value="${moduleListSales.moduleId }"></td>
								<td>${moduleListSales.moduleName}</td>
								<td>${moduleListSales.modulePrice}</td>
								<td>${moduleListSales.moduleDesc}</td>
								<td>${moduleListSales.moduleVer}</td>
							</tr>
						</c:forEach>

						<c:set var="moduleCarCount" value="${fn:length(moduleListCar)}" />
						<c:forEach var="moduleListCar" items="${moduleListCar}"
							varStatus="status">
							<tr>
								<!-- 첫 번째 반복에서만 rowspan이 적용된 <td>를 출력 -->
								<c:if test="${status.index == 0}">
									<td rowspan="${moduleCarCount}">차량</td>
								</c:if>
								<td><input type="checkbox" class="moduleCheckboxCar"
									value="${moduleListCar.moduleGrade}"
									onclick="syncCheckboxesCar(this)"> <input name=""
									value="${moduleListCar.moduleId }"></td>
								<td>${moduleListCar.moduleName}</td>
								<td>${moduleListCar.modulePrice}</td>
								<td>${moduleListCar.moduleDesc}</td>
								<td>${moduleListCar.moduleVer}</td>
							</tr>
						</c:forEach>

						<c:set var="moduleAccountCount"
							value="${fn:length(moduleListAccount)}" />
						<c:forEach var="moduleListAccount" items="${moduleListAccount}"
							varStatus="status">
							<tr>
								<!-- 첫 번째 반복에서만 rowspan이 적용된 <td>를 출력 -->
								<c:if test="${status.index == 0}">
									<td rowspan="${moduleAccountCount}">회계</td>
								</c:if>
								<td><input type="checkbox" class="moduleCheckboxAccount"
									value="${moduleListAccount.moduleGrade}"
									onclick="syncCheckboxesAccount(this)"> <input name=""
									value="${moduleListAccount.moduleId }"></td>
								<td>${moduleListAccount.moduleName}</td>
								<td>${moduleListAccount.modulePrice}</td>
								<td>${moduleListAccount.moduleDesc}</td>
								<td>${moduleListAccount.moduleVer}</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>

			</div>
			<!-- 버튼 그룹 -->
			<div class="btn-group">
				<c:if test="${bizNumber == '0000000000'}">
					<a href="moduleList.do"><button class="btn blue">모듈
							리스트</button></a>
				</c:if>
			</div>
		</div>

	</div>


	<!-- 메뉴바 임포트 -->
	<c:import url="/WEB-INF/views/common/menubar.jsp" />



	<!-- 푸터 임포트 -->
	<c:import url="/WEB-INF/views/common/footer.jsp" />

</body>

<script type="text/javascript">
	// 그룹웨어
	function syncCheckboxesGroup(checkbox) {
		var checkboxes = document.getElementsByClassName('moduleCheckboxGroup');

		for (var i = 0; i < checkboxes.length; i++) {
			// 체크된 체크박스의 값이 "ATD"일 때 "AD"도 체크, 또는 그 반대
			if ((checkbox.value === 'ATD' && checkboxes[i].value === 'AD')
					|| (checkbox.value === 'AD' && checkboxes[i].value === 'ATD')) {

				checkboxes[i].checked = checkbox.checked;
			}
		}
	}

	// 생산
	function syncCheckboxesProduction(checkbox) {
		var checkboxes = document
				.getElementsByClassName('moduleCheckboxProduction');

		for (var i = 0; i < checkboxes.length; i++) {
			if ((checkbox.value === 'P_IN' && checkboxes[i].value === 'P_OUT')
					|| (checkbox.value === 'P_OUT' && checkboxes[i].value === 'P_IN')) {

				checkboxes[i].checked = checkbox.checked;
			}
		}
	}

	// 구매
	function syncCheckboxesBuy(checkbox) {
		var checkboxes = document.getElementsByClassName('moduleCheckboxBuy');

		for (var i = 0; i < checkboxes.length; i++) {
			if ((checkbox.value === 'B_IN' && (checkboxes[i].value === 'B_OUT' || checkboxes[i].value === 'OF'))
					|| (checkbox.value === 'B_OUT' && (checkboxes[i].value === 'B_IN' || checkboxes[i].value === 'OF'))
					|| (checkbox.value === 'OF' && (checkboxes[i].value === 'B_IN' || checkboxes[i].value === 'B_OUT'))) {

				checkboxes[i].checked = checkbox.checked;
			}
		}
	}

	// 영업
	function syncCheckboxesSales(checkbox) {
		var checkboxes = document.getElementsByClassName('moduleCheckboxSales');

		for (var i = 0; i < checkboxes.length; i++) {
			if ((checkbox.value === 'S_IN' && checkboxes[i].value === 'S_OUT')
					|| (checkbox.value === 'S_OUT' && checkboxes[i].value === 'S_IN')) {

				checkboxes[i].checked = checkbox.checked;
			}
		}
	}

	// 차량
	function syncCheckboxesCar(checkbox) {
		var checkboxes = document.getElementsByClassName('moduleCheckboxCar');

		for (var i = 0; i < checkboxes.length; i++) {
			if ((checkbox.value === 'C_I' && checkboxes[i].value === 'C_R')
					|| (checkbox.value === 'C_R' && checkboxes[i].value === 'C_I')) {

				checkboxes[i].checked = checkbox.checked;
			}
		}
	}
	// 회계
</script>


</html>

