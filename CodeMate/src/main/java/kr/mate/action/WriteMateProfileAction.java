package kr.mate.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.mate.dao.MateDAO;
import kr.mate.vo.MateVO;
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
        
        MemberVO mem = dao.getMember(mem_num);
        
        request.setAttribute("mem", mem);
        request.setAttribute("notice_msg", "메이트 프로필을 수정했습니다");
        
        
        MateVO mate = new MateVO();
        mate.setMem_num(mem_num);
        
        MateDAO mateDao = MateDAO.getInstance();
        
        
        // 체크된 체크 박스 요소를 배열로 받기
        String[] hs_codes = request.getParameterValues("mh_num");
        
        // 체크된 하드 스킬이 있을 때 (배열이 null이 아닐 때)
        if(hs_codes!= null) {
        	for(String code : hs_codes) {
        		int hs_code = Integer.parseInt(code);
        		mate.setHs_code(hs_code);
        		mateDao.insertMateHardSkill(mate);
        	}
        }
        request.setAttribute("notice_url", request.getContextPath() + "/mateProfile/mateProfile.do");
        
        return "/WEB-INF/views/common/alert_view.jsp";
    }
}
