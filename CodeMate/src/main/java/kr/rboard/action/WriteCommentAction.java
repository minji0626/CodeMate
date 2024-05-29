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

public class WriteCommentAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer) session.getAttribute("mem_num");

		Map<String, String> mapAjax = new HashMap<String, String>();

		if (mem_num == null) { // 로그인하지 않은 경우
			mapAjax.put("result", "logout");
		} else { // 로그인 한 경우
			request.setCharacterEncoding("utf-8");

			RcommentVO rcomment = new RcommentVO();
			rcomment.setRb_num(Integer.parseInt(request.getParameter("rb_num")));
			rcomment.setMem_num(mem_num);
			rcomment.setRc_content(request.getParameter("rc_content"));

			RboardDAO rdao = RboardDAO.getInstance();
			rdao.writeComment(rcomment);

			mapAjax.put("result", "success");
		}

		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);

		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
