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

public class CheckDuplicatedEmailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String mem_email = request.getParameter("email");
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkEmail(mem_email,mem_num);
		
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		if(member == null) {//이메일 미중복
			mapAjax.put("result", "emailNotFound");
		}else {//이메일 중복
			mapAjax.put("result", "emailDuplicated");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		//JSON 문자열 반환 -> 클라이언트에게 보내려면 jsp가 필요함
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
