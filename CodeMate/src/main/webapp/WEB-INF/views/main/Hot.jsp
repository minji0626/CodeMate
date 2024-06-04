<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/cyy.css" type="text/css">
<div class="page-main">
<div id="mini-card1">
	<div class="w">
		<div class="explain">
			<b>이번주 코메 인기글</b>
		</div>
		<div id="back_btn">백엔드</div>
		<a class="more" href=${pageContext.request.contextPath}/rboard/list.do>더보기</a>
	</div>

	<div class="mini-container1">
		<div class="wrapper1">
			<div class="mini1">
				<div class="content1">slide1</div>
			</div>
			<div class="mini1">
				<div class="content1">slide2</div>
			</div>
			<div class="mini1">
				<div class="content1">slide3</div>
			</div>
			<div class="mini1">
				<div class="content1">slide4</div>
			</div>
		</div>

	</div>
	<!-- end of mini-container -->
	<button class="p1">
		<img src="${pageContext.request.contextPath}/images/left_arrow_1.png"
			alt="이전" width="20">
	</button>
	<button class="n1">
		<img src="${pageContext.request.contextPath}/images/right_arrow_1.png"
			alt="다음" width="20">
	</button>

</div>
<div id="mini-card2">
	<div class="w2">
		<div class="explain">
			<b>이번주 코메 인기글</b>
		</div>
		<div id="front_btn">프론트엔드</div>
		<a class="more" href=${pageContext.request.contextPath}/rboard/list.do>더보기</a>
	</div>

	<div class="mini-container2">
		<div class="wrapper2">
			<div class="mini2">
				<div class="content2">slide1</div>
			</div>
			<div class="mini2">
				<div class="content2">slide2</div>
			</div>
			<div class="mini2">
				<div class="content2">slide3</div>
			</div>
			<div class="mini2">
				<div class="content2">slide4</div>
			</div>
		</div>

	</div>
	<!-- end of mini-container -->
	<button class="p2">
		<img src="${pageContext.request.contextPath}/images/left_arrow_1.png"
			alt="이전" width="20">
	</button>
	<button class="n2">
		<img src="${pageContext.request.contextPath}/images/right_arrow_1.png"
			alt="다음" width="20">
	</button>

</div>
<!-- end of mini-card1 -->
</div>

<script src="${pageContext.request.contextPath}/js/card.js"></script>
