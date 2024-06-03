package kr.rboard.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RboardVO;

public class SearchResultsAction implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 검색 조건을 받음
		System.out.println("check1");
		System.out.println(request);
		request.setCharacterEncoding("utf-8");
        String[] r_skills = request.getParameterValues("r_skills");
        String rb_category = request.getParameter("rb_category");
        String r_fields = request.getParameter("r_fields");
        String rb_meet = request.getParameter("rb_meet");
        String search_key = request.getParameter("search_key");
        boolean recruiting_filter = Boolean.parseBoolean(request.getParameter("recruiting_filter"));

        // 검색 조건을 이용해 데이터베이스에서 결과를 조회
        RboardDAO rdao = RboardDAO.getInstance();
        List<RboardVO> rboardList = rdao.searchRboards(0, 10, r_skills, rb_category, r_fields, rb_meet, search_key, recruiting_filter);

		//Map 생성
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		mapAjax.put("rboardList", rboardList);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		System.out.println("r_fields"+r_fields);
		System.out.println("rb_category"+rb_category);
		System.out.println("rb_meet"+rb_meet);
		System.out.println("r_skills"+r_skills);
		System.out.println("search_key"+search_key);
		System.out.println("recruiting_filter"+recruiting_filter);
		return "/WEB-INF/views/common/ajax_view.jsp";
    }
	

}
