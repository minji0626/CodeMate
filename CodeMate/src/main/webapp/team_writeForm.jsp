<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/team_nav.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmj.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css" type="text/css">
    
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
    <div class="container_write">
        <form action="" id="write_form" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" name="title" id="title" maxlength="50" class="form-control">
            </div>
            <div class="form-group">
                <label for="content">내용</label>
                <textarea rows="20" cols="100" name="content" id="content" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <label for="filename">이미지</label>
                <input type="file" name="filename" id="filename" accept="image/gif,image/png,image/jpeg" class="form-control">
            </div>
            <div class="form-actions">
                <input type="submit" value="등록" class="btn btn-primary">
                <input type="button" value="목록" class="btn btn-secondary" onclick="location.href='team_board_test.jsp'">
            </div>
        </form>
    </div>
</body>
</html>
