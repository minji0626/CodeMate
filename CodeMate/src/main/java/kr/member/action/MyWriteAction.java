package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class MyWriteAction implements Action{

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
		
		return "/WEB-INF/views/member/myWrite.jsp";
	}

}
