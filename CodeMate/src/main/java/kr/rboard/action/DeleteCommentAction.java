package kr.rboard.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RcommentVO;

public class DeleteCommentAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		Map<String, String> mapAjax = new HashMap<String, String>();
		int rc_num = Integer.parseInt(request.getParameter("rc_num"));
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");

		RboardDAO rdao = RboardDAO.getInstance();
		RcommentVO db_rcomment = rdao.getRcomment(rc_num);
		
		if (mem_num == null) { //로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		} else if (mem_num == db_rcomment.getMem_num()) { //로그인한 회원번호와 작성자 회원번호 일치
			rdao.deleteRcomment(rc_num);
			mapAjax.put("result", "success");
		} else { //로그인한 회원번호와 작성자 회원번호 불일치
			mapAjax.put("result", "wrongAccess");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
