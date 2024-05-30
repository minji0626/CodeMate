package kr.cboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.cboard.vo.CboardVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class CboardDAO {
	private static CboardDAO instance = new CboardDAO();
	
	public static CboardDAO getInstance() {
		return instance;
	}
	private CboardDAO() {}

	// 글 등록하기	
	public void insertCboard(CboardVO cboard) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;

        try {
            conn = DBUtil.getConnection();
            sql = "INSERT INTO c_board(cb_num, mem_num, cb_title, cb_content, cb_file, cb_type, cb_ip) " +
                  "VALUES (c_board_seq.nextval,?, ?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cboard.getMem_num());
            pstmt.setString(2, cboard.getCb_title());
            pstmt.setString(3, cboard.getCb_content());
            pstmt.setString(4, cboard.getCb_file());
            pstmt.setInt(5, cboard.getCb_type());
            pstmt.setString(6, cboard.getCb_ip());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            DBUtil.executeClose(null, pstmt, conn);
        }
    }
	
	// 팀 게시판 총 글의 개수, 검색 개수
	public int getCboardCount(String keyfield, String keyword) throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = null;
	    String sub_sql = "";
	    int count = 0;

	    try {
	        conn = DBUtil.getConnection();

	        if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) {
					sub_sql += "WHERE cb_title LIKE '%' || ? || '%'"; 
				} else if(keyfield.equals("2")) {
					sub_sql += "WHERE mem_nickname LIKE '%' || ? || '%'";
				} else if(keyfield.equals("3")) sub_sql += "WHERE cb_content LIKE '%' || ? || '%'";
			}
			
			sql = "SELECT COUNT(*) FROM c_board JOIN member_detail USING(mem_num) " + sub_sql;

			pstmt = conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(1, keyword);
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// 컬럼 인덱스
				count = rs.getInt(1);
				// count(*)처럼 기호가 있으면 안에 쓰지 않고 알리아스를 명시해주는 것도 번거롭기 때문에 인덱스 사용
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}


	
	// 팀 게시판 목록 가져오기
	public List<CboardVO> getListBoard(int start, int end, String keyfield, String keyword) throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<CboardVO> list = null;
	    String sql = null;
	    String sub_sql = ""; // 이 부분은 필요 없을 것 같습니다.
	    int cnt = 0;

	    try {
	        conn = DBUtil.getConnection();
	        

	        if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) {
					sub_sql += "WHERE cb_title LIKE '%' || ? || '%'"; 
				} else if(keyfield.equals("2")) {
					sub_sql += "WHERE mem_nickname LIKE '%' || ? || '%'";
				} else if(keyfield.equals("3")) sub_sql += "WHERE cb_content LIKE '%' || ? || '%'";
			}
	        
	        sql =  "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM c_board LEFT OUTER JOIN member_detail USING(mem_num) " + sub_sql +" ORDER BY cb_num DESC) a) WHERE rnum >= ? AND rnum <= ?";
	        
	        pstmt = conn.prepareStatement(sql);
	        
	        // 검색어가 있을 경우에만 바인딩
	        if (keyword != null && !"".equals(keyword)) {
	            pstmt.setString(++cnt, "%" + keyword + "%");
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



	// 글 세부
	public CboardVO detailCboard(int cb_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		CboardVO cboard = null;
		try {
			conn = DBUtil.getConnection();
			sql="SELECT * FROM c_board JOIN member USING(mem_num) LEFT OUTER JOIN member_detail USING(mem_num) WHERE cb_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cb_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cboard = new CboardVO();
				cboard.setMem_id(rs.getString("mem_id"));
				cboard.setMem_nickname(rs.getString("mem_nickname"));
				cboard.setMem_num(rs.getInt("mem_num"));
				cboard.setMem_photo(rs.getString("mem_photo"));
				cboard.setCb_num(rs.getInt("cb_num"));
				cboard.setCb_title(rs.getString("cb_title"));
				cboard.setCb_content(rs.getString("cb_content"));
				cboard.setCb_reg_date(rs.getDate("cb_reg_date"));
				cboard.setCb_modify_date(rs.getDate("cb_modify_date"));
				cboard.setCb_file(rs.getString("cb_file"));
				cboard.setCb_hit(rs.getInt("cb_hit"));
				cboard.setCb_like(rs.getInt("cb_like"));
				cboard.setCb_type(rs.getInt("cb_type"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return cboard;
	}
	
	
	
	
}
