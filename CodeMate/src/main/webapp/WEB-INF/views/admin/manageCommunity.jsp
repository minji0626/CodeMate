<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 관리</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageMembers.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manageDeleteCommunity.js"></script>
</head>
<body>
	<!-- 헤더 링크-->
	<div class="page-container">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div id="flex_container">
			<!-- 사이드바 -->
			<jsp:include page="/WEB-INF/views/admin/admin_nav.jsp" />
			<!-- 사이드바 끝 -->
			<!-- 메인 -->
			<div class="float-B">
				<div class="align-center">
					<form id="manage_members_form" name="manage_members_form">
						<h3 class="admin-TitleText">커뮤니티 관리</h3>
						<ul class="search">
							<li><select name="keyfield">
									<option value="1"
										<c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
									<option value="2"
										<c:if test="${param.keyfield == 2}">selected</c:if>>닉네임</option>
									<option value="3"
										<c:if test="${param.keyfield == 3}">selected</c:if>>게시판 종류</option>
							</select></li>
							<li><input type="search" size="30" name="keyword"
								id="keyword" value="${param.keyword}"></li>
							<li><input type="submit" value="검색"></li>
						</ul>
						<c:if test="${count == 0}">
							<div>표시할 글이 없습니다.</div>
						</c:if>
						<c:if test="${count > 0}">
							<table id="search-result">
								<tr>
									<th>글 번호</th>
									<th>글 제목</th>
									<th>작성자</th>
									<th>게시판 종류</th>
									<th>마지막 수정일</th>
									<th>글 삭제</th>
								</tr>
								<c:forEach var="community" items="${communityList}">
									<tr>
										<td>${community.cb_num}</td>
										<td><a href='${pageContext.request.contextPath}/cboard/communityDetail.do?cb_num=${community.cb_num}'>${community.cb_title}</a></td>
										<td>${community.mem_nickname}</td>
										<td>
											<c:if test="${community.cb_type == 0}">자유 게시판</c:if>
											<c:if test="${community.cb_type == 1}">개발 게시판</c:if>
										</td>
										<c:if test="${empty community.cb_modify_date}">
											<td>${community.cb_reg_date}</td>
										</c:if>
										<c:if test="${!empty community.cb_modify_date}">
											<td>${community.cb_modify_date}</td>
										</c:if>
										<td>
											<button class="deleteMemberBtn" data-cbnum="${community.cb_num}">삭제</button>
										</td>
									</tr>
								</c:forEach>
							</table>
							<div>${page}</div>
						</c:if>
					</form>
				</div>
			</div>
			<!-- 메인 끝 -->
		</div>
	</div>
</body>
</html>
