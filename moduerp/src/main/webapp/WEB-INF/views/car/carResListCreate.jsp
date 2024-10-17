<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


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

#pagebutton {
	display: flex;
	justify-content: center;
	margin-top: 2%; /* 위쪽 여백 추가 */
}

#pagebutton a {
	color: black; /* 글자 색상 검은색 */
	text-decoration: none; /* 밑줄 제거 */
	font-size: 20px; /* 글자 크기 증가 */
	margin: 0 10px; /* 페이지 버튼 간격 조정 */
}

#pagebutton strong {
	font-size: 20px; /* 현재 페이지 강조 글자 크기 증가 */
	color: black; /* 강조 색상 검은색 유지 */
}

tbody tr:hover {
	cursor: pointer;
}
</style>

</head>

<body>
	<!-- 서브헤더 JSP 임포트 -->
	<c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

	<!-- 위에 하얀 박스  -->
	<div class="top-content-box">
		<ul id="menubar">
			<li><a href="carRes.do"><i class="fa-solid fa-car-side"></i>
					차량 예약</a></li>
			<li><a href="carMgt.do"><i class="fa-solid fa-list-check"></i>
					차량 결제 관리</a></li>
			<li><a href="map.do"><i class="fa-solid fa-signs-post"></i>
					도로 교통 / 경로 조회</a></li>
		</ul>
	</div>

	<!-- 하얀 큰 박스 -->
	<div class="content-box">

		<div class="content-title">차량관리 | 차량 예약 | 차량 예약 등록 및 예약 내역</div>

		<c:if test="${not empty alertMessage}">
    		<script>
       		 alert('${alertMessage}');
    		</script>
		</c:if>

		<!-- 테이블 -->
		<!-- 테이블 -->
		<form action="/moduerp/insertCarres.do" method="POST">
			<table>
				<thead>
					<tr>
						<th>차종</th>
						<th>차량 번호</th>
						<th>사원명</th>
						<th>부서명</th>
						<th>예약 일정</th>
						<th>예약 사유</th>
						<th>운행 여부</th>
					</tr>
				</thead>
				<tbody>
					<tr>


						<!-- 차종 입력 칸 -->
						<td><input list="carModels" name="carModel"
							id="carModelInput" placeholder="차종 입력" oninput="updateCarNum()"
							required /> <datalist id="carModels">
								<c:forEach var="cars" items="${cars}">
									<option value="${cars.carModel} | ${cars.carNum}" />
								</c:forEach>
							</datalist></td>
						<!-- 차량 번호 입력 칸 -->
						<td><input type="text" id="carNumInput" name="carNum"
							placeholder="차량 번호 입력" readonly="readonly" required /></td>
						<!-- 사원 이름 입력 칸 -->
						<td><input list="empNames" name="empName" id="empNameInput"
							placeholder="사원명" oninput="updateDepartmentId()" required /> <datalist
								id="empNames">
								<c:forEach var="empNameDepart" items="${empNameDepart}">
									<option
										value="${empNameDepart.empName} | ${empNameDepart.departmentId}" />
								</c:forEach>
							</datalist></td>
						<!-- 부서명 입력 칸 -->
						<td><input type="text" id="departmentIdInput"
							name="departmentId" placeholder="부서명" readonly="readonly"
							required /> <datalist id="departmentIds">
								<c:forEach var="departmentId" items="${departmentIds}">
									<option value="${departmentId}" />
								</c:forEach>
							</datalist></td>
						<td><input type="datetime-local" id="reserveStartDate"
							name="reserveStartDate" required /> ~ <input
							type="datetime-local" id="reserveEndDate" name="reserveEndDate"
							required /></td>
						<td><input type="text" name="useReason"
							placeholder="예약 사유 입력" required /></td>
						<td><input list="drivingStatus" name="drivingStatus"
							id="drivingStatusInput" placeholder="운행 상태 입력" required /> <datalist
								id="drivingStatus">
								<option value="운행 전">운행 전</option>
								<option value="운행 중">운행 중</option>
								<option value="운행 완료">운행 완료</option>
							</datalist></td>


					</tr>
				</tbody>
			</table>



			<!-- 버튼 그룹 -->
			<div class="btn-group">
				<button type="submit" class="btn blue">등록 완료</button>
			</div>
		</form>


		
		<!-- 테이블 -->
		<h3>차량 예약 내역</h3>
		<!-- 필터 박스 -->
		<form action="/moduerp/carresFilter.do">
			<div class="filter-box">
				<select name="filterOption" id="filterOption">
					<option disabled selected>옵션 선택</option>
					<option value="carModel">차종</option>
					<option value="carNum">차량 번호</option>
					<option value="empName">사원명</option>
					<option value="departmentId">부서명</option>
					<option value="drivingStatus">운행 여부</option>
				</select> <input type="date" name="startDate" id="startDate" required/> <input
					type="date" name="endDate" id="endDate" required/> <input type="text"
					name="filterText" id="filterText" placeholder="내용 입력" required/>
				<button type="submit" class="btn">조회</button>
				<button type="button" class="btn"
					onclick="window.location.href='carresListCreate.do';">초기화</button>
			</div>
		</form>
		<table>
			<thead>
				<tr>
					<th>차종</th>
					<th>차량 번호</th>
					<th>사원명</th>
					<th>부서명</th>
					<th>예약 일정</th>
					<th>예약 사유</th>
					<th>운행 여부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="carres" items="${carresList}">
					<tr
						onclick="window.location.href='getCarresDetail.do?carReserveCode=${ carres.carReserveCode }' ">
						<td>${ carres.carModel }</td>
						<td>${ carres.carNum }</td>
						<td>${ carres.empName }</td>
						<td>${ carres.departmentId }</td>
						<td><fmt:formatDate value="${carres.reserveStartDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /> ~ <fmt:formatDate
								value="${carres.reserveEndDate}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>${ carres.useReason }</td>
						<td>${ carres.drivingStatus }</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

		<!-- 페이지 버튼 -->
		<div id="pagebutton">
			<c:if test="${totalPages > 1}">
				<c:forEach var="i" begin="1" end="${totalPages}">
					<c:choose>
						<c:when test="${i == currentPage}">
							<strong>${i}</strong>
							<!-- 현재 페이지는 강조 -->
						</c:when>
						<c:otherwise>
							<a href="carresListCreate.do?page=${i}">${i}</a>
							<!-- 페이지 링크 -->
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
		</div>

	</div>
	
