 package kr.team.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.team.dao.TeamDAO;
import kr.team.vo.TeamVO;

public class TeamMainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		if(mem_num == null) {// 로그인 미실시
			return "redirect:/member/loginForm.do";
		}

		
		return "/WEB-INF/views/team/teamTo_Do.jsp";
	}

}