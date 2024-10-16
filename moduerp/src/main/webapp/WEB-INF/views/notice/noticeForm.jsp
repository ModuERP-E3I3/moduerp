<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ModuERP</title>

    <style type="text/css">
        /* 전체 페이지 기본 스타일 */
        body, html {
            margin: 0;
            padding: 0;
            font-family: 'Helvetica Neue', Arial, sans-serif;
            background-color: #f4f4f4;
            height: 100%;
            display: flex;
            flex-direction: column;
        }

        /* 메뉴바를 위해 상단 여백 추가 */
        .container {
            width: 60%;
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            margin: 100px auto; /* 메뉴바 공간 확보를 위한 여백 */
            box-sizing: border-box;
        }

        /* 메뉴바 스타일 */
        .menubar {
            position: fixed;
            top: 0;
            width: 100%;
            background-color: #333;
            color: white;
            padding: 10px;
            text-align: center;
            z-index: 1000; /* 메뉴바가 다른 요소 위에 표시되도록 설정 */
        }

        /* 제목 스타일 */
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        /* 레이블 스타일 */
        label {
            display: block;
            margin: 15px 0 5px;
            font-weight: bold;
            font-size: 14px;
            color: #333;
        }

        /* 텍스트 입력 필드와 텍스트에어리어 스타일 */
        input[type="text"], textarea, input[type="date"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-bottom: 20px;
            font-size: 16px;
            box-sizing: border-box;
        }

        /* 첨부 파일 입력 */
        input[type="file"] {
            margin-bottom: 20px;
        }

        /* 버튼 스타일 */
        button {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: white;
            border: none;
            font-size: 18px;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
        }

        button:hover {
            background-color: #0056b3;
        }

        /* 푸터 스타일 */
        footer {
            background-color: #2c3e50;
            color: #ecf0f1;
            text-align: center;
            padding: 20px 0;
            width: 100%;
            box-sizing: border-box;
            margin-top: auto;
        }

    </style>

</head>
<body>

<!-- 메뉴바 임포트 -->
<div class="menubar">
    <c:import url="/WEB-INF/views/common/menubar.jsp"/>
</div>

<div class="container">
    <h1>공지사항 ${notice.noticeId != null ? '수정' : '등록'}</h1>
    <form action="${pageContext.request.contextPath}/notice/${notice.noticeId != null ? 'edit' : 'add'}.do" method="post" enctype="multipart/form-data">
        <!-- 공지사항 ID (수정 시 필요) -->
        <c:if test="${notice.noticeId != null}">
            <input type="hidden" name="noticeId" value="${notice.noticeId}">
        </c:if>
        
        <!-- 제목 입력 -->
        <label for="title">제목</label>
        <input type="text" id="title" name="title" value="${notice.title}" required>
        
        <!-- 본문 입력 -->
        <label for="body">내용</label>
        <textarea id="body" name="body" rows="8" required>${notice.body}</textarea>
        
        <!-- 첨부 파일 입력 -->
        <label for="attachment">첨부파일</label>
        <input type="file" id="attachment" name="attachment" value="${notice.attachment}">
        
        <!-- 제출 버튼 -->
        <button type="submit">${notice.noticeId != null ? '공지사항 수정' : '공지사항 등록'}</button>
    </form>
</div>

<!-- 푸터 임포트 -->
<footer>
    <c:import url="/WEB-INF/views/common/footer.jsp"/>
</footer>

</body>
</html>
