<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.float-A {
    width: 250px;
    margin-right: 20px;
}
.myCount {
    background-color: #fff;
    border-radius: 5px;
    padding: 10px;
    text-decoration: none; /* 링크 밑줄 제거 (선택 사항) */
    color: inherit; /* 링크 색상 상속 (선택 사항) */
    font-size: 15px;
}

.myCount a.sideB_font {
    font-size: 20px;
    font-weight: bold;
}
/* 호버 기능 추가 - 제외할 항목들을 제외한 나머지 a 태그에만 적용 */
.myCount a:not(.no-hover):hover {
    font-weight: bold;
    color: black;
}
</style>
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