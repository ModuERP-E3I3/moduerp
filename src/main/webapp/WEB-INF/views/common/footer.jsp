<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Footer Example</title>
<style type="text/css">
    body {
        margin: 0;
        padding: 0;
        font-family: 'Helvetica Neue', Arial, sans-serif;
        background-color: #f4f4f4;
    }

    footer {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: flex-start;
        padding: 40px;
        background-color: #1c1c1c; /* Dark gray */
        color: #e0e0e0; /* Light gray */
        font-size: 0.9rem;
        border-top: 5px solid #002147; /* Accent line in navy */
    }

    footer a {
        color: #e0e0e0;
        text-decoration: none;
        font-weight: bold;
    }

    footer a:hover {
        text-decoration: underline;
    }

    footer p {
        margin: 10px 0;
        line-height: 1.6;
    }

    footer span {
        margin: 5px 0;
    }

    /* Flexbox for spacing and cleaner layout */
    footer .footer-content {
        display: flex;
        flex-direction: column;
        gap: 5px;
    }

    footer .footer-bottom {
        margin-top: 20px;
        border-top: 1px solid #555; /* Subtle top border for separation */
        padding-top: 10px;
        width: 100%;
        text-align: left;
        font-size: 0.8rem;
        color: #b0b0b0;
    }

    @media screen and (max-width: 768px) {
        footer {
            font-size: 0.8rem;
            padding: 20px;
        }
    }
</style>
</head>
<body>
<footer>
    <div class="footer-content">
        <p>&copy; 2024 <a href="mailto:copyright@test.org">copyright@test.org</a></p>
        <span>클라우드 AI 대화형 웹 애플리케이션 개발자 양성과정</span>
        <span>서울시 서초구 신논현동 ICT기술협회</span>
        <span>TEL: 02-1234-5678 | FAX: 02-1234-5679</span>
    </div>
    <div class="footer-bottom">
      최만경, 최은영, 최원준, 조현재, 박재윤, 이은지
    </div>
</footer>
</body>
</html>
