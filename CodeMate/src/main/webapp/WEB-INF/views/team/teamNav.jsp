<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
@charset "utf-8";
a{
	text-decoration: none;
}
/* nav 전체 container 지정 */
.left-outer {
	z-index: 1000;
	background-color: #E7E7E7;
	border-radius: 0 30px 30px 0;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
	position: fixed;
	left: 0;
	top: 0;
	width: 12%;
	height: 100vh;
	display: flex;
	flex-direction: column;
	align-items: center;
}

/* 메뉴가 표시되는 menu-bar */
.menu-bar {
	margin-top: 20px;
	width: 100%;
	padding-top: 5px;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.menu-item {
	padding: 10px 30px;
	color: #333333;
	text-decoration: none;
	display: block;
	border-radius: 10px;
	transition: background-color 0.3s ease;
	width: 80%;
	text-align: center;
}

.menu-item:hover {
	background-color: #f5f5f5;
}

.profile {
	width: 86%;
	height: 200px;
	border-radius: 8%;
	background: #FFFFFF;
	margin-top: 30px;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	position: relative;
}

/* 프로필 사진 */
#profile-img {
	margin: 15px auto;
	border-radius: 50%;
	width: 33%;
	height: auto;
}

/* 팀원, 팀장 지위 (flexbox 이용하여 수평, 수직 정렬) */
.team_status {
	width: 17%;
	height: 10%;
	font-size: 12px;
	font-weight: bold;
	color: #3D3D3D;
	border-radius: 294px;
	background: #D8D8D8;
	margin-top: 8px;
}

.team_nickname {
	font-size: 20px;
	font-weight: bold;
	color: #3D3D3D;
	margin-top: 10px;
}

.pj_close {
	margin-top: 200%;
}

.pj_close_btn {
	border: none;
	cursor: pointer;
	background-color: #E7E7E7;
	padding: 10px;
	border-radius: 20px;
}

.pj_close_btn:hover {
	background-color: #FF2910;
	transition: background-color 0.3s ease;
	color: white;
}

</style>
<script>
document.addEventListener("DOMContentLoaded", function() {
    var closeBtn = document.querySelector(".pj_close_btn");
    closeBtn.addEventListener("click", function() {
        var closeForm = document.querySelector(".pj_close");
        closeForm.submit();
    });
});
</script>

<div class="left-outer">
    <div class="profile">
    	<c:if test="${!empty mem_num && !empty mem_photo}">
			<img src="${pageContext.request.contextPath}/upload/${mem_photo}" id="profile-img" width="55" height="55">
		</c:if>
		<c:if test="${!empty mem_num && empty mem_photo}">
			<img src="${pageContext.request.contextPath}/images/face.png" id="profile-img" width="55" height="55">
		</c:if>
        <div class="team_status">
        	<c:if test="${tm_auth == 3 }">
        	<p style="display: flex; justify-content: center;align-items: center;">팀원</p>
        	</c:if>
        	<c:if test="${tm_auth == 4 }">
        	<p style="display: flex; justify-content: center;align-items: center;">팀장</p>
        	</c:if>
        </div>
        <span class="team_nickname">${mem_nickname}</span>
        <a href="${pageContext.request.contextPath}/member/myWrite.do">
        </a>
    </div>
    <!-- 외부 왼쪽 컨테이너 시작 -->
    <div class="menu-bar">
        <a href="${pageContext.request.contextPath}/team/teamTo_Do.do?team_num=${team_num}" class="menu-item">캘린더 & To-Do</a>
        <a href="${pageContext.request.contextPath}/team/teamBoardList.do?team_num=${team_num}" class="menu-item">팀 게시판</a>
        <a href="${pageContext.request.contextPath}/team/teamSetting.do?team_num=${team_num}" class="menu-item">팀 설정</a>
        <!-- 다른 메뉴 항목들 추가 -->
    </div>
    <c:if test="${tm_auth == 4 }">
    <form action="endProject.do" class="pj_close" method="post">
    	<input type="hidden" name="team_num" value="${team_num}">
    	<input type="button" value="프로젝트 종료" class="pj_close_btn">
    </form>
    </c:if>
</div>
