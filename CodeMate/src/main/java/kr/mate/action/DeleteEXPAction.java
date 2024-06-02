package kr.mate.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.mate.dao.MateDAO;
import kr.mate.vo.MateVO;

public class DeleteEXPAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션에 로그인된 상태로 있어야 됨
		HttpSession session = request.getSession();

		// 로그인 체크
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(mem_num == null) { // 로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}

		int user_num = Integer.parseInt(request.getParameter("mem_num"));

		if(mem_num!=user_num) {
			request.setAttribute("notice_msg","본인만 작성할 수 있습니다!");
			request.setAttribute("notice_url", request.getContextPath()+"/mateProfile/mateProfile.do?mem_num="+user_num);
			return "/WEB-INF/views/common/alert_view.jsp";
		}

		request.setCharacterEncoding("utf-8");

		int me_num = Integer.parseInt(request.getParameter("me_num"));

		MateDAO dao = MateDAO.getInstance();
		dao.deleteMateExp(me_num);

		request.setAttribute("notice_msg", "프로젝트 경험이 삭제되었습니다.");
		request.setAttribute("notice_url", request.getContextPath() + "/mateProfile/mateProfile.do?mem_num=" + user_num);

		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
