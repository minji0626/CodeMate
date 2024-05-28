<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <style>
        .slider-container {
        	margin-top: 10px;
            position: relative;
            width: 1300px; /* 컨테이너 너비 */
            height: 330px; /* 컨테이너 높이 */
            overflow: hidden;
            margin: 0 auto; /* 가운데 정렬 */
            border-radius: 10px;
        }

        .slider-wrapper {
            display: flex;
            transition: transform 0.5s ease-in-out;
            height: 100%;
        }

        .slide {
            width: 100%; /* 슬라이드 너비 */
            flex-shrink: 0;
        }

        .ad {
            width: 100%;
            height: auto;
        }

        /* 배너 버튼 */
        #prev, #next {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            background: none;
            border: none;
            cursor: pointer;
            opacity: 0.8;
            transition: opacity 0.3s ease;
        }

        #prev {
            position:absolute;
   			top:270px; left:250px;
        }

        #next {
            position:absolute;
   			top:270px; right:250px;
        }

        button:hover {
            opacity: 1;
        }
    </style>

<div class="slider-container">
    <div class="slider-wrapper" id="sliderWrapper">
		<div class="slide">
			<img class="ad"
				src="${pageContext.request.contextPath}/images/ad1.gif" alt="광고 1">
		</div>
		<div class="slide">
            <img class="ad" src="${pageContext.request.contextPath}/images/ad2.gif" alt="광고 2">
        </div>
    </div>
</div>

<button id="prev">
    <img src="${pageContext.request.contextPath}/images/left_arrow.png" alt="이전" width="20">
</button>
<button id="next">
    <img src="${pageContext.request.contextPath}/images/right_arrow.png" alt="다음" width="20">
</button>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        var sliderWrapper = document.getElementById("sliderWrapper");
        var prevButton = document.getElementById("prev");
        var nextButton = document.getElementById("next");
        var slides = document.querySelectorAll(".slide");
        var slideIndex = 0;
        var totalSlides = slides.length;

        function showSlide(index) {
            var offset = -index * 100;
            sliderWrapper.style.transform = 'translateX(' + offset + '%)';
        }

        function nextSlide() {
            slideIndex = (slideIndex + 1) % totalSlides;
            showSlide(slideIndex);
        }

        function prevSlide() {
            slideIndex = (slideIndex - 1 + totalSlides) % totalSlides;
            showSlide(slideIndex);
        }

        nextButton.addEventListener("click", nextSlide);
        prevButton.addEventListener("click", prevSlide);
    });
</script>
