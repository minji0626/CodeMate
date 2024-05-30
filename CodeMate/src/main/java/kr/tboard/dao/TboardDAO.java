package kr.tboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardReplyVO;
import kr.tboard.vo.TboardCommentVO;
import kr.tboard.vo.TboardVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class TboardDAO {
	private static TboardDAO instance = new TboardDAO();

	public static TboardDAO getInstance() {
		return instance;
	}

	private TboardDAO() {
	}

	// 글 등록하기	
	public void insertTboard(TboardVO tboard) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;

        try {
            conn = DBUtil.getConnection();
            sql = "INSERT INTO team_board(tb_num,team_num, mem_num, tb_title, tb_content, tb_file, tb_auth) " +
                  "VALUES (team_board_seq.nextval,?, ?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tboard.getTeam_num());
            pstmt.setInt(2, tboard.getMem_num());
            pstmt.setString(3, tboard.getTb_title());
            pstmt.setString(4, tboard.getTb_content());
            pstmt.setString(5, tboard.getTb_file());
            pstmt.setInt(6, tboard.getTb_auth());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            DBUtil.executeClose(null, pstmt, conn);
        }
    }
	
	// 팀 게시판 총 글의 개수, 검색 개수
	public int getTboardCount(String keyfield, String keyword, int team_num) throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = null;
	    String sub_sql = "";
	    int count = 0;
	    
	    try {
	        conn = DBUtil.getConnection();
	        
	        if (keyword != null && !"".equals(keyword)) {
	            // 검색 처리
	            if (keyfield.equals("1")) {
	                sub_sql += " AND tb_title LIKE '%' || ? || '%'";
	            } else if (keyfield.equals("2")) {
	                sub_sql += " AND mem_nickname LIKE '%' || ? || '%'";
	            } else if (keyfield.equals("3")) {
	                sub_sql += " AND tb_content LIKE '%' || ? || '%'";
	            }
	        }
	        
	        sql = "SELECT COUNT(*) FROM team_board JOIN member USING(mem_num) JOIN member_detail USING(mem_num) WHERE team_num = ?" + sub_sql;
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, team_num);

	        if (keyword != null && !"".equals(keyword)) {
	            pstmt.setString(2, keyword);
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

	
	// 팀 게시판 목록 가져오기
	public List<TboardVO> getListBoard(int start, int end, String keyfield, String keyword, int team_num) throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<TboardVO> list = null;
	    String sql = null;
	    String sub_sql = "";
	    int cnt = 0;

	    try {
	        conn = DBUtil.getConnection();
	        
	        if (keyword != null && !"".equals(keyword)) {
	            // 검색 처리
	            if (keyfield.equals("1")) {
	                sub_sql += " AND tb_title LIKE '%' || ? || '%'";
	            } else if (keyfield.equals("2")) {
	                sub_sql += " AND mem_nickname LIKE '%' || ? || '%'";
	            } else if (keyfield.equals("3")) {
	                sub_sql += " AND tb_content LIKE '%' || ? || '%'";
	            }
	        }

	        sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM team_board "
	              + "JOIN member USING(mem_num) JOIN member_detail USING(mem_num) "
	              + "WHERE team_num = ? " + sub_sql + " ORDER BY tb_num DESC) a) "
	              + "WHERE rnum >= ? AND rnum <= ?";
	        
	        pstmt = conn.prepareStatement(sql);
	        
	        pstmt.setInt(++cnt, team_num);  // team_num을 첫 번째 매개변수로 설정
	        if (keyword != null && !"".equals(keyword)) {
	            pstmt.setString(++cnt, keyword);
	        }
	        pstmt.setInt(++cnt, start);
	        pstmt.setInt(++cnt, end);
	        
	        rs = pstmt.executeQuery();
	        
	        list = new ArrayList<TboardVO>();
	        while (rs.next()) {
	            TboardVO tboard = new TboardVO();
	            tboard.setTb_num(rs.getInt("team_num"));
	            tboard.setMem_id(rs.getString("mem_id"));
	            tboard.setTb_title(StringUtil.useNoHTML(rs.getString("tb_title")));
	            tboard.setTb_reg_date(rs.getDate("tb_reg_date"));
	            tboard.setTb_auth(rs.getInt("tb_auth"));
	            tboard.setTb_num(rs.getInt("tb_num"));
	            tboard.setMem_nickname(rs.getString("mem_nickname"));
	            list.add(tboard);
	        }
	    } catch (Exception e) {
	        throw new Exception(e);
	    } finally {
	        DBUtil.executeClose(rs, pstmt, conn);
	    }

	    return list;
	}


	// 글 세부
	public TboardVO detailTboard(int tb_num,int team_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		TboardVO tboard = null;
		try {
			conn = DBUtil.getConnection();
			sql="SELECT * FROM team_board JOIN member USING(mem_num) LEFT OUTER JOIN member_detail USING(mem_num) WHERE tb_num=? and team_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tb_num);
			pstmt.setInt(2, team_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tboard = new TboardVO();
				tboard.setMem_id(rs.getString("mem_id"));
				tboard.setMem_nickname(rs.getString("mem_nickname"));
				tboard.setMem_num(rs.getInt("mem_num"));
				tboard.setMem_photo(rs.getString("mem_photo"));
				tboard.setTeam_num(rs.getInt("team_num"));
				tboard.setTb_num(rs.getInt("tb_num"));
				tboard.setTb_title(rs.getString("tb_title"));
				tboard.setTb_content(rs.getString("tb_content"));
				tboard.setTb_reg_date(rs.getDate("tb_reg_date"));
				tboard.setTb_modify_date(rs.getDate("tb_modify_date"));
				tboard.setTb_file(rs.getString("tb_file"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return tboard;
	}
	
	// 파일 삭제
	public void deleteFile(int tb_num,int team_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE team_board SET tb_file='' WHERE tb_num=? AND team_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tb_num);
			pstmt.setInt(2, team_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	
	// 글 수정하기
	public void updateTboard(TboardVO tboard) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			if(tboard.getTb_file()!=null && !"".equals(tboard.getTb_file())) {
				sub_sql += ", tb_file=?";
			}
			sql = "UPDATE team_board SET tb_title=?,tb_content=?,tb_modify_date=SYSDATE, tb_auth=?"+sub_sql+" WHERE tb_num=? AND team_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, tboard.getTb_title());
			pstmt.setString(++cnt, tboard.getTb_content());
			pstmt.setInt(++cnt, tboard.getTb_auth());
			if(tboard.getTb_file()!=null && !"".equals(tboard.getTb_file())) {
				pstmt.setString(++cnt, tboard.getTb_file());
			}
			pstmt.setInt(++cnt, tboard.getTb_num());
			pstmt.setInt(++cnt, tboard.getTeam_num());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 글 삭제하기
	public void deleteTboard(int tb_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql="";
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			
		}
	}
	
	// 댓글 등록
	public void insertCommentTboard(TboardCommentVO tboardComment) throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = null;
	    try {
	        conn = DBUtil.getConnection();
	        sql = "INSERT INTO team_comment(tc_num, mem_num, tb_num, tc_content) "
	                + "VALUES(team_comment_seq.nextval,?,?,?)";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, tboardComment.getMem_num());
	        pstmt.setInt(2, tboardComment.getTb_num());
	        pstmt.setString(3, tboardComment.getTc_content());
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        throw new Exception(e);
	    } finally {
	        DBUtil.executeClose(null, pstmt, conn);
	    }
	}


	
	// 댓글 개수
	public int getCommentTboardCount (int tb_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn=DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM team_comment WHERE tb_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tb_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	
	// 댓글 목록
	public List<TboardCommentVO> getListCommentTboard(int start, int end, int tb_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null; 
		List<TboardCommentVO> list = null;
		try {
			conn=DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM team_comment JOIN member_detail USING(mem_num) "
					+ "WHERE tb_num=? ORDER BY tc_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tb_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs=pstmt.executeQuery();
			list = new ArrayList<TboardCommentVO>();
			while(rs.next()) {
				TboardCommentVO comment= new TboardCommentVO();
				comment.setTc_num(rs.getInt("tc_num"));
				// 날짜 -> 1분전, 1시간전 , 1일전 형식의 문자열로 변환
				comment.setTc_reg_date(DurationFromNow.getTimeDiffLabel(rs.getString("tc_reg_date")));
				comment.setTc_content(StringUtil.useBrNoHTML(rs.getString("tc_content")));
				if(rs.getString("tc_modify_date") != null) {
					comment.setTc_modify_date(DurationFromNow.getTimeDiffLabel(rs.getString("tc_modify_date")));
				} 
				comment.setTb_num(rs.getInt("tb_num"));
				comment.setMem_nickname(rs.getString("mem_nickname"));		// 작성자 닉네임
				comment.setMem_num(rs.getInt("mem_num"));		// 작성자 회원 번호
				
				list.add(comment);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;	
	}
	
	
	// 댓글 상세
	
	// 댓글 수정
	
	// 댓글 삭제
	
	
}	
