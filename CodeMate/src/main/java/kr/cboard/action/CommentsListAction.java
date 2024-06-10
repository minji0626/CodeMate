package kr.cboard.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.cboard.dao.CboardDAO;
import kr.cboard.vo.CboardVO;
import kr.cboard.vo.CcommentVO;
import kr.controller.Action;

public class CommentsListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		int cb_num = Integer.parseInt(request.getParameter("cb_num"));
		
		CboardDAO cdao = CboardDAO.getInstance();
		List<CcommentVO> commentsList = cdao.getCcommentList(cb_num);
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		mapAjax.put("commentsList", commentsList);
		mapAjax.put("mem_num", mem_num);
		
		CboardVO board = cdao.detailCboard(cb_num);
		
		int cb_type= board.getCb_type();
		mapAjax.put("cb_type", cb_type);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
