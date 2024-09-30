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

        /* 전체 헤더 스타일 */
        header {
            width: 100%; /* 헤더 전체 너비 */
            height: 60px;
            background: #ffffff;
            color: #000000;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            position: fixed;
            top: 0;
            left: 0;
            z-index: 100;
            display: flex;
            justify-content: center; /* 내부 요소 중앙 배치 */
            align-items: center;
        }

        /* 내부 컨테이너 스타일 */
        .header-container {
            width: 70%; /* 내부 컨텐츠를 70%로 설정하여 중앙 배치 */
            display: flex;
            justify-content: space-between; /* 로고와 텍스트 양쪽에 배치 */
            align-items: center;
        }

        /* ModuERP 로고 스타일 */
        .header-container .logo {
            font-size: 24px;
            font-weight: bold;
            text-decoration: none;
            color: #217770;
        }

        /* 우측 텍스트 스타일 */
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

        /* 기존 로그인 페이지 스타일 */
        body {
            background-color: #223D38;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 100px; /* 헤더 높이와의 간격을 조정 */
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
    <!-- 상단 고정된 헤더 -->
    <header>
        <!-- 내부 컨텐츠를 위한 중앙 배치 div -->
        <div class="header-container">
            <a href="#" class="logo">ModuERP</a>
            <div class="signup-text">
                사장님의 첫 방문이시라면?	 <a href="signup.do">회원가입하기</a>
            </div>
        </div>
    </header>

    <!-- 기존 로그인 페이지 컨텐츠 -->
    <div class="container">
        <h1>로그인</h1>
        <form id="signinForm">
            <!-- Step 1: 사업자번호 -->
            <div class="form-group">
                <label for="bizNumber" class="active">사업자번호</label>
                <input type="text" id="bizNumber" placeholder="사업자번호 입력 (10자리)" maxlength="10" class="active" required>
                <span id="bizNumberError" class="error"></span>
            </div>

            <!-- Step 2: 승인코드 -->
            <div class="form-group">
                <label for="approvalCode">승인코드</label>
                <input type="text" id="approvalCode" placeholder="승인코드 입력 (6자리)" maxlength="6" required>
                <span id="approvalCodeError" class="error"></span>
            </div>

            <!-- Step 3: 이메일 -->
            <div class="form-group">
                <label for="email">이메일</label>
                <input type="email" id="email" placeholder="이메일 입력" required>
                <span id="emailError" class="error"></span>
            </div>

            <!-- Step 4: 암호 -->
            <div class="form-group">
                <label for="password">암호</label>
                <input type="password" id="password" placeholder="암호 입력 (8자 이상)" minlength="8" required>
                <span id="passwordError" class="error"></span>
            </div>

            <!-- Continue 버튼 -->
            <button type="button" class="btn" id="continueBtn" onclick="validateLogin()">Continue</button>
        </form>
    </div>

    <script>
        // 기존 폼 유효성 검사 스크립트 유지
        document.getElementById("bizNumber").oninput = function () {
            const bizNumber = document.getElementById("bizNumber").value;
            if (/^\d{10}$/.test(bizNumber)) {
                document.getElementById("bizNumberError").textContent = "";
                document.getElementById("approvalCode").classList.add("active");
                document.querySelector("label[for='approvalCode']").classList.add("active");
            } else {
                document.getElementById("bizNumberError").textContent = "유효한 10자리 사업자번호를 입력하세요.";
            }
        };

        document.getElementById("approvalCode").oninput = function () {
            const approvalCode = document.getElementById("approvalCode").value;
            if (/^\d{6}$/.test(approvalCode)) {
                document.getElementById("approvalCodeError").textContent = "";
                document.getElementById("email").classList.add("active");
                document.querySelector("label[for='email']").classList.add("active");
            } else {
                document.getElementById("approvalCodeError").textContent = "유효한 6자리 승인코드를 입력하세요.";
            }
        };

        document.getElementById("email").oninput = function () {
            const email = document.getElementById("email").value;
            if (validateEmail(email)) {
                document.getElementById("emailError").textContent = "";
                document.getElementById("password").classList.add("active");
                document.querySelector("label[for='password']").classList.add("active");
            } else {
                document.getElementById("emailError").textContent = "유효한 이메일 주소를 입력하세요.";
            }
        };

        document.getElementById("password").oninput = function () {
            const password = document.getElementById("password").value;
            if (password.length >= 8) {
                document.getElementById("passwordError").textContent = "";
                document.getElementById("continueBtn").classList.add("active");
            } else {
                document.getElementById("passwordError").textContent = "암호는 최소 8자 이상이어야 합니다.";
            }
        };

        function validateLogin() {
            alert("로그인 성공!");
        }

        function validateEmail(email) {
            const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            return re.test(email);
        }
    </script>
</body>
</html>
