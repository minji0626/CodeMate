<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 코메 신청서</title>
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
	href="${pageContext.request.contextPath}/css/myRapplyForm.css"
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
					<h3 class="mYPage-TitleText">나의 코메 신청서</h3>
				</div>
				<c:if test="${!empty rapply}">
						
						<div class="myPage-rapply-box" data-pass="${rapply.ra_pass}" style="cursor: pointer;">
						<div class="rboardCon-left">
						<div class="projectName">[모집글]${rapply.rb_title}얼마나 길어야얼마나 길어야얼마나 길어야얼마나 길어야얼마나 길어야얼마나 길어야</div>
						
						</div>
						<div class="applyCon-center">
						<div class="myApplyContent">${rapply.ra_content}</div>
						</div>
						<div class="delete-flexbox">
							<div class="delete-green" id="delete_green" data-ranum="${rapply.ra_num}" data-rbnum="${rapply.rb_num}">신청취소</div>
						</div>
						</div>
						
				</c:if>

			</div>
			<!-- 메인 정보 수정 끝 -->
		</div>
		<!-- flex_container끝 -->
	</div>
	<!-- page-container끝 -->
<script type="text/javascript">
$(function(){
    $('.myPage-rapply-box').each(function(){
        let passValue = $(this).data('pass');
        if (passValue == '0') {
            $(this).css('border', '2px solid red');
            $(this).find('.delete-green').hide(); // 신청취소 div 숨기기
        }
        if (passValue == '1') {
            $(this).css('border', '2px solid #5aca7c');
            $(this).find('.delete-green').hide(); // 신청취소 div 숨기기
        }
        if (passValue == '-1') {
            $(this).css('border', '2px solid black');
            $(this).find('.delete-green').show(); // 신청취소 div 보이기
        }
    });
});
</script>
</body>
</html>
