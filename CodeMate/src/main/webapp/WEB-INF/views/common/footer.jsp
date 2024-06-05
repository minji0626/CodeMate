<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
/* 기본 스타일 */

.footer {
	margin-top: 10%;
	bottom: 0;
	width: 100%;
	color: #000;
	padding: 10px 0; /* 높이를 줄이기 위해 패딩을 줄임 */
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
	align-items: flex-start;
	flex-wrap: wrap;
	padding: 10px; 
}

.logo-container {
	display: flex;
	align-items: center;
	font-weight: bold;
}

#logo_pic {
	margin-right: 15px;
	height: 60px; /* 높이를 줄임 */
	width: 60px; /* 높이를 줄임 */
}

.social-contact {
	display: flex;
	align-items: center;
	margin-left: auto;
}

#social_img {
	margin-right: 10px;
	height: 20px; /* 높이를 줄임 */
	width: 20px; /* 높이를 줄임 */
}

.main_page, .my_page, .find_team, .community{
	max-width: 200px;
	margin: 10px 0;
}

.main_page b, .my_page b, .find_team b, .community b {
	font-size: 15px;
}

.my_page ul, .find_team ul, .community ul {
	list-style: none;
	padding: 0;
}

.my_page ul li, .find_team ul li, .community ul li {
	margin-bottom: 5px; /* 높이를 줄이기 위해 마진을 줄임 */
	font-size: 13px;
}

.my_page ul li a, .find_team ul li a, .community ul li a {
	transition: transform 0.3s, color 0.3s;
}

.my_page ul li a:hover, .find_team ul li a:hover, .community ul li a:hover {
	transform: scale(1.05);
}

.copyright-container {
	display: flex; /* 추가 */
	justify-content: flex-end; /* 추가 */
	align-items: center; /* 추가 */
	width: 100%; /* 추가 */
}

.copyright {
	text-align: right; /* 변경 */
	font-size: 14px;
	padding-left: 20px; /* 변경 */
}
</style>

<footer class="footer">
	<div class="page-container">
		<div class="logo-container">
			<img id="logo_pic" src="${pageContext.request.contextPath}/images/로고1.png" height="60" width="60"> CODEMATE <!-- 이미지 크기를 줄임 -->
			<div class="social-contact">
				<a href="#"><img id="social_img" src="${pageContext.request.contextPath}/images/facebook.png" height="20" width="20"></a> <!-- 이미지 크기를 줄임 -->
				<a href="https://www.instagram.com/sist3482/"><img id="social_img" src="${pageContext.request.contextPath}/images/instagram.png" height="20" width="20"></a> <!-- 이미지 크기를 줄임 -->
				<a href="#"><img id="social_img" src="${pageContext.request.contextPath}/images/youtube.png" height="20" width="20"></a> <!-- 이미지 크기를 줄임 -->
				<a href="#"><img id="social_img" src="${pageContext.request.contextPath}/images/twitter.png" height="20" width="20"></a> <!-- 이미지 크기를 줄임 -->
				<a href="#"><img id="social_img" src="${pageContext.request.contextPath}/images/linkedin.png" height="20" width="20"></a> <!-- 이미지 크기를 줄임 -->
				<a href="https://github.com/reumeum/CodeMate"><img id="social_img" src="${pageContext.request.contextPath}/images/github.png" height="20" width="20"></a> <!-- 이미지 크기를 줄임 -->
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

			<div class="copyright-container">
				<div class="copyright">
					<p>Copyright &copy; Rights Reserved By CodeMate</p>
				</div>
			</div>
		</div>
	</div>
</footer>
