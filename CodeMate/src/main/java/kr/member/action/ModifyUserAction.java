package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class ModifyUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//회원제서비스로 로그인이 된 상태로 호출되야됨
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(mem_num == null) {//로그인이 안 된 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		
		//자바빈 생성해서 전송된 데이터 담고 전달
		MemberVO member = new MemberVO();
		member.setMem_num(mem_num);//회원번호
		member.setMem_name(request.getParameter("mem_name"));
		member.setMem_id(request.getParameter("mem_id"));
		member.setMem_email(request.getParameter("mem_email"));
		member.setMem_nickname(request.getParameter("mem_nickname"));
		member.setMem_phone(request.getParameter("mem_phone"));
		member.setMem_photo(request.getParameter("mem_photo"));
	
		//데이터 전달
		MemberDAO dao = MemberDAO.getInstance();
		dao.updateMember(member);
		
		//정상적으로 수정된걸 알려주는 결과 페이지-자바스크립트
		request.setAttribute("notice_msg", "나의 정보 수정 완료");
		request.setAttribute("notice_url", request.getContextPath()+"/member/modifyUserForm.do");
		//JSP경로 반환 
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
