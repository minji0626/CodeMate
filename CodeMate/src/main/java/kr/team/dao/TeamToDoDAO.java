package kr.team.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.team.vo.TeamToDoVO;
import kr.util.DBUtil;

public class TeamToDoDAO {
	private static TeamToDoDAO instance = new TeamToDoDAO();

	public static TeamToDoDAO getInstance() {
		return instance;
	}

	private TeamToDoDAO() {}
	
	
	// 팀 투두 추가하기
	public void addToDo (TeamToDoVO todo) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO team_todo(tt_num, team_num, tt_content, tt_date, tt_start, tt_end, tt_state) "
					+ "VALUES(team_todo_seq.nextval,?,?,?,?,?,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, todo.getTeam_num());
			pstmt.setString(2, todo.getTt_content());
			pstmt.setString(3, todo.getTt_date());
			pstmt.setString(4, todo.getTt_start());
			pstmt.setString(5, todo.getTt_end());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	// 팀 투두 list 불러오기
    public List<TeamToDoVO> getTeamToDo(int team_num) throws Exception {
        List<TeamToDoVO> teamtodo = null;
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
        try {
        	conn = DBUtil.getConnection();
            sql = "SELECT * FROM team_todo WHERE team_num=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, team_num);
            rs = pstmt.executeQuery();
            teamtodo = new ArrayList<TeamToDoVO>();
            while (rs.next()) {
                TeamToDoVO teamToDo = new TeamToDoVO();
                teamToDo.setTt_num(rs.getInt("tt_num"));
                teamToDo.setTeam_num(rs.getInt("team_num"));
                teamToDo.setTt_content(rs.getString("tt_content"));
                teamToDo.setTt_date(rs.getString("tt_date"));
                teamToDo.setTt_start(rs.getString("tt_start"));
                teamToDo.setTt_end(rs.getString("tt_end"));
                teamToDo.setTt_state(rs.getInt("tt_state"));
                
                teamtodo.add(teamToDo);
            }
        } catch (Exception e) {
        	throw new Exception(e);
        } finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
        return teamtodo;
    }
	
	
	// 팀 투두 삭제하기
	
	
	
	
}
