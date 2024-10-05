<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
            font-family: 'Arial', sans-serif;
        }

        header {
            width: 100%;
            height: 60px;
            background: #ffffff;
            color: #000000;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            position: fixed;
            top: 0;
            left: 0;
            z-index: 100;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .header-container {
            width: 70%;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .header-container .logo {
            font-size: 24px;
            font-weight: bold;
            text-decoration: none;
            color: #217770;
        }

        .header-container .signup-text {
            font-size: 14px;
            color: #333333;
        }

        .header-container .signup-text a {
            color: #217770;
            text-decoration: none;
            font-weight: bold;
        }

        .header-container .signup-text a:hover {
            text-decoration: underline;
        }

        body {
            background-color: #223D38;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 100px;
        }

        .container {
            background: rgba(255, 255, 255, 0.1);
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            width: 350px;
            text-align: center;
            color: #ffffff;
        }

        h1 {
            font-weight: 300;
            margin-bottom: 30px;
        }

        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }

        .form-group input {
            width: calc(100% - 20px);
            padding: 10px;
            border: none;
            border-radius: 5px;
            margin-bottom: 5px;
            display: none;
        }

        .form-group input.active {
            display: block;
        }

        .form-group label {
            margin-bottom: 5px;
            display: none;
        }

        .form-group label.active {
            display: block;
        }

        .error {
            color: red;
            font-size: 12px;
        }

        .btn {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: #ffffff;
            cursor: pointer;
            margin-top: 15px;
            display: none;
        }

        .btn.active {
            display: block;
        }

        .btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <header>
        <div class="header-container">
            <a href="main.do" class="logo">ModuERP</a>
            <div class="signup-text">
                사장님의 첫 방문이시라면? <a href="signup.do">회원가입하기</a>
            </div>
        </div>
    </header>

<div class="container">
    <h1>로그인</h1>

    <% if (request.getAttribute("errorMessage") != null) { %>
        <div style="color: red; text-align: center; margin-bottom: 15px;">
            <%= request.getAttribute("errorMessage") %>
        </div>
    <% } %>

    <form id="signinForm" action="login.do" method="POST">
        <div class="form-group">
            <label for="bizNumber" class="active">사업자번호</label>
            <input type="text" id="bizNumber" name="bizNumber" placeholder="사업자번호 입력 (10자리)" maxlength="10" class="active" required>
            <span id="bizNumberError" class="error"></span>
        </div>
        <div class="form-group">
            <label for="approvalCode">승인코드</label>
            <input type="text" id="approvalCode" name="approvalCode" placeholder="승인코드 입력 (6자리)" maxlength="6" required>
            <span id="approvalCodeError" class="error"></span>
        </div>
        <div class="form-group">
            <label for="email">이메일</label>
            <input type="email" id="email" name="empEmail" placeholder="이메일 입력" required>
            <span id="emailError" class="error"></span>
        </div>
        <div class="form-group">
            <label for="password">암호</label>
            <input type="password" id="password" name="password" placeholder="암호 입력 (8자 이상)" minlength="8" required>
            <span id="passwordError" class="error"></span>
        </div>
    </form>
</div>

<script>
    const bizNumberInput = document.getElementById("bizNumber");
    const approvalCodeInput = document.getElementById("approvalCode");
    const emailInput = document.getElementById("email");
    const passwordInput = document.getElementById("password");
    const signinForm = document.getElementById("signinForm");

    bizNumberInput.oninput = function () {
        if (/^\d{10}$/.test(bizNumberInput.value)) {
            document.getElementById("bizNumberError").textContent = "";
            approvalCodeInput.classList.add("active");
            document.querySelector("label[for='approvalCode']").classList.add("active");
            approvalCodeInput.focus();
        } else {
            document.getElementById("bizNumberError").textContent = "유효한 10자리 사업자번호를 입력하세요.";
        }
    };

    approvalCodeInput.oninput = function () {
        if (/^\d{6}$/.test(approvalCodeInput.value)) {
            document.getElementById("approvalCodeError").textContent = "";
            emailInput.classList.add("active");
            document.querySelector("label[for='email']").classList.add("active");
            emailInput.focus();
        } else {
            document.getElementById("approvalCodeError").textContent = "유효한 6자리 승인코드를 입력하세요.";
        }
    };

    emailInput.oninput = function () {
        if (validateEmail(emailInput.value)) {
            document.getElementById("emailError").textContent = "";
            passwordInput.classList.add("active");
            document.querySelector("label[for='password']").classList.add("active");
            passwordInput.focus();
        } else {
            document.getElementById("emailError").textContent = "유효한 이메일 주소를 입력하세요.";
        }
    };

    passwordInput.oninput = function () {
        if (passwordInput.value.length >= 8) {
            document.getElementById("passwordError").textContent = "";
            signinForm.submit(); // 자동으로 폼 제출
        } else {
            document.getElementById("passwordError").textContent = "암호는 최소 8자 이상이어야 합니다.";
        }
    };

 	// 이메일 유효성 검사 함수
    function validateEmail(email) {
    	// 정규표현식: [앞부분]@[도메인].[최상위도메인](예: .com, .net 등)
        const re = /^[^\s@]+@[^\s@]+\.[A-Za-z]{2,}$/;
        return re.test(email);
    }
</script>
</body>
</html>
