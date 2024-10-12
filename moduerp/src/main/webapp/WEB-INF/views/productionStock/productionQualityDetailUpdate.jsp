<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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

/* Modal Styles */
#delete-modal {
	display: none; /* 초기에는 보이지 않도록 설정 */
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%; /* 전체 화면 너비 */
	height: 100%; /* 전체 화면 높이 */
	background-color: rgba(0, 0, 0, 0.5); /* 배경 반투명 */
	display: flex; /* 플렉스 박스를 사용하여 중앙 정렬 */
}

.modal-content {
	background-color: #fff;
	padding: 20px;
	border-radius: 5px;
	text-align: center;
	width: 300px; /* 원하는 너비 */
	position: relative;
	margin: auto; /* 중앙 정렬을 위한 마진 */
	margin-top: 20%;
}

.modal-content h2 {
	margin-bottom: 20px;
}

.modal-content button {
	padding: 10px 20px;
	margin: 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.modal-content .go-delete {
	background-color: red;
	color: #fff;
}

.modal-content .stay-page {
	background-color: gray;
	color: #fff;
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

		<div class="content-title">생산관리 | 품질관리 |</div>

		<form action="/moduerp/updateProductionQulityUpdateDo.do"
			method="POST">
			<input type="hidden" name="inspecCode"
				value="${qualityControl.inspecCode}">
				 <input type="hidden"
				name="orderNumber" value="${workOrderDetails.orderNumber}">
			<!-- 테이블 -->
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

						<td>${qualityControl.taskName}</td>

						<td><input type="date" name="startDate"
							value="<fmt:formatDate value='${qualityControl.startDate}' pattern='yyyy-MM-dd' />" /></td>
						<td><input type="date" name="endExDate"
							value="<fmt:formatDate value='${qualityControl.endExDate}' pattern='yyyy-MM-dd' />" /></td>
						<td><select name="inspecType">
								<option value="외관 검사"
									${qualityControl.inspecType == '외관 검사' ? 'selected' : ''}>외관
									검사</option>
								<option value="기능 검사"
									${qualityControl.inspecType == '기능 검사' ? 'selected' : ''}>기능
									검사</option>

						</select></td>

						<td><select name="progressStatus">
								<option value="검사 전"
									${qualityControl.progressStatus == '검사 전' ? 'selected' : ''}>검사
									전</option>
								<option value="검사 중"
									${qualityControl.progressStatus == '검사 중' ? 'selected' : ''}>검사
									중</option>
								<option value="검사 완료"
									${qualityControl.progressStatus == '검사 완료' ? 'selected' : ''}>검사
									완료</option>
						</select></td>
						<td><input type="number" name="inspecQty"
							value="${qualityControl.inspecQty}" /></td>

						<td>
							<div id="1DirectorContainer">
								<c:forEach var="qDirector"
									items="${fn:split(qualityControl.qDirector, ', ') }">
									<div class="qDirector-input">
										<input list="qDirectorList" name="qDirector"
											value="${qDirector}" placeholder="검사자 입력" required>
										<datalist id="employeeNameList">
											<!-- employeeNames 리스트에서 값 불러오기 -->
											<c:forEach var="employee" items="${employeeNames}">
												<option value="${employee}"></option>
											</c:forEach>
										</datalist>
										<button type="button" class="remove-btn"
											onclick="removeWorker(this)">삭제</button>
									</div>
								</c:forEach>
							</div>
							<button type="button" onclick="addqDirector()">작업자 추가</button>
						</td>
					</tr>
				</tbody>


			</table>

			<!-- 버튼 그룹 -->
			<div class="btn-group">
				<button type="submit" class="btn green">수정 완료</button>
			</div>
		</form>


	</div>


</body>
<script type="text/javascript">
function addqDirector() {
    const container = document.getElementById('1DirectorContainer');
    const newInputDiv = document.createElement('div');
    newInputDiv.className = 'qDirector-input';
    newInputDiv.innerHTML = `
        <input list="employeeNamesList" name="qDirector" placeholder="검사자 입력" required />
        <datalist id="employeeNamesList">
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
function addWorker() {
    const container = document.getElementById('workerContainer');
    const newInputDiv = document.createElement('div');
    newInputDiv.className = 'worker-input';
    newInputDiv.innerHTML = `
        <input list="employeeNamesList" name="worker" placeholder="작업자 입력" required />
        <datalist id="employeeNamesList">
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
