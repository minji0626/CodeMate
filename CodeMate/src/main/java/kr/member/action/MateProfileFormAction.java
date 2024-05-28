package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.db.dao.DBDAO;
import kr.db.vo.FieldVO;
import kr.db.vo.HardSkillVO;
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

        MemberDAO dao = MemberDAO.getInstance();
        MemberVO member = dao.detailMP(mem_num);
        
        DBDAO dbdao = DBDAO.getInstance();
		List<FieldVO> fieldList = dbdao.getFieldList();
		List<HardSkillVO> hskillList = dbdao.getHardSkillList();
		
		request.setAttribute("member", member);
		
		request.setAttribute("fieldList", fieldList);
		request.setAttribute("hskillList", hskillList);
        
		return "/WEB-INF/views/mateProfile/mateProfileForm.jsp";
	}

}
