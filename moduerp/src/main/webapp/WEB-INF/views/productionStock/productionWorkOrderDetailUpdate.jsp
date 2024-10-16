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
			<li><a href="productionWorkorder.do"><i
					class="fa-solid fa-paste"></i>작업지시서</a></li>
			<li><a href="productionQuality.do"><i
					class="fa-solid fa-bars-progress"></i>품질관리</a></li>
		</ul>
	</div>

	<!-- 하얀 큰 박스 -->
	<div class="content-box">

		<div class="content-title">생산관리 | 작업지시서 | 수정</div>

		<form action="/moduerp/updateProductionWorkOrderUpdateDo.do"
			method="POST">
			<input type="hidden" name="orderNumber"
				value="${workOrderDetails.orderNumber}">
			<!-- 테이블 -->
			<table>
				<thead>
					<tr>
						<th>제품명</th>
						<th>작업명</th>
						<th>시작 날짜</th>
						<th>종료 예정 날짜</th>
						<th>작업 수량</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${workOrderDetails.itemName}</td>
						<td><input type="text" name="taskName"
							value="${workOrderDetails.taskName}" required /></td>
						<td><input type="date" name="startDate"
							value="<fmt:formatDate value='${workOrderDetails.startDate}' pattern='yyyy-MM-dd' />"
							required /></td>
						<td><input type="date" name="endExDate"
							value="<fmt:formatDate value='${workOrderDetails.endExDate}' pattern='yyyy-MM-dd' />"
							required /></td>

						<td><input type="number" name="qty"
							value="${workOrderDetails.qty}" required /></td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th>진행 상태</th>
						<th>작업팀</th>
						<th>작업자</th>
						<th>작업 장소</th>
						<th>지시자</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><select name="progressStatus">
								<option value="작업 전"
									${workOrderDetails.progressStatus == '작업 전' ? 'selected' : ''}>작업
									전</option>
								<option value="작업 중"
									${workOrderDetails.progressStatus == '작업 중' ? 'selected' : ''}>작업
									중</option>
								<option value="작업 완료"
									${workOrderDetails.progressStatus == '작업 완료' ? 'selected' : ''}>작업
									완료</option>
						</select></td>
						<!-- 작업팀을 선택하는 input 필드 -->
						<td><input list="workerTeamsList" name="workerTeam"
							value="${workOrderDetails.workerTeam}" placeholder="작업팀 선택"
							required /> <datalist id="workerTeamsList">
								<!-- workerTeams 리스트에서 값 불러오기 -->
								<c:forEach var="team" items="${workerTeams}">
									<option value="${team}"></option>
								</c:forEach>
							</datalist></td>

						<td>
							<div id="workerContainer">
								<!-- worker 값을 쉼표로 분리해서 각각의 input 필드로 출력 -->
								<c:forEach var="worker"
									items="${fn:split(workOrderDetails.worker, ',')}">
									<div class="worker-input">
										<!-- 기존 값을 가진 input 필드와 datalist -->
										<input list="employeeList" name="worker" value="${worker}"
											placeholder="작업자 입력" required />
										<datalist id="employeeNamesList">
											<!-- employeeNames 리스트에서 값 불러오기 -->
											<c:forEach var="employee" items="${employeeNames}">
												<option value="${employee}"></option>
											</c:forEach>
										</datalist>
										<button type="button" class="remove-btn"
											onclick="removeWorker(this)">삭제</button>
									</div>
								</c:forEach>
							</div> <!-- 작업자 추가 버튼 -->
							<button type="button" onclick="addWorker()">작업자 추가</button>
						</td>
						<!-- 작업 장소를 선택하는 input 필드 -->
						<td><input list="workPlacesList" name="workPlace"
							value="${workOrderDetails.workPlace}" placeholder="작업 장소 선택"
							required /> <datalist id="workPlacesList">
								<!-- workPlaces 리스트에서 값 불러오기 -->
								<c:forEach var="place" items="${workPlaces}">
									<option value="${place}"></option>
								</c:forEach>
							</datalist></td>

						<td>${workOrderDetails.wDirector}</td>
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
