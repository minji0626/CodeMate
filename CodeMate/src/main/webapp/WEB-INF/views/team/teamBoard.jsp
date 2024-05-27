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
        <!-- 검색 폼 -->
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
        <!-- 테이블 -->
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- 임시 공지사항 -->
                    <tr class="notice">
                        <td>1</td>
                        <td>임시 공지사항 제목</td>
                        <td>관리자</td>
                        <td>2024-05-01</td>
                    </tr>
                    <!-- 임시 게시글 -->
                    <tr>
                        <td>2</td>
                        <td><a href="/team/TBoardDetail.do">임시 게시글 제목</a></td>
                        <td>사용자</td>
                        <td>2024-05-02</td>
                    </tr>
                    <!-- 공지사항 -->
                    <c:forEach var="item" items="${noticeList}">
                        <c:if test="${tboard.tb_auth == 1}">
                            <tr class="notice">
                                <td>${tboard.tb_num}</td>
                                <td><a href="${pageContext.request.contextPath}/team/TBoardDetail.do?tb_num=${item.tb_num}">${item.tb_title}</a></td>
                                <td>${tboard.tb_title}</td>
                                <td>${tboard.mem_id}</td>
                                <td>${tboard.tb_reg_date}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    <!-- 일반 게시글 -->
                    <c:forEach var="item" items="${postList}">
                        <tr>
                            <td>${tboard.tb_num}</td>
                            <td>${tboard.tb_title}</td>
                            <td>${tboard.mem_id}</td>
                            <td>${tboard.tb_reg_date}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
  </div>
</body>
</html>
