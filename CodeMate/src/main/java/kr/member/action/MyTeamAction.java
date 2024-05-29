package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.team.dao.TeamDAO;
import kr.team.vo.TeamVO;

public class MyTeamAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//세션에 로그인된 상태로 있어야됨
		HttpSession session = request.getSession();
		//로그인 체크
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(mem_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.getMember(mem_num);
		
		//내가 참여중인
		TeamDAO teamDAO = TeamDAO.getInstance();
		//List 반환
		int team_num = Integer.parseInt(request.getParameter("team_num"));
		List<TeamVO> teamList = teamDAO.getTeamListByMemNum(mem_num,team_num);
		
		request.setAttribute("member", member);
		request.setAttribute("teamList", teamList);
		
		//JSP 경로 반환
		return "/WEB-INF/views/member/myTeam.jsp";
	}

}
