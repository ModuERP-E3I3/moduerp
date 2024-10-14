<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    max-width: 80%;  /* 본문 내용은 80% 너비로 */
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

            <!-- <div class="search-bar">
                <select name="category" id="category">
                    <option value="전체">종류 전체</option>
                    <option value="중요">중요</option>
                    <option value="일반">일반</option>
                </select>
                <select name="category" id="category">
                    <option value="전체">조건 선택</option>
                    <option value="제목">제목</option>
                    <option value="내용">내용</option>
                    <option value="제목+내용">제목+내용</option>
                </select>

                <input type="text" placeholder="내용">
                <button type="submit">검색</button>
            </div> -->

            <hr>

            <!-- 동적으로 공지사항 리스트 출력 -->
            <ul class="QNA-list">
                <c:forEach var="qna" items="${qnaList}">
                    <%-- <li class="<c:if test='${notice.noticeImp == "Y"}'>important</c:if>"> --%>
                    <li>
                        <a href="qnaDetail.do?qSeq=${qna.qSeq}">${qna.qTitle}</a>
                        <div class="meta">
                        	<span>${qna.empName}</span>
                            <span>No.${qna.qSeq}</span>
                            <span>작성일 : <fmt:formatDate value="${qna.qDate}" pattern="yyyy-MM-dd" /></span>
                            <%-- <span>조회수 : ${notice.viewCnt}</span> --%>
                        </div>
                    </li>
                </c:forEach>
            </ul>

             <!-- 페이지네이션 -->
            <%-- <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="noticeList.do?page=${currentPage - 1}" class="page-btn">이전</a>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="noticeList.do?page=${i}" class="page-btn ${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <a href="noticeList.do?page=${currentPage + 1}" class="page-btn">다음</a>
                </c:if>
            </div> --%>

            <!-- 작성 버튼 -->
            <!-- <a href="noticeWriteForm.do" class="action-btn">작성</a -->>
        </div>
    </div>
</div>

<!-- 푸터 -->
<footer>
    <c:import url="/WEB-INF/views/common/footer.jsp" />
</footer>

</body>
</html>
