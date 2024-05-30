package kr.rboard.action;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RboardVO;
import kr.util.StringUtil;

public class DetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int rb_num = Integer.parseInt(request.getParameter("rb_num"));
		
		RboardDAO rdao = RboardDAO.getInstance();
		
		//rboard 가져오기
		RboardVO rboard = rdao.getrboard(rb_num);
		//조회수 증가
		rdao.updateReadCount(rb_num);
		
		//D-n 연산
		LocalDate today = LocalDate.now();
		
        // endRecruit 날짜 파싱
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate endRecruit = LocalDate.parse(rboard.getRb_endRecruit(), formatter);

        // D-n 연산 (일수 차이 계산)
        long daysLeft = ChronoUnit.DAYS.between(today, endRecruit);
		
		rboard.setRb_title(StringUtil.useNoHTML(rboard.getRb_title()));
		rboard.setRb_content(StringUtil.useBrNoHTML(rboard.getRb_content()));

		request.setAttribute("rboard", rboard);
		request.setAttribute("daysLeft", daysLeft);
		
		return "/WEB-INF/views/rBoard/detail.jsp";
	}
	
}
