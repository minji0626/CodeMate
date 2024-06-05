package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import kr.member.vo.TeamVO;
import kr.util.DBUtil;

public class TeamDAO {
    private static TeamDAO instance = new TeamDAO();

    public static TeamDAO getInstance() {
        return instance;
    }

    private TeamDAO() {
    }

 // 팀 목록
    public List<TeamVO> getTeamList(int mem_num, HttpSession session) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        List<TeamVO> list = null; 
        String sql = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            sql = "SELECT team_num, tm_auth FROM team_member WHERE mem_num=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, mem_num);

            rs = pstmt.executeQuery();
            list = new ArrayList<TeamVO>();
            
            while (rs.next()) {
                int team_num = rs.getInt("team_num");
                int tm_auth = rs.getInt("tm_auth");

                // 세션에 tm_auth 값을 저장
                session.setAttribute("tm_auth", tm_auth);

                sql = "SELECT rb_period, rb_start, rb_pj_title FROM r_board WHERE rb_num=?";
                pstmt2 = conn.prepareStatement(sql);
                pstmt2.setInt(1, team_num);

                rs2 = pstmt2.executeQuery();
                while (rs2.next()) {
                    TeamVO team = new TeamVO();
                    team.setRb_period(rs2.getInt("rb_period"));
                    team.setRb_start(rs2.getString("rb_start"));
                    team.setRb_pj_title(rs2.getString("rb_pj_title"));
                    team.setTeam_num(team_num);
                    team.setTm_auth(tm_auth);
                    
                    sql = "SELECT team_status FROM team WHERE team_num=?";
                    pstmt3 = conn.prepareStatement(sql);
                    pstmt3.setInt(1, team_num);
                    rs3 = pstmt3.executeQuery();
                    if(rs3.next()) {
                    	team.setTeam_status(rs3.getInt(1));
                    }
                    
                    list.add(team);
                }
                
            }

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw new Exception(e);
        } finally {
            // 자원 정리
        	DBUtil.executeClose(rs3, pstmt3, null);
        	DBUtil.executeClose(rs2, pstmt2, null);
            DBUtil.executeClose(rs, pstmt, conn);
        }
        return list;
    }
    
    
    public TeamVO getUserTeam(int mem_num,int team_num) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TeamVO userTeam = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT tm_auth FROM team_member WHERE mem_num=? AND team_num=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, mem_num);
            pstmt.setInt(2, team_num);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                userTeam = new TeamVO();
                userTeam.setTm_auth(rs.getInt("tm_auth"));
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            DBUtil.executeClose(rs, pstmt, conn);
        }
        return userTeam;
    }
    
    public TeamVO getTeamMember(int mem_num,int team_num) throws Exception {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	TeamVO teammember = null;
    	try {
    		conn = DBUtil.getConnection();
    		String sql = "SELECT * FROM team_member LEFT OUTER JOIN team USING(team_num) WHERE mem_num=? AND team_num=?";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, mem_num);
    		pstmt.setInt(2, team_num);
    		rs = pstmt.executeQuery();
    		if (rs.next()) {
    			teammember = new TeamVO();
    			teammember.setMem_num(rs.getInt("mem_num"));
    			teammember.setTeam_num(rs.getInt("team_num"));
    			teammember.setTeam_status(rs.getInt("team_status"));
    			teammember.setTm_auth(rs.getInt("tm_auth"));
    			teammember.setTm_review_status(rs.getInt("tm_review_status"));
    		}
    	} catch (Exception e) {
    		throw new Exception(e);
    	} finally {
    		DBUtil.executeClose(rs, pstmt, conn);
    	}
    	return teammember;
    }
    
    public TeamVO getTeam(int team_num) throws Exception{
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	TeamVO teams = null;
    	try {
    		conn = DBUtil.getConnection();
    		String sql = "SELECT * FROM team WHERE team_num = ?";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, team_num);
    		rs = pstmt.executeQuery();
    		if (rs.next()) {
    			teams = new TeamVO();
    			teams.setTeam_num(rs.getInt("team_num"));
    			teams.setTeam_status(rs.getInt("team_status"));
    		}
    	} catch (Exception e) {
    		throw new Exception(e);
    	} finally {
    		DBUtil.executeClose(rs, pstmt, conn);
    	}
    	return teams;
    }
    
    public void stopTeam(int team_num) throws Exception{
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	String sql = null;
    	try {
    		conn = DBUtil.getConnection();
    		sql = "UPDATE team SET team_status=0 WHERE team_num=?";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, team_num);
    		pstmt.executeUpdate();
    		
    	} catch (Exception e) {
    		throw new Exception(e);
    	} finally {
    		DBUtil.executeClose(null, pstmt, conn);
    	}
    }

    public void startTeam(int team_num) throws Exception{
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	String sql = null;
    	try {
    		conn = DBUtil.getConnection();
    		sql = "UPDATE team SET team_status=1 WHERE team_num=?";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, team_num);
    		pstmt.executeUpdate();
    		
    	} catch (Exception e) {
    		throw new Exception(e);
    	} finally {
    		DBUtil.executeClose(null, pstmt, conn);
    	}
    }

}
