<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first</title>
<style type="text/css">
body {
    background-color: #f0f4f8;
    font-family: 'Arial', sans-serif;
    margin: 0;
    padding: 0;
}

h1 {
    font-size: 48pt;
    color: #2c3e50;
    text-align: center;
    margin-top: 50px;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

div {
    width: 500px;
    height: 250px;
    border: 2px solid #2c3e50;
    border-radius: 10px;
    background-color: #ecf0f1;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    position: relative;
    left: 50%;
    transform: translateX(-50%);
    padding: 20px;
    margin-top: 50px;
}

div form {
    font-size: 16pt;
    color: #34495e;
    font-weight: bold;
    margin: 10px;
    padding: 10px;
}

div#loginForm form input.pos {
    position: relative;
    left: 0;
    width: 90%; /* 가로폭 줄임 */
    height: 30px;
    margin: 10px 0;
    border: 1px solid #bdc3c7;
    border-radius: 10px; /* 둥근 모서리 */
    padding-left: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    font-size: 14pt;
}

div#loginForm form input[type=submit] {
    width: 90%; /* 가로폭 줄임 */
    height: 45px;
    margin: 20px 0;
    background-color: #2c3e50;
    color: white;
    border: none;
    border-radius: 10px; /* 둥근 모서리 */
    cursor: pointer;
    font-size: 16pt;
    font-weight: bold;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    transition: background-color 0.3s ease, transform 0.2s ease;
}

div#loginForm form input[type=submit]:hover {
    background-color: #1a5e70; /* 호버시 더 진한 색상 */
    transform: scale(1.05);
}

div#loginForm form label {
    display: block;
    margin-bottom: 10px;
    font-size: 14pt;
    color: #2c3e50;
}
</style>
</head>
<body>
<h1>로그인</h1>
<div id="loginForm">    
    <form action="login.do" method="post">
        <label>아이디 : <input type="text" name="userId" id="uerid" class="pos"></label> <br>
        <label>암 호 : <input type="password" name="userPwd" id="userpwd" class="pos"></label> <br>
        <input type="submit" value="로그인">
    </form>
</div>
</body>
</html>
