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

        MemberDAO dao = MemberDAO.getInstance();
        MemberVO member = dao.detailMP(mem_num);
        
        DBDAO dbdao = DBDAO.getInstance();
        List<FieldVO> fieldList = dbdao.getFieldList();
        List<HardSkillVO> hskillList = dbdao.getHardSkillList();
        
        MemberVO mem = dao.getMember(mem_num);
        
        MateDAO mateDAO = MateDAO.getInstance();
        List<MateVO> checkHardSkillList = mateDAO.getListMatHardSkill(mem_num);
        
        // 이전에 선택한 하드 스킬 코드 값을 추출하여 리스트에 저장
        List<Integer> checkedHardSkills = new ArrayList<>();
        for(MateVO mate : checkHardSkillList) {
            checkedHardSkills.add(mate.getHs_code());
        }
        
        // JSP로 전달할 속성 설정
        request.setAttribute("checkHardSkillList", checkHardSkillList);
        request.setAttribute("mem", mem);
        request.setAttribute("member", member);
        request.setAttribute("fieldList", fieldList);
        request.setAttribute("hskillList", hskillList);
        request.setAttribute("checkedHardSkills", checkedHardSkills); // 체크된 하드 스킬 코드 리스트를 JSP로 전달
        
        return "/WEB-INF/views/mateProfile/mateProfileForm.jsp";
    }

}
