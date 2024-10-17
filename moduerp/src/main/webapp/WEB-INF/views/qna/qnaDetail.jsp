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
	max-width: 60%;
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

#delete-answer-modal {
	display: none; /* 초기에는 보이지 않도록 설정 */
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%; /* 전체 화면 너비 */
	height: 100%; /* 전체 화면 높이 */
	background-color: rgba(0, 0, 0, 0.5); /* 배경 반투명 */
	display: flex; /* 플렉스 박스를 사용하여 중앙 정렬 */
}

#delete-qna-modal {
	display: none; /* 초기에는 보이지 않도록 설정 */
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%; /* 전체 화면 너비 */
	height: 100%; /* 전체 화면 높이 */
	background-color: rgba(0, 0, 0, 0.5); /* 배경 반투명 */
	display: flex; /* 플렉스 박스를 사용하여 중앙 정렬 */
}

.modal-content {
	background-color: #fff;
	padding: 20px;
	border-radius: 5px;
	text-align: center;
	width: 300px; /* 원하는 너비 */
	position: relative;
	margin: auto; /* 중앙 정렬을 위한 마진 */
	margin-top: 20%;
}

.modal-content h2 {
	margin-bottom: 20px;
}

.modal-content button {
	padding: 10px 20px;
	margin: 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.modal-content .go-delete {
	background-color: red;
	color: #fff;
}

.modal-content .stay-page {
	background-color: gray;
	color: #fff;
}
.button-group {
	margin-top : 10%;
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
					<fmt:formatDate value="${qnaDetail.qDate}" pattern="yyyy-MM-dd" />
				</p>
				<hr>
				<p>${qnaDetail.qContents}</p>
				<div class="button-group">
					<a href="qna.do" class="back-btn">목록으로 돌아가기</a>
					<c:if test="${uuid == qnaDetail.uuid}">
						<a href="questionUpdateForm.do?qSeq=${qnaDetail.qSeq}"><button
								class="btn green">수정</button></a>
					</c:if>
					<c:if
						test="${uuid == qnaDetail.uuid || uuid == '08fd74b7-f049-4583-bc44-213d2114aa5d'}">
						<button class="btn red" onclick="openDeleteQnaModal()">삭제</button>
					</c:if>
				</div>
			</div>
			<!-- 질문 삭제 모달 -->
			<div id="delete-qna-modal" style="display: none;">
				<div class="modal-content">
					<h2>정말로 삭제하시겠습니까?</h2>
					<p>삭제된 데이터는 복구할 수 없습니다.</p>
					<form action="deleteQna.do" method="POST">
						<input type="hidden" name="qSeq" value="${qnaDetail.qSeq}">
						<input type="hidden" name="aSeq" value="${answerDetail.aSeq}">
						<input type="hidden" name="qSeq" value="${qnaDetail.qStatus}">
						<button type="submit" class="go-delete">삭제</button>
						<button type="button" class="stay-page"
							onclick="closeDeleteQnaModal()">취소</button>
					</form>
				</div>
			</div>

			<div class="form-box">
				<c:choose>
					<c:when test="${qnaDetail.qStatus == 'N'}">
						<h1 style="color: gray;">답변 대기중</h1>
						<!-- 답변 입력 버튼 -->
						<c:if test="${uuid == '08fd74b7-f049-4583-bc44-213d2114aa5d'}">
							<button type="button" class="btn blue"
								onclick="location.href='qnaAnswerCreate.do?qSeq=${qnaDetail.qSeq}'">답변
								입력</button>
						</c:if>
					</c:when>
					<c:when test="${qnaDetail.qStatus == 'Y'}">
						<h1 style="color: green;">답변 완료</h1>
						<h3>${answerDetail.aTitle}</h3>
						<p>작성자: 관리자</p>
						<p>
							<fmt:formatDate value="${answerDetail.aDate}"
								pattern="yyyy-MM-dd" />
						</p>
						<hr>
						<p>${answerDetail.aContents}</p>
						<c:if test="${uuid == '08fd74b7-f049-4583-bc44-213d2114aa5d'}">
							<div class="button-group">
								<button type="button" class="btn green"
									onclick="location.href='answerUpdateForm.do?qSeq=${qnaDetail.qSeq}'">답변
									수정</button>
								<button class="btn red" onclick="openDeleteAnswerModal()">삭제</button>

								<!-- 답변 삭제 모달 -->
								<div id="delete-answer-modal" style="display: none;">
									<div class="modal-content">
										<h2>정말로 삭제하시겠습니까?</h2>
										<p>삭제된 데이터는 복구할 수 없습니다.</p>
										<form action="deleteAnswer.do" method="POST">
											<input type="hidden" name="aSeq" value="${answerDetail.aSeq}">
											<input type="hidden" name="qSeq" value="${answerDetail.qSeq}">
											<input type="hidden" name="qStatus" value="${qnaDetail.qStatus}">
											<button type="submit" class="go-delete">삭제</button>
											<button type="button" class="stay-page"
												onclick="closeDeleteAnswerModal()">취소</button>
										</form>
									</div>
								</div>
							</div>
						</c:if>
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
function openDeleteQnaModal() {
    document.getElementById('delete-qna-modal').style.display = 'block';
}

function closeDeleteQnaModal() {
    document.getElementById('delete-qna-modal').style.display = 'none';
}

function openDeleteAnswerModal() {
    document.getElementById('delete-answer-modal').style.display = 'block';
}

function closeDeleteAnswerModal() {
    document.getElementById('delete-answer-modal').style.display = 'none';
}
</script>


</html>
