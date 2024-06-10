package kr.member.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.member.vo.MemberVO;
import kr.rboard.dao.RboardDAO;
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
		PreparedStatement pstmt4 = null;
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
			sql = "INSERT INTO member (mem_num,mem_id,mem_auth) VALUES (?,?,2)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1,num);//시퀀스 번호
			pstmt2.setString(2,member.getMem_id());//아이디
			pstmt2.executeUpdate();

			sql = "INSERT INTO member_detail (mem_num,mem_name,mem_passwd, mem_phone, "
					+ "mem_email,mem_nickname, mem_reg_date, mem_level) VALUES ("
					+ "?,?,?,?,?,?, SYSDATE, 1)"; 
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1,num);
			pstmt3.setString(2,member.getMem_name());
			pstmt3.setString(3,member.getMem_passwd());
			pstmt3.setString(4,member.getMem_phone());
			pstmt3.setString(5,member.getMem_email());
			pstmt3.setString(6,member.getMem_nickname());
			pstmt3.executeUpdate();

			sql = "INSERT INTO mate_profile (mem_num, mp_state) VALUES(?, 0)";
			pstmt4 = conn.prepareStatement(sql);
			pstmt4.setInt(1, num);
			pstmt4.executeUpdate();
			
			//SQL 실행 시 모두 성공하면 commit
			conn.commit();

		}catch(Exception e) {
			//SQL문이 하나라도 실패하면 rollback
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt4, null);
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}


	//아이디 중복 체크
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
				member.setMem_email(rs.getString("mem_email"));//회원 탈퇴 시 필요 
				member.setMem_nickname(rs.getString("mem_nickname"));
				member.setMem_level(rs.getInt("mem_level"));
			}
		}catch(Exception e){ 
			throw new Exception (e); 
		}finally{ 
			DBUtil.executeClose(rs, pstmt, conn); 
		}
		return member; 
	}
	
	//닉네임 중복 체크
		public MemberVO checkNickname(String mem_nickname,Integer mem_num)throws Exception{
			Connection conn = null; 
			PreparedStatement pstmt = null; 
			ResultSet rs = null;
			MemberVO member = null; 
			String sql = null; 
			String sub_sql = "";
			try{ 
				//커넥션풀로부터 커넥션 할당 
				conn =DBUtil.getConnection(); 
				
				if(mem_num != null) {
					sub_sql = "AND mem_num != ?";					
				}
				
				//SQL문 작성
				sql = "SELECT * FROM member LEFT OUTER JOIN " +
						"member_detail USING(mem_num) WHERE mem_nickname = ?" +sub_sql; 
				//preparedStatment 객체 생성 
				pstmt =conn.prepareStatement(sql); 
				//?에 데이터 바인딩 
				pstmt.setString(1, mem_nickname); 
				if(mem_num != null) {
					pstmt.setInt(2, mem_num);				
				}
				//SQL문 실행 
				rs = pstmt.executeQuery(); 
				if(rs.next()) { 
					member = new MemberVO();
					member.setMem_num(rs.getInt("mem_num"));
					member.setMem_id(rs.getString("mem_id")); 
					member.setMem_auth(rs.getInt("mem_auth"));
					member.setMem_passwd(rs.getString("mem_passwd"));
					member.setMem_photo(rs.getString("mem_photo"));
					member.setMem_email(rs.getString("mem_email"));//회원 탈퇴 시 필요 
					member.setMem_nickname(rs.getString("mem_nickname"));
					member.setMem_level(rs.getInt("mem_level"));
				}
			}catch(Exception e){ 
				throw new Exception (e); 
			}finally{ 
				DBUtil.executeClose(rs, pstmt, conn); 
			}
			return member; 
		}
	
	// 이메일 중복 체크
		public MemberVO checkEmail(String mem_email, Integer mem_num)throws Exception{
			Connection conn = null; 
			PreparedStatement pstmt = null; 
			ResultSet rs = null;
			MemberVO member = null; 
			String sql = null; 
			String sub_sql = "";
			
			try{ 
				//커넥션풀로부터 커넥션 할당 
				conn =DBUtil.getConnection(); 
				
				if(mem_num != null) {
					sub_sql = "AND mem_num != ?";
				}
				//SQL문 작성
				sql = "SELECT * FROM member LEFT OUTER JOIN " +
						"member_detail USING(mem_num) WHERE mem_email=?" + sub_sql; 
				//preparedStatment 객체 생성 
				pstmt =conn.prepareStatement(sql); 
				//?에 데이터 바인딩 
				pstmt.setString(1, mem_email); 
				if(mem_num != null) {
					pstmt.setInt(2, mem_num);				
				}
				//SQL문 실행 
				rs = pstmt.executeQuery(); 
				if(rs.next()) { 
					member = new MemberVO();
					member.setMem_num(rs.getInt("mem_num"));
					member.setMem_id(rs.getString("mem_id")); 
					member.setMem_auth(rs.getInt("mem_auth"));
					member.setMem_passwd(rs.getString("mem_passwd"));
					member.setMem_photo(rs.getString("mem_photo"));
					member.setMem_email(rs.getString("mem_email"));//회원 탈퇴 시 필요 
					member.setMem_nickname(rs.getString("mem_nickname"));
					member.setMem_level(rs.getInt("mem_level"));
				}
			}catch(Exception e){ 
				throw new Exception (e); 
			}finally{ 
				DBUtil.executeClose(rs, pstmt, conn); 
			}
			return member; 

		}
		
		// 핸드폰 중복 체크
		public MemberVO checkPhone(String mem_phone, Integer mem_num)throws Exception{
			Connection conn = null; 
			PreparedStatement pstmt = null; 
			ResultSet rs = null;
			MemberVO member = null; 
			String sql = null; 
			String sub_sql = "";
			
			try{ 
				//커넥션풀로부터 커넥션 할당 
				conn =DBUtil.getConnection(); 
				
				if(mem_num != null) {
					sub_sql = "AND mem_num != ?";
				}
				//SQL문 작성
				sql = "SELECT * FROM member LEFT OUTER JOIN " +
						"member_detail USING(mem_num) WHERE mem_phone=?" + sub_sql; 
				//preparedStatment 객체 생성 
				pstmt =conn.prepareStatement(sql); 
				//?에 데이터 바인딩 
				pstmt.setString(1, mem_phone); 
				if(mem_num != null) {
					pstmt.setInt(2, mem_num);
				}
				//SQL문 실행 
				rs = pstmt.executeQuery(); 
				if(rs.next()) { 
					member = new MemberVO();
					member.setMem_num(rs.getInt("mem_num"));
					member.setMem_id(rs.getString("mem_id")); 
					member.setMem_auth(rs.getInt("mem_auth"));
					member.setMem_passwd(rs.getString("mem_passwd"));
					member.setMem_photo(rs.getString("mem_photo"));
					member.setMem_email(rs.getString("mem_email"));//회원 탈퇴 시 필요 
					member.setMem_nickname(rs.getString("mem_nickname"));
					member.setMem_level(rs.getInt("mem_level"));
				}
			}catch(Exception e){ 
				throw new Exception (e); 
			}finally{ 
				DBUtil.executeClose(rs, pstmt, conn); 
			}
			return member; 

		}
		
		//아이디 찾기 - 아이디 여부 확인
		public MemberVO findId(String phone,String email)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MemberVO member = null;
			String sql = null;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM member LEFT OUTER JOIN member_detail USING(mem_num) WHERE mem_email=? AND mem_phone=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setString(1, email);
				pstmt.setString(2, phone);
				//SQL문 실행
				rs = pstmt.executeQuery(); 
				if(rs.next()) { 
					member = new MemberVO();
					member.setMem_num(rs.getInt("mem_num"));
					member.setMem_id(rs.getString("mem_id")); 
					member.setMem_auth(rs.getInt("mem_auth"));
					member.setMem_passwd(rs.getString("mem_passwd"));
					member.setMem_photo(rs.getString("mem_photo"));
					member.setMem_email(rs.getString("mem_email"));
					member.setMem_nickname(rs.getString("mem_nickname"));
					member.setMem_level(rs.getInt("mem_level"));
					member.setMem_phone(rs.getString("mem_phone"));
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}		
			return member;
		}
		//비밀번호 찾기 - 해당 계정 확인
		public MemberVO SelectPw(String id,String phone,String email)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MemberVO member = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM member_detail JOIN member USING (mem_num) "
						+ "WHERE mem_id = ? AND mem_phone = ? AND mem_email = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);	
				pstmt.setString(2, phone);
				pstmt.setString(3, email);
				
				//SQL문 실행
				rs = pstmt.executeQuery(); 
				if(rs.next()) { 
					member = new MemberVO();
					member.setMem_num(rs.getInt("mem_num"));
					member.setMem_id(rs.getString("mem_id")); 
					member.setMem_auth(rs.getInt("mem_auth"));
					member.setMem_passwd(rs.getString("mem_passwd"));
					member.setMem_photo(rs.getString("mem_photo"));
					member.setMem_email(rs.getString("mem_email"));
					member.setMem_nickname(rs.getString("mem_nickname"));
					member.setMem_level(rs.getInt("mem_level"));
					member.setMem_phone(rs.getString("mem_phone"));
				}
			}catch(Exception e) {
				throw new Exception(e);	
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}		
			return member;
			}
		
		//비밀번호 찾기 - 재설정
		public void ReUpdatePw(MemberVO member)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				conn = DBUtil.getConnection();
				sql = "UPDATE member_detail SET mem_passwd=? WHERE mem_num=?";
						pstmt = conn.prepareStatement(sql);
						//?에 데이터 바인딩
						pstmt.setString(1, member.getMem_passwd());
						pstmt.setInt(2, member.getMem_num());
						pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		
	//회원상세 정보
	public MemberVO getMember(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM member JOIN member_detail USING(mem_num) WHERE mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setMem_num(rs.getInt("mem_num"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_nickname(rs.getString("mem_nickname"));
				member.setMem_phone(rs.getString("mem_phone"));
				member.setMem_photo(rs.getString("mem_photo"));
				member.setMem_reg_date(rs.getDate("mem_reg_date"));
				member.setMem_modify_date(rs.getDate("mem_modify_date"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}
	//회원정보 수정
	public void updateMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			//mem_modify_date=SYSDATE
			sql = "UPDATE member_detail SET mem_name=?,mem_email=?, mem_nickname=?, mem_phone=? WHERE mem_num=?";
					pstmt = conn.prepareStatement(sql);
					//?에 데이터 바인딩
					pstmt.setString(1, member.getMem_name());
					pstmt.setString(2, member.getMem_email());
					pstmt.setString(3, member.getMem_nickname());
					pstmt.setString(4, member.getMem_phone());
					pstmt.setInt(5, member.getMem_num());
					pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 메이트 프로필 작성하기 (수정하기)
		public void insertMP(MemberVO member) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				sql = "UPDATE mate_profile SET mp_introduce=?, mp_modify_date=SYSDATE, mp_state=?, mp_position=? WHERE mem_num=?";

				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, member.getMp_introduce());
				pstmt.setInt(2, member.getMp_state());
				pstmt.setString(3, member.getMp_position());
				pstmt.setInt(4, member.getMem_num());
				
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		public MemberVO detailMP(int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MemberVO member = null;
			String sql = null;
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM mate_profile JOIN member_detail USING(mem_num) WHERE mem_num=?";
				pstmt = conn.prepareStatement(sql); 
				pstmt.setInt(1, mem_num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					member = new MemberVO();
					member.setMem_num(rs.getInt("mem_num"));
					member.setMp_position(rs.getString("mp_position"));
					member.setMp_introduce(rs.getString("mp_introduce"));
					member.setMp_modify_date(rs.getDate("mp_modify_date"));
					member.setMp_state(rs.getInt("mp_state"));
					member.setMem_nickname(rs.getString("mem_nickname"));
					member.setMem_level(rs.getInt("mem_level"));
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return member;
		}
		//프로필 사진 수정(ajax)
		public void updateMyPhoto(String mem_photo,int mem_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				conn = DBUtil.getConnection();
				sql = "UPDATE member_detail SET mem_photo=? WHERE mem_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mem_photo);
				pstmt.setInt(2, mem_num);
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		
		
		//회원 탈퇴
		public void deleteMember(int mem_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			String sql = null;
			try {
				conn =DBUtil.getConnection();
				conn.setAutoCommit(false);
				//member의 auth값 변경
				sql = "UPDATE member SET mem_auth=0 WHERE mem_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mem_num);
				pstmt.executeUpdate();
				//member_detail 레코드 삭제
				sql = "DELETE FROM member_detail WHERE mem_num=?";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, mem_num);
				pstmt2.executeUpdate();
				conn.commit();
			}catch(Exception e) {
				conn.rollback();
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt2, null);
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		// 관리자용
		// 총 회원수, 검색 회원수(관리자용)
		public int getMembersCount(String keyfield, String keyword) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			String sub_sql = "";
			int count = 0;

			try {
				conn = DBUtil.getConnection();

				if (keyword != null && !keyword.equals("")) {
					if (keyfield.equals("1")) {
						sub_sql += "WHERE mem_id LIKE '%' || ? || '%'";
					} else if (keyfield.equals("2")) {
						sub_sql += "WHERE mem_nickname LIKE '%' || ? || '%'";
					} else if (keyfield.equals("3")) {
						sub_sql += "WHERE mem_num = ?";
					}
				}

				sql = "SELECT COUNT(*) FROM member LEFT OUTER JOIN member_detail USING(mem_num) " + sub_sql;
				pstmt = conn.prepareStatement(sql);

				if (keyword != null && !keyword.equals("")) {
					pstmt.setString(1, keyword);
				}

				// sql문 실행
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}

			} catch (Exception e) {
				throw new Exception();
			} finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}

			return count;
		}

		// 회원 목록 가져오기(관리자용) -회원 id, 닉네임, 회원번호로 검색 가능
		public List<MemberVO> getMembersList(int start, int end, String keyfield, String keyword) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<MemberVO> list = null;
			String sql = null;
			String sub_sql = "";
			int cnt = 0;
			String keyfield_string = null;

			try {
				conn = DBUtil.getConnection();

				if (keyword != null && !keyword.equals("")) {
					if (keyfield.equals("1")) {
						sub_sql += "WHERE mem_id LIKE '%' || ? || '%'";
						keyfield_string = "mem_id";
					} else if (keyfield.equals("2")) {
						sub_sql += "WHERE mem_nickname LIKE '%' || ? || '%'";
						keyfield_string = "mem_nickname";
					} else if (keyfield.equals("3")) {
						sub_sql += "WHERE mem_num = ?";
						keyfield_string = "mem_num";
					}
				} else {
					keyfield_string = "mem_num";
				}

				sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM member LEFT OUTER JOIN member_detail USING(mem_num) "
						+ sub_sql + " ORDER BY " + keyfield_string + " ASC)a) WHERE rnum >= ? AND rnum <= ?";

				pstmt = conn.prepareStatement(sql);

				if (keyword != null && !keyword.equals("")) {
					pstmt.setString(++cnt, keyword);
				}

				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);

				rs = pstmt.executeQuery();
				list = new ArrayList<MemberVO>();

				while (rs.next()) {
					MemberVO member = new MemberVO();
					member.setMem_num(rs.getInt("mem_num"));
					member.setMem_id(rs.getString("mem_id"));
					member.setMem_nickname(rs.getString("mem_nickname"));
					member.setMem_auth(rs.getInt("mem_auth"));
					member.setMem_email(rs.getString("mem_email"));
					member.setMem_reg_date(rs.getDate("mem_reg_date"));

					list.add(member);
				}
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}

			return list;
		}

		// 회원정지 (관리자용)
		public void lockMemberToggle(int mem_num, int locked) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;

			try {
				conn = DBUtil.getConnection();
				if (locked == 0) { // 회원 정지하기
					sql = "UPDATE member SET mem_auth=1, mem_report = NVL(mem_report, 0)+1 WHERE mem_num=?";
				} else if (locked == 1) { // 회원 정지풀기
					sql = "UPDATE member SET mem_auth=2 WHERE mem_num=?";
				}

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mem_num);

				pstmt.executeUpdate();
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}

		public void changeAuthToAdminToggle(int mem_num, int mem_auth) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;

			try {
				conn = DBUtil.getConnection();
				if (mem_auth == 9) { // 관리자->일반회원
					sql = "UPDATE member SET mem_auth=2 WHERE mem_num=?";
				} else if (mem_auth == 2) { // 일반회원->관리자
					sql = "UPDATE member SET mem_auth=9 WHERE mem_num=?";
				}

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mem_num);

				pstmt.executeUpdate();
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		
		
}




