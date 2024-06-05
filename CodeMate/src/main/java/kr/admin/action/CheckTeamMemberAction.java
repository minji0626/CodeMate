package kr.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.tmember.dao.TmemberDAO;
import kr.tmember.vo.TmemberVO;

public class CheckTeamMemberAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//세션에 로그인된 상태로 있어야됨
		HttpSession session = request.getSession();
		// 로그인 체크
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		Integer mem_auth = (Integer) session.getAttribute("mem_auth");
		if (mem_num == null) {// 로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";

		} else if (mem_auth == 9) { //관리자로 로그인 된 경우
			int team_num = Integer.parseInt(request.getParameter("team_num"));

			TmemberDAO dao = TmemberDAO.getInstance();
			int count = dao.getTmemberCount(team_num);

			List<TmemberVO> list = null;
			if(count > 0) {
				list = dao.getTeamMembers(team_num);
			}
			
			int leader_mem_num = dao.whoIsLeader(team_num);

			request.setAttribute("leader_mem_num", leader_mem_num);
			request.setAttribute("count", count);
			request.setAttribute("team_num", team_num);
			request.setAttribute("list", list);

			return "/WEB-INF/views/admin/manageTeamMember.jsp";
		} else {//관리자가 아닌 아이디로 로그인 된 경우
			request.setAttribute("notice_msg", "페이지 접근 권한이 없습니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			return "/WEB-INF/views/common/alert_view.jsp";

		}
	}
}
