package kr.consult.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.consult.vo.ConsultVO;
import kr.util.DBUtil;

public class ConsultDAO {
	private static ConsultDAO instance = new ConsultDAO();

	public static ConsultDAO getInstance() {
		return instance;
	}
	private ConsultDAO() {}
	
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
}
