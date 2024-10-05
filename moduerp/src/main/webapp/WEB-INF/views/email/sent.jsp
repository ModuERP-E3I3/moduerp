<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보낸 이메일</title>
<style type="text/css">
/* 기존 CSS 스타일 */
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

/* 기존 CSS 유지 */
.attachment-space {
	display: inline-block; /* 인라인 블록으로 설정 */
	width: 20px; /* 첨부 아이콘 공간 확보 */
	margin-right: 10px; /* 이메일 텍스트와 간격 유지 */
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
	position: relative; /* 드롭다운 메뉴를 위해 position 추가 */
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

/* 제목 스타일 */
.content-title {
	position: absolute;
	top: -40px;
	left: 20px;
	font-size: 24px;
	color: white;
	font-weight: bold;
}

/* 드롭다운 메뉴 스타일 */
.dropdown-menu {
	display: none; /* 기본적으로 숨김 */
	position: absolute;
	top: 100%; /* 부모 요소의 바로 아래에 위치 */
	left: 0; /* 부모 요소의 왼쪽에 맞춤 */
	background-color: white;
	border: 1px solid #ccc;
	z-index: 1;
	margin-top: 5px; /* 상단 여백 */
	border-radius: 5px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	list-style: none;
	padding: 0;
	white-space: nowrap;
}

/* 드롭다운 메뉴 항목 스타일 */
.dropdown-item {
	padding: 10px 20px; /* 내부 여백 */
	text-decoration: none; /* 밑줄 제거 */
	color: black; /* 글자 색상 */
	display: block; /* 블록 형태 */
}

.dropdown-item:hover {
	background-color: #f4f4f4; /* 호버 시 배경색 변경 */
}

/* 기본적으로 드롭다운 메뉴가 숨겨지지 않도록 수정 */
#menubar li:hover .dropdown-menu {
	display: block; /* 마우스를 올리면 드롭다운 메뉴가 보이도록 설정 */
}
/* 전체 선택 체크박스 및 액션 버튼 조정 */
.action-buttons {
	display: flex;
	align-items: center;
	padding-left: 15px; /* 이메일 항목의 체크박스와 동일한 padding */
	margin-bottom: 10px; /* 이메일 리스트와의 간격 */
}

/* 이메일 리스트의 스타일 */
.email-list {
	list-style: none;
	padding: 0;
}

#menubar li {
	position: relative; /* 부모 요소를 상대 위치로 설정하여 드롭다운 메뉴의 위치 기준을 지정 */
}

.email-item {
	border-bottom: 1px solid #ccc;
	padding: 10px 0;
	display: flex;
	align-items: center;
	justify-content: flex-start; /* 전체 선택과 동일한 위치 */
}

.email-item .checkbox {
	margin-left: 15px; /* 체크박스의 좌측 여백을 전체 선택과 일치시키기 */
}

.email-item:last-child {
	border-bottom: none;
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
	/* margin-left: 15px; */ /* 아이콘의 좌측 여백도 일치 */
}
</style>
</head>

