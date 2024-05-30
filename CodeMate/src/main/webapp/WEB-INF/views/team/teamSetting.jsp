<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀 설정</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/team_member.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="page-container">
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <jsp:include page="/WEB-INF/views/team/teamNav.jsp"/>
    
    <c:if test="${param.team_num != team_num }">
    <div id="wrong_access" style="text-align: center; margin-top: 25%; font-size: 20px; font-weight: bold;">
    	잘못된 접근입니다.
    </div>
    </c:if>
    
    <c:if test="${param.team_num == team_num }">
    <div id="mem_container">
        <input type="hidden" name="team_num" value="${param.team_num}">
        
        <c:if test="${count == 0}">
            저장된 정보가 없습니다.
        </c:if>
        <c:if test="${count > 0}">
        <!-- 팀원 목록 출력! -->
        <!-- 팀장 우선 출력하기-->
        <c:forEach  var="tmember" items="${list}">
        	<c:if test="${tmember.tm_auth == 4 }">
        	
                <div class="mem_personal" <c:if test="${mem_num == tmember.mem_num }">style="box-shadow: 0 0 10px rgb(204, 247, 217);"</c:if>>
                    <ul>
                        <li>
                        	<c:if test="${!empty tmember.mem_photo }">
                        	<img src="${pageContext.request.contextPath}/upload/${tmember.mem_photo}" class="team_mem_profile_img">
                        	</c:if>
                        	<c:if test="${empty tmember.mem_photo }">
                        	<img src="${pageContext.request.contextPath}/images/face.png" class="team_mem_profile_img">
                        	</c:if>
                        </li>
                        <li>
                           <span class="team_mem_status">팀장</span>
                        </li>
                        <li>
                        <span class="team_mem_nickname" data-tmnum="${tmember.team_num }" data-memnum="${tmember.mem_num}" data-nickname="${tmember.mem_nickname}" data-id="${tmember.mem_id}" data-level="${tmember.mem_level}">${tmember.mem_nickname}</span>
                        </li>
                        <c:if test="${mem_num != tmember.mem_num }">
                        <li>
                            <button class="team_setting_btn"><img src="${pageContext.request.contextPath}/images/cmj/setting_icon.png" class="setting_btn"></button>
                            <div class="dropdown_menu">
                                <a class="review-link" style="cursor: pointer">리뷰 쓰기</a>
                                <c:if test="${tm_auth == 4 }">
                                <!-- 팀장인 경우 데이터 속성 추가 -->
								<a class="mem_delete_btn" style="cursor: pointer" data-team-num="${tmember.team_num}" data-mem-num="${tmember.mem_num}">팀원 삭제</a>
                                <a class="mem_auth_btn" style="cursor: pointer">팀장 위임</a>
                                </c:if>
                            </div>
                        </li>
                        </c:if>
                    </ul>
                </div>
                </c:if>
            </c:forEach>
        
        	<!-- 팀원 출력하기! -->
            <c:forEach  var="tmember" items="${list}">
            <c:if test="${tmember.tm_auth == 3 }">
                <div class="mem_personal" <c:if test="${mem_num == tmember.mem_num }">style="box-shadow: 0 0 10px rgb(204, 247, 217);"</c:if>>
                    <ul>
                        <li>
                        	<c:if test="${!empty tmember.mem_photo }">
                        	<img src="${pageContext.request.contextPath}/upload/${tmember.mem_photo}" class="team_mem_profile_img">
                        	</c:if>
                        	<c:if test="${empty tmember.mem_photo }">
                        	<img src="${pageContext.request.contextPath}/images/face.png" class="team_mem_profile_img">
                        	</c:if>
                        </li>
                        <li>
                           <span class="team_mem_status">팀원</span>
                        </li>
                        <li>
                        <span class="team_mem_nickname" data-tmnum="${tmember.team_num }" data-memnum="${tmember.mem_num}" data-nickname="${tmember.mem_nickname}" data-id="${tmember.mem_id}" data-level="${tmember.mem_level}">${tmember.mem_nickname}</span>
                        </li>
                        <c:if test="${mem_num != tmember.mem_num }">
                        <li>
                            <button class="team_setting_btn"><img src="${pageContext.request.contextPath}/images/cmj/setting_icon.png" class="setting_btn"></button>
                            <div class="dropdown_menu">
                                <a class="review-link" style="cursor: pointer">리뷰 쓰기</a>
                                <c:if test="${tm_auth == 4 }">
                                <!-- 팀장인 경우 데이터 속성 추가 -->
								<a class="mem_delete_btn" style="cursor: pointer" data-team-num="${tmember.team_num}" data-mem-num="${tmember.mem_num}">팀원 삭제</a>
                                <a class="mem_auth_btn" style="cursor: pointer">팀장 위임</a>
                                </c:if>
                            </div>
                        </li>
                        </c:if>
                    </ul>
                </div>
                </c:if>
            </c:forEach>
        </c:if>
    </div>
    </c:if>
</div>

<div id="mate_review" style="display:none;">
    <div class="mate_review_window">
        <div class="mate_review_title">
            <h2>리뷰 작성</h2>
            <div class="mate_review_close">X</div>
        </div>
        <div class="content">
            <div class="mate_review_profile">
                <div class="photo_div">
                    <img src="" class="profile_image">
                </div>
                <div class="mr_account_nickname">
                    <div class="user_info">
                        <span class="user_nickname"></span> 
                        <span class="user_level"></span>
                    </div>
                    <a class="user_mp" href="#"> 
                        <span class="user_id"></span>
                        <img src="${pageContext.request.contextPath}/images/cmj/share_icon.png" id="share_img">
                    </a>  
                </div>
            </div>
            <div class="mr_content_div">
                <h4>내용</h4>
                <form id="mr_form" action="">
                    <textarea id="mr_content"></textarea>
                    <div class="btn-div">
                        <input type="submit" id="submit-btn" value="제출">
                        <input type="button" class="mate_review_close" id="close-btn" value="취소">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/team.member.js"></script>
</body>
</html>
