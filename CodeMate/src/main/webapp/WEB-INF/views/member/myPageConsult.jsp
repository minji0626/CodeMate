<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 1:1 문의</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myTeam.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myPageConsultDelete.js"></script>
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
    <h3 class="mYPage-TitleText">나의 1:1 문의</h3>
</div>

<select id="filter" class="styled-select" onchange="filterList()">
    <option value="all">전체</option>
    <option value="dev">문의</option>
    <option value="free">신고</option>
</select>


<c:if test="${empty consultList}">
<div class="none_message">나의 문의신고 이력이 없습니다</div>
</c:if>
<c:if test="${!empty consultList}">
<c:forEach var="consult" items="${consultList}">
    <div class="btn_open myPage-line-box" data-type="<c:out value="${consult.cs_category}"/>" 
    data-cs-title="${consult.cs_title}" data-cs-reg-date="${consult.cs_reg_date}" data-cs-email="${consult.cs_reply_email}" data-cs-content="${consult.cs_content}" data-cs-confirmed="${consult.cs_confirmed}"
    style="cursor: pointer;">
        <div class="team-left-myWrite">
            <div class="cboard_name">
                <c:if test="${consult.cs_category == 0}">
                    문의
                </c:if>
                <c:if test="${consult.cs_category == 1}">
                    신고
                </c:if>
            </div>
            <div class="projectName_font">[제목]${consult.cs_title}</div>
        </div>
        <div class="btn_box_write">
            
            <input type="submit" value="삭제" class="myConsultDelete_btn" data-csnum="${consult.cs_num}" onclick="event.stopPropagation();">
        </div>
    </div>
</c:forEach>
</c:if>

</div>
<!-- 메인 정보 수정 끝 -->
</div><!-- flex_container끝 -->
</div><!-- page-container끝 -->
<jsp:include page="myPageConsultDetail.jsp"/>
<script>
function filterList() {
    var selectedOption = document.getElementById("filter").value;
    var items = document.getElementsByClassName("myPage-line-box");

    for (var i = 0; i < items.length; i++) {
        var itemType = items[i].getAttribute("data-type");
        if (selectedOption === "all" || (selectedOption === "free" && itemType === "1") || (selectedOption === "dev" && itemType === "0")) {
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
