<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 1:1 문의 상세</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myTeam.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myPageConsultDelete.js"></script>

</head>
<body>
<!-- 헤더 링크-->
<div class="page-container">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div id="flex_container">
<!-- 사이드바 -->
<jsp:include page="/WEB-INF/views/member/myPage_nav.jsp"/>
<!-- 사이드바 끝 -->
<!-- 메인 정보 수정 -->
<div class="float-B">
				<div class="myConsult">
					<ul>
						<li>
							<h3>
								<span> <c:if test="${consult.cs_confirmed == 0}">[답변처리중]</c:if>
									<c:if test="${consult.cs_confirmed == 1}">[답변완료]</c:if>
								</span>${consult.cs_title}</h3>
						</li>
						<%-- <li>
							<h3>
								<span> <c:if test="${consult.cs_category == 0}">[일반문의]</c:if>
									<c:if test="${consult.cs_category == 1}">[신고]</c:if>
								</span>${consult.cs_title}</h3>
						</li> --%>
						<li>이메일 | ${consult.cs_reply_email}</li>
						<li>
						등록일 | ${consult.cs_reg_date}
						</li>
						<li>
						<span> <c:if test="${empty consult.cs_confirmed_date}">답변처리중</c:if>
									<c:if test="${!empty consult.cs_confirmed_date}">답변완료 | ${consult.cs_confirmed_date}</c:if>
								</span>
						</li>
						<li class="consult-write">
							<div class="c_bold">제목</div>
							<p>${consult.cs_title}</p>
						</li>
						<li>
							<div class="c_bold">문의 내용</div>
							<p>${consult.cs_content}</p>
						</li>
						
						
					</ul>
				</div>
			</div>
<!-- 메인 정보 수정 끝 -->
</div><!-- flex_container끝 -->
</div><!-- page-container끝 -->

</body>
</html>
