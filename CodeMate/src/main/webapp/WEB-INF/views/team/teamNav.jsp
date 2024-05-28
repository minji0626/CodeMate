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
	width: 230px;
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
	height: 230px;
	border-radius: 5%;
	background: #FFFFFF;
	margin-top: 30px;
	display: flex;
	flex-direction: column;
	align-items: center;
	padding: 5px;
	position: relative;
}

/* 프로필 사진 1:1비율 유지시키기 */
#profile-img {
	margin: 15px auto;
	border-radius: 50%;
}

/* 팀원, 팀장 지위 (flexbox 이용하여 수평, 수직 정렬) */
.team_status {
	width: 17%;
	height: 10%;
	font-size: 12px;
	font-weight: bold;
	line-height: normal;
	color: #3D3D3D;
	text-align: center;
	border-radius: 294px;
	background: #D8D8D8;
	margin-top: 8px;
}

.team_nickname {
	font-size: 20px;
	font-weight: bold;
	line-height: 30px;
	color: #3D3D3D;
	margin-top: 10px;
}

.written_post {
	display: flex;
	justify-content: center;
	margin-top: 10px;
}

#post_icon {
	width: 18px;
	height: 18px;
}

.my_post {
	font-size: 10px;
	font-weight: bold;
	line-height: normal;
	color: #3D3D3D;
	margin-left: 5px;
}

.my_post_cnt {
	font-size: 10px;
	font-weight: bold;
	line-height: normal;
	color: #3D3D3D;
	margin-left: 5px;
}
</style>
<div class="left-outer">
    <div class="profile">
    	<c:if test="${!empty mem_num && !empty mem_photo}">
			<img src="${pageContext.request.contextPath}/upload/${mem_photo}" id="profile-img" width="55" height="55">
		</c:if>
		<c:if test="${!empty mem_num && empty mem_photo}">
			<img src="${pageContext.request.contextPath}/images/face.png" id="profile-img" width="55" height="55">
		</c:if>
        <div class="team_status">
        	<p>팀원</p>
        </div>
        <span class="team_nickname">${mem_id}</span>
        <a href="${pageContext.request.contextPath}/member/myWrite.do">
        <div class="written_post">
            <img src="${pageContext.request.contextPath}/images/cmj/written_post.png" alt="내가 쓴 글" id="post_icon">
            <span class="my_post">내가 쓴 글</span>
            <span class="my_post_cnt">20개</span>
        </div>
        </a>
    </div>
    <!-- 외부 왼쪽 컨테이너 시작 -->
    <div class="menu-bar">
        <a href="${pageContext.request.contextPath}/team/teamTo_Do.do" class="menu-item">캘린더 & To-Do</a>
        <a href="${pageContext.request.contextPath}/team/teamBoard.do" class="menu-item">팀 게시판</a>
        <a href="${pageContext.request.contextPath}/team/teamSetting.do" class="menu-item">팀 설정</a>
        <!-- 다른 메뉴 항목들 추가 -->
    </div>
</div>
