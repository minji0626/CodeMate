<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 정보 수정</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modifyUser.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/modifyUserForm.js"></script>

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
    <form id="modify_form" name="modify_form" action="modifyUser.do" method="post">
    <h3 class="mYPage-TitleText">나의 정보 수정</h3>
    
    <!-- 프로필 사진 추가 -->
    <ul>
        <li>
            <c:if test="${empty member.mem_photo}">
                <img src="${pageContext.request.contextPath}/images/face.png" width="150" height="150" class="my-photo">
            </c:if>
            <c:if test="${!empty member.mem_photo}">
                <img src="${pageContext.request.contextPath}/upload/${member.mem_photo}" width="150" height="150" class="my-photo">
            </c:if>
        </li>
        <li>
            <div class="align-center">
                <input type="button" value="수정" id="mem_photo_btn">
            </div>
            <div id="mem_photo_choice" style="display: none;">
                <input type="file" id="mem_photo" accept="image/gif,image/png,image/jpeg">
                <input type="button" value="기본" id="photo_base">
                <input type="button" value="전송" id="mem_photo_submit">
                <input type="button" value="취소" id="mem_photo_reset">
            </div>
        </li>
    </ul>
    <!-- 프로필 사진 추가 끝 -->
    
    <div class="formBox">
        <div class="text-align-left">
            <ul class="item-align-center">
                <li>
                    <label for="mem_name" class="form_label">이름</label>
                    <input type="text" id="mem_name" name="mem_name" maxlength="10" class="input-check" value="${member.mem_name}">
                </li>
                <li>
                    <label for="mem_id" class="form_label">아이디</label>
                    <input type="text" id="mem_id" name="mem_id" maxlength="20" class="input-check" value="${member.mem_id}">
               		<input type="hidden" id="original_mem_id" value="${member.mem_id}">
                </li>
                <li>
                    <label for="mem_email" class="form_label">이메일</label>
                    <input type="email" id="mem_email" name="mem_email" maxlength="50" class="input-check" value="${member.mem_email}">
                    
                </li>
                
                
                <li>
                    <label for="mem_nickname" class="form_label">닉네임</label>
                    <input type="text" id="mem_nickname" name="mem_nickname" maxlength="20" class="input-check" value="${member.mem_nickname}">
               		<!-- 닉네임 중복체크 -->
                    <div id="message_nickname" class="error-message"></div>
                </li>
                
                <li>
                    <label for="mem_phone" class="form_label">전화번호</label>
                    <input type="text" id="mem_phone" name="mem_phone" maxlength="20" class="input-check" value="${member.mem_phone}">
                </li>
            </ul>
        </div>
        </div>
        <div class="align-center margin-bottom-large">
            <input type="submit" value="저장하기" class="save_btn" >
        </div>
        <div class="small-font">
            <br><br>
            <span><a href="${pageContext.request.contextPath}/member/deleteUserForm.do" id="header_logout">회원 탈퇴하기</a></span>
            <span>/</span>
            <span><a href="${pageContext.request.contextPath}/member/logout.do" id="header_logout">로그아웃</a></span>
        </div>
    </form>
</div>
</div>
<!-- 메인 정보 수정 끝 -->
</div>
</div>
</body>
</html>
