package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cboard.dao.CboardDAO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.rboard.dao.RboardDAO;
import kr.rboard.dao.rBoardDAO2;
import kr.rboard.vo.RboardVO;
import kr.tboard.dao.TboardDAO;
import kr.tmember.vo.TmemberVO;
import kr.util.FileUtil;

public class DeleteUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		
		//로그인이 된 경우
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		
		//컬럼명 맞는지 확인해야되고 최종때 회원탈퇴 다시 작성
		//DeleteUserFormAction/DeleteUserForm.jsp/DeleteUserForm/DeleteUser.jsp
		String mem_id = (String)session.getAttribute("mem_id");
		//String mem_passwd = request.getParameter("mem_passwd");
		//로그인한 아이디
		//String user_id = (String)session.getAttribute("user_id");//mem으로 안고쳐도 되겟지
		System.out.println(mem_id);
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO db_member = dao.checkMember(mem_id);
		if(db_member == null) {
			return "/WEB-INF/views/main/main.jsp";
		}
		//사용자가 입력한 아이디가 존재하고 로그인한 아이디와 일치하는지 체크,
		
		
		RboardDAO rdao = RboardDAO.getInstance();//후에 RboardDAO로 바꾸기!!
		
		//팀 내 등급 확인
		TboardDAO tdao = TboardDAO.getInstance();
		TmemberVO t_member = tdao.getTmemberAuth(mem_id);
		
		CboardDAO cdao = CboardDAO.getInstance();
		
		int tm_auth = t_member.getTm_auth();
		
		if(db_member != null && tm_auth!=4) {//인증 성공 + 팀장이 아님
			//회원정보 삭제
			System.out.println("mem_num: " + mem_num);
			dao.deleteMember(mem_num);
			//프로필 사진 삭제				디비나 세션중에서 photo빼와도됨(여기선 디비 사용)
			FileUtil.removeFile(request, db_member.getMem_photo());
			//Rboard 삭제
			rdao.deleteUserRboard(mem_num);
			//Tboard 삭제
			tdao.deleteTeamMember(mem_id);
			//Cboard 삭제
			cdao.deleteUserCboard(mem_id);
			/*
			 * //로그아웃 session.invalidate();
			 */
			return "/WEB-INF/views/member/deleteUser.do";
		}
		if(tm_auth == 4) {//팀장 직급일 경우 팀 설정으로 들어가 팀장직을 위임하도록 함
			return "redirect:/member/myTeam.do";
		}
		
		//인증 실패
//		request.setAttribute("mem_num", mem_num);
		//JSP 경로 반환
		
		return null;
		
	}

}
