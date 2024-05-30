package kr.rboard.vo;

import java.sql.Date;
import java.util.List;

import kr.db.vo.FieldVO;
import kr.db.vo.HardSkillVO;

public class RboardVO {
	private int rb_num;			//모집글 번호
	private int mem_num;		//글 작성 회원 번호
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
	private int rb_hit;
	
	private String[] r_skills;	//요구기술 list
    private String[] r_fields;	//모집필드 list
	
    //요구기술, 모집필드 이름 및 사진 변수 배열
	private String[] hs_name_arr;
	private String[] hs_photo_arr;
	private String[] f_name_arr;
	
	private String mem_nickname;
	private String mem_photo;
	
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
	
	
}
