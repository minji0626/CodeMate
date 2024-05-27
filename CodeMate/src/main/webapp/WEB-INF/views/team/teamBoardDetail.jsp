<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/team_board_detail.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <title>글상세</title>  
</head>
<body>
	<div class="page-container">
   			 <jsp:include page="/WEB-INF/views/common/header.jsp"/>
   			 <jsp:include page="/WEB-INF/views/team/teamNav.jsp"/>
				<div class="board_container">
		        <!-- 게시글 상세 부분 -->
		        <div class="container_board">
		       		<!-- 제목 -->	
		       		<h2>팀 게시판</h2>
		       		<!-- 작성자 및 정보 -->
		       		<div class="board_info">
		       			<img id="profile_pic" src="${pageContext.request.contextPath}/images/face.png"height="25" width="25"> 
		       			<span>작성자(닉네임)</span>
		       			<span>2024.5.14</span>
		       		</div>
		       		
		       		<hr style="color:#d4d4d4">
		       		
		       		<!-- 게시글 본문 -->
		       		<div class="board_content">
		       			<!-- 이미지 --> 
		       			<div class="board_file">
		       				<img src="${pageContext.request.contextPath}/images/cje/boardHitIcon.png">
		       			</div>
		       			<!-- 내용 -->
		       			<p>
		       				오늘 저녁 뭐 먹을까요 추천 좀요!
		       			</p>
		       		</div>
		   		</div>
		   		<div class="list-actions">    
		   			<span>마지막 수정일 : 2024.05.26</span>
				    <button class="btn btn-primary list-action" onclick="location.href='.do'">수정</button>
				    <button class="btn btn-secondary list-action" onclick="location.href='${pageContext.request.contextPath}/team/teamBoard.do'">목록</button>
				</div>
		   		 
		   		 <!-- 댓글 목록 -->
		   		 <div class="container_reList">
		   		 	<div class="reList">
			   		 	<div class="re_writer">
			   		 		<img id="profile_pic" src="${pageContext.request.contextPath}/images/face.png"height="25" width="25"> 
			       			<span> 닉네임</span>
			   		 	</div>
			   		 	<div class="re_content">
			   		 		<p>전 갈비찜 먹을라고요</p>
			   		 	</div>
			   		 	<div class="delete_button">
			   		 		<button class="btn btn-default" onclick="location.href='.do'">삭제</button>
			   		 	</div>
		   		 	</div>
		   		 	<div class="reList">
			   		 	<div class="re_writer">
			   		 		<img id="profile_pic" src="${pageContext.request.contextPath}/images/face.png"height="25" width="25"> 
			       			<span> 닉네임</span>
			   		 	</div>
			   		 	<div class="re_content">
			   		 		<p>김치찜이 최곤디</p>
			   		 	</div>
			   		 	<div class="delete_button">
			   		 		<button class="btn btn-default" onclick="location.href='.do'">삭제</button>
			   		 	</div>
			   		 </div>
		   		 </div>
		   		 
		        <!-- 댓글 시작 -->
		        <div class="container_re">
			        <div id="reply_div">
			        	<form id="re_form">
			        		<input type="hidden" name="cb_num" value="${cboard.cb_num}" id="cb_num">
			        		<div class="form-group">
				                <textarea rows="3" cols="100" name="cb_content" id="cb_content" class="form-control"></textarea>
				                <input type="submit" value="등록" class="btn btn-primary" onclick="location.href='.do'">
				            </div>
			        		
			        	</form>
			        </div>
		        </div>
		   		 </div>
		   	</div>
	      
</body>
</html>
