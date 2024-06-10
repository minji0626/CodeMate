package kr.rboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.util.DBUtil;

public class rBoardDAO2 {
	private static rBoardDAO2 instance = new rBoardDAO2();

	public static rBoardDAO2 getInstance() {
		return instance;
	}

	private rBoardDAO2() {
	};
	
	// 회원 탈퇴 시 rboard 삭제 - 예영작성
			public void deleteUserRboard(String mem_id) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null; // 북마크
				PreparedStatement pstmt2 = null; // 댓글
				PreparedStatement pstmt3 = null; // 모집스킬
				PreparedStatement pstmt4 = null; // 모집필드
				PreparedStatement pstmt5 = null; // 팀 (마감안된글은 팀도 지우고, 마감된 글은 팀 안지우기)
				PreparedStatement pstmt6 = null; // apply레코드는 지우지 않고 rb_num만 null되게
				PreparedStatement pstmt7 = null; // 모집글

				String sql = null;

				try {
					conn = DBUtil.getConnection();
					conn.setAutoCommit(false);

					// 북마크 삭제
					sql = "DELETE FROM r_bookmark JOIN member USING (mem_num) WHERE mem_id=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, mem_id);
					pstmt.executeUpdate();

					// 댓글 삭제
					sql = "DELETE FROM r_comment JOIN member USING (mem_num) WHERE mem_id=?";
					pstmt2 = conn.prepareStatement(sql);
					pstmt2.setString(1, mem_id);
					pstmt2.executeUpdate();

					// 모집스킬 삭제
					sql = "DELETE FROM r_skill JOIN member USING (mem_num) WHERE mem_id=?";
					pstmt3 = conn.prepareStatement(sql);
					pstmt3.setString(1, mem_id);
					pstmt3.executeUpdate();

					// 모집필드 삭제
					sql = "DELETE FROM r_field JOIN member USING (mem_num) WHERE mem_id=?";
					pstmt4 = conn.prepareStatement(sql);
					pstmt4.setString(1, mem_id);
					pstmt4.executeUpdate();

					// 모집이 마감되지 않은 글(0)은 team테이블에서도 삭제 
					sql = "DELETE FROM team JOIN member USING (mem_num) WHERE mem_id=?";
					pstmt5 = conn.prepareStatement(sql);
					pstmt5.setString(1, mem_id);
					pstmt5.executeUpdate();

					// 모집이 마감된 글은 team

					// apply레코드는 지우지 않고 rb_num만 null되게
					sql = "UPDATE r_apply SET rb_num=null JOIN member USING (mem_num) WHERE mem_id=?";
					pstmt6 = conn.prepareStatement(sql);
					pstmt6.setString(1, mem_id);
					pstmt6.executeUpdate();

					// 모집글 삭제
					sql = "DELETE FROM r_board JOIN member USING (mem_num) WHERE mem_id=?";
					pstmt7 = conn.prepareStatement(sql);
					pstmt7.setString(1, mem_id);
					pstmt7.executeUpdate();

					conn.commit();

				} catch (Exception e) {
					conn.rollback();
					throw new Exception(e);
				} finally {
					DBUtil.executeClose(null, pstmt7, null);
					DBUtil.executeClose(null, pstmt6, null);
					DBUtil.executeClose(null, pstmt5, null);
					DBUtil.executeClose(null, pstmt4, null);
					DBUtil.executeClose(null, pstmt3, null);
					DBUtil.executeClose(null, pstmt2, null);
					DBUtil.executeClose(null, pstmt, conn);
				}
				
			}

	
	
	
	
	
	
	
	
	
}
