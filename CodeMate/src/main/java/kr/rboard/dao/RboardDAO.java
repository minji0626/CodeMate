package kr.rboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.rboard.vo.RboardVO;
import kr.util.DBUtil;

public class RboardDAO {
	private static RboardDAO instance = new RboardDAO();

	public static RboardDAO getInstance() {
		return instance;
	}

	private RboardDAO() {
	};

	// rboard 등록
	public void insertRboard(RboardVO rboard) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		ResultSet rs = null;
		String sql = null;
		int rb_num = 0;

		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			//rboard seq 생성
			sql = "SELECT r_board_seq.nextval FROM DUAL";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				rb_num = rs.getInt(1);
			}

			// r_board에 추가
			sql = "INSERT INTO r_board (rb_num,mem_num,rb_category,rb_meet,rb_teamsize,rb_period,rb_start,"
					+ "rb_title,rb_content,rb_endrecruit,rb_pj_title) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, rb_num);
			pstmt2.setInt(2, rboard.getMem_num());
			pstmt2.setInt(3, rboard.getRb_category());
			pstmt2.setInt(4, rboard.getRb_meet());
			pstmt2.setInt(5, rboard.getRb_teamsize());
			pstmt2.setInt(6, rboard.getRb_period());
			pstmt2.setString(7, rboard.getRb_start());
			pstmt2.setString(8, rboard.getRb_title());
			pstmt2.setString(9, rboard.getRb_content());
			pstmt2.setString(10, rboard.getRb_endRecruit());
			pstmt2.setString(11, rboard.getRb_pj_title());

			pstmt2.executeUpdate();

			// r_skill에 추가
			sql = "INSERT INTO r_skill (rs_num,rb_num,hs_code) VALUES(r_skill_seq.nextval,?,?)";

			for (String skill : rboard.getR_skills()) {
				int intSkill = Integer.parseInt(skill);
				pstmt3 = conn.prepareStatement(sql);
				pstmt3.setInt(1, rb_num);
				pstmt3.setInt(2, intSkill);
				pstmt3.executeUpdate();
			}

			// r_field에 추가
			sql = "INSERT INTO r_field (rf_num,rb_num,f_code) VALUES(r_field_seq.nextval,?,?)";

			for (String field : rboard.getR_fields()) {
				int intField = Integer.parseInt(field);
				pstmt4 = conn.prepareStatement(sql);
				pstmt4.setInt(1, rb_num);
				pstmt4.setInt(2, intField);
				pstmt4.executeUpdate();
			}
			
			//team 테이블에 추가
			sql = "INSERT INTO team (team_num) VALUES(?)";
			
			pstmt5 = conn.prepareStatement(sql);
			pstmt5.setInt(1, rb_num);
			pstmt5.executeUpdate();
			
			//전체 커밋
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt5, null);
			DBUtil.executeClose(null, pstmt4, null);
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// rboard 수정
	// rboard 삭제
	// rboard 글 개수, 검색 개수
	public int getRboardCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT COUNT(*) FROM r_board";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	
	
	// rboard 목록 구하기
	public List<RboardVO> getRboardList(int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RboardVO> list = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM r_board JOIN"
					+ " (SELECT rb_num, LISTAGG(hs_name,',') within group ( order by hs_name) hs_name,"
					+ " LISTAGG(hs_photo,',') within group ( order by hs_name) hs_photo"
					+ " FROM r_skill JOIN hard_skill USING(hs_code) group by rb_num) USING(rb_num) ORDER BY rb_num DESC)a)"
					+ " WHERE rnum >= ? AND rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<RboardVO>();
			while (rs.next()) {
				RboardVO rboard = new RboardVO();
				rboard.setRb_num(rs.getInt("rb_num"));
				rboard.setReg_date(rs.getDate("rb_reg_date"));
				rboard.setRb_category(rs.getInt("rb_category"));
				rboard.setRb_meet(rs.getInt("rb_meet"));
				rboard.setRb_teamsize(rs.getInt("rb_teamsize"));
				rboard.setRb_period(rs.getInt("rb_period"));
				rboard.setRb_start(rs.getString("rb_start"));
				rboard.setRb_title(rs.getString("rb_title"));
				rboard.setRb_endRecruit(rs.getString("rb_endRecruit"));
				rboard.setHs_name_string(rs.getString("hs_name"));
				rboard.setHs_photo_string(rs.getString("hs_photo"));
				list.add(rboard);
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	
	// rboard detail 구하기
	//프로젝트 모집글 상세정보 읽어오기-민재가 했음 이상하면 지워주쇼
    public RboardVO getrboard(int mem_num)throws Exception{
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      RboardVO rboard = null;
      String sql = null;
      try {
         conn = DBUtil.getConnection();
         sql = "SELECT * FROM member JOIN r_board USING(mem_num) WHERE mem_num=? ";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, mem_num);
         rs = pstmt.executeQuery();
         if(rs.next()) {
            rboard = new RboardVO();
            rboard.setMem_num(rs.getInt("mem_num"));
            rboard.setRb_category(rs.getInt("rb_category"));
            rboard.setRb_meet(rs.getInt("rb_meet"));
            rboard.setRb_teamsize(rs.getInt("rb_teamsize"));
            rboard.setRb_period(rs.getInt("rb_period"));
            rboard.setRb_start(rs.getString("rb_start"));
            rboard.setRb_title(rs.getString("rb_title"));
            rboard.setRb_content(rs.getString("rb_content"));
            rboard.setRb_endRecruit(rs.getString("rb_endrecruit"));
            rboard.setRb_pj_title(rs.getString("rb_pj_title"));
         }
      }catch(Exception e) {
         throw new Exception(e);
      }finally {
         DBUtil.executeClose(rs, pstmt, conn);
      }
   return rboard;
   }

}

//작성중