package kr.rboard.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RapplyVO;
import kr.rboard.vo.RboardVO;

public class ApplyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		// 로그인 체크
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		if (mem_num == null) {// 로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";

			// 로그인이 된 경우
		}
		// 로그인이 된 경우
		request.setCharacterEncoding("utf-8");
		int rb_num = Integer.parseInt(request.getParameter("rb_num"));
		
		//자기 글이 아닐 때만 신청할 수 있음
		RboardDAO rdao = RboardDAO.getInstance();
		RboardVO db_rboard = rdao.getrboard(rb_num);
		
		if (mem_num == db_rboard.getMem_num()) {
			request.setAttribute("notice_msg", "자신의 글에는 신청할 수 없어요");
			request.setAttribute("notice_url", "detail.do?rb_num=" +rb_num);
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		
		//이미 신청한 글에는 신청할 수 없음
		boolean alreadyApplied = rdao.alreadyApplied(rb_num, mem_num);
		
		if (alreadyApplied) {
			request.setAttribute("notice_msg", "이미 신청한 코메 모집이에요");
			request.setAttribute("notice_url", "detail.do?rb_num=" +rb_num);
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		

		// input 데이터 받아와서 rapplyvo에 저장
		RapplyVO rapply = new RapplyVO();

		rapply.setRb_num(rb_num);
		rapply.setMem_num(mem_num);
		rapply.setRa_content(request.getParameter("ra_content"));

		// apply
		rdao.applyToRboard(rapply);

		// ApplyResultAction 컨트롤러로 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher("/rboard/applyResult.do");
		dispatcher.forward(request, response);
		return null;
	}

}
