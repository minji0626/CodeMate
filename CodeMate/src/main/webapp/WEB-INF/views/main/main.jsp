<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>

<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/cyy.css" type="text/css">
	
</head>
<body>
	<div class="page-container">
		<div class="page-main">
			<jsp:include page="/WEB-INF/views/common/header.jsp" />
			<jsp:include page="/WEB-INF/views/main/ad.jsp" />
			<jsp:include page="/WEB-INF/views/main/Hot.jsp" />
		</div>
	</div>
</body>
</html>