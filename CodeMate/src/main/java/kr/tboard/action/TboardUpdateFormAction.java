package kr.tboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.tboard.dao.TboardDAO;
import kr.tboard.vo.TboardVO;
import kr.util.StringUtil;

public class TboardUpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		if(mem_num == null) {// 로그인 미실시
			return "redirect:/member/loginForm.do";
		}
		
		int tb_num = Integer.parseInt(request.getParameter("tb_num"));
		int team_num = Integer.parseInt(request.getParameter("team_num"));
		TboardDAO dao = TboardDAO.getInstance();
		TboardVO tboard = dao.detailTboard(tb_num,team_num);
		if(mem_num!=tboard.getMem_num()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		// 큰 따옴표 처리해주기
		// 수정폼의 input 태그에서 큰 따옴표 오류 보정
		tboard.setTb_title(StringUtil.parseQuot(tboard.getTb_title()));
		
		// 로그인이 되어있고 로그인한 회원 번호와 작성자 회원 번호가 일치할 때
		 request.setAttribute("tboard", tboard);
		 request.setAttribute("team_num", team_num);
		return "/WEB-INF/views/team/teamUpdateForm.jsp";
	}
}
