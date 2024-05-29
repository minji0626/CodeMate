package kr.mate.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.mate.dao.MateDAO;
import kr.mate.vo.MateVO;

public class DeleteEXPAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 세션에 로그인된 상태로 있어야 됨
        HttpSession session = request.getSession();
        
        // 로그인 체크
        Integer mem_num = (Integer)session.getAttribute("mem_num");
        if(mem_num == null) { // 로그인이 되지 않은 경우
            return "redirect:/member/loginForm.do";
        }
        
        int me_num = Integer.parseInt(request.getParameter("me_num"));
        
        MateDAO dao = MateDAO.getInstance();
        dao.deleteMateExp(me_num);

        request.setAttribute("notice_msg", "프로젝트 경험이 삭제되었습니다.");
		request.setAttribute("notice_url", request.getContextPath() + "/mateProfile/mateProfile.do");

		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
