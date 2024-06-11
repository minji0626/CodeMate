package kr.cboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cboard.dao.CboardDAO;
import kr.cboard.vo.CboardVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class ModifyCommunityFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");

		if(mem_num == null) {// 로그인 미실시
			return "redirect:/member/loginForm.do";
		} 
		
		int cb_num = Integer.parseInt(request.getParameter("cb_num"));
		int cb_type = Integer.parseInt(request.getParameter("cb_type"));
		
		CboardDAO dao = CboardDAO.getInstance();
		CboardVO board = dao.detailCboard(cb_num);
		
		if(mem_num!=board.getMem_num()) {
			request.setAttribute("notice_msg","작성자만 수정할 수 있습니다!");
			request.setAttribute("notice_url", request.getContextPath()+"/cboard/communityDetail.do?cb_num="+board.getCb_num());
			return "/WEB-INF/views/common/alert_view.jsp";
		}

		board.setCb_title(StringUtil.parseQuot(board.getCb_title()));
		request.setAttribute("board", board);
		request.setAttribute("cb_type", cb_type);
		
		return "/WEB-INF/views/cBoard/modifyCommunityForm.jsp";
	}

}
