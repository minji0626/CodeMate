package kr.admin.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.cboard.dao.CboardDAO;
import kr.cboard.vo.CcommentVO;
import kr.controller.Action;

public class DeleteCommentAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

		Map<String, String> mapAjax = new HashMap<String, String>();
		int cc_num = Integer.parseInt(request.getParameter("cc_num"));

		HttpSession session = request.getSession();
		// 로그인 체크
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		Integer mem_auth = (Integer) session.getAttribute("mem_auth");
		if (mem_num == null) {// 로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";

		} else if (mem_auth == 9) { //관리자로 로그인 된 경우
			CboardDAO cdao =CboardDAO.getInstance();
			CcommentVO db_rcomment = cdao.getCcomment(cc_num);

			cdao.deleteCcomment(cc_num);
			request.setAttribute("notice_msg", "댓글을 삭제했습니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/admin/manageComment.do");
			return "/WEB-INF/views/common/alert_view.jsp";

		} else {
			request.setAttribute("notice_msg", "페이지 접근 권한이 없습니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		}

	}

}


