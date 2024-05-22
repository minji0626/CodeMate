<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" type="text/css">
</head>
<body>


<div id="main_logo">
	
	<li class="MainName">
		<a href="${pageContext.request.contextPath}/main/main.do">CODEMATE</a>
	</li>
	
		<!-- 로그인 여부무관 -->
		<li class="option"><a
			href="${pageContext.request.contextPath}/board/community.do">커뮤니티</a>
		</li>
		<li class="option"><a
			href="${pageContext.request.contextPath}/board/teammate.do">팀원
				구하기</a></li>
	</ul>
</div>
<div id="main_nav">
	


		<!-- 로그인 X -->
		<c:if test="${empty user_num}">
			<li><a href="${pageContext.request.contextPath}/member/login.do">로그인</a>
			</li>
			<li><a
				href="${pageContext.request.contextPath}/member/signUp.do">회원가입</a>
			</li>
		</c:if>

		<!-- 로그인 O -->
		<c:if test="${!empty user_num}">
			<li class="Menu-myPage">[<span>${user_nickname}</span>] <a
				href="${pageContext.request.contextPath}/member/mypage.do">
				
				<div class="menu-header" id="menuOne">
					<button class="menu-button" type="button"
						data-bs-toggle="collapse" data-bs-target="#menu1">
						<!-- data-bs-target="#collapseOne" => 버튼을 누르는 순간 보여지는 요소명시 -->
						닉네임
					</button>
				</div>
				
				<div id="menu1" class="menu1" data-bs-parent="#Menu-mypage">
					<div class="menu-body1">
					<li>
						<a href="${pageContext.request.contextPath}/member/mateProfile.do">메이트프로필</a>
					</li>
					</div>
					<div class="menu-body2">
					<li>
						<a href="${pageContext.request.contextPath}/member/MyPage.do">마이페이지</a>
					</li>
					</div>
					<div class="menu-body3">
					<li>
						<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
					</li>
					</div>
				</div>
				</a>
			</li>
		</c:if>
		
	</ul>
</div>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</html>






