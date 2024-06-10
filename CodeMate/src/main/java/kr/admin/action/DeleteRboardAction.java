package kr.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.admin.dao.AdminDAO;
import kr.cboard.vo.CboardVO;
import kr.controller.Action;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RboardVO;
import kr.tmember.dao.TmemberDAO;
import kr.util.PagingUtil;

public class DeleteRboardAction  implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 세션에 로그인된 상태로 있어야됨
		HttpSession session = request.getSession();
		// 로그인 체크
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		Integer mem_auth = (Integer) session.getAttribute("mem_auth");
		if (mem_num == null) {// 로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";

		} else if (mem_auth == 9) { //관리자로 로그인 된 경우

			// 로그인이 된 경우
			int rb_num = Integer.parseInt(request.getParameter("rb_num"));
			RboardDAO rdao = RboardDAO.getInstance();
			RboardVO db_rboard = rdao.getrboard(rb_num);

			// 로그인한 회원번호와 작성한 회원번호 일치
			rdao.deleteRboard(rb_num);

			return "redirect:/admin/manageRboard.do";
			
		} else { //관리자가 아닌 아이디로 로그인 된 경우
			request.setAttribute("notice_msg", "페이지 접근 권한이 없습니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		}


	}

}
