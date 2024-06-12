package kr.cboard.vo;

import java.sql.Date;

public class CboardVO {
	private int cb_num;
	private int mem_num;
	private String cb_title;
	private String cb_content;
	private int cb_hit;
	private int cb_like;
	private Date cb_reg_date;
	private Date cb_modify_date;
	private String cb_file;
	private String cb_ip;
	private int cb_type;
	private int cboard_like;
	
	
	public int getCboard_like() {
		
		return cboard_like;
	}
	public void setCboard_like(int cboard_like) {
		this.cboard_like = cboard_like;
	}
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
	public int getCb_num() {
		return cb_num;
	}
	public void setCb_num(int cb_num) {
		this.cb_num = cb_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getCb_title() {
		return cb_title;
	}
	public void setCb_title(String cb_title) {
		this.cb_title = cb_title;
	}
	public String getCb_content() {
		return cb_content;
	}
	public void setCb_content(String cb_content) {
		this.cb_content = cb_content;
	}
	public int getCb_hit() {
		return cb_hit;
	}
	public void setCb_hit(int cb_hit) {
		this.cb_hit = cb_hit;
	}
	public int getCb_like() {
		return cb_like;
	}
	public void setCb_like(int cb_like) {
		this.cb_like = cb_like;
	}
	public Date getCb_reg_date() {
		return cb_reg_date;
	}
	public void setCb_reg_date(Date cb_reg_date) {
		this.cb_reg_date = cb_reg_date;
	}
	public Date getCb_modify_date() {
		return cb_modify_date;
	}
	public void setCb_modify_date(Date cb_modify_date) {
		this.cb_modify_date = cb_modify_date;
	}
	public String getCb_file() {
		return cb_file;
	}
	public void setCb_file(String cb_file) {
		this.cb_file = cb_file;
	}
	public String getCb_ip() {
		return cb_ip;
	}
	public void setCb_ip(String cb_ip) {
		this.cb_ip = cb_ip;
	}
	public int getCb_type() {
		return cb_type;
	}
	public void setCb_type(int cb_type) {
		this.cb_type = cb_type;
	}
	
	
	
}
