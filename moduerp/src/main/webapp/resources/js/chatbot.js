// Get chatbot elements
const chatbot = document.getElementById('chatbot');
const conversation = document.getElementById('conversation');
const inputForm = document.getElementById('input-form');
const inputField = document.getElementById('input-field');
const checkboxes = document.querySelectorAll('input[type="checkbox"]');

// Add event listener to input form
inputForm.addEventListener('submit', function(event) {
  // Prevent form submission
  event.preventDefault();

  // Get user input
  const input = inputField.value;

  // Get checked checkboxes
  const checkedItems = [];
  checkboxes.forEach(function(checkbox) {
    if (checkbox.checked) {
      checkedItems.push(checkbox.value);
    }
  });

  // Clear input field
  inputField.value = '';
  const currentTime = new Date().toLocaleTimeString([], { hour: '2-digit', minute: "2-digit" });

  // Add user input to conversation
  let message = document.createElement('div');
  message.classList.add('chatbot-message', 'user-message');
  message.innerHTML = `<p class="chatbot-text" sentTime="${currentTime}">${input}</p>`;
  conversation.appendChild(message);

  // Generate chatbot response
  const response = generateResponse(input, checkedItems);

  // Add chatbot response to conversation
  message = document.createElement('div');
  message.classList.add('chatbot-message','chatbot');
  message.innerHTML = `<p class="chatbot-text" sentTime="${currentTime}">${response}</p>`;
  conversation.appendChild(message);
  message.scrollIntoView({behavior: "smooth"});
});

// Generate chatbot response function
function generateResponse(input, checkedItems) {
    // Add chatbot logic here
    let response = "";

    // Check for specific conditions
    if (input.includes("안녕")) {
        response = "안녕하세요! 반가워요. 😊";
    } else if (input.includes("카테고리")) {
        response = "인사관리, 그룹웨어, 생산, 구매, 영업, 차량 중에 무엇을 원하시나요?";
    } else if (input.includes("인사관리")) {
        response = "인사관리에는 직원관리가 있습니다!";
    } else if (input.includes("생산")) {
        response = "생산에는 생산 입고, 생산 출고, 작업지시서, 품질 관리가 있습니다!";
    } else if (input.includes("구매")) {
        response = "구매에는 구매 입고, 구매 출고, 배송 조회, 발주서가 있습니다!";
    } else if (input.includes("영업")) {
        response = "영업에는 영업 입고, 영업 출고, 거래처 관리가 있습니다!";
    } else if (input.includes("차량")) {
        response = "차량에는 차량 정보, 차량 예약, 차량 결제 관리, 도로 교통 및 경로 조회가 있습니다!";
    } else if (input.includes("그룹웨어")) {
        response = "그룹웨어에는 출퇴근, 근태문서, 이메일이 있습니다!";
    } 
     else {
        // Default response
        const responses = [
            "정확한 질문을 입력해주세요"
        ];
        response = responses[Math.floor(Math.random() * responses.length)];
    }

    return response;
}
