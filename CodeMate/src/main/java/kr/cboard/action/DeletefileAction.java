package kr.cboard.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.cboard.dao.CboardDAO;
import kr.cboard.vo.CboardVO;
import kr.controller.Action;
import kr.tboard.dao.TboardDAO;
import kr.tboard.vo.TboardVO;
import kr.util.FileUtil;

public class DeletefileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(mem_num == null) { // 로그인 되지 않은 경우
			mapAjax.put("result", "logout");
		} else { // 로그인 된 경우
			// 전송된 데이터 인코딩 타입 지정
			request.setCharacterEncoding("utf-8");
			// 전송된 데이터 반환
			int cb_num = Integer.parseInt(request.getParameter("cb_num"));
			
			CboardDAO dao = CboardDAO.getInstance();
			CboardVO db_board = dao.detailCboard(cb_num);
			
			// 로그인한 회원 번호와 작성자 회원 번호 일치 여부 체크
			if(mem_num != db_board.getMem_num()) {
				mapAjax.put("result", "wrongAccess");
			}else {
				dao.deleteFile(cb_num);
				// 파일 삭제
				FileUtil.removeFile(request, db_board.getCb_file());
				
				mapAjax.put("result", "success");
			}
		}  
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
