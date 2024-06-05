package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class CompletePwAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
				request.setCharacterEncoding("utf-8");
				
				//자바빈 생성해서 전송된 데이터 담고 전달
				MemberVO member = new MemberVO();
				member.setMem_passwd(request.getParameter("NewPw"));
				member.setMem_num(Integer.parseInt(request.getParameter("mem_num")));
				
				//데이터 전달
				MemberDAO dao = MemberDAO.getInstance();
				dao.ReUpdatePw(member);
		 
		return "/WEB-INF/views/member/CompletePw.jsp";
	}

}
