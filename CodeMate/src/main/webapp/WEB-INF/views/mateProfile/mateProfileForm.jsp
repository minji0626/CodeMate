<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메이트 프로필 수정</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cje.mpm.css" type="text/css">


</head>
<body>
<div class="page-container">
		
			<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<!-- 메이트 프로필 -->
<div id="mp_view">
    <!-- 메이트 프로필 창 -->
    <div class="mp_view_window">
        <!-- 메이트 프로필 제목 -->
        <div class="mp_view_title">
            <h2>메이트 프로필</h2>
        </div>
        <!-- 메이트 프로필 본문 -->
        <div class="content">
            <!-- 본인 프로필 (닉네임, 프사) -->
            <div class="mp_account_profile">
                <!-- 프로필 사진 -->
                    <c:if test="${empty mem.mem_photo}">
						<img src="${pageContext.request.contextPath}/images/face.png" 
											class="profile_image">
					</c:if>
					<c:if test="${!empty mem.mem_photo}">
						<img src="${pageContext.request.contextPath}/upload/${mem.mem_photo}" 
												class="profile_image">
					</c:if>
                <!-- 닉네임 -->
                <div class="mp_account_nickname">
                    <span class="user_nickname">${mem_nickname}</span>
                    <!-- 레벨 이미지 추가하기 -->
                    <img>
                    <br>
                    <span class="user_id">${mem_id}</span>
                </div>
            </div>
            <!-- 포지션 DIV -->
            <form id="mpModifyForm" action="mateProfileModify.do" method="post">
            	<input type="hidden" name="mem_num" value="${mem.mem_num}">
            	
            	<div class="mp_content_div div2">
                    <h4>메이트 프로필</h4>
	                    <div class="radioDiv">
		                    <input type="radio" id="mp_state_0" name="mp_state" value="비공개" <c:if test="${member.mp_state == 0}">checked</c:if>>
							<label for="mp_state_0">비공개</label>	
							<input type="radio" id="mp_state_1" name="mp_state" value="공개" <c:if test="${member.mp_state == 1}">checked</c:if>>
							<label for="mp_state_1">공개</label>
	                    </div>
                </div>
                

                <div class="mp_content_div">
                    <h4>포지션</h4>
                    <div class="">
                      <textarea name="mp_position" id="mp_position" class="in_introduce"> <c:if test="${!empty member.mp_position}">${member.mp_position}</c:if></textarea>
                    </div>
                </div>
                <!-- 자기소개 DIV -->
                <div class="mp_content_div">
                    <h4>자기소개</h4>
                    <div class="">
                        <textarea name="mp_introduce" id="mp_introduce" class="in_introduce"><c:if test="${!empty member.mp_introduce}">${member.mp_introduce}</c:if></textarea>
                    </div>
                </div>
                <!-- 스킬 DIV -->
               <div class="skill-container">
                    <div class="mp_content_div skill-item">
                        <h4>하드스킬</h4>
                        <div class="skill_div">
							<div id="scrollable_trigger" class="hs-select">하드 스킬을 선택해주세요.</div>
							<ul class="scrollable">
								<c:forEach var="hskill" items="${hskillList}">
							       <li class="block">
							            <input type="checkbox" name="hs_codes" id="hs_codes_${hskill.hs_code}" value="${hskill.hs_code}" 
							                <c:if test="${checkedHardSkills.contains(hskill.hs_code)}">checked</c:if>>
							            <label for="hs_codes_${hskill.hs_code}">
							                <img class="hskill-photo" src="${pageContext.request.contextPath}/images/hard_skill_logo/${hskill.hs_photo}">
							                ${hskill.hs_name}
							            </label>
							        </li>
							    </c:forEach>
							</ul>
                            <div id="hs-options" class="option-container"></div>
                        </div>
                    </div>
                    <div class="mp_content_div skill-item">
                        <h4>소프트스킬</h4>
                        <div class="skill_div">
                            <div id="scrollable_trigger" class="ss-select">소프트 스킬을 선택해주세요.</div>
							<ul class="scrollable">
								<c:forEach var="sskill" items="${sskillList}">
							       <li class="block">
							            <input type="checkbox" name="ss_codes" id="ss_codes_${sskill.ss_code}" value="${sskill.ss_code}" 
							                <c:if test="${checkedSoftSkills.contains(sskill.ss_code)}">checked</c:if>>
							            <label for="ss_codes_${hskill.hs_code}">
							                ${sskill.ss_name}
							            </label>
							        </li>
							    </c:forEach>
							</ul>
                            <div id="ss-options" class="option-container"></div>
                        </div>
                    </div>
                </div>
                
                <!-- 버튼 -->
                <div class="btn-div">
                    <input type="submit" id="modify-btn" value="수정">
                    <input type="button" id="close-btn" value="취소" onclick="location.href='mateProfile.do?mem_num=${user_num}'">
                    
                </div>
            </form>
            <form id="meInsertForm" action="insertEXP.do" method="post">
				<input type="hidden" name="mem_num" value="${user_num}">            
		            <div class="mp_content_div">
	                    <h4>프로젝트 경험</h4>
	                    <div class="mp_project">
		                        <span class="pjct">프로젝트 분류</span>  
		                        <input type="radio" id="me_category_0" name="me_category" value="개인">
		                        <label for="me_category_0">개인</label>
	                        <input type="radio" id="me_category_1" name="me_category" value="기업">
	                        <label for="me_category_1">기업</label>
	
	                        <br><br>
	                        <div class="inputinput">
		                        <span class="pjtt">프로젝트 제목</span>
		                        <input type="text" id="me_title" name="me_title" class="mt_input" style="width:80%; height:40px;">
	                        </div>
	                        <br><br>
	                        <div class="inputinput">
		                        <span class="pjct">프로젝트 설명</span>
		                       <input type="text" id="me_content" name="me_content" class="mt_input" style=" width:80%; height: 200px;">

	                        </div>
	                        <br><br>
	                        <div class="btn-div">
	                    		<input type="submit" id="insert-btn" value="추가">
	                		</div>
	                    </div>
	                </div> 
            </form>
        </div>
        </div>
    </div>
