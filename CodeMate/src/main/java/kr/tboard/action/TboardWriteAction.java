package kr.tboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.tboard.dao.TboardDAO;
import kr.tboard.vo.TboardVO;
import kr.util.FileUtil;

public class TboardWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		Integer team_num = (Integer) session.getAttribute("team_num");

		if(mem_num == null) {// 로그인 미실시
			return "redirect:/member/loginForm.do";
		} 

		request.setCharacterEncoding("UTF-8");

		TboardVO tboard = new TboardVO();
		tboard.setTb_title(request.getParameter("tb_title"));
		tboard.setTb_content(request.getParameter("tb_content"));
		tboard.setTb_auth(Integer.parseInt(request.getParameter("tb_auth")));
		tboard.setTb_file(FileUtil.createFile(request, "filename"));
		tboard.setTeam_num(team_num);
		tboard.setMem_num(mem_num);
		
		TboardDAO dao = TboardDAO.getInstance();
		dao.insertTboard(tboard);
		
		request.setAttribute("notice_msg", "글 작성이 완료되었습니다.");
		request.setAttribute("notice_url", request.getContextPath()+"/team/teamBoardList.do");
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
