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
		//팀원 구하기 슬라이드
		DBDAO dbdao = DBDAO.getInstance();
		List<FieldVO> fieldList = dbdao.getFieldList();
		List<HardSkillVO> hskillList = dbdao.getHardSkillList();

		RboardDAO rdao = RboardDAO.getInstance(); 
		int count1 = rdao.getRboardCount(null,null,null,null,null,false);//rboard의 글 갯수

		List<RboardVO> SlideList = null;
		if (count1 > 0) {
			SlideList = rdao.getSlideList();
		}

		request.setAttribute("fieldList", fieldList);
		request.setAttribute("hskillList", hskillList);
		request.setAttribute("SlideList", SlideList);
		
		//커뮤니티 슬라이드
		CboardDAO cdao = CboardDAO.getInstance();
		List<CboardVO> SlideList2 = null;
		List<CboardVO> SlideList3 = null;
		int count2 = cdao.getCboardCount(null, null, 0); //자유
		int count3 = cdao.getCboardCount(null, null, 1); //개발
		if(count2 > 0) {
			SlideList2 = cdao.getSlideListBoardF();
		}
		request.setAttribute("SlideList2",SlideList2);
		
		if(count3 > 0) {
			SlideList3 = cdao.getSlideListBoardD();
		}
		request.setAttribute("SlideList3",SlideList3);
		//JSP 경로 반환
		return "/WEB-INF/views/main/main.jsp";
	}
}




