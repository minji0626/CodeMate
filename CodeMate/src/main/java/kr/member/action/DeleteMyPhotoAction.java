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

public class DeleteMyPhotoAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인(mem_num있을때) 되어있어야 가능
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		if(mem_num == null) {// 로그인 미실시
			mapAjax.put("result", "logout");
		} else {
			// 로그인 된 경우
            request.setCharacterEncoding("UTF-8");
            // 파일 업로드 처리
            String mem_photo = FileUtil.createFile(request, "mem_photo");

            MemberDAO dao = MemberDAO.getInstance();
            //프로필 사진 수정
            dao.updateMyPhoto(mem_photo, mem_num);
            session.setAttribute("mem_photo", mem_photo);
            
            
            
		}
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
