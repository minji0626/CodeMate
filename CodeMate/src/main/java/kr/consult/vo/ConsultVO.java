package kr.consult.vo;

import java.sql.Date;

public class ConsultVO {
	private int cs_num;
	private int mem_num;
	private String cs_title;
	private String cs_content;
	private Date cs_reg_date;
	private Date cs_confirmed_date;
	private int cs_confirmed;
	private int cs_category;
	
	public int getCs_num() {
		return cs_num;
	}
	public void setCs_num(int cs_num) {
		this.cs_num = cs_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getCs_title() {
		return cs_title;
	}
	public void setCs_title(String cs_title) {
		this.cs_title = cs_title;
	}
	public String getCs_content() {
		return cs_content;
	}
	public void setCs_content(String cs_content) {
		this.cs_content = cs_content;
	}
	public Date getCs_reg_date() {
		return cs_reg_date;
	}
	public void setCs_reg_date(Date cs_reg_date) {
		this.cs_reg_date = cs_reg_date;
	}
	public Date getCs_confirmed_date() {
		return cs_confirmed_date;
	}
	public void setCs_confirmed_date(Date cs_confirmed_date) {
		this.cs_confirmed_date = cs_confirmed_date;
	}
	public int getCs_confirmed() {
		return cs_confirmed;
	}
	public void setCs_confirmed(int cs_confirmed) {
		this.cs_confirmed = cs_confirmed;
	}
	public int getCs_category() {
		return cs_category;
	}
	public void setCs_category(int cs_category) {
		this.cs_category = cs_category;
	}
	
	
}
