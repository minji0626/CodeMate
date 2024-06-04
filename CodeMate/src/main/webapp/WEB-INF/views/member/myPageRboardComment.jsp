<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀원구하기 댓글글</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/team.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/rcommentDelete.js"></script>

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
<div class="float-B">
<div class="align-center">
    <h3 class="mYPage-TitleText">팀원구하기 댓글</h3>
</div>
<c:if test="${empty rcommentList}">
<div class="MRC">팀원구하기의 댓글이 없습니다</div>
</c:if>
<c:if test="${!empty rcommentList}">

<c:forEach var="rcomment" items="${rcommentList}">
	<!-- 팀원구하기 모집글 링크 달기 -->
	<div class="myPage-line-box" onclick="window.location.href='${pageContext.request.contextPath}/rboard/detail.do?rb_num=${rcomment.rb_num}'" style="cursor: pointer;">
       
        <div class="team-left-myRcomment">
            <!-- <div class="cboard_name">팀원구하기 댓글</div> -->
                <div class="rcomment">게시글:${rcomment.rb_title}</div>
            <div class="projectName_font">${rcomment.rc_content}</div>
        </div>
        <div class="btn_box_write">
            <input type="button" value="수정" class="myUpdate_btn" onclick="">
            <input type="submit" value="삭제" class="myDelete_btn" data-rcnum="${rcomment.rc_num}">
        </div>
    </div>
</c:forEach>
</c:if>


</div>
<!-- 메인 정보 수정 끝 -->
</div>
</div>
</body>
</html>
