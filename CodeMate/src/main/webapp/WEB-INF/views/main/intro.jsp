<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CODE MATE</title>
<link href="${pageContext.request.contextPath}/images/로고1.png"
	rel="shortcut icon" type="image/x-icon">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<style type="text/css">
.page_header {
	font-size: 1.5em;
	font-weight: bold;
	padding: 10px;
	margin-left: 10%;
}

.intro-section {
	padding: 10px;
	margin-left: 12%;
}

.intro-section .section-title {
	font-size: 1em;
	font-weight: bold;
}

.level-name .lock-duration {
	padding: 8px;
}

.level .lock {
	display: flex;
	align-items: center;
	margin-top: 1.5%
}

.lock {
	font-size: 11pt;
	font-weight: bold;
	color: #636363;
	margin-top: 5px;
}

.level img {
	margin-left: 0.5%;
	width: 32px;
	height: 32px;
}

html {
	margin-bottom: 5%;
}
</style>
</head>
<body>

	<div class="page-container">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />

		<div class="page_header">코메 사용이 처음이세요?</div>
		<div class="intro-section">
			<span class="section-title">코드메이트 레벨 시스템</span> <br>
			<p>
				코드메이트는 회원님들의 학습과 성장을 돕기 위해 5개의 레벨을 운영합니다. <br> 각 레벨은 회원님의 실력
				향상과 경험 축적을 반영하며, 레벨 1부터 5까지 다음과 같이 구분됩니다.
			</p>
			<div class="level">
				레벨 1 <img alt="level1"
					src="${pageContext.request.contextPath}/images/level1.png">
			</div>
			<span class="level-name">초급</span>

			<div class="level">
				레벨 2 <img alt="level2"
					src="${pageContext.request.contextPath}/images/level2.png">
			</div>
			<span class="level-name">초중급</span>

			<div class="level">
				레벨 3 <img alt="level3"
					src="${pageContext.request.contextPath}/images/level3.png">
			</div>
			<span class="level-name">중급</span>

			<div class="level">
				레벨 4 <img alt="level4"
					src="${pageContext.request.contextPath}/images/level4.png">
			</div>
			<span class="level-name">중고급</span>

			<div class="level">
				레벨 5 <img alt="level5"
					src="${pageContext.request.contextPath}/images/level5.png">
			</div>
			<span class="level-name">고급</span>

			<p style="margin-top: 1.5%;">
				여러분이 프로젝트를 완료할 때마다 경험치를 얻게 되며, 프로젝트 3개를 성공적으로 마칠 때마다 다음 레벨로 올라가게
				됩니다.<br> 이를 통해 여러분님은 점진적으로 더 복잡하고 도전적인 프로젝트를 수행하게 되며, 자신의 능력을
				향상시킬 수 있습니다.<br> 코드메이트와 함께 꾸준히 학습하며, 각 레벨을 차례로 정복해보세요! <br>
			</p>
		</div>
		<div class="intro-section">
			<span class="section-title">코드메이트 회원정지 규정</span>
			<p>
				코드메이트는 여러 메이트들이 협업하는 공간이기 때문에 회원정지 시스템을 운영하고 있습니다. <br> 회원정지는
				회원에 대한 신고가 들어왔을 때 관리자의 검토를 통하여 적용됩니다.
			</p>
			<div class="lock">정지 누적 1회</div>
			<span class="lock-duration">1주일 정지</span>
			<div class="lock">정지 누적 2회</div>
			<span class="lock-duration">2주일 정지</span>
			<div class="lock">정지 누적 3회</div>
			<span class="lock-duration">30일 정지</span>
			<div class="lock">정지 누적 4회</div>
			<span class="lock-duration">영구 정지</span>
			<p style="margin-top: 1.5%;">
			코드메이트와 함께하는 여정에서 여러분의 무한한 가능성을 발견하고, 최고의 개발자로 성장해보세요. <br>
			규정을 준수하고 서로를 존중하며, 협업과 배움의 즐거움을 만끽하는 멋진 공간을 함께 만들어갑시다. 여러분의 열정과 노력이 코드메이트와 함께 빛날 수 있도록, 항상 최선을 다해 지원하겠습니다. 당신의 도전과 성공을 진심으로 응원합니다! <br>
			</p>
		</div>

	</div>

</body>
</html>