package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.rboard.dao.RboardDAO;
import kr.rboard.vo.RbookmarkVO;

public class DeleteMyPageBookMarkAction implements Action {
    Map<String, String> mapAjax = new HashMap<String, String>();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        // 로그인 체크
        Integer mem_num = (Integer) session.getAttribute("mem_num");
        if (mem_num == null) {
            // 로그인이 되지 않은 경우
            mapAjax.put("result", "logout");
        } else {
            request.setCharacterEncoding("utf-8");

            int rb_num = Integer.parseInt(request.getParameter("rb_num"));

            RbookmarkVO rbookmark = new RbookmarkVO(rb_num, mem_num);

            RboardDAO rdao = RboardDAO.getInstance();
            rbookmark = rdao.getRbookmark(rbookmark);

            if (rbookmark != null) {
                // 삭제할 북마크가 존재하는 경우에만 삭제 진행
                rdao.deleteRbookmark(rbookmark);
                mapAjax.put("result", "success");
            } else {
                // 삭제할 북마크가 존재하지 않는 경우
                mapAjax.put("result", "not_found");
            }
        }

        // JSON 데이터로 변환
        ObjectMapper mapper = new ObjectMapper();
        String ajaxData = mapper.writeValueAsString(mapAjax);

        request.setAttribute("ajaxData", ajaxData);

        return "/WEB-INF/views/common/ajax_view.jsp";
    }
}
