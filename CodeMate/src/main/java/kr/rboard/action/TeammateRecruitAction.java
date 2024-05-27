package kr.rboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RboardVO;

public class TeammateRecruitAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 확인
		HttpSession session = request.getSession();
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		
		if (mem_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		//인코딩 타입 지정
		request.setCharacterEncoding("utf-8");

		
		//RboardVO 생성 후 인풋 데이터 저장
		RboardVO rboard = new RboardVO();
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
		
		for (String r_skill : r_skills) {
			System.out.println(r_skill);
		}
		rboard.setR_skills(r_skills);
		rboard.setR_fields(r_fields);
		
		RboardDAO rdao = RboardDAO.getInstance();
		rdao.insertRboard(rboard);
		
		request.setAttribute("notice_msg", "모집글이 등록되었습니다.");
		request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");

		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
