package kr.cboard.vo;


public class CcommentVO {
	private int cc_num;
	private int mem_num;
	private int cb_num;
	private String cc_content;
	private String cc_reg_date;
	private String cc_modify_date;
	
	private String mem_photo;
	private String mem_nickname;
	

	private int cb_type;
	private String cb_title;

	public String getCb_title() {
		return cb_title;
	}
	public void setCb_title(String cb_title) {
		this.cb_title = cb_title;
	}
	public String getCc_reg_date() {
		return cc_reg_date;
	}
	public void setCc_reg_date(String cc_reg_date) {
		this.cc_reg_date = cc_reg_date;
	}
	public String getCc_modify_date() {
		return cc_modify_date;
	}
	public void setCc_modify_date(String cc_modify_date) {
		this.cc_modify_date = cc_modify_date;
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
	
	public int getCb_type() {
		return cb_type;
	}
	public void setCb_type(int cb_type) {
		this.cb_type = cb_type;
	}
	
}
