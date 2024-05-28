<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메이트 프로필 모달</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cje.mpm.css" type="text/css">

<style>
/* 여기에 CSS 코드를 추가하세요 */
</style>
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
                    <div class="photo_div">
                        <img src="${pageContext.request.contextPath}/upload/${mem_photo}" class="profile_image">
                    </div> 
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
            	메이트 프로필
                <input type="radio" id="mp_state_0" name="mp_state" value="비공개" <c:if test="${member.mp_state == 0}">checked</c:if>>
				<label for="mp_state_0">비공개</label>
				
				<input type="radio" id="mp_state_1" name="mp_state" value="공개" <c:if test="${member.mp_state == 1}">checked</c:if>>
				<label for="mp_state_1">공개</label>

                <div class="mp_content_div">
                    <h4>포지션</h4>
                    <div class="mp_content">
                    	<input type="text" name="mp_position" id="mp_position" <c:if test="${!empty member.mp_position}">value="${member.mp_position}"</c:if> class="in_position">
                    </div>
                </div>
                <!-- 자기소개 DIV -->
                <div class="mp_content_div">
                    <h4>자기소개</h4>
                    <div class="mp_content">
                        <textarea name="mp_introduce" id="mp_introduce" class="in_introduce"> <c:if test="${!empty member.mp_introduce}">${member.mp_introduce}</c:if> </textarea>
                    </div>
                </div>
                <!-- 스킬 DIV -->
               <div class="skill-container">
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
                <div class="mp_content_div">
                    <h4>프로젝트 경험</h4>
                    <div class="mp_project">
                        <span class="pjct">프로젝트 분류</span>  
                        <input type="radio" id="mt_category_0" name="mt_category" value="개인">
                        <label for="mt_category_0">개인</label>

                        <input type="radio" id="mt_category_1" name="mt_category" value="기업">
                        <label for="mt_category_1">기업</label>

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
                    <input type="button" id="close-btn" value="취소">
                </div>
            </form>
        </div>
        </div>
    </div>
</div>
<script type="text/javascript">
//하드스킬 셀렉트 박스 이벤트 리스너
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
</script>
</body>
</html>