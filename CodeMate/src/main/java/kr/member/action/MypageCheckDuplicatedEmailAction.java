package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class MypageCheckDuplicatedEmailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String mem_email = request.getParameter("mem_email");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkEmail(mem_email);
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		if(member == null) {//이메일 미중복
			mapAjax.put("result", "emailNotFound");
		}else {
			mapAjax.put("result", "emailDuplicated");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		//JSON 문자열 반환
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
