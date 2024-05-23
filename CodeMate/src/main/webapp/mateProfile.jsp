<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메이트 프로필 모달</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

<style>
/***************
메이트 프로필 모달 창
***************/

/********************************
/*메이트 프로필 모달창 제외한 나머지 페이지*/
*{
	font-family: "Noto Sans KR", sans-serif;
}
#mp_view {
	width: 100%;
	height: 100%;
	position: absolute;
	left: 0;
	top: 0;
	display: none;
	align-items: center;
	justify-content: center;
	background: rgba(71, 58, 58, 0.8);
}

/********************************
/*메이트 프로필 모달 창*/
.mp_view_window {
    background: #ffffff;
    width: 800px;
    position: relative;
    border-radius: 15px;
    max-height: 80vh; /* 최대 높이를 설정 */
    overflow-y: auto; /* 수직 스크롤바를 추가 */
}

/********************************
/*메이트 프로필 모달 창 제목 바 DIV*/
.mp_view_title {
	padding: 10px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	color: black;
	background: #5ACA7C;
	border-top-left-radius: 15px;
    border-top-right-radius:15px;
}

/*메이트 프로필 모달 창 제목 글자*/
.mp_view_title h2 {
	font-size: 13pt;
	margin:0;
	margin-left:10px;
}

/*메이트 프로필 모달 창 제목의 닫기 버튼*/
.mp_view_close {
	cursor: pointer;
	color: black;
}

/********************************
/*메이트 프로필 모달 창 본문 DIV*/
.content {
	margin-top: 20px;
	padding: 0px 10px;
	color: black;
}

/********************************
/*메이트 프로필 (닉네임, 프사 있는 곳) DIV*/
.mp_account_profile {
	display: flex;
	align-items: center;
	height: 80px;
	padding-bottom: 20px;
}

/* 사용자 프사 */
.profile_image {
	margin-left: 20px;
    width: 60px;
    height: 60px;
    border-radius: 50%; /* 동그라미 모양으로 설정 */
}

/*닉네임, 아이디 있는 div*/
.mp_account_nickname {
	margin-left: 15px;
}

.user_nickname {
	color: black;
	font-size: 14pt;
	font-weight: bold;
}

.user_id {
	color: #32B0FF;
}

/*수정하기 버튼*/
.mp_view_modify {
	display: flex;
	justify-content: center;
	align-items: center;
	position: absolute;
	background: #4D9964;
	border-radius: 15px;
	width: 100px;
	height: 35px;
	right: 50px;
}

.mp_view_modify span {
	text-align: center;
	display: inline-block;
	color: white;
	font-weight: bold;
}

/********************************/
/*메이트 프로필 컨텐츠란 전체 DIV*/
.mp_content_div {
	margin-left: 20px;
	padding-top: 5px;
	margin-right: 20px; 
}

/*메이트 프로필에 쓰이는 내용란 (회색 박스) DIV*/
.mp_content {
	border-radius: 8px;
	background: #e0e0e0;
}

.mp_content p, .mp_mate_review p, .mp_project p {
	padding: 15px;
}

/*************************/
/* 하드 스킬과 소프트 스킬을 감싸는 div에 대한 스타일 */
.skill-container {
	display: flex;
}

/* 각 스킬 요소에 대한 스타일 */
.skill-item {
	width: calc(46% - 10px);
	margin-right: 10px; /* 여기를 10px로 변경 */
	margin-bottom: 20px;
}

/*****************************/
/*프로젝트 경험 DIV*/
.mp_project {
	border-radius: 8px;
	border: 1px solid #adadad;
	padding-top: 20px;
}

/*****************************/
/*코드메이트 후기 DIV*/
.mp_mate_review {
	border-radius: 8px;
	background: #e0e0e0;
	border: 1px solid #adadad;
	margin-bottom: 70px;
}

/*****************************/
/*마지막 닫기 버튼*/
#close-btn {
	background: black;
	width: 200px;
	height: 50px;
	margin: 0 auto;
	margin-bottom: 40px;
	display: flex;
	justify-content: center;
	align-items: center;	
	border-radius: 8px;
}

#close-btn span {
	color: white;
}

/*********************************/
/* 프로젝트 경험*/
.pj_category{
	color: green;
	font-size: 10pt;
	margin-left: 20px;
	padding: 0;
	padding-top:20px;
	margin-bottom: 20px;
}

