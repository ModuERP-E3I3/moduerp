<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>erpMain</title>

<style type="text/css">
.top-content-box {
	width: 96%; /* 화면에 가득 차지 않게 */
	height: 6vh;
	background-color: white;
	margin-left: 1%;
	margin-right: 5%;
	margin-top: 1.8%;
	border: 1px solid #ccc;
	border-radius: 20px; /* 박스 둥글게 */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
	display: flex;
	justify-content: center; /* 수평 중앙 정렬 */
	align-items: center; /* 수직 중앙 정렬 */
	padding: 20px;
	font-size: 30px;
	font-weight: bold;
}
/* ul의 기본 스타일 제거 */
#menubar {
	list-style: none; /* 기본 list-style 없애기 */
	padding: 0;
	margin: 0;
	display: flex; /* li를 가로로 배치하기 위해 flexbox 사용 */
	justify-content: center;
	align-items: center;
}

#menubar li {
	margin: 0 40px; /* li 간의 간격 추가 */
}

#menubar li a {
	color: black;
	text-decoration: none;
	font-size: 16px;
	display: block;
	padding: 10px 20px;
	transition: background 0.3s ease;
}

#menubar li a:hover {
	background-color: #f4f4f4;
	border-radius: 10px; /* 호버 시 살짝 둥근 배경 */
}

/* 하얀 박스 스타일 */
.content-box {
	width: 96%; /* 화면에 가득 차지 않게 */
	height: 70vh; /* 화면 높이의 78% */
	background-color: white;
	margin-left: 1%;
	margin-right: 5%;
	margin-top: 3%;
	border: 1px solid #ccc;
	border-radius: 20px; /* 박스 둥글게 */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
	position: relative;
	padding: 20px; /* 내부 여백 추가 */
}

/* 제목 스타일 */
.content-title {
	position: absolute;
	top: -40px;
	left: 20px;
	font-size: 24px;
	color: white;
	font-weight: bold;
}

