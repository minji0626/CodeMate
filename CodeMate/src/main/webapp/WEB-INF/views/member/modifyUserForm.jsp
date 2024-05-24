<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 정보 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pmj.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	//회원 정보 수정 유효성 체크
	$('#modify_form').submit(function(){
		const items = document.querySelectorAll('.input-check');
		for(let i = 0;i<items.length;i++){
			if(items[i].value.trim()==''){
				const label = document.querySelector('label[for="'+items[i].id+'"]');
				alert(label.textContent + ' 항목은 필수 입력');
				items[i].value = '';
				items[i].focus();
				return false;
			}
		}  
	});
});
</script>
</head>
<body>
<!-- 헤더 링크-->
<div id="flex_container">
<!-- 사이드바 -->
<div class="float-A">
 	<div class="myCount">
   		<a href="a" class="sideB_font">나의 정보</a>
   		<a class="sideB_font">My 코메</a>
   		<a href="a">참여중인 팀</a>
   		<a href="a">나의 코메 신청</a>
   		<a href="a">나의 코메 모집</a>
   		<a href="a">북마크</a>
   		<a class="sideB_font">나의 활동</a>
   		<a href="a">쪽지</a>
   		<a href="a">내가 쓴 글</a>
   		<a href="a">내가 쓴 댓글</a>
	</div>	
</div>
<!-- 사이드바 끝 -->
<!-- 메인 정보 수정 -->
<div class="float-B">
<div class="align-center">
    <form name="modify_form" action="modifyUser.do" method="post" class="modifyUser">
    <h3 class="title">나의 정보 수정</h3>
        <div class="align-left">
            <ul class="item-align-center">
                <li>
                    <label for="mem_name" class="red-text">이름</label><br>
                    <input type="text" id="mem_name" name="mem_name" maxlength="10" class="input-check" value="${member.mem_name}">
                </li>
                <li>
                    <label for="id" class="blue-text">아이디</label><br>
                    <input type="text" id="id" name="id" maxlength="20" class="input-check" value="${member.mem_id}">
                </li>
                <li>
                    <label for="email" class="green-text">이메일</label><br>
                    <input type="email" id="email" name="email" maxlength="50" class="input-check" value="${member.mem_email}">
                </li>
                <li>
                    <label for="nickname" class="gray-text">닉네임</label><br>
                    <input type="text" id="nickname" name="nickname" maxlength="20" class="input-check" value="${member.mem_nickname}">
                </li>
                <li>
                    <label for="phone" class="default-text">전화번호</label><br>
                    <input type="text" id="phone" name="phone" maxlength="20" class="input-check" value="${member.mem_phone}">
                </li>
            </ul>
        </div>
        <br>
        <div class="align-center margin-bottom-large">
            <input type="submit" value="저장하기" class="save_btn" >
        </div>
        <div class="small_font">
            <br><br>
            <span>회원 탈퇴하기</span>
            <span>/</span>
            <span>로그아웃</span>
        </div>
    </form>
</div>
</div>
<!-- 메인 정보 수정 끝 -->
</div>
</body>
</html>