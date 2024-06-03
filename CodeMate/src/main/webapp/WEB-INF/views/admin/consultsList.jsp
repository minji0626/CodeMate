<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의 관리</title>
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
	src="${pageContext.request.contextPath}/js/consultsList.js"></script>
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
						<h3 class="admin-TitleText">1:1 문의</h3>
						<ul class="search">
							<li><select name="keyfield">
									<option value="1"
										<c:if test="${param.keyfield == 1}">selected</c:if>>회원ID</option>
									<option value="2"
										<c:if test="${param.keyfield == 2}">selected</c:if>>회원번호</option>
							</select></li>
							<li><input type="search" size="16" name="keyword"
								id="keyword" <c:if test="${param.keyfield != 3}">value="${param.keyword}"</c:if>></li>
							<li><input type="submit" value="검색"></li>
							<li><input type="button" id="getUnconfirmed"
								value="미처리 문의만 보기" data-keyfield="3" data-keyword="0"></li>
							<li><input type="button" id="getAll"
								value="전체"></li>
						</ul>
						<c:if test="${count == 0}">
							<div>표시할 문의가 없습니다.</div>
						</c:if>
						<c:if test="${count > 0}">
							<table id="search-result">
								<tr>
									<th></th>
									<th>문의번호</th>
									<th>종류</th>
									<th>회원ID</th>
									<th>문의 제목</th>
									<th>문의등록일</th>
									<th>처리유무</th>
								</tr>
								<c:forEach var="consult" items="${consultsList}">
									<tr>
										<td><input type="checkbox" class="consultCheck"
											name="consultCheck" data-csnum="${consult.cs_num}"
											value="${consult.cs_num}"></td>
										<td>${consult.cs_num}</td>
										<td><c:if test="${consult.cs_category == 0}">일반문의</c:if>
											<c:if test="${consult.cs_category == 1}">신고</c:if></td>
										<td>${consult.mem_id}</td>
										<td><a href="consultDetail.do?${consult.cs_num}">${consult.cs_title}</a></td>
										<td>${consult.cs_reg_date}</td>
										<td><c:if test="${consult.cs_confirmed == 0}">처리 안 됨</c:if>
											<c:if test="${consult.cs_confirmed == 1}">처리됨</c:if></td>
									</tr>
								</c:forEach>
							</table>
							<button id="confirm_cs" data-confirm="1">처리하기</button>
							<button id="unconfirm_cs" data-confirm="0">처리 취소</button>

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
