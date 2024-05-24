package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class RegisterUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 전송된 데이터 인코딩 타입 지정
		 request.setCharacterEncoding("utf-8");
		//자바빈(VO)생성
		 MemberVO member = new MemberVO();
		 member.setMem_id(request.getParameter("id"));
		 member.setMem_name(request.getParameter("name"));
		 member.setMem_passwd(request.getParameter("passwd"));
		 member.setMem_phone(request.getParameter("phone"));
		 member.setMem_email(request.getParameter("email"));
		 member.setMem_nickname(request.getParameter("nickname"));
		
		 
		 MemberDAO dao = MemberDAO.getInstance();
		 dao.insertMember(member);
		 
		 request.setAttribute("result_title", "회원 가입 완료");
		 request.setAttribute("result_msg", "회원 가입이 완료되었습니다.");
		 request.setAttribute("result_url", request.getContextPath()+"/main/main.do");
		 
		 
		return "/WEB-INF/views/common/result_view.jsp";
	}
	

}
