package kr.admin.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;

public class ChangeAuthToAdminToggleAction implements Action {

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
		if (mem_auth != 9) { // 관리자로 로그인 안된 경우
			mapAjax.put("result", "wrongAccess");
		} else {
			request.setCharacterEncoding("utf-8");
			int change_mem_num = Integer.parseInt(request.getParameter("mem_num"));
			int change_mem_auth = Integer.parseInt(request.getParameter("mem_auth")); // 0:정지 안됨, 1:정지됨
			
			MemberDAO mdao = MemberDAO.getInstance();
			mdao.changeAuthToAdminToggle(change_mem_num, change_mem_auth);
			if (change_mem_auth == 9) { //관리자 -> 일반회원
				mapAjax.put("result_msg", "회원을 일반회원으로 변경했습니다.");
			} else if (change_mem_auth == 2) { //일반회원 -> 관리자
				mapAjax.put("result_msg", "회원을 관리자로 변경했습니다.");
			}
			mapAjax.put("result", "success");
		}

		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
