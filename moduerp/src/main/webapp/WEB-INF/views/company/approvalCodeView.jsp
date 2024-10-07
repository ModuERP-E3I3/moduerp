<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>승인코드 확인</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
            font-family: 'Arial', sans-serif;
            background: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .code-container {
            width: 400px;
            background: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .approval-code {
            font-size: 36px;
            font-weight: bold;
            color: #333;
            margin: 20px 0;
        }
        .warning {
            color: red;
            margin-bottom: 20px;
        }
        .btn {
            padding: 10px 20px;
            background: #4CAF50;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s ease;
            margin-top: 20px;
        }
        .btn:hover {
            background: #45a049;
        }
    </style>
    <script>
        // 승인코드를 클립보드에 복사하는 함수
        function copyToClipboard() {
            var copyText = document.getElementById("approvalCode");
            navigator.clipboard.writeText(copyText.innerText)
                .then(() => alert("승인코드가 클립보드에 복사되었습니다!"))
                .catch(err => console.error("복사 실패:", err));
        }
    </script>
</head>
<body>
<div class="code-container">
    <h1>회원가입 완료</h1>
    <p class="warning">이 승인번호를 기억하세요! 다시 조회할 수 없습니다.</p>
    <div class="approval-code" id="approvalCode">${approvalCode}</div>
    <button class="btn" onclick="copyToClipboard()">승인코드 복사</button>
    <form action="signin.do" style="margin-top: 20px;">
        <button type="submit" class="btn">로그인하러 가기</button>
    </form>
</div>
</body>
</html>
