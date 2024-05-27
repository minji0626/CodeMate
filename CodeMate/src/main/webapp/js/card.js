const miniwrapper = document.getElementById('miniwrapper');
const slides = miniwrapper.querySelectorAll('.mini');
const prevBtn = document.getElementById('p');
const nextBtn = document.getElementById('n');
const slideCount = slides.length;
const slideWidth = 1030;
const slideSpeed = 300;
let currentIndex = 0; // 현재 슬라이드의 인덱스
const slideTransition = slideSpeed + "ms"; // 슬라이드 전환 시간

miniwrapper.style.width = slideWidth * (slideCount + 2) + "px";

let firstClone = slides[slideCount - 1].cloneNode(true); // 마지막 슬라이드를 복제하여 첫 번째에 추가
let lastClone = slides[0].cloneNode(true); // 첫 번째 슬라이드를 복제하여 마지막에 추가
miniwrapper.appendChild(firstClone);
miniwrapper.insertBefore(lastClone, miniwrapper.firstElementChild);

// 현재 슬라이드에 active 클래스 추가
slides[currentIndex].classList.add('slide_active');

nextBtn.addEventListener('click', function() {
    moveSlide(1);
});

prevBtn.addEventListener('click', function() {
    moveSlide(-1);
});

function moveSlide(direction) {
    currentIndex += direction;
    miniwrapper.style.transition = slideTransition;
    miniwrapper.style.transform = "translateX(-" + (slideWidth * currentIndex) + "px)";

    // 슬라이드가 첫 번째 또는 마지막일 때 처리
    if (currentIndex === slideCount + 1) {
        setTimeout(function() {
            miniwrapper.style.transition = "0ms";
            miniwrapper.style.transform = "translateX(-" + slideWidth + "px)";
        }, slideSpeed);
        currentIndex = 0;
    } else if (currentIndex === -1) {
        setTimeout(function() {
            miniwrapper.style.transition = "0ms";
            miniwrapper.style.transform = "translateX(-" + (slideWidth * slideCount) + "px)";
        }, slideSpeed);
        currentIndex = slideCount - 1;
    }

    // 슬라이드 active 클래스 추가 및 제거
    for (let i = 0; i < slides.length; i++) {
        slides[i].classList.remove('slide_active');
    }
    slides[currentIndex].classList.add('slide_active');
}