<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 댓글</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myTeam.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myPageApplyCboardDelete.js"></script>
<style>
.hidden {
    display: none !important;
}
</style>
</head>
<body>
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

<select id="filter" class="styled-select" onchange="filterList()">
    <option value="all">전체</option>
    <option value="dev">개발</option>
    <option value="free">자유</option>
</select>

<div id="no-comments-message" class="none_message hidden">나의 작성 댓글이 없습니다</div>

<c:if test="${!empty commentList}">
<c:forEach var="comment" items="${commentList}">
    <div class="myPage-line-box" onclick="window.location.href='${pageContext.request.contextPath}/cboard/communityDetail.do?cb_num=${comment.cb_num}'" data-type="${comment.cb_type}">
        <div class="team-left-myWrite">
            <div class="cboard_name">
                <c:if test="${comment.cb_type == 0}">
                    자유게시판
                </c:if>
                <c:if test="${comment.cb_type == 1}">
                    개발게시판
                </c:if>
            </div>
            <div class="projectName_font">[게시글]${comment.cb_title}</div>
            <div class="fav-reply">
                <div class="myApply-write">${comment.cc_content}⏎</div>
            </div>
        </div>
        <div class="btn_box_write">
            <input type="button" value="수정" class="myUpdate_btn" onclick="">
            <input type="submit" value="삭제" class="myDelete_btn" data-ccnum="${comment.cc_num}">
        </div>
    </div>
</c:forEach>
</c:if>
</div>
<!-- 메인 정보 수정 끝 -->
</div><!-- flex_container끝 -->
</div><!-- page-container끝 -->


<script>
//페이지 로딩 후 실행되는 함수
window.onload = function() {
 // 페이지 로딩 후에도 댓글이 없는지 확인
 checkCommentsExistence();
};

function checkCommentsExistence() {
 var items = document.getElementsByClassName("myPage-line-box");
 var noCommentsMessage = document.getElementById("no-comments-message");

 var commentsExist = false;

 for (var i = 0; i < items.length; i++) {
     var itemType = items[i].getAttribute("data-type");
     if (itemType === "1" || itemType === "0") {
         commentsExist = true;
         break;
     }
 }


 if (!commentsExist) {
     noCommentsMessage.classList.remove("hidden");
 }
}

function filterList() {
 var selectedOption = document.getElementById("filter").value;
 var items = document.getElementsByClassName("myPage-line-box");
 var noCommentsMessage = document.getElementById("no-comments-message");

 var visibleItems = 0;
 var commentsExist = false;

 for (var i = 0; i < items.length; i++) {
     var itemType = items[i].getAttribute("data-type");
     if (selectedOption === "all" || (selectedOption === "dev" && itemType === "1") || (selectedOption === "free" && itemType === "0")) {
         items[i].classList.remove("hidden");
         visibleItems++;

         commentsExist = true;
     } else {
         items[i].classList.add("hidden");
     }
 }

 if (visibleItems === 0 && !commentsExist) {
     noCommentsMessage.classList.remove("hidden");
 } else {
     noCommentsMessage.classList.add("hidden");
 }
}
</script>
</body>
</html>