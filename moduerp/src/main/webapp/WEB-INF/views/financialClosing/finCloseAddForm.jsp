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
			<li><a href="bankmg.do"><i class="fa-solid fa-money-check-dollar"></i> 은행
					계좌 관리</a></li>
			<li><a href="finClose.do"><i class="fa-solid fa-calendar-days"></i>
					결산 관리</a></li>
			<!-- 수정 -->
			<!-- <li><a href="productionWorkorder.do"><i class="fas fa-code"></i> 작업지시서</a></li> 수정
	        <li><a href="productionQuality.do"><i class="fas fa-plug"></i> 품질관리</a></li> 수정 -->
		</ul>
	</div>

	<!-- 하얀 큰 박스 -->
	<div class="content-box">

		<div class="content-title">회계 관리 | 결산 관리 | 신규 등록</div>
		<form action="finCloseAddForm.do" method="post">
			<table>
				<thead>
					<tr>
						<th>은행 선택</th>
						<th>시작일</th>
						<th>종료일</th>
						<th>총 매출</th>
						<th>총 비용</th>
						<th>순이익</th>
						<th>결산 유형</th>
						<th>결산일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" id="bankNameInput" name="bankName"
							required list="bankList" onchange="updateBankId()" /> <datalist
								id="bankList">
								<c:forEach var="bankmg" items="${bankmgList}">
									<option value="${bankmg.bankName}"
										data-bank-id="${bankmg.bankId}"></option>
								</c:forEach>
							</datalist><input type="hidden" id="bankIdInput" name="bankId" value=""></td>
						<td><input type="date" name="startDate" required /></td>
						<td><input type="date" name="endDate" required /></td>
						<td><input type="number" name="totalSales" placeholder="총 매출 입력" required /></td>
						<td><input type="number" name="totalExpenses" placeholder="총 비용 입력" required /></td>
						<td><input type="number" name="netProfit" placeholder="순이익 입력" required /></td>
						<td><input type="text" name="closingType" placeholder="구분 입력" required /></td>
						<td><input type="date" name="closingDate" required /></td>
					</tr>
				</tbody>
			</table>


			<div class="btn-group">
				<button class="btn blue" type="submit">등록</button>
			</div>
		</form>
	</div>
</body>

<script>
    function updateBankId() {
        const bankNameInput = document.getElementById('bankNameInput');
        const bankIdInput = document.getElementById('bankIdInput');
        
        // 모든 option 요소를 가져옵니다
        const options = document.querySelectorAll('#bankList option');

        // 선택한 은행 이름을 찾고 해당 bankId를 업데이트합니다
        options.forEach(option => {
            if (option.value === bankNameInput.value) {
                bankIdInput.value = option.getAttribute('data-bank-id');
            }
        });
    }
</script>

<script>
    const activeMenu = "bankmg";

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