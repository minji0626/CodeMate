package kr.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.admin.dao.AdminDAO;
import kr.cboard.vo.CboardVO;
import kr.controller.Action;
import kr.db.dao.DBDAO;
import kr.db.vo.FieldVO;
import kr.db.vo.HardSkillVO;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RboardVO;
import kr.util.PagingUtil;

public class ManageRboardAction implements Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 세션에 로그인된 상태로 있어야됨
		HttpSession session = request.getSession();
		// 로그인 체크
		Integer mem_num = (Integer) session.getAttribute("mem_num");
		Integer mem_auth = (Integer) session.getAttribute("mem_auth");
		if (mem_num == null) {// 로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";

		} else if (mem_auth == 9) { //관리자로 로그인 된 경우

			String pageNum = request.getParameter("pageNum");
			if (pageNum == null)
				pageNum = "1";

			String keyfield = request.getParameter("keyfield");
			String keyword = request.getParameter("keyword");


			RboardDAO rdao = RboardDAO.getInstance();
			int count = rdao.getRboardCountManage(keyfield, keyword);

			// 페이지 처리
			PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 12, 10,request.getContextPath() + "/admin/manageRboard.do");

			List<RboardVO> rboardList = null;
			if (count > 0) {
				rboardList = rdao.getListBoardManage(page.getStartRow(), page.getEndRow(), keyfield, keyword);
			}

			request.setAttribute("rboardList", rboardList);
			request.setAttribute("count", count);
			request.setAttribute("page", page.getPage());


			return "/WEB-INF/views/admin/manageRboard.jsp";
			
		} else { //관리자가 아닌 아이디로 로그인 된 경우
			request.setAttribute("notice_msg", "페이지 접근 권한이 없습니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			return "/WEB-INF/views/common/alert_view.jsp";
		}


	}

}