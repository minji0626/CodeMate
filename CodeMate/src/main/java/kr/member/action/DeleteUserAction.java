package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cboard.dao.CboardDAO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.rboard.dao.RboardDAO;
import kr.tboard.dao.TboardDAO;
import kr.tmember.dao.TmemberDAO;
import kr.tmember.vo.TmemberVO;
import kr.util.FileUtil;

public class DeleteUserAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Integer mem_num = (Integer) session.getAttribute("mem_num");

        if (mem_num == null) {
            return "redirect:/member/loginForm.do";
        }

        // 전송된 데이터 인코딩 타입 지정
        request.setCharacterEncoding("utf-8");

        MemberDAO dao = MemberDAO.getInstance();
        MemberVO db_vo = dao.getMember(mem_num);
        String input_id = request.getParameter("id");
        String input_passwd = request.getParameter("passwd");

        if (!input_id.equals(db_vo.getMem_id()) || !input_passwd.equals(db_vo.getMem_passwd())) {
            
            request.setAttribute("notice_msg", "입력한 정보가 올바르지 않습니다.");
            request.setAttribute("notice_url", request.getContextPath() + "/member/deleteUserForm.do");
            return "/WEB-INF/views/common/alert_view.jsp";
        } else {
            
            RboardDAO rdao = RboardDAO.getInstance();
            CboardDAO cdao = CboardDAO.getInstance();
            TmemberDAO tdao = TmemberDAO.getInstance();
            
            boolean check = tdao.UserTeamActive(mem_num);
            
            if (check) {

                // 프로필 사진 삭제
                FileUtil.removeFile(request, db_vo.getMem_photo());
                // Rboard 삭제 -> 비활성화일 때만 삭제
                rdao.deleteUserRboard(mem_num);

                // Cboard 삭제
                cdao.deleteUserCboard(mem_num);
                // 회원정보 삭제
                dao.deleteMember(mem_num);

                // 로그아웃
                session.invalidate();

                return "/WEB-INF/views/member/deleteUser.jsp";
            } else {
            	request.setAttribute("notice_msg", "진행 중인 프로젝트가 있으면 회원 탈퇴가 불가합니다.");
            	request.setAttribute("notice_url", request.getContextPath() + "/member/modifyUserForm.do");
                return "/WEB-INF/views/common/alert_view.jsp";
            }
        }
    }
}
