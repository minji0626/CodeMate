package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RboardVO;
import kr.team.dao.TeamDAO;
import kr.team.vo.TeamVO;
import kr.tmember.dao.TmemberDAO;
import kr.tmember.vo.TmemberVO;

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
		 
		//List 반환
		RboardDAO rboardDAO = RboardDAO.getInstance();
		List<RboardVO> rboardList = rboardDAO.getTeamList(mem_num);
		
		request.setAttribute("member", member);
		request.setAttribute("rboardList", rboardList);
		
		TmemberDAO tdao = TmemberDAO.getInstance();
		TmemberVO tmember = tdao.get
		
		//JSP 경로 반환
		return "/WEB-INF/views/member/myTeam.jsp";
	}

}
