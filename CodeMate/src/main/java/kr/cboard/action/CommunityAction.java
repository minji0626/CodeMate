package kr.cboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

public class CommunityAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 커뮤니티 (자유 게시판으로 이동)
		return "/WEB-INF/views/cBoard/freeBoard.jsp";
	}

}
