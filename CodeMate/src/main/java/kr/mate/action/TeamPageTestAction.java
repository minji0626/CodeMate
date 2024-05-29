package kr.mate.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.mate.dao.TeamDAO;
import kr.mate.vo.TeamVO;

public class TeamPageTestAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 세션에 로그인된 상태로 있어야 됨
        HttpSession session = request.getSession();
        
        // 로그인 체크
        Integer mem_num = (Integer)session.getAttribute("mem_num");
        if(mem_num == null) { // 로그인이 되지 않은 경우
            return "redirect:/member/loginForm.do";
        }
        
        TeamDAO dao = TeamDAO.getInstance();
        List<TeamVO> team = dao.getTeamList(mem_num);
        
        request.setAttribute("team", team);
        
		return "/WEB-INF/views/mateProfile/teamPageTest.jsp";
	}
	
}
