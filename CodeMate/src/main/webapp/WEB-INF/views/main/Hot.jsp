<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cyy.css" type="text/css">

<div id="mini-card">

<div>이번주 코메 인기글
   <button id="back_btn">백엔드</button>
<<<<<<< HEAD
   <a id="more" href=#>더보기</a>	
=======
   <a id="more" href=${pageContext.request.contextPath}/rboard/list.do>더보기</a>
>>>>>>> branch 'main' of https://github.com/reumeum/CodeMate.git
</div> 

<div class="mini-container">
   <div class="wrapper" id="miniwrapper">
      <div class="mini">
         <img class="content" src="${pageContext.request.contextPath}/images/back1.png">
      </div>
      <div class="mini">
         <img class="content" src="${pageContext.request.contextPath}/images/back2.png">
      </div>
      <div class="mini">
         <img class="content" src="${pageContext.request.contextPath}/images/back3.png">
      </div>
      <div class="mini">
         <img class="content" src="${pageContext.request.contextPath}/images/back1.png">
      </div>
      <div class="mini">
         <img class="content" src="${pageContext.request.contextPath}/images/back2.png">
      </div>
      <div class="mini">
         <img class="content" src="${pageContext.request.contextPath}/images/back3.png">
      </div>
   </div>
   
</div><!-- end of mini-container -->
   <button class="btn" id="p"><img src="${pageContext.request.contextPath}/images/arrow.png"></button>
   <button class="btn" id="n"><img src="${pageContext.request.contextPath}/images/arrow.png"></button> 

</div><!-- end of mini-card -->
<script src="${pageContext.request.contextPath}/js/card.js"></script> 