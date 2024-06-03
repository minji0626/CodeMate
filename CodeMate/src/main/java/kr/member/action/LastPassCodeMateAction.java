package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.rboard.dao.ApplyDAO;
import kr.rboard.vo.RapplyVO;

public class LastPassCodeMateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, String> mapAjax = new HashMap<String, String>();

		// 세션에 로그인된 상태로 있어야 됨
		HttpSession session = request.getSession();

		// 로그인 체크
		Integer login_num = (Integer)session.getAttribute("mem_num");
		if(login_num == null) { // 로그인이 되지 않은 경우
			request.setAttribute("notice_msg","로그인 하세요!");
			request.setAttribute("notice_url", "redirect:/member/loginForm.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		} 

		request.setCharacterEncoding("utf-8");

		int ra_num = Integer.parseInt(request.getParameter("ra_num"));
		int rb_num = Integer.parseInt(request.getParameter("rb_num"));

		RapplyVO apply = new RapplyVO();

		ApplyDAO dao = ApplyDAO.getInstance();

		dao.passMember(ra_num);

		int passCount = dao.howManyPass(rb_num);
		boolean checking = dao.passAndSize(rb_num, passCount);

		if(checking) {
			dao.teamActivation(rb_num);
			request.setAttribute("notice_msg","프로젝트가 활성화되었습니다!");
			request.setAttribute("notice_url", request.getContextPath()+"/member/myPageMoShin.do?rb_num="+rb_num);
			return "/WEB-INF/views/common/alert_view.jsp";
		} else {
			request.setAttribute("notice_msg","오류가 생겼습니다.");
			request.setAttribute("notice_url", request.getContextPath()+"/member/myPageMoShin.do?rb_num="+rb_num);
			return "/WEB-INF/views/common/alert_view.jsp";
		}


	}


}
