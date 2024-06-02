package kr.team.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.team.dao.TeamToDoDAO;
import kr.team.vo.TeamToDoVO;

public class AddTeamToDoAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HttpSession session = request.getSession();
	    Integer mem_num = (Integer) session.getAttribute("mem_num");
	    Integer team_num = (Integer) session.getAttribute("team_num");
		Map<String, String> mapAjax = new HashMap<String, String>();

		if (mem_num == null) { // 로그인하지 않은 경우
			mapAjax.put("result", "logout");
		} else { // 로그인 한 경우
	        // 요청 파라미터에서 팀 투두 내용, 날짜, 시작 시간, 종료 시간, 팀 번호 가져오기
	        String tt_content = request.getParameter("tt_content");
	        String tt_date = request.getParameter("tt_date");
	        String tt_start = request.getParameter("tt_start");
	        String tt_end = request.getParameter("tt_end");

	        // TeamToDoVO 객체에 데이터 설정
	        TeamToDoVO todo = new TeamToDoVO();
	        todo.setTeam_num(team_num);
	        todo.setTt_content(tt_content);
	        todo.setTt_date(tt_date);
	        todo.setTt_start(tt_start);
	        todo.setTt_end(tt_end);

	        System.out.println(team_num +","+tt_content+","+tt_date+","+tt_start+","+tt_end);
	        // TeamToDoDAO를 사용하여 팀 투두 추가
	        TeamToDoDAO dao = TeamToDoDAO.getInstance();
	        dao.addToDo(todo);

			mapAjax.put("result", "success");
		}

		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);

		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}