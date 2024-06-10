<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CODE MATE</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<style type="text/css">
.page_header {
	font-size: 1.5em;
	font-weight: bold;
	padding: 10px;
	margin-left: 10%;
}

.level_intro {
	padding: 10px;
	margin-left: 12%;
}

.level_intro .level_title {
	font-size: 1em;
	font-weight: bold;
}

.level_name {
	padding: 8px;
}

.level {
	display: flex;
	align-items: center;
	margin-top: 1.5%
}

.level img{
 margin-left: 0.5%;
 width: 32px;
 height: 32px;
}
</style>
</head>
<body>

<div class="page-container">
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	
	<div class="page_header">
	코메 사용이 처음이세요?
	</div>
		<div class="level_intro">
			<span class="level_title">코드메이트 레벨 시스템</span>
			<br>
			<p>
			코드메이트는 회원님들의 학습과 성장을 돕기 위해 5개의 레벨을 운영합니다. <br>
			각 레벨은 회원님의 실력 향상과 경험 축적을 반영하며, 레벨 1부터 5까지 다음과 같이 구분됩니다.
			</p>
			<div class="level">
			레벨 1	<img alt="level1" src="${pageContext.request.contextPath}/images/level1.png">
			</div>
			<span class="level_name">초급</span>
			
			<div class="level">
			레벨 2	<img alt="level2" src="${pageContext.request.contextPath}/images/level2.png">
			</div>
			<span class="level_name">초중급</span>
			
			<div class="level">
			레벨 3	<img alt="level3" src="${pageContext.request.contextPath}/images/level3.png">
			</div>
			<span class="level_name">중급</span>
			
			<div class="level">
			레벨 4 <img alt="level4" src="${pageContext.request.contextPath}/images/level4.png">
			</div>
			<span class="level_name">중고급</span>
			
			<div class="level">
			레벨 5 <img alt="level5" src="${pageContext.request.contextPath}/images/level5.png">
			</div>
			<span class="level_name">고급</span>
			
			<p style="margin-top: 1.5%;">
			회원님이 프로젝트를 완료할 때마다 경험치를 얻게 되며, 프로젝트 3개를 성공적으로 마칠 때마다 다음 레벨로 올라가게 됩니다.<br> 
			이를 통해 회원님은 점진적으로 더 복잡하고 도전적인 프로젝트를 수행하게 되며, 자신의 능력을 향상시킬 수 있습니다.<br>
			코드메이트와 함께 꾸준히 학습하며, 각 레벨을 차례로 정복해보세요! 당신의 성장과 성공을 응원합니다.<br>
			</p>
		</div>
		
</div>

</body>
</html>