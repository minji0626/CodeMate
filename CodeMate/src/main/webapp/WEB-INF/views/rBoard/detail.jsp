<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>코메 모집글 상세페이지</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/rboardDetail.css" type="text/css">
</head>

<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/rboardDetail.js"></script>

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
					<h2>${rboard.rb_title}</h2>
					<div>
						<c:if test="${rboard.mem_photo != null}">
						<img src="${pageContext.request.contextPath}/upload/${rboard.mem_photo}" class="profile-photo">						
						</c:if>
						<c:if test="${rboard.mem_photo == null}">
						<img src="${pageContext.request.contextPath}/images/face.png" class="profile-photo">
						</c:if>
						<span>${rboard.mem_nickname}</span>
						<span>${rboard.rb_reg_date}(작성일자)</span> 
						<c:if test="${mem_num == rboard.mem_num}">
						<input type="button" value="수정하기" id="modify_btn" class="btn" onclick='location.href="modifyForm.do"'>
						</c:if>
						<c:if test="${mem_num != rboard.mem_num}">
						<input type="button" value="신청하기" id="btn-modal" class="btn">
						<jsp:include page="/WEB-INF/views/rBoard/applyModal.jsp" />
						</c:if>
					</div>
				</div>
				<div class="content">
					<h3>모집 정보</h3>
					<ul>
						<li><span>모집 구분</span> <span>
							<c:if test="${rboard.rb_category == 0}">
							스터디
							</c:if>
							<c:if test="${rboard.rb_category == 1}">
							프로젝트
							</c:if>
							</span></li>
						<li><span>시작 예정</span> <span>${rboard.rb_start}</span></li>
						<li><span>진행 방식</span> <span>
							<c:if test="${rboard.rb_meet == 0}">
							온라인
							</c:if>
							<c:if test="${rboard.rb_meet == 1}">
							오프라인
							</c:if>
							<c:if test="${rboard.rb_meet == 2}">
							온라인/오프라인	
							</c:if>
							</span></li>
						<li><span>예상 기간</span> 
							<c:if test="${rboard.rb_period == 0}">
							<span>1개월 미만</span>
							</c:if>
							<c:if test="${rboard.rb_period == 6}">
							<span>6개월 이상</span>
							</c:if>
							<c:if test="${rboard.rb_period != 0 && rboard.rb_period != 6}">
							<span>${rboard.rb_period}개월</span>
							</c:if>
						</li>
						<li><span>모집 인원</span> <span>${rboard.rb_teamsize}명</span></li>
						<li><span>요구 스킬</span> 
							<span>
			                    <c:forEach var="i" begin="0" end="${fn:length(rboard.hs_photo_arr) - 1}">
			    				<img src="${pageContext.request.contextPath}/images/hard_skill_logo/${rboard.hs_photo_arr[i]}" title="${rboard.hs_name_arr[i]}" class="skill-logo">
								</c:forEach>
							</span>
						</li>
						<li><span>모집 필드</span> 
							<c:forEach var="field" items="${rboard.f_name_arr}">
								<span>${field}</span>
							</c:forEach>
						</li>
					</ul>
				</div>
				<div class="content">
					<h3>프로젝트 소개</h3>
					<div>
					${rboard.rb_content}
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