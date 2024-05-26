package kr.tboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import kr.tboard.vo.TboardVO;
import kr.util.DBUtil;

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
			sql = "INSERT INTO team_board(tb_num, team_num, mem_num, tb_title, tb_content, tb_file, tb_auth)"
                    + "VALUES(team_board_seq.nextval,?,?,?,?,?,?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tboard.getTb_num());
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
	public int getTboardCount(String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				// 검색 처리
				if(keyfield.equals("1")) {
					sub_sql += "WHERE tb_title LIKE '%' || ? || '%'";
				}
				else if(keyfield.equals("2")) {
					sub_sql += "WHERE mem_id LIKE '%' || ? || '%'";
				}
				else if(keyfield.equals("3")) {
					sub_sql += "WHERE tb_content LIKE '%' || ? || '%'";
				}
			}
			
			sql="SELECT COUNT(*) FROM tboard JOIN member USING(mem_num) " + sub_sql;
			pstmt = conn.prepareStatement(sql);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(1, keyword);
			}
			rs=pstmt.executeQuery();
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
	
	// 팀 게시판 목록 가져오기
	public List<TboardVO> getListBoard(int start, int end, String keyfield,String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<TboardVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			
		}
		
		
		return list;
	}
	
	// 글 세부
	public TboardVO detailTboard(TboardVO tboard) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		TboardVO board = null;
		try {
			conn = DBUtil.getConnection();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			
		}
		
		return board;
	}
	
	// 조회수 증가
	public void updateReadcount(int tb_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {

		}
	}
	
	// 파일 삭제
	public void deleteFile(int board_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {

		}
		
	}
	
	// 글 수정하기
	public void updateTboard(TboardVO board) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			
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
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			
		}
	}
	
	// 댓글 등록
	
	// 댓글 목록
	// 댓글 개수
	
	// 댓글 상세
	
	// 댓글 수정
	
	// 댓글 삭제
	
	
}	
