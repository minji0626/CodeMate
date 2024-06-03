<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 글</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myTeam.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myPageWriteCboardDelete.js"></script>
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

<select id="filter" class="styled-select" onchange="filterList()">
    <option value="all">전체</option>
    <option value="dev">개발</option>
    <option value="dev">자유</option>
    <option value="team">팀게시판</option>
    
</select>

<c:if test="${empty cboardList}">
<div class="MW">나의 작성 글이 없습니다</div>
</c:if>
<c:if test="${!empty cboardList}">
<c:forEach var="cboardList" items="${cboardList}">
    <div class="myPage-line-box" onclick="window.location.href='${pageContext.request.contextPath}/cboard/communityDetail.do?cb_num=${cboardList.cb_num}'" data-type="<c:out value="${cboardList.cb_type}"/>">
        <div class="team-left-myWrite">
            <div class="cboard_name">
                <c:if test="${cboardList.cb_type == '0'}">
                    자유게시판
                </c:if>
                <c:if test="${cboardList.cb_type == '1'}">
                    개발게시판
                </c:if>
            </div>
            <div class="projectName_font">${cboardList.cb_title}</div>
            <div class="fav-reply">
                <div class="myWrite-fav">조회수:${cboardList.cb_hit}</div>
            </div>
        </div>
        <div class="btn_box_write">
            <input type="button" value="수정" class="myUpdate_btn" onclick="">
            <input type="submit" value="삭제" class="myDelete_btn" data-cbnum="${cboardList.cb_num}">
        </div>
    </div>
</c:forEach>
</c:if>

</div>
<!-- 메인 정보 수정 끝 -->
</div><!-- flex_container끝 -->
</div><!-- page-container끝 -->

<script>
function filterList() {
    var selectedOption = document.getElementById("filter").value;
    var items = document.getElementsByClassName("myPage-line-box");

    for (var i = 0; i < items.length; i++) {
        var itemType = items[i].getAttribute("data-type");
        if (selectedOption === "all" || selectedOption === "dev" && itemType === "1" || selectedOption === "free" && itemType === "0") {
            items[i].classList.remove("hidden");
        } else {
            items[i].classList.add("hidden");
        }
    }
}
</script>

<style>
.hidden {
    display: none !important;
}
</style>

</body>
</html>
