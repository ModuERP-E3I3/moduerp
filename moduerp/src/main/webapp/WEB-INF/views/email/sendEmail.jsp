<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 전송</title>
<style type="text/css">
/* 기존 CSS 스타일을 여기에 추가하세요. */
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

/* 폼 스타일 */
.email-form {
	width: 100%;
	max-width: 600px;
	margin: 0 auto;
}

.email-form label {
	display: block;
	margin-top: 15px;
	font-weight: bold;
}

.email-form input[type="email"], .email-form input[type="text"],
	.email-form textarea, .email-form input[type="file"] {
	width: 100%;
	padding: 10px;
	margin-top: 5px;
	border: 1px solid #ccc;
	border-radius: 5px;
	resize: vertical;
}

.email-form textarea {
	height: 200px;
}

/* 버튼 스타일 */
.btn-group {
	margin-top: 20px;
	text-align: right;
}

.btn {
	padding: 10px 20px;
	margin-left: 5px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	text-decoration: none;
	color: white;
}

.btn.blue {
	background-color: blue;
}

.btn.red {
	background-color: red;
}

.btn.blue:hover {
	background-color: #0056b3;
}

.btn.red:hover {
	background-color: #cc0000;
}

/* 드롭다운 메뉴 스타일 */
.dropdown-menu {
	display: none; /* 기본적으로 숨김 */
	position: absolute; /* 위치 지정 */
	background-color: white;
	border: 1px solid #ccc;
	z-index: 1;
	margin-top: 5px; /* 상단 여백 */
	border-radius: 5px; /* 둥근 모서리 */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
	list-style: none; /* 동그라미 제거 */
	padding: 0; /* 여백 제거 */
	white-space: nowrap; /* 한 줄로 표시 */
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

.autocomplete-dropdown {
	display: none;
	border: 1px solid #ccc;
	max-height: 200px;
	overflow-y: auto;
	position: absolute;
	background-color: white;
	z-index: 1000;
}

.autocomplete-item {
	padding: 10px;
	cursor: pointer;
}

.autocomplete-item:hover {
	background-color: #f4f4f4;
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
			<li><a href="<c:url value='/leave.do' />"><i
					class="fas fa-clipboard"></i> 휴 가</a></li>
			<li><a href="javascript:void(0);" class="active"
				onclick="toggleDropdown(this);"><i class="fas fa-clipboard"></i>
					이메일</a>
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
		<div class="content-title">이메일 전송</div>

		<!-- 이메일 전송 폼 -->
		<form class="email-form" action="<c:url value='/email/sending.do' />"
			method="post" enctype="multipart/form-data">
			<label for="recipientEmail">수신자 이메일:</label> <input type="email"
				id="recipientEmail" name="recipientEmail" required />
			<div id="autocompleteDropdown" class="autocomplete-dropdown"></div>

			<label for="subject">제목:</label> <input type="text" id="subject"
				name="subject" required /> <label for="body">내용:</label>
			<textarea id="body" name="body" required></textarea>

			<label for="file">첨부파일 (선택):</label> <input type="file" id="file"
				name="file" />

			<div class="btn-group">
				<button type="submit" class="btn blue">전송</button>
				<a href="<c:url value='/email/list.do' />" class="btn red">취소</a>
			</div>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
     console.log("AJAX URL: " + "<c:url value='/email/searchRecipient.do' />");

	    $("#autocompleteDropdown").empty(); // 기존 목록 초기화
	    
        $(document).ready(function() {
        	 // 기존 이벤트 제거 후 다시 바인딩
              $("#recipientEmail").off("input").on("input", function() {
                let keyword = $(this).val();
                if (keyword.length > 2) {  // 3자 이상 입력 시 검색
                	console.log("Current keyword: ", keyword);
                    $.ajax({
                    	url: "<c:url value='/email/searchRecipient.do' />",
                        method: "GET",
                        dataType: "json", // 응답 타입을 JSON으로 설정
                        cache: false, // 캐싱 방지
                        data: { keyword: keyword },
                        contentType: "application/json; charset=utf-8",
                        success: function(response) {
                        	console.log("Received response: ", response); // 전체 구조 확인
                        	 response.forEach(function(employee, index) {
                        	     // console.log(`Employee ${index}: `, employee); // 각 개별 객체 출력
                        	      //console.log("empEmail: " + employee.empEmail + ", empName: " + employee.empName);
                             });
                        	
                        	
                            let dropdown = $("#autocompleteDropdown");
                            dropdown.empty(); // 기존 목록 비우기
                            
                            if (response && response.length > 0) {
                                dropdown.show(); // 결과 있으면 드롭다운 표시
                                let dropdownContent = "";
                                response.forEach(function(employee, index) {
                                    console.log(`Employee ${index}: `, employee);  // 각 employee 객체의 전체 구조 출력
                                    
                                    console.log("empEmail: ", employee.empEmail);  // empEmail 값 출력
                                    console.log("empName: ", employee.empName);    // empName 값 출력
                                    
                                    // 변수 값 확인
                                    let emails = employee.empEmail !== undefined ? employee.empEmail : "[No Email]";
                                    let names = employee.empName !== undefined ? employee.empName : "[No Name]";
                                    
                                    console.log("empEmail: ", emails);  // empEmail 확인
                                    console.log("empName: ", names);    // empName 확인
                                    
                                    // 템플릿 문자열을 사용하여 값 조합
                                   let dropdownText = emails + " (" + names + ")";

                                    
                                    console.log("Adding: " + emails + " (" + names + ")"); //추가 전 확인
                                    console.log("dropdownText: " + dropdownText);  // 최종 결합된 문자열 확인
                                    
                                 // 기존에 생성한 dropdownContent에 HTML을 추가
                                 // 템플릿 리터럴 내에서 JSP EL과 충돌 방지
                                    dropdownContent += `<div class="autocomplete-item">\${emails} (\${names})</div>`;

                                });

                                $("#autocompleteDropdown").html(dropdownContent).show(); // 기존 방식보다 신뢰성 높음
                                
                            } else {
                                dropdown.hide(); // 결과 없으면 숨김
                            }
                        },
                        error: function() {
                            console.error("이메일 검색 오류 발생");
                        }
                    });
                } else {
                    $("#autocompleteDropdown").hide(); // 입력이 짧으면 드롭다운 숨기기
                }
            });

            // 검색된 이메일 항목 클릭 시 입력란에 자동 채우기
            $(document).on("click", ".autocomplete-item", function() {
                $("#recipientEmail").val($(this).text().split(" ")[0]); // 이메일만 입력란에 설정
                $("#autocompleteDropdown").hide(); // 드롭다운 숨기기
            });

            // 다른 영역 클릭 시 드롭다운 숨기기
            $(document).click(function(e) {
                if (!$(e.target).closest("#autocompleteDropdown, #recipientEmail").length) {
                    $("#autocompleteDropdown").hide();
                }
            });
        });
    </script>
	<script>
        function toggleDropdown(element) {
            const dropdown = element.nextElementSibling; // 드롭다운 메뉴
            if (dropdown.style.display === "block") {
                dropdown.style.display = "none"; // 이미 열려있으면 닫기
            } else {
                dropdown.style.display = "block"; // 열기
            }
        }

        function hideDropdown() {
            const dropdowns = document.querySelectorAll('.dropdown-menu');
            dropdowns.forEach(dropdown => {
                dropdown.style.display = "none"; // 모든 드롭다운 닫기
            });
        }

        // 페이지 로드 시 드롭다운을 자동으로 닫기
        window.onclick = function(event) {
            if (!event.target.matches('.active')) {
                hideDropdown();
            }
        }
    </script>
</body>
</html>
