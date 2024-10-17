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

.btn_cart {
	background-color: #4CAF50;
	color: white;
	padding: 8px 16px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.moduleTableScroll {
	max-height: 630px; /* 원하는 높이 설정 */
	overflow-y: auto; /* 스크롤 활성화 */
	margin: 0 auto; /* 좌우 가운데 정렬 */
	width: 90%; /* 테이블의 너비를 90%로 설정 */
}

#cart-modal {
	display: none; /* 초기에는 보이지 않도록 설정 */
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5); /* 배경 반투명 */
	display: flex;
	justify-content: center;
	align-items: center;
}

.modal-content {
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	text-align: center;
	width: 300px;
}

.modal-content h2 {
	margin-bottom: 20px;
}

.modal-content button {
	padding: 10px 20px;
	margin: 10px;
	border: none;
	border-radius: 10px;
	cursor: pointer;
}

.go-cart {
	background-color: green !important;
	color: #fff;
}

.continue-shopping {
	background-color: gray !important;
	color: #fff;
}

input[type="checkbox"]:disabled {
	background-color: green; /* 배경색을 그린으로 설정 */
	opacity: 1; /* 투명도를 기본으로 설정 */
	border: 1px solid green; /* 테두리도 그린으로 설정 */
	cursor: not-allowed; /* 사용 불가 커서 표시 */
}
</style>
</head>
<body>

	<form id="cartForm" action="insertCart.do" method="post">
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

							<c:set var="moduleErpMgtCount"
								value="${fn:length(moduleListEmpMgt)}" />
							<c:forEach var="moduleListEmpMgt" items="${moduleListEmpMgt}"
								varStatus="status">
								<tr>
									<!-- 첫 번째 반복에서만 rowspan이 적용된 <td>를 출력 -->
									<c:if test="${status.index == 0}">
										<td rowspan="${moduleListEmpMgt}">그룹웨어</td>
									</c:if>

									<td><c:choose>
											<c:when
												test="${fn:contains(purchasedModulesList, moduleListEmpMgt.moduleGrade)}">
												<span style="color: red; font-weight: bold;">이미 구매한
													모듈입니다</span>
											</c:when>
											<c:otherwise>
												<input type="checkbox" class="moduleCheckboxGroup"
													value="${moduleListEmpMgt.moduleGrade}"
													onclick="syncCheckboxesGroup(this)"
													<c:if test="${moduleListEmpMgt.moduleGrade == 'HR'}">
						                               checked="checked" disabled="disabled"
						                           </c:if>>
												<input type="hidden" name=""
													value="${moduleListEmpMgt.moduleId}">
											</c:otherwise>
										</c:choose></td>

									<td>${moduleListEmpMgt.moduleName}</td>
									<td>${moduleListEmpMgt.modulePrice}</td>
									<td>${moduleListEmpMgt.moduleDesc}</td>
									<td>${moduleListEmpMgt.moduleVer}</td>
								</tr>
							</c:forEach>


							<c:set var="moduleGroupCount"
								value="${fn:length(moduleListGroup)}" />
							<c:forEach var="moduleListGroup" items="${moduleListGroup}"
								varStatus="status">
								<tr>
									<!-- 첫 번째 반복에서만 rowspan이 적용된 <td>를 출력 -->
									<c:if test="${status.index == 0}">
										<td rowspan="${moduleGroupCount}">그룹웨어</td>
									</c:if>

									<td><c:choose>
											<c:when
												test="${fn:contains(purchasedModulesList, moduleListGroup.moduleGrade)}">
												<span style="color: red; font-weight: bold;">이미 구매한
													모듈입니다</span>
											</c:when>
											<c:otherwise>
												<input type="checkbox" class="moduleCheckboxGroup"
													value="${moduleListGroup.moduleGrade}"
													onclick="syncCheckboxesGroup(this)"
													<c:if test="${moduleListGroup.moduleGrade == 'AD' 
						                                        || moduleListGroup.moduleGrade == 'ATD' 
						                                        || moduleListGroup.moduleGrade == 'EM'}">
						                               checked="checked" disabled="disabled"
						                           </c:if>>
												<input type="hidden" name=""
													value="${moduleListGroup.moduleId}">
											</c:otherwise>
										</c:choose></td>

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

									<td><c:choose>
											<c:when
												test="${fn:contains(purchasedModulesList, moduleListProduction.moduleGrade)}">
												<span style="color: red; font-weight: bold;">이미 구매한
													모듈입니다</span>
											</c:when>
											<c:otherwise>
												<input type="checkbox" class="moduleCheckboxProduction"
													value="${moduleListProduction.moduleGrade}"
													onclick="syncCheckboxesProduction(this)">
												<input type="hidden" name=""
													value="${moduleListProduction.moduleId}">
											</c:otherwise>
										</c:choose></td>

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

									<td><c:choose>
											<c:when
												test="${fn:contains(purchasedModulesList, moduleListBuy.moduleGrade)}">
												<span style="color: red; font-weight: bold;">이미 구매한
													모듈입니다</span>
											</c:when>
											<c:otherwise>
												<input type="checkbox" class="moduleCheckboxBuy"
													value="${moduleListBuy.moduleGrade}"
													onclick="syncCheckboxesBuy(this)">
												<input type="hidden" name=""
													value="${moduleListBuy.moduleId}">
											</c:otherwise>
										</c:choose></td>

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

									<td><c:choose>
											<c:when
												test="${fn:contains(purchasedModulesList, moduleListSales.moduleGrade)}">
												<span style="color: red; font-weight: bold;">이미 구매한
													모듈입니다</span>
											</c:when>
											<c:otherwise>
												<input type="checkbox" class="moduleCheckboxSales"
													value="${moduleListSales.moduleGrade}"
													onclick="syncCheckboxesSales(this)">
												<input type="hidden" name=""
													value="${moduleListSales.moduleId}">
											</c:otherwise>
										</c:choose></td>

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

									<td><c:choose>
											<c:when
												test="${fn:contains(purchasedModulesList, moduleListCar.moduleGrade)}">
												<span style="color: red; font-weight: bold;">이미 구매한
													모듈입니다</span>
											</c:when>
											<c:otherwise>
												<input type="checkbox" class="moduleCheckboxCar"
													value="${moduleListCar.moduleGrade}"
													onclick="syncCheckboxesCar(this)">
												<input type="hidden" name=""
													value="${moduleListCar.moduleId}">
											</c:otherwise>
										</c:choose></td>

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

									<td><c:choose>
											<c:when
												test="${fn:contains(purchasedModulesList, moduleListAccount.moduleGrade)}">
												<span style="color: red; font-weight: bold;">이미 구매한
													모듈입니다</span>
											</c:when>
											<c:otherwise>
												<input type="checkbox" class="moduleCheckboxAccount"
													value="${moduleListAccount.moduleGrade}"
													onclick="syncCheckboxesAccount(this)">
												<input type="hidden" name=""
													value="${moduleListAccount.moduleId}">
											</c:otherwise>
										</c:choose></td>

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
					<button type="button" class="btn_cart" onclick="submitCart()">장바구니
						담기</button>
					<c:if test="${bizNumber == '0000000000'}">
						<!-- a 태그를 사용하지 않고 버튼만 사용 -->
						<button class="btn blue" type="button"
							onclick="location.href='moduleList.do'">모듈 리스트</button>

					</c:if>
				</div>
			</div>
		</div>
		<!-- 장바구니 확인 모달 -->
		<div id="cart-modal" style="display: none;">
			<div class="modal-content">
				<h2>장바구니에 담았습니다!</h2>
				<!-- 숨겨진 필드 추가 -->
				<input type="hidden" id="buttonClicked" name="buttonClicked"
					value="">

				<!-- '장바구니 가기' 버튼 -->
				<button class="go-cart"
					onclick="setButtonValue('go-cart'); goToCart()">장바구니 가기</button>

				<!-- '계속 쇼핑하기' 버튼 -->
				<button class="continue-shopping"
					onclick="setButtonValue('continue-shopping'); closeCartModal()">계속
					쇼핑하기</button>

			</div>
		</div>
	</form>



	<!-- 메뉴바 임포트 -->
	<c:import url="/WEB-INF/views/common/menubar.jsp" />



	<!-- 푸터 임포트 -->
	<c:import url="/WEB-INF/views/common/footer.jsp" />

