<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<div class="moduleUseAgeY">사용중인 모듈</div>
				<table>
					<thead>
						<tr>

							<th>모듈 이름</th>
							<th>모듈 등급 코드</th>
							<th>모듈 타입</th>
							<th>모듈 가격</th>
							<th>모듈 설명</th>
							<th>모듈 버전</th>
							<th>모듈 생성 날짜</th>
							<th>모듈 수정 날짜</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="module" items="${moduleList}">
							<tr
								onclick="window.location.href='getModuleDetails.do?moduleId=${module.moduleId}'">

								<td>${module.moduleName}</td>
								<td>${module.moduleGrade}</td>
								<td>${module.moduleType}</td>
								<td>${module.modulePrice}</td>
								<td>${module.moduleDesc}</td>
								<td>${module.moduleVer}</td>
								<td>${module.createDate}</td>
								<td>${module.updateDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="moduleUseAgeN">비 사용중 모듈</div>
				<table>
					<thead>
						<tr>

							<th>모듈 이름</th>
							<th>모듈 등급 코드</th>
							<th>모듈 가격</th>
							<th>모듈 설명</th>
							<th>모듈 버전</th>

							<th>모듈 생성 날짜</th>
							<th>모듈 수정 날짜</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="moduleListN" items="${moduleListN}">
							<tr
								onclick="window.location.href='getModuleDetails.do?moduleId=${moduleListN.moduleId}'">


								<td>${moduleListN.moduleName}</td>
								<td>${moduleListN.moduleGrade}</td>
								<td>${moduleListN.modulePrice}</td>
								<td>${moduleListN.moduleDesc}</td>
								<td>${moduleListN.moduleVer}</td>

								<td>${moduleListN.createDate}</td>
								<td>${moduleListN.updateDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="btn">
				<a href="moduleCreate.do"><button class="btn blue">모듈
						등록</button></a>
			</div>
		</div>

	</div>


	<!-- 메뉴바 임포트 -->
	<c:import url="/WEB-INF/views/common/menubar.jsp" />



	<!-- 푸터 임포트 -->
	<c:import url="/WEB-INF/views/common/footer.jsp" />

</body>
</html>

