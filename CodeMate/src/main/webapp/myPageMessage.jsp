<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>북마크</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myTeam.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<style type="text/css">

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

.message-table th,
.message-table td {
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
    <div class="align-center">
        <h3 class="mYPage-TitleText">쪽지</h3>
    </div>
    <!-- 메뉴 -->
    <div class="menu">
        <ul>
            <li><a href="#" class="active">받은 쪽지함</a></li>
            <li><a href="myPageMessage2.jsp">보낸 쪽지함</a></li>
        </ul>
    </div>
    
    <!-- 받은 쪽지함 -->
    <div class="message-box">
    <!-- 받은 쪽지함 내용 -->
    <div class="received-messages">
        <table class="message-table">
            <thead>
                <tr>
                    <th><input type="checkbox" id="check-all-received"></th>
                    <th>보낸 사람</th>
                    <th>제목</th>
                    <th>날짜</th>
                    <th>읽음 상태</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><input type="checkbox" class="message-checkbox"></td>
                    <td>유저1</td>
                    <td>안녕하세요!</td>
                    <td>2024년 6월 5일</td>
                    <td>읽음</td>
                </tr>
                <tr>
                    <td><input type="checkbox" class="message-checkbox"></td>
                    <td>유저1</td>
                    <td>안녕하세요!</td>
                    <td>2024년 6월 5일</td>
                    <td>읽음</td>
                </tr>
                <tr>
                    <td><input type="checkbox" class="message-checkbox"></td>
                    <td>유저1</td>
                    <td>안녕하세요!</td>
                    <td>2024년 6월 5일</td>
                    <td>읽음</td>
                </tr>
                <tr>
                    <td><input type="checkbox" class="message-checkbox"></td>
                    <td>유저1</td>
                    <td>안녕하세요!</td>
                    <td>2024년 6월 5일</td>
                    <td>읽음</td>
                </tr>
                <tr>
                    <td><input type="checkbox" class="message-checkbox"></td>
                    <td>유저1</td>
                    <td>안녕하세요!</td>
                    <td>2024년 6월 5일</td>
                    <td>읽음</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="action-buttons">
        <button class="btn delete-selected">선택 삭제</button>
        <button class="btn mark-all-read">전체 읽음</button>
        <button class="btn delete-all">전체 삭제</button>
    </div>
    </div>
</div>

</div><!-- flex_container끝 -->
</div><!-- page-container끝 -->



</body>
</html>
