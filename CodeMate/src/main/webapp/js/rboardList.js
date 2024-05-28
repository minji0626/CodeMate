$(document).ready(function() {
	$('.r-item').click(function() {
		let rb_num = $(this).attr('data-rbnum');
		location.href = '/rboard/detail.do?rb_num=' + rb_num;
	});
});