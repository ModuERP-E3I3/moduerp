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
	display: flex;
	flex-direction: column; /* 상하 정렬 */
	justify-content: center;
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

.moduleUseAge {
	margin-top: 3%;
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
	margin-right: 3%;
	margin-top: 3%;
}

.btn.blue {
	background-color: blue;
	color: white;
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
			<form action="/moduerp/moduleInsert.do" method="POST">
				<table>
					<thead>
						<tr>
							<th>모듈 이름</th>
							<th>모듈 등급 코드</th>
							<th>모듈 타입</th>
							<th>모듈 가격</th>
							<th>모듈 설명</th>
							<th>모듈 버전</th>

						</tr>
					</thead>
					<tbody>
						<tr>
							<td><select name="moduleName" required
								onchange="setModuleGrade(this)">
									<option value="" disabled selected>모듈명 선택</option>
									<option value="출퇴근" data-code="ATD" data-type="그룹웨어">출퇴근</option>
									<option value="근태문서" data-code="AD" data-type="그룹웨어">근태문서</option>
									<option value="이메일" data-code="EM" data-type="그룹웨어">이메일</option>

									<option value="생산 입고" data-code="P_IN" data-type="생산">생산
										입고</option>
									<option value="생산 출고" data-code="P_OUT" data-type="생산">생산
										출고</option>
									<option value="작업지시서" data-code="WO" data-type="생산">작업지시서</option>
									<option value="품질 관리" data-code="QC" data-type="생산">품질
										관리</option>

									<option value="구매 입고" data-code="B_IN" data-type="구매">구매
										입고</option>
									<option value="구매 출고" data-code="B_OUT" data-type="구매">구매
										출고</option>
									<option value="배송 조회" data-code="DT" data-type="구매">배송
										조회</option>
									<option value="발주서" data-code="OF" data-type="구매">발주서</option>

									<option value="영업 입고" data-code="S_IN" data-type="영업">영업
										입고</option>
									<option value="영업 출고" data-code="S_OUT" data-type="영업">영업
										출고</option>
									<option value="거래처 관리" data-code="VM" data-type="영업">거래처
										관리</option>

									<option value="차량 정보" data-code="C_I" data-type="차량">차량
										정보</option>
									<option value="차량 예약" data-code="C_R" data-type="차량">차량
										예약</option>
									<option value="차량 결제 관리" data-code="C_P" data-type="차량">차량
										결제 관리</option>
									<option value="도로 교통 및 경로 조회" data-code="RTRL" data-type="차량">도로
										교통 및 경로 조회</option>

									<option value="은행계좌관리" data-code="BAM" data-type="회계">은행계좌관리</option>
									<option value="결산관리" data-code="CM" data-type="회계">결산관리</option>
							</select></td>
							<td><input name="moduleGrade" type="text" id="moduleGrade"
								placeholder="모듈 등급 코드" readonly></td>
							<td><input name="moduleType" type="text" id="moduleType"
								placeholder="모들 타입" readonly /></td>
							<td><input name="modulePrice" type="number"
								placeholder="모듈 가격 입력" required></td>
							<td><input name="moduleDesc" type="text"
								placeholder="모듈 설명 입력" required></td>
							<td><input name="moduleVer" type="text"
								placeholder="모듈 버전 입력" required></td>

						</tr>

					</tbody>
				</table>
				<div class="btn">
					<button type="submit" class="btn blue">모듈 등록 하기</button>
				</div>
			</form>
		</div>
	</div>


	<!-- 메뉴바 임포트 -->
	<c:import url="/WEB-INF/views/common/menubar.jsp" />



	<!-- 푸터 임포트 -->
	<c:import url="/WEB-INF/views/common/footer.jsp" />

</body>

<script>
	function setModuleGrade(selectElement) {
		// 선택된 옵션의 data-code 속성 값을 가져와서 모듈 등급 코드 필드에 설정
		var selectedCode = selectElement.options[selectElement.selectedIndex]
				.getAttribute('data-code');
		document.getElementById('moduleGrade').value = selectedCode;

		// 선택된 옵션의 data-type 속성 값을 가져와서 모듈 타입 필드에 설정
		var selectedType = selectElement.options[selectElement.selectedIndex]
				.getAttribute('data-type');
		document.getElementById('moduleType').value = selectedType;
	}
</script>

</html>