</div>
<script type="text/javascript">
document.querySelectorAll('input[name="hs_codes"]').forEach(checkbox => {
    checkbox.addEventListener('change', function(event) {
        const option = event.target;
        const container = document.getElementById('hs-options');
        const label = option.nextElementSibling.textContent.trim();
        let optionDiv = document.querySelector(`#hs-options [data-value="${option.value}"]`);

        if (option.checked) {
            if (!optionDiv) {
                optionDiv = document.createElement('div');
                optionDiv.classList.add('option-item');
                optionDiv.setAttribute('data-value', option.value);
                optionDiv.textContent = label;

                const removeBtn = document.createElement('span');
                removeBtn.classList.add('remove-btn');
                removeBtn.textContent = 'X';

                removeBtn.addEventListener('click', function() {
                    option.checked = false;
                    option.disabled = false; // 체크박스 해제 후 다시 활성화
                    optionDiv.remove();
                });

                optionDiv.appendChild(removeBtn);
                // 새로운 div가 마지막에 추가되도록 appendChild 대신 insertBefore 사용
                container.insertBefore(optionDiv, null);

                option.disabled = true; // 체크박스 비활성화
            }
        } else {
            option.checked = true; // 체크박스가 해제되지 않도록 유지
        }
    });
});

// 추가된 하드 스킬에 대해 처리하는 함수
function handleAddedHardSkills() {
    const container = document.getElementById('hs-options');
    const checkboxes = document.querySelectorAll('input[name="hs_codes"]:checked');
    
    checkboxes.forEach(checkbox => {
        const value = checkbox.value;
        const label = checkbox.nextElementSibling.textContent.trim();
        let optionDiv = document.querySelector(`#hs-options [data-value="${value}"]`);

        if (!optionDiv) {
            optionDiv = document.createElement('div');
            optionDiv.classList.add('option-item');
            optionDiv.setAttribute('data-value', value);
            optionDiv.textContent = label;

            const removeBtn = document.createElement('span');
            removeBtn.classList.add('remove-btn');
            removeBtn.textContent = 'X';

            removeBtn.addEventListener('click', function() {
                checkbox.checked = false;
                checkbox.disabled = false; // 체크박스 해제 후 다시 활성화
                optionDiv.remove();
            });

            optionDiv.appendChild(removeBtn);
            // 새로운 div가 마지막에 추가되도록 appendChild 대신 insertBefore 사용
            container.insertBefore(optionDiv, null);

            checkbox.disabled = true; // 체크박스 비활성화
        }
    });
}

// 페이지 로드 시 추가된 하드 스킬에 대한 처리 실행
handleAddedHardSkills();

// 제출시 disabled 해제
document.getElementById('mpModifyForm').addEventListener('submit', function() {
    document.querySelectorAll('input[name="hs_codes"]:disabled').forEach(checkbox => {
        checkbox.disabled = false; // 폼 제출 전에 체크박스를 다시 활성화
    });
});


