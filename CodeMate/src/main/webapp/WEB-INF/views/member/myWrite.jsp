<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 글</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
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
	<h3 class="mYPage-TitleText">내가 쓴 글</h3>
</div>

    <select class="styled-select">
        <option value="option1">전체</option>
        <option value="option2">모집</option>
        <option value="option3">자유</option>
    </select>

<div class="box">
<div class="box-wirte">
<div class="myWrite-board-name">게시판 이름</div>
<div class="myWrite-Title">내가 쓴 글 제목</div>
<div class="fav-reply">
<div class="myWrite-fav">좋아요</div>
<div class="myWrite-reply">댓글</div>
</div>
</div>
<div class="btn_box_write">
	<input type="button" value="수정" class="myUpdate_btn" onclick="">
	<input type="submit" value="취소" id="myDelete_btn" name="myDelete_btn" class="myDelete_btn">
</div>

</div>
</div>
<!-- 메인 정보 수정 끝 -->
</div><!-- flex_container끝 -->
</div><!-- page-container끝 -->
</body>
</html>
