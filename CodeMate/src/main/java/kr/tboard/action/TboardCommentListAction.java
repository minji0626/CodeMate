package kr.tboard.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.tboard.dao.TboardDAO;
import kr.tboard.vo.TboardCommentVO;
import kr.util.PagingUtil;

public class TboardCommentListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		String rowCount = request.getParameter("rowCount");
		if(rowCount == null) rowCount = "10";
		
		int tb_num = Integer.parseInt(request.getParameter("tb_num"));
		
		TboardDAO dao = TboardDAO.getInstance();
		int count = dao.getCommentTboardCount(tb_num);
		
//		ajax 방식으로 목록을 표시하기 때문에 PagingUtil은 페이지수 표시가 목적이 아니라 목록 데이터의 페이지 처리를 위해 rownum 번호를 구하는 것이 목적이다.
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, Integer.parseInt(rowCount));
		List<TboardCommentVO> list = null;
		if(count > 0) {
			list = dao.getListCommentTboard(page.getStartRow(), page.getEndRow(), tb_num);
		} else {
			list = Collections.emptyList();
		}
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		mapAjax.put("count", count);
		mapAjax.put("list", list);
//		로그인한 사람이 작성자인지 체크하기 위해서 로그인한 회원 번호 전송
		mapAjax.put("mem_num", mem_num);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
