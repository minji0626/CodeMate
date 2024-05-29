package kr.team.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.rboard.vo.RboardVO;
import kr.team.vo.TeamVO;
import kr.tmember.vo.TmemberVO;
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
    

}