/* 테이블 스타일 */
table {
	width: 100%;
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

.btn.red {
	background-color: red;
	color: white;
}

.btn.green {
	background-color: green;
	color: white;
}

.btn.blue {
	background-color: blue;
	color: white;
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

.top-content-box {
	background-color: white;
}
</style>

</head>

<body>
	<!-- 서브헤더 JSP 임포트 -->
	<c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

	<!-- 위에 하얀 박스  -->
	<div class="top-content-box">
		<ul id="menubar">
			<li><a href="productionStockIn.do"><i
					class="fa-solid fa-store"></i> 생산 입고</a></li>
			<li><a href="productionStockOut.do"><i
					class="fa-solid fa-store-slash"></i> 생산 출고</a></li>
			<li><a href="productionWorkorder.do"><i class="fa-solid fa-paste"></i>작업지시서</a></li>
			<li><a href="productionQuality.do"><i class="fa-solid fa-bars-progress"></i>품질관리</a></li>
		</ul>
	</div>

	<!-- 하얀 큰 박스 -->
	<div class="content-box">

		<div class="content-title">생산관리 | 품질관리 | 신규 등록</div>

		<!-- 테이블 -->
		<form action="/moduerp/productionQualityInsert.do" method="POST">
			<table>
				<thead>
					<tr>
						<th>검사 항목 제품명</th>
						<th>시작 날짜</th>
						<th>종료 예정 날짜</th>
						<th>검사 유형</th>
						<th>진행 상태</th>
						<th>검사 수량</th>
						<th>검사자</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input list="workOrderNames" name="workOrderName"
							id="workOrderNameInput" placeholder="제품명 선택" required
							onchange="updateOrderNumber()" /> <datalist id="workOrderNames">
								<option value="==========">==========</option>

								<!-- 'completedWorkOrders' 리스트에서 데이터 가져오기 -->
								<c:forEach var="workOrder" items="${completedWorkOrders}">
									<c:set var="availableQty"
										value="${workOrder.qty - inspecQtyMap[workOrder.orderNumber]}" />
									<c:if test="${availableQty > 0}">
										<option value="${workOrder.taskName} | 작업수량 : ${availableQty}"
											data-order-number="${workOrder.orderNumber}"
											data-available-qty="${availableQty}"></option>
									</c:if>
								</c:forEach>

								<option value="==========">==========</option>
							</datalist> <!-- 선택한 orderNumber를 담을 숨겨진 입력 필드 --> <input type="hidden"
							name="orderNumber" id="orderNumberInput" value="" /></td>
						<td><input type="date" name="qcStartDate" required></td>
						<td><input type="date" name="qcEndExDate" required></td>
						<td><select name="inspectionType" required>
								<option value="" disabled selected hidden>검사 유형 선택</option>
								<option value="외관 검사">외관 검사</option>
								<option value="기능 검사">기능 검사</option>
						</select></td>
						<td><select name="progressStatus" required>
								<option value="" disabled selected hidden>진행 상태 선택</option>
								<option value="검사 전">검사 전</option>
								<option value="검사 중">검사 중</option>
								<option value="검사 완료">검사 완료</option>
						</select></td>

						<td><input type="number" name="qcQty" id="qcQty" required /></td>
						<!-- 작업자를 선택하는 input 필드 -->
						<td>
							<!-- 작업자 선택 부분 -->
							<div id="qDirectorContainer">
								<div class="worker-input">
									<input list="employeeNamesList" name="worker"
										placeholder="작업자 선택" required />
									<datalist id="employeeNamesList">
										<!-- employeeNames 모델에 담긴 리스트를 반복하여 datalist에 출력 -->
										<c:forEach var="employee" items="${employeeNames}">
											<option value="${employee}"></option>
										</c:forEach>
									</datalist>
									<button type="button" class="remove-btn"
										onclick="removeWorker(this)">삭제</button>
								</div>
							</div>
							<button type="button" onclick="addWorker()">작업자 추가</button>
						</td>
					</tr>
				</tbody>
			</table>

			<!-- 버튼 그룹 -->
			<div class="btn-group">
				<button type="submit" class="btn blue">등록 완료</button>
			</div>
		</form>


	</div>
</body>


<script type="text/javascript">
	function addWorker() {
	    const container = document.getElementById('qDirectorContainer');
	    const newInputDiv = document.createElement('div');
	    newInputDiv.className = 'worker-input';
	    newInputDiv.innerHTML = `
	        <input list="employeeNamesList" name="worker" placeholder="작업자 선택" required />
	        <datalist id="employeeNamesList">
	            <!-- 이 부분은 서버에서 제공된 employeeNames 데이터를 사용 -->
	            <c:forEach var="employee" items="${employeeNames}">
	                <option value="${employee}"></option>
	            </c:forEach>
	        </datalist>
	        <button type="button" class="remove-btn" onclick="removeWorker(this)">삭제</button>
	    `;
	    container.appendChild(newInputDiv);
	}
	
	function removeWorker(button) {
	    const inputDiv = button.parentElement;
	    inputDiv.remove();
	}

</script>


<script type="text/javascript">
	document.getElementById('qcQty').addEventListener('input', function() {
	    // 선택된 workOrderNameInput의 값을 가져옵니다.
	    var selectedValue = document.getElementById('workOrderNameInput').value;
	    var datalist = document.getElementById('workOrderNames');
	    var options = datalist.getElementsByTagName('option');
	    var selectedQty = 0; // 기본값을 설정합니다.
	
	    // 옵션을 순회하여 선택한 값과 일치하는지 확인합니다.
	    for (var i = 0; i < options.length; i++) {
	        if (options[i].value === selectedValue) {
	            // 일치하는 옵션의 data-task-qty 속성에서 작업 수량을 가져옵니다.
	            selectedQty = parseInt(options[i].getAttribute('data-available-qty'), 10);
	            break; // 일치하는 값을 찾았으면 반복 종료
	        }
	    }
	
	    // 입력된 검사 수량을 가져옵니다.
	    var enteredQty = parseInt(this.value, 10);
	
	    // 입력된 값이 작업 수량을 초과하는지 확인
	    if (enteredQty > selectedQty) {
	        alert("입력한 검사 수량이 작업 수량을 초과했습니다.");
	        this.value = ''; // 입력 필드 비우기
	    }
	});

</script>


<script>
    document.getElementById('workOrderNameInput').addEventListener('input', function() {
        // "=========="가 선택된 경우 값을 비워서 선택되지 않게 함
        if (this.value === '==========') {
            this.value = ''; // 선택을 방지하기 위해 값을 비움
        }
    });
</script>

<script>
    function updateOrderNumber() {
        var input = document.getElementById('workOrderNameInput');
        var datalist = document.getElementById('workOrderNames');
        var options = datalist.getElementsByTagName('option');
        var valid = false;

        // 사용자가 선택한 값과 일치하는 값이 있는지 확인
        for (var i = 0; i < options.length; i++) {
            if (options[i].value === input.value) {
                valid = true;
                break;
            }
        }

        // 유효하지 않은 값이면 경고창 띄우고 입력 필드 비우기
        if (!valid) {
            alert("유효한 항목을 선택하세요.");
            input.value = ''; // 입력된 값 제거
        }
    }
    

    // 사용자가 포커스를 벗어날 때 updateItemCode 함수 실행
    document.getElementById('workOrderNameInput').addEventListener('blur', updateOrderNumber);
</script>

<script>
	function updateOrderNumber() {
	    // workOrderNameInput에서 선택한 옵션
	    var input = document.getElementById('workOrderNameInput');
	    // 사용자가 선택한 값을 가져옵니다.
	    var selectedValue = input.value;
	
	    // datalist에서 모든 옵션을 가져옵니다.
	    var datalist = document.getElementById('workOrderNames');
	    var options = datalist.getElementsByTagName('option');
	
	    var orderNumber = ''; // 기본값을 설정합니다.
	
	    // 옵션을 순회하여 선택한 값과 일치하는지 확인합니다.
	    for (var i = 0; i < options.length; i++) {
	        if (options[i].value === selectedValue) {
	            // 일치하는 경우 data-item-code 속성에서 itemCode를 가져옵니다.
	            orderNumber = options[i].getAttribute('data-order-number');
	            break; // 일치하는 값을 찾았으면 반복 종료
	        }
	    }
	
	    // 숨겨진 입력 필드에 orderNumber 설정
	    document.getElementById('orderNumberInput').value = orderNumber;
	}
</script>

<script>
	function updateOrderNumber() {
	    var input = document.getElementById('workOrderNameInput');
	    var selectedValue = input.value;
	
	    // datalist의 모든 옵션을 확인하여 일치하는 옵션의 orderNumber를 가져옴
	    var options = document.querySelectorAll('#workOrderNames option');
	    var orderNumber = ''; // 기본값
	
	    options.forEach(function(option) {
	        if (option.value === selectedValue) {
	            orderNumber = option.getAttribute('data-order-number');
	        }
	    });
	
	    // 숨겨진 입력 필드에 orderNumber 설정
	    document.getElementById('orderNumberInput').value = orderNumber;
	
	    // qcQty 값을 비움 (옵션이 변경되었을 때)
	    document.getElementById('qcQty').value = ''; 
	}
</script>

<script>
    const activeMenu = "productionStockIn";

    document.addEventListener('DOMContentLoaded', function() {
        const menuItems = document.querySelectorAll('nav.side ul li a');
        menuItems.forEach(item => {
            if (item.href.includes(activeMenu)) {
                item.classList.add('active');
            }
        });
    });
</script>


</html>
