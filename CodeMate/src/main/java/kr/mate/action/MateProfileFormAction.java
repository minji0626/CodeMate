package kr.mate.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.db.dao.DBDAO;
import kr.db.vo.FieldVO;
import kr.db.vo.HardSkillVO;
import kr.db.vo.SoftSkillVO;
import kr.mate.dao.MateDAO;
import kr.mate.vo.MateVO;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
public class MateProfileFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션에 로그인된 상태로 있어야 됨
		HttpSession session = request.getSession();

		// 로그인 체크
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(mem_num == null) { // 로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}

		int user_num = Integer.parseInt(request.getParameter("mem_num"));
		
		if(mem_num!=user_num) {
			request.setAttribute("notice_msg","본인만 수정할 수 있습니다!");
			request.setAttribute("notice_url", request.getContextPath()+"/mateProfile/mateProfile.do?mem_num="+user_num);
			return "/WEB-INF/views/common/alert_view.jsp";
		}

		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.detailMP(user_num);

		DBDAO dbdao = DBDAO.getInstance();

		List<HardSkillVO> hskillList = dbdao.getHardSkillList();
		List<SoftSkillVO> sskillList = dbdao.getSoftSkillList();

		MemberVO mem = dao.getMember(user_num);

		MateDAO mateDAO = MateDAO.getInstance();
		List<MateVO> checkHardSkillList = mateDAO.getListMatHardSkill(user_num);
		List<MateVO> checkSoftSkillList = mateDAO.getListMatSoftSkill(user_num);

		// 이전에 선택한 하드 스킬 코드 값을 추출하여 리스트에 저장
		List<Integer> checkedHardSkills = new ArrayList<>();
		for(MateVO mate : checkHardSkillList) {
			checkedHardSkills.add(mate.getHs_code());
		}

		// 이전에 선택한 하드 스킬 코드 값을 추출하여 리스트에 저장
		List<Integer> checkedSoftSkills = new ArrayList<>();
		for(MateVO mate : checkSoftSkillList) {
			checkedSoftSkills.add(mate.getSs_code());
		}

		
		
		// JSP로 전달할 속성 설정
		request.setAttribute("checkHardSkillList", checkHardSkillList);
		request.setAttribute("user_num", user_num);
		request.setAttribute("mem", mem);
		request.setAttribute("member", member);
		request.setAttribute("sskillList", sskillList);
		request.setAttribute("hskillList", hskillList);
		request.setAttribute("checkedHardSkills", checkedHardSkills); // 체크된 하드 스킬 코드 리스트를 JSP로 전달
		request.setAttribute("checkedSoftSkills", checkedSoftSkills); 

		return "/WEB-INF/views/mateProfile/mateProfileForm.jsp";
	}

}
