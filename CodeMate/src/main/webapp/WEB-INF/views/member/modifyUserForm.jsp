<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 정보 수정</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modifyUser.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
    // 회원 정보 수정 유효성 체크
    $('#modify_form').submit(function(){
        
    	const idField1 = document.getElementById('mem_name');
        if(idField1.value.trim() == ''){
            alert('이름은 필수 입력');
            idField1.focus();
            return false;
        }
        
        const idField2 = document.getElementById('mem_id');
        const originalId = document.getElementById('original_mem_id').value;
        if (idField2.value.trim() == '' || idField2.value != originalId) {
            alert('아이디는 변경할 수 없습니다.');
            idField2.focus();
            idField2.value = originalId;
            return false;
        }
        const idField3 = document.getElementById('mem_email');
        if(idField3.value.trim() == ''){
            alert('이메일 필수 입력');
            idField3.focus();
            return false;
        }
        const idField4 = document.getElementById('mem_nickname');
        if(idField4.value.trim() == ''){
            alert('닉네임 필수 입력');
            idField4.focus();
            return false;
        }
        
        const idField5 = document.getElementById('mem_phone');
        if(idField5.value.trim() == ''){
            alert('전화번호 필수 입력');
            idField5.focus();
            return false;
        }
  
    });
    
    
    /* 프로필 사진 추가 */
	$('#mem_photo_btn').click(function(){
		$('#mem_photo').click();// 버튼 클릭 시 숨겨진 파일 입력 필드를 클릭하도록 설정
	});

	// 이미지 미리 보기
	let photo_path = $('.my-photo').attr('src'); // 처음 화면에 보여지는 이미지 읽기
	$('#mem_photo').change(function(){
		let my_photo = this.files[0]; // 업로드한 파일 정보
		if(!my_photo){
			// 선택을 취소하면 원래 처음 화면으로 되돌림
			$('.my-photo').attr('src', photo_path);
			return;
		}
		
		// 용량 체크
		if(my_photo.size > 1024*1024){
			alert(Math.round(my_photo.size/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
			$('.my-photo').attr('src', photo_path);
			$(this).val(''); // 선택한 파일 정보 지우기
			return;
		}
		// 화면에 이미지 미리 보기 + 이게 62줄에 있는 이미지 미리 보기랑 무엇이 다른건지
		const reader = new FileReader();
		reader.readAsDataURL(my_photo);
		
		reader.onload=function(){
			$('.my-photo').attr('src', reader.result);
		};
	});

	// 이미지 삭제 버튼 클릭 시
	$('#delete_photo_btn').click(function(){
		if(confirm('프로필 사진을 삭제하시겠습니까?')){
			$('.my-photo').attr('src', '${pageContext.request.contextPath}/images/face.png'); // 기본 이미지로 변경
			$('#mem_photo').val(''); // 파일 선택 창 초기화
		}
	});

	// 이미지 미리보기 취소
	$('#mem_photo_reset').click(function(){
		// 초기 이미지 표시
		$('.my-photo').attr('src', photo_path); // 이미지 미리보기 전 이미지로 되돌리기
		$('#mem_photo').val('');
		$('#mem_photo_choice').hide();
		$('#mem_photo_btn').show(); // 수정 버튼 표시
	});
	/* 프로필 사진 추가 */
});

</script>
</head>
<body>
<!-- 헤더 링크-->
<div class="page-container">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div id="flex_container">
<!-- 사이드바 -->
<jsp:include page="/WEB-INF/views/member/myPage_nav.jsp"/>
<!-- 사이드바 끝 -->
<!-- 메인 정보 수정 -->
<div class="float-B">
<div class="align-center">
    <form id="modify_form" name="modify_form" action="modifyUser.do" method="post">
    <h3 class="mYPage-TitleText">나의 정보 수정</h3>
    
    <!-- 프로필 사진 추가 -->
			<ul>
				<li>
					<c:if test="${empty member.mem_photo}"><%--memberdao 상세정보에 photo넣어서 아용할 수 있음 --%>
						<img src="${pageContext.request.contextPath}/images/face.png" width="150" height="150" class="my-photo">
					</c:if>
					<c:if test="${!empty member.mem_photo}">
						<img src="${pageContext.request.contextPath}/upload/${member.mem_photo}" width="150" height="150" class="my-photo">
					</c:if>
				</li>
				<li>
					<div class="align-center">
					<!-- 프로필 사진 추가 버튼 -->
    				<button type="button" id="mem_photo_btn"  style="border: none; background: none; padding: 0; margin: 0; cursor: pointer;">
        			<img src="${pageContext.request.contextPath}/images/photo_icon.png" width="15" />
   					 </button>
    				<!-- 프로필 사진 삭제 버튼 -->
    				<button type="button" id="delete_photo_btn" style="border: none; background: none; padding: 0; margin: 0; cursor: pointer;">
        			<img src="${pageContext.request.contextPath}/images/delete_icon.png" width="15" />
   					</button>
					</div>
					
					<!-- 파일 입력 필드를 직접 숨김 -->
					<input type="file" id="mem_photo" accept="image/gif,image/png,image/jpeg" style="display:none;">
					
				</li>
			</ul>
    <!-- 프로필 사진 추가 끝 -->
    
    <div class="formBox">
        <div class="text-align-left">
            <ul class="item-align-center">
            
            	<li>
            	
            	</li>
            
                <li>
                    <label for="mem_name" class="form_label">이름</label><br>
                    <input type="text" id="mem_name" name="mem_name" maxlength="10" class="input-check" value="${member.mem_name}">
                </li>
                <li>
                    <label for="mem_id" class="form_label">아이디</label><br>
                    <input type="text" id="mem_id" name="mem_id" maxlength="20" class="input-check" value="${member.mem_id}">
               		<input type="hidden" id="original_mem_id" value="${member.mem_id}">
                </li>
                <li>
                    <label for="mem_email" class="form_label">이메일</label><br>
                    <input type="email" id="mem_email" name="mem_email" maxlength="50" class="input-check" value="${member.mem_email}">
                </li>
                <li>
                    <label for="mem_nickname" class="form_label">닉네임</label><br>
                    <input type="text" id="mem_nickname" name="mem_nickname" maxlength="20" class="input-check" value="${member.mem_nickname}">
                </li>
                <li>
                    <label for="mem_phone" class="form_label">전화번호</label><br>
                    <input type="text" id="mem_phone" name="mem_phone" maxlength="20" class="input-check" value="${member.mem_phone}">
                </li>
            </ul>
        </div>
        </div>
        <div class="align-center margin-bottom-large">
            <input type="submit" value="저장하기" class="save_btn" >
        </div>
        <div class="small-font">
            <br><br>
            <span>회원 탈퇴하기</span>
            <span>/</span>
            <span><a href="${pageContext.request.contextPath}/member/logout.do" id="header_logout">로그아웃</a></span>
        </div>
    </form>
</div>
</div>
<!-- 메인 정보 수정 끝 -->
</div>
</div>
</body>
</html>
