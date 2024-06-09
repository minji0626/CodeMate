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

public class MyPageMessageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(mem_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.getMember(mem_num);
		
		//나의 쪽저 정보
		//RboardDAO rboardDAO = RboardDAO.getInstance(); 
		//List 반환
		//List<RboardVO> rboardList = rboardDAO.getBookMarkBoardListByMemNum(mem_num);
		request.setAttribute("member", member);
		//request.setAttribute(null, dao);
		
		//JSP경로 반환
		return "/WEB-INF/views/member/myPageMessage.jsp";
	}

}
