var slides = document.querySelector('.wrapper'),
  slide = document.querySelectorAll('.mini'),
  currentIdx = 0,
  slideCount = slide.length,//슬라이드 개수
  slideWidth = 200,//슬라이드 이미지 넓이
  slideMargin = 10,
  prevBtn = document.querySelector('.p'),
  nextBtn = document.querySelector('.n');
var currentSlides = document.querySelectorAll('.clone');
var newSlideCount = currentSlides.length + slide.length; 
                              //currentSlide인데 왠지모르게 안됨->직접 숫자로 넣음
var newWidth = (slideWidth + slideMargin) * 18 - slideMargin + 'px';
makeClone();

function makeClone(){
   slides.style.transform = 'translateX('+ (slideWidth + slideMargin) * -newSlideCount +'px)';
   for(var i =0; i<slideCount; i++){//기존 슬라이드 뒤에 복제본 추가
      //a.cloneNode()->a요소 복제/a.cloneNode(true)->a요소의 자식까지 복제
      var cloneSlide = slide[i].cloneNode(true);
      cloneSlide.classList.add('clone');
      //기존의 요소 뒤에 추가 ->appendChild
      //a.appendChild(b) -> a요소 뒤에 b추가
      slides.appendChild(cloneSlide);
   }
   for(var i = slideCount-1; i>0; i--){//기존 슬라이드 앞에 복제본 추가
      var cloneSlide = slide[i].cloneNode(true);
      cloneSlide.classList.add('clone');
      //기존의 요소 앞에 추가 ->appendChild
      //a.prepend(b) -> a요소 앞에 b추가
      slides.prepend(cloneSlide);
   }
   //전체 슬라이드의 길이를 구해 wrapper의 길이로 넣어야 가로로 정렬이 됨
   
   //전체 길이 구한 다음 마지막 슬라이드의 마진을 빼줌
    slides.style.width = newWidth;
    //중간 슬라이드가 보이는 화면 중앙에 있어야 이전버튼을 누르더라도 슬라이드가 보임
   
}


function moveSlide(num){
      slides.style.transform = 'translateX(' + (-(slideWidth + slideMargin) * num) + 'px)';
      
      currentIdx = num;
      console.log(currentIdx,slideCount);
      if(currentIdx == slideCount){
         slides.style.left = '0px';
         currentIdx = 0;
      }else if(currentIdx == -(slideCount-2)){
         slides.style.transform = 'translateX('+ ((slideWidth + slideMargin) * num) + 'px)';
         currentIdx = 0;
      }
}

nextBtn.addEventListener('click', function () {
   moveSlide(currentIdx + 1);
});

prevBtn.addEventListener('click', function () {
   moveSlide(currentIdx - 1);
});