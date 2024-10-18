<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ModuERP</title>

<style type="text/css">

/* 전체 페이지 기본 스타일 */
body, html {
	margin-top: 2%;
	font-family: 'Helvetica Neue', Arial, sans-serif;
	padding: 0;
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
	justify-content: center; /* 중앙 정렬 */
	align-items: center;
	gap: 200px; /* 이미지 사이 간격 */
	background-color: white;
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

/* 이미지 스타일 */
div.image-wrapper img {
	width: 800px;
	height: auto;
	box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3); /* 입체감 설정 */
	border-radius: 20px; /* 모서리 둥글게 */
	transition: transform 0.3s ease, box-shadow 0.3s ease;
	/* 호버 시 확대 및 그림자 변화 */
}

/* 이미지 호버 시 확대 효과 */
div.image-wrapper img:hover {
	transform: scale(1.05);
	box-shadow: 0 15px 30px rgba(0, 0, 0, 0.4); /* 호버 시 그림자 더 진하게 */
}

/* 이미지 위 둥근 모서리 사각형 텍스트 스타일 */
div.image-wrapper .image-label {
	position: absolute;
	top: 20px; /* 이미지의 상단에서 20px 아래에 배치 */
	left: 50%;
	transform: translateX(-50%);
	background-color: rgba(0, 0, 0, 0.6); /* 반투명 검정 배경 */
	color: white;
	font-size: 25px;
	font-weight: bold;
	padding: 10px 20px; /* 텍스트 주변 여백 설정 */
	border-radius: 15px;
	text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5); /* 텍스트 그림자 */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 사각형에 그림자 */
}

body {
	font-family: 'Arial', sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f9f9f9;
	color: #333;
}

.image-section {
	width: 100%;
	height: 50vh; /* 전체 화면의 절반 높이 */
	background-image:
		url('https://commons.wikimedia.org/wiki/Category:16:9?uselang=ko#/media/File:Aspect-ratio-16x9.svg');
	/* 이미지 경로 설정 */
	background-size: cover;
	background-position: center;
}

.container {
	max-width: 1200px;
	margin: 0 auto;
	padding: 50px 20px;
	display: flex;
	justify-content: space-between;
	align-items: flex-start;
}

.text-section {
	flex: 1;
	padding-right: 50px;
}

.stats-section {
	display: flex;
	flex-direction: column;
	align-items: flex-end;
	flex: 1;
}

h1 {
	font-size: 36px;
	margin-bottom: 20px;
	text-align: left;
}

p {
	font-size: 18px;
	color: #666;
	margin-bottom: 40px;
	text-align: left;
}

.stats {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
	width: 100%;
	max-width: 400px;
}

.stat-item {
	width: 48%;
	margin-bottom: 20px;
	text-align: center;
}

.stat-label {
	font-size: 18px;
	color: #666;
	margin-bottom: 10px;
}

.stat {
	font-size: 28px;
	font-weight: bold;
	color: #217770;
	margin-bottom: 3%;
}
</style>

<script type="text/javascript">
	function movePage() {
		location.href = "loginPage.do";
	}
</script>

</head>
<body>

	<!-- 이미지 섹션 -->
	<div class="image-section"></div>

	<div class="container">
		<div class="text-section">
			<h1>
				ModuERP는 모든 사업자들이 <br>성장할 수 있도록 함께합니다.
			</h1>
			<p>
				19개의 모듈을 고객의 필요에 따라 자유롭게 조합하고 <br>혁신적인 기술과 직관적인 사용자 경험을 제공하여 <br>
				사업장의 업무 효율을 극대화하는 것<br>Modu의 비전이자 목표입니다.
			</p>
		</div>
		<div class="stats-section">
			<div class="stats">
				<div class="stat-item">
					<div class="stat">인사 관리</div>
					<div class="stat-label">직원관리</div>
				</div>
				<div class="stat-item">
					<div class="stat">구매 관리</div>
					<div class="stat-label">구매입출고, 배송조회, 발주서관리</div>
				</div>
				<div class="stat-item">
					<div class="stat">생산 관리</div>
					<div class="stat-label">생산입출고, 품질관리, 작업지시서</div>
				</div>
				<div class="stat-item">
					<div class="stat">영업 관리</div>
					<div class="stat-label">영업입출고, 거래처관리</div>
				</div>
				<div class="stat-item">
					<div class="stat">차량 관리</div>
					<div class="stat-label">
						차량정보, 차량예약, <br>결제관리, 경로조회
					</div>
				</div>
				<div class="stat-item">
					<div class="stat">그룹웨어</div>
					<div class="stat-label">
						출퇴근, 인사관리, <br>사내메일
					</div>
				</div>

			</div>
		</div>
	</div>


	<!-- 메뉴바 임포트 -->
	<c:import url="/WEB-INF/views/common/menubar.jsp" />



	<!-- 푸터 임포트 -->
	<c:import url="/WEB-INF/views/common/footer.jsp" />

</body>
</html>

