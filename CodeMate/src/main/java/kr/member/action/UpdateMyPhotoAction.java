package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.util.FileUtil;

public class UpdateMyPhotoAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//로그인(mem_num있을때) 되어있어야 가능
		Map<String, String> mapAjax = new HashMap<String, String>();

        HttpSession session = request.getSession();
        Integer mem_num = (Integer) session.getAttribute("mem_num");

        if (mem_num == null) { // 로그인이 되지 않은 경우
            mapAjax.put("result", "logout");
        } else {
            // 로그인 된 경우
            request.setCharacterEncoding("UTF-8");
                // 파일 업로드 처리
                String mem_photo = FileUtil.createFile(request, "mem_photo");

                MemberDAO dao = MemberDAO.getInstance();
                // 프로필 사진 수정
                dao.updateMyPhoto(mem_photo, mem_num);

                // 새롭게 업로드하면 이전 파일 삭제
                String user_photo = (String)session.getAttribute("user_photo");
                FileUtil.removeFile(request, user_photo);
                // 현재 파일로 세션 정보 갱신
                session.setAttribute("user_photo", mem_photo);
                mapAjax.put("result", "success");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
