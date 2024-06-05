package kr.rboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RboardVO;

public class DeleteRboardAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer) session.getAttribute("mem_num");

		if (mem_num == null) { // 로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}

		// 로그인이 된 경우
		int rb_num = Integer.parseInt(request.getParameter("rb_num"));
		RboardDAO rdao = RboardDAO.getInstance();
		RboardVO db_rboard = rdao.getrboard(rb_num);

		if (rb_num != db_rboard.getRb_num()) {
			request.setAttribute("notice_msg", "잘못된 접근입니다");
			request.setAttribute("notice_url", "detail.do?rb_num=" + rb_num);
			return "/WEB-INF/views/common/alert_view.jsp";
		}

		// 로그인한 회원번호와 작성한 회원번호 일치
		rdao.deleteRboard(rb_num);

		return "redirect:/rboard/list.do";
	}

}
