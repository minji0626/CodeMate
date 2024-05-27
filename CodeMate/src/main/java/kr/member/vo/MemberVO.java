package kr.member.vo;

import java.sql.Date;

public class MemberVO {
	private int mem_num;
	private String mem_id;
	private int mem_auth;
	private int mem_report;
	
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
	
	private String mp_position; // 메이트 프로필에서의 포지션
	private String mp_introduce; // 메이트 프로필에서의 자기소개
 	private Date mp_modify_date; // 메이트 프로필 수정일
	private int mp_state; // 메이트 프로필 공개 여부
	
	public String getMp_position() {
		return mp_position;
	}

	public void setMp_position(String mp_position) {
		this.mp_position = mp_position;
	}

	public String getMp_introduce() {
		return mp_introduce;
	}

	public void setMp_introduce(String mp_introduce) {
		this.mp_introduce = mp_introduce;
	}

	public Date getMp_modify_date() {
		return mp_modify_date;
	}

	public void setMp_modify_date(Date mp_modify_date) {
		this.mp_modify_date = mp_modify_date;
	}

	public int getMp_state() {
		return mp_state;
	}

	public void setMp_state(int mp_state) {
		this.mp_state = mp_state;
	}
	
	//비밀번호 일치 여부 체크
		public boolean isCheckedPassword(String userPasswd) {
				//회원등급(auth) : 0탈퇴,1정지 는 가입 불가 / (0:탈퇴,1:정지,2:일반,9:관리)
			if(mem_auth > 1 && mem_passwd.equals(userPasswd)) {
				return true;
			}
			return false;
		}
	
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getMem_auth() {
		return mem_auth;
	}
	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}
	public int getMem_report() {
		return mem_report;
	}
	public void setMem_report(int mem_report) {
		this.mem_report = mem_report;
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
	
	
	
	
	
	
}
