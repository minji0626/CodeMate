package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class MemberDAO2 {
	// 싱글턴 패턴
	private static MemberDAO2 instance = new MemberDAO2();

	public static MemberDAO2 getInstance() {
		return instance;
	}

	private MemberDAO2() {
	}
	
	//총 회원수, 검색 회원수
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
				}
				else if (keyfield.equals("2")) {
					sub_sql += "WHERE mem_nickname LIKE '%' || ? || '%'";
				}
				else if (keyfield.equals("3")) {
					sub_sql += "WHERE mem_num = ?";
				}
			}
			
			sql = "SELECT COUNT(*) FROM member LEFT OUTER JOIN member_detail USING(mem_num) " + sub_sql;
			pstmt = conn.prepareStatement(sql);
			
			if (keyword != null && !keyword.equals("")) {
				pstmt.setString(1, keyword);
			}
			
			//sql문 실행
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
				}
				else if (keyfield.equals("2")) {
					sub_sql += "WHERE mem_nickname LIKE '%' || ? || '%'";
					keyfield_string = "mem_nickname";
				}
				else if (keyfield.equals("3")) {
					sub_sql += "WHERE mem_num = ?";
					keyfield_string = "mem_num";
				}
			}
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM member LEFT OUTER JOIN member_detail USING(mem_num) " +sub_sql+
					" ORDER BY " + keyfield_string + " ASC)a) WHERE rnum >= ? AND rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			
			if (keyword != null && !keyword.equals("")) {
				pstmt.setString(++cnt, keyword);
			}
			
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<MemberVO>();
			
			while(rs.next()) {
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
}
