package kr.admin.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.consult.dao.ConsultDAO;
import kr.controller.Action;

public class ConfirmConsultsAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션에 로그인된 상태로 있어야됨
		HttpSession session = request.getSession();
		// 로그인 체크
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		Integer mem_auth = (Integer) session.getAttribute("mem_auth");
		if (mem_num == null) {// 로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";

		} 
		
		Map<String, String> mapAjax = new HashMap<String, String>();
		if (mem_auth != 9) { //관리자로 로그인 안된 경우
			mapAjax.put("result", "wrongAccess");
		} else {
			request.setCharacterEncoding("utf-8");
			String[] consults = request.getParameterValues("cs_num_list");
			int confirm = Integer.parseInt(request.getParameter("confirm")); //0이면 unconfirm 1이면 confirm 실행
			ConsultDAO cdao = ConsultDAO.getInstance();
			
			if (confirm == 0) {
				cdao.unconfirmConsults(consults);
				mapAjax.put("result_msg", "문의 처리를 취소했습니다.");
			} else if (confirm==1) {
				cdao.confirmConsults(consults);
				mapAjax.put("result_msg", "문의를 처리했습니다.");
			}
			mapAjax.put("result", "success");

		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
