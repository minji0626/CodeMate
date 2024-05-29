package kr.tboard.vo;

import java.sql.Date;

public class TboardVO {
		private int tb_num;			// 팀 게시판 글 번호
		private int team_num;		// 팀 번호
		private int mem_num;		// 회원 번호
		private String tb_title;	// 팀 게시글 제목
		private String tb_content;	// 팀 게시글 내용
		private Date tb_reg_date;	// 팀 게시글 등록일
		private Date tb_modify_date; // 팀 게시글 수정일
		private String tb_file;		// 팀 게시글 파일명
		private int tb_auth;		// 팀 게시글 공지사항(0:일반 글, 1: 공지사항 default는 0)
		
		private String mem_id;
		private String mem_photo;
		
		public String getMem_photo() {
			return mem_photo;
		}

		public void setMem_photo(String mem_photo) {
			this.mem_photo = mem_photo;
		}

		public int getTb_num() {
			return tb_num;
		}

		public void setTb_num(int tb_num) {
			this.tb_num = tb_num;
		}

		public int getTeam_num() {
			return team_num;
		}

		public void setTeam_num(int team_num) {
			this.team_num = team_num;
		}

		public int getMem_num() {
			return mem_num;
		}

		public void setMem_num(int mem_num) {
			this.mem_num = mem_num;
		}

		public String getTb_title() {
			return tb_title;
		}

		public void setTb_title(String tb_title) {
			this.tb_title = tb_title;
		}

		public String getTb_content() {
			return tb_content;
		}

		public void setTb_content(String tb_content) {
			this.tb_content = tb_content;
		}

		public Date getTb_reg_date() {
			return tb_reg_date;
		}

		public void setTb_reg_date(Date tb_reg_date) {
			this.tb_reg_date = tb_reg_date;
		}

		public Date getTb_modify_date() {
			return tb_modify_date;
		}

		public void setTb_modify_date(Date tb_modify_date) {
			this.tb_modify_date = tb_modify_date;
		}

		public String getTb_file() {
			return tb_file;
		}

		public void setTb_file(String tb_file) {
			this.tb_file = tb_file;
		}

		public int getTb_auth() {
			return tb_auth;
		}

		public void setTb_auth(int tb_auth) {
			this.tb_auth = tb_auth;
		}

		public String getMem_id() {
			return mem_id;
		}

		public void setMem_id(String mem_id) {
			this.mem_id = mem_id;
		}
		
		
		
}
