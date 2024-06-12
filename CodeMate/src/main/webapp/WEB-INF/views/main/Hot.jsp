<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/cyy.css" type="text/css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
	rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<div class="page-main">
	<div id="mini-card1">
	
		<div class="w">
			<div class="explain">
				<b>이번주 코메 인기글</b>
			</div>
			<div id="back_btn" onclick="location.href='${pageContext.request.contextPath}/rboard/list.do'">
				팀원 구하기
			</div>
			<div class="go_more">
				<a class="more" href=${pageContext.request.contextPath}/rboard/list.do>더보기</a>
			</div>
		</div>
		
		<div class="mini-container1">
			<div class="wrapper1">
				<c:forEach var="rboard" items="${SlideList}">
					<div class="mini1">
						<div class="content1"
							onclick="location.href='${pageContext.request.contextPath}/rboard/detail.do?rb_num=${rboard.rb_num}'">
							<div class="link">
								<div class="rb_category_div">
									<!-- 카테고리 -->

									<c:if test="${rboard.rb_category == 0}">
									<div class="rb_category study">
									<img class="category_pic" src="${pageContext.request.contextPath}/images/study_w2.png" width="13" height="13">
										 스터디</div>
									</c:if>

									<c:if test="${rboard.rb_category == 1}">
									<div class="rb_category project">
									<img class="category_pic" src="${pageContext.request.contextPath}/images/project_w.png" width="13" height="13">
										 프로젝트</div>
									</c:if>
									<div class="endDate">
										<c:if test="${rboard.daysLeft > 0 && rboard.team_status != 1 && rboard.team_status != 3}"> <img src="${pageContext.request.contextPath}/images/alert.png" width="20" height="20">마감 ${rboard.daysLeft}일 전</c:if>
										<c:if test="${rboard.daysLeft == 0 && rboard.team_status != 1&& rboard.team_status != 3}">오늘 마감</c:if>
										<c:if test="${rboard.daysLeft < 0 || rboard.team_status == 1 || rboard.team_status == 3}">모집 종료</c:if>
									</div>
								</div>

								<!-- <hr size="1px" id="line1"> -->
								<p class="rb-title">${rboard.rb_title}</p>
								<!-- 제목 -->
								<div class="detail">
									<div class="way">
										<span class="proceed onoff">
											<c:if test="${rboard.rb_meet == 0}">
												온라인
											</c:if> 
											<c:if test="${rboard.rb_meet == 1}">
												오프라인
											</c:if> 
										<c:if test="${rboard.rb_meet == 2}">
									온라인/오프라인
									</c:if>
										</span>
									</div>	
										<div class="field_all">
											<c:forEach var="field" items="${rboard.f_name_arr}">
											<span class="mofield">${field}</span>
											</c:forEach>
										</div>
										<div class="apply_count_all">
											<span class="apply_count">신청인원 | ${rboard.rb_apply_count}/
											<c:if
													test="${rboard.rb_teamsize==0}">인원 미정</c:if> <c:if
													test="${rboard.rb_teamsize!=0 && rboard.rb_teamsize!=10}">${rboard.rb_teamsize}</c:if>
												<c:if test="${rboard.rb_teamsize==10}">10명 이상</c:if>
											</span>
										</div>
									

								</div>
							</div>
							<!-- end of link -->
						</div>
						<!-- end of content -->
					</div>
				</c:forEach>
			</div>
		</div>
		<!-- end of mini-container -->

		<button class="p1">
			<img src="${pageContext.request.contextPath}/images/left_arrow_1.png"
				alt="이전" width="20">
		</button>

		<button class="n1">
			<img
				src="${pageContext.request.contextPath}/images/right_arrow_1.png"
				alt="다음" width="20">
		</button>
	</div>

	<div id="mini-card2">
	
		<div class="w">
			<div class="explain"> <b>이번주 자유 게시판 인기글</b></div>
			<div id="back_btn1" onclick="location.href='${pageContext.request.contextPath}/cboard/community.do'">자유 게시판</div>
			<div class="go_more">
				<a class="more" href=${pageContext.request.contextPath}/cboard/community.do>더보기</a>
			</div>
		</div>

		<div class="mini-container2">
			<div class="wrapper2">
				<c:forEach var="cboard" items="${SlideList2}">
					<div class="mini2">
						<div class="content2" onclick="location.href='${pageContext.request.contextPath}/cboard/communityDetail.do?cb_num=${cboard.cb_num}'">
							<div class="link2">
								<div class="top">
									
									<div class="type"> 
										<img src="${pageContext.request.contextPath}/images/cje/freeBoardIcon.png" width="25px" id="free">
										<span class="board_type">자유 게시판</span>
									</div>
									
								</div>

								<div id="middle">
									<span class="title">${cboard.cb_title}</span>
								</div>

								<div class="detail2">
								
									<div id="below">
										<span class="b date">등록일 | ${cboard.cb_reg_date}</span>
									</div>

										<div class="cb_like">
										 <img id="good3" src="${pageContext.request.contextPath}/images/cje/yesLike.png">
										  ${cboard.cboard_like}
										 </div>
								</div>
								
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

		<!-- end of mini-container -->
		<button class="p2">
			<img src="${pageContext.request.contextPath}/images/left_arrow_1.png"
				alt="이전" width="20">
		</button>
		
		<button class="n2">
			<img
				src="${pageContext.request.contextPath}/images/right_arrow_1.png"
				alt="다음" width="20">
		</button>
	</div>
	<!-- end of mini-card2 -->
	
	<div id="mini-card3">
	
		<div class="w">
			<div class="explain"> <b>이번주 개발 게시판 인기글</b></div>
			<div id="back_btn1" onclick="location.href='${pageContext.request.contextPath}/cboard/community.do?cb_type=1'">개발 게시판</div>
			<div class="go_more">
				<a class="more" href=${pageContext.request.contextPath}/cboard/community.do?cb_type=1>더보기</a>
			</div>
		</div>

		<div class="mini-container3">
			<div class="wrapper3">
				<c:forEach var="cboard" items="${SlideList3}">
					<div class="mini3">
						<div class="content2" onclick="location.href='${pageContext.request.contextPath}/cboard/communityDetail.do?cb_num=${cboard.cb_num}'">
							<div class="link2">
							
								<div class="type">
										<img src="${pageContext.request.contextPath}/images/cje/codingBoardIcon.png" width="25px" id="coding"> 
										<span class="board_type">개발 게시판</span>
								</div>

								<div id="middle">
									<span class="title">${cboard.cb_title}</span>
								</div>

								<div class="detail2">
								
									<div id="below">
										<span class="b date">등록일 | ${cboard.cb_reg_date}</span>
									</div>

										<div class="cb_like">
										 <img id="good3" src="${pageContext.request.contextPath}/images/cje/yesLike.png">
										  ${cboard.cboard_like}
										 </div>
								</div>
								
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

		<!-- end of mini-container -->
		<button class="p3">
			<img src="${pageContext.request.contextPath}/images/left_arrow_1.png"
				alt="이전" width="20">
		</button>
		
		<button class="n3">
			<img
				src="${pageContext.request.contextPath}/images/right_arrow_1.png"
				alt="다음" width="20">
		</button>
	</div>
</div>
<script src="${pageContext.request.contextPath}/js/card.js"></script>