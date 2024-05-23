<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헤더</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cyy.css" type="text/css">
</head>
<body>


<div id="main_logo">
<ul>
	<li class="MainName" style="list-style: none;">
		<a  href="${pageContext.request.contextPath}/main/main.do">
		<img id="logo_pic" src="${pageContext.request.contextPath}/images/로고1.png" height="80" width="80">
		CODEMATE</a>
	</li>
</ul>
</div><!-- end of div#main_logo -->

<div id="main_nav">
	<ul style="list-style: none;">
		<!-- 로그인 여부무관 -->
		<li class="option">
			<a href="${pageContext.request.contextPath}/board/community.do">커뮤니티</a>
				/
			<a href="${pageContext.request.contextPath}/board/teammate.do">팀원 구하기</a>
		</li>
	</ul>


   		<!-- 로그인 X -->	
        <c:if test="${empty mem_num}">
        
        	  <ul class="menu-header" style="list-style: none;">
            	<li>
                	<a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a>
            		/
                	<a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a>
            	</li>
            </ul> 
             
             </c:if>
             
             <!-- 로그인 O -->
      <c:if test="${!empty mem_num}">
		
		<div class="menu-header" id="menuOne">
                    	
                        <a class="menu-button" data-bs-toggle="collapse" data-bs-target="#menu1"
                        	href="${pageContext.request.contextPath}/member/mypage.do">
                        	<img id="profile_pic" src="../images/face.png" height="40" width="40">
                            닉네임
                            <%-- [<span>${user_nickname}</span>] --%>
                        </a>
                    
                    <div id="menu1" class="menu1" data-bs-parent="#Menu-mypage">
                      <div class="menu-body1">
                         <ul style="list-style: none;">  
                            <li>
                                <a href="${pageContext.request.contextPath}/member/mateProfile.do">메이트프로필</a>
                            </li>
                         </ul>  
                      </div>
                        <div class="menu-body2">
                         <ul style="list-style: none;">  
                            <li>
                                <a href="${pageContext.request.contextPath}/member/MyPage.do">마이페이지</a>
                            </li>
                         </ul>
                        </div>
                      	  <div class="menu-body3">
                        	<ul style="list-style: none;">
                            	<li>
                                <a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
                            	</li>
                        	</ul>
                     	  </div>
                  </div><!-- end of div menu1 -->
                  </div><!-- end of div menu-header -->
                   
        </c:if>

</div><!-- end of div#main_nav -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

