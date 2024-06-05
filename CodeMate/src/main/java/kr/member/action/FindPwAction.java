package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class FindPwAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
				request.setCharacterEncoding("utf-8");
				//전송된 데이터 반환
				String mem_id = request.getParameter("mem_id");
				String mem_phone = request.getParameter("mem_phone");
				String mem_email = request.getParameter("mem_email");
				
				
				MemberDAO dao = MemberDAO.getInstance();
				MemberVO member = dao.SelectPw(mem_id,mem_phone, mem_email);
				
		
				if(member == null) {
					request.setAttribute("ckPw", 0);
				}else {
					request.setAttribute("ckPw", 1);
					request.setAttribute("mem_num", member.getMem_num());
				}
				
				return "/WEB-INF/views/member/findPw.jsp";
	}

}
