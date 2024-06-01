package kr.team.vo;

public class TeamToDoVO {
	private int tt_num;				// 팀 투두 번호
	private int team_num;			// 팀 번호
	private String tt_content;		// 팀 투두 내용
	private String tt_date;			// 팀 투두 등록일
	private String tt_start; 			// 팀 투두 시작 시간
	private String tt_end; 			// 팀 투두 끝나는 시간
	private int tt_state; 			// 팀 투두 상태
	
	
	public String getTt_start() {
		return tt_start;
	}
	public void setTt_start(String tt_start) {
		this.tt_start = tt_start;
	}
	public String getTt_end() {
		return tt_end;
	}
	public void setTt_end(String tt_end) {
		this.tt_end = tt_end;
	}
	public String getTt_date() {
		return tt_date;
	}
	public void setTt_date(String tt_date) {
		this.tt_date = tt_date;
	}
	public int getTt_num() {
		return tt_num;
	}
	public void setTt_num(int tt_num) {
		this.tt_num = tt_num;
	}
	public int getTeam_num() {
		return team_num;
	}
	public void setTeam_num(int team_num) {
		this.team_num = team_num;
	}
	public String getTt_content() {
		return tt_content;
	}
	public void setTt_content(String tt_content) {
		this.tt_content = tt_content;
	}
	
	public int getTt_state() {
		return tt_state;
	}
	public void setTt_state(int tt_state) {
		this.tt_state = tt_state;
	}
	
	
}
