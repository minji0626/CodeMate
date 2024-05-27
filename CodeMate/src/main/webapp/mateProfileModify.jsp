<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메이트 프로필 모달</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cje.mpm.css" type="text/css">

</head>
<body>
<body>
    <div>
        <button id="btn-modal">모달창 열려랏!</button>
    </div>

    <!-- 메이트 프로필 -->
    <div id="mp_view">
        <!-- 메이트 프로필 창 -->
        <div class="mp_view_window">
            <!-- 메이트 프로필 제목 -->
            <div class="mp_view_title">
                <h2>메이트 프로필</h2>
                <!-- 메이트 프로필 창 닫기 -->
                <div class="mp_view_close">X</div>
            </div>
            <!-- 메이트 프로필 본문 -->
            <div class="content">
                <!-- 본인 프로필 (닉네임, 프사) -->
                <div class="mp_account_profile">
                    <!-- 프로필 사진 -->
                    <div class="photo_div">
                        <img src="images/face.png" class="profile_image">
                    </div>  
                    <!-- 닉네임 -->
                    <div class="mp_account_nickname">
                        <span class="user_nickname">분모재</span>
                        <!-- 레벨 이미지 추가하기 -->
                        <img>
                        <br>
                        <span class="user_id">boonmojae</span>
                        <!-- 아이디 옆 이미지 추가하기 -->
                        <img>
                    </div>
                </div>
                <!-- 포지션 DIV -->
                <form id="mp_modify_form" action="">
                <div class="mp_content_div">
                    <h4>포지션</h4>
                        <input type="text" name="" id="" value="" class="in_position">
                </div>
                <!-- 자기소개 DIV -->
                <div class="mp_content_div">
                    <h4>자기소개</h4>
                    <div class="mp_content">
                        <textarea name="" id="" class="in_introduce"> </textarea>
                    </div>
                </div>
                <!-- 스킬 DIV -->
                <div class="skill-container">
                    <!-- 하드스킬 DIV -->
                    <div class="mp_content_div skill-item">
                        <h4>하드스킬</h4>
                        <div class="skill_div">
                            <select id="hs-select">
                                <option value="hs" class="hs" selected disabled >하드 스킬을 선택해주세요</option>
                                <option value="option1">HTML</option>
                                <option value="option2">CSS</option>
                                <option value="option3">JAVA SCRIPT</option>
                                <option value="option4">JAVA</option>
                                <option value="option5">JSP</option>
                            </select>
                            <div id="hs-options" class="option-container"></div>
                        </div>
                    </div>
                    <!-- 소프트스킬 DIV -->
                    <div class="mp_content_div skill-item">
                        <h4>소프트스킬</h4>
                        <div class="skill_div">
                            <select id="ss-select">
                                <option value="ss" class="ss" selected disabled >소프트 스킬을 선택해주세요</option>
                                <option value="option1">소통의 왕</option>
                                <option value="option2">시간 잘 지킴</option>
                                <option value="option3">코딩의 신</option>
                            </select>
                            <div id="ss-options" class="option-container"></div>
                        </div>
                    </div>
                </div>
                <!-- 프로젝트 경험 DIV -->
                <div class="mp_content_div">
                    <h4>프로젝트 경험</h4>
                    <div class="mp_project">
                        <span class="pjct">프로젝트 분류</span>  
                            개인 <input type="checkbox" id="mt_category_0" name="mt_category" value="개인" onclick=checkOnlyOne(this)>
							기업 <input type="checkbox" id="mt_category_1" name="mt_category" value="기업" onclick=checkOnlyOne(this)>
							<br><br>
                        <span class="pjtt">프로젝트 제목</span>
                        	<input type="text" id="mt_title" name="mt_title" value="" class="mt_input">
                        	<br><br>
                        <span class="pjpr">프로젝트 기간</span>
                        <input type="text" id="mt_period" name="mt_period" value="" class="mt_input">
                        <br><br>
                        <span class="pjct">프로젝트 설명</span>
                        <input type="text" id="mt_content" name="mt_content" value="" class="mt_input">
                        <br><br>
                    </div>
                </div>
                 <!-- 버튼 -->
		                <div class="btn-div">
		                    <input type="submit" id="modify-btn" value="수정">
		                    <div class="mate_review_close" id="close-btn">
		                        <span>취소</span>
		                    </div>
		                </div>
                </form>
            </div>
        </div>
    </div>

