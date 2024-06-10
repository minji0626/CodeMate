package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
//import kr.util.SessionManager;

public class LoginAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(id);
		boolean check = false;
		 
		if(member!=null) {//동일한 id 존재
			//비밀번호 일치 여부 체크
			check = member.isCheckedPassword(passwd);
			//정지회원의 경우 상태 표시
			request.setAttribute("auth", member.getMem_auth());
		}
		if(check) {//인증 성공
			//로그인 처리
			HttpSession session = request.getSession();
			session.setAttribute("mem_num", member.getMem_num());
			session.setAttribute("mem_id", member.getMem_id());
			session.setAttribute("mem_photo", member.getMem_photo());
			session.setAttribute("mem_auth", member.getMem_auth());
			session.setAttribute("mem_nickname", member.getMem_nickname());
			session.setAttribute("mem_level", member.getMem_level());
			
//			SessionManager.addUserSession(member.getMem_num(), session);
			//메인으로 리다이렉트
			return "redirect:/main/main.do"; 
		}
		//인증 실패
		return "/WEB-INF/views/member/login.jsp";
	}

}
