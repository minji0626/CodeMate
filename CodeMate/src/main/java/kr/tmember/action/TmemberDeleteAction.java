package kr.tmember.action;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.tmember.dao.TmemberDAO;
import kr.tmember.vo.TmemberVO;

public class TmemberDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int team_num = Integer.parseInt(request.getParameter("team_num"));
		
		System.out.println(team_num + ", "+mem_num);
		Map<String, String> mapAjax = new HashMap<String, String>();

		TmemberDAO dao = TmemberDAO.getInstance();

		Integer user_num = (Integer)session.getAttribute("mem_num");
		if(user_num == null) { // 로그인 되지 않은 경우
			mapAjax.put("result", "logout");
		} else { // 로그인 된 경우
			// 전송된 데이터 인코딩 타입 지정

			dao.deleteTeamMember(mem_num, team_num);
			mapAjax.put("result", "success");

		}
		// JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);

		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
