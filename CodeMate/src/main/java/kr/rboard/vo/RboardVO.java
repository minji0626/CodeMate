package kr.rboard.vo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import kr.db.vo.FieldVO;
import kr.db.vo.HardSkillVO;

public class RboardVO {
	private int rb_num; // 모집글 번호
	private int mem_num; // 글 작성 회원 번호
	private Date rb_reg_date; // 모집글 등록일
	private int rb_category; // 모집글 분류 (스터디/프로젝트)
	private int rb_meet; // 진행방식(0온라인/1오프라인/2온라인+오프라인)
	private int rb_teamsize; // 모집인원
	private int rb_period; // 예정 진행기간
	private String rb_start; // 예정 시작일
	private String rb_title; // 모집글 제목
	private String rb_content; // 모집글 내용
	private String rb_endRecruit; // 모집 마감일
	private String rb_pj_title; // 프로젝트명
	private int rb_hit; // 조회수
	private int rb_apply_count; // 신청자수
	private long daysLeft; // 마감까지 남은 날짜

	private String[] r_skills; // 요구기술 list
	private String[] r_fields; // 모집필드 list

	// 요구기술, 모집필드 이름 및 사진 변수 배열
	private String[] hs_name_arr;
	private String[] hs_photo_arr;
	private String[] f_name_arr;

	// 댓글에서 띄울 프로필
	private String mem_nickname;
	private String mem_photo;

	// 신청내역에서 띄울 ra_num
	private int ra_num;

	private int ra_pass;
	private String ra_content;
	private Date ra_date;

	public String getRa_content() {
		return ra_content;
	}

	public void setRa_content(String ra_content) {
		this.ra_content = ra_content;
	}

	public Date getRa_date() {
		return ra_date;
	}

	public void setRa_date(Date ra_date) {
		this.ra_date = ra_date;
	}

	private int team_num;
	private int team_status;
	
	private int apply_count;
	private int pass_count;

	public int getPass_count() {
		return pass_count;
	}

	public void setPass_count(int pass_count) {
		this.pass_count = pass_count;
	}

	public int getApply_count() {
		return apply_count;
	}

	public void setApply_count(int apply_count) {
		this.apply_count = apply_count;
	}

	// 남은 날짜 계산 및 필드 설정 메서드
	public void calculateDaysLeft() {
		// 현재 날짜
		LocalDate today = LocalDate.now();

		// 모집 마감일 날짜 파싱
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate endRecruit = LocalDate.parse(rb_endRecruit, formatter);

		// 일수 차이 계산
		long daysLeft = ChronoUnit.DAYS.between(today, endRecruit);

		// 필드에 저장
		this.daysLeft = daysLeft;
	}

	public long getDaysLeft() {
		return daysLeft;
	}

	public void setDaysLeft(long daysLeft) {
		this.daysLeft = daysLeft;
	}

	public int getTeam_num() {
		return team_num;
	}

	public void setTeam_num(int team_num) {
		this.team_num = team_num;
	}

	public int getTeam_status() {
		return team_status;
	}

	public void setTeam_status(int team_status) {
		this.team_status = team_status;
	}

	public int getRa_pass() {
		return ra_pass;
	}

	public void setRa_pass(int ra_pass) {
		this.ra_pass = ra_pass;
	}

	public int getRb_num() {
		return rb_num;
	}

	public void setRb_num(int rb_num) {
		this.rb_num = rb_num;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public Date getRb_reg_date() {
		return rb_reg_date;
	}

	public void setRb_reg_date(Date rb_reg_date) {
		this.rb_reg_date = rb_reg_date;
	}

	public int getRb_category() {
		return rb_category;
	}

	public void setRb_category(int rb_category) {
		this.rb_category = rb_category;
	}

	public int getRb_meet() {
		return rb_meet;
	}

	public void setRb_meet(int rb_meet) {
		this.rb_meet = rb_meet;
	}

	public int getRb_teamsize() {
		return rb_teamsize;
	}

	public void setRb_teamsize(int rb_teamsize) {
		this.rb_teamsize = rb_teamsize;
	}

	public int getRb_period() {
		return rb_period;
	}

	public void setRb_period(int rb_period) {
		this.rb_period = rb_period;
	}

	public String getRb_start() {
		return rb_start;
	}

	public void setRb_start(String rb_start) {
		this.rb_start = rb_start;
	}

	public String getRb_title() {
		return rb_title;
	}

	public void setRb_title(String rb_title) {
		this.rb_title = rb_title;
	}

	public String getRb_content() {
		return rb_content;
	}

	public void setRb_content(String rb_content) {
		this.rb_content = rb_content;
	}

	public String getRb_endRecruit() {
		return rb_endRecruit;
	}

	public void setRb_endRecruit(String rb_endRecruit) {
		this.rb_endRecruit = rb_endRecruit;
		calculateDaysLeft();
	}	

	public String getRb_pj_title() {
		return rb_pj_title;
	}

	public void setRb_pj_title(String rb_pj_title) {
		this.rb_pj_title = rb_pj_title;
	}

	public String[] getR_skills() {
		return r_skills;
	}

	public void setR_skills(String[] r_skills) {
		this.r_skills = r_skills;
	}

	public String[] getR_fields() {
		return r_fields;
	}

	public void setR_fields(String[] r_fields) {
		this.r_fields = r_fields;
	}

	public String[] getHs_name_arr() {
		return hs_name_arr;
	}

	public void setHs_name_arr(String[] hs_name_arr) {
		this.hs_name_arr = hs_name_arr;
	}

	public String[] getHs_photo_arr() {
		return hs_photo_arr;
	}

	public void setHs_photo_arr(String[] hs_photo_arr) {
		this.hs_photo_arr = hs_photo_arr;
	}

	public String[] getF_name_arr() {
		return f_name_arr;
	}

	public void setF_name_arr(String[] f_name_arr) {
		this.f_name_arr = f_name_arr;
	}

	public String getMem_nickname() {
		return mem_nickname;
	}

	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}

	public String getMem_photo() {
		return mem_photo;
	}

	public void setMem_photo(String mem_photo) {
		this.mem_photo = mem_photo;
	}

	public int getRb_hit() {
		return rb_hit;
	}

	public void setRb_hit(int rb_hit) {
		this.rb_hit = rb_hit;
	}

	public int getRb_apply_count() {
		return rb_apply_count;
	}

	public void setRb_apply_count(int rb_apply_count) {
		this.rb_apply_count = rb_apply_count;
	}

	public int getRa_num() {
		return ra_num;
	}

	public void setRa_num(int ra_num) {
		this.ra_num = ra_num;
	}

}
