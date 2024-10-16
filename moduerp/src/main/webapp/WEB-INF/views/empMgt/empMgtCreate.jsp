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
			<li><a href="account.do"><i class="fas fa-bullhorn"></i>
					거래처관리</a></li>
			<li><a href="salesStockIn.do"><i class="fas fa-clipboard"></i>
					영업 입고</a></li>
			<!-- 수정 -->
			<li><a href="salesStockOut.do"><i class="fas fa-code"></i>
					영업 출고</a></li>
			<!-- 수정 -->
		</ul>
	</div>

	<!-- 하얀 큰 박스 -->
	<div class="content-box">

		<div class="content-title">인사 관리 | 직원 관리 | 신규 등록</div>


		<!-- 테이블 -->
		<form action="/moduerp/employeeCreate.do" method="POST">
			<table>
				<thead>
					<tr>
						<th>부서명</th>
						<th>직급</th>
						<th>직원명</th>
						<th>이메일</th>
						<th>전화번호</th>
						<th>주소</th>
						<th>사설권한</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<!-- 부서 선택 드롭다운  -->
						<td><select name="departmentId">
								<c:forEach var="department" items="${departmentList}">
									<option value="${department.departmentId}">${department.departmentName}</option>
								</c:forEach>
						</select></td>
						<td><input type="text" name="jobId" placeholder="직급 입력" /></td>
						<td><input type="text" name="empName" placeholder="직원명 입력" /></td>
						<td><input type="text" name="email" placeholder="이메일 입력" /></td>
						<td><input type="text" name="phone" placeholder="전화번호 입력" /></td>
						<td><input type="text" name="address" placeholder="주소 입력" /></td>

						<!-- 사설권한 라디오 버튼 -->
						<td><label for="privateAuthority">권한:</label> <input
							type="radio" name="privateAuthority" value="Y" checked> Y
							<input type="radio" name="privateAuthority" value="N"> N
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
<script>
function addMaterialType() {
    const container = document.getElementById('materialTypeContainer');
    const newInputDiv = document.createElement('div');
    newInputDiv.className = 'material-type-input';
    newInputDiv.innerHTML = `
        <input list="materialTypes" name="materialType" placeholder="자재 종류 입력" />
        <datalist id="materialTypes">
            <c:forEach var="itemName" items="${itemNames}">
                <option value="${itemName}"></option>
            </c:forEach>
        </datalist>
        <button type="button" class="remove-btn" onclick="removeMaterialType(this)">삭제</button>
    `;
    container.appendChild(newInputDiv);
}

function removeMaterialType(button) {
    const inputDiv = button.parentElement;
    inputDiv.remove();
}
</script>


<script>
    const activeMenu = "empMgt";

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
