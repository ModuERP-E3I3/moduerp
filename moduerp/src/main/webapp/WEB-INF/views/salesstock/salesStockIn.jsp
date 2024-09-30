<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>erpMain</title>

<style type="text/css">
    /* 하얀 박스 스타일 */
    .content-box {
<<<<<<< HEAD
<<<<<<< HEAD
        width: 96.8%; /* 화면에 가득 차지 않게 */
=======
        width: 98%; /* 화면에 가득 차지 않게 */
>>>>>>> parent of 29df839 (Merge branch 'eunji' into mankyoung)
=======
        width: 98%; /* 화면에 가득 차지 않게 */
>>>>>>> parent of 29df839 (Merge branch 'eunji' into mankyoung)
        height: 78vh; /* 화면 높이의 78% */
        background-color: white;
        margin-left: 1%;
        margin-top: 6%;
        border: 1px solid #ccc;
        border-radius: 20px; /* 박스 둥글게 */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
        position: relative;
        padding: 20px; /* 내부 여백 추가 */
    }

    /* 제목 스타일 */
    .content-title {
        position: absolute;
        top: -40px;
        left: 20px;
        font-size: 24px;
        color: white; 
        font-weight: bold;
    }

    /* 테이블 스타일 */
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th, td {
        border: 1px solid #ccc;
        padding: 10px;
        text-align: center;
    }

    th {
        background-color: #f4f4f4;
        font-weight: bold;
    }

    /* 버튼 스타일 */
    .btn-group {
        margin-top: 20px;
        text-align: right;
    }

    .btn {
        padding: 8px 16px;
        margin-left: 5px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .btn.red {
        background-color: red;
        color: white;
    }

    .btn.green {
        background-color: green;
        color: white;
    }

    .btn.blue {
        background-color: blue;
        color: white;
    }

    .filter-box {
        margin-bottom: 20px;
    }

    .filter-box input, .filter-box select {
        padding: 8px;
        margin-right: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    .filter-box input[type="date"] {
        width: 160px;
    }

</style>

</head>

<body>
    <!-- 서브헤더 JSP 임포트 -->
    <c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

    <!-- 하얀 큰 박스 -->
    <div class="content-box">

        <div class="content-title">메인페이지</div>

        <!-- 필터 박스 -->
        <div class="filter-box">
            <select>
                <option>조회기간</option>
            </select>
            <input type="date" />
            <input type="date" />
            <select>
                <option>품목 선택</option>
            </select>
            <input type="text" placeholder="내용 입력" />
            <button class="btn">조회</button>
        </div>

        <!-- 테이블 -->
        <table>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>거래처</th>
                    <th>품목코드</th>
                    <th>품명</th>
                    <th>입고 수량</th>
                    <th>출고 수량</th>
                    <th>재고 수량</th>
                    <th>단가</th>
                    <th>비고</th>
                </tr>
            </thead>
            <tbody>
                <!-- 더미 데이터 -->
                <c:forEach var="item" items="${dummyData}">
                    <tr>
                        <td>${item.no}</td>
                        <td>${item.client}</td>
                        <td>${item.productCode}</td>
                        <td>${item.productName}</td>
                        <td>${item.inQuantity}</td>
                        <td>${item.outQuantity}</td>
                        <td>${item.stockQuantity}</td>
                        <td>${item.unitPrice}</td>
                        <td>${item.remark}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- 버튼 그룹 -->
        <div class="btn-group">
            <button class="btn red">삭제</button>
            <button class="btn green">수정</button>
            <button class="btn blue">등록</button>
        </div>

    </div>
</body>
</html>
