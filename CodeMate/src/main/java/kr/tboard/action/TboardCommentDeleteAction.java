package kr.tboard.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.tboard.dao.TboardDAO;
import kr.tboard.vo.TboardCommentVO;

public class TboardCommentDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		Map<String, String> mapAjax = new HashMap<String, String>();
		int tc_num = Integer.parseInt(request.getParameter("tc_num"));
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");

		TboardDAO tdao = TboardDAO.getInstance();
		TboardCommentVO db_tcomment = tdao.getTcomment(tc_num);
		
		if (mem_num == null) { //로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		} else if (mem_num.equals(db_tcomment.getMem_num())) { //로그인한 회원번호와 작성자 회원번호 일치
			tdao.deleteTcomment(tc_num);
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
