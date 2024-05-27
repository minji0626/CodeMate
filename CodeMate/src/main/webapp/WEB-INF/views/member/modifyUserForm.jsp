<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
