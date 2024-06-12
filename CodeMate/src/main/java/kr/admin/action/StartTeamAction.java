package kr.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.TeamDAO;
import kr.rboard.dao.ApplyDAO;

public class StartTeamAction  implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//세션에 로그인된 상태로 있어야됨
		HttpSession session = request.getSession();
		// 로그인 체크
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		Integer mem_auth = (Integer) session.getAttribute("mem_auth");
		if (mem_num == null) {// 로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		} else if (mem_auth == 9) { //관리자로 로그인 된 경우
			int team_num = Integer.parseInt(request.getParameter("team_num"));
			TeamDAO tdao = TeamDAO.getInstance();
			
			ApplyDAO dao = ApplyDAO.getInstance();
			boolean check = dao.minimumTeamMember(team_num);
			if(check) {
				dao.teamActivation(team_num);
				request.setAttribute("notice_msg", "팀을 활성화시켰습니다.");
				request.setAttribute("notice_url", request.getContextPath() + "/admin/manageStopTeam.do");
				return "/WEB-INF/views/common/alert_view.jsp";
			} else {
				request.setAttribute("notice_msg", "팀원이 최소 한 명은 필요합니다.");
				request.setAttribute("notice_url", request.getContextPath() + "/admin/manageStopTeam.do");
				return "/WEB-INF/views/common/alert_view.jsp";
			}
			
		} else {
			request.setAttribute("notice_msg", "페이지 접근 권한이 없습니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		}

	}
}