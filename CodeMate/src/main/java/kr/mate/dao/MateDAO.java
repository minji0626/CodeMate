package kr.mate.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import kr.mate.vo.MateVO;
import kr.util.DBUtil;

public class MateDAO {
	private static MateDAO instance = new MateDAO();

	public static MateDAO getInstance() {
		return instance;
	}
	private MateDAO() {}
	
	public void insertMateHardSkill(MateVO mate) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			
			sql = "INSERT INTO mate_hard_skill (mh_num, mem_num, hs_code) "
					+ "VALUES (mate_hard_skill_seq.nextval, ?, ?)";
					pstmt = conn.prepareStatement(sql);
					
					//?에 데이터 바인딩
					pstmt.setInt(1, mate.getMem_num());
					pstmt.setInt(2, mate.getHs_code());
					
					pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
    
}
