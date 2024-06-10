package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cboard.dao.CboardDAO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.rboard.dao.RboardDAO;
import kr.tboard.dao.TboardDAO;
import kr.tmember.vo.TmemberVO;
import kr.util.FileUtil;

public class DeleteUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		if (mem_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		//로그인이 된 경우
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");

		MemberDAO dao = MemberDAO.getInstance();
		MemberVO db_vo = dao.getMember(mem_num);
		//전송된 데이터 반환
		String input_id = request.getParameter("id");
		String input_passwd = request.getParameter("passwd");
		
		int check = 0;
		if(!input_id.equals(db_vo.getMem_id()) || !input_passwd.equals(db_vo.getMem_passwd())) {
			check = 0;
			request.setAttribute("check", check);
			return "/WEB-INF/views/member/deleteUser.jsp";
		}else {
			check=1;
			request.setAttribute("check", check);
		}
		
		
//		MemberVO db_member = dao.checkMember(mem_id);
//		if(db_member == null) {
//			return "/WEB-INF/views/main/main.jsp";
//		}
		//사용자가 입력한 아이디가 존재하고 로그인한 아이디와 일치하는지 체크,
		
		
		RboardDAO rdao = RboardDAO.getInstance();//후에 RboardDAO로 바꾸기!!
		
		//팀 내 등급 확인
		TboardDAO tdao = TboardDAO.getInstance();
		TmemberVO t_member = tdao.getTmemberAuth(mem_num);
		
		CboardDAO cdao = CboardDAO.getInstance();
		
		Integer tm_auth = null;
		if (t_member != null) {
			tm_auth = (Integer)t_member.getTm_auth();
		}
		
		if(check == 1 && (tm_auth == null || tm_auth!=4)) {//인증 성공 + 팀장이 아님
			//회원정보 삭제
			System.out.println("mem_num: " + mem_num);
			dao.deleteMember(mem_num);
			//프로필 사진 삭제				디비나 세션중에서 photo빼와도됨(여기선 디비 사용)
			FileUtil.removeFile(request, db_vo.getMem_photo());
			//Rboard 삭제
			rdao.deleteUserRboard(mem_num);
			//Tboard 삭제
			tdao.deleteTeamMember(mem_num);
			//Cboard 삭제
			cdao.deleteUserCboard(mem_num);
			
			 //로그아웃 
			session.invalidate();
			 
			return "/WEB-INF/views/member/deleteUser.jsp";
		} else { //회원탈퇴 실패
			
			if(tm_auth == 4) {//팀장 직급일 경우 팀 설정으로 들어가 팀장직을 위임하도록 함
				return "redirect:/member/myTeam.do";
			}
			
//			return "redirect:/member/modifyUserForm.do";
		}
		
		return null;
	}

}
