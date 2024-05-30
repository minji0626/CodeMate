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

public class ToggleBookmarkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		if (mem_num==null) { //로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		} else { //로그인이 된 경우
			//전송된 데이터 인코딩 타입 지정
			request.setCharacterEncoding("utf-8");
			int rb_num = Integer.parseInt(request.getParameter("rb_num"));
			
			RbookmarkVO rbookmark = new RbookmarkVO();
			rbookmark.setRb_num(rb_num);
			rbookmark.setMem_num(mem_num);
			
			RboardDAO rdao = RboardDAO.getInstance();
			//좋아요 등록 여부 체크
			RbookmarkVO db_rbookmark = rdao.getRbookmark(rbookmark);
			if (db_rbookmark!=null) { //좋아요 등록 O
				//좋아요 삭제
				rdao.deleteRbookmark(db_rbookmark);
				mapAjax.put("status", "noRbookmark");
			} else { //좋아요 등록 X
				//좋아요 등록
				rdao.insertRbookmark(rbookmark);
				mapAjax.put("status", "yesRbookmark");
			}
			mapAjax.put("result", "success");
			mapAjax.put("count", rdao.getRbookmarkCount(rb_num));
		}
		
		//json 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
	
}
