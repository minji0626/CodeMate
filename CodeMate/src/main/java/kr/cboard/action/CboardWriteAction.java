package kr.cboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cboard.dao.CboardDAO;
import kr.cboard.vo.CboardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class CboardWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");

		if(mem_num == null) {// 로그인 미실시
			return "redirect:/member/loginForm.do";
		} 

		request.setCharacterEncoding("UTF-8");

		CboardVO cboard = new CboardVO();
		cboard.setCb_title(request.getParameter("cb_title"));
		cboard.setCb_content(request.getParameter("cb_content"));
		
		String cb_type = request.getParameter("cb_type");
		if (cb_type != null) {
			if (cb_type.equals("0")) {
				cboard.setCb_type(0);
			} else if (cb_type.equals("1")) {
				cboard.setCb_type(1);
			}
		}
		cboard.setCb_ip(request.getRemoteAddr());
		
		cboard.setCb_file(FileUtil.createFile(request, "cb_file"));
		
		cboard.setMem_num(mem_num);
		
		CboardDAO dao = CboardDAO.getInstance();
		dao.insertCboard(cboard);
		
		request.setAttribute("notice_msg", "글 작성이 완료되었습니다.");
		request.setAttribute("notice_url", request.getContextPath()+"/cboard/community.do");
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
