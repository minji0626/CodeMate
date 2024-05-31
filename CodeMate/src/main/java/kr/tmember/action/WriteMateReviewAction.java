package kr.tmember.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.tmember.dao.TmemberDAO;
import kr.tmember.vo.MateReviewVO;

public class WriteMateReviewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer) session.getAttribute("mem_num");


		if (mem_num == null) { // 로그인하지 않은 경우
			return "redirect:/member/loginForm.do";
		} 

		// 로그인 한 경우
			request.setCharacterEncoding("utf-8");

			MateReviewVO mr = new MateReviewVO();

			int team_num = Integer.parseInt(request.getParameter("team_num"));
			int mr_receiver = Integer.parseInt(request.getParameter("mr_receiver"));

			mr.setMr_writer(mem_num);
			mr.setMr_receiver(mr_receiver);
			mr.setMr_content(request.getParameter("mr_content"));
			
			TmemberDAO dao = TmemberDAO.getInstance();
			dao.insertMateReview(mr);

			request.setAttribute("notice_msg", "메이트 리뷰를 작성했습니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/team/teamSetting.do?team_num="+team_num);

			return "/WEB-INF/views/common/alert_view.jsp";
	}

}