.pj_name{
	margin-top: 3px;
	margin-bottom: 1px;
	margin-left: 20px;
	padding: 0;
}

.pj_period{
	color: #bdbdbd;
	font-size: 8pt;
	padding: 0;
	margin-top: 0px;
	margin-left: 20px;
	margin-bottom: 0px;
}

.pj_content{
	font-size: 11pt;
	margin-top: 0px;
	margin-left: 10px;
	
}

</style>
</head>
<body>
	<div>
		<button id="btn-modal">모달창 열려랏!</button>
	</div>

	<!-- 메이트 프로필 -->
	<div id="mp_view">
		<!-- 메이트 프로필 창 -->
		<div class="mp_view_window">
			<!-- 메이트 프로필 제목 -->
			<div class="mp_view_title">
				<h2>메이트 프로필</h2>
				<!-- 메이트 프로필 창 닫기 -->
				<div class="mp_view_close">X</div>
			</div>
			<!-- 메이트 프로필 본문 -->
			<div class="content">
				<!-- 본인 프로필 (닉네임, 프사) -->
				<div class="mp_account_profile">
					<!-- 프로필 사진 -->
					<div class="photo_div">
						<img src="images/face.png" class="profile_image">
					</div>	
					<!-- 닉네임 -->
					<div class="mp_account_nickname">
						<span class="user_nickname">분모재</span>
<!-- 레벨 이미지 추가하기 -->
						<img>
						<br>
						<span class="user_id">boonmojae</span>
<!-- 아이디 옆 이미지 추가하기 -->
						<img>
					</div>
					<div class="mp_view_modify">
						<span>수정하기</span>
					</div>	
				</div>
				<!-- 포지션 DIV -->
				<!-- 
아마 여기 p 태그들에 클래스 부여해야 할 듯
				 -->
				<div class="mp_content_div">
					<h4>포지션</h4>
					<div class="mp_content">
						<p>
						</p>
					</div>
				</div>
				<!-- 자기소개 DIV -->
				<div class="mp_content_div">
					<h4>자기소개</h4>
					<div class="mp_content">
						<p>
						
						</p>
					</div>
				</div>
				<!-- 스킬 DIV -->
				<div class="skill-container">
					<!-- 하드스킬 DIV -->
					<div class="mp_content_div skill-item">
						<h4>하드스킬</h4>
						<div class="mp_content">
							<p>
								<!-- 내용 -->
							</p>
						</div>
					</div>
					<!-- 소프트스킬 DIV -->
					<div class="mp_content_div skill-item">
						<h4>소프트스킬</h4>
						<div class="mp_content">
							<p>
								<!-- 내용 -->
							</p>
						</div>
					</div>
				</div>
				<!-- 프로젝트 경험 DIV -->
				<div class="mp_content_div">
					<h4>프로젝트 경험</h4>
					<div class="mp_project">
						<span class="pj_category"> 프로젝트 분류</span>
						<h4 class="pj_name">프로젝트 이름</h4>
						<span class="pj_period">프로젝트 기간</span>
						<p class="pj_content">프로젝트 설명</p>
					</div>
				</div>
				<!-- 코드메이트 후기 DIV -->
				<div class="mp_content_div">
					<h4>메이트 후기</h4>
					<div class="mp_mate_review">
						<p>
							<!-- 내용 -->
						</p>
					</div>
				</div>
				<!-- 닫기 버튼 -->
				<div class="mp_view_close" id="close-btn">
				<span>닫기</span>
				</div>
			</div>
		</div>
	</div>
<script>
    const mp_view = document.getElementById("mp_view");

    function modalOn() {
        mp_view.style.display = "flex";
    }

    function isModalOn() {
        return mp_view.style.display === "flex";
    }

    function modalOff() {
        mp_view.style.display = "none";
    }

    const btnModal = document.getElementById("btn-modal");

    btnModal.addEventListener("click", e => {
        modalOn();
    });

    // 첫 번째 닫기 버튼
    document.querySelector(".mp_view_close").addEventListener("click", e => {
        modalOff();
    });

    // 마지막 닫기 버튼에 대한 이벤트 리스너 추가
    document.getElementById("close-btn").addEventListener("click", e => {
        modalOff();
    });
</script>

</body>
</html>
