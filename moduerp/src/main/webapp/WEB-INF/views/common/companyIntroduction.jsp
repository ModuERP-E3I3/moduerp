<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Font Awesome CDN 추가 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <meta charset="UTF-8">
    <title>ModuERP</title>

    <style type="text/css">
        /* 전체 페이지 기본 스타일 */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
            font-family: 'Helvetica Neue', Arial, sans-serif;
            display: flex; /* Flex 컨테이너로 설정 */
            flex-direction: column; /* 수직 방향으로 정렬 */
            background-color: #f9f9f9;
            color: #333;
            font-weight: 550;
        }

        /* 메인 콘텐츠 래퍼 */
        .main-content {
            flex: 1; /* 남은 공간을 모두 차지 */
            margin-top: 2%; /* 상단 여백 조정 */
            padding: 20px; /* 내부 여백 조정 */
        }

        /* 구분선 스타일 */
        hr {
            border: none;
            border-top: 1px solid #ddd;
            margin: 40px 0;
        }

        /* 이미지 컨테이너 스타일 */
        div#image-container {
            display: flex;
            justify-content: center; /* 중앙 정렬 */
            align-items: center;
            gap: 200px; /* 이미지 사이 간격 */
            background-color: white;
            border-radius: 20px;
            margin: 40px auto; /* 페이지 중앙 정렬 */
            width: 100%;
            height: 700px;
            max-width: 2400px;
        }

        div.image-wrapper {
            position: relative;
            text-align: center;
        }

        /* 이미지 스타일 */
        div.image-wrapper img {
            width: 800px;
            height: auto;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3); /* 입체감 설정 */
            border-radius: 20px; /* 모서리 둥글게 */
            transition: transform 0.3s ease, box-shadow 0.3s ease; /* 호버 시 확대 및 그림자 변화 */
        }

        /* 이미지 호버 시 확대 효과 */
        div.image-wrapper img:hover {
            transform: scale(1.05);
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.4); /* 호버 시 그림자 더 진하게 */
        }

        /* 이미지 위 둥근 모서리 사각형 텍스트 스타일 */
        div.image-wrapper .image-label {
            position: absolute;
            top: 20px; /* 이미지의 상단에서 20px 아래에 배치 */
            left: 50%;
            transform: translateX(-50%);
            background-color: rgba(0, 0, 0, 0.6); /* 반투명 검정 배경 */
            color: white;
            font-size: 25px;
            font-weight: bold;
            padding: 10px 20px; /* 텍스트 주변 여백 설정 */
            border-radius: 15px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5); /* 텍스트 그림자 */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 사각형에 그림자 */
        }

        .image-section {
            width: 100%;
            height: auto;
            margin: 40px auto; /* 좌우 가운데 정렬 및 상단 여백 */
            text-align: center;
            position: relative; /* 자식 요소의 절대 위치 설정을 위한 기준 */
        }

        .image-section img {
            max-width: 100%; /* div의 크기에 맞춰 이미지 크기 조정 */
            max-height: 100%; /* 높이도 div에 맞게 조정 */
            object-fit: cover; /* 이미지가 잘리지 않도록 조정 */
        }

        .image-section div {
            position: absolute; /* 이미지 위에 겹쳐지도록 절대 위치 설정 */
            color: white; /* 텍스트 색상 */
            font-size: 36px;
            font-weight: bold;
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.7); /* 텍스트 그림자 */
            top: 190px; /* 이미지 위에서 190px 아래에 배치 */
            left: 50%; /* 가운데 정렬 */
            transform: translateX(-50%); /* 정확한 가로 중앙 정렬을 위한 보정 */
        }

        #logo{
            padding: 12px 10px;
            font-size: 160px;
            opacity: 0.7; /* 이미지 반투명 설정 */
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px 10px;
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
        }

        .text-section {
            flex: 1;
            padding-right: 50px;
        }

        .stats-section {
            display: flex;
            flex-direction: column;
            align-items: flex-end;
            flex: 1;
        }

        h1 {
            font-size: 36px;
            margin-bottom: 20px;
            text-align: left;
            font-weight: 780;
        }

        p {
            font-size: 18px;
            color: #666;
            margin-bottom: 40px;
            text-align: left;
        }

        .stats {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            width: 100%;
            max-width: 400px;
        }

        .stat-item {
            width: 48%;
            margin-bottom: 20px;
            text-align: center;
        }

        .stat-label {
            font-size: 18px;
            color: #666;
            margin-bottom: 10px;
        }

        .stat {
            font-size: 28px;
            font-weight: bold;
            color: #217770;
            margin-bottom: 3%;
        }

        /* 푸터 스타일 */
        footer {
            background-color: #1c1c1c; /* 어두운 회색 배경 */
            color: #e0e0e0; /* 밝은 회색 텍스트 */
            font-size: 0.9rem;
            border-top: 5px solid #1C1C1C; /* 네이비 색상의 강조 선 */
            padding: 20px 0;
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
            margin: 0;
        }

        /* 기존 'ul li' 스타일 */
        .footer-section ul li {
            margin-bottom: 10px;
            display: flex;
            align-items: center;
        }

        /* 'links' 섹션의 <ul> 스타일 수정 */
        .footer-section.links ul {
            /* Flexbox 속성 제거 */
            /* display: flex; */
            /* justify-content: center; */
            /* gap: 15px; */
            padding: 0;
            margin: 0;
        }

        /* 'links' 섹션의 <li> 스타일 수정 */
        .footer-section.links {
            text-align: center; /* 섹션 내 텍스트 중앙 정렬 */
        }

        .footer-section.links ul li {
            display: block; /* Flexbox 영향을 받지 않도록 설정 */
            text-align: center; /* 텍스트 중앙 정렬 */
            margin-bottom: 10px; /* 링크 간 간격 유지 */
        }

        /* 아이콘이 있는 다른 섹션의 <li> 스타일 유지 */
        .footer-section.contact ul li {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
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

    <script type="text/javascript">
        function movePage() {
            location.href = "loginPage.do";
        }
    </script>

</head>
<body>
    <!-- 메인 콘텐츠 영역 -->
    <div class="main-content">
        <!-- 이미지 섹션 -->
        <div class="image-section">
            <div id="logo">ModuERP</div>
            <img src='https://ifh.cc/g/LOAYWS.jpg' border='0' alt="ModuERP 이미지">
            <div class="image-label">ModuERP</div>
        </div>

        <div class="container">
            <div class="text-section">
                <h1>
                    Modu ERP <br> 모두를 위한 모듈형 ERP
                </h1>
                <p>
                    19개의 모듈을 고객의 필요에 자유롭게 조합하고  <br> 혁신적인 기술과 직관적인 사용자 경험을 제공하여 <br>
                    사업장의 업무 효율을 극대화하는 것<br>Modu의 비전이자 목표입니다.
                </p>
            </div>
            <div class="stats-section">
                <div class="stats">
                    <div class="stat-item">
                        <div class="stat">인사 관리</div>
                        <div class="stat-label">직원관리</div>
                    </div>
                    <div class="stat-item">
                        <div class="stat">구매 관리</div>
                        <div class="stat-label">구매입출고, 배송조회, 발주서관리</div>
                    </div>
                    <div class="stat-item">
                        <div class="stat">생산 관리</div>
                        <div class="stat-label">생산입출고, 품질관리, 작업지시서</div>
                    </div>
                    <div class="stat-item">
                        <div class="stat">영업 관리</div>
                        <div class="stat-label">영업입출고, 거래처관리</div>
                    </div>
                    <div class="stat-item">
                        <div class="stat">차량 관리</div>
                        <div class="stat-label">
                            차량정보, 차량예약, <br>결제관리, 경로조회
                        </div>
                    </div>
                    <div class="stat-item">
                        <div class="stat">그룹웨어</div>
                        <div class="stat-label">
                            출퇴근, 인사관리, <br>사내메일
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <!-- 메뉴바 임포트 -->
        <c:import url="/WEB-INF/views/common/menubar.jsp" />
    </div>

    <!-- 푸터 임포트 -->
    <footer>
        <c:import url="/WEB-INF/views/common/footer.jsp" />
    </footer>
</body>

<script type="text/javascript">
    // 추가적인 자바스크립트가 필요할 경우 여기에 작성
</script>

</html>
