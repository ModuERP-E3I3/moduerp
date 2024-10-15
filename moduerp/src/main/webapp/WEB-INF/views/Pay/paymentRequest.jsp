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
	display: flex; /* Flexbox 적용 */
	flex-direction: row; /* 가로 방향 정렬 */
	justify-content: space-between; /* 양 끝에 요소 배치 */
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
	margin-left: 1%;
}

.btn_pay {
	background-color: #4CAF50;
	color: white;
	padding: 5px 12px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 20px;
}

.left-group {
	text-align: left;
	margin-left: 3%;
}

.right-group {
	margin-top: 30%;
	text-align: right;
	margin-right: 1.5%;
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

#totalPrice {
	margin-bottom: 10px;
	font-weight: bold;
	font-size: 20px;
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
												value="${module.moduleGrade}"> <input type="hidden"
												name="moduleIds" value="${module.moduleId}"></td>
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
												value="${module.moduleGrade}"> <input type="hidden"
												name="moduleIds" value="${module.moduleId}"></td>

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
												value="${module.moduleGrade}"> <input type="hidden"
												name="moduleIds" value="${module.moduleId}"></td>

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
												value="${module.moduleGrade}"> <input type="hidden"
												name="moduleIds" value="${module.moduleId}"></td>

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
												value="${module.moduleGrade}"> <input type="hidden"
												name="moduleIds" value="${module.moduleId}"></td>

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
												value="${module.moduleGrade}"> <input type="hidden"
												name="moduleIds" value="${module.moduleId}"></td>

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


						</tbody>

					</table>

				</div>


			</div>
			<div class="right-group">
				<div id="totalPrice">
					총 금액<br>${totalModulePrice}원
				</div>
				<button type="button" class="btn_pay" onclick="">결제하기</button>
			</div>


		</div>


	</form>



	<!-- 메뉴바 임포트 -->
	<c:import url="/WEB-INF/views/common/menubar.jsp" />



	<!-- 푸터 임포트 -->
	<c:import url="/WEB-INF/views/common/footer.jsp" />

</body>

<script type="text/javascript">
function submitPayment() {
    const form = document.getElementById('cartForm');
    
    form.action = 'createPayment.do';
    form.method = 'post';
    form.submit();
}

</script>

<script>
  function handleNoCard() {
    // 알림창 표시
    alert("등록된 카드가 없습니다.");
    // 특정 페이지로 이동
    window.location.href = "http://localhost:8080/moduerp/payment.do"; 
  }
</script>

<script type="text/javascript">
// GroupWare
document.addEventListener('DOMContentLoaded', () => {
    const checkboxes = document.querySelectorAll('input[name="selectedModules"]');
 // ATD와 AD 체크박스는 비활성화
    disableCheckbox(['ATD', 'AD', 'EM']);
 
    function disableCheckbox(values) {
        values.forEach(value => {
            const checkbox = Array.from(checkboxes).find(cb => cb.value === value);
            if (checkbox) {
                checkbox.disabled = true; // 비활성화
            }
        });
    }
    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', (event) => {
            const selectedValue = event.target.value;
            const isChecked = event.target.checked;

            if (selectedValue === 'ATD') {
                // ATD 체크할 때 AD와 EM도 같이 체크
                toggleCheckbox(['AD', 'EM'], isChecked);
            } else if (selectedValue === 'AD') {
                // AD 체크할 때 ATD와 EM도 같이 체크
                toggleCheckbox(['ATD', 'EM'], isChecked);
            } else if (selectedValue === 'EM') {
                // EM은 독립적으로 체크되고 해제됨
                // 특별한 로직 필요 없음 (기본 동작)
            }
        });
    });

    function toggleCheckbox(values, isChecked) {
        values.forEach(value => {
            const relatedCheckbox = Array.from(checkboxes).find(cb => cb.value === value);
            if (relatedCheckbox) {
                relatedCheckbox.checked = isChecked;
            }
        });
    }
});

