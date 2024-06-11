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
<style>

/* 메뉴 스타일 */
.menu {
	margin-bottom: 20px;
}

.menu ul {
	list-style-type: none;
	padding: 0;
	margin: 0;
}

.menu ul li {
	display: inline;
	margin-right: 10px;
}

.menu ul li a {
	text-decoration: none;
	color: #333;
	padding: 5px 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.menu ul li a.active {
	background-color: #ccc;
}

/* 테이블 스타일 */
.message-table {
	width: 100%;
	border-collapse: collapse;
	text-align: center;
}

.message-table th, .message-table td {
	border: 1px solid #ccc;
	padding: 8px;
}

.message-table th {
	background-color: #f2f2f2;
}

/* 버튼 스타일 */
.message-buttons {
	margin-top: 20px;
}

.message-buttons button {
	margin-right: 10px;
	padding: 8px 16px;
	font-size: 14px;
	cursor: pointer;
}

/* 버튼 스타일 수정 */
.action-buttons {
	position: relative;
	margin-top: 10px;
	text-align: right;
}

.btn {
	background-color: #78AFE2;
	border: none;
	color: white;
	padding: 8px 15px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	margin-left: 10px;
	cursor: pointer;
	border-radius: 5px;
	transition: background-color 0.3s;
}

* {
	margin: 0;
	padding: 0;
}

a {
	text-decoration: none;
}

.wrap {
	padding: 10px;
}

.pop_wrap {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background: rgba(0, 0, 0, .5);
	font-size: 0;
	text-align: center;
}

.pop_wrap:after {
	display: inline-block;
	height: 100%;
	vertical-align: middle;
	content: '';
}

.pop_wrap .pop_inner {
	display: inline-block;
	padding: 20px 30px;
	background: #fff;
	width: 400px;
	vertical-align: middle;
	font-size: 15px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	text-align: left;
	font-family: 'Noto Sans KR', sans-serif;
}

.pop_inner .header {
	font-size: 18px;
	font-weight: bold;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
	padding-bottom: 10px;
}

.pop_inner .content {
	margin-bottom: 20px;
}

.pop_inner .footer {
	text-align: right;
	border-top: 1px solid #ccc;
	padding-top: 10px;
}

.pop_inner .footer button {
	margin-left: 10px;
}

.pop_inner .message-details {
	margin-bottom: 20px;
}

.pop_inner .message-details p {
	margin: 5px 0;
}
</style>
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
										<td><a href="#" class="btn_open" data-type="<c:out value="${consult.cs_category}"/>" 
												    data-cs-title="${consult.cs_title}" data-cs-reg-date="${consult.cs_reg_date}" data-cs-email="${consult.cs_reply_email}" 
    												data-cs-content="${consult.cs_content}" data-cs-confirmed="${consult.cs_confirmed}">${consult.cs_title}</a></td>
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
	<jsp:include page="consultDetail.jsp"/>
</body>
</html>
