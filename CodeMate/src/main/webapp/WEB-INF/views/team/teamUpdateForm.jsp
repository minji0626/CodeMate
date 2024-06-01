<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/team_board_write.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<title>CODEMATE Team Project</title>
    <link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<script type="text/javascript">
window.onload = function(){
    const myForm = document.getElementById('update_form');
    myForm.onsubmit = function(){
        const title = document.getElementById('tb_title');
        const content = document.getElementById('tb_content');
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
        const radio = document.querySelectorAll('input[type=radio]:checked');
			if(radio.length < 1){
				alert('글 종류를 선택해주세요.');
				return false;
			};

    };
};
</script>
</head>
<body id="team_main_body">
  <div class="page-container">
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <jsp:include page="/WEB-INF/views/team/teamNav.jsp"/>
    <div class="container_write">
        <form action="tboardUpdate.do" id="update_form" method="post" enctype="multipart/form-data">
        <input type="hidden" name="tb_num" value="${tboard.tb_num}">
        <input type="hidden" name="team_num" value="${tboard.team_num}">
        	<div class="form-group">
				<input type="radio" name="tb_auth" value="1" id="tb_auth1">
				공지사항
				<input type="radio" name="tb_auth" value="2" id="tb_auth2"> 
				일반 게시글
			</div>
            <div class="form-group">
                <label for="tb_title">제목</label>
                <input type="text" name="tb_title" id="tb_title" maxlength="50" class="form-control" value="${tboard.tb_title }">
            </div>
            <div class="form-group">
                <label for="tb_content">내용</label>
                <textarea rows="14" name="tb_content" id="tb_content" class="form-control">${tboard.tb_content}</textarea>
            </div>
            <div class="form-group">
                <label for="tb_file">이미지</label>
                
                <c:if test="${!empty tboard.tb_file}">
    					<div id="file_detail">
        					<img src="${pageContext.request.contextPath}/upload/${tboard.tb_file}" width="150" style="margin-left: 3%;">
        					<br>
        					<input type="button" value="파일 삭제" id="file_del" class="btn">
        				</div>
        				<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
        				<script type="text/javascript">
        				$(function(){
        					$('#file_del').click(function(){
        						let choice = confirm('파일을 삭제하시겠습니까?');
        						if(choice){
        							// 서버와 통신
        							$.ajax({
        								url:'deleteFile.do',
        								type:'post',
        								data:{tb_num:${tboard.tb_num},team_num:${tboard.team_num}},
        								dataType:'json',
        								success:function(param){
        									if(param.result == 'logout'){
        										alert('로그인 후 사용바랍니다.');
        									} else if(param.result=='success'){
        										$('#file_detail').hide();
        									} else if(param.result =='wrongAccess'){
        										alert('잘못된 접근입니다.');
        									} else{
        										alert('파일 삭제 도중 오류가 발생하였습니다.');
        									}
        								},
        								error:function(){
        									alert('네트워크 오류가 발생하였습니다.');
        								}
        							}); // ajax end
        						} // if(choice) end
        					});
        				});
        				</script>
        		</c:if>
                
                <input type="file" name="tb_file" id="tb_file" accept="image/gif,image/png,image/jpeg" class="form-control btn">

				
            </div>
            <div class="form-actions">
                <input type="submit" value="수정" class="btn btn-primary">
                <input type="button" value="목록" class="btn btn-secondary" onclick="location.href='${pageContext.request.contextPath}/team/teamBoardList.do?team_num=${tboard.team_num}'">
            </div>
        </form>
    </div>
</div>
</body>
</html>
