<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의 상세</title>
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
	src="${pageContext.request.contextPath}/js/consultDetail.js"></script>
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
				<div>
					<ul>
						<li>
							<h3>
								<span> <c:if test="${consult.cs_category == 0}">[일반문의]</c:if>
									<c:if test="${consult.cs_category == 1}">[신고]</c:if>
								</span>${consult.cs_title}</h3>
						</li>
						<li><c:if test="${consult.cs_confirmed == 1}">
								<span>(${consult.cs_confirmed_date} 처리됨)</span>
							</c:if></li>
						<li><span>문의 작성자 | </span> <span>${consult.mem_id} (회원번호: ${mem_num})</span></li>
						<li><span>회신 이메일 | </span> <span>${consult.cs_reply_email}</span></li>
						<li>
							<div>문의 내용</div>
							<p>${consult.cs_content}</p>
						</li>
						<li><c:if test="${consult.cs_confirmed == 0}">
								<button id="confirm_cs" data-confirm="1" data-csnum="${consult.cs_num}">처리하기</button>
							</c:if> <c:if test="${consult.cs_confirmed == 1}">
								<button id="confirm_cs" data-confirm="0" data-csnum="${consult.cs_num}">처리취소</button>
							</c:if>
							<button onclick="location.href='consultsList.do'">목록으로</button>
							</li>
					</ul>
				</div>
			</div>
			<!-- 메인 끝 -->
		</div>
	</div>
</body>
</html>
