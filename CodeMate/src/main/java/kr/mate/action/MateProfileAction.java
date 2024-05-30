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

        HttpSession session = request.getSession();
        int user_num = Integer.parseInt(request.getParameter("mem_num"));
        
        MemberDAO dao = MemberDAO.getInstance();
        MemberVO member = dao.detailMP(user_num);
        
        MemberVO mem = dao.getMember(user_num);
        
        MateDAO mateDAO = MateDAO.getInstance();
        List<MateVO> hardSkillList = null;
        hardSkillList = mateDAO.getListMatHardSkill(user_num);
        
        List<MateVO> softSkillList = null;
        softSkillList = mateDAO.getListMatSoftSkill(user_num);
        
        List<MateVO> mateExpList = null;
        mateExpList = mateDAO.getMateExp(user_num );
        
        request.setAttribute("mateExpList", mateExpList);
        request.setAttribute("softSkillList", softSkillList);
        
        request.setAttribute("mem", mem);
        request.setAttribute("member", member);
        request.setAttribute("hardSkillList", hardSkillList);
        
		return "/WEB-INF/views/mateProfile/mateProfile.jsp";    
	}

}
