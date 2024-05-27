package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class WriteMateProfileAction implements Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 세션에 로그인된 상태로 있어야 됨
        HttpSession session = request.getSession();
        
        // 로그인 체크
        Integer mem_num = (Integer)session.getAttribute("mem_num");
        if(mem_num == null) { // 로그인이 되지 않은 경우
            return "redirect:/member/loginForm.do";
        }
        
        request.setCharacterEncoding("utf-8");
        
        MemberVO member = new MemberVO();
        member.setMem_num(mem_num);
        member.setMp_introduce(request.getParameter("mp_introduce"));
        
        // 라디오 버튼 값 받기
        String mp_state = request.getParameter("mp_state");
        if (mp_state != null) {
            if (mp_state.equals("비공개")) {
                member.setMp_state(0);
            } else if (mp_state.equals("공개")) {
                member.setMp_state(1);
            }
        }
        
        member.setMp_position(request.getParameter("mp_position"));
        
        MemberDAO dao = MemberDAO.getInstance();
        dao.insertMP(member);
        
        request.setAttribute("notice_msg", "메이트 프로필을 수정했습니다");
        request.setAttribute("notice_url", request.getContextPath() + "/member/mateProfile.do");
        
        return "/WEB-INF/views/common/alert_view.jsp";
    }
}
