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

        int check = 0;
        if (!input_id.equals(db_vo.getMem_id()) || !input_passwd.equals(db_vo.getMem_passwd())) {
            request.setAttribute("check", check);
            return "/WEB-INF/views/member/deleteUser.jsp";
        } else {
            check = 1;
            request.setAttribute("check", check);
        }

        RboardDAO rdao = RboardDAO.getInstance();
        TboardDAO tdao = TboardDAO.getInstance();
        TmemberVO t_member = tdao.getTmemberAuth(mem_num);
        CboardDAO cdao = CboardDAO.getInstance();

        Integer tm_auth = t_member != null ? t_member.getTm_auth() : null;

        if (check == 1 && (tm_auth == null || tm_auth != 4)) {

            // 프로필 사진 삭제
            FileUtil.removeFile(request, db_vo.getMem_photo());
            // Rboard 삭제
            rdao.deleteUserRboard(mem_num);
            // Tboard 삭제
            tdao.deleteTeamMember(mem_num);
            // Cboard 삭제
            cdao.deleteUserCboard(mem_num);
            // 회원정보 삭제
            dao.deleteMember(mem_num);

            // 로그아웃
            session.invalidate();

            return "/WEB-INF/views/member/deleteUser.jsp";
        } else {
            if (tm_auth != null && tm_auth == 4) {
                return "redirect:/member/myTeam.do";
            }
        }

        return null;
    }
}
