package kr.admin.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.tmember.dao.TmemberDAO;

public class DeleteLeaderLastAction  implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		Map<String, String> mapAjax = new HashMap<String, String>();

		TmemberDAO dao = TmemberDAO.getInstance();

		// 로그인 체크
		Integer user_num = (Integer) session.getAttribute("mem_num");
		Integer user_auth = (Integer) session.getAttribute("mem_auth");
		if (user_num == null) {// 로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";

		} else if (user_auth == 9) { //관리자로 로그인 된 경우 
			// 전송된 데이터 인코딩 타입 지정
			request.setCharacterEncoding("utf-8");

			int mem_num = Integer.parseInt(request.getParameter("mem_num"));
			int team_num = Integer.parseInt(request.getParameter("team_num"));
			int leader = Integer.parseInt(request.getParameter("leader"));
			dao.deleteTeamMember(mem_num, team_num);
			dao.projectDone(team_num);
			mapAjax.put("result", "success");

		} else {
			mapAjax.put("result", "wrongAccess");
		}
		// JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);

		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}