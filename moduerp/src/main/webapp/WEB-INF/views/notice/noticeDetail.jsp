<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Notice Detail</title>
    <style type="text/css">
       body {
           font-family: 'Arial', sans-serif;
           margin: 0;
           padding: 0;
           background-color: white;
           display: flex;
           flex-direction: column;
           align-items: center;
           justify-content: space-between; /* 컨텐츠와 푸터 사이 공간 확보 */
           min-height: 100vh; /* 최소 높이를 100% 설정 */
       }

       .container {
           width: 60%;
           display: flex;
           flex-direction: column;
           margin-top: 100px; /* 상단 여백 추가 */
           flex-grow: 1; /* 남은 공간을 차지하게 함 */
       }

       h1, h2, .meta, .content {
           text-align: left; /* 왼쪽 정렬 */
       }

       h1 {
           font-size: 35px;
           margin-bottom: 20px;
       }

       h2 {
           font-size: 24px;
           margin-bottom: 10px;
       }

       .meta {
           font-size: 14px;
           color: #777;
           margin-bottom: 20px;
           margin-left:5px;
       }

       .content {
           font-size: 16px;
           color: #333;
           line-height: 1.6;
           margin-top: 20px;
       }

       .button-group {
           display: flex;
           gap: 10px;
           margin-top: 30px;
       }

       .back-btn {
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

       footer {
           width: 100%;
           background-color: white;
           text-align: center;
           padding: 20px 0;
           border-top: 1px solid #ddd;
           position: fixed; /* 푸터를 화면 하단에 고정 */
           bottom: 0;
           left: 0;
       }

    </style>
    <script type="text/javascript">
        function goBack() {
            window.history.back(); // 뒤로 가기 기능
        }
    </script>
</head>
<body>

<!-- 메뉴바 임포트 -->
   <div class="menubar">
        <c:import url="/WEB-INF/views/common/menubar.jsp" />
    </div>

<div class="container">
    <h1>공지사항</h1>
    <h2>${notice.title}</h2>
    <div class="meta">
        <fmt:formatDate value="${notice.noticeDate}" pattern="yyyy-MM-dd" />
    </div>
    <div class="content">
        ${notice.body}
    </div>
</div>

<div class="button-group">
    <a href="javascript:goBack();" class="back-btn">목록으로 돌아가기</a> <!-- 자바스크립트로 뒤로 가기 -->
    <a href="noticeUpdateForm.do?noticeId=${notice.noticeId}" class="back-btn">수정</a>
    <a href="noticeDelete.do?noticeId=${notice.noticeId}" class="back-btn">삭제</a>
</div>

<!-- 푸터 임포트 -->
<footer>
    <c:import url="/WEB-INF/views/common/footer.jsp" />
</footer>

</body>
</html>
