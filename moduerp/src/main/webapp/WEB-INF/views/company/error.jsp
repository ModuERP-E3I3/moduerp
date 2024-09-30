<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>에러 페이지</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
            font-family: 'Arial', sans-serif;
            background: #f8d7da;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .error-container {
            width: 500px;
            background: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            border: 1px solid #f5c2c7;
        }

        h1 {
            font-size: 28px;
            margin-bottom: 20px;
            color: #721c24;
        }

        .error-message {
            font-size: 16px;
            color: #721c24;
            margin-bottom: 30px;
        }

        .btn {
            width: 150px;
            padding: 10px;
            background: #c82333;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
        }

        .btn:hover {
            background: #a71d2a;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>오류 발생</h1>
        <div class="error-message">
            <p>처리 중 오류가 발생했습니다. 아래 메시지를 확인하세요.</p>
            <p><b>${message}</b></p> <!-- 에러 메시지 출력 -->
        </div>
        <a href="<c:url value='/home.do' />" class="btn">홈으로 돌아가기</a>
    </div>
</body>
</html>
