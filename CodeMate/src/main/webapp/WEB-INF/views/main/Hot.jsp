<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cyy.css" type="text/css">

<div id="mini-card">

<div class="w">이번주 코메 인기글
   <button id="back_btn">백엔드</button>
   <a id="more" href=${pageContext.request.contextPath}/rboard/list.do>더보기</a>
</div> 

<div class="mini-container"> 
   <div class="wrapper">
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
<button class="p">
    <img src="${pageContext.request.contextPath}/images/left_arrow.png" alt="이전" width="20">
</button>
<button class="n">
    <img src="${pageContext.request.contextPath}/images/right_arrow.png" alt="다음" width="20">
</button>

</div><!-- end of mini-card -->
<script src="${pageContext.request.contextPath}/js/card.js"></script> 