package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class MemberDAO {
	//싱글턴 패턴
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	private MemberDAO() {}
	
	//회원가입
		public void insertMember(MemberVO member)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			PreparedStatement pstmt3 = null;
			//pstmt가 3개 -> SQL문이 3개
			ResultSet rs = null;
			String sql = null;
			int num = 0;//시퀀스 번호 저장
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//오토 커밋 해제
				conn.setAutoCommit(false);
				
				//회원 번호(mem_num) 생성
				sql = "SELECT member_seq.nextval FROM dual";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {//컬럼 인덱스
					num = rs.getInt(1);
				}
				//auth값은 자동으로 2라고 들어감
				sql = "INSERT INTO member (mem_num,mem_id,mem_auth) VALUES (?,?,?)";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1,num);//시퀀스 번호
				pstmt2.setString(2,member.getMem_id());//아이디
				pstmt2.setInt(3,2);//auth
				pstmt2.executeUpdate();
				
				sql = "INSERT INTO member_detail (mem_num,mem_name,mem_passwd,"
						+ "mem_phone,mem_email,mem_nickname,mem_level) VALUES ("
						+ "?,?,?,?,?,?,?)"; 
				pstmt3 = conn.prepareStatement(sql);
				pstmt3.setInt(1,num);
				pstmt3.setString(2,member.getMem_name());
				pstmt3.setString(3,member.getMem_passwd());
				pstmt3.setString(4,member.getMem_phone());
				pstmt3.setString(5,member.getMem_email());
				pstmt3.setString(6,member.getMem_nickname());
				pstmt3.setInt(7,member.getMem_level());
				pstmt3.executeUpdate();
				
				//SQL 실행 시 모두 성공하면 commit
				conn.commit();
				
			}catch(Exception e) {
				//SQL문이 하나라도 실패하면 rollback
				conn.rollback();
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt3, null);
				DBUtil.executeClose(null, pstmt2, null);
				DBUtil.executeClose(rs, pstmt, conn);
			}
		}
	
		
		  //ID 중복 체크 및 로그인 처리 
		public MemberVO checkMember(String id)throws Exception{
		  Connection conn = null; 
		  PreparedStatement pstmt = null; 
		  ResultSet rs = null;
		  MemberVO member = null; 
		  String sql = null; 
		  
		  try{ 
			  //커넥션풀로부터 커넥션 할당 
		  conn =DBUtil.getConnection(); 
		  //SQL문 작성
		  sql = "SELECT * FROM member LEFT OUTER JOIN " +
		  "member_detail USING(mem_num) WHERE mem_id = ?"; 
		  //preparedStatment 객체 생성 
		  pstmt =conn.prepareStatement(sql); 
		  //?에 데이터 바인딩 
		  pstmt.setString(1, id); 
		  //SQL문 실행 
		  rs = pstmt.executeQuery(); 
		  if(rs.next()) { 
		  member = new MemberVO();
		  member.setMem_num(rs.getInt("mem_num"));
		  member.setMem_id(rs.getString("mem_id")); 
		  member.setMem_auth(rs.getInt("mem_auth"));
		  member.setMem_passwd(rs.getString("mem_passwd"));
		  member.setMem_photo(rs.getString("mem_photo"));
		  member.setMem_email(rs.getString("mem_email"));//회원 탈퇴 시 필요 } }catch(Exception e)
		  }
		  }catch(Exception e){ 
			  throw new Exception (e); 
		 }finally{ 
			  DBUtil.executeClose(rs, pstmt, conn); }
		  	
		 
		  return member; 
		
		  }
	
	
	
}