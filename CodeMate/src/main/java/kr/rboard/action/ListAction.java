package kr.rboard.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.db.dao.DBDAO;
import kr.db.vo.FieldVO;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RboardVO;
import kr.util.PagingUtil;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) pageNum = "1";
		
		String keyfield = null; //검색 로직 추가 후 수정
		String keyword = null;
		
		DBDAO dbdao = DBDAO.getInstance();
		List<FieldVO> fieldList = dbdao.getFieldList();
		
		RboardDAO rdao = RboardDAO.getInstance();
		int count = rdao.getRboardCount();
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 20, 10, "/rboard/list.do");
		
		List<RboardVO> rboardList = null;
		if (count > 0) {
			rboardList = rdao.getRboardList(page.getStartRow(), page.getEndRow());
		}
		
		for (RboardVO rboard : rboardList) {
			rboard.setHs_name_arr(rboard.getHs_name_string().split(","));
			rboard.setHs_photo_arr(rboard.getHs_photo_string().split(","));
			rboard.setF_name_arr(rboard.getF_name_string().split(","));
		}
		
		request.setAttribute("fieldList", fieldList);
		request.setAttribute("rboardList", rboardList);
		request.setAttribute("count", count);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/rBoard/list.jsp";
	}

}
