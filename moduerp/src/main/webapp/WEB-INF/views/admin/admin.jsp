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
						<td>${admin.bizNumber}</td>
						<td>${admin.jobId}</td>
						<td>${admin.updatedAt}</td>
						<td>${admin.deleted}</td>
						<td>${admin.lastLoginLocation}</td>
						<td>${admin.empEmail}</td>
					</tr>
                </c:forEach>
            </tbody>
        </table>
        
        
    </div>
</div>

<!-- 푸터 임포트 -->
<c:import url="/WEB-INF/views/common/footer.jsp" />

<script>
    // 서버에서 받아온 데이터로 차트에 넣을 데이터 설정
    const chartLabels = [];
    const joinedData = [];
    const leftData = [];

    <c:forEach var="record" items="${memberStats}">
        chartLabels.push('${record.registrationDate}');
        joinedData.push(${record.isDeleted ? 0 : 1});
        leftData.push(${record.isDeleted ? 1 : 0});
    </c:forEach>

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
