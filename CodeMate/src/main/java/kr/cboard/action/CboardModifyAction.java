package kr.cboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cboard.dao.CboardDAO;
import kr.cboard.vo.CboardVO;
import kr.controller.Action;
import kr.util.FileUtil;
import kr.util.StringUtil;

public class CboardModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");

		if(mem_num == null) {// 로그인 미실시
			return "redirect:/member/loginForm.do";
		} 
		
		request.setCharacterEncoding("utf-8");
		
		int cb_num = Integer.parseInt(request.getParameter("cb_num"));
		
		CboardDAO dao = CboardDAO.getInstance();
		CboardVO db_board = dao.detailCboard(cb_num);
		
		if(mem_num != db_board.getMem_num()) {
			request.setAttribute("notice_msg","작성자만 수정할 수 있습니다!");
			request.setAttribute("notice_url", request.getContextPath()+"/cboard/communityDetail.do?cb_num="+cb_num);
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		
		CboardVO board = new CboardVO();
		board.setCb_num(cb_num);
		board.setCb_title(request.getParameter("cb_title"));
		board.setCb_content(request.getParameter("cb_content"));
		
		String cb_type = request.getParameter("cb_type");
		if (cb_type != null) {
			if (cb_type.equals("0")) {
				board.setCb_type(0);
			} else if (cb_type.equals("1")) {
				board.setCb_type(1);
			}
		}
		board.setCb_ip(request.getRemoteAddr());
		
		board.setCb_file(FileUtil.createFile(request, "cb_file"));
		
		dao.updateBoard(board);
		
		if(board.getCb_file()!=null && !"".equals(board.getCb_file())) {
			FileUtil.removeFile(request, db_board.getCb_file());
		}


		return "redirect:/cboard/communityDetail.do?cb_num="+cb_num;
	}

}
