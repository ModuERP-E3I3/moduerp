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

	<!-- 상단 메뉴 -->
	<div class="top-content-box">
		<ul id="menubar">
			<li><a href="empMgt.do">직원 관리</a></li>
		</ul>
	</div>


	<div class="content-box">
		<div class="content-title">인사 관리 | 직원 관리 | ${employeeDetail.empName} 수정</div>

		<form action="/moduerp/updateEmployee.do" method="POST">
			<input type="hidden" name="uuid" value="${employeeDetail.uuid}" />

			<!-- 테이블 -->
			<!-- 테이블 -->
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
						<td><select name="departmentId">
								<c:forEach var="department" items="${departmentList}">
									<option value="${department.departmentId}"
										${department.departmentId == employeeDetail.departmentId ? 'selected' : ''}>
										${department.departmentName}</option>
								</c:forEach>
						</select></td>
						<td><input type="text" name="jobId" placeholder="직급"
							value="${employeeDetail.jobId}" required /></td>

						<td><input type="text" name="empName" placeholder="직원명"
							value="${employeeDetail.empName}" required /></td>

						<td><input type="text" name="email" placeholder="이메일"
							value="${employeeDetail.email}" required /></td>

						<td><input type="text" name="phone" placeholder="전화번호"
							value="${employeeDetail.phone}" required /></td>

						<td><input type="text" name="address" placeholder="주소"
							value="${employeeDetail.address}" required /></td>

						<!-- 사설권한 라디오 버튼 -->
						<td><label for="privateAuthority">권한:</label> <input
							type="radio" name="privateAuthority" value="Y"
							${employeeDetail.privateAuthority == 'Y' ? 'checked' : ''}>
							Y <input type="radio" name="privateAuthority" value="N"
							${employeeDetail.privateAuthority == 'N' ? 'checked' : ''}>
							N</td>
					</tr>
				</tbody>
			</table>



			<div class="btn-group">
				<button type="submit" class="btn green">수정 완료</button>
			</div>
		</form>
	</div>
</body>
</html>
