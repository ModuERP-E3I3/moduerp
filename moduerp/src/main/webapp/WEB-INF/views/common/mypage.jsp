<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ModuERP</title>

<style type="text/css">

/* 전체 페이지 기본 스타일 */
body, html {
	margin-top: 2% ;
    font-family: 'Helvetica Neue', Arial, sans-serif;
    padding: 0;
    background-color: #f4f4f4;
}

/* 구분선 스타일 */
hr {
    border: none;
    border-top: 1px solid #ddd;
    margin: 40px 0;
}

/* 이미지 컨테이너 스타일 */
div#image-container {
    display: flex;
    justify-content: center; /* 중앙 정렬 */
    align-items: center;
    gap: 200px; /* 이미지 사이 간격 */
    background-color: white ;
    box-shadow: 0 12px 24px rgba(0, 0, 0, 0.2); /* 입체감 설정 */
    border-radius: 20px; 
    margin: 40px auto; /* 페이지 중앙 정렬 */
    width: 100%; 
    height: 700px;
    max-width: 2400px; 
}

div.image-wrapper {
    position: relative;
    text-align: center;
}

/* 이미지 스타일 */
div.image-wrapper img {
    width: 800px;
    height: auto; 
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3); /* 입체감 설정 */
    border-radius: 20px; /* 모서리 둥글게 */
    transition: transform 0.3s ease, box-shadow 0.3s ease; /* 호버 시 확대 및 그림자 변화 */
}

/* 이미지 호버 시 확대 효과 */
div.image-wrapper img:hover {
    transform: scale(1.05);
    box-shadow: 0 15px 30px rgba(0, 0, 0, 0.4); /* 호버 시 그림자 더 진하게 */
}

/* 이미지 위 둥근 모서리 사각형 텍스트 스타일 */
div.image-wrapper .image-label {
    position: absolute;
    top: 20px; /* 이미지의 상단에서 20px 아래에 배치 */
    left: 50%;
    transform: translateX(-50%);
    background-color: rgba(0, 0, 0, 0.6); /* 반투명 검정 배경 */
    color: white;
    font-size: 25px;
    font-weight: bold;
    padding: 10px 20px; /* 텍스트 주변 여백 설정 */
    border-radius: 15px; 
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5); /* 텍스트 그림자 */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);   /* 사각형에 그림자 */
}

</style>

<script type="text/javascript">
function movePage(){
	location.href = "loginPage.do";
}
</script>

</head>
<body>



<!-- 메뉴바 임포트 -->
<c:import url="/WEB-INF/views/common/menubar.jsp">
    <c:param name="loginUUID" value="${loginUUID}" />
</c:import>



<!-- 푸터 임포트 -->
<c:import url="/WEB-INF/views/common/footer.jsp" />
</body>
</html>

