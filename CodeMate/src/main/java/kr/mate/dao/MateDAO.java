package kr.mate.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.mate.vo.MateVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class MateDAO {
	private static MateDAO instance = new MateDAO();

	public static MateDAO getInstance() {
		return instance;
	}
	private MateDAO() {}

	// 메이트 하드 스킬 목록 보기
	public List<MateVO> getListMatHardSkill(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MateVO> list = null; 
		String sql = null;

		try {
			conn = DBUtil.getConnection();

			sql =  "SELECT * FROM mate_hard_skill JOIN hard_skill USING(hs_code) WHERE mem_num=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, mem_num);

			rs = pstmt.executeQuery();
			list = new ArrayList<MateVO>();
			while(rs.next()) {
				MateVO mate = new MateVO();
				mate.setHs_code(mem_num);
				mate.setHs_code(rs.getInt("hs_code"));
				mate.setMh_num(rs.getInt("mh_num"));
				mate.setHs_name(rs.getString("hs_name"));
				mate.setHs_category(rs.getInt("hs_category"));
				mate.setHs_photo(rs.getString("hs_photo"));
				list.add(mate);
			}

		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			// 자원 정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}

	// 초기화
	public void deleteMateHardSkill(MateVO mate) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			sql = "SELECT COUNT(*) FROM mate_hard_skill WHERE mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mate.getMem_num());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}

			if(count!=0) {
				sql = "DELETE FROM mate_hard_skill WHERE mem_num=?";

				pstmt2 = conn.prepareStatement(sql);

				//?에 데이터 바인딩
				pstmt2.setInt(1, mate.getMem_num());

				pstmt2.executeUpdate();
			}		

			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}



	// 메이트 하드 스킬 추가
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
