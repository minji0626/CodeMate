package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class GetId implements Action{

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      // 전송된 데이터 인코딩 타입 지정
      request.setCharacterEncoding("utf-8");
      //전송된 데이터 반환
      String phone = request.getParameter("phone");
      String email = request.getParameter("email");
      
      MemberDAO dao = MemberDAO.getInstance();
      MemberVO member = dao.findId(phone, email);
      
      Map<String,String> mapAjax = new HashMap<String,String>();
      if(member == null) {
         mapAjax.put("result", "idNotFound");
      }else {
         mapAjax.put("result", "idFound");
      }
      
      ObjectMapper mapper = new ObjectMapper();
      String ajaxData = mapper.writeValueAsString(mapAjax);
      //JSON 문자열 반환 -> 클라이언트에게 보내려면 jsp가 필요함
      request.setAttribute("ajaxData", ajaxData);
      
      return "/WEB-INF/views/common/ajax_view.jsp";
   }

}
