package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.util.FileUtil;

public class DeletePhotoAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> mapAjax = new HashMap<String, String>();
        HttpSession session = request.getSession();
        Integer mem_num = (Integer) session.getAttribute("mem_num");
        if (mem_num == null) { // 로그인이 되지 않은 경우
            mapAjax.put("result", "logout");
        } else {
        	MemberDAO dao = MemberDAO.getInstance();
            String mem_photo = (String) session.getAttribute("mem_photo");
            MemberVO member = dao.getMember(mem_num);
            if (mem_photo != null) {
            	session.removeAttribute("mem_photo");
                FileUtil.removeFile(request, mem_photo);
                // 파일이 삭제된 후에는 세션에서도 해당 정보를 제거해야 합니다.
                // 프로필 사진 수정
    	        dao.updateMyPhoto(null, mem_num);                
                
                // 현재 파일로 세션 정보 갱신
                session.setAttribute("mem_photo", null); 
            }
            mapAjax.put("result", "success");
        }
        
        ObjectMapper mapper = new ObjectMapper();
        String ajaxData = mapper.writeValueAsString(mapAjax);
        request.setAttribute("ajaxData", ajaxData);

        return "/WEB-INF/views/common/ajax_view.jsp";
    }
}