</body>

<script>
function updateDepartmentId() {
    const empNameInput = document.getElementById('empNameInput');
    const departmentIdInput = document.getElementById('departmentIdInput');
    const selectedValue = empNameInput.value;

    // 선택된 값을 "|"로 분리
    const parts = selectedValue.split(' | ');

    if (parts.length === 2) {
        // 첫 번째 부분은 empName, 두 번째 부분은 departmentId
        empNameInput.value = parts[0]; // 사원명
        departmentIdInput.value = parts[1]; // 부서명
    } else {
        // 부서명 입력란 초기화
        departmentIdInput.value = '';
    }
}
</script>

<script>
function updateCarNum() {
    const carModelInput = document.getElementById('carModelInput');
    const carNumInput = document.getElementById('carNumInput');
    const selectedValue = carModelInput.value;

    // 선택된 값을 "|"로 분리
    const parts = selectedValue.split(' | ');

    if (parts.length === 2) {
        // 첫 번째 부분은 carModel, 두 번째 부분은 carNum
        carModelInput.value = parts[0]; // 차량 모델
        carNumInput.value = parts[1]; // 차량 번호
    } else {
        // 두 입력란을 초기화
        carNumInput.value = '';
    }
}
</script>

<script>
    const activeMenu = "carRes";

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
