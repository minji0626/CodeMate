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
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
		<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/kbr.css" type="text/css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/rboardDetail.css" type="text/css">
</head>

<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/rboardDetail.js"></script>
<script src="${pageContext.request.contextPath}/js/rboardComment.js"></script>

<body id="modal_background">
	<div class="page-container">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="page-main">
			
				<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
					viewBox="0 0 24 24" fill="none" stroke="currentColor"
					stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
					class="feather feather-arrow-left">
                    <line x1="19" y1="12" x2="5" y2="12"></line>
                    <polyline points="12 19 5 12 12 5"></polyline>
                </svg>
                <div class="content-main">
				<div class="content-header">
					<h2>${rboard.rb_title} 
					<span id="d_day">
						<c:if test="${rboard.daysLeft > 0 && rboard.team_status != 1 && rboard.team_status != 3}">D-${rboard.daysLeft}</c:if>
						<c:if test="${rboard.daysLeft == 0 && rboard.team_status != 1&& rboard.team_status != 3}">오늘 마감</c:if>
						<c:if test="${rboard.daysLeft < 0 || rboard.team_status == 1 || rboard.team_status == 3}">모집 종료</c:if>
					</span>
					<img id="bookmark_img" data-rbnum="${rboard.rb_num}" src="" width="36px">
					</h2>
						<span>${rboard.rb_reg_date}(작성일자)</span> 
						<span><img class="hit-image" src="${pageContext.request.contextPath}/images/cje/boardHitIcon.png"> <span id="rb_hit">${rboard.rb_hit}</span></span>					
					<div>
						<div class="flex-container">
								<div id="profile_div">
								<c:if test="${rboard.mem_photo != null}">
								<img src="${pageContext.request.contextPath}/upload/${rboard.mem_photo}" class="profile-photo">						
								</c:if>
								<c:if test="${rboard.mem_photo == null}">
								<img src="${pageContext.request.contextPath}/images/face.png" class="profile-photo">
								</c:if>
								<span><a href="${pageContext.request.contextPath}/mateProfile/mateProfile.do?mem_num=${rboard.mem_num}">${rboard.mem_nickname}</a></span>
							</div>
							<c:if test="${mem_num == rboard.mem_num}">
							<div id="header_btn_div">
								<c:if test="${rboard.daysLeft >= 0 && rboard.team_status != 1 && rboard.team_status != 3}">
								<input type="button" value="수정하기" id="modify_btn" class="btn-basic btn" onclick='location.href="modifyForm.do?rb_num=${rboard.rb_num}"'>
								<input type="button" value="삭제하기" id="delete_btn" class="btn-cancel btn" onclick='deleteRboard(${rboard.rb_num})'>
								</c:if>
							</div>
							<script>
							//글삭제
							function deleteRboard(rb_num) {
								var check = confirm("정말 삭제하시겠습니까?", "list.do");
								
								if (check) {
									location.href="deleteRboard.do?rb_num=" + rb_num;
								} else {
								}
							}
							</script>
							</c:if>
							<c:if test="${mem_num != rboard.mem_num}">
							<c:if test="${rboard.daysLeft >= 0 && rboard.team_status != 1 && rboard.team_status != 3}">
							<input type="button" value="신청하기" id="btn-modal" class="btn-basic btn" 
							data-memnum="${mem_num}" data-rbmemnum="${rboard.mem_num}" <c:if test="${alreadyApplied}">data-alreadyapplied="1"</c:if>>
							<jsp:include page="/WEB-INF/views/rBoard/applyModal.jsp" />
							</c:if>
							</c:if>
						</div>
					</div>
				</div>
				<div class="content">
					<h3>모집 정보</h3>
					<ul id="content_info">
						<li><span class="info-title">모집 구분</span> <span>
							<c:if test="${rboard.rb_category == 0}">
							스터디
							</c:if>
							<c:if test="${rboard.rb_category == 1}">
							프로젝트
							</c:if>
							</span></li>
						<li><span class="info-title">시작 예정</span> <span>${rboard.rb_start}</span></li>
						<li><span class="info-title">진행 방식</span> <span>
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
						<li><span class="info-title">예상 기간</span> 
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
						<li><span class="info-title">모집 인원</span> <span>${rboard.rb_teamsize}명</span></li>
						<li id="hskill_li"><span class="info-title">요구 스킬</span> 
							<span>
			                    <c:forEach var="i" begin="0" end="${fn:length(rboard.hs_photo_arr) - 1}">
			    				<img src="${pageContext.request.contextPath}/images/hard_skill_logo/${rboard.hs_photo_arr[i]}" title="${rboard.hs_name_arr[i]}" class="skill-logo">
								</c:forEach>
							</span>
						</li>
						<li><span class="info-title">모집 필드</span> 
							<c:forEach var="field" items="${rboard.f_name_arr}">
								<span class="mofield">${field}</span>
							</c:forEach>
						</li>
					</ul>
				</div>
				<div class="content">
					<h3>프로젝트 소개</h3>
					<div class="project_intro">
					${rboard.rb_content}
					</div>
				</div>
			
			<%-- 댓글 섹션 --%>
			<div id="comments_container">
			
				<h4>
					댓글 <span id="comments-cnt">0</span>
				</h4>
				
				<%-- 댓글 목록 --%>
				<div id="comments_list">
				
				</div>
			
				<%-- 새 댓글창 --%>
				<div id="new_comment">
					<form id="comment_form">
						<input type="hidden" id="rb_num" name="rb_num" value="${rboard.rb_num}" >
						<div class="flex-container">
						<c:if test="${empty mem.mem_photo}">
							<img src="${pageContext.request.contextPath}/images/face.png" class="profile-photo">
						</c:if>
						<c:if test="${!empty mem.mem_photo}">
							<img src="${pageContext.request.contextPath}/upload/${mem.mem_photo}" class="profile-photo">
						</c:if>
					<textarea name="rc_content" id="rc_content" placeholder="댓글을 입력하세요." cols="100" rows="6"></textarea>						
						</div>
						<div class="align-right">
							<input type="submit" class="btn btn-basic" value="댓글 등록">
						</div>
					</form>
				</div>
				
			</div>
		</div>
		</div>
	</div>
</body>

</html>