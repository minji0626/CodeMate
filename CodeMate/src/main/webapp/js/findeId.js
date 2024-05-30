document.getElementById('idForm').addEventListener('submit', function(event) {
  event.preventDefault(); // 폼의 기본 동작을 막음
  
  var email = document.getElementById('emailInput').value;
  var foundId = findIdByEmail(email); // 이메일을 기준으로 아이디 찾기

  var resultElement = document.getElementById('result');
  if (foundId) {
    resultElement.textContent = '찾은 아이디: ' + foundId;
  } else {
    resultElement.textContent = '일치하는 아이디가 없습니다.';
  }
});

function findIdByEmail(email) {
  // 여기에 이메일을 기준으로 아이디를 찾는 로직을 작성
}