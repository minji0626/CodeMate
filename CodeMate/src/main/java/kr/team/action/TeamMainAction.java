package kr.team.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.TeamDAO;
import kr.member.vo.TeamVO;

public class TeamMainAction implements Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Integer mem_num = (Integer) session.getAttribute("mem_num");
        Integer tm_auth = null;
        // 사용자가 클릭한 팀 번호를 가져옵니다.
        int team_num = Integer.parseInt(request.getParameter("team_num"));

        if (mem_num == null) {// 로그인 미실시
            return "redirect:/member/loginForm.do";
        }
		/*
		 * if(team_status == 0) {
		 *  팀이 비활성화 상태라면 접근 불가하다고 alert 띄워주기
		 * }
		 */
        else {
            // TeamDAO를 사용하여 사용자의 권한 정보를 가져옵니다.
            TeamDAO dao = TeamDAO.getInstance();
            TeamVO team = dao.getUserTeam(mem_num,team_num);
            if (team != null) {
                tm_auth = team.getTm_auth();
            }
        }
        // 가져온 권한 정보를 세션에 저장합니다.
        session.setAttribute("tm_auth", tm_auth);
        
        // 가져온 팀 정보를 세션에 저장합니다.
        session.setAttribute("team_num", team_num);

        // 팀의 홈페이지로 이동합니다.
        return "/WEB-INF/views/team/teamTo_Do.jsp";
    }
}
