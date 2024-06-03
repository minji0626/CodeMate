package kr.tmember.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.tmember.dao.TmemberDAO;

public class UpdateTeamLeaderAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer tm_auth = (Integer) session.getAttribute("tm_auth");
		int team_num = Integer.parseInt(request.getParameter("team_num"));
		int current_leader_mem_num = (Integer) session.getAttribute("mem_num");
		int new_leader_mem_num = Integer.parseInt(request.getParameter("new_leader_mem_num"));

		System.out.println(tm_auth +","+ team_num +","+ current_leader_mem_num +","+ new_leader_mem_num);
		
		Map<String, String> mapAjax = new HashMap<String, String>();

		if (tm_auth != null && tm_auth == 4) {
			TmemberDAO dao = TmemberDAO.getInstance();
			dao.modifyTeamLeaderAndChangeMember(team_num, current_leader_mem_num, new_leader_mem_num);
			mapAjax.put("result", "success");
			session.setAttribute("tm_auth", 3);
		} else {
			mapAjax.put("result", "noAuth");
		}

		// JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);

		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
