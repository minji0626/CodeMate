package kr.rboard.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.db.dao.DBDAO;
import kr.db.vo.FieldVO;
import kr.db.vo.HardSkillVO;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RboardVO;

public class ModifyRboardFormAction implements Action {

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
		
		for (String skill : db_rboard.getHs_name_arr()) {
			System.out.println(skill);
		}
		
		for (String field : db_rboard.getF_name_arr()) {
			System.out.println(field);
		}
		
		
		// 로그인한 회원번호와 작성한 회원번호 일치
		DBDAO dbdao = DBDAO.getInstance();
		List<FieldVO> fieldList = dbdao.getFieldList();
		List<HardSkillVO> hskillList = dbdao.getHardSkillList();
		
		request.setAttribute("fieldList", fieldList);
		request.setAttribute("hskillList", hskillList);
		request.setAttribute("rboard", db_rboard);
		
		return "/WEB-INF/views/rBoard/modifyRboardForm.jsp";
	}
	
}
