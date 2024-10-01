<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Account Popup</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .popup-container {
            width: 600px;
            border: 1px solid #ccc;
            border-radius: 8px;
            padding: 20px;
            margin: 20px auto;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            background-color: #f9f9f9;
        }

        .popup-header {
            background-color: #007bff;
            color: white;
            padding: 10px;
            border-radius: 5px 5px 0 0;
        }

        .popup-header h2 {
            margin: 0;
            font-size: 18px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .form-group input[type="text"], .form-group input[type="number"], .form-group input[type="tel"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form-inline {
            display: flex;
            justify-content: space-between;
        }

        .form-inline input[type="text"] {
            width: 48%;
        }

        .form-actions {
            text-align: right;
            margin-top: 20px;
        }

        .form-actions button {
            padding: 8px 16px;
            border: none;
            background-color: #007bff;
            color: white;
            border-radius: 4px;
            cursor: pointer;
        }

        .form-actions button:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>

<div class="popup-container">
    <div class="popup-header">
        <h2>거래처 등록</h2>
    </div>

    <div class="form-group">
        <label for="tradeCode">거래처 코드</label>
        <input type="text" id="tradeCode" value="220-81-65848" readonly>
    </div>

    <div class="form-group">
        <label for="businessType">거래처 코드 구분</label>
        <div class="form-inline">
            <input type="radio" name="businessType" checked> 사업자등록번호
            <input type="radio" name="businessType"> 비사업자(내국인)
            <input type="radio" name="businessType"> 비사업자(외국인)
        </div>
    </div>

    <div class="form-group">
        <label for="tradeName">상호(이름)</label>
        <input type="text" id="tradeName" value="(주)한동공업">
    </div>

    <div class="form-group">
        <label for="ceoName">대표자명</label>
        <input type="text" id="ceoName" value="김민혁">
    </div>

    <div class="form-group">
        <label for="businessField">업태</label>
        <input type="text" id="businessField" value="제조업">
    </div>

    <div class="form-group">
        <label for="productType">종목</label>
        <input type="text" id="productType" value="철강 제조">
    </div>

    <div class="form-group">
        <label for="assetLimit">여신 한도</label>
        <input type="number" id="assetLimit" value="150000000" readonly>
    </div>

    <div class="form-group">
        <label for="managerName">전담 직원</label>
        <input type="text" id="managerName" value="박세나">
    </div>

    <div class="form-group">
        <label for="contactPerson">거래처 담당자</label>
        <input type="text" id="contactPerson" value="박지원 / 010-1234-5678">
    </div>

    <div class="form-actions">
        <button type="button">저장</button>
        <button type="button">취소</button>
    </div>
</div>

</body>
</html>
