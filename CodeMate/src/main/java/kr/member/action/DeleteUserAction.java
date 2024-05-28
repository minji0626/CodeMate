package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.util.FileUtil;

public class DeleteUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		if(mem_num == null) {
			return "redirect:/member/loginForm.do";
		}
		//로그인이 된 경우
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		
		//컬럼명 맞는지 확인해야되고 최종때 회원탈퇴 다시 작성
		//DeleteUserFormAction/DeleteUserForm.jsp/DeleteUserForm/DeleteUser.jsp
		String mem_id = request.getParameter("mem_id");
		//String mem_passwd = request.getParameter("mem_passwd");
		//로그인한 아이디
		//String user_id = (String)session.getAttribute("user_id");//mem으로 안고쳐도 되겟지
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO db_member = dao.checkMember(mem_id);
		boolean check = false;
		//사용자가 입력한 아이디가 존재하고 로그인한 아이디와 일치하는지 체크,
		
		if(check) {//인증 성공
			//회원정보 삭제
			dao.deleteMember(mem_num);
			//프로필 사진 삭제				디비나 세션중에서 photo빼와도됨(여기선 디비 사용)
			FileUtil.removeFile(request, db_member.getMem_photo());
			//로그아웃
			session.invalidate();
		}
		
		//인증 실패
		request.setAttribute("check", check);
		//JSP 경로 반환
		
		
		
		return "/WEB-INF/views/member/deleteUser.do";
	}

}
