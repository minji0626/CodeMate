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

<style>
/***************
메이트 프로필 모달 창
***************/

/********************************
/*메이트 프로필 모달창 제외한 나머지 페이지*/
*{
	font-family: "Noto Sans KR", sans-serif;
}
#mp_view {
	width: 100%;
	height: 100%;
	position: absolute;
	left: 0;
	top: 0;
	display: none;
	align-items: center;
	justify-content: center;
	background: rgba(71, 58, 58, 0.8);
}

/********************************
/*메이트 프로필 모달 창*/
.mp_view_window {
    background: #ffffff;
    width: 800px;
    position: relative;
    border-radius: 15px;
    max-height: 80vh; /* 최대 높이를 설정 */
    overflow-y: auto; /* 수직 스크롤바를 추가 */
}

/********************************
/*메이트 프로필 모달 창 제목 바 DIV*/
.mp_view_title {
	padding: 10px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	color: black;
	background: #5ACA7C;
	border-top-left-radius: 15px;
    border-top-right-radius:15px;
}

/*메이트 프로필 모달 창 제목 글자*/
.mp_view_title h2 {
	font-size: 13pt;
	margin:0;
	margin-left:10px;
}

/*메이트 프로필 모달 창 제목의 닫기 버튼*/
.mp_view_close {
	cursor: pointer;
	color: black;
}

/********************************
/*메이트 프로필 모달 창 본문 DIV*/
.content {
	margin-top: 20px;
	padding: 0px 10px;
	color: black;
}

/********************************
/*메이트 프로필 (닉네임, 프사 있는 곳) DIV*/
.mp_account_profile {
	display: flex;
	align-items: center;
	height: 80px;
	padding-bottom: 20px;
}

/* 사용자 프사 */
.profile_image {
	margin-left: 20px;
    width: 60px;
    height: 60px;
    border-radius: 50%; /* 동그라미 모양으로 설정 */
}

/*닉네임, 아이디 있는 div*/
.mp_account_nickname {
	margin-left: 15px;
}

.user_nickname {
	color: black;
	font-size: 14pt;
	font-weight: bold;
}

.user_id {
	color: #32B0FF;
}

/********************************/
/*메이트 프로필 컨텐츠란 전체 DIV*/
.mp_content_div {
	margin-left: 20px;
	padding-top: 5px;
	margin-right: 20px;
	 
}

/*스킬 회색 박스 DIV 스타일*/
.skill_div{
	border-radius: 8px;
	background: #e0e0e0;
	min-height: 130px;
}

/*포지션 input 태그 스타일*/
.in_position {
	border-radius: 8px;
	background: #e0e0e0;
	width: 710px;
	height: 40px;
	overflow: auto;
	box-sizing: border-box;
	border:none;
}

/*자기소개 textarea 스타일*/
.in_introduce {
	border-radius: 8px;
	background: #e0e0e0;
	width: 710px;
	height: 80px;
	border:none;
}


/*************************/
/* 하드 스킬과 소프트 스킬을 감싸는 div에 대한 스타일 */
.skill-container {
	display: flex;
}

/* 각 스킬 요소에 대한 스타일 */
.skill-item {
	width: calc(46% - 10px);
	margin-right: 10px; /* 여기를 10px로 변경 */
	margin-bottom: 20px;
}

/*****************************/
/*프로젝트 경험 DIV*/
.mp_project {
	border-radius: 8px;
	border: 1px solid #adadad;
	padding-top: 20px;
}

/*****************************/
/*코드메이트 후기 DIV*/
.mp_mate_review {
	border-radius: 8px;
	background: #e0e0e0;
	border: 1px solid #adadad;
	margin-bottom: 70px;
}


/*****************************/
/*버튼*/
.btn-div {
    display: flex;
    justify-content: space-between; /* 두 버튼을 좌우로 나란히 정렬합니다 */
    align-items: center; /* 버튼을 수직으로 중앙 정렬합니다 */
    margin-top: 25px;
    margin-bottom: 40px;
    
}


/*닫기 버튼, 수정버튼*/
#close-btn, #modify-btn {
	background: black;
	width: 320px;
	height: 50px;
	display: flex;
	justify-content: center;
	align-items: center;	
	text-align: center;
	border-radius: 30px;
	margin: 0 10px; /* 버튼 간격을 조정합니다 */
}

