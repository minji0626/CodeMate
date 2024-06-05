package kr.tboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.tboard.dao.TboardDAO;
import kr.tboard.vo.TboardVO;
import kr.util.FileUtil;

public class TboardDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		if(mem_num == null) {// 로그인 미실시
			return "redirect:/member/loginForm.do";
		}
		
		// 로그인 된 경우
		int tb_num = Integer.parseInt(request.getParameter("tb_num"));
		int team_num = Integer.parseInt(request.getParameter("team_num"));
		
		TboardDAO dao = TboardDAO.getInstance();
		TboardVO db_board = dao.detailTboard(tb_num,team_num);
		
		if(mem_num != db_board.getMem_num()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		// 로그인한 회원 번호와 작성자 회원 번호가 동일한 경우
		dao.deleteTboard(tb_num,team_num);
		
		// 파일 삭제
		FileUtil.removeFile(request, db_board.getTb_file());
		
		request.setAttribute("notice_msg", "글 삭제가 완료되었습니다.");
		request.setAttribute("notice_url", request.getContextPath()+"/team/teamBoardList.do?team_num="+team_num);
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}
}
