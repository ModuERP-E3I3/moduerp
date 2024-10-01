<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NoticeList</title>
</head>
<body>

<!-- 메뉴바 임포트 -->
<c:import url="/WEB-INF/views/common/menubar.jsp" />

<h1>공지사항 리스트</h1>

<table align="center" width="500" border="1" cellspacing="0" cellpadding="0">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>첨부파일</th>
            <th>작성일</th>
        </tr>
        <!-- 공지사항 리스트 반복 출력 -->
        <c:forEach var="notice" items="${noticeList}">
            <tr>
                <td>${notice.noticeId}</td>
                <td><a href="noticeDetail.do?noticeId=${notice.noticeId}">${notice.title}</a></td>
                <td>${notice.writer}</td>
                <td>${notice.createdDate}</td>
            </tr>
        </c:forEach>
</table>

<!-- 페이지 네비게이션 -->
<div>
    <c:if test="${totalPages > 1}">
        <c:forEach var="i" begin="1" end="${totalPages}">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <strong>${i}</strong>
                </c:when>
                <c:otherwise>
                    <a href="noticeList.do?page=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </c:if>
</div>

<hr style="clear:both;">


<!-- 푸터 임포트 -->
<c:import url="/WEB-INF/views/common/footer.jsp" />

</body>
</html>