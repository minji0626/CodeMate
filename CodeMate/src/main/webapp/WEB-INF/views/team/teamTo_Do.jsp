<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmj.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <title>CODEMATE Team Project</title>
    <link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
  </head>
  <body>
  
  <div class="page-container">
  	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <jsp:include page="/WEB-INF/views/team/teamNav.jsp"/>
    
    <c:if test="${alert == 1 }">
   	 	<script type="text/javascript">
    	alert('팀 프로젝트가 종료되었습니다. 리뷰를 작성해주세요.');
    	</script>
    </c:if>
    
    <c:if test="${team_num != sessionScope.team_num }">
    <div id="wrong_access" style="text-align: center; margin-top: 25%; font-size: 20px; font-weight: bold;">
    잘못된 접근입니다.
    </div>
    </c:if>
    
    <c:if test="${team_num == sessionScope.team_num}">
    <div class="container">
      <div class="left">
        <div class="calendar">
          <div class="month">
            <i class="fas fa-angle-left prev"></i>
            <div class="date"></div>
            <i class="fas fa-angle-right next"></i>
          </div>
          <div class="weekdays">
            <div>일</div>
            <div>월</div>
            <div>화</div>
            <div>수</div>
            <div>목</div>
            <div>금</div>
            <div>토</div>
          </div>
          <div class="days"></div>
          <div class="goto-today">
            <div class="goto">
              <input type="text" placeholder="mm/yyyy" class="date-input" />
              <button class="goto-btn">이동</button>
            </div>
          </div>
        </div>
      </div>
      <div class="right">
        <div class="today-date">
          <div class="event-day"></div>
          <div class="event-date"></div>
        </div>
        <div class="events"></div>
        <div class="add-event-wrapper">
          <div class="add-event-header">
            <div class="title">To-Do 추가</div>
            <i class="fas fa-times close"></i>
          </div>
          <div class="add-event-body">
            <div class="add-event-input">
              <input type="text" placeholder="To-Do 내용" class="event-name" />
            </div>
            <div class="add-event-input">
              <input
                type="text"
                placeholder="To-Do 시작 시간 (24시간 형식)"
                class="event-time-from"
              />
            </div>
            <div class="add-event-input">
              <input
                type="text"
                placeholder="To-do 종료 시간 (24시간 형식)"
                class="event-time-to"
              />
            </div>
          </div>
          <div class="add-event-footer">
            <button class="add-event-btn">추가하기</button>
          </div>
        </div>
      </div>
      <button class="add-event">
        <i class="fas fa-plus"></i>
      </button>
    </div>
    </c:if>
    
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/team.main.js"></script>
  </body>
</html>
