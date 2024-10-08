<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항 수정</title>
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

        .notice-form {
            width: 100%;
            max-width: 80%; /* 본문 내용은 80% 너비로 */
            background: white;
            margin-top: 30px;
            padding: 30px 0; /* 패딩 추가 */
            text-align: center;
        }

        .box {
            width: 100%;
            margin: 0 auto;
        }

        h2 {
            font-size: 24px;
            margin-bottom: 30px;
        }

        .form-group {
            margin-bottom: 20px;
            text-align: left;
            width: 100%;
        }

        .form-group label {
            font-size: 16px;
            margin-bottom: 5px;
            display: block;
        }

        .form-group input[type="text"],
        .form-group textarea {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .form-group input[type="checkbox"] {
            margin-right: 5px;
        }

        .button-group {
            display: flex;
            justify-content: flex-end; /* 오른쪽 정렬 */
            gap: 10px; /* 버튼 사이 간격 */
            margin-top: 30px;
        }

        .submit-btn, .cancel-btn {
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            text-decoration: none;
            border-radius: 4px;
        }

        .submit-btn:hover, .cancel-btn:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>

<div class="container">
    <div class="bankmg-form">
        <div class="box">
            <h2>공지사항 수정</h2>

            <form action="noticeUpdate.do" method="post">
                <input type="hidden" name="noticeId" value="${notice.noticeSeq}">

                <div class="form-group">
                    <label for="title">제목</label>
                    <input type="text" id="title" name="noticeTitle" value="${notice.noticeTitle}" required>
                </div>

                <div class="form-group">
                    <label for="body">내용</label>
                    <textarea id="body" name="noticeBody" rows="10" cols="50" required>${notice.noticeBody}</textarea>
                </div>

                <div class="form-group">
                    <label for="imp">중요 공지 여부</label>
                    <input type="checkbox" id="imp" name="noticeImp" value="Y" <c:if test="${notice.noticeImp == 'Y'}">checked</c:if>> 중요
                </div>

                <div class="button-group">
                    <button type="submit" class="submit-btn">수정 완료</button>
                    <a href="noticeList.do" class="cancel-btn">취소</a>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
