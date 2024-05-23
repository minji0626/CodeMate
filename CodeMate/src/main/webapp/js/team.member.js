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
});
