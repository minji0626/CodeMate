package kr.tmember.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.tmember.vo.TmemberVO;
import kr.util.DBUtil;

public class TmemberDAO {
	private static TmemberDAO instance = new TmemberDAO();

	public static TmemberDAO getInstance() {
		return instance;
	}

	private TmemberDAO() { }
	
	// 팀 멤버 불러오기
	public TmemberVO getMember(int mem_num) throws Exception {
		TmemberVO tmember = null;
		
		return tmember;
	}
	
	// 팀 목록 가져오기
	
	
	public TmemberVO getTmember(int mem_num)throws Exception{
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	TmemberVO tmember = null;
    	String sql = null;
    	try {
    		conn = DBUtil.getConnection();
    		sql = "SELECT team_num FROM team_member WHERE mem_num=?";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, mem_num);
    		rs = pstmt.executeQuery();
    		if(rs.next()) {
    			tmember = new TmemberVO();
    			tmember.setTeam_num(rs.getInt("team_num"));
    		}
    	}catch(Exception e) {
    		throw new Exception(e);
    	}finally {
    		DBUtil.executeClose(rs, pstmt, conn);
    	}
    	return tmember;
    }
}
