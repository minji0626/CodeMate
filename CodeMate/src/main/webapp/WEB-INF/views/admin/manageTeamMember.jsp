<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀원 관리</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageMembers.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
 
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
						<h3 class="admin-TitleText">팀원 관리</h3>
						
						<c:if test="${count == 0}">
							<div>표시할 회원이 없습니다.</div>
						</c:if>
						<c:if test="${count > 0}">
							<table id="search-result">
								<tr>
									<th>팀 번호</th>
									<th>이름</th>
									<th>등급</th>
									<th>팀장 위임</th>
									<th>팀원 삭제</th>
								</tr>
								<c:forEach var="tmember" items="${list}">
									<tr>
										<td>${team_num}</td>
										<td>${tmember.mem_nickname}</td>
										<c:if test="${tmember.tm_auth==4}">
											<td>팀장</td>
										</c:if>
										<c:if test="${tmember.tm_auth==3}">
											<td>팀원</td>
										</c:if>
										
										<td><a class="mem_auth_btn button changeLeader" style="cursor: pointer" data-current-leader="${leader_mem_num}" data-team-num="${team_num}" data-mem-num="${tmember.mem_num}">팀장 위임</a>
										<c:if test="${tmember.team_status==1}">
											<td><a class="mem_delete_btn button deleteTeamMember" style="cursor: pointer" data-current-leader="${leader_mem_num}" data-team-num="${team_num}" data-mem-num="${tmember.mem_num}">팀원 삭제</a></td>
										</c:if>
										<c:if test="${tmember.team_status==3}">
											<td><a class="button checkTeamMember" style="cursor: pointer" data-current-leader="${leader_mem_num}" data-team-num="${team_num}" data-mem-num="${tmember.mem_num}"> 삭제 불가 </a></td>
										</c:if>	
										
										
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/manageTeamMember.js"></script>
</body>
</html>
