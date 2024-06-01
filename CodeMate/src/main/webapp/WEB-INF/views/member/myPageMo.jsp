<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 코메 모집</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myTeam.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/rboardWriteDelete.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    

	//'활성화' 버튼 클릭 이벤트
    $('.close-green').click(function(event) {
        event.stopPropagation();
        if ($(this).hasClass('active')) {
            $(this).removeClass('active').addClass('inactive').css('background-color', 'red').text('비활성화');
        } else {
            $(this).removeClass('inactive').addClass('active').css('background-color', '').text('활성화');
        }
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
	<h3 class="mYPage-TitleText">나의 코메 모집</h3>
</div>


<c:forEach var="rboard" items="${rboardList}">
<div class="myPage-line-box" onclick="window.location.href='${pageContext.request.contextPath}/member/myPageMoShin.do'">
<div class="team-left">
<div class="skill_font_T">${rboard.rb_pj_title}</div>
<div class="projectName_font">${rboard.rb_title}</div>
</div>
<div class="team-right">

<div class="delete-green" data-rbnum="${rboard.rb_num}">모집글 삭제</div>
<div>
<div class="team-count">모집인원:${rboard.rb_teamsize}명</div>
<div>모집마감:${rboard.rb_endRecruit}</div>
</div>
<div class="close-green">활성화</div>
</div>
</div>
</c:forEach>


</div>
<!-- 메인 정보 수정 끝 -->
</div><!-- flex_container끝 -->
</div><!-- page-container끝 -->
</body>
</html>
