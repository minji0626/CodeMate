package kr.tmember.vo;

import java.sql.Date;

public class MateReviewVO {
	private int mr_num;
	private int mr_writer;
	private int mr_receiver;
	private Date mr_reg_date;
	private String mr_content;
	public int getMr_num() {
		return mr_num;
	}
	public void setMr_num(int mr_num) {
		this.mr_num = mr_num;
	}
	public int getMr_writer() {
		return mr_writer;
	}
	public void setMr_writer(int mr_writer) {
		this.mr_writer = mr_writer;
	}
	public int getMr_receiver() {
		return mr_receiver;
	}
	public void setMr_receiver(int mr_receiver) {
		this.mr_receiver = mr_receiver;
	}
	public Date getMr_reg_date() {
		return mr_reg_date;
	}
	public void setMr_reg_date(Date mr_reg_date) {
		this.mr_reg_date = mr_reg_date;
	}
	public String getMr_content() {
		return mr_content;
	}
	public void setMr_content(String mr_content) {
		this.mr_content = mr_content;
	}
	
	
}
