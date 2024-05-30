<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="idForm">
  <input type="email" id="emailInput" placeholder="이메일 주소" required>
  <button type="submit">아이디 찾기</button>
</form>
<div id="result"></div>
<script src="${pageContext.request.contextPath}/js/findeId.js"></script> 
</body>
</html>