package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.rboard.dao.ApplyDAO;
import kr.rboard.vo.RapplyVO;

public class ActivationAction  implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, String> mapAjax = new HashMap<String, String>();

		// 세션에 로그인된 상태로 있어야 됨
		HttpSession session = request.getSession();

		// 로그인 체크
		Integer login_num = (Integer)session.getAttribute("mem_num");
		if(login_num == null) { // 로그인이 되지 않은 경우
			request.setAttribute("notice_msg","로그인 하세요!");
			request.setAttribute("notice_url", "redirect:/member/loginForm.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		} 

		request.setCharacterEncoding("utf-8");

		int rb_num = Integer.parseInt(request.getParameter("rb_num"));

		ApplyDAO dao = ApplyDAO.getInstance();

		dao.teamActivation(rb_num);
		request.setAttribute("notice_msg","프로젝트가 활성화되었습니다!");
		request.setAttribute("notice_url", request.getContextPath()+"/member/myPageMo.do");
		return "/WEB-INF/views/common/alert_view.jsp";



	}


}
