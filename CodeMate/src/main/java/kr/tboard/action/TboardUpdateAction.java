package kr.tboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.tboard.dao.TboardDAO;
import kr.tboard.vo.TboardVO;
import kr.util.FileUtil;

public class TboardUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		if(mem_num == null) {// 로그인 미실시
			return "redirect:/member/loginForm.do";
		}
		
		// 로그인 된 경우
		// 전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("UTF-8");
		// 전송되 데이터 반환
		int tb_num = Integer.parseInt(request.getParameter("tb_num"));
		TboardDAO dao = TboardDAO.getInstance();
		// 수정 전 데이터
		TboardVO db_board = dao.detailTboard(tb_num);
		// 로그인한 회원 번호와 작성자 회원 번호 일치 여부 체크하기
		if(mem_num != db_board.getMem_num()) {
			//  로그인한 회원 번호와 작성자 회원 번호 불일치
			return "/WEB-INF/views/common/notice.jsp";
		} 
			// 로그인한 회원 번호와 작성자 회원 번호 일치
			TboardVO tboard = new TboardVO();
			
			tboard.setTb_num(tb_num);
			tboard.setTb_auth(Integer.parseInt(request.getParameter("tb_auth")));
			tboard.setTb_title(request.getParameter("tb_title"));
			tboard.setTb_content(request.getParameter("tb_content"));
			tboard.setTb_file(FileUtil.createFile(request, "tb_file"));
			
			dao.updateTboard(tboard);
			
			if(tboard.getTb_file()!=null && !"".equals(tboard.getTb_file())) {
				// filename이 널이 아니고, 빈칸이 아니라면
				// 새 파일로 교체할 때 원래 파일을 제거
				FileUtil.removeFile(request, db_board.getTb_file());
			}
			
			request.setAttribute("notice_msg", "글 수정 완료되었습니다.");
			request.setAttribute("notice_url", request.getContextPath()+"/team/TBoardDetail.do?tb_num="+tb_num);
			
		return "/WEB-INF/views/common/alert_view.jsp";
		// redirect:/board/detail.do?board_num="+board_num
	}

}
