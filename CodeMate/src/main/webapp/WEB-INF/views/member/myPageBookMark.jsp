<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myTeam.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".delete-green").click(function() {
            $(this).closest(".myPage-line-box").remove();
        });
    });
</script>
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
	<h3 class="mYPage-TitleText">북마크</h3>
</div>
<%-- <c:forEach var="변수명" items="${액션에서 반환한 리스트 변수}"> --%>
<div class="myPage-line-box">
<div class="team-left">
<div class="skill_font_T">백엔드</div>
<div class="projectName_font">쇼핑몰 웹페이지 프로젝트 같이 하실분 구해요</div>
</div>

<div class="team-right">
<div class="delete-green">북마크 취소</div>
<div class="team-count">신청인원:3명</div>
<div>모집마감일 2024.05.23~2024.06.10</div>
</div>
</div>
<%-- </c:forEach> --%>
</div>
<!-- 메인 정보 수정 끝 -->
</div><!-- flex_container끝 -->
</div><!-- page-container끝 -->
</body>
</html>
