package kr.mate.action;

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

public class WriteMateProfileAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션에 로그인된 상태로 있어야 됨
		HttpSession session = request.getSession();

		// 로그인 체크
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(mem_num == null) { // 로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		request.setCharacterEncoding("utf-8");
		int user_num = Integer.parseInt(request.getParameter("mem_num"));
        
        if(mem_num!=user_num) {
        	request.setAttribute("notice_msg","본인만 수정할 수 있습니다!");
			request.setAttribute("notice_url", request.getContextPath()+"/mateProfile/mateProfile.do?mem_num="+user_num);
			return "/WEB-INF/views/common/alert_view.jsp";
        }

		

		// 자기소개 
		MemberVO member = new MemberVO();
		member.setMem_num(user_num);
		member.setMp_introduce(request.getParameter("mp_introduce"));

		// 라디오 버튼 값 받기
		String mp_state = request.getParameter("mp_state");
		if (mp_state != null) {
			if (mp_state.equals("비공개")) {
				member.setMp_state(0);
			} else if (mp_state.equals("공개")) {
				member.setMp_state(1);
			}
		}

		member.setMp_position(request.getParameter("mp_position"));

		MemberDAO dao = MemberDAO.getInstance();
		dao.insertMP(member);

		MemberVO mem = dao.getMember(user_num);

		// ----------------------------------------------------------
		// 스킬 

		MateDAO mateDao = MateDAO.getInstance();

		// MateVO 객체 생성 및 초기화
		MateVO mate = new MateVO();
		mate.setMem_num(user_num);

		// 하드 스킬 초기화
		mateDao.deleteMateHardSkill(mate);

		// 새로운 하드 스킬 추가
		String[] hsCodes = request.getParameterValues("hs_codes");
		if (hsCodes != null) {
			for (String code : hsCodes) {
				int hs_code = Integer.parseInt(code);
				mate.setHs_code(hs_code);
				mateDao.insertMateHardSkill(mate);
			}
		} else {
			System.out.println("없음.");
		}

		// 소프트 스킬 초기화   	    
		mateDao.deleteMateSoftSkill(mate);

		// 새로운 소프트 스킬 추가
		String[] ssCodes = request.getParameterValues("ss_codes");
		if (ssCodes != null) {
			for (String code : ssCodes) {
				int ss_code = Integer.parseInt(code);
				mate.setSs_code(ss_code);
				mateDao.insertMateSoftSkill(mate);
			}
		} else {
			System.out.println("없음.");
		}

		DBDAO dbdao = DBDAO.getInstance();
		List<HardSkillVO> hskillList = dbdao.getHardSkillList();
		List<SoftSkillVO> sskillList = dbdao.getSoftSkillList();
		
		

		// 메이트 리뷰 불러오기
		List<MateVO> mateReview = mateDao.getMateReview(user_num);
		
		
		//----------------------------------------------------------
	
		request.setAttribute("user_num", user_num);
		request.setAttribute("mateReview", mateReview);
		request.setAttribute("sskillList", sskillList);
		request.setAttribute("hskillList", hskillList);
		request.setAttribute("mem", mem);
		request.setAttribute("notice_msg", "메이트 프로필이 수정되었습니다.");
		request.setAttribute("notice_url", request.getContextPath() + "/mateProfile/mateProfile.do?mem_num="+user_num);

		return "/WEB-INF/views/common/alert_view.jsp";
	}
}