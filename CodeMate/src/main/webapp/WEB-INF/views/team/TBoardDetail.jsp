<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세</title>  
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/team_board_detail.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
</head>
<body> 
	<div class="page-container">
   			 <jsp:include page="/WEB-INF/views/common/header.jsp"/>
   			 <jsp:include page="/WEB-INF/views/team/teamNav.jsp"/>
				<div class="board_container">
		        <!-- 게시글 상세 부분 -->
		        <div class="container_board">
		       		<!-- 제목 -->	
		       		<h2>${tboard.tb_title }</h2>
		       		<!-- 작성자 및 정보 -->
		       		<div class="board_info">
		       			<c:if test="${!empty tboard.mem_photo }">
        				<img class="profile_pic" height="25" width="25" src="${pageContext.request.contextPath}/upload/${tboard.mem_photo}" alt="프로필 이미지">
        				</c:if>
        				<c:if test="${empty tboard.mem_photo }">
        				<img class="profile_pic" height="25" width="25" src="${pageContext.request.contextPath}/images/face.png" alt="기본 이미지">
        				</c:if>
		       			<span>${tboard.mem_id}</span>
		       			<span>${tboard.tb_reg_date }</span>
		       		</div>
		       		
		       		<hr style="color:#d4d4d4">
		       		
		       		<!-- 게시글 본문 -->
		       		<div class="board_content">
		       			<!-- 내용 -->
		       			<c:if test="${!empty tboard.tb_file }">
    						<div class="align-center">
        						<img src="${pageContext.request.contextPath}/upload/${tboard.tb_file}">
        					</div>
        				</c:if>
		       			<p>
		       				${tboard.tb_content }
		       			</p>
		       		</div>
		   		</div>
		   		<div class="list-actions">    
		   			<span>
		   				<c:if test="${!empty tboard.tb_modify_date }">
        					최근 수정일 : ${tboard.tb_modify_date }
        				</c:if>
					</span>
				    
				    <c:if test="${mem_num == tboard.mem_num }">
        			<button id="modify_btn" class="btn btn-primary list-action" onclick="location.href='${pageContext.request.contextPath}/team/teamUpdateForm.do?tb_num=${tboard.tb_num}'">수정</button>
				    <button id="delete_btn" class="btn btn-primary list-action">삭제</button>
						<script type="text/javascript">
							const delete_btn = document.getElementById('delete_btn');
							delete_btn.onclick = function(){
								let choice = confirm('해당 글을 삭제하시겠습니까?');
								if(choice){
									location.replace('delete.do?tb_num=${tboard.tb_num}');
								}
							}
						</script>
        			</c:if>
				    <button class="btn btn-secondary list-action" onclick="location.href='${pageContext.request.contextPath}/team/teamBoardList.do?team_num=${team_num }'">목록</button>
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
			        		<input type="hidden" name="cb_num" value="${tboard.tb_num}" id="cb_num">
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
