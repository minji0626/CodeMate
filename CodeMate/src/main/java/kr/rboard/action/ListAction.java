package kr.rboard.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.db.dao.DBDAO;
import kr.db.vo.FieldVO;
import kr.db.vo.HardSkillVO;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RboardVO;
import kr.util.PagingUtil;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1";

		String keyfield = null; // 검색 로직 추가 후 수정
		String keyword = null;

		DBDAO dbdao = DBDAO.getInstance();
		List<FieldVO> fieldList = dbdao.getFieldList();
		List<HardSkillVO> hskillList = dbdao.getHardSkillList();

		RboardDAO rdao = RboardDAO.getInstance();
		int count = rdao.getRboardCount(null, null, null, null, null, false);

		// 페이지 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 12, 10, "/rboard/list.do");

		List<RboardVO> rboardList = null;
		if (count > 0) {
			rboardList = rdao.getRboardList(page.getStartRow(), page.getEndRow(), null, null, null, null, null, false);
		}

		request.setAttribute("fieldList", fieldList);
		request.setAttribute("hskillList", hskillList);
		request.setAttribute("rboardList", rboardList);
		request.setAttribute("count", count);
		request.setAttribute("page", page.getPage());

		return "/WEB-INF/views/rBoard/list.jsp";
	}

}