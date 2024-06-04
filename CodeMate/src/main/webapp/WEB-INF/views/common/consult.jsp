<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>

/* 모달 배경 */
#consult_modal {
	display: none;
	position: fixed;
	z-index: 1000;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: hidden;
	background-color: rgba(0, 0, 0, 0.5);
}

/* 모달 내용 */
.consult_modal_window {
	background-color: white;
	position: fixed;
	bottom: 20px;
	left: 20px;
	transform: translate(0%, 0%); /* 왼쪽 하단에 고정*/
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
	width: 20%;
	height: 62%;
	overflow-y: auto; /* 스크롤 가능 */
}

/* 모달 닫기 버튼 (모달 외부 클릭 시) */
#consult_modal.close-modal {
	display: block;
}

/* 모달 내용 (모달 외부 클릭 시) */
#consult_modal .consult_modal_window.close-modal {
	display: none;
}

/* 모달 타이틀 */
.consult_modal_title {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}

/* 폼 요소 스타일 */
.input-check {
	width: calc(100% - 20px);
	padding: 10px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
}

/* 버튼 스타일 */
.btn-div {
	margin-top: 20px;
	display: flex;
	justify-content: space-between;
}

.btn {
	width: 30%; padding : 10px 20px;
	border: none;
	border-radius: 5px;
	background-color: #007bff;
	color: white;
	cursor: pointer;
	margin: 0 auto 10px;
	padding: 10px 20px;
}

#consult_form label {
	text-align: left;
	margin-bottom: 2%;
}

.consult_modal_close {
 cursor: pointer;
 
}


/* 1:1문의와 select 요소의 위치 조정 */
#consult_auth {
	display: inline-block;
	vertical-align: top;
	margin-right: 10px;
}

/* 문의 내용 입력란 스타일 */
#consult_content {
	width: calc(100% - 20px);
	height: 200px;
	margin: 0 auto 10px;
}
</style>
<!-- 1:1 문의 버튼 -->
<div class="fixed-button">
	<img src="${pageContext.request.contextPath}/images/consult2.png"
		alt="1:1 문의" id="btn-modal">
</div>

<!-- 1:1문의 모달 -->
<div id="consult_modal">
	<div class="consult_modal_window">
		<!-- 프로필 제목 -->
		<div class="consult_modal_title">
			<h2>1:1문의</h2>
			<!-- 창 닫기 -->
			<div class="consult_modal_close">X</div>
		</div>
		<!-- 본문 -->
		<div class="modal_content">
			<!-- 문의하기 DIV -->
			<div class="consult_content_div">
				<form id="consult_form"
					action="${pageContext.request.contextPath}/main/sendConsult.do"
					method="post">
					<ul>
						<li><select name="cs_category" class="input-check" id="consult_auth">
								<option value="" selected>구분</option>
								<option value="0">일반문의</option>
								<option value="1">신고</option>
						</select></li>
						<li><label for="cs_title">제목</label> <input type="text"
							name="cs_title" id="cs_title" placeholder="문의 제목을 입력하세요"
							class="input-check" maxlength="70"></li>
						<li><textarea id="consult_content" name="cs_content"
								placeholder="문의 내용을 입력하세요" class="input-check"></textarea></li>
						<li><label for="email">이메일</label> <input type="email"
							name="email" id="email" placeholder="회신받을 이메일을 입력하세요"
							class="input-check"></li>
					</ul>
					<!-- 버튼 -->
					<div class="btn-div">
						<input type="submit" class="btn" id="send_consult_btn"
							value="문의하기"> <input type="button"
							class="consult_modal_close btn" id="close_btn" value="취소">
					</div>

				</form>
			</div>

		</div>
	</div>
</div>

<script>
    const consult_modal = document.getElementById("consult_modal");

    function modalOn() {        	
    	const background = document.getElementById("modal_background");
    	background.classList.add("modal-on-background");
    	consult_modal.style.display = "flex";
    }

    function isModalOn() {
        return consult_modal.style.display === "flex";
    }

    function modalOff() {
    	const background = document.getElementById("modal_background");
    	background.classList.remove("modal-on-background");
    	consult_modal.style.display = "none";
    }

    const btnModal = document.getElementById("btn-modal");

    btnModal.onclick = function() {
    	if (${empty mem_num}) {
    		alert('로그인 후 이용해주세요');
    		window.location.replace("../member/loginForm.do");
    	} else {
            modalOn();
        }
    }

    // 첫 번째 닫기 버튼
    document.querySelector(".consult_modal_close").addEventListener("click", e => {
        modalOff();
    });

    // 마지막 닫기 버튼에 대한 이벤트 리스너 추가
    document.getElementById("close_btn").addEventListener("click", e => {
        modalOff();
    });

    // 유효성 검사 추가
    document.getElementById("consult_form").addEventListener("submit", function(event) {
        let valid = true;
        let firstInvalidElement = null;

        // 입력값이 있는지 확인
        document.querySelectorAll(".input-check").forEach(function(element) {
            if (!element.value.trim()) {
                valid = false;
                if (!firstInvalidElement) {
                    firstInvalidElement = element;
                }
                let labelText = element.previousElementSibling ? element.previousElementSibling.innerText : '필수 항목';
                alert(labelText + '을 입력해주세요');
                element.focus();
                return false;
            }
        });

        if (!valid) {
            event.preventDefault();
        }
    });
</script>
