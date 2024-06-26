<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<link href="${pageContext.request.contextPath}/images/로고1.png"
	rel="shortcut icon" type="image/x-icon">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/manageMembers.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/manageMembers.js"></script>
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
						<h3 class="admin-TitleText">회원관리</h3>
						<ul class="search">
							<li><select name="keyfield">
									<option value="1"
										<c:if test="${param.keyfield == 1}">selected</c:if>>ID</option>
									<option value="2"
										<c:if test="${param.keyfield == 2}">selected</c:if>>닉네임</option>
									<option value="3"
										<c:if test="${param.keyfield == 3}">selected</c:if>>회원번호</option>
							</select></li>
							<li><input type="search" size="30" name="keyword"
								id="keyword" value="${param.keyword}"></li>
							<li><input type="submit" value="검색"></li>
						</ul>
						<c:if test="${count == 0}">
							<div>표시할 회원이 없습니다.</div>
						</c:if>
						<c:if test="${count > 0}">
							<table id="search-result">
								<tr>
									<th>회원번호</th>
									<th>ID</th>
									<th>닉네임</th>
									<th>회원등급</th>
									<th>이메일</th>
									<th>가입일</th>
									<th>정지</th>
									<th>탈퇴</th>
									<th>등급 변경</th>
								</tr>
								<c:forEach var="member" items="${membersList}">
									<tr>
										<td>${member.mem_num}</td>
										<td>${member.mem_id}</td>
										<td>${member.mem_nickname}</td>
										<td><c:if test="${member.mem_auth == 0}">탈퇴</c:if> <c:if
												test="${member.mem_auth == 1}">정지</c:if> <c:if
												test="${member.mem_auth == 2}">일반</c:if> <c:if
												test="${member.mem_auth == 9}">관리자</c:if></td>
										<td>${member.mem_email}</td>
										<td>${member.mem_reg_date}</td>
										<td><c:if test="${member.mem_auth != 1}">
												<button
													class="lockMemberBtn <c:if test="${member.mem_auth == 0}">disabled</c:if>"
													data-memid="${member.mem_id}"
													data-memnum="${member.mem_num}" data-locked="0"
													<c:if test="${member.mem_auth == 0 || member.mem_auth == 9}">disabled="disabled"</c:if>>
													정지</button>
											</c:if> <c:if test="${member.mem_auth == 1}">
												<button class="lockMemberBtn" data-memid="${member.mem_id}"
													data-memnum="${member.mem_num}" data-locked="1">정지
													취소</button>
											</c:if></td>
										<td>
											<button
												class="deleteMemberBtn <c:if
												test="${member.mem_auth == 0 || member_mem.auth == 9}">disabled</c:if>"
												data-memid="${member.mem_id}"
												data-memnum="${member.mem_num}"
												<c:if
												test="${member.mem_auth == 0 || member_mem.auth == 9}">disabled</c:if>>탈퇴</button>
										</td> 
										<td><select name="changeAuthToAdmin"
											class="auth-select <c:if
												test="${member.mem_num == mem_num || member.mem_auth == 0 || member.mem_auth == 1}">disabled</c:if>"
											<c:if
												test="${member.mem_num == mem_num || member.mem_auth == 0 || member.mem_auth == 1}">disabled</c:if>>
												<option value="2"
													<c:if test="${member.mem_auth != 9}">selected</c:if>>일반</option>
												<option value="9"
													<c:if test="${member.mem_auth == 9}">selected</c:if>>관리자</option>
										</select>
											<button class="changeAuthBtn <c:if
												test="${member.mem_num == mem_num || member.mem_auth == 0 || member.mem_auth == 1}">disabled</c:if>" data-memnum="${member.mem_num}"
												data-memauth="${member.mem_auth}" <c:if
												test="${member.mem_num == mem_num || member.mem_auth == 0 || member.mem_auth == 1}">disabled</c:if>>등급 변경</button></td>
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
