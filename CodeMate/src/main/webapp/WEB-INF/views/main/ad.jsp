<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.slide {
	position: absolute;
	opacity: 0;
	transition: opacity 0.5s ease-in-out;
}

.slide.active {
	opacity: 1;
}

.slider-container {
	position: relative;
	width: 100%;
	height: 330px; 
	overflow: hidden;
}

.slider-wrapper {
	display: flex;
	width: 300%;
	transition: transform 0.5s ease-in-out;
}

.ad {
	width: 100%;
	height: 400px;
}


/* 배너 버튼 */
#prev{
   position:absolute;
   top:270px; left:250px;
}
#next{
   position:absolute;
   top:270px; right:250px;
}

 button:hover{
   opacity: 0.5;
}
</style>
    <div class="slider-container" id="slider">
        <div class="slider-wrapper" id="sliderWrapper">
            <div class="slide active">
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
    <button id="prev" style="border: none; background: none; padding: 0; margin: 0; cursor: pointer;">
        <img src="${pageContext.request.contextPath}/images/left_arrow.png" width="15">
    </button>
    <button id="next" style="border: none; background: none; padding: 0; margin: 0; cursor: pointer;">
        <img src="${pageContext.request.contextPath}/images/right_arrow.png" width="15">
    </button>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        var sliderWrapper = document.getElementById("sliderWrapper");
        var prevButton = document.getElementById("prev");
        var nextButton = document.getElementById("next");
        var slides = document.querySelectorAll(".slide");
        var slideIndex = 0;

        function showSlide(index) {
            slides.forEach(function (slide) {
                slide.classList.remove("active");
            });
            slides[index].classList.add("active");
        }

        function nextSlide() {
            slideIndex = (slideIndex + 1) % slides.length;
            showSlide(slideIndex);
        }

        function prevSlide() {
            slideIndex = (slideIndex - 1 + slides.length) % slides.length;
            showSlide(slideIndex);
        }

        nextButton.addEventListener("click", nextSlide);
        prevButton.addEventListener("click", prevSlide);
    });
</script>

