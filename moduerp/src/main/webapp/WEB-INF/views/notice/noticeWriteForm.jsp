<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항 작성</title>
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
            margin: 0 auto;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        h1 {
            font-size: 24px;
            margin-bottom: 30px;
            text-align: center;
        }

        .form-box {
            max-width: 80%;
            width: 100%;
            background-color: white;
            padding: 30px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        label {
            font-size: 16px;
            font-weight: bold;
            margin-bottom: 10px;
            display: block;
        }

        input[type="text"], textarea {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 4px;
            margin-bottom: 20px;
            box-sizing: border-box;
        }

        input[type="checkbox"] {
            margin-left: 10px;
        }

        button {
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 4px;
            font-size: 16px;
        }

        button:hover {
            background-color: #0056b3;
        }

        .form-footer {
            margin-top: 30px;
            text-align: right;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>공지사항 작성</h1>

    <div class="form-box">
        <form action="noticeWrite.do" method="post">
            <label for="title">제목</label>
            <input type="text" id="title" name="noticeTitle" required>

            <label for="body">내용</label>
            <textarea id="body" name="noticeBody" rows="10" cols="50" required></textarea>

            <label for="imp">중요 공지 여부</label>
            <input type="checkbox" id="imp" name="noticeImp" value="Y"> 중요

            <div class="form-footer">
                <button type="submit">등록</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
