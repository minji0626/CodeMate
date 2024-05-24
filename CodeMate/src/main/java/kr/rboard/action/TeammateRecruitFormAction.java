package kr.rboard.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.db.dao.DBDAO;
import kr.db.vo.FieldVO;
import kr.db.vo.HardSkillVO;

public class TeammateRecruitFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DBDAO dao = DBDAO.getInstance();
		List<FieldVO> fieldList = dao.getFieldList();
		List<HardSkillVO> hskillList = dao.getHardSkillList();
		
		request.setAttribute("fieldList", fieldList);
		request.setAttribute("hskillList", hskillList);
		
		//JSP 경로 설정
		return "/WEB-INF/views/rBoard/teammateRecruitForm.jsp";
	}
	
}
