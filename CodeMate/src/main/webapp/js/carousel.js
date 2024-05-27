const sliderWrapper = document.getElementById('sliderWrapper'); 
const slides = sliderWrapper.querySelectorAll('.slide'); 
const prevBtn = document.getElementById('prev'); 
const nextBtn = document.getElementById('next'); 
const slideCount = slides.length;//이미지 3개 넣어서 3개임 
const slideWidth = 1043;
const slideSpeed = 300;
let startNum = 0; 

sliderWrapper.style.width = slideWidth * (slideCount) + "px"; //2700px

let firstChild = sliderWrapper.firstElementChild;//sliderWrapper의 첫 번째 자식 요소를 선택하여 firstChild에 할당(back1 img가 있는 div를 할당)
let lastChild = sliderWrapper.lastElementChild;
let clonedFirst = firstChild.cloneNode(true);//firstChild노드를 복제해서 clonedNode에 할당 하는데 true니까 하위요소도 같이 복제(img도 같이 복제됨)
let clonedLast = lastChild.cloneNode(true); 

sliderWrapper.appendChild(clonedFirst);//sliderWrapper의 자식 노드 중 가장 마지막에 clonedFirst를 추가
sliderWrapper.insertBefore(clonedLast, sliderWrapper.firstElementChild); //sliderWrapper의 첫 번째 자식 요소 앞에 clonedLast를 삽입
sliderWrapper.style.transform = "translate3d(-" + (slideWidth * (startNum + 1)) + "px, 0px, 0px)";//startNum=0이면 -900px만큼 이동 


let currentIndex = startNum; 
let curSlide = slides[currentIndex];//NodeList -> 배열이랑 비슷,querySelectorAll로 받은 요소들의 컬랙션 
curSlide.classList.add('slide_active'); //curSlide의 classList에 slide_active 추가
//classList는 DOM 요소의 클래스 목록을 나타내는 속성
//DOM 요소의 클래스는 HTML 요소에 적용된 CSS 클래스 ex) <div class="box red">는 

nextBtn.addEventListener('click', function() {  
   if (currentIndex < slideCount - 1) {                 
      sliderWrapper.style.transition = slideSpeed + "ms";    
      sliderWrapper.style.transform = "translate3d(-" + 
         (slideWidth * (currentIndex + 2)) + "px, 0px, 0px)";  
   }  
   if (currentIndex === slideCount - 1) {    
      setTimeout(function() {      
         sliderWrapper.style.transition = "0ms";      
         sliderWrapper.style.transform = "translate3d(-" + slideWidth +"px, 0px, 0px)";    
      }, slideSpeed);    
      currentIndex = -1; 
   }  
   curSlide.classList.remove('slide_active');  
   curSlide = slides[++currentIndex];  
   curSlide.classList.add('slide_active');
}); 


/** Prev Button Event */
prevBtn.addEventListener('click', function() {  
   if (currentIndex >= 0) {    
      sliderWrapper.style.transition = slideSpeed + "ms";    
      sliderWrapper.style.transform = "translate3d(-" + 
         (slideWidth * currentIndex) + "px, 0px, 0px)";  
   }  
   if (currentIndex === 0) {    
      setTimeout(function() {      
         sliderWrapper.style.transition = "0ms";      
         sliderWrapper.style.transform = "translate3d(-" + 
            (slideWidth * slideCount) + "px, 0px, 0px)";   
      }, slideSpeed);    
      currentIndex = slideCount;  
   }  
   curSlide.classList.remove('slide_active');  
   curSlide = slides[--currentIndex];  
   curSlide.classList.add('slide_active');
});


