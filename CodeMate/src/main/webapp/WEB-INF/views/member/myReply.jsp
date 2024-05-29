<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 댓글</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    // '삭제' 버튼 클릭 이벤트
    $('#myDelete_btn').click(function(event) {
        event.preventDefault(); // 기본 동작 방지 (예: 폼 제출)
        $(this).closest('.box_reply').remove(); // 부모 요소인 .box_reply 삭제
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
    <h3 class="mYPage-TitleText">내가 쓴 댓글</h3>
</div>
<div class="box_reply">
<div class="write_reply">
내가 쓴 글 내가 쓴 글 내가 쓴 내가 쓴 글 내가 쓴 내가 쓴 글 내가 쓴
</div>
<div class="btn_box">
    <input type="submit" value="수정" class="myUpdate_btn" onclick="location.href=''">
    <input type="submit" value="취소" id="myDelete_btn" name="myDelete_btn" class="myDelete_btn">
</div>
</div>
</div>
<!-- 메인 정보 수정 끝 -->
</div><!-- flex_container끝 -->
</div><!-- page-container끝 -->
</body>
</html>
