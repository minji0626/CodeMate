package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class FindIdAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환

		String mem_phone = request.getParameter("mem_phone");
		String mem_email = request.getParameter("mem_email");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.findId(mem_phone, mem_email);
		
		
		
		if(member == null) {
			request.setAttribute("ckId", 0);
			return "/WEB-INF/views/member/findId.jsp";
		}else {
			request.setAttribute("ckId", 1);
			request.setAttribute("mem_id",member.getMem_id());
		}
		
	      
		return "/WEB-INF/views/member/findId.jsp";
	}

}
