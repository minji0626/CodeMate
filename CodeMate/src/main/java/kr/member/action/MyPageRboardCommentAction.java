package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RcommentVO;

public class MyPageRboardCommentAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//세션에 로그인된 상태로 있어야됨
		HttpSession session = request.getSession();
		//로그인 체크
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(mem_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.getMember(mem_num);
		
		//모집 게시물 정보
		RboardDAO rboardDAO = RboardDAO.getInstance(); 
		//List 반환
		List<RcommentVO> rcommentList = rboardDAO.getRcommentListByMemNum(mem_num);
		//지금 적어놓은거는 detail에서 보는거고 내가 필요한거는 다시 만들어야됨
		
		request.setAttribute("member", member);
		request.setAttribute("rcommentList", rcommentList);
		
		
		
		//JSP경로 반환
		return "/WEB-INF/views/member/myPageRboardComment.jsp";
	}

}
