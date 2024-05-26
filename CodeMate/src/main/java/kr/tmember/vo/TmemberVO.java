package kr.tmember.vo;

public class TmemberVO {
	private int mem_num;			// 멤버 번호
	private int team_num;			// 팀 번호
	private int tm_auth;			// 팀 지위 (3 : 팀원 4: 팀장 (정지회원, 탈퇴회원은 팀테이블에서 삭제)
	private int tm_review_status;	// 0: 안 씀 1: 씀 (default 0) => 다 써야만 완료한 프로젝트 카운트
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getTeam_num() {
		return team_num;
	}
	public void setTeam_num(int team_num) {
		this.team_num = team_num;
	}
	public int getTm_auth() {
		return tm_auth;
	}
	public void setTm_auth(int tm_auth) {
		this.tm_auth = tm_auth;
	}
	public int getTm_review_status() {
		return tm_review_status;
	}
	public void setTm_review_status(int tm_review_status) {
		this.tm_review_status = tm_review_status;
	}
	
	
}
