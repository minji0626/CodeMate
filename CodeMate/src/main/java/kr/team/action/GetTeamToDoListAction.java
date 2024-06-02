package kr.team.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.team.dao.TeamToDoDAO;
import kr.team.vo.TeamToDoVO;

public class GetTeamToDoListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		Integer team_num = (Integer) session.getAttribute("team_num");
		
		
		TeamToDoDAO tododao = TeamToDoDAO.getInstance();
		List<TeamToDoVO> teamtodo = tododao.getTeamToDo(team_num);
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		mapAjax.put("teamtodo", teamtodo);
		mapAjax.put("team_num", team_num);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
