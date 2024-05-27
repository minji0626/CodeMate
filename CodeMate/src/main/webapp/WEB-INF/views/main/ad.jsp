<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cyy.css" type="text/css">

 

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




<script src="${pageContext.request.contextPath}/js/carousel.js"></script> 