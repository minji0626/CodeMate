package kr.team.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.TeamDAO;
import kr.member.vo.TeamVO;
import kr.tmember.dao.TmemberDAO;
import kr.tmember.vo.TmemberVO;

public class TeamMainAction implements Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	request.setCharacterEncoding("utf-8");
    	
        HttpSession session = request.getSession();
        Integer mem_num = (Integer) session.getAttribute("mem_num");
        Integer tm_auth = null;
        
        // 사용자가 클릭한 팀 번호를 가져옵니다.
        int team_num = Integer.parseInt(request.getParameter("team_num"));
        TeamDAO dao = TeamDAO.getInstance();
        TmemberDAO tmdao = TmemberDAO.getInstance();
        
        if (mem_num == null) {// 로그인 미실시
            return "redirect:/member/loginForm.do";
        }
        
        TeamVO teams = dao.getTeam(team_num);
		if (teams == null || teams.getTeam_status() == 0) {
		    // 팀이 존재하지 않는 경우
		    request.setAttribute("notice_msg", "팀이 존재하지 않거나 비활성화 팀입니다. 참여중인 팀 페이지로 돌아갑니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/member/myTeam.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		int team_status = teams.getTeam_status();
		
		TeamVO review = dao.getUserTeam(mem_num, team_num);
		int ctmember = tmdao.getTmemberCount(team_num);
		
		if(review.getTm_review_status() == 0 && teams.getTeam_status() == 3 && ctmember > 1) {
			request.setAttribute("alert", 1);
		}
		
        
        TeamVO teammember = dao.getTeamMember(mem_num, team_num);
        if(teammember == null) {
        	request.setAttribute("notice_msg", "잘못된 접근입니다. 참여중인 팀 페이지로 돌아갑니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/member/myTeam.do");
			return "/WEB-INF/views/common/alert_view.jsp";
        }        
        
        // TeamDAO를 사용하여 사용자의 권한 정보를 가져옵니다.
        TeamVO team = dao.getUserTeam(mem_num,team_num);
        if (team != null) {
                tm_auth = team.getTm_auth();
         }

        session.setAttribute("tm_auth", tm_auth);
        session.setAttribute("team_num", team_num);
        session.setAttribute("team_status", team_status);

        // 팀의 홈페이지로 이동합니다.
        return "/WEB-INF/views/team/teamTo_Do.jsp";
    }
}
