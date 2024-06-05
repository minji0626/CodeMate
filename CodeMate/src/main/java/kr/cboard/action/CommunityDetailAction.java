package kr.cboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cboard.dao.CboardDAO;
import kr.cboard.vo.CboardVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class CommunityDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(mem_num==null) {
			mem_num=0;
		}
				
		int cb_num = Integer.parseInt(request.getParameter("cb_num"));

		CboardDAO dao = CboardDAO.getInstance();
		CboardVO board = dao.detailCboard(cb_num);

		//조회수 증가
		dao.updateReadcount(cb_num);

		board.setCb_title(StringUtil.useNoHTML(board.getCb_title()));
		board.setCb_content(StringUtil.useBrNoHTML(board.getCb_content()));
		
		boolean check = dao.memberLike(cb_num, mem_num);
		
		request.setAttribute("check", check);
		request.setAttribute("cb_num", cb_num);
		request.setAttribute("board", board);
		return "/WEB-INF/views/cBoard/communityDetail.jsp";
	}

}