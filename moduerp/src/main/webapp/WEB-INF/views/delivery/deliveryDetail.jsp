<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>erpMain</title>

<style type="text/css">
/* 기존 스타일 그대로 유지 */

.content-box {
	width: 96%; 
	background-color: white;
	margin-left: 1%;
	margin-right: 5%;
	margin-top: 3%;
	border: 1px solid #ccc;
	border-radius: 20px; 
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); 
	position: relative;
	padding: 20px; 
}

/* 나머지 스타일 생략 */
</style>

</head>

<body>
	<c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

	<div class="top-content-box">
		<ul id="menubar">
			<li><a href="buyStockIn.do"><i class="fas fa-bullhorn"></i> 구매 입고</a></li>
			<li><a href="buyStockOut.do"><i class="fas fa-bullhorn"></i> 구매 출고</a></li>
			<li><a href="delivery.do"><i class="fa-solid fa-truck"></i> 배송 조회</a></li>
		</ul>
	</div>

	<div class="content-box">
		<div class="content-title">구매관리 | 배송조회 | ${itemDetails.itemName}</div>

		<!-- 필터 박스 -->
		<div class="filter-box">
			<select>
				<option>조회기간</option>
			</select> 
			<input type="date" /> 
			<input type="date" /> 
			<select>
				<option>품목 선택</option>
			</select> 
			<input type="text" placeholder="내용 입력" />
			<button class="btn">조회</button>
		</div>

		<!-- 첫 번째 테이블 -->
		<table>
			<thead>
				<tr>
                    <th>입고 날짜</th>
                    <th>재고명</th>
                    <th>입고 수량</th>
                    <th>입고 장소</th>
                    <th>입고 단가</th>
                    <th>직원명</th> 
				</tr>
			</thead>
			<tbody>
				<tr>           
					 <td>${itemDetails.createdAt}</td>
                     <td>${itemDetails.itemName}</td>   
                     <td>${itemDetails.stockIn}</td>
                     <td>${itemDetails.stockPlace}</td>  
                     <td>${itemDetails.inPrice}</td>
                     <td>${itemDetails.iDirector}</td>
				</tr>
			</tbody>
		</table>
		
		<!-- 두 번째 테이블 -->
		<form action="/moduerp/deliveryCreate.do" method="POST">
			<input type="hidden" name="inDate" value="<%=java.time.LocalDate.now()%>">
			<table>
				<thead>
					<tr>
						<th>택배규격</th>
						<th>수신자번호</th>
						<th>수신자주소</th>
						<th>운송장번호</th>
						<th>배송업체</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" id="spec" name="spec"></td>
						<td><input type="text" name="receiverId" /></td>
						<td><input type="text" name="address" placeholder="수신자 주소" /></td>
						<td><input type="text" name="waybill" placeholder="운송장번호" /></td>
						<td>
							<input type="text" id="deliveryCompany" name="deliveryCompany" list="deliveryCompanyCode" placeholder="택배사 선택" required> 
							<datalist id="deliveryCompanyCode">
								<option value="01">우체국택배</option>
								<option value="04">CJ대한통운</option>
								<option value="05">한진택배</option>
								<option value="06">로젠택배</option>
								<option value="08">롯데택배</option>
								<option value="94">카카오 T 당일배송</option>
								<option value="95">큐익스프레스</option>
								<option value="11">일양로지스</option>
								<option value="22">대신택배</option>
								<option value="23">경동택배</option>
								<option value="24">GS Postbox 택배</option>
								<option value="46">CU편의점택배</option>
							</datalist>
						</td>
					</tr>
				</tbody>
			</table>

			<div class="btn-group">
				<button type="submit" class="btn blue">등록 완료</button>
			</div>
		</form>

		<div class="btn-group">
			<button class="btn red" onclick="openDeleteModal()">삭제</button>
			<a href="buyStockInDetailUpdate.do?itemCode=${itemDetails.itemCode}">
				<button class="btn green">수정</button>
			</a>
		</div>

	</div>

	<!-- 삭제 확인 모달 -->
	<div id="delete-modal" style="display: none;">
		<div class="modal-content">
			<h2>정말로 삭제하시겠습니까?</h2>
			<p>삭제된 데이터는 복구할 수 없습니다.</p>
			<form action="deleteBuyStockIn.do" method="POST">
				<input type="hidden" name="itemCode" value="${itemDetails.itemCode}">
				<button type="submit" class="go-delete">삭제</button>
				<button type="button" class="stay-page" onclick="closeDeleteModal()">취소</button>
			</form>
		</div>
	</div>

	<script type="text/javascript">
	function openDeleteModal() {
	    document.getElementById('delete-modal').style.display = 'block';
	}

	function closeDeleteModal() {
	    document.getElementById('delete-modal').style.display = 'none';
	}
	</script>
</body>
</html>