document.querySelectorAll('input[name="ss_codes"]').forEach(checkbox => {
    checkbox.addEventListener('change', function(event) {
        const option = event.target;
        const container = document.getElementById('ss-options');
        const label = option.nextElementSibling.textContent.trim();
        let optionDiv = document.querySelector(`#hs-options [data-value="${option.value}"]`);

        if (option.checked) {
            if (!optionDiv) {
                optionDiv = document.createElement('div');
                optionDiv.classList.add('option-item');
                optionDiv.setAttribute('data-value', option.value);
                optionDiv.textContent = label;

                const removeBtn = document.createElement('span');
                removeBtn.classList.add('remove-btn');
                removeBtn.textContent = 'X';

                removeBtn.addEventListener('click', function() {
                    option.checked = false;
                    option.disabled = false; // 체크박스 해제 후 다시 활성화
                    optionDiv.remove();
                });

                optionDiv.appendChild(removeBtn);
                // 새로운 div가 마지막에 추가되도록 appendChild 대신 insertBefore 사용
                container.insertBefore(optionDiv, null);

                option.disabled = true; // 체크박스 비활성화
            }
        } else {
            option.checked = true; // 체크박스가 해제되지 않도록 유지
        }
    });
});

// 추가된 하드 스킬에 대해 처리하는 함수
function handleAddedSoftSkills() {
    const container = document.getElementById('ss-options');
    const checkboxes = document.querySelectorAll('input[name="ss_codes"]:checked');
    
    checkboxes.forEach(checkbox => {
        const value = checkbox.value;
        const label = checkbox.nextElementSibling.textContent.trim();
        let optionDiv = document.querySelector(`#hs-options [data-value="${value}"]`);

        if (!optionDiv) {
            optionDiv = document.createElement('div');
            optionDiv.classList.add('option-item');
            optionDiv.setAttribute('data-value', value);
            optionDiv.textContent = label;

            const removeBtn = document.createElement('span');
            removeBtn.classList.add('remove-btn');
            removeBtn.textContent = 'X';

            removeBtn.addEventListener('click', function() {
                checkbox.checked = false;
                checkbox.disabled = false; // 체크박스 해제 후 다시 활성화
                optionDiv.remove();
            });

            optionDiv.appendChild(removeBtn);
            // 새로운 div가 마지막에 추가되도록 appendChild 대신 insertBefore 사용
            container.insertBefore(optionDiv, null);

            checkbox.disabled = true; // 체크박스 비활성화
        }
    });
}

// 페이지 로드 시 추가된 하드 스킬에 대한 처리 실행
handleAddedSoftSkills();

// 제출시 disabled 해제
document.getElementById('mpModifyForm').addEventListener('submit', function() {
    document.querySelectorAll('input[name="ss_codes"]:disabled').forEach(checkbox => {
        checkbox.disabled = false; // 폼 제출 전에 체크박스를 다시 활성화
    });
});


//HTML 요소 가져오기
var categoryRadios = document.getElementsByName("me_category");
var titleInput = document.getElementById("me_title");
var contentInput = document.getElementById("me_content");
var submitButton = document.getElementById("insert-btn");

//입력값 검증 함수
function validateInputs() {
 var filledCount = 0;

 // 입력값이 비어있지 않은 경우 카운트 증가
 if (titleInput.value.trim() !== "") {
     filledCount++;
 }
 if (contentInput.value.trim() !== "") {
     filledCount++;
 }

 // "프로젝트 분류" 중 하나가 선택되었는지 확인
 var categoryChecked = false;
 for (var i = 0; i < categoryRadios.length; i++) {
     if (categoryRadios[i].checked) {
         categoryChecked = true;
         break;
     }
 }

 // 필수 입력 조건을 충족하는 경우 제출 버튼 활성화
 if (categoryChecked && filledCount >= 2) {
     submitButton.disabled = false;
 } else {
     submitButton.disabled = true;
 }

 // 모든 필드가 비어있는 경우 제출 버튼 비활성화
 if (titleInput.value.trim() === "" && contentInput.value.trim() === "" && !categoryChecked) {
     submitButton.disabled = true;
 }
}

//입력값 변화 감지를 위한 이벤트 리스너 추가
for (var i = 0; i < categoryRadios.length; i++) {
 categoryRadios[i].addEventListener("change", validateInputs);
}
titleInput.addEventListener("input", validateInputs);
contentInput.addEventListener("input", validateInputs);

//페이지 로드 시에도 검증 수행
validateInputs();

</script>
</body>
</html>