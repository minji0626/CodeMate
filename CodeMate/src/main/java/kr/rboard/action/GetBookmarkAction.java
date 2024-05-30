package kr.rboard.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RbookmarkVO;

public class GetBookmarkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		int rb_num = Integer.parseInt(request.getParameter("rb_num"));
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		RboardDAO rdao = RboardDAO.getInstance();
		if (mem_num==null) { //로그인이 되지 않은 경우
			mapAjax.put("status", "noRbookmark");
		} else { //로그인 된 경우
			RbookmarkVO db_rbookmark = rdao.getRbookmark(new RbookmarkVO(rb_num, mem_num));
			if (db_rbookmark!=null) {
				//로그인한 회원이 북마크함
				mapAjax.put("status", "yesRbookmark");
			} else {
				//로그인한 회원이 북마크 하지 않음
				mapAjax.put("status", "noRbookmark");
			}
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
