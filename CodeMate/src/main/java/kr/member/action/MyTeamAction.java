package kr.member.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;


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
		request.setAttribute("member", member);
		
		//모집 게시물 정보
		//RboardDAO rboardDAO = RboardDAO.getInstance();
		//List 반환
		//List<RboardVO> rboardList = rboardDAO.get

		//request.setAttribute("rboardList", rboardList);
		
		//TmemberDAO tdao = TmemberDAO.getInstance();
		//TmemberVO tmember = tdao.getTmember(mem_num);
		
		//request.setAttribute("tmember", tmember);
		
		//JSP 경로 반환
		return "/WEB-INF/views/member/myTeam.jsp";
	}

}
