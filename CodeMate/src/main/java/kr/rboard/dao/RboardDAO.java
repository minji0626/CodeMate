package kr.rboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt4, null);
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// rboard 수정
	// rboard 삭제
	// rboard 목록 개수
	// rboard 목록 구하기
	// rboard detail 구하기

}

//작성중