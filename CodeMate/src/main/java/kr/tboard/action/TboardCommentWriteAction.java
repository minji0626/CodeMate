package kr.tboard.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.tboard.dao.TboardDAO;
import kr.tboard.vo.TboardCommentVO;

public class TboardCommentWriteAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> mapAjax = new HashMap<String, String>();

        HttpSession session = request.getSession();
        Integer mem_num = (Integer) session.getAttribute("mem_num");

        if (mem_num == null) {
            // 로그인 X
            mapAjax.put("result", "logout");
        } else {
            // 로그인 O
            request.setCharacterEncoding("utf-8");
            TboardCommentVO comment = new TboardCommentVO();
            comment.setMem_num(Integer.parseInt(request.getParameter("mem_num")));
            comment.setTb_num(Integer.parseInt(request.getParameter("tb_num")));
            comment.setTc_content(request.getParameter("tc_content"));

            TboardDAO dao = TboardDAO.getInstance();
            dao.insertCommentTboard(comment);
            mapAjax.put("result", "success");
        }

        // JSON 데이터로 변환
        ObjectMapper mapper = new ObjectMapper();
        String ajaxData = mapper.writeValueAsString(mapAjax);
        request.setAttribute("ajaxData", ajaxData);
        
        return "/WEB-INF/views/common/ajax_view.jsp";
    }
}
