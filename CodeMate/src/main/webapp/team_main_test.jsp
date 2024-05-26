<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/team_nav.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cmj.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <title>Team Main Page - Test</title>
  </head>
  <body id="team_main_body">
  <div class="page-container">
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <jsp:include page="team_nav_test.jsp"/>

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
            <button class="today-btn">오늘</button>
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
            <div class="title">이벤트 추가</div>
            <i class="fas fa-times close"></i>
          </div>
          <div class="add-event-body">
            <div class="add-event-input">
              <input type="text" placeholder="이벤트 이름" class="event-name" />
            </div>
            <div class="add-event-input">
              <input
                type="text"
                placeholder="이벤트 시작 시간"
                class="event-time-from"
              />
            </div>
            <div class="add-event-input">
              <input
                type="text"
                placeholder="이벤트 종료 시간"
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
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/team.main.js"></script>
  </body>
</html>
