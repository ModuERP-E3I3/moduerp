<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column; /* 상하 정렬 */
	align-items: center;
	background-color: white;
	box-shadow: 0 12px 24px rgba(0, 0, 0, 0.2); /* 입체감 설정 */
	border-radius: 20px;
	margin: 40px auto; /* 페이지 중앙 정렬 */
	width: 100%;
	height: 700px;
	max-width: 2400px;
	align-items: center;
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

.moduleUseAge {
	margin-top: 3%;
}

.moduleTable {
	margin: 0 auto; /* 좌우 가운데 정렬 */
	width: 90%; /* 테이블의 너비를 90%로 설정 */
}

/* 버튼 스타일 */
.btn-group {
	margin-top: 20px;
	float: right; /* 오른쪽 정렬 */
	margin-right: -65%; /* 간격 조정 */
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

<script type="text/javascript">
	function movePage() {
		location.href = "loginPage.do";
	}
</script>

</head>
<body>
	<form action="/moduerp/moduleUpdate.do" method="POST">
		<input type="hidden" name="moduleId" value="${moduleDetails.moduleId}">
		<!-- 서브 이미지 컨테이너 -->
		<div id="image-container">

			<!-- 모듈 리스트 출력 -->
			<div class="moduleTable">

				<table>
					<thead>
						<tr>

							<th>모듈 이름</th>
							<th>모듈 등급 코드</th>
							<th>모듈 가격</th>
							<th>모듈 설명</th>
							<th>모듈 버전</th>
							<th>사용 여부</th>
							<th>모듈 생성 날짜</th>
							<th>모듈 수정 날짜</th>
						</tr>
					</thead>
					<tbody>
						<tr>

							<td>${moduleDetails.moduleName}</td>
							<td>${moduleDetails.moduleGrade}</td>
							<td><input type="number" name="modulePrice"
								value="${moduleDetails.modulePrice}" required /></td>
							<td><input type="text" name="moduleDesc"
								value="${moduleDetails.moduleDesc}" required /></td>
							<td><input type="text" name="moduleVer"
								value="${moduleDetails.moduleVer}" required /></td>
							<td><c:set var="moduleUsageStr"
									value="${fn:trim(moduleDetails.moduleUsage)}" /> <select
								name="moduleUsage" required>
									<option value="Y" ${moduleUsageStr == 'Y' ? 'selected' : ''}>Y</option>
									<option value="N" ${moduleUsageStr == 'N' ? 'selected' : ''}>N</option>
							</select></td>


							<td>${moduleDetails.createDate}</td>
							<td>${moduleDetails.updateDate}</td>
						</tr>
					</tbody>
				</table>

			</div>
			<!-- 버튼 그룹 -->
			<div class="btn-group">
				<button type="submit" class="btn green">수정 완료</button>
			</div>


		</div>
	</form>

	<!-- 메뉴바 임포트 -->
	<c:import url="/WEB-INF/views/common/menubar.jsp" />



	<!-- 푸터 임포트 -->
	<c:import url="/WEB-INF/views/common/footer.jsp" />

</body>


</html>

