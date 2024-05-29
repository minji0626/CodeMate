package kr.tboard.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.controller.Action;
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

        request.setAttribute("count", count);
        request.setAttribute("list", list);
        request.setAttribute("page", page.getPage());
        request.setAttribute("team_num", team_num);

        return "/WEB-INF/views/team/teamBoard.jsp";
    }
}
