package kr.rboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RboardVO;

public class ModifyRboardAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		if (mem_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		request.setCharacterEncoding("utf-8");
		int rb_num = Integer.parseInt(request.getParameter("rb_num"));
		RboardDAO rdao = RboardDAO.getInstance();
		RboardVO db_rboard = rdao.getrboard(rb_num);

		if (rb_num != db_rboard.getRb_num()) {
			request.setAttribute("notice_msg", "잘못된 접근입니다");
			request.setAttribute("notice_url", "detail.do?rb_num=" + rb_num);
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		
		// 로그인한 회원번호와 작성한 회원번호 일치
		//RboardVO 생성 후 인풋 데이터 저장
		RboardVO rboard = new RboardVO();
		rboard.setRb_num(Integer.parseInt(request.getParameter("rb_num")));
		rboard.setMem_num(mem_num);
		rboard.setRb_category(Integer.parseInt(request.getParameter("rb_category")));
		rboard.setRb_teamsize(Integer.parseInt(request.getParameter("rb_teamsize")));
		rboard.setRb_meet(Integer.parseInt(request.getParameter("rb_meet")));
		rboard.setRb_start(request.getParameter("rb_start"));
		rboard.setRb_period(Integer.parseInt(request.getParameter("rb_period")));
		rboard.setRb_endRecruit(request.getParameter("rb_endRecruit"));
		rboard.setRb_pj_title(request.getParameter("rb_pj_title"));
		rboard.setRb_title(request.getParameter("rb_title"));
		rboard.setRb_content(request.getParameter("rb_content"));
		
		String r_skills[] = request.getParameterValues("r_skills");
		String r_fields[] = request.getParameterValues("r_fields");
		
		rboard.setR_skills(r_skills);
		rboard.setR_fields(r_fields);
		
		System.out.println(rboard.getRb_teamsize());
		
		rdao.modifyRboard(rboard);
		
		request.setAttribute("notice_msg", "코메 모집글을 수정했어요");
		request.setAttribute("notice_url", "detail.do?rb_num=" + rb_num);
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
