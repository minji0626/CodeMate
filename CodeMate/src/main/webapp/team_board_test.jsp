<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/team_nav.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/team_board.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <title>Team Main Page - Test</title>
</head>
<body id="team_main_body">
  <div class="page-container">
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <jsp:include page="team_nav_test.jsp"/>
    <div class="content-container">
        <div class="button-container">
            <button class="write-button" onclick="location.href='team_writeForm.jsp'">글쓰기</button>
        </div>
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
