package kr.main.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.cboard.dao.CboardDAO;
import kr.cboard.vo.CboardVO;
import kr.controller.Action;
import kr.db.dao.DBDAO;
import kr.db.vo.FieldVO;
import kr.db.vo.HardSkillVO;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RboardVO;

public class MainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DBDAO dbdao = DBDAO.getInstance();
		List<FieldVO> fieldList = dbdao.getFieldList();
		List<HardSkillVO> hskillList = dbdao.getHardSkillList();

		RboardDAO rdao = RboardDAO.getInstance(); 
		int count = rdao.getRboardCount(null,null,null,null,null,false);//rboard의 글 갯수

		

		List<RboardVO> SlideList = null;
		if (count > 0) {
			SlideList = rdao.getSlideList();
		}

		request.setAttribute("fieldList", fieldList);
		request.setAttribute("hskillList", hskillList);
		request.setAttribute("SlideList", SlideList);
		request.setAttribute("count", count);
		
		CboardDAO cdao = CboardDAO.getInstance();
		List<CboardVO> SlideList2 = null;
		SlideList2 = cdao.getSlideListBoard();
		request.setAttribute("SlideList2",SlideList2);
		
		//JSP 경로 반환
		return "/WEB-INF/views/main/main.jsp";
	}
}




