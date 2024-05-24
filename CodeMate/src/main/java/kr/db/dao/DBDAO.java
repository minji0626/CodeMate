package kr.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.db.vo.FieldVO;
import kr.db.vo.HardSkillVO;
import kr.util.DBUtil;

public class DBDAO {
	private static DBDAO instance = new DBDAO();

	public static DBDAO getInstance() {
		return instance;
	}

	private DBDAO() {};

	public List<FieldVO> getFieldList() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FieldVO> list = null;
		String sql = "";
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM field_db";
			
			pstmt  = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<FieldVO>();
			while (rs.next()) {
				FieldVO field = new FieldVO();
				field.setF_code(rs.getInt("f_code"));
				field.setF_name(rs.getString("f_name"));
				list.add(field);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	};
	
	public List<HardSkillVO> getHardSkillList() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HardSkillVO> list = null;
		String sql = "";
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM hard_skill";
			pstmt  = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<HardSkillVO>();
			while (rs.next()) {
				HardSkillVO hskill= new HardSkillVO();
				hskill.setHs_code(rs.getInt("hs_code"));
				hskill.setHs_name(rs.getString("hs_name"));
				hskill.setHs_category(rs.getInt("hs_category"));
				hskill.setHs_photo(rs.getString("hs_photo"));
				list.add(hskill);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	};
}
