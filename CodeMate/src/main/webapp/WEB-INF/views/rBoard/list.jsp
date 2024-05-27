<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>코메 구하기</title>
    <link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
	rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/rboardlist.css" type="text/css">
</head>
<script>
    // 드롭다운 메뉴에서 옵션을 선택할 때마다 검색 함수 실행
    function addEventListeners() {
        document.querySelectorAll('.search-menu').forEach(function(element) {
            element.addEventListener('change', function() {
                search();
            });
        });
    }

    // 검색 기능을 처리하는 함수
    function search() {
        // 선택된 옵션 가져오기
        var selectedOptions = [];
        document.querySelectorAll('.search-menu').forEach(function(element) {
            selectedOptions.push(element.value);
        });
        
        // 여기에 검색 기능 실행하는 코드 추가
        console.log('선택된 옵션:', selectedOptions);
        // 검색 결과를 화면에 표시하거나 검색 요청을 서버에 전송하는 등의 로직을 추가할 수 있습니다.
    }

    document.addEventListener('DOMContentLoaded', addEventListeners);
</script>

<body>
<div class="page-container">
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
    <div class="page-main">
        <div class="content-main">
            <!--모집 구분-->
            <ul class="category">
                <li class="category-item">전체</li>
                <li class="category-item">프로젝트</li>
                <li class="category-item">스터디</li>
            </ul>
            <!--검색-->
            <div class="search-container">

                <select class="search-menu">
                    <option value="" disabled selected>기술 스택</option>
                    <option value="option1">옵션 1</option>
                    <option value="option2">옵션 2</option>
                    <option value="option3">옵션 3</option>
                    <!-- 필요한 만큼 옵션 추가 -->
                </select>
                <select name="r_fields" class="search-menu">
                    <option value="" disabled selected>모집 필드</option>
                    <c:forEach var="field" items="${fieldList}">
                    	<option value="${field.f_code}">${field.f_name}</option>
                    </c:forEach>
                </select>
                <select name="rb_meet" class="search-menu">
                    <option value="" disabled selected>진행 방식</option>
                    <option value="0">온라인</option>
                    <option value="1">오프라인</option>
                    <option value="2">온라인/오프라인</option>
                    <!-- 필요한 만큼 옵션 추가 -->
                </select>
                <span class="search-menu">
                    내 북마크 보기
                </span>
                <span class="search-menu">
                    모집중 보기
                </span>
                <div id="search_key_div" class="search-menu">
                    <input type="search" name="search_key" id="search_key" placeholder="제목, 글 내용을 검색해보세요.">
                </div>
            </div>

            <!--모집글 리스트-->
            <ul id="r_board">
                <li class="r-item">
                    <div class="r-item-header">
                        <span>마감일 | </span>
                        <span>2024.05.20</span>
                    </div>
                    <div class="r-item-main">
                        <span>[프로젝트]</span>
                        <p>도서관 프로젝트 FE 팀원 모집합니다.</p>
                    </div>
                    <div>
                        <img src="${pageContext.request.contextPath}/images/hard_skill_logo/cpp-logo.png" class="skill-logo">
                        <img src="${pageContext.request.contextPath}/images/hard_skill_logo/reactjs-logo.png" class="skill-logo">
                    </div>
                    <div class="r-item-number">
                        <span>신청인원 | </span>
                        <span>30/5</span>
                    </div>
                </li>
                <li class="r-item">
                    <div class="r-item-header">
                        <span>마감일 | </span>
                        <span>2024.06.30</span>
                    </div>
                    <div class="r-item-main">
                        <span>[스터디]</span>
                        <p>취준/포폴) 약 알림 서비스 함께 만들어요...</p>
                    </div>
                    <div>
                        <img src="${pageContext.request.contextPath}/images/hard_skill_logo/cpp-logo.png" class="skill-logo">
                        <img src="${pageContext.request.contextPath}/images/hard_skill_logo/cpp-logo.png" class="skill-logo">
                    </div>
                    <div class="r-item-number">
                        <span>신청인원 | </span>
                        <span>0/3</span>
                    </div>
                </li>
                <li class="r-item">
                    <div class="r-item-header">
                        <span>마감일 | </span>
                        <span>2024.07.25</span>
                    </div>
                    <div class="r-item-main">
                        <span>[프로젝트]</span>
                        <p>[ 취준용 / 재미용 ] ⚾️ 야구 덕후 사이드 프로젝트 - 직관 친구 구하기 APP 디자이너/ 백엔드 팀원 모집</p>
                    </div>
                    <div>
                        <img src="${pageContext.request.contextPath}/images/hard_skill_logo/cpp-logo.png" class="skill-logo">
                        <img src="${pageContext.request.contextPath}/images/hard_skill_logo/cpp-logo.png" class="skill-logo">
                    </div>
                    <div class="r-item-number">
                        <span>신청인원 | </span>
                        <span>1/2</span>
                    </div>
                </li>
                <li class="r-item">
                    <div class="r-item-header">
                        <span>마감일 | </span>
                        <span>2024.07.25</span>
                    </div>
                    <div class="r-item-main">
                        <span>...</span>
                        <p>...</p>
                    </div>
                    <div>
                        <img src="${pageContext.request.contextPath}/images/hard_skill_logo/cpp-logo.png" class="skill-logo">
                        <img src="${pageContext.request.contextPath}/images/hard_skill_logo/cpp-logo.png" class="skill-logo">
                    </div>
                    <div class="r-item-number">
                        <span>신청인원 | </span>
                        <span>0/0</span>
                    </div>
                </li>
                <li class="r-item">
                    <div class="r-item-header">
                        <span>마감일 | </span>
                        <span>2024.07.25</span>
                    </div>
                    <div class="r-item-main">
                        <span>...</span>
                        <p>...</p>
                    </div>
                    <div>
                        <img src="${pageContext.request.contextPath}/images/hard_skill_logo/cpp-logo.png" class="skill-logo">
                        <img src="${pageContext.request.contextPath}/images/hard_skill_logo/cpp-logo.png" class="skill-logo">
                    </div>
                    <div class="r-item-number">
                        <span>신청인원 | </span>
                        <span>0/0</span>
                    </div>
                </li>
            </ul>

            <!--페이지 표시-->
            <div class="align-center">&lt; 1 2 3 4 5 6 &gt;</div>
        </div>
    </div>
    </div>
</body>

</html>