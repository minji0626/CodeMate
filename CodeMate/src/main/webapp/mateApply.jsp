<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메이트 프로필 모달</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/cje.ma.css" type="text/css">

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
