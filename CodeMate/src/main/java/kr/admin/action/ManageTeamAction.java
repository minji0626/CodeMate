package kr.admin.action;

import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kr.controller.Action;
import kr.db.dao.DBDAO;
import kr.db.vo.FieldVO;
import kr.db.vo.HardSkillVO;
import kr.rboard.dao.ApplyDAO;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RboardVO;
import kr.util.PagingUtil;

public class ManageTeamAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 세션에 로그인된 상태로 있어야됨
        HttpSession session = request.getSession();
        // 로그인 체크
        Integer mem_num = (Integer) session.getAttribute("mem_num");
        Integer mem_auth = (Integer) session.getAttribute("mem_auth");
        if (mem_num == null) { // 로그인이 되지 않은 경우
            return "redirect:/member/loginForm.do";
        } else if (mem_auth == 9) { // 관리자로 로그인 된 경우
        	String pageNum = request.getParameter("pageNum");
    		if (pageNum == null)
    			pageNum = "1";

    		String keyfield = null; // 검색 로직 추가 후 수정
    		String keyword = null;


    		RboardDAO rdao = RboardDAO.getInstance();
    		int count = rdao.getRboardCount(null, null, null, null, null, false);

            ApplyDAO dao = ApplyDAO.getInstance();

    		// 페이지 처리
    		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 12, 10, request.getContextPath() + "/admin/manageTeam.do");

    		List<RboardVO> rboardList = null;
    		if (count > 0) {
    			rboardList = rdao.getRboardList(page.getStartRow(), page.getEndRow(), null, null, null, null, null, false);
    		}

            // 요소 제거 시 Iterator 사용
            if (rboardList != null) {
                Iterator<RboardVO> it = rboardList.iterator();
                while (it.hasNext()) {
                    RboardVO board = it.next();
                    boolean check = dao.checkActivation(board.getRb_num());
                    if (!check) {
                        it.remove();
                    }
                }
            }

            request.setAttribute("rboardList", rboardList);
            request.setAttribute("count", count);
            request.setAttribute("page", page.getPage());

            return "/WEB-INF/views/admin/manageTeam.jsp";
        } else { // 관리자가 아닌 아이디로 로그인 된 경우
            request.setAttribute("notice_msg", "페이지 접근 권한이 없습니다.");
            request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
            return "/WEB-INF/views/common/alert_view.jsp";
        }
    }
}
