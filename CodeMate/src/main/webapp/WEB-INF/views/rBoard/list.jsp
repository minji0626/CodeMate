<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>코메 구하기</title>
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
<script>
	// 드롭다운 메뉴에서 옵션을 선택할 때마다 검색 함수 실행
	function addEventListeners() {
		document.querySelectorAll('.search-menu').forEach(function(element) {
			element.addEventListener('change', function() {
				search();
			});
		});
	}

	// 검색 기능을 처리하는 함수
	function search() {
		// 선택된 옵션 가져오기
		var selectedOptions = [];
		document.querySelectorAll('.search-menu').forEach(function(element) {
			selectedOptions.push(element.value);
		});

		// 여기에 검색 기능 실행하는 코드 추가
		console.log('선택된 옵션:', selectedOptions);
		// 검색 결과를 화면에 표시하거나 검색 요청을 서버에 전송하는 등의 로직을 추가할 수 있습니다.
	}

	document.addEventListener('DOMContentLoaded', addEventListeners);
</script>
<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/rboardList.js"></script>

<body>
	<div class="page-container">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="page-main">
			<div>
				<!--모집 구분-->
				<ul class="category">
					<li class="category-item" data-category="2">전체</li>
					<li class="category-item" data-category="1">프로젝트</li>
					<li class="category-item" data-category="0">스터디</li>
				</ul>
				<!--검색-->
				<div class="search-container">

					<select class="search-menu">
						<option value="" disabled selected>기술 스택</option>
						<option value="option1">옵션 1</option>
						<option value="option2">옵션 2</option>
						<option value="option3">옵션 3</option>
						<!-- 필요한 만큼 옵션 추가 -->
					</select> <select name="r_fields" class="search-menu">
						<option value="" disabled selected>모집 필드</option>
						<c:forEach var="field" items="${fieldList}">
							<option value="${field.f_code}">${field.f_name}</option>
						</c:forEach>
					</select> <select name="rb_meet" class="search-menu">
						<option value="" disabled selected>진행 방식</option>
						<option value="0">온라인</option>
						<option value="1">오프라인</option>
						<option value="2">온라인/오프라인</option>
						<!-- 필요한 만큼 옵션 추가 -->
					</select> <span class="search-menu"> 내 북마크 보기 </span> <span
						class="search-menu"> 모집중 보기 </span>
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
							<li class="r-item"
								onclick="location.href= '${pageContext.request.contextPath}/rboard/detail.do?rb_num=${rboard.rb_num}'">
								<div class="r-item-header">
									<span>마감일 | </span> <span>${rboard.rb_endRecruit}</span>
								</div>
								<div class="r-item-main">
									<span> <c:if test="${rboard.rb_category == 0}">
						[스터디]
						</c:if> <c:if test="${rboard.rb_category == 1}">
						[프로젝트]
						</c:if>
									</span>
									<p>${rboard.rb_title}</p>
								</div>
								<div>
									<c:forEach var="i" begin="0"
										end="${fn:length(rboard.hs_photo_arr) - 1}">
										<img
											src="${pageContext.request.contextPath}/images/hard_skill_logo/${rboard.hs_photo_arr[i]}"
											title="${rboard.hs_name_arr[i]}" class="skill-logo">
									</c:forEach>
								</div> <span>진행방식 | </span> <span> <c:if
										test="${rboard.rb_meet == 0}">
					온라인
					</c:if> <c:if test="${rboard.rb_meet == 1}">
					오프라인
					</c:if> <c:if test="${rboard.rb_meet == 2}">
					온라인/오프라인
					</c:if>
							</span>
								<div>
									<span>모집필드 | </span>
									<c:forEach var="field" items="${rboard.f_name_arr}">
										<span>${field}</span>
									</c:forEach>
								</div>
								<div>
									<span>신청인원 | </span> <span>${rboard.rb_apply_count}/${rboard.rb_teamsize}</span>
								</div>
								<div>
									<span>조회수 </span> <span>${rboard.rb_hit}</span>
								</div>
							</li>
						</c:forEach>
					</ul>

					<!--페이지 표시-->
					<div class="align-center">${page}</div>
				</c:if>
			</div>
		</div>
	</div>
</body>

</html>