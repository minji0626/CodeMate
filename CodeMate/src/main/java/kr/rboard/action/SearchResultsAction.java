package kr.rboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

public class SearchResultsAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 검색 조건을 받음
		
        String[] r_skills = request.getParameterValues("r_skills");
        String r_fields = request.getParameter("r_fields");
        String rb_meet = request.getParameter("rb_meet");
        String search_key = request.getParameter("search_key");
        boolean bookmark_filter = Boolean.parseBoolean(request.getParameter("bookmark_filter"));
        boolean recruiting_filter = Boolean.parseBoolean(request.getParameter("recruiting_filter"));

        // 검색 조건을 이용해 데이터베이스에서 결과를 조회
//        List<Rboardvo> rboardList = searchRboards(r_skills, r_fields, rb_meet, search_key, bookmark_filter, recruiting_filter);

        // 결과를 JSP로 전달
//        request.setAttribute("rboardList", rboardList);
        return "/WEB-INF/views/rboardList.jsp";
    }
	

}
