package kr.tboard.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.TeamDAO;
import kr.member.vo.TeamVO;
import kr.tboard.dao.TboardDAO;
import kr.tboard.vo.TboardVO;
import kr.util.PagingUtil;

public class TboardListAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pageNum = request.getParameter("pageNum");
        if (pageNum == null) pageNum = "1";

        String keyfield = request.getParameter("keyfield");
        String keyword = request.getParameter("keyword");
        int team_num = Integer.parseInt(request.getParameter("team_num"));

        TboardDAO dao = TboardDAO.getInstance();
        int count = dao.getTboardCount(keyfield, keyword, team_num);

        PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 10, 10, "teamBoardList.do?team_num=" + team_num);

        List<TboardVO> list = null;
        if (count > 0) {
            list = dao.getListBoard(page.getStartRow(), page.getEndRow(), keyfield, keyword, team_num);
        }
        

		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		TeamDAO tdao = TeamDAO.getInstance();
		Integer team_num2 = (Integer)session.getAttribute("team_num");
		
		
		
		if(mem_num == null) {// 로그인 미실시
			return "redirect:/member/loginForm.do";
		}
		
		
		TeamVO teams = tdao.getTeam(team_num);
		if (teams == null || teams.getTeam_status() == 0) {
		    // 팀이 존재하지 않는 경우
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script type=\"text/javascript\">");
		    out.println("var contextPath = '" + request.getContextPath() + "';");
		    out.println("alert('팀이 존재하지 않거나 비활성화 팀입니다. 참여중인 팀 페이지로 돌아갑니다.');");
		    out.println("window.location.href = contextPath + '/member/myTeam.do';");
		    out.println("</script>");
		    return null;
		}
		
		TeamVO teammember = tdao.getTeamMember(mem_num, team_num);
	        if(teammember == null) {
	        	response.setContentType("text/html; charset=UTF-8");
	        	PrintWriter out = response.getWriter();
	        	out.println("<script type=\"text/javascript\">");
	        	out.println("var contextPath = '" + request.getContextPath() + "';");
	        	out.println("alert('잘못된 접근입니다. 참여중인 팀으로 돌아갑니다.');");
	        	out.println("window.location.href = contextPath + '/member/myTeam.do';");
	        	out.println("</script>");

	            return null;
	        } 
        
        

        request.setAttribute("count", count);
        request.setAttribute("list", list);
        request.setAttribute("page", page.getPage());
        request.setAttribute("team_num", team_num);

        return "/WEB-INF/views/team/teamBoard.jsp";
    }
}
