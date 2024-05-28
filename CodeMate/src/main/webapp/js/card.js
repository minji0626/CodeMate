 document.addEventListener("DOMContentLoaded", function () {
        var sliderWrapper = document.getElementById("miniwrapper");
        var prevButton = document.getElementById("p");
        var nextButton = document.getElementById("n");
        var slides = document.querySelectorAll(".mini");
        var slideIndex = 0;
        var totalSlides = slides.length;

        function showSlide(index) {
            var offset = -index * 20;
            sliderWrapper.style.transform = 'translateX(' + offset + '%)';
        }
		
        function nextSlide() {
            slideIndex = (slideIndex + 1) % totalSlides;
            showSlide(slideIndex);
        }

        function prevSlide() {
            slideIndex = (slideIndex - 1 + totalSlides) % totalSlides;
            if(slideIndex == 0){
				
			}else{
				showSlide(slideIndex);
			}
            
        }




	




        nextButton.addEventListener("click", nextSlide);
        prevButton.addEventListener("click", prevSlide);
    });