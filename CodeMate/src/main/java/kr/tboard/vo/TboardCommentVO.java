package kr.tboard.vo;


public class TboardCommentVO {
	private int tc_num;			// 팀 게시글 댓글 번호
	private int tb_num;			// 팀 게시글 번호
	private int mem_num;		// 회원 번호
	private String tc_content;	// 팀 게시글 댓글 내용
	private String tc_reg_date;	// 팀 게시글 댓글 등록일
	private String tc_modify_date; // 팀 게시글  댓글 수정일
	
	private String mem_nickname;
	private int team_num;
	private String mem_photo;
	
	
	
	public String getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(String mem_photo) {
		this.mem_photo = mem_photo;
	}
	public int getTeam_num() {
		return team_num;
	}
	public void setTeam_num(int team_num) {
		this.team_num = team_num;
	}
	public String getMem_nickname() {
		return mem_nickname;
	}
	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}
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
	public String getTc_reg_date() {
		return tc_reg_date;
	}
	public void setTc_reg_date(String tc_reg_date) {
		this.tc_reg_date = tc_reg_date;
	}
	public String getTc_modify_date() {
		return tc_modify_date;
	}
	public void setTc_modify_date(String tc_modify_date) {
		this.tc_modify_date = tc_modify_date;
	}
	
	
	
	
}
