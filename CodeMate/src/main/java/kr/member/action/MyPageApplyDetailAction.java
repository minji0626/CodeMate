package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.consult.dao.ConsultDAO;
import kr.consult.vo.ConsultVO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class MyPageApplyDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		if(mem_num==null) {
			return "redirect:/member/loginForm.do";
		}
		//로그인이 된 경우
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.getMember(mem_num);
		
		int ra_num = Integer.parseInt(request.getParameter("ra_num"));
		
		//나의 신청목록
		ConsultDAO cdao = ConsultDAO.getInstance();
		ConsultVO consult = cdao.getConsult(ra_num);
		
		request.setAttribute("member", member);
		request.setAttribute("consult", consult);
		//JSP 경로 반환
		return "/WEB-INF/views/member/myPageConsultDetail.jsp";
	}

}
