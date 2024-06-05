package kr.rboard.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RboardVO;
import kr.util.PagingUtil;

public class SearchResultsAction implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		String rowCount = request.getParameter("rowCount");
		if (rowCount == null) {
			rowCount = "12";
		}
		
        // 검색 조건을 받음
        String[] r_skills = request.getParameterValues("r_skills");
        String rb_category = request.getParameter("rb_category");
        String r_fields = request.getParameter("r_fields");
        String rb_meet = request.getParameter("rb_meet");
        String search_key = request.getParameter("search_key");
        boolean recruiting_filter = Boolean.parseBoolean(request.getParameter("recruiting_filter"));

        
        // 검색 조건을 이용해 데이터베이스에서 결과를 조회
		RboardDAO rdao = RboardDAO.getInstance();
		int count = rdao.getRboardCount(r_skills, rb_category, r_fields, rb_meet, search_key, recruiting_filter);

		// 페이지 처리
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, Integer.parseInt(rowCount));
		List<RboardVO> rboardList = null;
		if (count > 0) {
			rboardList = rdao.getRboardList(page.getStartRow(), page.getEndRow(), 
					r_skills, rb_category, r_fields, rb_meet, search_key, recruiting_filter);
		} else {
			rboardList = Collections.emptyList();
		}
        
        
		//Map 생성
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		mapAjax.put("count", count);
		mapAjax.put("rboardList", rboardList);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
    }

}

//이제 ajax 쪽으로 넘어가서 그부분 수정하고 js에서 페이지 처리 보여주는 부분도 수정
//검색어 넣었을 때 잘 되는지

