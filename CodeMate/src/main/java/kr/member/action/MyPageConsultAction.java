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

public class MyPageConsultAction implements Action{

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
		
		//나의 문의 목록 리스트
		ConsultDAO cdao = ConsultDAO.getInstance();
		List<ConsultVO> consultList = cdao.getConsultListByMemNum(mem_num);
		
		request.setAttribute("member", member);
		request.setAttribute("consultList", consultList);
		
		return "/WEB-INF/views/member/myPageConsult.jsp";
	}

}
