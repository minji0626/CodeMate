<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>코메 구하기</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/kbr.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/rboardForm.css"
	type="text/css">
</head>

<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/rboardForm.js"></script>

<body>
	<div class="page-container">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="content-main">
			<form id="rboard_form" action="modify.do" method="post">
				<input type="hidden" name="rb_num" value="${rboard.rb_num}">
				<!--프로젝트 정보-->
				<div class="content">
					<h3>1.프로젝트 기본 정보를 입력해주세요.</h3>
					<ul id="project_info">
						<li class="flex-container"><label for="rb_category" class="info-label">모집 구분</label> <select class="input-check input-style"
							name="rb_category" id="rb_category">
								<option value="0" <c:if test="${rboard.rb_category==0}">selected</c:if>>스터디</option>
								<option value="1" <c:if test="${rboard.rb_category==1}">selected</c:if>>프로젝트</option>
						</select></li>
						<li class="flex-container"><label for="rb_teamsize" class="info-label">모집 인원</label> <select class="input-check input-style"
							name="rb_teamsize" id="rb_teamsize">
								<option value="0" <c:if test="${rboard.rb_teamsize==0}">selected</c:if>>인원 미정</option>
								<option value="1" <c:if test="${rboard.rb_teamsize==1}">selected</c:if>>1명</option>
								<option value="2" <c:if test="${rboard.rb_teamsize==2}">selected</c:if>>2명</option>
								<option value="3" <c:if test="${rboard.rb_teamsize==3}">selected</c:if>>3명</option>
								<option value="4" <c:if test="${rboard.rb_teamsize==4}">selected</c:if>>4명</option>
								<option value="5" <c:if test="${rboard.rb_teamsize==5}">selected</c:if>>5명</option>
								<option value="6" <c:if test="${rboard.rb_teamsize==6}">selected</c:if>>6명</option>
								<option value="7" <c:if test="${rboard.rb_teamsize==7}">selected</c:if>>7명</option>
								<option value="8" <c:if test="${rboard.rb_teamsize==8}">selected</c:if>>8명</option>
								<option value="9" <c:if test="${rboard.rb_teamsize==9}">selected</c:if>>9명</option>
								<option value="10" <c:if test="${rboard.rb_teamsize==10}">selected</c:if>>10명 이상</option>
						</select></li>
						<li class="flex-container"><label for="rb_meet" class="info-label">진행 방식</label> <select class="input-check input-style" name="rb_meet"
							id="rb_meet">
								<option value="0" <c:if test="${rboard.rb_meet==0}">selected</c:if>>온라인</option>
								<option value="1" <c:if test="${rboard.rb_meet==1}">selected</c:if>>오프라인</option>
								<option value="2" <c:if test="${rboard.rb_meet==2}">selected</c:if>>온라인/오프라인</option>
						</select></li>
						<li class="flex-container"><label for="rb_start" class="info-label">시작예정일</label><input class="input-check input-style" type="date"
							name="rb_start" id="rb_start" value=${rboard.rb_start}></li>
						<li class="flex-container"><label for="rb_period"  class="info-label">진행 기간</label> <select class="input-check input-style"
							name="rb_period" id="rb_period">
								<option value="0" <c:if test="${rboard.rb_period==0}">selected</c:if>>1개월 미만</option>
								<option value="1" <c:if test="${rboard.rb_period==1}">selected</c:if>>1개월</option>
								<option value="2" <c:if test="${rboard.rb_period==2}">selected</c:if>>2개월</option>
								<option value="3" <c:if test="${rboard.rb_period==3}">selected</c:if>>3개월</option>
								<option value="4" <c:if test="${rboard.rb_period==4}">selected</c:if>>4개월</option>
								<option value="5" <c:if test="${rboard.rb_period==5}">selected</c:if>>5개월</option>
								<option value="6" <c:if test="${rboard.rb_period==6}">selected</c:if>>6개월 이상</option>
						</select></li>
						<li class="flex-container"><label for="rb_endRecruit" class="info-label">모집 종료일</label> <input class="input-check input-style" type="date"
							name="rb_endRecruit" id="rb_endRecruit" value=${rboard.rb_endRecruit}></li>
						<li id="scroll_container_sub"  class="flex-container">
						<label class="info-label">요구 기술</label>
							<div id="scrollable_trigger" class="input-style">요구하는 기술 스택을 선택하세요.</div>
							<ul class="scrollable">
								<c:forEach var="hskill" items="${hskillList}">
									<li class="block">
										<input type="checkbox" name="r_skills" id="r_skill_${hskill.hs_code}" value="${hskill.hs_code}"
										<c:if test="${fn:contains(fn:join(rboard.hs_name_arr, ' '), hskill.hs_name)}">checked</c:if>>
										<label for="r_skill_${hskill.hs_code}"><img class="hskill-photo" src="${pageContext.request.contextPath}/images/hard_skill_logo/${hskill.hs_photo}">${hskill.hs_name}</label>
									</li>
								</c:forEach>
							</ul>
						</li>
						<li class="flex-container">
							<label class="block info-label">모집 필드</label>
							<ul id="field_list">
								<c:forEach var="field" items="${fieldList}">
									<li class="block">
										<input type="checkbox" name="r_fields" id="r_field_${field.f_code}" value="${field.f_code}"
										<c:if test="${fn:contains(fn:join(rboard.f_name_arr, ' '), field.f_name)}"> checked</c:if>>
										<label for="r_field_${field.f_code}">${field.f_name}</label>
									</li>
								</c:forEach>
							</ul>
						</li>
						<li class="flex-container"><label for="rb_pj_title" class="info-label">프로젝트 제목</label> <input class="input-check input-style"
							type="text" name="rb_pj_title" id="rb_pj_title" value=${rboard.rb_pj_title}
							placeholder="프로젝트의 제목을 입력해주세요" maxlength="40"></li>
					</ul>
				</div>
				<!--프로젝트 소개-->
				<div class="content">
					<h3>2.프로젝트에 대해 소개해주세요.</h3>
					<ul id="project_detail">
						<li><label for="rb_title" class="info-label">모집글 제목</label> <input class="input-check" type="text"
							id="rb_title" name="rb_title" placeholder="글 제목을 입력해주세요" value="${rboard.rb_title}" maxlength="60">
						</li>
						<li>
						<label style="display:none">프로젝트 소개글</label>
						<textarea name="rb_content" id="rb_content" class="input-check" rows="30"
								placeholder="프로젝트에 대해 소개해주세요">${rboard.rb_content}</textarea>
						</li>
					</ul>
				</div>
				<!--버튼-->
				<div class="btn-div align-center">
					<input type="button" value="취소" class="btn btn-cancel" onclick="location.href='detail.do?rb_num=${rboard.rb_num}'"> <input type="submit" class="btn btn-basic"
						value="글 수정">
				</div>

			</form>
		</div>
	</div>
</body>
</html>
