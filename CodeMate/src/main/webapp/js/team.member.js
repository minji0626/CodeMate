$(document).ready(function() {
    
    /*팀 설정에서 setting icon 눌렀을 때 dropdown Menu 불러오기*/
    $('.team_setting_btn').click(function(event) {
        const dropdownMenu = $(this).next();
        dropdownMenu.toggle();
        
        // 다른 드롭다운 메뉴 숨기기
        $('.dropdown_menu').not(dropdownMenu).hide();
        
        event.stopPropagation();
    });

    // 페이지를 클릭하면 모든 드롭다운 메뉴 숨기기
    $(document).click(function() {
        $('.dropdown_menu').hide();
    });

    // 드롭다운 메뉴 클릭 시 이벤트 전파 막기
    $('.dropdown_menu').click(function(event) {
        event.stopPropagation();
    });
    
    
    /*mate_review 리뷰 작성하는 모달*/
    const mate_review = $("#mate_review");
	
	/* 모달 on 함수 */
    function modalOn() {
        mate_review.css("display", "flex");
    }

	/* 모달 off 함수 */
    function modalOff() {
        mate_review.hide();
    }
	
	/* 리뷰 쓰기 메뉴 */
    $(".review-link").click(function(e) {
        e.preventDefault();
        
        // 개인의 닉네임 가져와서 innerText 시키기
        const nickname = $(this).closest('.mem_personal').find('.team_mem_nickname').data('nickname');
        $('.mate_review_profile .user_nickname').text(nickname);
        // 개인의 아이디 가져와서 innerText 시키기
        const id = $(this).closest('.mem_personal').find('.team_mem_nickname').data('id');
        $('.mate_review_profile .user_id').text(id);
        // 개인의 레벨 가져와서 innerText 시키기
        const level = $(this).closest('.mem_personal').find('.team_mem_nickname').data('level');
        $('.mate_review_profile .user_level').text(level);
        // 개인의 사진 가져와서 setAttribute 시키기
        const profileImage = $(this).closest('.mem_personal').find('.team_mem_profile_img');
        const imageUrl = profileImage.attr('src');
        $('.mate_review_profile .profile_image').attr('src', imageUrl);
        
        modalOn();
    });
	
	/* close-btn을 클릭하면 modal off 함수가 호출된다*/
	$('#close-btn, .mate_review_close').click(function() {
   		 modalOff(); // 모달창 닫기 함수 호출
   		 $('#mr_content').val('');
	});

    mate_review.click(function(e) {
        if ($(e.target).is(mate_review)) {
            modalOff();
        }
    });
    
});

$(function(){
	
})




