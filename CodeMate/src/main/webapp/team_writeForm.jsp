<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />
    <link rel="stylesheet" href="css/share.css" type="text/css">
    <link rel="stylesheet" href="css/cmj.css" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<title>팀 게시판 작성 Test</title>
<script type="text/javascript">
window.onload = function(){
	const myForm = document.getElementById('write_form');
	myForm.onsubmit = function(){
		const title = document.getElementById('title');
		const content = document.getElementById('content');

		if(title.value.trim()==''){
				alert('제목을 입력하세요.');
				title.value='';
				title.focus();
				return false;
		};
		
		if(content.value.trim()==''){
			alert('내용을 입력하세요.');
			content.value='';
			content.focus();
			return false;
		};

	};
};
</script>
</head>
<body>
<%@ include file="team_nav_test.jsp" %>
	<form action="write.do" id="write_form" method="post" enctype="multipart/form-data">
			<ul>
			 	<li>
			 		<label for="title">제목</label>
			 		<input type="text" name="title" id="title" maxlength="50">
			 	</li>
			 	<li>
			 		<label for="content">내용</label>
			 		<textarea rows="5" cols="40" name="content" id="content"></textarea>
			 	</li>
			 	<li>
			 		<label for="filename">이미지</label>
			 		<input type="file" name="filename" id="filename" accept="image/gif,image/png,image/jpeg">
			 	</li>
			</ul>
			
			<div class="align-center">
				<input type="submit" value="등록">
				<input type="button" value="목록" onclick="location.href='list.do">
		</div>
		</form>
</body>
</html>