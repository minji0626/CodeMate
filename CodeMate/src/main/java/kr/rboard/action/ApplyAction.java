package kr.rboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RapplyVO;

public class ApplyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		//로그인 체크
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(mem_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
			
			//로그인이 된 경우
		}
		//로그인이 된 경우
		request.setCharacterEncoding("utf-8");
		
		//input 데이터 받아와서 rapplyvo에 저장
		RapplyVO rapply = new RapplyVO();
		
		int rb_num = Integer.parseInt(request.getParameter("rb_num"));
		rapply.setRb_num(rb_num);
		rapply.setMem_num(mem_num);
		rapply.setRa_content(request.getParameter("ra_content"));

		//apply
		RboardDAO rdao = RboardDAO.getInstance();
		rdao.applyToRboard(rapply);
		
		String rb_title = request.getParameter("rb_title");
		
		request.setAttribute("result_title", "코드메이트 신청 완료");
		request.setAttribute("result_msg", "[" + rb_title + "] 메이트 모집에 신청했어요!");
		request.setAttribute("btn_value1", "모집글로 돌아가기");
		request.setAttribute("btn_value2", "신청내역 확인");
		request.setAttribute("result_url1", "detail.do?rb_num=" + rb_num);
		request.setAttribute("result_url2", request.getContextPath() + "/member/myPageShin.do");
		
		return "/WEB-INF/views/common/result_view_two_btn.jsp";
	}

}
