<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 코메 모집 신청자</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myTeam.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>

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
<div class="float-C">
<div class="align-center"><!-- myPage-TItleText만 가운데 정렬됨 -->
	<h3 class="mYPage-TitleText">나의 코메 모집 신청자</h3>
</div>


				<c:forEach var="ra" items="${rapplyList}">
					<div class="myPage-line-box-moshin" data-pass="${ra.ra_pass}">
						<div class="profile_mypage">

							<!-- 이미지 수정 -->
							<c:if test="${empty ra.mem_photo}">
								<img src="${pageContext.request.contextPath}/images/face.png"
									class="profile_pic_mypage" height="40" width="40">
							</c:if>
							<c:if test="${!empty ra.mem_photo}">
								<img
									src="${pageContext.request.contextPath}/upload/${ra.mem_photo}"
									class="profile_pic_mypage" height="40" width="40">
							</c:if>

							<%-- class="profile_image"다 이 명칭 사용,근데 여기는 사진이 네모여서 class명 바꿈 --%>
							<div class="nickname_mypage">${ra.mem_nickname}</div>

							<div class="mo_check">
								<form class="passForm">
									<input type="hidden" name="ra_num" value="${ra.ra_num}">
									<input type="hidden" name="rb_num" value="${ra.rb_num}">
									<input type="hidden" name="mem_num" value="${ra.mem_num}">
									<input type="submit" class="yes" value="합격">
								</form>
								<form class="unPassForm">
									<input type="hidden" name="ra_num" value="${ra.ra_num}">
									<input type="hidden" name="rb_num" value="${ra.rb_num}">
									<input type="hidden" name="mem_num" value="${ra.mem_num}">
									<input type="submit" class="no" value="불합격">
								</form>
							</div>

							<br>
							<div class="mypage_clob">
								<div class="shin-text">신청동기</div>

								<div class="content">${ra.ra_content}</div>
							</div>
						</div>
					</div>
				</c:forEach>

			</div>
<!-- 메인 정보 수정 끝 -->
</div><!-- flex_container끝 -->
</div><!-- page-container끝 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/moshin.js"></script>
</body>
</html>
