<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>구매 출고 처리</title>
</head>
<body>
    <h1>구매 출고 처리</h1>

    <form action="${pageContext.request.contextPath}/buystock/out" method="post">
        <label for="bStockOutId">출고 ID:</label>
        <input type="text" id="bStockOutId" name="bStockOutId" required><br>

        <label for="itemCode">아이템 코드:</label>
        <input type="text" id="itemCode" name="itemCode" required><br>

        <label for="uuid">UUID:</label>
        <input type="text" id="uuid" name="uuid" required><br>

        <label for="accountNo">계좌번호:</label>
        <input type="text" id="accountNo" name="accountNo" required><br>

        <label for="bankId">은행 ID:</label>
        <input type="text" id="bankId" name="bankId" required><br>

        <label for="departmentId">부서 ID:</label>
        <input type="text" id="departmentId" name="departmentId" required><br>

        <label for="bStockOutDate">출고 날짜:</label>
        <input type="date" id="bStockOutDate" name="bStockOutDate" required><br>

        <label for="bStockOutPlace">출고 장소:</label>
        <input type="text" id="bStockOutPlace" name="bStockOutPlace"><br>

        <label for="bStockOutQty">출고 수량:</label>
        <input type="number" id="bStockOutQty" name="bStockOutQty" required><br>

        <input type="submit" value="출고 처리">
    </form>
</body>
</html>
