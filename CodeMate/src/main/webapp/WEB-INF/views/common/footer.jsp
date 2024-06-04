<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
/* 기본 스타일 */
.footer {
	position: fixed;
	bottom: 0;
	width: 100%;
	background-color: #f8f9fa;
	padding: 10px;
}

.flex-container {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
	align-content: center;
}

.main_page, .my_page, .find_team, .community {
	margin: 10px;
	box-sizing: border-box; 
}

.main_page b, .my_page b, .find_team b, .community b {
	font-size: 15px;
}

.my-page, .find-team, .community-name {
	margin-bottom: 20px;
}

li {
	margin-bottom: 8px;
	margin-left: 8px;
}

.my_page ul li a {
	transition: transform 0.3s;
	text-decoration: none;
}

.my_page ul li a:hover {
	transform: scale(1.05);
}

.logo-container {
	display: flex;
	justify-content: flex-start;
	align-items: center;
	font-size: 25px;
	font-weight: bold;
}

.social-contact {
	margin-left: auto;
	display: flex;
	align-items: center;
}

#social_img {
	margin-right: 8px;
	height: 30px;
	width: 30px;
}

ul {
	font-size: 13px;
}

</style>
<footer>
<div class="page-container">
	<div class="logo-container">
	<img id="logo_pic" src="${pageContext.request.contextPath}/images/로고1.png" height="80"width="80"> CODEMATE

		<div class="social-contact">
		<a href="#"><img id="social_img" src="${pageContext.request.contextPath}/images/facebook.png"></a>
		<a href="https://www.instagram.com/sist3482/"><img id="social_img" src="${pageContext.request.contextPath}/images/instagram.png"></a>
		<a href="#"><img id="social_img" src="${pageContext.request.contextPath}/images/youtube.png"></a>
		<a href="#"><img id="social_img" src="${pageContext.request.contextPath}/images/twitter.png"></a>
		<a href="#"><img id="social_img" src="${pageContext.request.contextPath}/images/linkedin.png"></a>
		<a href="https://github.com/reumeum/CodeMate"><img id="social_img" src="${pageContext.request.contextPath}/images/github.png"></a>
		</div>
	</div>


<div class="flex-container">
<div class="main_page">
 <a href="${pageContext.request.contextPath}/main/main.do"><b>메인</b></a>
</div>

<div class="my_page">
<div class="my-page"><b>마이 페이지</b></div>
<ul>
	<li><a href="${pageContext.request.contextPath}/member/modifyUserForm.do">나의 정보</a></li>
	<li><a href="${pageContext.request.contextPath}/member/myTeam.do">참여중인 팀</a></li>
	<li><a href="${pageContext.request.contextPath}/member/myPageShin.do">나의 코메 신청</a></li>
	<li><a href="${pageContext.request.contextPath}/member/myPageMo.do">나의 코메 모집</a></li>
	<li><a href="${pageContext.request.contextPath}/member/myPageBookMark.do">북마크</a></li>
	<li><a href="${pageContext.request.contextPath}/member/myWrite.do">내가 쓴 글</a></li>
	<li><a href="${pageContext.request.contextPath}/member/myReply.do">내가 쓴 댓글</a></li>
	<li><a href="${pageContext.request.contextPath}/member/myPageRboardApply.do">팀원 구하기 댓글</a></li>
</ul>
</div>

<div class="find_team">
<div class="find-team"><b>팀원 구하기</b></div>
	<ul>
		<li><a href="${pageContext.request.contextPath}/rboard/list.do">모집글 목록</a></li>
		<li><a href="${pageContext.request.contextPath}/rboard/teammateRecruitForm.do">모집글 작성</a></li>
	</ul>
</div>

<div class="community">
<div class="community-name"><b>커뮤니티</b></div>
	<ul>
		<li><a href="${pageContext.request.contextPath}/cboard/community.do?cb_type=0">자유 게시판</a></li>
		<li><a href="${pageContext.request.contextPath}/cboard/community.do?cb_type=1">개발 게시판</a></li>
	</ul>
</div>
</div>
</div>

</footer>