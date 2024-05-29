package kr.rboard.vo;

import java.sql.Date;

public class RapplyVO {
	private int ra_num;
	private int rb_num;
	private int mem_num;
	private String ra_content;
	private Date ra_date;
	private int ra_pass; //0:불합격, 1:합격 (nullable)
	
	
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
