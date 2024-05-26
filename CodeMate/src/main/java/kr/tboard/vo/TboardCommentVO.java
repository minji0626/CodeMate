package kr.tboard.vo;

import java.sql.Date;

public class TboardCommentVO {
	private int tc_num;			// 팀 게시글 댓글 번호
	private int tb_num;			// 팀 게시글 번호
	private int mem_num;		// 회원 번호
	private String tc_content;	// 팀 게시글 댓글 내용
	private Date tc_reg_date;	// 팀 게시글 댓글 등록일
	private Date tc_modify_date; // 팀 게시글  댓글 수정일
	
	public int getTc_num() {
		return tc_num;
	}
	public void setTc_num(int tc_num) {
		this.tc_num = tc_num;
	}
	public int getTb_num() {
		return tb_num;
	}
	public void setTb_num(int tb_num) {
		this.tb_num = tb_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getTc_content() {
		return tc_content;
	}
	public void setTc_content(String tc_content) {
		this.tc_content = tc_content;
	}
	public Date getTc_reg_date() {
		return tc_reg_date;
	}
	public void setTc_reg_date(Date tc_reg_date) {
		this.tc_reg_date = tc_reg_date;
	}
	public Date getTc_modify_date() {
		return tc_modify_date;
	}
	public void setTc_modify_date(Date tc_modify_date) {
		this.tc_modify_date = tc_modify_date;
	}
	
	
	
}
