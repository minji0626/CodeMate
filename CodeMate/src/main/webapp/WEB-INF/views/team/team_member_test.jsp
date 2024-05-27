<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team Member Test Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/team_nav.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/team_member.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <title>Team Main Page - Test</title>
  </head>
  <body id="team_main_body">
  <div class="page-container">
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <jsp:include page="team_nav_test.jsp"/>
  <div id="mem_container">
  
     <div class="mem_personal">
        <ul>
            <li><img src="${pageContext.request.contextPath}/images/cmj/boy_profile.png" class="team_mem_profile_img"></li>
            <li><span class="team_mem_status">팀장</span></li>
            <li><span class="team_mem_nickname" data-nickname="홍길동" data-id="honggildong" data-level="2">홍길동</span></li>
            <li>
                <button class="team_setting_btn"><img src="${pageContext.request.contextPath}/images/cmj/setting_icon.png" class="setting_btn"></button>
                <div class="dropdown_menu">
                    <a href="#" class="review-link">리뷰 쓰기</a>
                    <a href="#" id="mem_delete_btn">팀원 삭제</a>
                    <a href="#" id="mem_auth_btn">팀장 위임</a>
                </div>
            </li>
        </ul>
    </div>

    <div class="mem_personal">
        <ul>
            <li><img src="${pageContext.request.contextPath}/images/cmj/girl_profile.png" class="team_mem_profile_img"></li>
            <li><span class="team_mem_status">팀원</span></li>
            <li><span class="team_mem_nickname" data-nickname="유재석" data-id="jaeseok" data-level="3">유재석</span></li>
			<li>
                <button class="team_setting_btn"><img src="${pageContext.request.contextPath}/images/cmj/setting_icon.png" class="setting_btn"></button>
                <div class="dropdown_menu">
                   <a href="#" class="review-link">리뷰 쓰기</a>
                    <a href="#" id="mem_delete_btn">팀원 삭제</a>
                    <a href="#" id="mem_auth_btn">팀장 위임</a>
                </div>
            </li>
        </ul>
    </div>
    <div class="mem_personal">
        <ul>
            <li><img src="${pageContext.request.contextPath}/images/cmj/girl_profile.png" class="team_mem_profile_img"></li>
            <li><span class="team_mem_status">팀원</span></li>
            <li><span class="team_mem_nickname" data-nickname="유재석" data-id="jaeseok" data-level="3">유재석</span></li>
			<li>
                <button class="team_setting_btn"><img src="${pageContext.request.contextPath}/images/cmj/setting_icon.png" class="setting_btn"></button>
                <div class="dropdown_menu">
                   <a href="#" class="review-link">리뷰 쓰기</a>
                    <a href="#" id="mem_delete_btn">팀원 삭제</a>
                    <a href="#" id="mem_auth_btn">팀장 위임</a>
                </div>
            </li>
        </ul>
    </div>
    <div class="mem_personal">
        <ul>
            <li><img src="${pageContext.request.contextPath}/images/cmj/girl_profile.png" class="team_mem_profile_img"></li>
            <li><span class="team_mem_status">팀원</span></li>
            <li><span class="team_mem_nickname" data-nickname="유재석" data-id="jaeseok" data-level="3">유재석</span></li>
			<li>
                <button class="team_setting_btn"><img src="${pageContext.request.contextPath}/images/cmj/setting_icon.png" class="setting_btn"></button>
                <div class="dropdown_menu">
                   <a href="#" class="review-link">리뷰 쓰기</a>
                    <a href="#" id="mem_delete_btn">팀원 삭제</a>
                    <a href="#" id="mem_auth_btn">팀장 위임</a>
                </div>
            </li>
        </ul>
    </div>
    <div class="mem_personal">
        <ul>
            <li><img src="${pageContext.request.contextPath}/images/cmj/girl_profile.png" class="team_mem_profile_img"></li>
            <li><span class="team_mem_status">팀원</span></li>
            <li><span class="team_mem_nickname" data-nickname="유재석" data-id="jaeseok" data-level="3">유재석</span></li>
			<li>
                <button class="team_setting_btn"><img src="${pageContext.request.contextPath}/images/cmj/setting_icon.png" class="setting_btn"></button>
                <div class="dropdown_menu">
                   <a href="#" class="review-link">리뷰 쓰기</a>
                    <a href="#" id="mem_delete_btn">팀원 삭제</a>
                    <a href="#" id="mem_auth_btn">팀장 위임</a>
                </div>
            </li>
        </ul>
    </div>

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
					<a href="#"> 
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
</div>
<script type="text/javascript" src="js/team.member.js"></script>
    
</body>
</html>