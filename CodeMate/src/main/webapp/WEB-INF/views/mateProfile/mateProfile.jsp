<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메이트 프로필</title>
    <link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cje.mp.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
</head>
<body>
	<div class="page-container">
			<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <!-- 메이트 프로필 -->
    <div id="mp_view">
        <!-- 메이트 프로필 창 -->
        <div class="mp_view_window">
            <!-- 메이트 프로필 제목 -->
            <div class="mp_view_title">
                <h2>메이트 프로필</h2>
            </div>
            <!-- 메이트 프로필 본문 -->
            <div class="content">
                <!-- 본인 프로필 (닉네임, 프사) -->
                <div class="mp_account_profile">
                    <!-- 프로필 사진 -->
                    <div class="photo_div">
	                    <c:if test="${empty mem.mem_photo}">
						<img src="${pageContext.request.contextPath}/images/face.png" 
											class="profile_image">
					</c:if>
					<c:if test="${!empty mem.mem_photo}">
						<img src="${pageContext.request.contextPath}/upload/${mem.mem_photo}" 
												class="profile_image">
					</c:if>
                    </div> 
                    <!-- 닉네임 -->
					<div class="mp_account_nickname">
					    <div class="user_info_container">
					        <span class="user_nickname">${member.mem_nickname}</span>
					        <!-- 레벨 이미지 추가하기 -->
					        <c:choose>
					            <c:when test="${mem.mem_level==1}">
					                <img src="${pageContext.request.contextPath}/images/level1.png" width="30px">
					            </c:when>
					            <c:when test="${mem.mem_level==2}">
					                <img src="${pageContext.request.contextPath}/images/level2.png" width="30px">
					            </c:when>
					            <c:when test="${mem.mem_level==3}">
					                <img src="${pageContext.request.contextPath}/images/level3.png" width="30px">
					            </c:when>
					            <c:when test="${mem.mem_level==4}">
					                <img src="${pageContext.request.contextPath}/images/level4.png" width="30px">
					            </c:when>
					            <c:when test="${mem.mem_level>=5}">
					                <img src="${pageContext.request.contextPath}/images/level5.png" width="30px">
					            </c:when>
					        </c:choose>
					    </div>
					    <div class="id_user">
					        <span class="user_id">${mem.mem_id}</span> <!-- 아이디 영역 -->
					    </div>
					</div>





                    <c:if test="${member.mem_num==mem_num}">
                    	<button class="mp_view_modify" onclick="location.href='mateProfileForm.do?mem_num=${mem.mem_num}'"><span>수정하기</span> </button>
                    </c:if>     
                </div>
                <!-- 포지션 DIV -->
                <!-- 
    아마 여기 p 태그들에 클래스 부여해야 할 듯
                     -->
                <div class="mp_content_div">
                    <h4>포지션</h4>
                    <div class="mp_content">
                        <p>
                        <c:if test="${member.mp_state == 0 && member.mem_num != mem_num}">
						    비공개 정보입니다.
						</c:if>
						<c:if test="${member.mp_state == 1 || member.mem_num == mem_num}">
							 <c:if test="${fn:trim(member.mp_position) eq ''}"> 
						        등록된 포지션이 없습니다.
						    </c:if>
						    <c:if test="${fn:trim(member.mp_position) ne ''}"> 
						        ${member.mp_position}
						    </c:if>
						</c:if>
                        	
                        </p>
                    </div>
                </div>
                <!-- 자기소개 DIV -->
                <div class="mp_content_div">
                    <h4>자기소개</h4>
                    <div class="mp_content">
                        <p>
	                        <c:if test="${member.mp_state == 0 && member.mem_num != mem_num}">
	                        		비공개 정보입니다.
	                        </c:if>
	                        <c:if test="${member.mp_state == 1 || member.mem_num == mem_num}">
		                        <c:if test="${fn:trim(member.mp_introduce) eq ''}"> 
							        등록된 자기소개가 없습니다.
							    </c:if>
							    <c:if test="${fn:trim(member.mp_introduce) ne ''}"> 
							        ${member.mp_introduce}
							    </c:if>
	                        </c:if>                        	
                        </p>
                    </div>
                </div>
                <!-- 스킬 DIV -->
                <div class="skill-container">
                    <!-- 하드스킬 DIV -->
                    <div class="mp_content_div skill-item">
                        <h4>하드스킬</h4>
                        <div class="mp_content_skill">
                        	<c:if test="${member.mp_state == 0 && member.mem_num != mem_num}">
	                        		<div class="mp_content"><p>비공개 정보입니다.</p></div>
	                        </c:if>
	                        <c:if test="${member.mp_state == 1 || member.mem_num == mem_num}">
	                            <c:if test="${!empty hardSkillList}">
	                            	<c:forEach var="hs_skill" items="${hardSkillList}">
	                            		
	                            		<div id="hs-options" class="option-container">
	                            			<label for="hs_code_${hs_skill.hs_code}"><img class="hskill-photo" src="${pageContext.request.contextPath}/images/hard_skill_logo/${hs_skill.hs_photo}"><span class="option-item">${hs_skill.hs_name}</span></label>
	                            		</div>
	                            	</c:forEach>
	                            </c:if>
	                            <c:if test="${empty hardSkillList}">
	                            			<div class="mp_content"><p>등록된 하드 스킬이 없습니다.</p></div>
								</c:if>
                            </c:if>
                        </div>
                    </div>
                    <!-- 소프트스킬 DIV -->
                    <div class="mp_content_div skill-item">
                        <h4>소프트스킬</h4>
                        <div class="mp_content_skill">
                            <c:if test="${member.mp_state == 0 && member.mem_num != mem_num}">
	                        		<div class="mp_content"><p>비공개 정보입니다.</p></div>
	                        </c:if>
	                        <c:if test="${member.mp_state == 1 || (member.mp_state == 0 &&member.mem_num == mem_num)}">
	                            <c:if test="${!empty softSkillList}">
	                            	<c:forEach var="ss_skill" items="${softSkillList}">
	                            		<div id="ss-options" class="option-container">
	                            			<label for="ss_code_${ss_skill.ss_code}"><span class="option-item">${ss_skill.ss_name}</span></label>
	                            		</div>
	                            	</c:forEach>
	                            </c:if>
	                            <c:if test="${empty softSkillList}">
	                            			<div class="mp_content"><p>등록된 소프트 스킬이 없습니다.</p></div>
								</c:if>
                            </c:if>
                        </div>
                    </div>
                </div>
                <!-- 프로젝트 경험 DIV -->
                
                <div class="mp_content_div">
                    <h4>프로젝트 경험</h4>
                     <c:if test="${member.mp_state == 0 && member.mem_num !=mem_num}">
	                       <div class="mp_content"><p>비공개 정보입니다.</p></div>
	                 </c:if>
	                 <c:if test="${member.mp_state == 1 || member.mem_num == mem_num}">
	                 	<c:if test="${!empty mateExpList}"> 	
		                 	<c:forEach var="exp" items="${mateExpList}" varStatus="loop">
							    <c:if test="${loop.index < 5}">
							        <div class="mp_project">
							            <span class="pj_category">프로젝트 분류 :
							                <c:if test="${exp.me_category==0}">
							                    개인
							                </c:if>
							                <c:if test="${exp.me_category==1}">
							                    기업
							                </c:if>
							            </span>
							            <h4 class="pj_name">프로젝트 이름 : ${exp.me_title}</h4>
							            <p class="pj_content">프로젝트 설명 : ${exp.me_content} </p>
							            <form action="deleteEXP.do" method="post">
							                <input type="hidden" name="me_num" value="${exp.me_num}">
							                <input type="hidden" name="mem_num" value="${mem_num}">
							                <c:if test="${member.mem_num==mem_num}">
							                <button type="submit" class="delete-btn">삭제</button>
							                </c:if>
							            </form>
							        </div>
							    </c:if>
							</c:forEach>
	                 	</c:if>
	                 	<c:if test="${empty mateExpList}">
  							<div class="mp_content"><p>등록된 프로젝트 경험이 없습니다.</p></div>
	                 	</c:if>
	                 </c:if>
                </div>   
                <!-- 코드메이트 후기 DIV -->
                
                <div class="mp_content_div">
                    <h4>메이트 후기</h4>
                      <c:if test="${member.mp_state == 0 && member.mem_num != mem_num}">
	                       <div class="mp_content"><p>비공개 정보입니다.</p></div>
	                 </c:if>
	                 <c:if test="${member.mp_state == 1 || member.mem_num == mem_num}">
	                 	<c:if test="${!empty mateReviewList}"> 	
		                 	<c:forEach var="mr" items="${mateReviewList}" varStatus="loop">
							    <c:if test="${loop.index < 5}">
							        <div class="mp_project">
							            <h4 class="pj_name">내용 : ${mr.mr_content}</h4> 
							            <p class="pj_content">리뷰 등록일 : ${mr.mr_reg_date }</p>
							        </div>
							    </c:if>
							</c:forEach>
	                 	</c:if>
	                 	<c:if test="${empty mateReviewList}">
  							<div class="mp_content"><p>등록된 리뷰가 없습니다.</p></div>
	                 	</c:if>
	                 </c:if>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>
