package kr.tmember.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.TeamDAO;
import kr.member.vo.TeamVO;
import kr.tmember.dao.TmemberDAO;

public class ProjectDoneAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		int team_num = Integer.parseInt(request.getParameter("team_num"));

		if (mem_num == null) {// 로그인 미실시
			return "redirect:/member/loginForm.do";
		}

		TeamDAO tdao = TeamDAO.getInstance();
		TeamVO teammember = tdao.getTeamMember(mem_num, team_num);
		if (teammember == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("var contextPath = '" + request.getContextPath() + "';");
			out.println("alert('잘못된 접근입니다. 참여중인 팀으로 돌아갑니다.');");
			out.println("window.location.href = contextPath + '/member/myTeam.do';");
			out.println("</script>");

			return null;
		}

		TmemberDAO dao = TmemberDAO.getInstance();		
		dao.projectDone(team_num);
		
		request.setAttribute("notice_msg", "프로젝트가 종료되었습니다. 리뷰를 작성해주세요");
		request.setAttribute("notice_url", request.getContextPath() + "/team/teamSetting.do?team_num=" + team_num);

		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
