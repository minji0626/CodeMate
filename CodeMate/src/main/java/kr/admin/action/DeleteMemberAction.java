package kr.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.cboard.dao.CboardDAO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.member.vo.TeamVO;
import kr.rboard.dao.RboardDAO;
import kr.tboard.dao.TboardDAO;
import kr.tmember.dao.TmemberDAO;
import kr.tmember.vo.TmemberVO;
import kr.util.FileUtil;

public class DeleteMemberAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, String> mapAjax = new HashMap<String, String>();

		HttpSession session = request.getSession();
		// 로그인 체크
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		Integer mem_auth = (Integer) session.getAttribute("mem_auth");
		if (mem_num == null) {// 로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
			mapAjax.put("result_msg", "로그인 후 이용해주세요");
		} else if (mem_auth == 9) { // 관리자로 로그인 된 경우
			// 전송된 데이터 인코딩 타입 지정
			request.setCharacterEncoding("utf-8");

			MemberDAO dao = MemberDAO.getInstance();
			RboardDAO rdao = RboardDAO.getInstance();
			CboardDAO cdao = CboardDAO.getInstance();
			TmemberDAO tdao = TmemberDAO.getInstance();

			int db_mem_num = Integer.parseInt(request.getParameter("mem_num"));

			List<TeamVO> teamList = tdao.getUserActiveTeams(db_mem_num);

			if (teamList.size() > 0) { // 활성화된 팀이 있는 경우
				// 활성화된 팀에서 탈퇴시키기
				for (TeamVO team : teamList) {
					int team_num = team.getTeam_num();
					int team_leader = tdao.whoIsLeader(team_num);

					if (db_mem_num == team_leader) { // 팀장인 경우
						// 팀 멤버수 세기
						int tmember_count = tdao.getTmemberCount(team_num);
						if (tmember_count == 1) { //팀원이 한명일 때
							//팀원이 1명이면 그냥 팀 내용 삭제하고 team_status 3으로 변경
							tdao.deleteTeamContent(team_num);
							tdao.projectDone(team_num);
						} else { //팀원이 여러명일 때
							// 팀장위임하기
							// 새로운 팀장 teamnum
							int new_leader = tdao.pickNextLeader(team_num, db_mem_num);
							tdao.modifyTeamLeaderAndChangeMember(team_num, db_mem_num, new_leader);
							// 팀멤버 삭제
							tdao.deleteTeamMember(db_mem_num, team_num);
						}
						
					} else { // 팀원인 경우
						tdao.deleteTeamMember(db_mem_num, team_num);
					}
				}

			} // end of if

			// 팀에서 탈퇴 완료했을 때
			MemberVO db_vo = dao.getMember(db_mem_num);

			// 프로필 사진 삭제
			FileUtil.removeFile(request, db_vo.getMem_photo());
			// Rboard 삭제 -> 비활성화일 때만 삭제
			rdao.deleteUserRboard(db_mem_num);

			// Cboard 삭제
			cdao.deleteUserCboard(db_mem_num);
			// 회원정보 삭제
			dao.deleteMember(db_mem_num);

			// 삭제 성공했을 시
			mapAjax.put("result", "success");
			mapAjax.put("result_msg", "회원을 성공적으로 탈퇴시켰습니다.");
		} else { //관리자로 로그인되지 않은 경우
			mapAjax.put("result", "wrongAccess");
			mapAjax.put("result_msg", "접근 권한이 없습니다.");
		}

		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
