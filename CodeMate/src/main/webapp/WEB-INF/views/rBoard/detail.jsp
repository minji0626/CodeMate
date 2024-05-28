<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>코메 모집글 상세페이지</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/rboardDetail.css" type="text/css">
</head>

<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/rboardList.js"></script>

<body>
	<div class="page-container">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="page-main">
			<div class="content-main">
				<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
					viewBox="0 0 24 24" fill="none" stroke="currentColor"
					stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
					class="feather feather-arrow-left">
                    <line x1="19" y1="12" x2="5" y2="12"></line>
                    <polyline points="12 19 5 12 12 5"></polyline>
                </svg>
				<div class="content-header">
					<h2>🍰[프론트 개발자] 베이커리 프로젝트 프론트 개발자 한 분 모집합니다🍰</h2>
					<div>
						<img src="../images/face.png" class="profile-photo"> <span>닉네임</span>
						<span>2024.05.22(작성일자)</span> <input type="button" value="신청하기"
							id="apply-btn">
					</div>
				</div>
				<div class="content">
					<h3>모집 정보</h3>
					<ul>
						<li><span>모집 구분</span> <span>프로젝트</span></li>
						<li><span>시작 예정</span> <span>2024.06.01</span></li>
						<li><span>진행 방식</span> <span>온라인/오프라인</span></li>
						<li><span>예상 기간</span> <span>3개월</span></li>
						<li><span>모집 인원</span> <span>3명</span></li>
						<li><span>요구 스킬</span> <span> <img
								src="../images/c++_logo.png" class="skill-logo"> <img
								src="../images/react_logo.png" class="skill-logo">
						</span></li>
						<li><span>모집 필드</span> <span>프론트엔드</span> <span>백엔드</span> <span>디자이너</span>
						</li>
					</ul>
				</div>
				<div class="content">
					<h3>프로젝트 소개</h3>
					<div>
						이곳저곳 널리 퍼져있는 건강 디저트를 한 눈에 편리하게 볼 수 있는 건강 베이커리 플랫폼 프로젝트 입니다.<br>
						함께하실 프론트 개발자 모집합니다 !☺<br>
						<br> 🍰 건강 디저트 e-커머스 🍰<br>
						<br> 백엔드도 모집합니다...<br> 암튼 모집~ 지금까지 두명 모였고요 세명 더 받습니다<br>
						<br> ...<br>
					</div>
				</div>
			</div>
			<div class="comments">
				<h4>
					댓글 <span>2</span>
				</h4>
				<form id="comment_form" action="">
					<img src="../images/face.png" class="profile-photo">
					<textarea name="rc_content" id="rc_content"
						placeholder="댓글을 입력하세요." cols="100" rows="6"></textarea>
					<div class="align-right">
						<input type="submit" value="댓글 등록">
					</div>
				</form>
				<hr size="1" width="100%">
				<div class="comment-item">
					<div class="comment-item-header">
						<img src="../images/face.png" class="profile-photo"> <span>닉네임</span>
						<span>30분전</span>
					</div>
					<p>와!! 이 프로젝트... 궁금한게 있는데 오픈카톡 좀 주실 수 있나요?</p>
				</div>
				<div class="comment-item">
					<div class="comment-item-header">
						<img src="../images/face.png" class="profile-photo"> <span>아이스아메리카노</span>
						<span>3일전</span>
					</div>
					<p>pm은 따로 안구하시나요??</p>
				</div>
			</div>
		</div>
	</div>
</body>

</html>