<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/team_nav.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmj.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cje.board.css" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<title>팀 게시판 Test</title>
</head>
<body>

	<div>
	    <div class="button-container">
	    	<button class="write-button" onclick="location.href='team_writeForm.jsp'">글쓰기</button>
		</div>
		
		
		<!-- 게시판 선택 메뉴 -->
		<div>		
		<ul>
			<li>
				<img src="${pageContext.request.contextPath}/images/cje/freeBoardIcon.png" width="30px" class="board-icon"> <span><a href="${pageContext.request.contextPath}/board/community.do">자유 게시판</a></span>
			</li>
			<li>
				<img src="${pageContext.request.contextPath}/images/cje/codingBoardIcon.png" width="30px" class="board-icon"> <span><a href="${pageContext.request.contextPath}/board/community2.do"><b>개발 게시판</b></a> </span>
			</li>
		</ul>
		</div>
	 <table>
	 	<tr>
	 		<th>번호</th>
	 		<th>제목</th>
	 		<th>작성자</th>
	 		<th>조회수</th>
	 		<th>작성일</th>
	 	</tr>
	 	<tr class="content">
	 		<td>1</td>
	 		<td>개발 게시판</td>
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
	    <tr>
	 		<td>3</td>
	 		<td>오늘은 컴퓨터 관련 이야기를 나눠볼까요?</td>
	 		<td>분모재</td>
	 		<td>12</td>
	 		<td>2024-05-19</td>
	 	</tr>
	    <tr>
	 		<td>4</td>
	 		<td>저번 주말에 친구들과 함께 해커톤을 개최했어요!</td>
	 		<td>박길동</td>
	 		<td>8</td>
	 		<td>2024-05-20</td>
	 	</tr>
	    <tr>
	 		<td>5</td>
	 		<td>팀원들 모두 수고하셨습니다. 오늘 회식 어떠세요?</td>
	 		<td>홍길동</td>
	 		<td>10</td>
	 		<td>2024-05-21</td>
	 	</tr>
	    <tr>
	 		<td>6</td>
	 		<td>팀원들 모두 수고하셨습니다. 오늘 회식 어떠세요?</td>
	 		<td>홍길동</td>
	 		<td>10</td>
	 		<td>2024-05-21</td>
	 	</tr>
	    <tr>
	 		<td>7</td>
	 		<td>팀원들 모두 수고하셨습니다. 오늘 회식 어떠세요?</td>
	 		<td>홍길동</td>
	 		<td>10</td>
	 		<td>2024-05-21</td>
	 	</tr>
	 	<tr>
	 		<td>8</td>
	 		<td>팀원들 모두 수고하셨습니다. 오늘 회식 어떠세요?</td>
	 		<td>홍길동</td>
	 		<td>10</td>
	 		<td>2024-05-21</td>
	 	</tr>
	 	<tr>
	 		<td>9</td>
	 		<td>팀원들 모두 수고하셨습니다. 오늘 회식 어떠세요?</td>
	 		<td>홍길동</td>
	 		<td>10</td>
	 		<td>2024-05-21</td>
	 	</tr>
	 	<tr>
	 		<td>10</td>
	 		<td>팀원들 모두 수고하셨습니다. 오늘 회식 어떠세요?</td>
	 		<td>홍길동</td>
	 		<td>10</td>
	 		<td>2024-05-21</td>
	 	</tr>
	 </table>
	</div>
</body>
</html>
