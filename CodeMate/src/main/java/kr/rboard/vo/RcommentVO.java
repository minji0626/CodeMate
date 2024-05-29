package kr.rboard.vo;

import java.sql.Date;

public class RcommentVO {
	private int rc_num;
	private int mem_num;
	private int rb_num;
	private String rc_content;
	private Date rc_reg_date;
	private Date rc_modify_date;
	
	private String mem_photo;		//member_detail에서 불러옴
	private String mem_nickname;	//member_detail에서 불러옴
	
	
	public int getRc_num() {
		return rc_num;
	}
	public void setRc_num(int rc_num) {
		this.rc_num = rc_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getRb_num() {
		return rb_num;
	}
	public void setRb_num(int rb_num) {
		this.rb_num = rb_num;
	}
	public String getRc_content() {
		return rc_content;
	}
	public void setRc_content(String rc_content) {
		this.rc_content = rc_content;
	}
	public Date getRc_reg_date() {
		return rc_reg_date;
	}
	public void setRc_reg_date(Date rc_reg_date) {
		this.rc_reg_date = rc_reg_date;
	}
	public Date getRc_modify_date() {
		return rc_modify_date;
	}
	public void setRc_modify_date(Date rc_modify_date) {
		this.rc_modify_date = rc_modify_date;
	}
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
	
	
}
