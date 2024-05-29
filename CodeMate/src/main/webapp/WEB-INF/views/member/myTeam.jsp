<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>참여중인 팀</title>
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

<!-- <div class="myPage-line-box" onclick="location.href='${pageContext.request.contextPath}/team/teamTo_Do.do'" style="cursor: pointer;"> -->

<%-- <c:forEach var="rboard" items="${rboardList}"> --%>
<div class="myPage-line-box" onclick="location.href='${pageContext.request.contextPath}/team/teamTo_Do.do'" style="cursor: pointer;">
<div class="team-left">
<div class="skill_font_T">${member.mem_nickname}</div>
<div class="projectName_font">${member.mem_nickname}</div>
</div>
<div class="team-right">
<div class="delete-green-box">
</div>
<div>모집마감:${member.mem_nickname}</div>
</div>
</div>
<%-- </c:forEach> --%>


</div>
<!-- 메인 정보 수정 끝 -->
</div><!-- flex_container끝 -->
</div><!-- page-container끝 -->
</body>
</html>
