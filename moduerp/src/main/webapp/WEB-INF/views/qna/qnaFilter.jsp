<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NoticeList</title>
<style type="text/css">
/* 기본적인 페이지 설정 */
body {
	font-family: 'Arial', sans-serif;
	margin: 0;
	padding: 0;
	background-color: white;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

.container {
	width: 100%;
	margin: 0 auto; /* 중앙 정렬 */
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}

.menubar {
	width: 100%;
	background-color: white; /* 흰색 배경 */
	border-bottom: 1px solid #ddd;
	padding: 10px 0;
	text-align: center;
}

.QNA-board {
	width: 100%;
	background: white;
	margin-top: 30px;
	padding: 30px 0; /* 패딩 추가 */
	text-align: center; /* 텍스트 중앙 정렬 */
}

.box {
	max-width: 80%; /* 본문 내용은 80% 너비로 */
	margin: 0 auto;
	width: 100%;
}

h2 {
	font-size: 24px;
	margin-bottom: 30px;
}

.search-bar {
	display: flex;
	justify-content: center;
	gap: 10px;
	margin-bottom: 30px;
}

.search-bar select, .search-bar input, .search-bar button {
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ddd;
	border-radius: 4px;
}

.search-bar button {
	background-color: #007bff;
	color: white;
	border: none;
	cursor: pointer;
	border-radius: 4px;
}

.search-bar button:hover {
	background-color: #0056b3;
}

.QNA-list {
	list-style: none;
	padding: 0;
	margin: 0;
	text-align: left; /* 리스트 항목은 왼쪽 정렬 */
	width: 100%;
}

.QNA-list li {
	padding: 20px 0;
	border-bottom: 1px solid #eee;
	display: flex;
	justify-content: space-between;
	max-width: 100%;
}

.QNA-list li a {
	font-size: 18px;
	text-decoration: none;
	color: #333;
}

.QNA-list li a:hover {
	text-decoration: underline;
}

.QNA-list li .meta {
	font-size: 14px;
	color: #777;
	display: flex;
	gap: 20px;
}

.QNA-list .important a {
	color: red;
}

/* 페이지네이션 */
.pagination {
	display: flex;
	justify-content: center;
	margin-top: 30px;
}

.page-btn {
	padding: 10px 15px;
	margin: 0 5px;
	background-color: #f1f1f1;
	border: 1px solid #ddd;
	cursor: pointer;
}

.page-btn:hover {
	background-color: #ddd;
}

.page-btn.active {
	background-color: #007bff;
	color: white;
}

/* 작성, 수정, 삭제 버튼 공통 스타일 */
.action-btn {
	margin-top: 30px;
	padding: 10px 15px;
	background-color: #007bff;
	color: white;
	border: none;
	cursor: pointer;
	text-decoration: none;
	border-radius: 4px;
}

/* 페이지 정보 */
.page-info {
	text-align: center;
	margin-top: 30px;
	color: black;
	font-size: 14px;
	margin-bottom: 50px;
}

/* 푸터 설정 */
footer {
	width: 100%;
	background-color: white;
	text-align: center;
	padding: 20px 0;
	border-top: 1px solid #ddd;
	margin-top: 50px;
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

</style>
</head>
<body>

	<div class="container">
		<!-- 메뉴바 -->
		<div class="menubar">
			<c:import url="/WEB-INF/views/common/menubar.jsp" />
		</div>

		<!-- 공지사항 게시판 -->
		<div class="QNA-board">
			<div class="box">
				<h2>QnA</h2>

				<form action="/moduerp/qnaFilter.do">
				<div class="filter-box">
                <select name="filterOption" id="filterOption">
                    <option disabled selected>옵션 선택</option>
                    <option value="empName" ${option == 'empName' ? 'selected' : ''}>작성자 명</option>
                    <option value="qTitle" ${option == 'qTitle' ? 'selected' : ''}>글 제목</option>
                </select>
                <input type="date" name="startDate" id="startDate"
					value="${startDate != null && startDate.length() >= 10 ? startDate.substring(0, 10) : ''}" />
				<input type="date" name="endDate" id="endDate"
					value="${endDate != null && endDate.length() >= 10 ? endDate.substring(0, 10) : ''}" />
				<input type="text" name="filterText" id="filterText"
					placeholder="내용 입력" value="${filterText != null ? filterText : ''}" />
				<button type="submit" class="btn">조회</button>
				<button type="button" class="btn"
					onclick="window.location.href='qna.do';">초기화</button>
				</div>
				</form>
				<hr>

				<!-- 동적으로 공지사항 리스트 출력 -->
				<ul class="QNA-list">
					<c:forEach var="qna" items="${qnaList}">
						<%-- <li class="<c:if test='${notice.noticeImp == "Y"}'>important</c:if>"> --%>
						<li><a href="qnaDetail.do?qSeq=${qna.qSeq}">${qna.qTitle}</a>
							<div class="meta">
								<span>${qna.empName}</span> <span>No.${qna.qSeq}</span> <span>작성일
									: <fmt:formatDate value="${qna.qDate}" pattern="yyyy-MM-dd" />
								</span>
								<%-- <span>조회수 : ${notice.viewCnt}</span> --%>
							</div></li>
					</c:forEach>
				</ul>

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
									<a href="qna.do?page=${i}&filterOption=${option}&filterText=${filterText}&startDate=${startDate}&endDate=${endDate}">${i}</a>
									<!-- 페이지 링크 -->
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</div>

				<div class="btn-group">
					<a href="qnaCreate.do"><button class="btn blue">등록</button></a>
				</div>

				
			</div>
		</div>
	</div>

	<!-- 푸터 -->
	<footer>
		<c:import url="/WEB-INF/views/common/footer.jsp" />
	</footer>

</body>
</html>
