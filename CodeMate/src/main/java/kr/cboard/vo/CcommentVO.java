package kr.cboard.vo;

import java.sql.Date;

public class CcommentVO {
	private int cc_num;
	private int mem_num;
	private int cb_num;
	private String cc_content;
	private Date cc_reg_date;
	private Date cc_modify_date;
	
	private String mem_photo;
	private String mem_nickname;
	
	
	public String getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(String mem_photo) {
		this.mem_photo = mem_photo;
	}
	public String getMem_nickname() {
		return mem_nickname;
	}
	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}
	public int getCc_num() {
		return cc_num;
	}
	public void setCc_num(int cc_num) {
		this.cc_num = cc_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getCb_num() {
		return cb_num;
	}
	public void setCb_num(int cb_num) {
		this.cb_num = cb_num;
	}
	public String getCc_content() {
		return cc_content;
	}
	public void setCc_content(String cc_content) {
		this.cc_content = cc_content;
	}
	public Date getCc_reg_date() {
		return cc_reg_date;
	}
	public void setCc_reg_date(Date cc_reg_date) {
		this.cc_reg_date = cc_reg_date;
	}
	public Date getCc_modify_date() {
		return cc_modify_date;
	}
	public void setCc_modify_date(Date cc_modify_date) {
		this.cc_modify_date = cc_modify_date;
	}
	
	
}
