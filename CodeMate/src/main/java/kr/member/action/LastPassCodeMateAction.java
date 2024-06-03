package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.rboard.dao.ApplyDAO;
import kr.rboard.vo.RapplyVO;

public class LastPassCodeMateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, String> mapAjax = new HashMap<String, String>();

		// 세션에 로그인된 상태로 있어야 됨
		HttpSession session = request.getSession();

		// 로그인 체크
		Integer login_num = (Integer)session.getAttribute("mem_num");
		if(login_num == null) { // 로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		} else {
			request.setCharacterEncoding("utf-8");

			int ra_num = Integer.parseInt(request.getParameter("ra_num"));
			int rb_num = Integer.parseInt(request.getParameter("rb_num"));

			RapplyVO apply = new RapplyVO();

			ApplyDAO dao = ApplyDAO.getInstance();
			
			RapplyVO info = dao.applyInfo(ra_num);
			int check = info.getRa_pass();
			if(check==1) {
				mapAjax.put("result", "already");
			} else {
				// 현재 합격자 수
				int passCount = dao.howManyPass(rb_num);
				System.out.println(passCount);
				boolean checking = dao.passAndSize(rb_num, (passCount+1));
				if(checking) {
					 mapAjax.put("result", "last");
				} else {
					dao.passMember(ra_num);
					mapAjax.put("result", "success");
				}
			}
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);

		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}