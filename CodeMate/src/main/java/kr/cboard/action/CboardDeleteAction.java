package kr.cboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cboard.dao.CboardDAO;
import kr.cboard.vo.CboardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class CboardDeleteAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Integer mem_num = (Integer) session.getAttribute("mem_num");
        
        if (mem_num == null) { // 로그인이 되지 않은 경우
            return "redirect:/member/loginForm.do";
        }

        // 로그인 된 경우
        int cb_num = Integer.parseInt(request.getParameter("cb_num"));
        CboardDAO dao = CboardDAO.getInstance();
        CboardVO db_board = dao.detailCboard(cb_num);

        Integer mem_auth = (Integer) session.getAttribute("mem_auth");
        
        if(mem_auth==9) { // 관리자
        	 dao.deleteCboard(cb_num);
             // 파일 삭제
             FileUtil.removeFile(request, db_board.getCb_file());
             
             request.setAttribute("notice_msg", "글 삭제 완료");
             request.setAttribute("notice_url", request.getContextPath() + "/cboard/community.do?cb_type=0");
             return "/WEB-INF/views/common/alert_view.jsp";
        } else if (!mem_num.equals(db_board.getMem_num())) { // 로그인한 회원번호와 작성자 회원번호 일치 여부 체크
            request.setAttribute("notice_msg", "작성자만 삭제할 수 있습니다.");
            request.setAttribute("notice_url", request.getContextPath() + "/cboard/communityDetail.do?cb_num=" + cb_num);
            return "/WEB-INF/views/common/alert_view.jsp";
        } else {
            // 로그인한 회원번호와 작성자 회원번호 일치
            dao.deleteCboard(cb_num);
            // 파일 삭제
            FileUtil.removeFile(request, db_board.getCb_file());
            
            request.setAttribute("notice_msg", "글 삭제 완료");
            request.setAttribute("notice_url", request.getContextPath() + "/cboard/community.do?cb_type=0");
            return "/WEB-INF/views/common/alert_view.jsp";
        }
    }
}
