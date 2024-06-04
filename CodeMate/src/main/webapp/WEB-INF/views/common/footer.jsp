<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
/* 기본 스타일 */

.footer {
	bottom: 0;
	width: 100%;
	color: #000;
	padding: 20px 0;
	box-shadow: 0 -2px 10px rgba(0, 0, 0, 0.1);
}

.footer a {
	color: #000;
	text-decoration: none;
}

.footer a:hover {
	color: #adb5bd;
}

.flex {
	display: flex;
	justify-content: space-around;
	flex-wrap: wrap;
	padding: 20px;
}

.logo-container {
	display: flex;
	align-items: center;
	margin-bottom: 20px;
}

#logo_pic {
	margin-right: 15px;
}

.social-contact {
	display: flex;
	align-items: center;
	margin-left: auto;
}

#social_img {
	margin-right: 10px;
	height: 30px;
	width: 30px;
}

.main_page, .my_page, .find_team, .community, .copyright {
	max-width: 200px;
	margin: 10px 0;
}

.main_page b, .my_page b, .find_team b, .community b {
	font-size: 16px;
}

.my_page ul, .find_team ul, .community ul {
	list-style: none;
	padding: 0;
}

.my_page ul li, .find_team ul li, .community ul li {
	margin-bottom: 10px;
}

.my_page ul li a, .find_team ul li a, .community ul li a {
	transition: transform 0.3s, color 0.3s;
}

.my_page ul li a:hover, .find_team ul li a:hover, .community ul li a:hover {
	transform: scale(1.05);
}

.copyright {
	text-align: center;
	font-size: 14px;
	margin-top: 20px;
}
</style>

<footer class="footer">
	<div class="page-container">
		<div class="logo-container">
			<img id="logo_pic" src="${pageContext.request.contextPath}/images/로고1.png" height="80" width="80"> CODEMATE
			<div class="social-contact">
				<a href="#"><img id="social_img" src="${pageContext.request.contextPath}/images/facebook.png"></a>
				<a href="https://www.instagram.com/sist3482/"><img id="social_img" src="${pageContext.request.contextPath}/images/instagram.png"></a>
				<a href="#"><img id="social_img" src="${pageContext.request.contextPath}/images/youtube.png"></a>
				<a href="#"><img id="social_img" src="${pageContext.request.contextPath}/images/twitter.png"></a>
				<a href="#"><img id="social_img" src="${pageContext.request.contextPath}/images/linkedin.png"></a>
				<a href="https://github.com/reumeum/CodeMate"><img id="social_img" src="${pageContext.request.contextPath}/images/github.png"></a>
			</div>
		</div>

		<div class="flex">
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

		<div class="copyright">
			<p>Copyright &copy; Rights Reserved By CodeMate</p>
		</div>
	</div>
</footer>
