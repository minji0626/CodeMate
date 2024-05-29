package kr.mate.vo;

import java.sql.Date;

public class TeamVO {
		private int team_num;		// 팀 번호
		// 팀 상태 0: 비활성화, 1: 활성화(팀 인원 수가 다 찼을 경우 자동 활성화 / 다 안 찼는데 팀 모집자가 활성화 버튼을 누른다면 누른 시간 전까지의 승인 인원만 포함), 2: 완료
		private int team_status;
		
		private int mem_num;
		private int tm_auth;
		private int rb_num;
		private Date rb_reg_date;
		private int rb_category;
		private int rb_meet;
		private int rb_teamsize;
		private int rb_period;
		private String rb_start;
		private String rb_title;
		private String rb_content;
		private String rb_endrecruit;
		private String rb_pj_title;
		
		
		
		public int getMem_num() {
			return mem_num;
		}
		public void setMem_num(int mem_num) {
			this.mem_num = mem_num;
		}
		public int getTm_auth() {
			return tm_auth;
		}
		public void setTm_auth(int tm_auth) {
			this.tm_auth = tm_auth;
		}
		public int getRb_num() {
			return rb_num;
		}
		public void setRb_num(int rb_num) {
			this.rb_num = rb_num;
		}
		public Date getRb_reg_date() {
			return rb_reg_date;
		}
		public void setRb_reg_date(Date rb_reg_date) {
			this.rb_reg_date = rb_reg_date;
		}
		public int getRb_category() {
			return rb_category;
		}
		public void setRb_category(int rb_category) {
			this.rb_category = rb_category;
		}
		public int getRb_meet() {
			return rb_meet;
		}
		public void setRb_meet(int rb_meet) {
			this.rb_meet = rb_meet;
		}
		public int getRb_teamsize() {
			return rb_teamsize;
		}
		public void setRb_teamsize(int rb_teamsize) {
			this.rb_teamsize = rb_teamsize;
		}
		public int getRb_period() {
			return rb_period;
		}
		public void setRb_period(int rb_period) {
			this.rb_period = rb_period;
		}
		public String getRb_start() {
			return rb_start;
		}
		public void setRb_start(String rb_start) {
			this.rb_start = rb_start;
		}
		public String getRb_title() {
			return rb_title;
		}
		public void setRb_title(String rb_title) {
			this.rb_title = rb_title;
		}
		public String getRb_content() {
			return rb_content;
		}
		public void setRb_content(String rb_content) {
			this.rb_content = rb_content;
		}
		public String getRb_endrecruit() {
			return rb_endrecruit;
		}
		public void setRb_endrecruit(String rb_endrecruit) {
			this.rb_endrecruit = rb_endrecruit;
		}
		public String getRb_pj_title() {
			return rb_pj_title;
		}
		public void setRb_pj_title(String rb_pj_title) {
			this.rb_pj_title = rb_pj_title;
		}
		public int getTeam_num() {
			return team_num;
		}
		public void setTeam_num(int team_num) {
			this.team_num = team_num;
		}
		public int getTeam_status() {
			return team_status;
		}
		public void setTeam_status(int team_status) {
			this.team_status = team_status;
		}	
		
		
}
