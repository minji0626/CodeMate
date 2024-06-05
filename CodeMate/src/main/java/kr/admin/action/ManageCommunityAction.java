package kr.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.admin.dao.AdminDAO;
import kr.cboard.vo.CboardVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class ManageCommunityAction implements Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		 if ("3".equals(keyfield)) {
	            if ("자유 게시판".equals(keyword)) {
	                keyword = "0";
	            } else if ("개발 게시판".equals(keyword)) {
	                keyword = "1";
	            } else if ("개".equals(keyword)) {
	                keyword = "1";
	            } else if ("개발".equals(keyword)) {
	                keyword = "1";
	            } else if ("자유".equals(keyword)) {
	                keyword = "0";
	            } else if ("자".equals(keyword)) {
	                keyword = "0";
	            } else if ("유".equals(keyword)) {
	                keyword = "0";
	            } else if ("발".equals(keyword)) {
	                keyword = "1";
	            } 
	        }
		
		AdminDAO dao = AdminDAO.getInstance();
		int count = dao.getCount(keyfield, keyword);
		
		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 20,20,"manageCommunity.do");
		
		List<CboardVO> communityList = null;
		if(count > 0) {
			communityList = dao.getListBoard(page.getStartRow(), page.getEndRow(), keyfield, keyword);
		}
		request.setAttribute("count", count);
		request.setAttribute("communityList", communityList);
		request.setAttribute("page", page.getPage());
        
        // 커뮤니티 (자유 게시판으로 이동)
        return "/WEB-INF/views/admin/manageCommunity.jsp";
    }

}
