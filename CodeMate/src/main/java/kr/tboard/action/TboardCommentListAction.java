package kr.tboard.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.tboard.dao.TboardDAO;
import kr.tboard.vo.TboardCommentVO;

public class TboardCommentListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		int tb_num = Integer.parseInt(request.getParameter("tb_num"));
		
		TboardDAO tdao = TboardDAO.getInstance();
		List<TboardCommentVO> commentsList = tdao.getListCommentTboard(tb_num);
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		mapAjax.put("commentsList", commentsList);
		mapAjax.put("mem_num", mem_num);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
