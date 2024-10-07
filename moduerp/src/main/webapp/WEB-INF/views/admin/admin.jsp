<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .container {
            display: flex;
            justify-content: space-between;
            padding: 20px;
            background-color: white;
        }

        .chart-container {
            width: 60%;
        }

        .table-container {
            width: 35%;
        }

        .scrollable-table {
            max-height: 480px; /* Set a maximum height for the table content */
            overflow-y: auto; /* Enable vertical scrolling */
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .filter-options {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<!-- 메뉴바 임포트 -->
<c:import url="/WEB-INF/views/common/menubar.jsp" />

<h1>관리자 페이지</h1>

<div class="container">
    <!-- Left: Chart -->
    <div class="chart-container">
        <canvas id="memberChart"></canvas>
    </div>

    <!-- Right: Member Table -->
    <div class="table-container">
        <h2>회원가입 현황 데이터 테이블</h2>
        <div class="scrollable-table">
            <table>
                <thead>
                    <tr>
                        <th>가입일자</th>
                        <th>가입자 UUID</th>
                        <th>가입자 Email</th>
                        <th>마지막 로그인 시간</th>
                        <th>탈퇴일자</th>
                        <th>탈퇴 사유</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="admin" items="${adminList}">
                        <tr>
                            <td>${admin.registrationDate}</td>
                            <td>${admin.uuid}</td>
                            <td>${admin.empName}</td>
                            <td>${admin.empEmail}</td>
                            <td>${admin.deletedAt}</td>
                            <td>${admin.deletedExcuse}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- 푸터 임포트 -->
<c:import url="/WEB-INF/views/common/footer.jsp" />

<script>
    // 서버에서 받아온 데이터로 차트에 넣을 데이터 설정
    const chartLabels = [];
    const joinedData = [];
    const leftData = [];

    // 가입과 탈퇴 날짜별로 카운트하기 위한 Map
    const joinCountByDate = new Map();
    const leaveCountByDate = new Map();

    <c:forEach var="record" items="${memberStats}">
        const registrationDate = '${record.registrationDate}';
        const deletedAt = '${record.deletedAt}';
        
        // 가입자 수 카운트 (등록된 날짜별로 1씩 증가)
        if (registrationDate) {
            if (!joinCountByDate.has(registrationDate)) {
                joinCountByDate.set(registrationDate, 0);
                chartLabels.push(registrationDate); // 새로운 날짜가 발견되면 레이블에 추가
            }
            joinCountByDate.set(registrationDate, joinCountByDate.get(registrationDate) + 1);
        }

        // 탈퇴자 수 카운트 (탈퇴된 날짜별로 1씩 증가)
        if (deletedAt) {
            if (!leaveCountByDate.has(deletedAt)) {
                leaveCountByDate.set(deletedAt, 0);
                if (!chartLabels.includes(deletedAt)) {
                    chartLabels.push(deletedAt); // 새로운 탈퇴일이 있으면 레이블에 추가
                }
            }
            leaveCountByDate.set(deletedAt, leaveCountByDate.get(deletedAt) + 1);
        }
    </c:forEach>

    // 각 날짜에 대해 데이터를 배열에 채우기
    chartLabels.sort(); // 날짜순으로 정렬
    chartLabels.forEach(date => {
        joinedData.push(joinCountByDate.get(date) || 0);  // 해당 날짜에 가입자 수가 없으면 0
        leftData.push(leaveCountByDate.get(date) || 0);  // 해당 날짜에 탈퇴자 수가 없으면 0
    });

    const config = {
        type: 'line',
        data: {
            labels: chartLabels,
            datasets: [
                {
                    label: 'Joined Members',
                    data: joinedData,  
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 2,
                    fill: false
                },
                {
                    label: 'Left Members',
                    data: leftData, 
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 2,
                    fill: false
                }
            ]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
            },
            scales: {
                x: {
                    display: true,
                    title: {
                        display: true,
                        text: 'Date'
                    }
                },
                y: {
                    display: true,
                    title: {
                        display: true,
                        text: 'Number of Members'
                    }
                }
            }
        }
    }; 

    // Render chart
    const memberChart = new Chart(
        document.getElementById('memberChart'),
        config
    );
</script>

</body>
</html>
