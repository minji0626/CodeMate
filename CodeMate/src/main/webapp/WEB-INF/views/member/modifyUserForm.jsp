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
	$('#mem_photo_choice').show();
	$(this).hide();//수정 버튼 감추기
});

//이미지 미리 보기
let photo_path = $('.my-photo').attr('src');//처음 화면에 보여지는 이미지 읽기
$('#mem_photo').change(function(){
	let my_photo = this.files[0];//업로드한 파일 정보
	if(!my_photo){
		//선택을 취소하면 원래 처음 화면으로 되돌림
		$('.my-photo').attr('src',photo_path);
		return;
	}
	
	//용량 체크
	if(my_photo.size > 1024*1024){
		alert(Math.round(my_photo.size/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
		$('.my-photo').attr('src',photo_path);
		$(this).val('');//선택한 파일 정보 지우기
		return;
	}
	//화면에 이미지 미리 보기
	const reader = new FileReader();
	reader.readAsDataURL(my_photo);
	
	reader.onload=function(){
		$('.my-photo').attr('src',reader.result);
	};
	
});//end of change

//이미지 전송
$('#mem_photo_submit').click(function(){
	if($('#mem_photo').val()==''){
		alert('파일을 선택하세요');
		$('#mem_photo').focus();
		return;
	}
	//파일 전송
	const form_data = new FormData();
	
	form_data.append('mem_photo',$('#mem_photo')[0].files[0])
	//dao에서 p를 소문자로 씀
	$.ajax({
		url:'updateMyPhoto.do',
		type:'post',
		data:form_data,
		dataType:'json',
		contentType:false, //데이터 객체를 문자열로 바꿀지 설정.true이면 일반 문자 false면 파일이 섞여있음
		processData:false, //해당 타입을 true로 하면 일반 text로 구분	//위에 두개를 false로 설정해 파일이 섞여있다는걸 나타냄
		success:function(param){
			if(param.result == 'logout'){
				alert('로그인 후 사용하세요');
			}else if(param.result == 'success'){
				alert('프로필 사진이 수정되었습니다.');
				//수정된 이미지 정보 저장
				photo_path = $('.my-photo').attr('src');
				//변경 전으로 초기화
				$('#mem_photo').val('');
				$('#mem_photo_choice').hide();
				$('#mem_photo_btn').show();//수정 버튼 표시
			}else{
				alert('파일 전송 오류 발생');	
			}
		},
		error:function(){
			alert('네트워크 오류 발생');
		}
	});
});//end of click


//이미지 미리보기 취소
$('#mem_photo_reset').click(function(){
	//초기 이미지 표시
	$('.my-photo').attr('src',photo_path);//이미지 미리보기 전 이미지로 되돌리기
	$('#mem_photo').val('');
	$('#mem_photo_choice').hide();
	$('#mem_photo_btn').show();//수정 버튼 표시
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
<div class="float-A">
    <div class="myCount">
        <a href="${pageContext.request.contextPath}/member/modifyUserForm.do" class="sideB_font">나의 정보</a>
        <a class="sideB_font">My 코메</a>
        <a href="${pageContext.request.contextPath}/member/myTeam.do">참여중인 팀</a>
        <a href="${pageContext.request.contextPath}/member/myPageShin.do">나의 코메 신청</a>
        <a href="${pageContext.request.contextPath}/member/myPageMo.do">나의 코메 모집</a>
        <a href="${pageContext.request.contextPath}/member/myPageBookMark.do">북마크</a>
        <a class="sideB_font">나의 활동</a>
        <a href="${pageContext.request.contextPath}/member/myWrite.do">내가 쓴 글</a>
        <a href="${pageContext.request.contextPath}/member/myReply.do">내가 쓴 댓글</a>
    </div>   
</div>
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
						<img src="${pageContext.request.contextPath}/images/face.png" width="200" height="200" class="my-photo">
					</c:if>
					<c:if test="${!empty member.mem_photo}">
						<img src="${pageContext.request.contextPath}/upload/${member.mem_photo}" width="200" height="200" class="my-photo">
					</c:if>
				</li>
				<li>
					<div class="align-center">
						<input type="button" value="프로필 사진 수정" id="mem_photo_btn">
					</div>
					<div id="mem_photo_choice" style="display:none;"><%--display인식안되면 파일업로드 수정폼 나옴 --%>
						<input type="file" id="mem_photo" accept="image/gif,image/png,image/jpeg">
						<input type="button" value="전송" id="mem_photo_submit"><%-- aja통신할거라 submit아님 --%>
						<input type="button" value="취소" id="mem_photo_reset">
					</div>
				</li>
			</ul>
    <!-- 프로필 사진 추가 -->
    
    
    <div class="formBox">
        <div class="text-align-left">
            <ul class="item-align-center">
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
