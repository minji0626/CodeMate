<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>코메 구하기</title>
<link href="${pageContext.request.contextPath}/images/로고1.png"
	rel="shortcut icon" type="image/x-icon">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/kbr.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/rboardList.css"
	type="text/css">
</head>

<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>

<body>
	<div class="page-container">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="page-main">
			<div>
				<!--검색-->
				<div class="search-container">
					<div id="search_container_sub">
						<div id="scrollable_trigger" class="search-menu">기술 스택</div>
						<ul class="scrollable" style="display: none">
							<c:forEach var="hskill" items="${hskillList}">
								<li class="block"><input type="checkbox" name="r_skills"
									id="r_skill_${hskill.hs_code}" value="${hskill.hs_name}">
									<label for="r_skill_${hskill.hs_code}"><img
										class="hskill-photo"
										src="${pageContext.request.contextPath}/images/hard_skill_logo/${hskill.hs_photo}">${hskill.hs_name}</label>
								</li>
							</c:forEach>
						</ul>
						<select name="r_fields" class="search-menu">
							<option value="" selected>모집 필드</option>
							<c:forEach var="field" items="${fieldList}">
								<option value="${field.f_name}">${field.f_name}</option>
							</c:forEach>
						</select> 
						<select name="rb_category" class="search-menu">
							<option value="" selected>모집구분</option>
							<option value="0">스터디</option>
							<option value="1">프로젝트</option>
							<option value="2">전체</option>
						</select> 
						<select name="rb_meet" class="search-menu">
							<option value="" selected>진행 방식</option>
							<option value="0">온라인</option>
							<option value="1">오프라인</option>
							<option value="2">온라인/오프라인</option>
						</select> 
						<span class="search-menu" id="recruiting_filter_span">
							<input type="checkbox" id="recruiting_filter" name="recruiting_filter" value="1" style="display:none">
							<label for="recruiting_filter" id="recruiting_filter_label">모집중 보기</label>
						</span>
					</div>
					<div id="r_btn_div" class="flex-container">
						<div id="search_key_div" class="search-menu">
							<input type="search" name="search_key" id="search_key"
								placeholder="제목, 글 내용을 검색해보세요.">
						</div>
						<input type="button" value="코메 모집하기" class="btn btn-basic"
							onclick="location.href='teammateRecruitForm.do'">
					</div>
				</div>

				<!--모집글 리스트-->
				<c:if test="${count == 0}">
					<div>표시할 게시물이 없습니다.</div>
				</c:if>
				<c:if test="${count > 0}">
					<ul id="r_board">
						<c:forEach var="rboard" items="${rboardList}">
							<li class="r-item <c:if test="${rboard.daysLeft < 0 || rboard.team_status == 1 || rboard.team_status == 3}">not-recruiting</c:if>"
								onclick="location.href= '${pageContext.request.contextPath}/rboard/detail.do?rb_num=${rboard.rb_num}'">
								<div class="r-item-header">
									<div class="rb_category_div">
										<c:if test="${rboard.rb_category == 0}"><span class="rb_category study"> 
										<img src="${pageContext.request.contextPath}/images/study_w2.png" width="13" height="13">
										스터디</span>
										</c:if>
										<c:if test="${rboard.rb_category == 1}"><span class="rb_category project"> 
										<img src="${pageContext.request.contextPath}/images/project_w.png" width="13" height="13">
										프로젝트</span>
										</c:if>
									</div>
									<span class="rb_endRecruit">
										마감일 &nbsp;|&nbsp; ${rboard.rb_endRecruit}
									</span>
								</div>
								<div class="r-item-main">
									<div class="rb-title">${rboard.rb_title}</div>
									<div>
										<div class="skill-logo-div">
											<c:forEach var="i" begin="0"
												end="${fn:length(rboard.hs_photo_arr) - 1}">
												<c:if test="${i < 6}">
												<img
													src="${pageContext.request.contextPath}/images/hard_skill_logo/${rboard.hs_photo_arr[i]}"
													title="${rboard.hs_name_arr[i]}" class="skill-logo">
												</c:if>
												<c:if test="${i == 6}">
												<img
													src="${pageContext.request.contextPath}/images/more_icon.png"
													class="skill-logo">
												</c:if>
											</c:forEach>
										</div>
										<div class="r-item-info">
											<div class="proceed_all">
												<span class="proceed"> 
												<c:if test="${rboard.rb_meet == 0}">
												온라인
												</c:if> 
												<c:if test="${rboard.rb_meet == 1}">
												오프라인
												</c:if> 
												<c:if test="${rboard.rb_meet == 2}">
												온라인/오프라인
												</c:if>
												</span>
											</div>
											<div class="field_all">
												<c:forEach var="field" items="${rboard.f_name_arr}">
													<span class="mofield">${field}</span>
												</c:forEach>
											</div>
											<div class="apply_count_all">
												<span class="apply_count">신청인원 |
												${rboard.rb_apply_count}
												<c:if test="${rboard.rb_teamsize==0}">/ 인원 미정</c:if>
												<c:if test="${rboard.rb_teamsize!=0 && rboard.rb_teamsize!=10}">/ ${rboard.rb_teamsize}</c:if>
												<c:if test="${rboard.rb_teamsize==10}">/ 10명이상</c:if>
												</span>
											</div>
										</div>
									</div>
								</div>
								<div class="hit-div">
									<img src="${pageContext.request.contextPath}/images/cje/boardHitIcon.png"> <span>${rboard.rb_hit}</span>
								</div>
								<c:if test="${rboard.daysLeft < 0 || rboard.team_status == 1 || rboard.team_status == 3}">
									<div class="end_recruit">
									모집 마감
									</div>
								</c:if>
							</li>
						</c:forEach>
					</ul>

					<!--페이지 표시-->
					<div class="paging-button" <c:if test="${count <= 12}">style="display:none;"</c:if>>
						<input type="button" value="다음글 보기">
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/js/rboardList.js"></script>
</html>