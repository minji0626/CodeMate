package kr.consult.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.consult.vo.ConsultVO;
import kr.db.vo.FieldVO;
import kr.util.DBUtil;

public class ConsultDAO {
	private static ConsultDAO instance = new ConsultDAO();

	public static ConsultDAO getInstance() {
		return instance;
	}

	private ConsultDAO() {
	}

	// 문의글 작성하기
	public void sendConsult(ConsultVO consult) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO consult(cs_num, mem_num, cs_title, cs_content, cs_category) VALUES(consult_seq.nextval,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, consult.getMem_num());
			pstmt.setString(2, consult.getCs_title());
			pstmt.setString(3, consult.getCs_content());
			pstmt.setInt(4, consult.getCs_category());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 문의글 개수 구하기(관리자)
	public int getConsultsCount(String keyfield, String keyword) throws Exception {
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
				} else if (keyfield.equals("2")) {
					sub_sql += "WHERE TO_CHAR(mem_num) = ?";
				} else if (keyfield.equals("3")) {
					sub_sql += "WHERE TO_CHAR(cs_confirmed) = ?";
				}
			}

			sql = "SELECT COUNT(*) FROM consult LEFT OUTER JOIN member USING(mem_num)" + sub_sql;
			pstmt = conn.prepareStatement(sql);

			if (keyword != null && !keyword.equals("")) {
				pstmt.setString(1, keyword);

			}

			// sql문 실행
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

	// 문의글 목록 불러오기(관리자)
	public List<ConsultVO> getConsultsList(int start, int end, String keyfield, String keyword) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ConsultVO> list = null;
		String sql = null;
		String sub_sql = "";

		try {
			conn = DBUtil.getConnection();

			if (keyword != null && !keyword.equals("")) {
				if (keyword != null && !keyword.equals("")) {
					if (keyfield.equals("1")) {
						sub_sql += "WHERE mem_id LIKE '%' || ? || '%'";
					} else if (keyfield.equals("2")) {
						sub_sql += "WHERE TO_CHAR(mem_num) = ?";
					} else if (keyfield.equals("3")) {
						sub_sql += "WHERE TO_CHAR(cs_confirmed) = ?";
					}
				}
			}

			sql = "SELECT * FROM consult LEFT OUTER JOIN member USING(mem_num)" + sub_sql + "ORDER BY cs_reg_date DESC";
			pstmt = conn.prepareStatement(sql);
			
			if (keyword != null && !keyword.equals("")) {
				pstmt.setString(1, keyword);

			}

			rs = pstmt.executeQuery();

			list = new ArrayList<ConsultVO>();
			while (rs.next()) {
				ConsultVO consult = new ConsultVO();
				consult.setCs_num(rs.getInt("cs_num"));
				consult.setMem_num(rs.getInt("mem_num"));
				consult.setMem_id(rs.getString("mem_id"));
				consult.setCs_title(rs.getString("cs_title"));
				consult.setCs_confirmed(rs.getInt("cs_confirmed"));
				consult.setCs_category(rs.getInt("cs_category"));
				consult.setCs_reg_date(rs.getDate("cs_reg_date"));
				list.add(consult);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return list;
	}

	// 문의 처리하기
	public void confirmConsults(String[] consults) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			// 처리 여부에 따라 쿼리를 선택하고 바인딩할 값 설정
			sql = "UPDATE consult SET cs_confirmed = 1, cs_confirmed_date = CASE WHEN cs_confirmed_date IS NULL THEN SYSDATE ELSE cs_confirmed_date END WHERE cs_num = ?";

			// 모든 문의 번호에 대해 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			for (int i=0; i<consults.length; i++) {
				int csNum = Integer.parseInt(consults[i]);
				pstmt.setInt(1, csNum);
				pstmt.addBatch();
				
				if(i % 1000 == 0) {
					pstmt.executeBatch();
				}
			}
			pstmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 문의 처리취소하기
	public void unconfirmConsults(String[] consults) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			// 처리 여부에 따라 쿼리를 선택하고 바인딩할 값 설정
			sql = "UPDATE consult SET cs_confirmed = 0, cs_confirmed_date = NULL WHERE cs_num = ?";

			// 모든 문의 번호에 대해 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			for (int i=0; i<consults.length; i++) {
				int csNum = Integer.parseInt(consults[i]);
				pstmt.setInt(1, csNum);
				pstmt.addBatch();
				
				if(i % 1000 == 0) {
					pstmt.executeBatch();
				}
			}
			pstmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 문의글 목록 불러오기(마이페이지)
}
