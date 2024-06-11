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
<style>

/* 메뉴 스타일 */
.menu {
	margin-bottom: 20px;
}

.menu ul {
	list-style-type: none;
	padding: 0;
	margin: 0;
}

.menu ul li {
	display: inline;
	margin-right: 10px;
}

.menu ul li a {
	text-decoration: none;
	color: #333;
	padding: 5px 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.menu ul li a.active {
	background-color: #ccc;
}

/* 테이블 스타일 */
.message-table {
	width: 100%;
	border-collapse: collapse;
	text-align: center;
}

.message-table th, .message-table td {
	border: 1px solid #ccc;
	padding: 8px;
}

.message-table th {
	background-color: #f2f2f2;
}

/* 버튼 스타일 */
.message-buttons {
	margin-top: 20px;
}

.message-buttons button {
	margin-right: 10px;
	padding: 8px 16px;
	font-size: 14px;
	cursor: pointer;
}

/* 버튼 스타일 수정 */
.action-buttons {
	position: relative;
	margin-top: 10px;
	text-align: right;
}

.btn {
	background-color: #78AFE2;
	border: none;
	color: white;
	padding: 8px 15px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	margin-left: 10px;
	cursor: pointer;
	border-radius: 5px;
	transition: background-color 0.3s;
}

* {
	margin: 0;
	padding: 0;
}

a {
	text-decoration: none;
}

.wrap {
	padding: 10px;
}

.pop_wrap {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background: rgba(0, 0, 0, .5);
	font-size: 0;
	text-align: center;
}

.pop_wrap:after {
	display: inline-block;
	height: 100%;
	vertical-align: middle;
	content: '';
}

.pop_wrap .pop_inner {
	display: inline-block;
	padding: 20px 30px;
	background: #fff;
	width: 400px;
	vertical-align: middle;
	font-size: 15px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	text-align: left;
	font-family: 'Noto Sans KR', sans-serif;
}

.pop_inner .header {
	font-size: 18px;
	font-weight: bold;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
	padding-bottom: 10px;
}

.pop_inner .content {
	margin-bottom: 20px;
}

.pop_inner .footer {
	text-align: right;
	border-top: 1px solid #ccc;
	padding-top: 10px;
}

.pop_inner .footer button {
	margin-left: 10px;
}

.pop_inner .message-details {
	margin-bottom: 20px;
}

.pop_inner .message-details p {
	margin: 5px 0;
}
</style>
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
            <div class="projectName_font">${consult.cs_content}</div>
            <div class="fav-reply">
                <div class="myApply-write">제목:${consult.cs_title}</div>
            </div>
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
