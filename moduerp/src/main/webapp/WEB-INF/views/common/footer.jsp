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

  /* Footer Styles */
footer {
    background-color: #1c1c1c; /* 어두운 회색 배경 */
    color: #e0e0e0; /* 밝은 회색 텍스트 */
    font-size: 0.9rem;
    border-top: 5px solid #002147; /* 네이비 색상의 강조 선 */
}

.footer-container {
    display: flex;
    flex-wrap: wrap;
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
    gap: 20px;
}

.footer-section {
    flex: 1;
    min-width: 250px;
}

.footer-section h2 {
    margin-bottom: 15px;
    font-size: 1.2rem;
    border-bottom: 2px solid #1abc9c; /* 하단 언더라인 */
    display: inline-block;
    padding-bottom: 5px;
}

.footer-section p {
    line-height: 1.6;
}

.footer-section ul {
    list-style: none;
    padding: 0;
}
.footer-section.links {
    text-align: center; /* 가운데 정렬 적용 */
}


.footer-section.links ul li {
  text-align: center; /* 가운데 정렬 적용 */
}

.footer-section ul li {
    margin-bottom: 10px;
    display: flex;
    align-items: center;
}

.footer-section ul li i {
    margin-right: 10px;
    color: #1abc9c; /* 아이콘 색상 */
}

.footer-section ul li a {
    color: #e0e0e0;
    text-decoration: none;
    transition: color 0.3s ease;
}

.footer-section ul li a:hover {
    color: #1abc9c; /* 호버 시 색상 변경 */
}

.footer-section .socials a {
    margin-right: 10px;
    color: #e0e0e0;
    font-size: 1.2rem;
    transition: color 0.3s ease;
}

.footer-section .socials a:hover {
    color: #1abc9c;
}


.footer-bottom {
    text-align: center;
    margin-top: 20px;
    border-top: 1px solid #555; /* 섬세한 상단 경계 */
    padding-top: 10px;
    font-size: 0.8rem;
    color: #b0b0b0;
}

/* Responsive Design */
@media screen and (max-width: 768px) {
    footer {
        padding: 20px;
        font-size: 0.8rem;
    }
    
    .footer-container {
        flex-direction: column;
        align-items: center;
    }
    
    .footer-section {
        max-width: 500px;
        text-align: center;
    }
    
    .footer-section .socials a {
        margin: 0 5px;
    }
}

</style>
</head>
<body>
<footer>
     <div class="footer-container">
        <!-- About Section -->
        <div class="footer-section about">
            <h2>ModuERP</h2>
            <p>
                ModuERP는 귀사의 비즈니스 관리를 위한 통합 솔루션을 제공합니다.
                혁신적인 기술과 사용자 친화적인 디자인으로 업무 효율을 극대화합니다.
            </p>
            <div class="socials">
                <a href="#" aria-label="Facebook"><i class="fab fa-facebook-f"></i></a>
                <a href="#" aria-label="Twitter"><i class="fab fa-twitter"></i></a>
                <a href="#" aria-label="LinkedIn"><i class="fab fa-linkedin-in"></i></a>
                <a href="#" aria-label="Instagram"><i class="fab fa-instagram"></i></a>
            </div>
        </div>
        
        <!-- Useful Links Section -->
        <div class="footer-section links">
            <h2>유용한 링크</h2>
            <ul>
                <li><a href="${pageContext.request.contextPath}/home">홈</a></li>
                <li><a href="${pageContext.request.contextPath}/services">서비스</a></li>
                <li><a href="${pageContext.request.contextPath}/support">고객 지원</a></li>
                <li><a href="${pageContext.request.contextPath}/contact">문의하기</a></li>
                <li><a href="${pageContext.request.contextPath}/about">회사 소개</a></li>
            </ul>
        </div>
        
        <!-- Contact Section -->
        <div class="footer-section contact">
            <h2>문의하기</h2>
            <ul>
                <li><i class="fas fa-map-marker-alt"></i> 서울특별시 서초구 신논현동 ICT기술협회</li>
                <li><i class="fas fa-phone"></i> TEL: 02-1234-5678 | FAX: 02-1234-5679</li>
                <li><i class="fas fa-envelope"></i> <a href="mailto:support@moduerp.com">support@moduerp.com</a></li>
            </ul>
        </div>
    </div>
    <div class="footer-bottom">
        <p>최만경, 최은영, 최원준, 조현재, 박재윤, 이은지</p>
    </div>
</footer>
</body>
</html>