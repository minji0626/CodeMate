package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
//import kr.util.SessionManager;

public class LogoutAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		if(mem_num == null) {//로그인이 안 된 경우
			return "redirect:/member/loginForm.do";
		}
		//로그아웃 처리
		session.invalidate();
//		SessionManager.invalidateUserSession(mem_num);
		//메인으로 리다이렉트
		return "redirect:/main/main.do";
	}

}
