<!-- /WEB-INF/views/common/mypage.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ModuERP</title>
    
    <!-- Font Awesome 포함 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    
    <style type="text/css">
        /* 전체 페이지 기본 스타일 */
   body, html {
	margin-top: 2% ;
    font-family: 'Helvetica Neue', Arial, sans-serif;
    padding: 0;
    background-color: #f4f4f4;
}

        
        /* 레이아웃 컨테이너 */
        .container {
            display: flex;
            min-height: calc(100vh - 60px); /* 상단 메뉴바 높이 고려 */
        }
        
        /* 왼쪽 사이드바 스타일 */
        .sidebar {
            width: 250px;
            background-color: #2c3e50;
            padding-top: 20px;
            box-shadow: 2px 0 5px rgba(0,0,0,0.1);
        }
        
        .sidebar ul {
            list-style-type: none;
            padding: 0;
        }
        
        .sidebar ul li {
            padding: 15px 20px;
        }
        
        .sidebar ul li a {
            color: #ecf0f1;
            text-decoration: none;
            display: block;
            transition: background 0.3s;
        }
        
        .sidebar ul li a:hover {
            background-color: #34495e;
            border-radius: 4px;
        }
        
        /* 활성화된 메뉴 항목 스타일 */
        .sidebar ul li a.active {
            background-color: #1abc9c; /* 활성화 시 배경색 */
            color: white; /* 활성화 시 텍스트 색상 */
            border-radius: 4px;
        }
        
        /* 활성화된 메뉴 항목의 아이콘 색상 변경 */
        .sidebar ul li a.active i {
            color: white; /* 아이콘 색상을 흰색으로 변경 */
        }
        
        /* 메인 콘텐츠 영역 스타일 */
       .main-content {
    flex: 1;
    padding: 40px;
    background-color: #fff;
    overflow-y: auto;
    margin-top: 20px; /* 원하는 값으로 조정 */
}

        
        /* 푸터 스타일 */
        footer {
            text-align: center;
            padding: 20px;
            background-color: #2c3e50;
            color: #ecf0f1;
            position: fixed;
            width: 100%;
            bottom: 0;
        }
        
        /* 반응형 디자인 */
        @media (max-width: 768px) {
            .container {
                flex-direction: column;
            }
        
            .sidebar {
                width: 100%;
                box-shadow: none;
            }
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
    <c:import url="/WEB-INF/views/common/menubar.jsp" />

    <!-- 레이아웃 컨테이너 -->
    <div class="container">
        <!-- 왼쪽 사이드바 -->
        <div class="sidebar">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/passwordManagement.do">
                        <i class="fas fa-key"></i> 비밀번호 관리
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/cardManagement.do">
                        <i class="fas fa-credit-card"></i> 카드 관리
                    </a>
                </li>
            </ul>
        </div>
        
        <!-- 메인 콘텐츠 영역 -->
        <div class="main-content">
            <jsp:include page="${contentPage}" />
        </div>
    </div>

    <!-- 푸터 임포트 -->
    <c:import url="/WEB-INF/views/common/footer.jsp" />
</body>
</html>