<body>
	<!-- 서브헤더 JSP 임포트 -->
	<c:import url="/WEB-INF/views/common/erpMenubar.jsp" />

	<div class="top-content-box">
		<ul id="menubar">
			<li><a href="<c:url value='/attendance.do' />"><i
					class="fas fa-bullhorn"></i> 출퇴근</a></li>
			<li><a href="<c:url value='/leave.do' />"><i
					class="fas fa-clipboard"></i> 휴가</a></li>
			<li style="position: relative;"><a href="javascript:void(0);"
				class="active" onclick="toggleDropdown(this);"><i
					class="fas fa-envelope"></i> 이메일</a>
				<ul class="dropdown-menu">
					<li><a href="<c:url value='/email/list.do' />"
						class="dropdown-item" onclick="hideDropdown()">전체 이메일</a></li>
					<li><a href="<c:url value='/email/inbox.do' />"
						class="dropdown-item" onclick="hideDropdown()">받은 이메일</a></li>
					<li><a href="<c:url value='/email/sent.do' />"
						class="dropdown-item" onclick="hideDropdown()">보낸 이메일</a></li>
				</ul></li>
		</ul>
	</div>



	<!-- 하얀 큰 박스 -->
	<div class="content-box">
		<div class="content-title">보낸 이메일</div>

		<!-- 전체 선택 및 동작 버튼 -->
		<div class="action-buttons"
			style="display: flex; align-items: center; padding-left: 15px; margin-bottom: 10px;">
			<input type="checkbox" id="selectAll" onclick="selectAllEmails(this)">
			<span style="margin-left: 5px;">전체 선택</span>
			<button style="margin-left: 15px;" onclick="deleteSelected()">삭제</button>
			<button style="margin-left: 10px;" onclick="markAsRead()">읽음</button>
		</div>


		<!-- 이메일 리스트 -->
		<ul class="email-list">
			<c:forEach var="email" items="${emails}">
				<li class="email-item">
					<!-- 체크박스 --> <input type="checkbox" class="checkbox"
					name="emailCheckbox" value="${email.emailId}"> <!-- 읽음 여부 아이콘 -->
					<img class="read-status icon"
					src="<c:choose>
             <c:when test='${email.isRead.toString() eq "Y"}'>
                 <c:url value='/resources/icons/read.png'/>
             </c:when>
             <c:otherwise>
                 <c:url value='/resources/icons/unread.png'/>
             </c:otherwise>
          </c:choose>" />


					<!-- 첨부파일 아이콘 공간 확보 --> <span class="attachment-space"> <c:if
							test="${not empty email.attachmentPath}">
							<img class="attach-icon icon"
								src="<c:url value='/resources/icons/attach.png' />" alt="첨부파일" />
						</c:if>
				</span> <!-- 수신자 메일주소 --> ${email.recipientName} &nbsp; <!-- 이메일 제목 (클릭 시 상세 페이지 이동) -->
					<a href="view.do?emailId=${email.emailId}">${email.subject}</a>&nbsp;
					<!-- 보낸 날짜 --> <span class="date"> <fmt:setLocale
							value="ko_KR" /> <fmt:formatDate value="${email.sentDate}"
							pattern="yyyy.MM.dd a hh:mm" />
				</span>
				</li>
			</c:forEach>
		</ul>

		<!-- 메일쓰기 버튼 -->
		<div class="btn-group" style="text-align: right; margin-top: 20px;">
			<a href="send.do" class="btn blue">메일쓰기</a>
		</div>
	</div>

	<script>
	 var contextPath = '<%=request.getContextPath()%>';
	 
    function toggleDropdown(element) {
        const dropdown = element.nextElementSibling; // 드롭다운 메뉴
        if (dropdown) {
            console.log("드롭다운 상태:", dropdown.style.display);
            dropdown.style.display = (dropdown.style.display === "block" || dropdown.style.display === "") ? "none" : "block";
            console.log("변경된 드롭다운 상태:", dropdown.style.display);
        }
    }
    function hideDropdown() {
        const dropdowns = document.querySelectorAll('.dropdown-menu');
        dropdowns.forEach(dropdown => {
            dropdown.style.display = "none"; // 모든 드롭다운 메뉴 숨기기
        });
    }

    
        // 전체 선택 기능
        function selectAllEmails(selectAllCheckbox) {
            const checkboxes = document.querySelectorAll('input[name="emailCheckbox"]');
            checkboxes.forEach(checkbox => {
                checkbox.checked = selectAllCheckbox.checked;
            });
        }

        function deleteSelected() {
            const selectedEmails = Array.from(document.querySelectorAll('input[name="emailCheckbox"]:checked'))
                .map(checkbox => Number(checkbox.value));  // 선택된 이메일 ID들을 배열로 만듦

            if (selectedEmails.length > 0) {
                // 이메일 ID 배열을 서버로 전송하여 삭제 처리 요청
                fetch(contextPath + '/email/deleteEmails.do', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(selectedEmails)
                })
                .then(response => response.text())
                .then(result => {
                    if (result === 'success') {
                        alert("선택한 이메일이 삭제되었습니다.");
                        location.reload();  // 삭제 후 페이지를 새로고침하여 업데이트된 목록을 보여줌
                    } else {
                        alert("이메일 삭제에 실패했습니다.");
                    }
                })
                .catch(error => console.error('Error:', error));
            } else {
                alert("삭제할 이메일을 선택하세요.");
            }
        }


        function markAsRead() {
            const selectedEmails = Array.from(document.querySelectorAll('input[name="emailCheckbox"]:checked'))
                .map(checkbox => Number(checkbox.value));

            console.log("선택된 이메일 ID:", selectedEmails);

            if (selectedEmails.length > 0) {
                fetch(contextPath + '/email/markAsRead.do', {
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
