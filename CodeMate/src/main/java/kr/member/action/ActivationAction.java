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

public class ActivationAction  implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, String> mapAjax = new HashMap<String, String>();

		// 세션에 로그인된 상태로 있어야 됨
		HttpSession session = request.getSession();

		// 로그인 체크
		Integer login_num = (Integer)session.getAttribute("mem_num");
		if(login_num == null) { // 로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
			
			
		}  

		request.setCharacterEncoding("utf-8");

		int rb_num = Integer.parseInt(request.getParameter("rb_num"));

		ApplyDAO dao = ApplyDAO.getInstance();
		boolean check = dao.minimumTeamMember(rb_num);
		if(check) {
			dao.teamActivation(rb_num);
			mapAjax.put("result", "success");
		} else {
			mapAjax.put("result", "noMember");
		}
		
		ObjectMapper mapper = new ObjectMapper();
        String ajaxData = mapper.writeValueAsString(mapAjax);
        request.setAttribute("ajaxData", ajaxData);

        return "/WEB-INF/views/common/ajax_view.jsp";
		

	}


}
