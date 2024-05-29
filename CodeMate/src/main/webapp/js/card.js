var slides = document.querySelector('.wrapper'),
  slide = document.querySelectorAll('.mini'),
  prevBtn = document.querySelector('.p'),
  nextBtn = document.querySelector('.n'),
currentIdx = 0,
  slideCount = slide.length,//기존 슬라이드 개수(6)
  slideWidth = 250,//슬라이드 이미지 넓이
  slideMargin = 40,//좌우마진있어서 *2함

  AllSlideCount = slideCount * 3, //전체 슬라이드 개수

  newWidth = (slideWidth+slideMargin+12) * AllSlideCount  + 'px';//wrapper 너비
/*------------------------------------------------------------------*/

	makeClone();

function makeClone(){
   /* 복제&추가 */
   for(var i =0; i<slideCount; i++){//기존 슬라이드 뒤에 복제본 추가
      //a.cloneNode()->a요소 복제/a.cloneNode(true)->a요소의 자식까지 복제
      var cloneSlide = slide[i].cloneNode(true);
      cloneSlide.classList.add('clone');
      //기존의 요소 뒤에 추가 ->appendChild
      //a.appendChild(b) -> a요소 뒤에 b추가
      slides.appendChild(cloneSlide);
   }
   for(var i = slideCount-1; i>=0; i--){//기존 슬라이드 앞에 복제본 추가
      var cloneSlide = slide[i].cloneNode(true);
      cloneSlide.classList.add('clone');
      //기존의 요소 앞에 추가 ->appendChild
      //a.prepend(b) -> a요소 앞에 b추가
      slides.prepend(cloneSlide);
   }
   
   //전체 슬라이드의 길이를 구해 wrapper의 길이로 넣어야 가로로 정렬이 됨
   slides.style.width = newWidth;
   
   //중간 슬라이드가 보이는 화면 중앙에 있어야 이전버튼을 누르더라도 슬라이드가 보임
   slides.style.transform = 'translateX('+ -((slideWidth+slideMargin) * slideCount) +'px)';
  
}

/* 무한루프 */
function moveSlide(num){
		currentIdx = num;
		console.log(currentIdx, -(slideWidth +slideMargin) * num);
	  
	  /* 좌,우 이동방향 */	
	  if(currentIdx > 0 ){//다음
		  slides.style.transform = 'translateX(' + -(slideWidth +slideMargin) * num + 'px)';
	  }else if(currentIdx < 0){//이전
	  		 currentIdx * -1;
		  slides.style.transform = 'translateX(' + (slideWidth +slideMargin) * num + 'px)';
	  }/* ??????뭐임 왜  */
      
      /* 다시 돌아옴 */
      if(currentIdx == slideCount){//4인 경우
         slides.style.left = -(slideWidth +slideMargin)+'px';
         currentIdx = 0;
      }else if(currentIdx == -slideCount){//-4인 경우
         slides.style.transform = 'translateX('+ (slideWidth +slideMargin)* num + 'px)';
         currentIdx = 0;
      }
}

/* 버튼 */
nextBtn.addEventListener('click', function () {
   moveSlide(currentIdx + 1);//currentIdx 는 양수가 됨
});

prevBtn.addEventListener('click', function () {
   moveSlide(currentIdx - 1);//currentIdx 는 음수가 됨
});


