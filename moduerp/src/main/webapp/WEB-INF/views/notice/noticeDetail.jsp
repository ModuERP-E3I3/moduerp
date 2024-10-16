<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- fn 라이브러리 추가 -->
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
           min-height: 100vh; /* 최소 높이를 100% 설정 */
       }

       .container {
           width: 60%;
           margin: 100px auto 0; /* 상단 여백 추가, 중앙 정렬 */
           flex-grow: 1; /* 남은 공간을 차지하게 함 */
           padding-left: 20px; /* 좌측 여백 */
       }

       h1, h2, .meta, .content {
           text-align: left; /* 왼쪽 정렬 */
       }
       
       h1 {
           font-size: 45px;
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
           margin-left: 5px;
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

       .back-btn, .edit-btn, .delete-btn {
           padding: 10px 15px;
           background-color: #007bff;
           color: white;
           border: none;
           cursor: pointer;
           text-decoration: none;
           border-radius: 4px;
       }

       .back-btn:hover, .edit-btn:hover, .delete-btn:hover {
           background-color: #0056b3;
       }

       footer {
           width: 100%;
           background-color: white;
           text-align: center;
           padding: 20px 0;
           border-top: 1px solid #ddd;
           margin-top: auto; /* 푸터가 컨텐츠 아래에 배치되도록 설정 */
       }

    </style>
    <script type="text/javascript">
        function goBack() {
            window.history.back(); // 뒤로 가기 기능
        }

        function deleteNotice(deleteUrl) {
            if (confirm('정말로 삭제하시겠습니까?')) {
                fetch(deleteUrl, {
                    method: 'POST',
                })
                .then(response => {
                    if (response.ok) {
                        alert('삭제되었습니다.');
                        window.location.href = '<c:url value="/notice/list.do" />';
                    } else {
                        alert('삭제에 실패했습니다.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('삭제 처리 중 오류가 발생했습니다.');
                });
            }
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

    <!-- 첨부파일 출력 (언더스코어 뒤의 부분만 표시) -->
<c:if test="${not empty notice.attachment}">
    <div class="meta">
        <!-- 언더스코어 뒤의 파일명만 출력 -->
        <a href="<c:url value='/resources/templates/email_files/${notice.attachment}' />" download="${fn:substringAfter(notice.attachment, '_')}">
            ${fn:substringAfter(notice.attachment, '_')}
        </a>
    </div>
</c:if>


    <div class="content">
        ${notice.body}
    </div>

<div class="button-group">
    <a href="javascript:goBack();" class="back-btn">목록으로 돌아가기</a>
    <a href="<c:url value='/notice/edit/${notice.noticeId}.do' />" class="edit-btn">수정</a>
    <button class="delete-btn" onclick="deleteNotice('<c:url value='/notice/delete/${notice.noticeId}.do' />')">삭제</button>
</div>

</div>


<!-- 푸터 임포트 -->
<footer>
    <c:import url="/WEB-INF/views/common/footer.jsp" />
</footer>

</body>
</html>