</body>

<script type="text/javascript">
	function submitCart() {
		var form = document.getElementById('cartForm');

		// 각 모듈의 체크된 체크박스들을 모두 가져오기
		var allCheckedCheckboxes = document
				.querySelectorAll('.moduleCheckboxGroup:checked, '
						+ '.moduleCheckboxProduction:checked, '
						+ '.moduleCheckboxBuy:checked, '
						+ '.moduleCheckboxSales:checked, '
						+ '.moduleCheckboxCar:checked, '
						+ '.moduleCheckboxAccount:checked');

		// 체크된 항목이 하나도 없을 경우 경고 메시지 출력
		if (allCheckedCheckboxes.length === 0) {
			alert('장바구니에 담을 제품을 선택해주세요!');
			return false; // 폼 제출 방지
		}

		// 기존에 추가된 숨겨진 input 필드 제거
		while (form.querySelector('input[name="moduleIds"]')) {
			form.removeChild(form.querySelector('input[name="moduleIds"]'));
		}

		// 그룹웨어 체크박스 처리
		var groupCheckboxes = document
				.querySelectorAll('.moduleCheckboxGroup:checked');
		groupCheckboxes.forEach(function(checkbox) {
			var moduleId = checkbox.nextElementSibling.value;
			var input = document.createElement('input');
			input.type = 'hidden';
			input.name = 'moduleIds';
			input.value = moduleId;
			form.appendChild(input);
		});

		// 생산 체크박스 처리
		var productionCheckboxes = document
				.querySelectorAll('.moduleCheckboxProduction:checked');
		productionCheckboxes.forEach(function(checkbox) {
			var moduleId = checkbox.nextElementSibling.value;
			var input = document.createElement('input');
			input.type = 'hidden';
			input.name = 'moduleIds';
			input.value = moduleId;
			form.appendChild(input);
		});

		// 구매 체크박스 처리
		var buyCheckboxes = document
				.querySelectorAll('.moduleCheckboxBuy:checked');
		buyCheckboxes.forEach(function(checkbox) {
			var moduleId = checkbox.nextElementSibling.value;
			var input = document.createElement('input');
			input.type = 'hidden';
			input.name = 'moduleIds';
			input.value = moduleId;
			form.appendChild(input);
		});

		// 영업 체크박스 처리
		var salesCheckboxes = document
				.querySelectorAll('.moduleCheckboxSales:checked');
		salesCheckboxes.forEach(function(checkbox) {
			var moduleId = checkbox.nextElementSibling.value;
			var input = document.createElement('input');
			input.type = 'hidden';
			input.name = 'moduleIds';
			input.value = moduleId;
			form.appendChild(input);
		});

		// 차량 체크박스 처리
		var carCheckboxes = document
				.querySelectorAll('.moduleCheckboxCar:checked');
		carCheckboxes.forEach(function(checkbox) {
			var moduleId = checkbox.nextElementSibling.value;
			var input = document.createElement('input');
			input.type = 'hidden';
			input.name = 'moduleIds';
			input.value = moduleId;
			form.appendChild(input);
		});

		// 회계 체크박스 처리
		var accountCheckboxes = document
				.querySelectorAll('.moduleCheckboxAccount:checked');
		accountCheckboxes.forEach(function(checkbox) {
			var moduleId = checkbox.nextElementSibling.value;
			var input = document.createElement('input');
			input.type = 'hidden';
			input.name = 'moduleIds';
			input.value = moduleId;
			form.appendChild(input);
		});

		// 모달을 열고 form 전송은 잠시 중단
		openCartModal();
		return false;
	}

	function setButtonValue(value) {
		document.getElementById('buttonClicked').value = value;
	}

	function openCartModal() {
		document.getElementById('cart-modal').style.display = 'flex'; // flex로 변경
	}

	function closeCartModal() {
		document.getElementById('cart-modal').style.display = 'none';
		// '계속 쇼핑하기' 버튼을 눌렀을 때도 form을 전송하도록 설정
		var form = document.getElementById('cartForm');
		form.submit();

	}

	function goToCart() {
		// '장바구니 가기' 버튼을 눌렀을 때 form을 전송
		var form = document.getElementById('cartForm');
		form.submit();
	}
