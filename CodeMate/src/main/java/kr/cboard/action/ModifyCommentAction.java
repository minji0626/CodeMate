package kr.cboard.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.cboard.dao.CboardDAO;
import kr.cboard.vo.CcommentVO;
import kr.controller.Action;

public class ModifyCommentAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		Map<String, String> mapAjax = new HashMap<String, String>();
		int cc_num = Integer.parseInt(request.getParameter("cc_num"));
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");

		CboardDAO cdao = CboardDAO.getInstance();
		CcommentVO db_rcomment = cdao.getCcomment(cc_num);
		
		if (mem_num == null) { //로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		} else if (mem_num == db_rcomment.getMem_num()) { //로그인한 회원번호와 작성자 회원번호 일치
			CcommentVO ccomment =cdao.getCcomment(cc_num);
			ccomment.setCc_content(request.getParameter("cc_content"));
			
			cdao.modifyCcomment(ccomment);
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
