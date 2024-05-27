package kr.rboard.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.db.dao.DBDAO;
import kr.db.vo.FieldVO;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DBDAO dbdao = DBDAO.getInstance();
		List<FieldVO> fieldList = dbdao.getFieldList();
		
		request.setAttribute("fieldList", fieldList);
		return "/WEB-INF/views/rBoard/list.jsp";
	}

}
