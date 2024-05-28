/**
 * 
 */

 document.addEventListener('DOMContentLoaded', function() {
    const settingButtons = document.querySelectorAll('.team_setting_btn');
    
    settingButtons.forEach(button => {
        button.addEventListener('click', function(event) {
            const dropdownMenu = this.nextElementSibling;
            dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
            
            // 다른 드롭다운 메뉴 숨기기
            document.querySelectorAll('.dropdown_menu').forEach(menu => {
                if (menu !== dropdownMenu) {
                    menu.style.display = 'none';
                }
            });
            
            event.stopPropagation();
        });
    });

    // 페이지를 클릭하면 모든 드롭다운 메뉴 숨기기
    document.addEventListener('click', function() {
        document.querySelectorAll('.dropdown_menu').forEach(menu => {
            menu.style.display = 'none';
        });
    });

    // 드롭다운 메뉴 클릭 시 이벤트 전파 막기
    document.querySelectorAll('.dropdown_menu').forEach(menu => {
        menu.addEventListener('click', function(event) {
            event.stopPropagation();
        });
    });
    
    const mate_review = document.getElementById("mate_review");

    function modalOn() {
        mate_review.style.display = "flex";
    }

    function modalOff() {
        mate_review.style.display = "none";
    }

    const reviewLinks = document.querySelectorAll(".review-link");

    reviewLinks.forEach(link => {
        link.addEventListener("click", e => {
            e.preventDefault();
            
            // 개인의 닉네임 가져와서 innerText 시키기
            const nickname = e.target.closest('.mem_personal').querySelector('.team_mem_nickname').getAttribute('data-nickname');
            document.querySelector('.mate_review_profile .user_nickname').innerText = nickname;
            // 개인의 아이디 가져와서 innerText 시키기
            const id = e.target.closest('.mem_personal').querySelector('.team_mem_nickname').getAttribute('data-id');
            document.querySelector('.mate_review_profile .user_id').innerText = id;
            // 개인의 레벨 가져와서 innerText 시키기
            const level = e.target.closest('.mem_personal').querySelector('.team_mem_nickname').getAttribute('data-level');
            document.querySelector('.mate_review_profile .user_level').innerText = level;
            // 개인의 사진 가져와서 setAttribute 시키기
            const profileImage = e.target.closest('.mem_personal').querySelector('.team_mem_profile_img');
            const imageUrl = profileImage.getAttribute('src');
            document.querySelector('.mate_review_profile .profile_image').setAttribute('src', imageUrl);
            
            modalOn();
        });
    });

		 document.getElementById("close-btn").addEventListener("click", e => {
		 modalOff(); // 모달창 닫기 함수 호출
	 });


    document.querySelector(".mate_review_close").addEventListener("click", e => {
        modalOff();
    });



    mate_review.addEventListener("click", e => {
        if (e.target === mate_review) {
            modalOff();
        }
    });
    
});
