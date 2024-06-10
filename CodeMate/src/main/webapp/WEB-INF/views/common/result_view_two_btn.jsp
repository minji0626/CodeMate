<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${result_title}</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/kbr.css" type="text/css">
</head>
<body>
	<div class="page-container">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="content-main-div align-center">
			<h2>${result_title}</h2>
			<div>
				<div class="content-main-msg">
					${result_msg}
					<p></p>
					<div class="btn-div">
					<input type="button" class="btn-basic btn" value="${btn_value1}"
						onclick="location.href='${result_url1}'"> <input
						type="button" class="btn-basic btn" value="${btn_value2}"
						onclick="location.href='${result_url2}'">
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>