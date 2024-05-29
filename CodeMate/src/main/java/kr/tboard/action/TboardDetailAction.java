package kr.tboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.tboard.dao.TboardDAO;
import kr.tboard.vo.TboardVO;

public class TboardDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		int tb_num = Integer.parseInt(request.getParameter("tb_num"));
		
		TboardVO tboard = new TboardVO();
		TboardDAO dao = TboardDAO.getInstance();
		
		tboard = dao.detailTboard(tb_num);
		
		request.setAttribute("tboard", tboard);
		
		return "/WEB-INF/views/team/TBoardDetail.jsp";
	}

}
