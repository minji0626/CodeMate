<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cyy.css" type="text/css">
</head>
<body>
<div id="main_logo">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/main/main.do">CODEMATE</a>
        </li>
    </ul>    
</div>
<div id="main_nav">
        <!-- 로그인 여부무관 -->
   <c:if test="${empty user_num}">
      	
         <ul id="option">
            <li>
                <a href="${pageContext.request.contextPath}/board/community.do">커뮤니티</a>
                /
                <a href="${pageContext.request.contextPath}/board/teammate.do">팀원 구하기</a>
            </li>
        </ul>
   </c:if>
      
     <!-- 로그인 X -->	 
        <c:if test="${empty user_num}">
        	<%-- <ul id="login">
            	<li>
                	<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>
            		/
                	<a href="${pageContext.request.contextPath}/member/signUp.do">회원가입</a>
            	</li>
            </ul> --%>
            
            
            
            <li id="login">
                    <div class="menu-header" id="menuOne">
                        <a class="menu-button" data-bs-toggle="collapse" data-bs-target="#menu1"
                        	href="${pageContext.request.contextPath}/member/mypage.do">
                            닉네임
                            <%-- [<span>${user_nickname}</span>] --%>
                        </a>
                    </div>
                    <div id="menu1" class="menu1" data-bs-parent="#Menu-mypage">
                      <div class="menu-body1">
                         <ul>   
                            <li>
                                <a href="${pageContext.request.contextPath}/member/mateProfile.do">메이트프로필</a>
                            </li>
                         </ul>   
                      </div>
                        <div class="menu-body2">
                         <ul>   
                            <li>
                                <a href="${pageContext.request.contextPath}/member/MyPage.do">마이페이지</a>
                            </li>
                         </ul>
                        </div>
                      	  <div class="menu-body3">
                        	<ul>
                            	<li>
                                <a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
                            	</li>
                        	</ul>
                     	  </div>
                  </div>
                </a>
            </li>
            
        </c:if>
	<!-- 
	5/22일 기준 css여기까지 완성 
	닉네임 앞에 이미지 넣기
	아코디언 닫힌채로 나오도록 바꾸기
	-->





        <!-- 로그인 O -->
        <c:if test="${!empty user_num}">
            
            <!-- css 확인을 위해 잠시 로그인x 로 옮김 -->
            
            
            
        </c:if>
</div>

   
       
    


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
