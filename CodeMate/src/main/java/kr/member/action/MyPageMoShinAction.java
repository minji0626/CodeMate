package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RapplyVO;

public class MyPageMoShinAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 세션에 로그인된 상태로 있어야됨
        HttpSession session = request.getSession();
        // 로그인 체크
        Integer mem_num = (Integer) session.getAttribute("mem_num");
        if (mem_num == null) { // 로그인이 되지 않은 경우
            return "redirect:/member/loginForm.do";
        }

        // 로그인이 된 경우
        MemberDAO memberDAO = MemberDAO.getInstance();
        MemberVO member = memberDAO.getMember(mem_num); // 한건의 데이터 읽어옴

        // 모집글에 대한 신청자 리스트 가져오기
        RboardDAO rboardDAO = RboardDAO.getInstance();
        List<RapplyVO> rapplyList = rboardDAO.myRboardApplyList(mem_num);

        // 데이터를 request에 설정
        request.setAttribute("member", member);
        request.setAttribute("rapplyList", rapplyList);

        // JSP 경로 반환
        return "/WEB-INF/views/member/myPageMoShin.jsp";
    }
}
