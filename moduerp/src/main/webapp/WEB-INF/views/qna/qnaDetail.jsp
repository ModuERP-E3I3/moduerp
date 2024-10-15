<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qna 작성</title>
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

h1 {
	font-size: 24px;
	margin-bottom: 30px;
	text-align: center;
}

.form-box {
	max-width: 80%;
	width: 100%;
	background-color: white;
	padding: 30px;
	border: 1px solid #ddd;
	border-radius: 4px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

label {
	font-size: 16px;
	font-weight: bold;
	margin-bottom: 10px;
	display: block;
}

.menubar {
	width: 100%;
	background-color: white; /* 흰색 배경 */
	border-bottom: 1px solid #ddd;
	padding: 10px 0;
	text-align: center;
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

button {
	padding: 10px 15px;
	background-color: #007bff;
	color: white;
	border: none;
	cursor: pointer;
	border-radius: 4px;
	font-size: 16px;
}

button:hover {
	background-color: #0056b3;
}

.form-footer {
	margin-top: 30px;
	text-align: right;
}

.back-btn {
	margin-top: 30px;
	padding: 10px 15px;
	background-color: #007bff;
	color: white;
	border: none;
	cursor: pointer;
	text-decoration: none;
	border-radius: 4px;
}
</style>
</head>
<body>

	<div class="container">
		<!-- 메뉴바 -->
		<div class="menubar">
			<c:import url="/WEB-INF/views/common/menubar.jsp" />
		</div>

		<!-- qna 게시판 -->
		<div class="container">

			<h1>질문글 상세 보기</h1>

			<div class="form-box">
				<h3>${qnaDetail.qTitle}</h3>
				<p>작성자: ${qnaDetail.empName}</p>
				<p>
					작성일:
					<fmt:formatDate value="${qnaDetail.qDate}" pattern="yyyy-MM-dd" />
				</p>
				<hr>
				<p>${qnaDetail.qContents}</p>
				<div class="button-group">
					<a href="qna.do" class="back-btn">목록으로 돌아가기</a> <a
						href="questionUpdateForm.do?qSeq=${qnaDetail.qSeq}"><button
							class="btn green">수정</button></a>
					<button class="btn red" onclick="openDeleteModal()">삭제</button>
				</div>
			</div>
			<!-- 삭제 확인 모달 -->
			<div id="delete-modal" style="display: none;">
				<div class="modal-content">
					<h2>정말로 삭제하시겠습니까?</h2>
					<p>삭제된 데이터는 복구할 수 없습니다.</p>
					<!-- 삭제 버튼을 포함하는 폼 추가 -->
					<form action="deleteQna.do" method="POST">
						<input type="hidden" name="qSeq"
							value="${qnaDetail.qSeq}">
						<!-- itemCode를 숨겨진 필드로 전달 -->
						<button type="submit" class="go-delete">삭제</button>
						<button type="button" class="stay-page"
							onclick="closeDeleteModal()">취소</button>
					</form>
				</div>
			</div>

			<div class="form-box">
				<c:choose>
					<c:when test="${qnaDetail.qStatus == 'N'}">
						<h1 style="color: gray;">답변 대기중</h1>
						<!-- 답변 입력 버튼 -->
						<%-- <button type="button" class="btn blue"
							onclick="location.href='qnaAnswerForm.do?qSeq=${qnaDetail.qSeq}'">답변
							입력</button> --%>
					</c:when>
					<c:when test="${qnaDetail.qStatus == 'Y'}">
						<h1 style="color: green;">답변 완료</h1>
					</c:when>
				</c:choose>
			</div>


		</div>

	</div>

	<!-- 푸터 -->
	<footer>
		<c:import url="/WEB-INF/views/common/footer.jsp" />
	</footer>

</body>
<script type="text/javascript">
function openDeleteModal() {
    document.getElementById('delete-modal').style.display = 'block';
}

function closeDeleteModal() {
    document.getElementById('delete-modal').style.display = 'none';
}



</script>


</html>
