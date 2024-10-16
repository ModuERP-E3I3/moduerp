<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NoticeList</title>
<style type="text/css">
body {
	font-family: 'Arial', sans-serif;
	margin: 0;
	padding: 0;
	background-color: white;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: flex-start;
	min-height: 100vh; /* 전체 높이를 100%로 설정 */
}

h1 {
	font-size: 45px;
}

h2 {
	font-size: 20px;
	font-weight: bold;
	margin-bottom: 5px; /* 제목과 날짜 사이 간격 조정 */
}

.container {
	width: 100%;
	margin: 0 auto;
	display: flex;
	flex-direction: column;
	align-items: flex-start; /* 왼쪽 정렬 */
	justify-content: flex-start; /* 상단에 배치 */
	padding-left: 20px; /* 좌측 여백 */
	flex-grow: 1; /* 남은 공간을 차지하도록 설정 */
}

.menubar {
	width: 100%;
	background-color: white;
	border-bottom: 1px solid #ddd;
	padding: 10px 0;
	text-align: center;
}

.notice-board {
	width: 100%;
	background: white;
	margin-top: 30px;
	padding: 30px 20px;
	text-align: left; /* 텍스트 왼쪽 정렬 */
}

.box {
	max-width: 60%;
	margin: 0 auto;
	width: 100%;
}

h1, h2 {
	text-align: left; /* 공지사항과 제목을 왼쪽 정렬 */
}

.search-bar {
	display: flex;
	justify-content: flex-start; /* 왼쪽 정렬 */
	gap: 10px;
	margin-bottom: 30px;
	align-items: center; /* 등록 버튼과 검색 바를 수평으로 정렬 */
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

.notice-list {
	list-style: none;
	padding: 0;
	margin: 0;
	text-align: left; /* 리스트 왼쪽 정렬 */
	width: 100%;
}

.notice-list li {
	padding: 20px 0;
	border-bottom: 1px solid #eee;
	display: block;
}

.notice-list li a {
	font-size: 18px;
	text-decoration: none;
	color: #333;
}

.notice-list li a:hover {
	text-decoration: underline;
}

.notice-list li .meta {
	font-size: 14px;
	color: #777;
	margin-top: 0; /* 날짜와 제목 사이 간격 제거 */
}

/* 등록 버튼 스타일 */
.action-btn {
	padding: 10px 15px;
	background-color: #28a745; /* 녹색 배경 */
	color: white;
	border: none;
	cursor: pointer;
	text-decoration: none;
	border-radius: 4px;
	display: inline-block;
	margin-left: 10px; /* 검색 버튼과 간격 */
}

.action-btn:hover {
	background-color: #218838; /* Hover 시 더 짙은 녹색 */
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

.page-info {
	text-align: left; /* 페이지 정보도 왼쪽 정렬 */
	margin-top: 30px;
	color: black;
	font-size: 14px;
}

footer {
	text-align: center;
	background-color: #2c3e50;
	color: #ecf0f1;
	width: 100%;
	padding: 20px 0;
	margin-top: auto; /* 푸터를 컨텐츠 이후에 배치하도록 설정 */
}

/* 반응형 디자인 */
@media ( max-width : 768px) {
	.container {
		flex-direction: column;
	}
	.sidebar {
		width: 100%;
		box-shadow: none;
	}
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
		<div class="notice-board">
			<div class="box">
				<h1>공지사항</h1>

				<!-- 검색 바 -->
				<div class="search-bar">
					<form action="<c:url value='/notice/list.do' />" method="get">
						<select name="category" id="category">
							<option value="전체">조건 선택</option>
							<option value="제목">제목</option>
							<option value="내용">내용</option>
							<option value="제목+내용">제목+내용</option>
						</select> <input type="text" name="keyword" placeholder="내용">
						<button type="submit">검색</button>
					</form>
					<!-- 등록 버튼을 검색 바 오른쪽에 배치 -->
					<a href="<c:url value='/notice/form.do' />" class="action-btn">등록</a>
				</div>

				<hr>

				<!-- 공지사항 리스트 -->
				<ul class="notice-list">
					<c:forEach var="notice" items="${noticeList}">
						<li>
							<h2><a href="${pageContext.request.contextPath}/notice/view/${notice.noticeId}.do">${notice.title}</a></h2>
							<div class="meta">
								<fmt:formatDate value="${notice.noticeDate}" pattern="yyyy-MM-dd" />
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>

	<!-- 푸터 -->
	<footer>
		<c:import url="/WEB-INF/views/common/footer.jsp" />
	</footer>

</body>
</html>