#close-btn {
	margin-right: 10px;
}

#modify-btn{
	margin-left: 10px;
}

#close-btn span, #modify-btn {
	color: white;
	 font-size: 14pt; 
}
/*********************************/
/* 프로젝트 경험*/
.pj_category{
	color: green;
	font-size: 10pt;
	margin-left: 20px;
	padding: 0;
	padding-top:20px;
	margin-bottom: 20px;
}

.pj_name{
	margin-top: 3px;
	margin-bottom: 1px;
	margin-left: 20px;
	padding: 0;
}

.pj_period{
	color: #bdbdbd;
	font-size: 8pt;
	padding: 0;
	margin-top: 0px;
	margin-left: 20px;
	margin-bottom: 0px;
}

.pj_content{
	font-size: 11pt;
	margin-top: 0px;
	margin-left: 10px;
	
}



/*셀렉트*/
/* 스킬 선택 셀렉트 박스 스타일 */
.option-container {
    margin-top: 10px;
    border: none;
}

.option-item {
    display: inline-block;
    margin: 5px;
    padding: 5px 10px;
    background-color: #505050;
    border: none;
    border-radius: 5px;
    font-size: 9pt;
    color:white;
}

.remove-btn {
    margin-left: 10px;
    color: #FF9090; /* 수정된 부분: X 버튼 색상 변경 */
    cursor: pointer;
}

#hs-select, #ss-select {
    border: none;
    color: #bdbdbd;
    width: 200px;
    height: 30px;
    text-align: center;
    margin: 5px;
    background-color: #f0f0f0;
    border-radius: 30px;
    -webkit-appearance: none; /* 웹킷 기반 브라우저에서 화살표 숨기기 */
    -moz-appearance: none; /* 모질라 기반 브라우저에서 화살표 숨기기 */
    appearance: none; /* 표준 방법으로 화살표 숨기기 */
    background-image: none; /* 기본 화살표 이미지 숨기기 */
    background-position: right center; /* 선택된 옵션의 표시 위치 설정 */
    background-repeat: no-repeat; /* 배경 이미지 반복 없애기 */
    padding-right: 20px; /* 우측 여백을 추가하여 텍스트와 화살표 간격 유지 */
}

option {
    color: #00380d;
}

.hs {
    color: black;
}


/********************/
/*프로젝트 분류*/
.pjct, .pjtt, .pjct, .pjpr{
	font-weight: bold;
	margin-left: 20px;
	margin-right: 20px;
}

/*mt_input 인풋 박스*/
.mt_input{
	border-radius: 8px;
	background: #e0e0e0;
	border: none;
	width: 550px;
}

</style>
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

    // 첫 번째 닫기 버튼
    document.querySelector(".mp_view_close").addEventListener("click", e => {
        modalOff();
    });

    // 마지막 닫기 버튼에 대한 이벤트 리스너 추가
    document.getElementById("close-btn").addEventListener("click", e => {
        modalOff();
    });
    
    
    
    
    
 // 셀렉트 (하드스킬)
    document.getElementById('hs-select').addEventListener('change', function(event) {
        const selectedOptions = Array.from(event.target.selectedOptions);
        const container = document.getElementById('hs-options'); // 변경된 ID로 수정

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
                    // Re-enable the option when removed
                    option.disabled = false;
                });

                optionDiv.appendChild(removeBtn);
                container.appendChild(optionDiv);

                // Disable the selected option
                option.disabled = true;
            }
        });

        // Clear the selected options to avoid duplicate additions
        event.target.selectedIndex = -1;
    });

    // 셀렉트 (소프트스킬)
    document.getElementById('ss-select').addEventListener('change', function(event) {
        const selectedOptions = Array.from(event.target.selectedOptions);
        const container = document.getElementById('ss-options'); // 변경된 ID로 수정

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
                    // Re-enable the option when removed
                    option.disabled = false;
                });

                optionDiv.appendChild(removeBtn);
                container.appendChild(optionDiv);

                // Disable the selected option
                option.disabled = true;
            }
        });

        // Clear the selected options to avoid duplicate additions
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
	  
	  const checkboxes 
	      = document.getElementsByName("mt_category");
	  
	  checkboxes.forEach((cb) => {
	    cb.checked = false;
	  })
	  
	  element.checked = true;
	}

    
    
    
</script>

</body>
</html>
