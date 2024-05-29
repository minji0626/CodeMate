package kr.team.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.team.vo.TeamVO;
import kr.util.DBUtil;

public class TeamDAO {
	private static TeamDAO instance = new TeamDAO();

	public static TeamDAO getInstance() {
		return instance;
	}

	private TeamDAO() {
	}
	
	// 팀 생성 (시퀀스로 번호 늘어나고, 팀 상태는 1이 활성화)
    public void createTeam(int teamNum) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        
        try {
            conn = DBUtil.getConnection();
            sql = "INSERT INTO TEAM(team_num, team_status) VALUES(team_seq.nextval, 1)";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
        	DBUtil.executeClose(null, pstmt, conn);
        }
    }
    
    // 팀 불러오기
    public List<TeamVO> getTeamListByMemNum(int mem_num,int team_num)throws Exception{
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	List<TeamVO> list = null;
    	String sql = null;
    	
    	try {
    		conn = DBUtil.getConnection();
    		sql = "SELECT * FROM team_board WHERE mem_num=? AND team_num=?";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, mem_num);
    		pstmt.setInt(2, team_num);
    		rs = pstmt.executeQuery();
    		
    		list = new ArrayList<TeamVO>();
    		while (rs.next()) {
    			TeamVO team = new TeamVO();
    			team.setTeam_num(rs.getInt("team_num"));
    			team.setTeam_status(rs.getInt("team_status"));
    			list.add(team);
    		}
    	}catch(Exception e) {
    		throw new Exception(e);
    	}finally {
    		DBUtil.executeClose(rs, pstmt, conn);
    	}
    	
    	return list;
    }
}
