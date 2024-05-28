package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RboardVO;

public class MyPageMoAction implements Action{

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
		RboardDAO rdao = RboardDAO.getInstance();
		RboardVO rboard = rdao.getrboard(mem_num);
		
		//내가 쓴 모집글 메서드 불러오기 rboar에서 
		
		
		
		request.setAttribute("rboard", rboard);
		
		//JSP 경로 반환
		return "/WEB-INF/views/member/myPageMo.jsp";
	}

}