<script>
const mp_view = document.getElementById("mp_view");

function modalOn() {
    mp_view.style.display = "flex";
}

function isModalOn() {
    return mp_view.style.display === "flex";
}

function modalOff() {
    mp_view.style.display = "none";
}

const btnModal = document.getElementById("btn-modal");

btnModal.addEventListener("click", e => {
    modalOn();
});

// 첫 번째 닫기 버튼 이벤트 리스너 추가
document.querySelector(".mp_view_close").addEventListener("click", e => {
    modalOff();
});

// 마지막 닫기 버튼 이벤트 리스너 추가
document.getElementById("close-btn").addEventListener("click", e => {
    modalOff();
});

// 하드스킬 셀렉트 박스 이벤트 리스너
document.getElementById('hs-select').addEventListener('change', function(event) {
    const selectedOptions = Array.from(event.target.selectedOptions);
    const container = document.getElementById('hs-options');

    selectedOptions.forEach(option => {
        if (!document.querySelector(`#hs-options [data-value="${option.value}"]`)) {
            const optionDiv = document.createElement('div');
            optionDiv.classList.add('option-item');
            optionDiv.setAttribute('data-value', option.value);
            optionDiv.textContent = option.text;

            const removeBtn = document.createElement('span');
            removeBtn.classList.add('remove-btn');
            removeBtn.textContent = 'X';
            removeBtn.addEventListener('click', function() {
                option.selected = false;
                optionDiv.remove();
                // 제거 시 옵션 다시 활성화
                option.disabled = false;
            });

            optionDiv.appendChild(removeBtn);
            container.appendChild(optionDiv);

            // 선택된 옵션 비활성화
            option.disabled = true;
        }
    });

    // 중복 추가 방지를 위해 선택된 옵션 초기화
    event.target.selectedIndex = -1;
});

// 소프트스킬 셀렉트 박스 이벤트 리스너
document.getElementById('ss-select').addEventListener('change', function(event) {
    const selectedOptions = Array.from(event.target.selectedOptions);
    const container = document.getElementById('ss-options');

    selectedOptions.forEach(option => {
        if (!document.querySelector(`#ss-options [data-value="${option.value}"]`)) {
            const optionDiv = document.createElement('div');
            optionDiv.classList.add('option-item');
            optionDiv.setAttribute('data-value', option.value);
            optionDiv.textContent = option.text;

            const removeBtn = document.createElement('span');
            removeBtn.classList.add('remove-btn');
            removeBtn.textContent = 'X';
            removeBtn.addEventListener('click', function() {
                option.selected = false;
                optionDiv.remove();
                // 제거 시 옵션 다시 활성화
                option.disabled = false;
            });

            optionDiv.appendChild(removeBtn);
            container.appendChild(optionDiv);

            // 선택된 옵션 비활성화
            option.disabled = true;
        }
    });

    // 중복 추가 방지를 위해 선택된 옵션 초기화
    event.target.selectedIndex = -1;
});

// 체크박스 선택 제한 함수
function uncheckOtherCheckbox(checkboxId) {
    var checkboxes = document.getElementsByName('mt_category');
    for (var i = 0; i < checkboxes.length; i++) {
        var checkbox = checkboxes[i];
        if (checkbox.id !== checkboxId) {
            checkbox.checked = false;
        }
    }
}

function checkOnlyOne(element) {
    const checkboxes = document.getElementsByName("mt_category");

    checkboxes.forEach((cb) => {
        cb.checked = false;
    })

    element.checked = true;
}
    
    
</script>

</body>
</html>
