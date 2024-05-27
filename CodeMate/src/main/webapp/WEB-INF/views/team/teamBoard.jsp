<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/team_nav.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/team_board.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <title>팀 게시판</title>
</head>
<body>
  <div class="page-container">
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <jsp:include page="/WEB-INF/views/team/teamNav.jsp"/>
    <div class="content-container">
        <div class="button-container">
            <button class="write-button" onclick="location.href='${pageContext.request.contextPath}/team/teamWriteForm.do'">글쓰기</button>
        </div>
        <form id="search_form" action="list.do" method="get">
    		<div class="search-container">
       		<select name="keyfield" class="search-select">
            	<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
            	<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>작성자</option>
            	<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
        	</select>
        <input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}" class="search-input">
        <input type="submit" value="검색" class="search-submit">
    </div>
</form>
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>조회수</th>
                        <th>작성일</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>오늘 날씨가 되게 좋아요</td>
                        <td>홍길동</td>
                        <td>4</td>
                        <td>2024-05-17</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>내일은 비가 올 예정입니다.</td>
                        <td>이길동</td>
                        <td>6</td>
                        <td>2024-05-18</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
  </div>
</body>
</html>
