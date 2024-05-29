 package kr.mate.action;

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
        // 사용자가 클릭한 팀 번호 가져오기
        int team_num = Integer.parseInt(request.getParameter("team_num"));
        // 가져온 팀 정보를 세션에 저장
        session.setAttribute("team_num", team_num);
        // 세션에 저장이 되는지 확인해봤음, myTeam의 주소 바꿔야함
        System.out.println(team_num);
        // 팀의 홈페이지로 이동
		return "/WEB-INF/views/team/teamTo_Do.jsp";
	}

}