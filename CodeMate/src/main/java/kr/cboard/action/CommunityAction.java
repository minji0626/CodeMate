package kr.cboard.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.cboard.dao.CboardDAO;
import kr.cboard.vo.CboardVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class CommunityAction implements Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
        String keyfield = request.getParameter("keyfield");
        String keyword = request.getParameter("keyword");
        
        String pageNum = request.getParameter("pageNum");
        
        if (pageNum == null) pageNum = "1";

        CboardDAO dao = CboardDAO.getInstance();
        
        int count = dao.getCboardCount(keyfield, keyword);

        PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 10, 10, "community.do");

        List<CboardVO> list = null;
        
        if (count > 0) {
            list = dao.getListBoard(page.getStartRow(), page.getEndRow(), keyfield, keyword);
        }

        request.setAttribute("count", count);
        request.setAttribute("list", list);
        request.setAttribute("page", page.getPage());
        
        // 커뮤니티 (자유 게시판으로 이동)
        return "/WEB-INF/views/cBoard/freeBoard.jsp";
    }

}
