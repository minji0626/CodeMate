package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class CheckDuplicatedNicknameAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");

		// 전송된 데이터 반환
		String mem_nickname = request.getParameter("mem_nickname");
		
		//mem_num 구하기
		HttpSession session = request.getSession();
		Integer mem_num = (Integer) session.getAttribute("mem_num");

		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkNickname(mem_nickname, mem_num);

		Map<String, String> mapAjax = new HashMap<String, String>();

		if (member == null) { // 아이디 미중복
			mapAjax.put("result", "nicknameNotFound");
		} else { // 아이디 중복

			mapAjax.put("result", "nicknameDuplicated");
		}

		ObjectMapper mapper = new ObjectMapper();

		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);

		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
