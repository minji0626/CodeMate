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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cje.mp.css" type="text/css">
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
