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


public class GetLikeAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		int cb_num = Integer.parseInt(
				        request.getParameter("cb_num"));
		
		Map<String,Object> mapAjax = 
				               new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Integer mem_num = 
				(Integer)session.getAttribute("mem_num");
		CboardDAO dao = CboardDAO.getInstance();
		if(mem_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("status", "logout");
		}else {//로그인 된 경우
			ClikeVO cLike = dao.selectLike(new ClikeVO());
			boolean check = dao.memberLike(cb_num, mem_num);
			
			request.setAttribute("check", check);
			if(check) {
				//로그인한 회원이 좋아요를 클릭함
				mapAjax.put("status", "yesLike");
			}else {
				//로그인한 회원이 좋아요를 미클릭
				mapAjax.put("status", "noLike");
			}
		}
		
		//좋아요 개수
		mapAjax.put("count", dao.selectLikeCount(cb_num));
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
