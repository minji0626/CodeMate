<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>참여중인 팀</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myTeam.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>

</head>
<body>
<!-- 헤더 링크-->
<div class="page-container">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div id="flex_container">
<!-- 사이드바 -->
<jsp:include page="/WEB-INF/views/member/myPage_nav.jsp"/>
<!-- 사이드바 끝 -->
<!-- 메인 정보 수정 -->
<div class="float-C">
<div class="align-center"><!-- myPage-TItleText만 가운데 정렬됨 -->
	<h3 class="mYPage-TitleText">참여중인 팀</h3>
</div>

<c:if test="${empty team}">
<div class="none_message">참가 중인 팀이 없습니다</div>
</c:if>
<c:if test="${!empty team}">
    <c:forEach var="myteam" items="${team}">
    	<c:if test="${myteam.team_status==1}">
	        <div class="myPage-line-box" onclick="location.href='${pageContext.request.contextPath}/team/teamMain.do?team_num=${myteam.team_num}'" style="cursor: pointer;">
	        <div class="team-left">
	        	<div class="skill_font_T"></div>
	        	<div class="projectName_font">${myteam.rb_pj_title}</div>
		        <c:if test="${myteam.tm_auth == '4'}">
		            팀장
		        </c:if>
		        <c:if test="${myteam.tm_auth == '3'}">
		            팀원
		        </c:if>
	       		 </div>
	        <div class="team-right">
	        <div class="delete-green-box">
	        </div>
	        <div class="team-count">프로젝트 시작일:${myteam.rb_start}</div>
	        <div>프로젝트 진행기간:${myteam.rb_period}개월</div>
	        </div>
	        </div>
        </c:if>
    </c:forEach>
 
    <c:forEach var="myteam" items="${team}">
    	<c:if test="${myteam.team_status==3}">
	        <div class="myPage-line-box" onclick="location.href='${pageContext.request.contextPath}/team/teamMain.do?team_num=${myteam.team_num}'" style="cursor: pointer; background-color: #e6e6e6;">
	        <div class="team-left">
	        	<div class="skill_font_T"></div>
	        	<div class="projectName_font">[프로젝트 종료]${myteam.rb_pj_title}</div>
		        <c:if test="${myteam.tm_auth == '4'}">
		            팀장
		        </c:if>
		        <c:if test="${myteam.tm_auth == '3'}">
		            팀원
		        </c:if>
	       		 </div>
	        <div class="team-right">
	        <div class="delete-green-box">
	        </div>
	        <div class="team-count">프로젝트 시작일:${myteam.rb_start}</div>
	        <div>프로젝트 진행기간:${myteam.rb_period}개월</div>
	        </div>
	        </div>
        </c:if>
    </c:forEach>
</c:if>


</div>
<!-- 메인 정보 수정 끝 -->
</div><!-- flex_container끝 -->
</div><!-- page-container끝 -->
</body>
</html>
