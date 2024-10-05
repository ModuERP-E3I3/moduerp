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
			<li><a href="productionStockIn.do"><i
					class="fas fa-bullhorn"></i> 생산 입고</a></li>
			<li><a href="productionStockOut.do"><i
					class="fas fa-clipboard"></i> 생산 출고</a></li>
			<!-- 수정 -->
			<li><a href="productionWorkorder.do"><i class="fas fa-code"></i>
					작업지시서</a></li>
			<!-- 수정 -->
			<li><a href="productionQuality.do"><i class="fas fa-plug"></i>
					품질관리</a></li>
			<!-- 수정 -->
		</ul>
	</div>

	<!-- 하얀 큰 박스 -->
	<div class="content-box">

		<div class="content-title">생산관리 | 생산출고</div>

		<!-- 폼 시작 -->
		<form action="/moduerp/productionStockOutCreate.do" method="POST">
			<!-- 필터 박스 -->
			<div class="filter-box">
				<select>
					<option>조회기간</option>
				</select> <input type="date" /> <input type="date" /> <select>
					<option>품목 선택</option>
				</select> <input type="text" placeholder="내용 입력" />
				<button class="btn">조회</button>
			</div>

			<!-- 테이블 -->
			<table>
				<thead>
					<tr>
						<th>제품명</th>
						<th>출고날짜</th>
						<th>출고장소</th>
						<th>출고수량</th>
						<th>출고단가</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input list="itemNames" name="itemName"
							id="itemNameInput" placeholder="품목 이름 선택" required
							onchange="updateItemCode()" /> <datalist id="itemNames">
								<c:forEach var="item" items="${itemList}">
									<option value="${item.itemName} 재고 : ${item.stock}"
										data-item-code="${item.itemCode}"></option>
								</c:forEach>
							</datalist> <input type="hidden" name="itemCode" id="itemCodeInput" /> <!-- itemCode를 담을 숨겨진 입력 필드 -->
						</td>

						<td><input type="date" name="createdOutAt" required /></td>
						<td><input list="stockOutPlaces" name="stockOutPlace"
							placeholder="출고 장소 선택" required /> <datalist id="stockOutPlaces">
								<c:forEach var="place" items="${stockOutPlaces}">
									<option value="${place}"></option>
								</c:forEach>
							</datalist></td>
						<td><input type="number" name="stockOut" required /></td>
						<td><input type="number" name="outPrice" step="0.01" required /></td>
					</tr>
				</tbody>
			</table>

			<!-- 버튼 그룹 -->
			<div class="btn-group">
				<button type="submit" class="btn blue">등록완료</button>
			</div>
		</form>
		<!-- 폼 끝 -->
	</div>
</body>

<script>
function updateItemCode() {
    // itemNameInput에서 선택한 옵션
    var input = document.getElementById('itemNameInput');
    // 사용자가 선택한 값을 가져옵니다.
    var selectedValue = input.value;

    // datalist에서 모든 옵션을 가져옵니다.
    var datalist = document.getElementById('itemNames');
    var options = datalist.getElementsByTagName('option');

    var itemCode = ''; // 기본값을 설정합니다.

    // 옵션을 순회하여 선택한 값과 일치하는지 확인합니다.
    for (var i = 0; i < options.length; i++) {
        if (options[i].value === selectedValue) {
            // 일치하는 경우 data-item-code 속성에서 itemCode를 가져옵니다.
            itemCode = options[i].getAttribute('data-item-code');
            break; // 일치하는 값을 찾았으면 반복 종료
        }
    }

    // 숨겨진 입력 필드에 itemCode 설정
    document.getElementById('itemCodeInput').value = itemCode;
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
