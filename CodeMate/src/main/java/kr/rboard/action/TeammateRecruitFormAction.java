package kr.rboard.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.db.dao.DBDAO;
import kr.db.vo.FieldVO;
import kr.db.vo.HardSkillVO;

public class TeammateRecruitFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 확인
		HttpSession session = request.getSession();
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		
		if (mem_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		DBDAO dbdao = DBDAO.getInstance();
		List<FieldVO> fieldList = dbdao.getFieldList();
		List<HardSkillVO> hskillList = dbdao.getHardSkillList();
		
		request.setAttribute("fieldList", fieldList);
		request.setAttribute("hskillList", hskillList);
		
		//JSP 경로 설정
		return "/WEB-INF/views/rBoard/teammateRecruitForm.jsp";
	}
	
}
