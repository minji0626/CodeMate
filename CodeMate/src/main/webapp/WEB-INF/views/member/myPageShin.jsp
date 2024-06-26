<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 코메 신청</title>
<link href="${pageContext.request.contextPath}/images/로고1.png"
	rel="shortcut icon" type="image/x-icon">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/myTeam.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/rapplyDelete.js"></script>

</head>
<body>
	<!-- 헤더 링크-->
	<div class="page-container">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div id="flex_container">
			<!-- 사이드바 -->
			<jsp:include page="/WEB-INF/views/member/myPage_nav.jsp" />
			<!-- 사이드바 끝 -->
			<!-- 메인 정보 수정 -->
			<div class="float-C">
				<div class="align-center">
					<!-- myPage-TItleText만 가운데 정렬됨 -->
					<h3 class="mYPage-TitleText">나의 코메 신청</h3>
				</div>
				<c:if test="${empty aprboardList}">
					<div class="none_message">나의 신청내역이 없습니다</div>
				</c:if>
				<c:if test="${!empty aprboardList}">
					<c:forEach var="aprboard" items="${aprboardList}">
						<div class="myPage-line-box" data-pass="${aprboard.ra_pass}"
							onclick="window.location.href='${pageContext.request.contextPath}/rboard/detail.do?rb_num=${aprboard.rb_num}'" 
							style="cursor: pointer;">
							
							<div class="team-left">
								<%-- <c:if test="${aprboard.rb_category == '0'}">스터디</c:if>
								<c:if test="${aprboard.rb_category == '1'}">프로젝트</c:if> --%>
								<div class="skill_font_T">[프로젝트명]${aprboard.rb_pj_title}</div>
								<div class="projectName_font">${aprboard.rb_title}</div>
							</div>

							<div class="team-right-mo">
							<div class="count-myPageShin">
								<div class="team-count">모집인원:${aprboard.rb_teamsize}명</div>
								<div class="team-count">프로젝트 진행기간:${aprboard.rb_period}개월</div>
								<div>모집 마감일:${aprboard.rb_endRecruit}</div>
							</div>
							<div class="right">
								<input type="button" value="나의 신청서" class="rboard-click"
								data-ra-content="${aprboard.ra_content}" data-ra-date="${aprboard.ra_date}" 
								data-rb-title="${aprboard.rb_title}" data-ra-pass="${aprboard.ra_pass}" onclick="event.stopPropagation(); showModal(this);">
								<div class="delete-green" id="delete_green" data-ranum="${aprboard.ra_num}">신청취소</div>
							</div>
							</div>
						</div>
					</c:forEach>
				</c:if>

			</div>
			<!-- 메인 정보 수정 끝 -->
		</div>
		<!-- flex_container끝 -->
	</div>
	<!-- page-container끝 -->
	
	<!-- 모달팝업 -->
	<div class="wrap">
	  <div id="pop_info" class="pop_wrap" style="display:none;">
	    <div class="pop_inner">
	      <div class="header">
	      <span id="popup-confirm">모집글</span>
	        <span id="popup-title"></span>
	        
	      </div>
	      <span id="consult-message">나의 신청 내용</span>
	      <div class="consult_content">
	        <span id="popup-message"></span>
	      </div>
	      <div class="footer">
	      	<div class="consult_reg_date">
	      	<span>모집 신청일 | </span>
	          <span id="popup-date"></span>
	        </div>
	        <button type="button" class="btn_close"><img src="${pageContext.request.contextPath}/images/delete_icon.png"></button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<script type="text/javascript">
	$(function(){
	    $('.myPage-line-box').each(function(){
	        let passValue = $(this).data('pass');
	        if (passValue == '0') {
	            $(this).css('border', '2px solid red');
	            $(this).find('.delete-green').hide(); // 신청하기 div 숨기기
	        }
	        if (passValue == '1') {
	            $(this).css('border', '2px solid #5aca7c');
	            $(this).find('.delete-green').hide(); // 신청하기 div 숨기기
	        }
	        if (passValue == '-1') {
	            $(this).css('border', '2px solid black');
	            $(this).find('.delete-green').show(); // 신청하기 div 보이기
	        }
	    });
	});

	function showModal(button) {
	    var title = $(button).data('rb-title');
	    var date = $(button).data('ra-date');
	    var content = $(button).data('ra-content');
	    $('#popup-title').text(title);
	    $('#popup-date').text(date);
	    $('#popup-message').text(content);
	    $('#pop_info').show();
	}

	$(document).ready(function() {
	    $('.btn_close').on('click', function() {
	        $('#pop_info').hide();
	    });
	});
	</script>

</body>
</html>
