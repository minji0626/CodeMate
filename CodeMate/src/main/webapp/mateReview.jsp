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
/*모달창 제외한 나머지 페이지*/
*{
	font-family: "Noto Sans KR", sans-serif;
}

#mate_review {
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
/* 모달 창*/
.mate_review_window {
    background: #ffffff;
    width: 600px;
    position: relative;
    border-radius: 15px;
    max-height: 80vh; /* 최대 높이를 설정 */
    overflow-y: auto; /* 수직 스크롤바를 추가 */
}

/********************************
/*모달 창 제목 바 DIV*/
.mate_review_title {
	padding: 10px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	color: black;
	background: #5ACA7C;
	border-top-left-radius: 15px;
    border-top-right-radius:15px;
}

/*모달 창 제목 글자*/
.mate_review_title h2 {
	font-size: 13pt;
	margin:0;
	margin-left:10px;
}

/*메이트 프로필 모달 창 제목의 닫기 버튼*/
.mate_review_close {
	cursor: pointer;
	color: black;
}

/********************************
/*모달 창 본문 DIV*/
.content {
	margin-top: 20px;
	padding: 0px 10px;
	color: black;
}

/********************************
/* 프로필 (닉네임, 프사 있는 곳) DIV*/
.mate_review_profile {
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
.mr_account_nickname {
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


/********************************/
/*리뷰 쓰기 컨텐츠란 전체 DIV*/
.mr_content_div {
	margin-left: 20px;
	padding-top: 5px;
	margin-right: 20px;
}

/*메이트 프로필에 쓰이는 내용란 (회색 박스) DIV*/
.mr_content {
	border-radius: 8px;
	background: #e0e0e0;
}

.mr_content p{
	padding: 15px;
}

#mr_content{
	border-radius: 8px;
	background: #e0e0e0;
	border-radius: 8px;
	background: #e0e0e0;
	border : none;
	width: 535px;
	height: 300px;
	margin: 0 auto;
}


/*****************************/
/*버튼*/
.btn-div {
    display: flex;
    justify-content: space-between; /* 두 버튼을 좌우로 나란히 정렬합니다 */
    align-items: center; /* 버튼을 수직으로 중앙 정렬합니다 */
    margin-top: 25px;
    margin-bottom: 40px;
    
}


/*닫기 버튼*/
#close-btn, #submit-btn {
	background: black;
	width: 320px;
	height: 50px;
	display: flex;
	justify-content: center;
	align-items: center;	
	text-align: center;
	border-radius: 30px;
	margin: 0 10px; /* 버튼 간격을 조정합니다 */
}

#close-btn {
	margin-right: 10px;
}

#submit-btn{
	margin-left: 10px;
}

#close-btn span, #submit-btn {
	color: white;
	 font-size: 14pt; 
}


</style>
</head>
<body>
    <div>
        <button id="btn-modal">모달창 열려랏!</button>
    </div>

    <!-- 메이트 리뷰 -->
    <div id="mate_review">
        <!-- 프로필 창 -->
        <div class="mate_review_window">
            <!-- 프로필 제목 -->
            <div class="mate_review_title">
                <h2>리뷰 작성</h2>
                <!-- 창 닫기 -->
                <div class="mate_review_close">X</div>
            </div>
            <!-- 본문 -->
            <div class="content">
                <!-- 리뷰 쓰려는 팀원의 프로필 (닉네임, 프사) -->
                <div class="mate_review_profile">
                    <!-- 프로필 사진 -->
                    <div class="photo_div">
                        <img src="images/face.png" class="profile_image">
                    </div>    
                    <!-- 닉네임 -->
                    <div class="mr_account_nickname">
                        <span class="user_nickname">분모재</span>
                        <!-- 레벨 이미지 추가하기 -->
                        <img>
                        <br>
                        <span class="user_id">boonmojae</span>
                        <!-- 아이디 옆 이미지 추가하기 -->
                        <img>
                    </div>
                </div>
                
                <!-- 리뷰 쓰기 DIV -->
                <div class="mr_content_div">
                    <h4>내용</h4>
                    <form id="mr_form" action="">
                        <textarea id="mr_content"></textarea>
                        <!-- 버튼 -->
		                <div class="btn-div">
		                    <input type="submit" id="submit-btn" value="제출">
		                    <div class="mate_review_close" id="close-btn">
		                        <span>취소</span>
		                    </div>
		                </div>
                    </form>
                </div>
                
            </div>
        </div>
    </div>


    <script>
        const mate_review = document.getElementById("mate_review");

        function modalOn() {
            mate_review.style.display = "flex";
        }

        function isModalOn() {
            return mate_review.style.display === "flex";
        }

        function modalOff() {
            mate_review.style.display = "none";
        }

        const btnModal = document.getElementById("btn-modal");

        btnModal.addEventListener("click", e => {
            modalOn();
        });

        // 첫 번째 닫기 버튼
        document.querySelector(".mate_review_close").addEventListener("click", e => {
            modalOff();
        });

        // 마지막 닫기 버튼에 대한 이벤트 리스너 추가
        document.getElementById("close-btn").addEventListener("click", e => {
            modalOff();
        });
    </script>
</body>


</html>
