package kr.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.cboard.vo.CboardVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class AdminDAO {
	private static AdminDAO instance = new AdminDAO();

	public static AdminDAO getInstance() {
		return instance;
	}

	private AdminDAO() {
	}
	
	// 개수 파악하기
	public int getCount(String keyfield, String keyword) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;

		try {
			conn = DBUtil.getConnection();

			if (keyword != null && !"".equals(keyword)) {
				if (keyfield.equals("1")) {
					sub_sql += "WHERE cb_title LIKE '%' || ? || '%'";
				} else if (keyfield.equals("2")) {
					sub_sql += "WHERE mem_nickname LIKE '%' || ? || '%'";
				} else if (keyfield.equals("3"))
					sub_sql += "WHERE cb_type LIKE '%' || ? || '%'";
			}

			sql = "SELECT COUNT(*) FROM c_board JOIN member_detail USING(mem_num)" + sub_sql;

			pstmt = conn.prepareStatement(sql);
			if (keyword != null && !"".equals(keyword)) {
				pstmt.setString(1, keyword);
			}
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

	// 게시판 목록 가져오기
	public List<CboardVO> getListBoard(int start, int end, String keyfield, String keyword) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CboardVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;

		try {
			conn = DBUtil.getConnection();

			if (keyword != null && !"".equals(keyword)) {
				if (keyfield.equals("1")) {
					sub_sql += "WHERE cb_title LIKE '%' || ? || '%'";
				} else if (keyfield.equals("2")) {
					sub_sql += "WHERE mem_nickname LIKE '%' || ? || '%'";
				} else if (keyfield.equals("3"))
					sub_sql += "WHERE cb_type LIKE '%' || ? || '%'";
			}

			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM c_board LEFT OUTER JOIN member_detail USING(mem_num) "
					+ sub_sql + " ORDER BY cb_num DESC) a) WHERE rnum >= ? AND rnum <= ?";

			pstmt = conn.prepareStatement(sql);

			// 검색어가 있을 경우에만 바인딩
			if (keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);

			rs = pstmt.executeQuery();

			list = new ArrayList<CboardVO>();
			while (rs.next()) {
				CboardVO cboard = new CboardVO();
				cboard.setCb_num(rs.getInt("cb_num"));
				cboard.setCb_title(StringUtil.useNoHTML(rs.getString("cb_title")));
				cboard.setMem_nickname(rs.getString("mem_nickname"));
				cboard.setCb_reg_date(rs.getDate("cb_reg_date"));
				cboard.setCb_modify_date(rs.getDate("cb_modify_date"));
				cboard.setCb_type(rs.getInt("cb_type"));
				list.add(cboard);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return list;
	}

}
