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
		 <li><a href="buyStockIn.do"><i class="fas fa-bullhorn"></i> 구매 입고</a></li>
         <li><a href="buyStockOut.do"><i class="fas fa-bullhorn"></i> 구매 출고</a></li>
         <li><a href="delivery.do"><i class="fa-solid fa-truck"></i> 배송 조회</a></li>

		</ul>
	</div>

	<!-- 하얀 큰 박스 -->
	<div class="content-box">

		<div class="content-title">구매관리 | 배송조회 </div>

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
					<th>순번</th>
                    <th>입고 날짜</th>
                    <th>제품명</th>
                    <th>입고 수량</th>
                    <th>입고 장소</th>
                    <th>입고 단가</th>
                    <th>직원명</th> 
				</tr>
			</thead>

			<tbody>
				<c:forEach var="item" items="${itemList}" varStatus="status">
					 <tr
                     onclick="window.location.href='getDeliveryDetails.do?itemCode=${item.itemCode}'">
                  <td>${(currentPage - 1) * 10 + (status.index + 1)}</td>
                  <!-- 순번 계산 -->           
                     <td><fmt:formatDate value="${item.createdAt}"
								pattern="yyyy-MM-dd" /></td>
                     <td>${item.itemName}</td>   
                     <td>${item.stockIn}</td>
                     <td>${item.stockPlace}</td>  
                     <td>${item.inPrice}</td>
                     <td>${item.iDirector}</td>
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
							<a href="delivery.do?page=${i}">${i}</a>
							<!-- 페이지 링크 -->
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
		</div>



		<!-- 버튼 그룹 -->
		<div class="btn-group">
			<a href="deliveryCreate.do"><button class="btn blue">등록</button></a>
		</div>

	</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- jQuery 추가 -->

<script>
    function getItemCode(itemCode) {
        console.log("클릭한 item_code: " + itemCode);

        $.ajax({
        	url: '/moduerp/getDeliveryDetails.do', // URL을 수정
            type: 'GET',
            data: { itemCode: itemCode },
            success: function(response) {
                console.log("데이터 가져오기 성공:", response);
                // 필요한 작업 수행
            },
            error: function(xhr, status, error) {
                console.error("데이터 가져오기 실패:", error);
            }
        });

    }
</script>



<script>
    const activeMenu = "purchaseOrders";

    document.addEventListener('DOMContentLoaded', function() {
        const menuItems = document.querySelectorAll('nav.side ul li a');
        menuItems.forEach(item => {
            if (item.href.includes(activeMenu)) {
                item.classList.add('active');
            }
        });
    });
</script>

<!-- 배송조회 api -->
<script>
    document.getElementById('trackingForm').addEventListener('submit', function(event) {
        event.preventDefault(); // 폼 제출 기본 동작 방지
        
        // 고정된 API Key
        var apiKey = '3uPTRFP4o7AXr6mP7ZwWWw';
        
        // 폼 데이터 추출
        var tCode = document.getElementById('t_code').value;
        var tInvoice = document.getElementById('t_invoice').value;
        
        // URL 생성
        var url = `https://info.sweettracker.co.kr/tracking/4?t_key=${apiKey}&t_code=${tCode}&t_invoice=${tInvoice}`;
        
        // 새로운 페이지로 이동
        window.location.href = url;
    });
</script>


</html>
