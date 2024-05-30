package kr.tmember.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.tmember.vo.TmemberVO;
import kr.util.DBUtil;

public class TmemberDAO {
	private static TmemberDAO instance = new TmemberDAO();

	public static TmemberDAO getInstance() {
		return instance;
	}

	private TmemberDAO() { }
	
	/*
	 * // 팀 멤버 불러오기 public TmemberVO getMember(int mem_num) throws Exception {
	 * TmemberVO tmember = null;
	 * 
	 * return tmember; }
	 * 
	 * // 팀 목록 가져오기
	 * 
	 * 
	 * public TmemberVO getTmember(int mem_num)throws Exception{ Connection conn =
	 * null; PreparedStatement pstmt = null; ResultSet rs = null; TmemberVO tmember
	 * = null; String sql = null; try { conn = DBUtil.getConnection(); sql =
	 * "SELECT team_num FROM team_member WHERE mem_num=?"; pstmt =
	 * conn.prepareStatement(sql); pstmt.setInt(1, mem_num); rs =
	 * pstmt.executeQuery(); if(rs.next()) { tmember = new TmemberVO();
	 * tmember.setTeam_num(rs.getInt("team_num")); } }catch(Exception e) { throw new
	 * Exception(e); }finally { DBUtil.executeClose(rs, pstmt, conn); } return
	 * tmember; }
	 */
	
	// team_setting에서 사용되는 팀멤버 count 하기
	public int getTmemberCount (int team_num) throws Exception{
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = null;
	    int count = 0;
	    try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM team_member JOIN member USING(mem_num) JOIN member_detail USING(mem_num) WHERE team_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, team_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	    return count;
	}
	
	
	// team_setting에 쓸 목록 가져오기
	public List<TmemberVO> getTeamMembers(int team_num) throws Exception{
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql = null;
		 List<TmemberVO> list = null;
		 try {
			 conn = DBUtil.getConnection();
			 sql = "SELECT * FROM team_member JOIN member USING(mem_num) JOIN member_detail USING(mem_num) WHERE team_num=?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1,team_num);
			 rs = pstmt.executeQuery();
			 
			 list = new ArrayList<TmemberVO>();
			 while (rs.next()) {
				TmemberVO tmember = new TmemberVO();
				tmember.setMem_photo(rs.getString("mem_photo"));
				tmember.setMem_num(rs.getInt("mem_num"));
				tmember.setMem_id(rs.getString("mem_id"));
				tmember.setMem_level(rs.getInt("mem_level"));
				tmember.setTeam_num(rs.getInt("team_num"));
				tmember.setMem_nickname(rs.getString("mem_nickname"));
				tmember.setTm_auth(rs.getInt("tm_auth"));
				tmember.setTm_review_status(rs.getInt("tm_review_status"));
				
				list.add(tmember);
			}
		} catch(Exception e) {
    		throw new Exception(e);
    	} finally {
    		DBUtil.executeClose(rs, pstmt, conn);
    	}
		 return list;
	}
	
	//팀 멤버 불러오기
	public void getMembers(int team_num) throws Exception{
		Connection conn = null;
		String sql = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM team_member WHERE team_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, team_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	
	// 팀 멤버 삭제하기
	public void deleteTeamMember(int team_num, int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt3= null;
		PreparedStatement pstmt2= null;
		PreparedStatement pstmt= null;
		String sql = null;
		try {
			// 커넥션 풀로부터 커넥션 할당시키기
			conn = DBUtil.getConnection();
			// 오토 커밋 해제
			conn.setAutoCommit(false);
			
			// 해당 멤버가 작성한 글 삭제
			sql="DELETE FROM team_board WHERE mem_num=? AND team_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, team_num);
			pstmt.executeUpdate();
			
			// 해당 멤버가 작성한 댓글 삭제
			sql="DELETE FROM team_comment WHERE mem_num=? AND team_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, mem_num);
			pstmt2.setInt(2, team_num);
			pstmt2.executeUpdate();
			
			// team_member에서 해당 멤버 삭제하기
			sql="DELETE FROM team_member WHERE mem_num=? AND team_num=?";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, mem_num);
			pstmt3.setInt(2, team_num);
			pstmt3.executeUpdate();
			
			// 모든 sql문이 성공한다면 커밋을 시킨다
			conn.commit();
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	
	
}
