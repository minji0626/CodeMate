<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="main_header">
	<ul id="header_left">
		<!-- 로그인 여부무관 -->
		<li><a
			href="${pageContext.request.contextPath}/cboard/community.do">커뮤니티</a>
			/ <a
			href="${pageContext.request.contextPath}/rboard/teammateRecruitForm.do">팀원
				구하기</a></li>
	</ul>

	<div id="main_logo">
		<a href="${pageContext.request.contextPath}/main/main.do"> <img
			id="logo_pic" src="${pageContext.request.contextPath}/images/로고1.png"
			height="80" width="80"> CODEMATE
		</a>
	</div>
	<!-- end of div#main_logo -->
	
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
			<div>
				<div class="login_profile">
					<img id="profile_pic"
						src="${pageContext.request.contextPath}/images/face.png"
						height="40" width="40"> <span>닉네임</span>
					<button id="header_more">
						<img
							src="${pageContext.request.contextPath}/images/header_icon.png"
							id="header_icon">
					</button>
					<div class="dropdown_header_menu">
						<a href="${pageContext.request.contextPath}/member/mateProfile.do"
							class="header_mate_profile">메이트프로필</a> <a
							href="${pageContext.request.contextPath}/member/modifyUserForm.do"
							id="header_my_page">마이페이지</a> <a
							href="${pageContext.request.contextPath}/member/logout.do"
							id="header_logout">로그아웃</a>
					</div>

				</div>
			</div>
			<!-- end of div menu-header -->
		</c:if>
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
</script>