package kr.cboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.cboard.dao.CboardDAO;
import kr.cboard.vo.CboardVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class CommunityDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int cb_num = Integer.parseInt(request.getParameter("cb_num"));

        CboardDAO dao = CboardDAO.getInstance();
        CboardVO board = dao.detailCboard(cb_num);
        
        board.setCb_title(StringUtil.useNoHTML(board.getCb_title()));
		//HTML를 허용하지 않으면서 줄바꿈
		board.setCb_content(StringUtil.useBrNoHTML(board.getCb_content()));

        request.setAttribute("cb_num", cb_num);
        request.setAttribute("board", board);
		return "/WEB-INF/views/cBoard/communityDetail.jsp";
	}

}