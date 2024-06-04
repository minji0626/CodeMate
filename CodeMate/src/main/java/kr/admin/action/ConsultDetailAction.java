package kr.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.consult.dao.ConsultDAO;
import kr.consult.vo.ConsultVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class ConsultDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션에 로그인된 상태로 있어야됨
		HttpSession session = request.getSession();
		// 로그인 체크
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		Integer mem_auth = (Integer) session.getAttribute("mem_auth");
		if (mem_num == null) {// 로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";

		} else if (mem_auth == 9) { //관리자로 로그인 된 경우
			request.setCharacterEncoding("utf-8");
			int cs_num = Integer.parseInt(request.getParameter("cs_num"));
			
			ConsultDAO cdao = ConsultDAO.getInstance();
			ConsultVO consult = cdao.getConsult(cs_num);
			
			request.setAttribute("consult", consult);
			
			//JSP 경로 반환
			return "/WEB-INF/views/admin/consultDetail.jsp";
			
		} else { //관리자가 아닌 아이디로 로그인 된 경우
			request.setAttribute("notice_msg", "페이지 접근 권한이 없습니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		
	}

}
