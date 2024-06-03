package kr.cboard.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.cboard.dao.CboardDAO;
import kr.cboard.vo.ClikeVO;
import kr.controller.Action;

public class ToggleLikeAction implements Action {

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
			int cb_num = Integer.parseInt(request.getParameter("cb_num"));
			
			ClikeVO clike = new ClikeVO();
			clike.setCb_num(cb_num);
			clike.setMem_num(mem_num);
			
			CboardDAO cdao = CboardDAO.getInstance();
			//좋아요 등록 여부 체크
			ClikeVO db_clike = cdao.selectLike(clike);
			if (db_clike!=null) { //좋아요 등록 O
				//좋아요 삭제
				cdao.deleteLike(db_clike);
				mapAjax.put("status", "noLike");
			} else { //좋아요 등록 X
				//좋아요 등록
				cdao.insertLike(clike);
				mapAjax.put("status", "yesLike");
			}
			mapAjax.put("result", "success");
			mapAjax.put("count", cdao.selectLikeCount(cb_num));
		}
		
		//json 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
	
}
