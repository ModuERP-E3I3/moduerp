<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Notice Detail</title>
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

       .notice-detail {
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

       .detail-header {
           margin-bottom: 30px;
           border-bottom: 2px solid #eee;
           padding-bottom: 10px;
       }

       .detail-header h3 {
           font-size: 22px;
           margin: 0;
       }

       .detail-meta {
           font-size: 14px;
           color: #777;
           margin-top: 10px;
       }

       .detail-meta span {
           margin-right: 20px;
       }

       .detail-content {
           text-align: left;
           font-size: 16px;
           color: #333;
           line-height: 1.6;
           margin-top: 20px;
       }

       .detail-content p {
           margin-bottom: 10px;
       }
       
       .button-group {
           display: flex;
           gap: 10px; /* 버튼 사이 간격 */
           margin-top: 30px;
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

       .back-btn:hover {
           background-color: #0056b3;
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

<!-- 메뉴바 임포트 -->
<c:import url="/WEB-INF/views/common/menubar.jsp" />

<h1>공지사항 상세 보기</h1>

<div>
    <h3>${notice.noticeTitle}</h3>
    <p>작성자: ${notice.uuID}</p>
    <p>작성일: <fmt:formatDate value="${notice.writeDate}" pattern="yyyy-MM-dd" /></p>
    <hr>
    <p>${notice.noticeBody}</p>
</div>

<div class="button-group">
    <a href="noticeList.do" class="back-btn">목록으로 돌아가기</a>
    <a href="noticeUpdateForm.do?noticeId=${notice.noticeSeq}" class="back-btn">수정</a>
    <a href="noticeDelete.do?noticeId=${notice.noticeSeq}" class="back-btn">삭제</a>
</div>

<!-- 푸터 임포트 -->
<c:import url="/WEB-INF/views/common/footer.jsp" />

</body>
</html>
