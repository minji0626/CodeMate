package kr.tmember.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.TeamDAO;
import kr.member.vo.TeamVO;
import kr.tmember.dao.TmemberDAO;
import kr.tmember.vo.TmemberVO;

public class TeamSettingAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		TeamDAO tdao = TeamDAO.getInstance();
		Integer team_num = (Integer)session.getAttribute("team_num");
		
		int team_num2 = Integer.parseInt(request.getParameter("team_num"));
		
		
		if(mem_num == null) {// 로그인 미실시
			return "redirect:/member/loginForm.do";
		}
		
		
		TeamVO teams = tdao.getTeam(team_num2);
		if (teams == null || teams.getTeam_status() == 0) {
		    // 팀이 존재하지 않는 경우
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script type=\"text/javascript\">");
		    out.println("var contextPath = '" + request.getContextPath() + "';");
		    out.println("alert('팀이 존재하지 않거나 비활성화 팀입니다. 참여중인 팀 페이지로 돌아갑니다.');");
		    out.println("window.location.href = contextPath + '/member/myTeam.do';");
		    out.println("</script>");
		    return null;
		}
		
		TeamVO teammember = tdao.getTeamMember(mem_num, team_num2);
	        if(teammember == null) {
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
		int count = dao.getTmemberCount(team_num);
		
		List<TmemberVO> list = null;
		if(count > 0) {
			list = dao.getTeamMembers(team_num);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("team_num", team_num);
		request.setAttribute("list", list);
		
		// 팀 설정
		return "/WEB-INF/views/team/teamSetting.jsp";
	}

}
