<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 글</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    // '취소' 버튼 클릭 이벤트
    $('.myDelete_btn').click(function(event) {
        event.preventDefault();
        $(this).closest('.myPage-line-box').remove(); 
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
<div class="align-center"><!-- myPage-TitleText만 가운데 정렬됨 -->
    <h3 class="mYPage-TitleText">내가 쓴 글</h3>
</div>

<select class="styled-select">
    <option value="option1">전체</option>
    <option value="option2">모집</option>
    <option value="option3">자유</option>
</select>

<%-- <c:forEach var="변수" items="${변수}"> --%>
    <div class="myPage-line-box">
    <div class="team-left">
        <div class="board_name">자유게시판</div>
        <c:if test="">
        자유게시판 0
        </c:if>
        <c:if test="">
        개발게시판 1
        </c:if>
        <div class="projectName_font">내가 쓴 글 제목</div>
        <div class="fav-reply">
            <div class="myWrite-fav">좋아요</div>
            <div class="myWrite-reply">댓글</div>
        </div>
    </div>
        <div class="btn_box_write">
        <input type="button" value="수정" class="myUpdate_btn" onclick="">
        <input type="submit" value="삭제" id="myDelete_btn" name="myDelete_btn" class="myDelete_btn">
    </div>
    </div>
<%-- </c:forEach>   --%> 

</div>
<!-- 메인 정보 수정 끝 -->
</div><!-- flex_container끝 -->
</div><!-- page-container끝 -->
</body>
</html>
