package kr.cboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class WriteCommunityFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");

		if(mem_num == null) {// 로그인 미실시
			return "redirect:/member/loginForm.do";
		} 
		
		int cb_type = Integer.parseInt(request.getParameter("cb_type"));
		
		request.setAttribute("cb_type",cb_type);
		
		//커뮤니티 (개발 게시판으로 이동)
		return "/WEB-INF/views/cBoard/writeCommunityForm.jsp";
	}

}
