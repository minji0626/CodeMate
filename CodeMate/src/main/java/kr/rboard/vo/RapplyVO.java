package kr.rboard.vo;

import java.sql.Date;

public class RapplyVO {
	private int ra_num;
	private int rb_num;
	private int mem_num;
	private String ra_content;
	private Date ra_date;
	private int ra_pass; //0:불합격, 1:합격 (nullable)

	private Date rb_reg_date;		//모집글 등록일
	private int rb_category;	//모집글 분류 (스터디/프로젝트)
	private int rb_meet;		//진행방식(0온라인/1오프라인/2온라인+오프라인)
	private int rb_teamsize;	//모집인원
	private int rb_period;		//예정 진행기간
	private String rb_start;	//예정 시작일
	private String rb_title;	//모집글 제목
	private String rb_content;	//모집글 내용
	private String rb_endRecruit; //모집 마감일
	private String rb_pj_title;	//프로젝트명
	private int rb_hit;			//조회수
	private int rb_apply_count; //신청자수

	//member_detail조인해서 사용	
	private String mem_name;
	private String mem_nickname;
	private String mem_passwd;
	private String mem_phone;
	private String mem_email;
	private int mem_level; 
	private Date mem_reg_date;
	private Date mem_modify_date;
	private String mem_photo;

	private int team_num;		// 팀 번호
	// 팀 상태 0: 비활성화, 1: 활성화(팀 인원 수가 다 찼을 경우 자동 활성화 / 다 안 찼는데 팀 모집자가 활성화 버튼을 누른다면 누른 시간 전까지의 승인 인원만 포함), 2: 완료
	private int team_status;
	
	
	private int tm_auth;
	private int tm_review_status;
	
	
	
	public int getTm_auth() {
		return tm_auth;
	}
	public void setTm_auth(int tm_auth) {
		this.tm_auth = tm_auth;
	}
	public int getTm_review_status() {
		return tm_review_status;
	}
	public void setTm_review_status(int tm_review_status) {
		this.tm_review_status = tm_review_status;
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
	}
	public String getRb_pj_title() {
		return rb_pj_title;
	}
	public void setRb_pj_title(String rb_pj_title) {
		this.rb_pj_title = rb_pj_title;
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
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_nickname() {
		return mem_nickname;
	}
	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}
	public String getMem_passwd() {
		return mem_passwd;
	}
	public void setMem_passwd(String mem_passwd) {
		this.mem_passwd = mem_passwd;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public int getMem_level() {
		return mem_level;
	}
	public void setMem_level(int mem_level) {
		this.mem_level = mem_level;
	}
	public Date getMem_reg_date() {
		return mem_reg_date;
	}
	public void setMem_reg_date(Date mem_reg_date) {
		this.mem_reg_date = mem_reg_date;
	}
	public Date getMem_modify_date() {
		return mem_modify_date;
	}
	public void setMem_modify_date(Date mem_modify_date) {
		this.mem_modify_date = mem_modify_date;
	}
	public String getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(String mem_photo) {
		this.mem_photo = mem_photo;
	}
	public int getRa_num() {
		return ra_num;
	}
	public void setRa_num(int ra_num) {
		this.ra_num = ra_num;
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
	public int getRa_pass() {
		return ra_pass;
	}
	public void setRa_pass(int ra_pass) {
		this.ra_pass = ra_pass;
	}


}
