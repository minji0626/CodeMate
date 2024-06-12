<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>활성화 팀 관리</title>
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
						<h3 class="admin-TitleText">활성화 팀 관리</h3>
						
						<c:if test="${count == 0}">
							<div>표시할 팀이 없습니다.</div>
						</c:if>
						<c:if test="${count > 0}">
							<table id="search-result">
								<tr>
									<th>팀 번호</th>
									<th>프로젝트 이름</th>
									<th>프로젝트 시작일</th>
									<th>프로젝트 기간</th>
									<th>상태</th>
									<th>팀원</th>
								</tr>
								<c:forEach var="member" items="${rboardList}">
									<tr>
										<td>${member.rb_num}</td>
										<td>${member.rb_pj_title}</td>
										<td>${member.rb_start}</td>
										<td>${member.rb_period}개월</td>
										<td>
											<c:if test="${member.team_status==1}">
												진행 중
											</c:if>
											<c:if test="${member.team_status==3}">
												종료
											</c:if>
										</td>
										<td><a href="checkTeamMember.do?team_num=${member.rb_num}" class="button checkTeamMember">팀원 확인</a>
										<%-- <td><a href="stopTeam.do?team_num=${member.rb_num}" class="button deleteTeam"> 강제 정지 </a></td> --%>
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
