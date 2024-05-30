<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 코메 신청</title>
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
	<h3 class="mYPage-TitleText">나의 코메 신청</h3>
</div>

<c:forEach var="aprboard" items="${aprboardList}">
<div class="myPage-line-box">
<div class="team-left">
<c:if test="${aprboard.rb_category == '0'}">
	스터디
</c:if>
<c:if test="${aprboard.rb_category == '1'}">
	프로젝트
</c:if>
<div class="projectName_font">${aprboard.rb_pj_title}</div>
</div>

<div class="team-right">
<div class="delete-green">신청 취소</div>
<div class="team-count">모집인원:${aprboard.rb_teamsize}명</div>
<!-- 시작일인데 진행기간 넣어봄 -->
<div class="team-count">프로젝트 진행기간 ${aprboard.rb_period}</div><!-- 글자 간격 마진 주려고 클래스 복붙 -->
<div >모집 마감일 ${aprboard.rb_endRecruit}</div>
</div>
</div>
</c:forEach>

</div>
<!-- 메인 정보 수정 끝 -->
</div><!-- flex_container끝 -->
</div><!-- page-container끝 -->
</body>
</html>
