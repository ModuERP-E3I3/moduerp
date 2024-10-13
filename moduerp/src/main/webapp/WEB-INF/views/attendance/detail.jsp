<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style type="text/css">
        .top-content-box {
            width: 96%;
            height: 6vh;
            background-color: white;
            margin: 1.8% 5% 0 1%;
            border: 1px solid #ccc;
            border-radius: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
            font-size: 30px;
            font-weight: bold;
        }

        #menubar {
            list-style: none;
            padding: 0;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        #menubar li {
            margin: 0 40px;
        }

        #menubar li a {
            color: black;
            text-decoration: none;
            font-size: 16px;
            padding: 10px 20px;
            transition: background 0.3s ease;
        }

        #menubar li a:hover {
            background-color: #f4f4f4;
            border-radius: 10px;
        }

        .content-box {
            width: 96%;
            height: 70vh;
            background-color: white;
            margin: 5% 5% 0 1%;
            border: 1px solid #ccc;
            border-radius: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            position: relative;
        }

        .content-title {
            position: absolute;
            top: -40px;
            left: 20px;
            font-size: 24px;
            color: white;
            font-weight: bold;
        }

        body {
            font-family: Arial, sans-serif;
        }

		 table {
           width: 100%;
           border-collapse: collapse;
           margin-bottom: 20px;
           margin: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ccc;
        }

        th {
            background-color: #f4f4f4;
            font-weight: bold;
        }
		
		
        .button-container {
            text-align: center;
            margin-top: 30px;
            clear: both; /* 버튼을 테이블 아래로 보내기 위한 설정 */
        }

        .button-container button {
            padding: 10px 20px;
            margin: 5px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .edit-button {
            background-color: #4CAF50;
            color: white;
        }

        .cancel-button {
            background-color: #f44336;
            color: white;
        }

        .back-button {
            background-color: #bbb;
            white-space: nowrap; /* 텍스트가 줄 바꿈 없이 한 줄로 출력되게 설정 */
        }

        .approve-button {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>

<body>
    <!-- 서브헤더 JSP 임포트 -->
    <c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

<!-- 위에 하얀 박스 -->
	<div class="top-content-box">
		<ul id="menubar">
			<li><a href="<c:url value='/attendance.do' />"><i
					class="fas fa-bullhorn"></i> 출퇴근</a></li>
			<li><a href="<c:url value='/attendanceDocument/mylist.do' />"><i
					class="fas fa-clipboard"></i> 근태문서</a></li>
			<li><a href="<c:url value='/email/inbox.do' />"> <i
					class="fas fa-envelope"></i> 이메일
			</a></li>
		</ul>
	</div>

    <!-- 메인 콘텐츠 박스 -->
    <div class="content-box">
        <div class="content-title">
            ${request.startDate.substring(0, 10)} ~ ${request.endDate.substring(0, 10)} 상세보기
        </div>

        <table>
                <tr>
                    <th>신청 유형</th>
                    <td>${request.applicationType}</td>
                </tr>
                <tr>
                    <th>신청자</th>
                    <td>${request.requesterName}</td>
                </tr>
                <tr>
                    <th>시작 날짜</th>
                    <td>${request.startDate.substring(0, 10)}</td>
                </tr>
                <tr>
                    <th>종료 날짜</th>
                    <td>${request.endDate.substring(0, 10)}</td>
                </tr>
                <tr>
                    <th>시작 시간</th>
                    <td>
                        <c:choose>
                            <c:when test="${request.startTime != null}">
                                <fmt:formatDate value="${request.startTimeAsDate}" pattern="HH:mm" />
                            </c:when>
                            <c:otherwise>없음</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <th>종료 시간</th>
                    <td>
                        <c:choose>
                            <c:when test="${request.endTime != null}">
                                <fmt:formatDate value="${request.endTimeAsDate}" pattern="HH:mm" />
                            </c:when>
                            <c:otherwise>없음</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <th>근태 사유</th>
                    <td>${request.reason}</td>
                </tr>
                <tr>
                    <th>첨부 파일</th>
                    <td>
                        <c:choose>
                            <c:when test="${request.attachment != null}">
                                <a href="/attachments/${request.attachment}">${request.attachment}</a>
                            </c:when>
                            <c:otherwise>첨부 파일 없음</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <th>결재자</th>
                    <td>${request.approverName}</td>
                </tr>
                <tr>
                    <th>비고</th>
                    <td>${request.remarks}</td>
                </tr>
                <tr>
                    <th>연락처</th>
                    <td>${request.contact}</td>
                </tr>
              <tr>
    			<th>제출 여부</th>
    				<td><span style="color: red; font-weight: bold;">${request.status}</span></td>
			</tr>
				<c:if test="${request.isApproved != 'N'}">
    			<tr>
       				 <th>승인 여부</th>
   	     			<td><span style="color: blue; font-weight: bold;">${request.isApproved}</span></td>
   			 	</tr>
			</c:if>

        </table>

         <div class="button-container" style="clear: both;">
            <c:if test="${loginUser == request.approver && request.isApproved != '승인' && request.isApproved != '반려'}">
    			<button class="approve-button" onclick="approveRequest('${request.attendancerequestId}')">승인하기</button>
    			<button class="reject-button" style="background-color: #f44336; color: white;" onclick="rejectRequest('${request.attendancerequestId}')">반려하기</button>
			</c:if>

			<c:if test="${loginUser == request.approver && request.isApproved == '반려'}">
    			<button class="undo-reject-button" style="background-color: #f44336; color: white;" onclick="undoRejectRequest('${request.attendancerequestId}')">반려 취소</button>
			</c:if>
			
		    <c:if test="${loginUser == request.approver && request.isApproved == '승인'}">
    			<button class="undo-reject-button" style="background-color: #f44336; color: white;" onclick="undoApproveRequest('${request.attendancerequestId}')">승인 취소</button>
			</c:if>
			
            <c:if test="${loginUser != request.approver && request.isApproved != '승인'}">
                <button class="edit-button" onclick="location.href='<c:url value='/attendanceDocument/send.do?attendancerequestId=${request.attendancerequestId}' />'">수정하기</button>
                <button class="cancel-button" onclick="if(confirm('정말로 삭제하시겠습니까? 복구할 수 없습니다.')) { location.href='<c:url value='/attendanceDocument/cancel.do?attendancerequestId=${request.attendancerequestId}' />'; }">신청취소</button>
                <c:if test="${request.status == '임시저장'}">
                    <button class="submit-button" onclick="submitRequest('${request.attendancerequestId}', '${request.approver}')">제출하기</button>
                </c:if>
            </c:if>
            
              <c:if test="${loginUser != request.approver && request.isApproved == '반려'}">
            	<button class="resubmit-button" style="background-color: #007bff; color: white;" onclick="resubmitRequest('${request.attendancerequestId}')">재요청</button>
            </c:if>
            
            
            <button class="back-button" onclick="location.href='<c:url value='/attendanceDocument/mylist.do' />'">목록으로 돌아가기</button>
        </div>
    </div>

    <script>
    function resubmitRequest(attendanceRequestId) {
        fetch('<c:url value="/attendanceDocument/undoReject.do"/>', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'attendancerequestId=' + attendanceRequestId
        })
        .then(response => response.json())
        .then(result => {
        	 alert('결재 재요청을 성공했습니다.');
        	 const mylistUrl = '<c:url value="/attendanceDocument/mylist.do"/>';
             const detailUrlBase = '<c:url value="/attendanceDocument/detail/"/>';
            if (result.redirectUrl) {
            	 location.href = result.redirectUrl === "/attendanceDocument/mylist.do" ? mylistUrl : detailUrlBase + attendanceRequestId + '.do';
            } else {
                alert(result.error);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('결재 재요청을 실패했습니다. 다시 시도해주세요.');
        });
    }

    
    function undoApproveRequest(attendanceRequestId) {
        fetch('<c:url value="/attendanceDocument/undoReject.do"/>', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'attendancerequestId=' + attendanceRequestId
        })
        .then(response => response.json())
        .then(result => {
       	    alert('결재승인을 취소했습니다.');
            const mylistUrl = '<c:url value="/attendanceDocument/mylist.do"/>';
            const detailUrlBase = '<c:url value="/attendanceDocument/detail/"/>';

            if (result.redirectUrl) {
                location.href = result.redirectUrl === "/attendanceDocument/mylist.do" ? mylistUrl : detailUrlBase + attendanceRequestId + '.do';
            } else {
                alert(result.error);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('결재승인의 취소를 실패했습니다. 다시 시도해주세요.');
        });
    }
    
    
    
    function undoRejectRequest(attendanceRequestId) {
        fetch('<c:url value="/attendanceDocument/undoReject.do"/>', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'attendancerequestId=' + attendanceRequestId
        })
        .then(response => response.json())
        .then(result => {
       	    alert('결재반려를 취소했습니다.');
            const mylistUrl = '<c:url value="/attendanceDocument/mylist.do"/>';
            const detailUrlBase = '<c:url value="/attendanceDocument/detail/"/>';

            if (result.redirectUrl) {
                location.href = result.redirectUrl === "/attendanceDocument/mylist.do" ? mylistUrl : detailUrlBase + attendanceRequestId + '.do';
            } else {
                alert(result.error);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('결재반려의 취소를 실패했습니다. 다시 시도해주세요.');
        });
    }


    
    function rejectRequest(attendanceRequestId) {
        // POST 요청으로 결재 반려 처리        
        fetch('<c:url value="/attendanceDocument/reject.do"/>', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'attendancerequestId=' + attendanceRequestId
        })
        .then(response => response.json())
        .then(result => {
            if (result.redirectUrl) {
            	 alert('결재반려에 성공했습니다.');
                // 리다이렉트 URL을 설정된 c:url 변수로 이동
                const redirectUrl = result.redirectUrl === "/attendanceDocument/mylist.do" ? '<c:url value="/attendanceDocument/mylist.do"/>' : '<c:url value="/attendanceDocument/detail/' + attendanceRequestId + '.do"/>';
                location.href = redirectUrl;
            } else {
                alert(result.error);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('결재반려에 실패했습니다. 다시 시도해주세요.');
        });
    }


    
    function approveRequest(attendancerequestId) {
        // POST 요청으로 결재 승인 처리
        fetch('<c:url value="/attendanceDocument/approve.do"/>', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'attendancerequestId=' + attendancerequestId
        })
        .then(response => response.text())
        .then(result => {
            alert('결재승인을 성공했습니다.');
            location.href = '<c:url value="/attendanceDocument/mylist.do"/>';
        })
        .catch(error => {
            console.error('Error:', error);
            alert('결재승인에 실패했습니다. 다시 시도해주세요.');
        });
    }

    function submitRequest(attendanceRequestId, approverUUID) {
        const requestBody = 'id=' + attendanceRequestId + '&status=제출완료&approver=' + approverUUID + '&isApproved=대기';
        
        // c:url 값을 자바스크립트 변수로 설정
        const mylistUrl = '<c:url value="/attendanceDocument/mylist.do"/>';
        const detailUrlBase = '<c:url value="/attendanceDocument/detail/"/>';

        fetch('<c:url value="/attendanceDocument/updateStatus.do"/>', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: requestBody
        })
        .then(response => response.json())
        .then(result => {
        	  alert('문서제출을 성공했습니다.');
            if (result.redirectUrl) {
                // 리다이렉트 URL을 설정된 c:url 변수로 이동
                location.href = result.redirectUrl === "/attendanceDocument/mylist.do" ? mylistUrl : detailUrlBase + attendanceRequestId + '.do';
            } else {
                alert(result.error);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('문서제출에 실패했습니다. 다시 시도해주세요.');
        });
    }


    </script>
</body>
</html>
