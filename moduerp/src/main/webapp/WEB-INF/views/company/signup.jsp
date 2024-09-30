<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입 페이지</title>
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

        .signup-container {
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

        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .form-group input[type="password"] {
            margin-bottom: 10px;
        }

        .form-group a.download-template {
            display: block;
            text-decoration: none;
            color: #217770;
            margin-top: 5px;
            font-size: 12px;
        }

        .form-group input[type="file"] {
            border: none;
        }

        .btn {
            width: 100%;
            padding: 15px;
            background: #4CAF50;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        .btn:hover {
            background: #45a049;
        }
    </style>
</head>
<body>
    <div class="signup-container">
        <h1>회원가입</h1>
        <form id="signupForm" action="register.do" method="post" enctype="multipart/form-data">
            <!-- 사업자번호 입력 -->
            <div class="form-group">
                <label for="bizNumber">사업자번호</label>
                <input type="text" id="bizNumber" name="bizNumber" placeholder="사업자번호 입력 (10자리)" maxlength="10" required>
            </div>

            <!-- 회사명 입력 -->
            <div class="form-group">
                <label for="companyName">회사명</label>
                <input type="text" id="companyName" name="companyName" placeholder="회사명 입력" required>
            </div>

            <!-- 이메일 입력 -->
            <div class="form-group">
                <label for="email">이메일</label>
                <input type="email" id="email" name="email" placeholder="이메일 입력" required>
            </div>

            <!-- 암호 입력 -->
            <div class="form-group">
                <label for="password">암호</label>
                <input type="password" id="password" name="password" placeholder="암호 입력 (8자 이상)" minlength="8" required>
            </div>

            <!-- 엑셀 파일 첨부 -->
            <div class="form-group">
                <label for="fileUpload">부서 및 사원 정보 엑셀 파일 첨부</label>
                <input type="file" id="fileUpload" name="fileUpload" accept=".xls,.xlsx" required>
                 <span id="template-file" style="display: block; margin-top: 5px; font-size: 12px;">
        <a href="<c:url value='/resources/templates/부서직원파일양식.xlsx'/>" download="부서직원파일양식.xlsx">
            부서직원파일양식.xlsx
        </a>
    </span>
            </div>

            <!-- 회원가입 버튼 -->
            <button type="submit" class="btn">회원가입</button>
        </form>
    </div>

    <script>
        // 회원가입 버튼 클릭 시 폼 전송 및 로그인 페이지로 이동
        document.getElementById("signupForm").onsubmit = function () {
            alert("회원가입이 완료되었습니다!");
            window.location.href = "loginPage.do"; // 로그인 페이지로 이동
            return false; // 실제 서버 전송을 막고 페이지 이동만 처리
        };
    </script>
</body>
</html>
