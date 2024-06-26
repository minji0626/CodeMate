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


	// 메이트 하드 스킬 목록 보기
	public List<MateVO> getListMatSoftSkill(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MateVO> list = null; 
		String sql = null;

		try {
			conn = DBUtil.getConnection();

			sql =  "SELECT * FROM mate_soft_skill JOIN soft_skill USING(ss_code) WHERE mem_num=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, mem_num);

			rs = pstmt.executeQuery();
			list = new ArrayList<MateVO>();
			while(rs.next()) {
				MateVO mate = new MateVO();
				mate.setSs_code(mem_num);
				mate.setSs_code(rs.getInt("ss_code"));
				mate.setMs_num(rs.getInt("ms_num"));
				mate.setSs_name(rs.getString("ss_name"));
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
	public void deleteMateSoftSkill(MateVO mate) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			sql = "SELECT COUNT(*) FROM mate_soft_skill WHERE mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mate.getMem_num());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}

			if(count!=0) {
				sql = "DELETE FROM mate_soft_skill WHERE mem_num=?";

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
	public void insertMateSoftSkill(MateVO mate) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();


			sql = "INSERT INTO mate_soft_skill (ms_num, mem_num, ss_code) "
					+ "VALUES (mate_soft_skill_seq.nextval, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			//?에 데이터 바인딩
			pstmt.setInt(1, mate.getMem_num());
			pstmt.setInt(2, mate.getSs_code());

			pstmt.executeUpdate();

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 메이트 프로젝트 경험 추가
	public void insertMateExp(MateVO mate) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();


			sql = "INSERT INTO mate_exp (me_num, mem_num, me_title, me_content, me_category) "
					+ "VALUES (mate_exp_seq.nextval, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			//?에 데이터 바인딩
			pstmt.setInt(1, mate.getMem_num());
			pstmt.setString(2, mate.getMe_title());
			pstmt.setString(3, mate.getMe_content());
			pstmt.setInt(4, mate.getMe_category());

			pstmt.executeUpdate();

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 메이트 프로젝트 불러오기
	public List<MateVO> getMateExp(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MateVO> list = null; 
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();

			sql = "SELECT * FROM mate_exp WHERE mem_num=? ORDER BY me_num  DESC";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, mem_num);

			rs = pstmt.executeQuery();
			list = new ArrayList<MateVO>();
			while(rs.next()) {
				MateVO mate = new MateVO();
				mate.setMem_num(mem_num);
				mate.setMe_num(rs.getInt("me_num"));
				mate.setMe_title(rs.getString("me_title"));
				mate.setMe_content(rs.getString("me_content"));
				mate.setMe_category(rs.getInt("me_category"));
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

	// 메이트 프로젝트 경험 삭제하기
	public void  deleteMateExp(int me_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();


			sql = "DELETE FROM mate_exp WHERE me_num =?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, me_num);

			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	public List<MateVO> getMateReview(int mem_num) throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    PreparedStatement pstmt2 = null;
	    ResultSet rs = null;
	    ResultSet rs2 = null;
	    List<MateVO> list = null;
	    String sql = null;
	    int mr_num = 0;
	    try {
	        conn = DBUtil.getConnection();

	        sql = "SELECT MAX(mr_num) FROM mate_review WHERE mr_receiver=? GROUP BY mr_writer";
	        pstmt = conn.prepareStatement(sql);

	        pstmt.setInt(1, mem_num);

	        rs = pstmt.executeQuery();
	        list = new ArrayList<MateVO>();
	        while(rs.next()) {
	        	mr_num = rs.getInt(1);
	        	sql = "SELECT * FROM mate_review WHERE mr_num=?";
	        	pstmt2 = conn.prepareStatement(sql);
	        	pstmt2.setInt(1, mr_num);
	        	rs2 = pstmt2.executeQuery();
	        	while(rs2.next()) {
	        		MateVO mate = new MateVO();
		            mate.setMem_num(mem_num);
		            mate.setMr_reg_date(rs2.getDate("mr_reg_date"));
		            mate.setMr_content(rs2.getString("mr_content"));
		            list.add(mate);
	        	}
	            
	        }

	    } catch(Exception e) {
	        throw new Exception(e);
	    } finally {
	        // 자원 정리
	    	if(mr_num!=0) {
	    		DBUtil.executeClose(rs2, pstmt2, null);
	    	}
	        DBUtil.executeClose(rs, pstmt, conn);
	    }
	    return list;
	}

}
