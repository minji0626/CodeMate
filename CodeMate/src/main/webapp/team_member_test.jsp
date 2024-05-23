<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team Member Test Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmj.css" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
	<%@ include file="team_nav_test.jsp" %>
  <div id="mem_container">
  
     <div class="mem_personal">
        <ul>
            <li><img src="${pageContext.request.contextPath}/images/cmj/boy_profile.png" class="team_mem_profile_img"></li>
            <li><span class="team_mem_status">팀장</span></li>
            <li><span class="team_mem_nickname">홍길동</span></li>
            <li>
                <button class="team_setting_btn"><img src="${pageContext.request.contextPath}/images/cmj/setting_icon.png" class="setting_btn"></button>
                <div class="dropdown_menu">
                    <a href="#">리뷰 쓰기</a>
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
            <li><span class="team_mem_nickname">박길동</span></li>
			<li>
                <button class="team_setting_btn"><img src="${pageContext.request.contextPath}/images/cmj/setting_icon.png" class="setting_btn"></button>
                <div class="dropdown_menu">
                    <a href="#">리뷰 쓰기</a>
                    <a href="#" id="mem_delete_btn">팀원 삭제</a>
                    <a href="#" id="mem_auth_btn">팀장 위임</a>
                </div>
            </li>
        </ul>
    </div>
    <div class="mem_personal">
        <ul>
            <li><img src="${pageContext.request.contextPath}/images/cmj/girl2_profile.png" class="team_mem_profile_img"></li>
            <li><span class="team_mem_status">팀원</span></li>
            <li><span class="team_mem_nickname">김길동</span></li>
             <li>
                <button class="team_setting_btn"><img src="${pageContext.request.contextPath}/images/cmj/setting_icon.png" class="setting_btn"></button>
                <div class="dropdown_menu">
                    <a href="#">리뷰 쓰기</a>
                    <a href="#" id="mem_delete_btn">팀원 삭제</a>
                    <a href="#" id="mem_auth_btn">팀장 위임</a>
                </div>
            </li>
        </ul>
    </div>
    <div class="mem_personal">
        <ul>
            <li><img src="${pageContext.request.contextPath}/images/cmj/face.png" class="team_mem_profile_img"></li>
            <li><span class="team_mem_status">팀원</span></li>
            <li><span class="team_mem_nickname">이길동</span></li>
            <li><button class="team_setting_btn"><img src="${pageContext.request.contextPath}/images/cmj/setting_icon.png" class="setting_btn"></button>
            <div class="dropdown_menu">
                   <a href="#">리뷰 쓰기</a>
                   <a href="#" id="mem_delete_btn">팀원 삭제</a>
                   <a href="#" id="mem_auth_btn">팀장 위임</a>
                </div>
            </li>
        </ul>
    </div>
    <div class="mem_personal">
        <ul>
            <li><img src="${pageContext.request.contextPath}/images/cmj/account_circle.png" class="team_mem_profile_img"></li>
            <li><span class="team_mem_status">팀원</span></li>
            <li><span class="team_mem_nickname">분모재</span></li>
            <li><button class="team_setting_btn"><img src="${pageContext.request.contextPath}/images/cmj/setting_icon.png" class="setting_btn"></button>
            <div class="dropdown_menu">
                    <a href="#">리뷰 쓰기</a>
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
            <li><span class="team_mem_nickname">최길동</span></li>
            <li><button class="team_setting_btn"><img src="${pageContext.request.contextPath}/images/cmj/setting_icon.png" class="setting_btn"></button>
            <div class="dropdown_menu">
                   <a href="#">리뷰 쓰기</a>
                    <a href="#" id="mem_delete_btn">팀원 삭제</a>
                    <a href="#" id="mem_auth_btn">팀장 위임</a>
                </div>
            </li>
        </ul>
    </div>
    <div class="mem_personal">
        <ul>
            <li><img src="${pageContext.request.contextPath}/images/cmj/boy2_profile.png" class="team_mem_profile_img"></li>
            <li><span class="team_mem_status">팀원</span></li>
            <li><span class="team_mem_nickname">박길동</span></li>
            <li><button class="team_setting_btn"><img src="${pageContext.request.contextPath}/images/cmj/setting_icon.png" class="setting_btn"></button>
            <div class="dropdown_menu">
                    <a href="#">리뷰 쓰기</a>
                    <a href="#" id="mem_delete_btn">팀원 삭제</a>
                    <a href="#" id="mem_auth_btn">팀장 위임</a>
                </div>
            </li>
        </ul>
    </div>
    <div class="mem_personal">
        <ul>
            <li><img src="${pageContext.request.contextPath}/images/cmj/boy3_profile.png" class="team_mem_profile_img"></li>
            <li><span class="team_mem_status">팀원</span></li>
            <li><span class="team_mem_nickname">박길동</span></li>
            <li><button class="team_setting_btn"><img src="${pageContext.request.contextPath}/images/cmj/setting_icon.png" class="setting_btn"></button>
            <div class="dropdown_menu">
                    <a href="#">리뷰 쓰기</a>
                    <a href="#" id="mem_delete_btn">팀원 삭제</a>
                    <a href="#" id="mem_auth_btn">팀장 위임</a>
                </div>
            </li>
        </ul>
    </div>
</div>

    <script type="text/javascript" src="js/team.member.js"></script>
    
</body>
</html>