</script>


<script type="text/javascript">
	// 그룹웨어
	// 그룹웨어 체크박스 동기화
	function syncCheckboxesGroup(checkbox) {
		var checkboxes = document.getElementsByClassName('moduleCheckboxGroup');
		var isChecked = checkbox.checked;

		// 선택된 체크박스의 값에 따라 나머지 체크박스를 동일하게 설정
		for (var i = 0; i < checkboxes.length; i++) {
			if (checkboxes[i].value === 'AD' || checkboxes[i].value === 'ATD'
					|| checkboxes[i].value === 'EM') {
				checkboxes[i].checked = isChecked;
			}
		}
	}

	// 생산
	function syncCheckboxesProduction(checkbox) {
		var checkboxes = document
				.getElementsByClassName('moduleCheckboxProduction');

		for (var i = 0; i < checkboxes.length; i++) {
			if (checkbox.checked) {
				// P_IN과 P_OUT은 서로 연동되어 체크/해제
				if ((checkbox.value === 'P_IN' && checkboxes[i].value === 'P_OUT')
						|| (checkbox.value === 'P_OUT' && checkboxes[i].value === 'P_IN')) {
					checkboxes[i].checked = true;
				}

				// WO 선택 시 P_IN, P_OUT도 선택
				if (checkbox.value === 'WO') {
					if (checkboxes[i].value === 'P_IN'
							|| checkboxes[i].value === 'P_OUT') {
						checkboxes[i].checked = true;
					}
				}

				// QC 선택 시 P_IN, P_OUT, WO 모두 선택
				if (checkbox.value === 'QC') {
					if (checkboxes[i].value === 'P_IN'
							|| checkboxes[i].value === 'P_OUT'
							|| checkboxes[i].value === 'WO') {
						checkboxes[i].checked = true;
					}
				}
			} else {
				// P_IN과 P_OUT이 해제되면 WO와 QC도 해제
				if ((checkbox.value === 'P_IN' || checkbox.value === 'P_OUT')) {
					for (var j = 0; j < checkboxes.length; j++) {
						if (checkboxes[j].value === 'WO'
								|| checkboxes[j].value === 'QC') {
							checkboxes[j].checked = false;
						}
					}
				}

				// P_IN과 P_OUT은 서로 연동되어 해제
				if ((checkbox.value === 'P_IN' && checkboxes[i].value === 'P_OUT')
						|| (checkbox.value === 'P_OUT' && checkboxes[i].value === 'P_IN')) {
					checkboxes[i].checked = false;
				}

				// WO 해제 시 QC도 해제
				if (checkbox.value === 'WO') {
					for (var j = 0; j < checkboxes.length; j++) {
						if (checkboxes[j].value === 'QC') {
							checkboxes[j].checked = false;
						}
					}
					checkbox.checked = false;
				}

				// QC 해제 시 QC만 해제
				if (checkbox.value === 'QC') {
					checkbox.checked = false;
				}
			}
		}
	}

	// 구매
	function syncCheckboxesBuy(checkbox) {
		var checkboxes = document.getElementsByClassName('moduleCheckboxBuy');

		for (var i = 0; i < checkboxes.length; i++) {
			if (checkbox.checked) {
				// B_IN 선택 시 B_OUT, OF도 선택
				if (checkbox.value === 'B_IN'
						&& (checkboxes[i].value === 'B_OUT' || checkboxes[i].value === 'OF')) {
					checkboxes[i].checked = true;
				}
				// B_OUT 선택 시 B_IN, OF도 선택
				if (checkbox.value === 'B_OUT'
						&& (checkboxes[i].value === 'B_IN' || checkboxes[i].value === 'OF')) {
					checkboxes[i].checked = true;
				}
				// OF 선택 시 B_IN, B_OUT도 선택
				if (checkbox.value === 'OF'
						&& (checkboxes[i].value === 'B_IN' || checkboxes[i].value === 'B_OUT')) {
					checkboxes[i].checked = true;
				}
				// DT 선택 시 B_IN, B_OUT, OF 모두 선택
				if (checkbox.value === 'DT') {
					if (checkboxes[i].value === 'B_IN'
							|| checkboxes[i].value === 'B_OUT'
							|| checkboxes[i].value === 'OF') {
						checkboxes[i].checked = true;
					}
				}
			} else {
				// B_IN, B_OUT, OF가 해제될 때 DT도 해제
				if (checkbox.value === 'B_IN' || checkbox.value === 'B_OUT'
						|| checkbox.value === 'OF') {
					for (var j = 0; j < checkboxes.length; j++) {
						if (checkboxes[j].value === 'DT') {
							checkboxes[j].checked = false;
						}
					}
				}

				// DT는 해제될 때 다른 체크박스에 영향 없음
				if (checkbox.value === 'DT') {
					checkbox.checked = false;
				}

				// B_IN, B_OUT, OF는 서로 연동된 체크/해제
				if ((checkbox.value === 'B_IN' && (checkboxes[i].value === 'B_OUT' || checkboxes[i].value === 'OF'))
						|| (checkbox.value === 'B_OUT' && (checkboxes[i].value === 'B_IN' || checkboxes[i].value === 'OF'))
						|| (checkbox.value === 'OF' && (checkboxes[i].value === 'B_IN' || checkboxes[i].value === 'B_OUT'))) {
					checkboxes[i].checked = false;
				}
			}
		}
	}

	// 영업
	function syncCheckboxesSales(checkbox) {
		var checkboxes = document.getElementsByClassName('moduleCheckboxSales');

		for (var i = 0; i < checkboxes.length; i++) {
			if (checkbox.checked) {
				// S_IN과 S_OUT은 서로 연동되어 체크/해제
				if ((checkbox.value === 'S_IN' && checkboxes[i].value === 'S_OUT')
						|| (checkbox.value === 'S_OUT' && checkboxes[i].value === 'S_IN')) {
					checkboxes[i].checked = true;
				}

				// VM 선택 시 S_IN, S_OUT도 선택
				if (checkbox.value === 'VM') {
					if (checkboxes[i].value === 'S_IN'
							|| checkboxes[i].value === 'S_OUT') {
						checkboxes[i].checked = true;
					}
				}
			} else {
				// S_IN과 S_OUT이 해제될 때 VM도 해제
				if (checkbox.value === 'S_IN' || checkbox.value === 'S_OUT') {
					for (var j = 0; j < checkboxes.length; j++) {
						if (checkboxes[j].value === 'VM') {
							checkboxes[j].checked = false;
						}
					}
				}

				// S_IN과 S_OUT은 서로 연동되어 해제
				if ((checkbox.value === 'S_IN' && checkboxes[i].value === 'S_OUT')
						|| (checkbox.value === 'S_OUT' && checkboxes[i].value === 'S_IN')) {
					checkboxes[i].checked = false;
				}

				// VM 해제 시 VM만 해제
				if (checkbox.value === 'VM') {
					checkbox.checked = false;
				}
			}
		}
	}

	// 차량
	function syncCheckboxesCar(checkbox) {
		var checkboxes = document.getElementsByClassName('moduleCheckboxCar');

		for (var i = 0; i < checkboxes.length; i++) {
			if (checkbox.checked) {
				// C_I와 C_R은 서로 연동되어 체크/해제
				if ((checkbox.value === 'C_I' && checkboxes[i].value === 'C_R')
						|| (checkbox.value === 'C_R' && checkboxes[i].value === 'C_I')) {
					checkboxes[i].checked = true;
				}

				// C_P 선택 시 C_I와 C_R도 선택
				if (checkbox.value === 'C_P') {
					if (checkboxes[i].value === 'C_I'
							|| checkboxes[i].value === 'C_R') {
						checkboxes[i].checked = true;
					}
				}
			} else {
				// C_I 또는 C_R 해제 시 C_P도 해제
				if (checkbox.value === 'C_I' || checkbox.value === 'C_R') {
					for (var j = 0; j < checkboxes.length; j++) {
						if (checkboxes[j].value === 'C_P') {
							checkboxes[j].checked = false;
						}
					}
				}

				// C_I와 C_R은 서로 연동되어 해제
				if ((checkbox.value === 'C_I' && checkboxes[i].value === 'C_R')
						|| (checkbox.value === 'C_R' && checkboxes[i].value === 'C_I')) {
					checkboxes[i].checked = false;
				}

				// C_P 해제 시 C_P만 해제
				if (checkbox.value === 'C_P') {
					checkbox.checked = false;
				}
			}
		}
	}

	// 회계
	function syncCheckboxesAccount(checkbox) {
		var checkboxes = document
				.getElementsByClassName('moduleCheckboxAccount');

		for (var i = 0; i < checkboxes.length; i++) {
			if (checkbox.checked) {
				// CM 선택 시 BAM도 선택
				if (checkbox.value === 'CM' && checkboxes[i].value === 'BAM') {
					checkboxes[i].checked = true;
				}
			} else {
				// CM 해제 시 CM만 해제, BAM은 그대로 유지
				if (checkbox.value === 'CM') {
					checkbox.checked = false;
				}
			}
		}
	}
</script>


</html>

