package kr.mate.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import kr.controller.Action;
import kr.db.vo.SoftSkillVO;
import kr.mate.dao.MateDAO;
import kr.mate.vo.MateVO;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class MateProfileAction implements Action {
	// 하
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 세션에 로그인된 상태로 있어야 됨
        HttpSession session = request.getSession();
        
        // 로그인 체크
        Integer mem_num = (Integer)session.getAttribute("mem_num");
        if(mem_num == null) { // 로그인이 되지 않은 경우
            return "redirect:/member/loginForm.do";
        }
        
        
        MemberDAO dao = MemberDAO.getInstance();
        MemberVO member = dao.detailMP(mem_num);
        
        MemberVO mem = dao.getMember(mem_num);
        
        MateDAO mateDAO = MateDAO.getInstance();
        List<MateVO> hardSkillList = null;
        hardSkillList = mateDAO.getListMatHardSkill(mem_num);
        
        List<MateVO> softSkillList = null;
        softSkillList = mateDAO.getListMatSoftSkill(mem_num);
        
        List<MateVO> mateExpList = null;
        mateExpList = mateDAO.getMateExp(mem_num);
        
        request.setAttribute("mateExpList", mateExpList);
        request.setAttribute("softSkillList", softSkillList);
        
        request.setAttribute("mem", mem);
        request.setAttribute("member", member);
        request.setAttribute("hardSkillList", hardSkillList);
        
		return "/WEB-INF/views/mateProfile/mateProfile.jsp";    
	}

}
