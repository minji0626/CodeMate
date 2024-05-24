<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>코메 구하기</title>
<link rel="stylesheet" href="../css/share.css" type="text/css">
</head>
<body>
	<div class="page-main">
		<div class="align-center">
			<!--헤더-->
			코드메이트
		</div>
		<div class="content-main">
			<form id="rboard_insert_form" action="" method="post">
				<!--프로젝트 정보-->
				<h3>1.프로젝트 기본 정보를 입력해주세요.</h3>
				<ul>
					<li><label for="rb_category">모집 구분</label> <select
						name="rb_category" id="rb_category" required>
							<option value="" disabled selected>스터디/프로젝트</option>
							<option value="0">스터디</option>
							<option value="1">프로젝트</option>
					</select></li>
					<li><label for="rb_period">모집 인원</label> <select
						name="rb_period" id="rb_period" required>
							<option value="" disabled selected>인원 미정~10명 이상</option>
							<option value="0">인원 미정</option>
							<option value="1">1명</option>
							<option value="2">2명</option>
							<option value="3">3명</option>
							<option value="4">4명</option>
							<option value="5">5명</option>
							<option value="6">6명</option>
							<option value="7">7명</option>
							<option value="8">8명</option>
							<option value="9">9명</option>
							<option value="10">10명 이상</option>
					</select></li>
					<li><label for="rb_meet">진행 방식</label> <select name="meet"
						id="meet" required>
							<option value="" disabled selected>스터디/프로젝트</option>
							<option value="0">온라인</option>
							<option value="1">오프라인</option>
					</select></li>
					<li><label for="rb_start">시작 예정일</label> <input type="date"
						name="rb_start" id="rb_start"></li>
					<li><label for="rb_period">진행 기간</label> <select
						name="rb_period" id="rb_period" required>
							<option value="" disabled selected>1개월 이내~6개월 이상</option>
							<option value="0">1개월 미만</option>
							<option value="1">1개월</option>
							<option value="2">2개월</option>
							<option value="3">3개월</option>
							<option value="4">4개월</option>
							<option value="5">5개월</option>
							<option value="6">6개월 이상</option>
					</select></li>
					<li><label for="r_skill">요구 기술</label> <input type="checkbox"
						name="r_skill" id="java" value="java"> <label for="java">Java</label>
						<input type="checkbox" name="r_skill" id="javascript"
						value="javascript"> <label for="javascript">Javascript</label>
						<input type="checkbox" name="r_skill" id="oracle" value="oracle">
						<label for="oracle">Oracle</label></li>
					<li><label for="r_field">모집 필드</label> <input type="checkbox"
						name="r_field" id="frontend" value="frontend"> <label
						for="frontend">프론트엔드</label> <input type="checkbox" name="r_field"
						id="backend" value="backend"> <label for="backend">백엔드</label>
						<input type="checkbox" name="r_field" id="devops" value="devops">
						<label for="devops">데브옵스</label></li>
					<li><label for="rb_pj_title">프로젝트 제목</label> <input
						type="text" name="rb_pj_title" id="rb_pj_title"
						placeholder="프로젝트의 제목을 입력해주세요"></li>
				</ul>
				<!--프로젝트 소개-->
				<h3>2.프로젝트에 대해 소개해주세요.</h3>
				<ul>
					<li><label for="rb_title">제목</label> <input type="text"
						id="rb_title" name="rb_title" placeholder="글 제목을 입력해주세요">
					</li>
					<li><textarea name="rb_content" id="rb_content" rows="30"
							cols="150" placeholder="프로젝트에 대해 소개해주세요"></textarea></li>
				</ul>
				<!--버튼-->
				<div class="align-right">
					<input type="button" value="취소"> <input type="submit"
						value="글 등록">
				</div>

			</form>
		</div>
	</div>
</body>
</html>

<!--자바스크립트 작성 완료 후 전체 input required 넣기-->