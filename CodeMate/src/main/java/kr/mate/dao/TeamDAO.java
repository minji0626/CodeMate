package kr.mate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import kr.mate.vo.TeamVO;
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
        ResultSet rs = null;
        ResultSet rs2 = null;
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
                    list.add(team);
                }
                DBUtil.executeClose(rs2, pstmt2, null);
            }

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw new Exception(e);
        } finally {
            // 자원 정리
            DBUtil.executeClose(rs, pstmt, conn);
        }
        return list;
    }

}
