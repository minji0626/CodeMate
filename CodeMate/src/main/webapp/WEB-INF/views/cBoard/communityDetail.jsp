<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세</title>
    
    <link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
	
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cje.boardDetail.css" type="text/css">
    
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
		<div class="page-container">
			<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	        <div class="container_write">
	        <form action="" id="write_form" method="post" enctype="multipart/form-data">
	        	<div class="form-group">
					<input type="radio" name="cb_type" value="1" id="cb_type1">
					<img src="${pageContext.request.contextPath}/images/cje/freeBoardIcon.png" width="15"> 자유 게시판
					<input type="radio" name="cb_type" value="2" id="cb_type2"> 
					<img src="${pageContext.request.contextPath}/images/cje/codingBoardIcon.png" width="15"> 개발 게시판
				</div>
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
	        <!-- 댓글 시작 -->
	        <div id="reply_div">
	        	<span class="re_title">댓글 달기</span>
	        	<form id="re_form">
	        		<input type="hidden" name="board_num" value="${board.board_num}" id="board_num">
	        		<textarea rows="3" cols="50" name="re_content" <c:if test="${empty user_num}">disabled="disabled"</c:if>id="re_content" class="rep-content"><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>
	        		<c:if test="${!empty user_num}">
	        			<div id="re_first">
	        				<span class="letter-count">300/300</span>
	        			</div>
	        			<div id="re_second" class="align-right">
	        				<input type="submit" value="전송">
	        			</div>
	        		</c:if>
	        	</form>
	        </div>
	        <!-- 댓글 목록 출력 시작 -->
	        <div id="output">
	        
	        </div>
	        <div class="paging-button" style="display:none;">
	        	<input type="button" value="다음글 보기">
	        </div>
	        <div id="loading" style="display:none;">
	        	<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
	        </div>
	        <!-- 댓글 목록 출력 끝 -->
	        <!-- 댓글 끝 -->
    </div>
</body>
</html>
