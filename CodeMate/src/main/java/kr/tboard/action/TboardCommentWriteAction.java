package kr.tboard.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.cboard.dao.CboardDAO;
import kr.cboard.vo.CcommentVO;
import kr.controller.Action;
import kr.tboard.dao.TboardDAO;
import kr.tboard.vo.TboardCommentVO;

public class TboardCommentWriteAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer) session.getAttribute("mem_num");

		Map<String, String> mapAjax = new HashMap<String, String>();

		if (mem_num == null) { // 로그인하지 않은 경우
			mapAjax.put("result", "logout");
		} else { // 로그인 한 경우
			request.setCharacterEncoding("utf-8");

			TboardCommentVO tbcomment = new TboardCommentVO();
			tbcomment.setTb_num(Integer.parseInt(request.getParameter("tb_num")));
			tbcomment.setMem_num(mem_num);
			tbcomment.setTc_content(request.getParameter("tc_content"));

			TboardDAO tdao = TboardDAO.getInstance();
			tdao.insertCommentTboard(tbcomment);

			mapAjax.put("result", "success");
		}

		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);

		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
