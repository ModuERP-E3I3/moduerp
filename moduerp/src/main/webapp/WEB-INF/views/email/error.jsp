<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>오류 발생</title>
    <style>
        body, html {
            height: 100%;
            margin: 0;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #f9f9f9;
        }
        .error-container {
            width: 60%;
            max-width: 800px;
            padding: 30px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .error-title {
            font-size: 24px;
            font-weight: bold;
            color: #d9534f;
            margin-bottom: 20px;
        }
        .error-message {
            font-size: 16px;
            color: #333;
            margin-bottom: 30px;
        }
        .nav-buttons {
            margin-top: 20px;
        }
        .nav-buttons a {
            text-decoration: none;
            color: white;
            background-color: #0275d8;
            padding: 10px 20px;
            border-radius: 5px;
            margin: 0 10px;
            transition: background-color 0.3s ease;
        }
        .nav-buttons a:hover {
            background-color: #025aa5;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <%-- 에러 제목 --%>
        <div class="error-title">오류가 발생했습니다</div>
        
        <%--오류 메시지 표시 --%>
        <div class="error-message">
            <c:choose>
            	<%-- 컨트롤러에서 전달된 오류 메시지가 있을 경우 --%>
                <c:when test="${not empty errorMessage}">
                    <c:out value="${errorMessage}" />
                </c:when>
                <%-- 기본 오류 메시지 --%>
                <c:otherwise>
                    요청을 처리하는 동안 문제가 발생했습니다. 잠시 후 다시 시도해 주세요.
                </c:otherwise>
            </c:choose>
        </div>

		<%--네비게이션 버튼 --%>
        <div class="nav-buttons">
            <a href="javascript:history.back()">이전 페이지로</a>
            <a href="<c:url value='/' />">홈으로 이동</a>
        </div>
    </div>
</body>
</html>
