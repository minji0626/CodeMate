package kr.rboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RboardVO;
import kr.util.StringUtil;

public class DetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int rb_num = Integer.parseInt(request.getParameter("rb_num"));
		
		RboardDAO rdao = RboardDAO.getInstance();
		
		//rboard 가져오기
		RboardVO rboard = rdao.getrboard(rb_num);
		
		boolean alreadyApplied = false;
		//로그인한 경우에는 이미 신청한 글인지 체크
		HttpSession session = request.getSession();
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		
		if (mem_num != null) {
			alreadyApplied = rdao.alreadyApplied(rb_num, mem_num);
		}
		
		
		//조회수 증가
		rdao.updateReadCount(rb_num);
		
		rboard.setRb_title(StringUtil.useNoHTML(rboard.getRb_title()));
		rboard.setRb_content(StringUtil.useBrNoHTML(rboard.getRb_content()));

		request.setAttribute("rboard", rboard);
		request.setAttribute("alreadyApplied", alreadyApplied);
		
		return "/WEB-INF/views/rBoard/detail.jsp";
	}
	
}
