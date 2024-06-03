package kr.team.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.team.dao.TeamToDoDAO;

public class DeleteToDoAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer mem_num = (Integer) session.getAttribute("mem_num");

		Map<String, String> mapAjax = new HashMap<String, String>();

		if (mem_num == null) { // 로그인하지 않은 경우
			mapAjax.put("result", "logout");
		} else {
			// 요청 파라미터에서 팀 투두 번호 가져오기
			int tt_num = Integer.parseInt(request.getParameter("tt_num"));

			// 팀 투두 상태 변경
			TeamToDoDAO dao = TeamToDoDAO.getInstance();
			dao.deleteToDo(tt_num);

			mapAjax.put("result", "success");
		}

		// JSON 형태로 응답을 반환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);

		return "/WEB-INF/views/common/ajax_view.jsp";

	}

}
