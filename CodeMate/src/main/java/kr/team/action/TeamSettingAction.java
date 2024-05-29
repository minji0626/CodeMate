package kr.team.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.tmember.dao.TmemberDAO;
import kr.tmember.vo.TmemberVO;

public class TeamSettingAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		int team_num = Integer.parseInt(request.getParameter("team_num"));
		
		if(mem_num == null) {// 로그인 미실시
			return "redirect:/member/loginForm.do";
		}
		request.setCharacterEncoding("utf-8");
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
