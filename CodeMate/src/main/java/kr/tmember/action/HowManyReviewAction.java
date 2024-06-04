package kr.tmember.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.rboard.dao.ApplyDAO;
import kr.tmember.dao.TmemberDAO;
import kr.tmember.vo.MateReviewVO;

public class HowManyReviewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer) session.getAttribute("mem_num");

		Map<String, String> mapAjax = new HashMap<String, String>();

		if (mem_num == null) { // 로그인하지 않은 경우
			mapAjax.put("result", "logout");
		} else {
			// 로그인 한 경우
			request.setCharacterEncoding("utf-8");

			MateReviewVO mr = new MateReviewVO();

			int team_num = Integer.parseInt(request.getParameter("team_num"));
			int mr_receiver = Integer.parseInt(request.getParameter("mr_receiver"));

			TmemberDAO dao = TmemberDAO.getInstance();
			int reviewCount = dao.HowManyReview(mem_num, team_num);
			int check = dao.currentTeamSize(team_num);
			if(check==(reviewCount+1)) {
				dao.finishProject(team_num, team_num);
				
				
				mapAjax.put("result","finish");
			} else {
				mapAjax.put("result", "ing");
			}
			
		}
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);


		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}