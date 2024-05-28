<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="main_header">
	<div id="main_header_menu">
			<!-- 로그인 여부무관 -->
			<div id="header_left">
			<a href="${pageContext.request.contextPath}/cboard/community.do">커뮤니티</a> / 
			<a href="${pageContext.request.contextPath}/rboard/teammateRecruitForm.do"> 팀원 구하기</a>
			</div>
			
			<div id="main_logo">
			<a href="${pageContext.request.contextPath}/main/main.do" class="logo-a"> 
			<img id="logo_pic" src="${pageContext.request.contextPath}/images/로고1.png" height="80"width="80"> CODEMATE
			</a>
			</div>

		<div id="header_right">
			<!-- 로그인 X -->
			<c:if test="${empty mem_num}">
				<ul class="menu-header" style="list-style: none;">
					<li><a
						href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a>
						/ <a
						href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a>
					</li>
				</ul>
			</c:if>

			<!-- 로그인 O -->
			<c:if test="${!empty mem_num}">
					<div class="login_profile">
						<img id="profile_pic"
							src="${pageContext.request.contextPath}/images/face.png"
							height="40" width="40"> <span>${mem_id}</span>
						<button id="header_more">
							<img
								src="${pageContext.request.contextPath}/images/header_icon.png"
								id="header_icon">
						</button>
						<div class="dropdown_header_menu">
							<a href="${pageContext.request.contextPath}/member/mateProfile.do" class="header_mate_profile">메이트프로필</a>
							<a href="${pageContext.request.contextPath}/member/modifyUserForm.do"
								id="header_my_page">마이페이지</a> <a
								href="${pageContext.request.contextPath}/member/logout.do"
								id="header_logout">로그아웃</a>
						</div>
					</div>
				<!-- end of div menu-header -->
			</c:if>
		</div> <!-- end of div header right -->
	</div>
</div>


<!-- 메이트 프로필 -->
	<div id="mp_view" style="display:none;">
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
					<div class="mp_view_modify">
						<span>수정하기</span>
					</div>	
				</div>
				<!-- 포지션 DIV -->
				<!-- 
아마 여기 p 태그들에 클래스 부여해야 할 듯
				 -->
				<div class="mp_content_div">
					<h4>포지션</h4>
					<div class="mp_content">
						<p>
						</p>
					</div>
				</div>
				<!-- 자기소개 DIV -->
				<div class="mp_content_div">
					<h4>자기소개</h4>
					<div class="mp_content">
						<p>
						
						</p>
					</div>
				</div>
				<!-- 스킬 DIV -->
				<div class="skill-container">
					<!-- 하드스킬 DIV -->
					<div class="mp_content_div skill-item">
						<h4>하드스킬</h4>
						<div class="skill-content">
							<p>
								<!-- 내용 -->
							</p>
						</div>
					</div>
					<!-- 소프트스킬 DIV -->
					<div class="mp_content_div skill-item">
						<h4>소프트스킬</h4>
						<div class="skill-content">
							<p>
								<!-- 내용 -->
							</p>
						</div>
					</div>
				</div>
				<!-- 프로젝트 경험 DIV -->
				<div class="mp_content_div">
					<h4>프로젝트 경험</h4>
					<div class="mp_project">
						<span class="pj_category"> 프로젝트 분류</span>
						<h4 class="pj_name">프로젝트 이름</h4>
						<span class="pj_period">프로젝트 기간</span>
						<p class="pj_content">프로젝트 설명</p>
					</div>
				</div>
				<!-- 코드메이트 후기 DIV -->
				<div class="mp_content_div">
					<h4>메이트 후기</h4>
					<div class="mp_mate_review">
						<p>
							<!-- 내용 -->
						</p>
					</div>
				</div>
				<!-- 닫기 버튼 -->
				<div class="mp_view_close" id="close-btn">
				<span>닫기</span>
				</div>
			</div>
		</div>
	</div>


<script>
document.addEventListener('DOMContentLoaded', function() {
    const headerButton = document.getElementById("header_more");

    headerButton.addEventListener('click', function(event) {
        const dropdownMenu = this.nextElementSibling;
        dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';

        // 다른 드롭다운 메뉴 숨기기
        document.querySelectorAll('.dropdown_header_menu').forEach(menu => {
            if (menu !== dropdownMenu) {
                menu.style.display = 'none';
            }
        });

        event.stopPropagation();
    });

    // 페이지를 클릭하면 모든 드롭다운 메뉴 숨기기
    document.addEventListener('click', function() {
        document.querySelectorAll('.dropdown_header_menu').forEach(menu => {
            menu.style.display = 'none';
        });
    });

    // 드롭다운 메뉴 클릭 시 이벤트 전파 막기
    document.querySelectorAll('.dropdown_header_menu').forEach(menu => {
        menu.addEventListener('click', function(event) {
            event.stopPropagation();
        });
    });
});

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
</script>
