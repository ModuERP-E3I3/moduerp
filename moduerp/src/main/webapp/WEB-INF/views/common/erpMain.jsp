<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>erpMain</title>

<style type="text/css">
    /* 하얀 박스 스타일 */
    .content-box {
        width: 98%; /* 화면에 가득 차지 않게 */
        height: 78vh; /* 화면 높이의 78% */
        background-color: white;
        margin-left: 1%;
        margin-top: 6%;
        border: 1px solid #ccc;
        border-radius: 20px; /* 박스 둥글게 */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
        position: relative;
    }

    /* 제목 스타일 */
    .content-title {
        position: absolute;
        top: -40px;
        left: 20px;
        font-size: 24px;
        color: white; 
        font-weight: bold;
    }
</style>



<!--   <script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
function movePage() {
    location.href = "loginPage.do";
}
</script>
-->


</head>

<body>
    <!-- 서브헤더 JSP 임포트 -->
    <c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

    <!-- 하얀 큰 박스 -->
    <div class="content-box">

        <div class="content-title">메인페이지</div>

        <!-- 이 안에 내용 넣을 거임!! -->    
        
        
    </div>
</body>
</html>