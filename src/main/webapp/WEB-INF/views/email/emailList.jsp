<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 이메일</title>
<style type="text/css">
<%-- 상단 영역의 스타일 --%>
.top-content-box {
	width: 96%;
	height: 6vh;
	background-color: white;
	margin-left: 1%;
	margin-right: 5%;
	margin-top: 1.8%;
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

<%-- 상단 메뉴바 스타일링 --%>
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
	position: relative;
}

#menubar li a {
	color: black;
	text-decoration: none;
	font-size: 16px;
	display: block;
	padding: 10px 20px;
	transition: background 0.3s ease;
}

#menubar li a:hover, #menubar li a.active {
	background-color: #f4f4f4;
	border-radius: 10px;
}

<%-- 드롭다운 메뉴 스타일 --%>
.dropdown-menu {
	display: none;
	position: absolute;
	background-color: white;
	border: 1px solid #ccc;
	z-index: 1;
	margin-top: 5px;
	border-radius: 5px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	list-style: none;
	padding: 0;
	white-space: nowrap;
}

.dropdown-item {
	padding: 10px 20px;
	text-decoration: none;
	color: black;
	display: block;
}

.dropdown-item:hover {
	background-color: #f4f4f4;
}

<%-- 드롭다운 메뉴 보이기 및 숨기기 --%>
.show {
	display: block;
}

<%-- 이메일 목록 영역 스타일 --%>
.content-box {
	width: 96%;
	background-color: white;
	margin-left: 1%;
	margin-right: 5%;
	margin-top: 5%;
	border: 1px solid #ccc;
	border-radius: 20px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	position: relative;
	padding: 20px;
}

<%-- 보낸 이메일 제목 스타일 --%>
.content-title {
	position: absolute;
	top: -40px;
	left: 20px;
	font-size: 24px;
	color: white;
	font-weight: bold;
}

