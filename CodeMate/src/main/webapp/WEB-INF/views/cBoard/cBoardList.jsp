<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

    <link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
	
	
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cje.board.css" type="text/css">
    
	
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
	
<title>커뮤니티</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<style type="text/css">
	.page-container {
    width: 70%;
    min-width: 900px;
    margin: 0 auto;
}
</style>

</head>
<body>

	<div class="page-container">
		<div class="page-main">
			<jsp:include page="/WEB-INF/views/common/header.jsp"/>			
		</div>	
			<div class="table-container">
				<div class="button-container">
		    		<button class="write-button" onclick="location.href='writeCommunityForm.do'">글쓰기</button>
				</div>
				<!-- 게시판 선택 메뉴 -->
				<div>		 
				<ul class="menu">
					<li>
						<img src="${pageContext.request.contextPath}/images/cje/freeBoardIcon.png" width="30px" class="board-icon">
						<span><a href="${pageContext.request.contextPath}/cboard/community.do?cb_type=0">
						<c:if test="${cb_type==0}"><b>자유 게시판</b></c:if> 
						<c:if test="${cb_type==1}">자유 게시판</c:if> 
						</a></span>
					</li>
					<li>
						<img src="${pageContext.request.contextPath}/images/cje/codingBoardIcon.png" width="30px" class="board-icon"> 
						<span><a href="${pageContext.request.contextPath}/cboard/community.do?cb_type=1"> 
						<c:if test="${cb_type==0}">개발 게시판</c:if> 
						<c:if test="${cb_type==1}"><b>개발 게시판</b></c:if>
						</a></span>
					</li>
				</ul>
				</div>
			 <!-- 검색 폼 -->
		        <form id="search_form" action="community.do" method="get">
		            <div class="search-container">
		                <select name="keyfield" class="search-select">
		                    <option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
		                    <option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>작성자</option>
		                    <option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
		                </select>
		                <input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}" class="search-input">
		                <input type="submit" value="검색" class="search-submit">
		            </div>
		        </form>
		        
		        <!-- 테이블 -->
		        <c:if test="${count == 0 }">
        			<p>표시할 글 정보가 없습니다.</p>
        		</c:if>
        		
        		<c:if test="${count > 0 }">
	        		 <table>
		                <thead>
		                    <tr>
		                        <th>번호</th>
		                        <th>제목</th>
		                        <th>작성자</th>
		                        <th>작성일</th>
		                        <th>조회수</th>
		                    </tr>
		                </thead>
	                	<tbody>
							<c:forEach var="cboard" items="${list}">
		                        <tr>
		                            <td>${cboard.cb_num}</td>
		                            <td><a href="${pageContext.request.contextPath}/cboard/communityDetail.do?cb_num=${cboard.cb_num}">${cboard.cb_title}</a></td>
		                            <c:if test="${cboard.cb_type==0}">
		                            	<td>코메</td>
		                            </c:if>
		                            <c:if test="${cboard.cb_type==1}">
		                            	<td><a href="${pageContext.request.contextPath}/mateProfile/mateProfile.do?mem_num=${cboard.mem_num}">${cboard.mem_nickname}</a></td>
		                            </c:if>
		                            
		                            <td>${cboard.cb_reg_date}</td>
		                            <td>${cboard.cb_hit}</td>
		                        </tr>
		                    </c:forEach>
		                </tbody>
		            </table>
			  		<div class="align-center">${page}</div>
            	</c:if>
			</div>
		</div> 	
</body>
</html>
