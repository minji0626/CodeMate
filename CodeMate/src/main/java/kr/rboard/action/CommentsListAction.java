package kr.rboard.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RcommentVO;

public class CommentsListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		int rb_num = Integer.parseInt(request.getParameter("rb_num"));
		
		RboardDAO rdao = RboardDAO.getInstance();
		List<RcommentVO> commentsList = rdao.getRcommentList(rb_num);
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		mapAjax.put("commentsList", commentsList);
		mapAjax.put("mem_num", mem_num);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