<%-- 보낸 이메일이 없을 때의 스타일 --%>
.no-email-message {
	text-align: center;
	margin-top: 50px;
	padding: 30px;
	font-size: 20px;
	color: #333;
	background-color: #f9f9f9;
	border: 1px solid #ddd;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

<%-- 메일쓰기 버튼 스타일 --%>
.no-email-button {
	display: inline-block;
	margin-top: 20px;
	padding: 10px 20px;
	background-color: #5cb85c;
	color: white;
	font-size: 18px;
	border: none;
	border-radius: 20px;
	text-decoration: none;
	transition: background 0.3s ease;
}

.no-email-button:hover {
	background-color: #4cae4c;
}

<%-- 액션 버튼 스타일 --%>
.action-buttons {
	display: flex;
	align-items: center;
	padding-left: 15px;
	margin-bottom: 10px;
}

<%-- 이메일 리스트 스타일 --%>
.email-list {
	list-style: none;
	padding: 0;
}

.email-item {
	border-bottom: 1px solid #ccc;
	padding: 10px 0;
	display: flex;
	align-items: center;
	justify-content: flex-start;
}

.email-item .checkbox {
	margin-left: 15px;
}

.date {
	margin-left: auto;
	margin-right: 10px;
}

.checkbox, .read-status, .attach-icon {
	margin-right: 15px;
}

.icon {
	width: 16px;
	height: 16px;
}

<%-- 첨부파일 아이콘 공간 확보 --%>
.attachment-space {
	display: inline-block;
	width: 20px;
	margin-right: 15px;
}

<%-- 수신자 이름 공간 확보 (일정한 넓이 유지) --%>
.recipient-name {
	display: inline-block;
	width: 150px; /* 고정된 넓이로 공간 확보 */
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	margin-right: 20px; /* 제목과의 간격 조정 */
}
</style>
</head>

<body>
	<%-- 서브헤더 JSP 임포트 --%>
	<c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

	<%-- 상단 메뉴바 --%>
	<div class="top-content-box">
		<ul id="menubar">
			<li><a href="<c:url value='/attendance.do' />"><i class="fas fa-bullhorn"></i> 출퇴근</a></li>
			<li><a href="<c:url value='/leave.do' />"><i class="fas fa-clipboard"></i> 휴가</a></li>
			<li style="position: relative;"><a href="javascript:void(0);" class="active" onclick="toggleDropdown(this);"><i class="fas fa-envelope"></i> 이메일</a>
				<ul class="dropdown-menu">
					<li><a href="<c:url value='/email/send.do' />" class="dropdown-item" onclick="hideDropdown()">메일 쓰기</a></li>
					<li><a href="<c:url value='/email/list.do' />" class="dropdown-item" onclick="hideDropdown()">전체 이메일</a></li>
					<li><a href="<c:url value='/email/inbox.do' />" class="dropdown-item" onclick="hideDropdown()">받은 이메일</a></li>
					<li><a href="<c:url value='/email/sent.do' />" class="dropdown-item" onclick="hideDropdown()">보낸 이메일</a></li>
				</ul>
			</li>
		</ul>
	</div>

	<%-- 메인 콘텐츠 영역 --%>
	<div class="content-box">
		<div class="content-title">전체 이메일</div>

		<c:choose>
			<%-- 이메일 리스트가 비어 있는 경우 --%>
			<c:when test="${empty emails}">
				<div class="no-email-message">
					받거나 보낸 이메일이 없습니다! 이메일을 보내 보세요☺️ <br><br>
					<a href="send.do" class="no-email-button">메일쓰기</a>
				</div>
			</c:when>

			<%-- 이메일 리스트가 존재하는 경우 --%>
			<c:otherwise>
				<div class="action-buttons">
					<input type="checkbox" id="selectAll" onclick="selectAllEmails(this)"> 
					<span style="margin-left: 5px;">전체 선택</span>
					<button style="margin-left: 15px;" onclick="deleteSelected()">삭제</button>
					<button style="margin-left: 10px;" onclick="markAsRead()">읽음</button>
				</div>

				<%-- 이메일 리스트 --%>
				<ul class="email-list">
					<c:forEach var="email" items="${emails}">
						<li class="email-item">
							<%-- 체크박스 --%>
							<input type="checkbox" class="checkbox" name="emailCheckbox" value="${email.emailId}">

							<%-- 읽음 여부 아이콘 --%>
							<img class="read-status icon" src="<c:choose>
								<c:when test='${email.isRead.toString() eq "Y"}'>
									<c:url value='/resources/icons/read.png'/>
								</c:when>
								<c:otherwise>
									<c:url value='/resources/icons/unread.png'/>
								</c:otherwise>
							</c:choose>" />

							<%-- 첨부파일 아이콘 공간 확보 --%>
							<span class="attachment-space">
								<c:if test="${not empty email.attachmentPath}">
									<img class="attach-icon icon" src="<c:url value='/resources/icons/attach.png' />" alt="첨부 파일">
								</c:if>
							</span>

							<%-- 수신자 이름 --%>
							<span class="recipient-name">${email.recipientName}</span>

							<%-- 이메일 제목 --%>
							<a href="view.do?emailId=${email.emailId}">${email.subject}</a> &nbsp;

							<%-- 보낸 날짜 --%>
							<span class="date">
								<fmt:setLocale value="ko_KR" />
								<fmt:formatDate value="${email.sentDate}" pattern="yyyy.MM.dd a hh:mm" />
							</span>
						</li>
					</c:forEach>
				</ul>
			</c:otherwise>
		</c:choose>
	</div>
	
	
	<%-- JavaScript 추가 --%>
	<script>
		<%-- 드롭다운 메뉴 토글 --%>
		function toggleDropdown(element) {
			const dropdownMenu = element.nextElementSibling;
			dropdownMenu.classList.toggle('show');
		}

		<%-- 전체 선택 기능 --%>
		function selectAllEmails(selectAllCheckbox) {
			const checkboxes = document.querySelectorAll('input[name="emailCheckbox"]');
			checkboxes.forEach(checkbox => {
				checkbox.checked = selectAllCheckbox.checked;
			});
		}

		<%-- 이메일 삭제 --%>
		function deleteSelected() {
			const selectedEmails = Array.from(document.querySelectorAll('input[name="emailCheckbox"]:checked'))
				.map(checkbox => Number(checkbox.value));
			if (selectedEmails.length > 0) {
				fetch('<%=request.getContextPath()%>/email/deleteEmails.do', {
					method: 'POST',
					headers: { 'Content-Type': 'application/json' },
					body: JSON.stringify(selectedEmails)
				})
				.then(response => response.text())
				.then(result => {
					if (result === 'success') {
						alert("선택한 이메일이 삭제되었습니다.");
						location.reload();
					} else {
						alert("이메일 삭제에 실패했습니다.");
					}
				})
				.catch(error => console.error('Error:', error));
			} else {
				alert("삭제할 이메일을 선택하세요.");
			}
		}

		<%-- 읽음 처리 --%>
		function markAsRead() {
			const selectedEmails = Array.from(document.querySelectorAll('input[name="emailCheckbox"]:checked'))
				.map(checkbox => Number(checkbox.value));
			if (selectedEmails.length > 0) {
				fetch('<%=request.getContextPath()%>/email/markAsRead.do', {
					method: 'POST',
					headers: { 'Content-Type': 'application/json' },
					body: JSON.stringify(selectedEmails)
				})
				.then(response => response.text())
				.then(result => {
					if (result === 'success') {
						location.reload();
					}
				})
				.catch(error => console.error('Error:', error));
			} else {
				alert("읽음 처리할 이메일을 선택하세요.");
			}
		}
	</script>
	
</body>
</html>
