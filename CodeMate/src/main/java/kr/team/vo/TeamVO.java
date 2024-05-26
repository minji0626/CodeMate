package kr.team.vo;

public class TeamVO {
		private int team_num;		// 팀 번호
		// 팀 상태 0: 비활성화, 1: 활성화(팀 인원 수가 다 찼을 경우 자동 활성화 / 다 안 찼는데 팀 모집자가 활성화 버튼을 누른다면 누른 시간 전까지의 승인 인원만 포함), 2: 완료
		private int team_status;
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
