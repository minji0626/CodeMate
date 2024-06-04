package kr.main.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.consult.dao.ConsultDAO;
import kr.consult.vo.ConsultVO;
import kr.controller.Action;

public class SendConsultAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");

		if(mem_num == null) {// 로그인 미실시
			return "redirect:/member/loginForm.do";
		} 
		
		request.setCharacterEncoding("utf-8");
		
		ConsultVO consult = new ConsultVO();
		consult.setMem_num(mem_num);
		consult.setCs_title(request.getParameter("cs_title"));
		consult.setCs_content(request.getParameter("cs_content"));
		consult.setCs_category(Integer.parseInt(request.getParameter("cs_category")));
		consult.setCs_reply_email(request.getParameter("email"));
		
		ConsultDAO cdao = ConsultDAO.getInstance();
		cdao.sendConsult(consult);
		
		request.setAttribute("notice_msg", "1:1 문의를 보냈습니다.");
		request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
