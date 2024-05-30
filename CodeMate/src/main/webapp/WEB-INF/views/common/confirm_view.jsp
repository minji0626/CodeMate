<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	if (confirm('${notice_msg}')) {
		location.href = '${notice_url}';
	};
</script>
