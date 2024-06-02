package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cboard.dao.CboardDAO;
import kr.cboard.vo.CcommentVO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class MyReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//세션에 로그인된 상태로 있어야됨
		HttpSession session = request.getSession();
		//로그인 체크
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(mem_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.getMember(mem_num);
		
		CboardDAO cdao = CboardDAO.getInstance();
		List<CcommentVO> commentList = cdao.myPageApplyListByMemNum(mem_num);
		
		request.setAttribute("member", member);
		request.setAttribute("commentList", commentList);
		
		//JSP 경로 반환
		return "/WEB-INF/views/member/myReply.jsp";
	}

}
