<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    
	
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cje.boardDetail.css" type="text/css">
    <link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
    
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    
    <title>글상세</title>  
</head>
<body>
		<div class="page-container">
			<div class="page-main">
				<jsp:include page="/WEB-INF/views/common/header.jsp"/>
			</div>
				<div class="board_container">
		        <!-- 게시글 상세 부분 -->
		        <div class="container_board">
		        	<!-- 게시판 분류 -->
		       		<div class="board_category">
		       			<c:if test="${board.cb_type==0}">	
		       				<img src="${pageContext.request.contextPath}/images/cje/freeBoardIcon.png" width="15"> 자유 게시판
						</c:if>
						<c:if test="${board.cb_type==1}">	
		       				<img src="${pageContext.request.contextPath}/images/cje/codingBoardIcon.png" width="15"> 개발 게시판
						</c:if>	

		       		</div>
		       		<!-- 제목 -->	
		       		<h2>${board.cb_title}</h2>
		       		<!-- 작성자 및 정보 -->
		       		<div class="board_info">
						<c:if test="${!empty mem_photo}">
							<img id="profile_pic" src="${pageContext.request.contextPath}/upload/${mem_photo}" height="40" width="40">
						</c:if>
						<c:if test="${empty mem_photo}">
							<img id="profile_pic" src="${pageContext.request.contextPath}/images/face.png" height="40" width="40">
						</c:if>
		       			<span> ${board.mem_nickname}</span>
		       			<span>${board.cb_reg_date}</span>
		       			<img src="${pageContext.request.contextPath}/images/cje/boardHitIcon.png" width="15">
		       			<span>${board.cb_hit}</span>
		       		</div>
		       		
		       		<hr style="color:#d4d4d4">
		       		
		       		<!-- 게시글 본문 -->
		       		<div class="board_content">
		       			<!-- 이미지 --> 
		       			<c:if test="${!empty board.cb_file}">
    						<div class="board_file">
        						<img src="${pageContext.request.contextPath}/upload/${board.cb_file}" width="400">
        					</div>
        				</c:if>
		       			<!-- 내용 -->
		       			<p>
		       				${board.cb_content}
		       			</p>
		       		</div>
		       		<!-- 좋아요 -->
		       		<div class="board_like">
		       			<img src="${pageContext.request.contextPath}/images/cje/boardLikeIcon.png" height="25" width="25">
		       			<span>${board.cb_like}</span>
		       		</div>
		   		</div>
		   		<div class="list-actions">    
		   			<c:if test="${!empty board.cb_modify_date}">
        					최근 수정일 : ${board.cb_modify_date}
        			</c:if>
				    <button class="btn btn-primary list-action" onclick="location.href='modifyCommunityForm.do?cb_num=${board.cb_num}'">수정</button>
				    <button class="btn btn-secondary list-action" onclick="location.href='community.do?cb_type=${board.cb_type}'">목록</button>
				    
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
