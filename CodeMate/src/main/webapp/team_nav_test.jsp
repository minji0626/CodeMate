<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="left-outer">
    <div class="profile">
        <img src="${pageContext.request.contextPath}/images/cmj/account_circle.png" id="profile-img">
        <span class="team_status">팀원</span>
        <span class="team_nickname">분모재</span>
        <div class="written_post">
            <img src="${pageContext.request.contextPath}/images/cmj/written_post.png" alt="내가 쓴 글" id="post_icon">
            <span class="my_post">내가 쓴 글</span>
            <span class="my_post_cnt">20개</span>
        </div>
    </div>
    <!-- 외부 왼쪽 컨테이너 시작 -->
    <div class="menu-bar">
        <a href="${pageContext.request.contextPath}/team_main_test.jsp" class="menu-item">캘린더 & To-Do</a>
        <a href="${pageContext.request.contextPath}/team_board_test.jsp" class="menu-item">팀 게시판</a>
        <a href="${pageContext.request.contextPath}/team_member_test.jsp" class="menu-item">팀 설정</a>
        <!-- 다른 메뉴 항목들 추가 -->
    </div>
</div>