// Production
document.addEventListener('DOMContentLoaded', () => {
    const checkboxes = document.querySelectorAll('input[name="selectedModules"]');

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', (event) => {
            const selectedValue = event.target.value;
            const isChecked = event.target.checked;

            switch (selectedValue) {
                case 'P_IN':
                    toggleCheckbox(['P_OUT', 'WO', 'QC'], isChecked);
                    break;
                case 'P_OUT':
                    toggleCheckbox(['P_IN', 'WO', 'QC'], isChecked);
                    break;
                case 'WO':
                    toggleCheckbox(['QC'], isChecked);
                    break;
                case 'QC':
                    // QC는 독립적으로 동작합니다. 특별한 로직 필요 없음.
                    break;
            }
        });
    });

    function toggleCheckbox(values, isChecked) {
        values.forEach(value => {
            const relatedCheckbox = Array.from(checkboxes).find(cb => cb.value === value);
            if (relatedCheckbox) {
                relatedCheckbox.checked = isChecked;
            }
        });
    }
});

// Buy
document.addEventListener('DOMContentLoaded', () => {
    const checkboxes = document.querySelectorAll('input[name="selectedModules"]');

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', (event) => {
            const selectedValue = event.target.value;
            const isChecked = event.target.checked;

            switch (selectedValue) {
                case 'B_IN':
                    toggleCheckbox(['B_OUT', 'OF', 'DT'], isChecked);
                    break;
                case 'B_OUT':
                    toggleCheckbox(['B_IN', 'OF', 'DT'], isChecked);
                    break;
                case 'OF':
                    toggleCheckbox(['B_IN', 'B_OUT', 'DT'], isChecked);
                    break;
                case 'DT':
                    // DT는 혼자 선택 및 해제됩니다. 특별한 로직 필요 없음.
                    break;
            }
        });
    });

    function toggleCheckbox(values, isChecked) {
        values.forEach(value => {
            const relatedCheckbox = Array.from(checkboxes).find(cb => cb.value === value);
            if (relatedCheckbox) {
                relatedCheckbox.checked = isChecked;
            }
        });
    }
});


// Salse
document.addEventListener('DOMContentLoaded', () => {
    const checkboxes = document.querySelectorAll('input[name="selectedModules"]');

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', (event) => {
            const selectedValue = event.target.value;
            const isChecked = event.target.checked;

            switch (selectedValue) {
                case 'S_IN':
                    toggleCheckbox(['S_OUT', 'VM'], isChecked);
                    break;
                case 'S_OUT':
                    toggleCheckbox(['S_IN', 'VM'], isChecked);
                    break;
                case 'VM':
                    // VM은 독립적으로 선택 및 해제됩니다. 별도의 로직이 필요 없습니다.
                    break;
            }
        });
    });

    function toggleCheckbox(values, isChecked) {
        values.forEach(value => {
            const relatedCheckbox = Array.from(checkboxes).find(cb => cb.value === value);
            if (relatedCheckbox) {
                relatedCheckbox.checked = isChecked;
            }
        });
    }
});


//Car
document.addEventListener('DOMContentLoaded', () => {
    const checkboxes = document.querySelectorAll('input[name="selectedModules"]');

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', (event) => {
            const selectedValue = event.target.value;
            const isChecked = event.target.checked;

            switch (selectedValue) {
                case 'C_I':
                    toggleCheckbox(['C_R', 'C_P'], isChecked);
                    break;
                case 'C_R':
                    toggleCheckbox(['C_I', 'C_P'], isChecked);
                    break;
                case 'C_P':
                case 'RTRL':
                    // C_P와 RTRL은 독립적으로 선택 및 해제됩니다. 별도의 로직 필요 없음.
                    break;
            }
        });
    });

    function toggleCheckbox(values, isChecked) {
        values.forEach(value => {
            const relatedCheckbox = Array.from(checkboxes).find(cb => cb.value === value);
            if (relatedCheckbox) {
                relatedCheckbox.checked = isChecked;
            }
        });
    }
});


//Account

document.addEventListener('DOMContentLoaded', () => {
    const checkboxes = document.querySelectorAll('input[name="selectedModules"]');

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', (event) => {
            const selectedValue = event.target.value;
            const isChecked = event.target.checked;

            switch (selectedValue) {
                case 'BAM':
                    toggleCheckbox(['CM'], isChecked);
                    break;
                case 'CM':
                    // CM은 독립적으로 동작합니다. 별도의 로직 필요 없음.
                    break;
            }
        });
    });

    function toggleCheckbox(values, isChecked) {
        values.forEach(value => {
            const relatedCheckbox = Array.from(checkboxes).find(cb => cb.value === value);
            if (relatedCheckbox) {
                relatedCheckbox.checked = isChecked;
            }
        });
    }
});

</script>

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

