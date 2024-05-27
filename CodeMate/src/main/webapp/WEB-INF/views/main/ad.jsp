<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cyy.css" type="text/css">
</head>
<body>
<div class="page-container"> 

    <div class="container">
        <div class="slider-container" id="slider">
            <div class="slider-wrapper" id="sliderWrapper">
                <div class="slide">
                    <img class="ad" src="${pageContext.request.contextPath}/images/back1.png">
                </div>
                <div class="slide">
                    <img class="ad" src="${pageContext.request.contextPath}/images/back2.png">
                </div>
                <div class="slide">
                    <img class="ad" src="${pageContext.request.contextPath}/images/back3.png">
                </div>
            </div>
        </div>
        <button id="prev"><img src="${pageContext.request.contextPath}/images/arrow.png"></button>
        <button id="next"><img src="${pageContext.request.contextPath}/images/arrow.png"></button>
    </div>
    
</div>



<script src="${pageContext.request.contextPath}/js/carousel.js"></script> 
</body>
</html>