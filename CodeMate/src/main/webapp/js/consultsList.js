$(document).ready(function() {
    //체크박스 선택 후 처리하기/처리취소 이벤트 연결
    $('#confirm_cs').click(function(event) {
        var cs_num_list = [];
        $('.consultCheck:checked').each(function() {
            cs_num_list.push($(this).data('csnum'));
        });
        
        console.log(cs_num_list);
        event.preventDefault;
    });


});