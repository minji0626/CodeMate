package kr.tboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.cboard.vo.CcommentVO;
import kr.rboard.vo.RcommentVO;
import kr.tboard.vo.TboardCommentVO;
import kr.tboard.vo.TboardVO;
import kr.tmember.vo.TmemberVO;
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
			sql = "INSERT INTO team_board(tb_num,team_num, mem_num, tb_title, tb_content, tb_file, tb_auth) "
					+ "VALUES (team_board_seq.nextval,?, ?, ?, ?, ?, ?)";

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

			sql = "SELECT COUNT(*) FROM team_board JOIN member USING(mem_num) JOIN member_detail USING(mem_num) WHERE team_num = ?"
					+ sub_sql;
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
	public List<TboardVO> getListBoard(int start, int end, String keyfield, String keyword, int team_num)
			throws Exception {
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
					+ "JOIN member USING(mem_num) JOIN member_detail USING(mem_num) " + "WHERE team_num = ? " + sub_sql
					+ " ORDER BY tb_num DESC) a) " + "WHERE rnum >= ? AND rnum <= ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(++cnt, team_num); // team_num을 첫 번째 매개변수로 설정
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
	public TboardVO detailTboard(int tb_num, int team_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		TboardVO tboard = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM team_board JOIN member USING(mem_num) LEFT OUTER JOIN member_detail USING(mem_num) WHERE tb_num=? and team_num=?";
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
	public void deleteFile(int tb_num, int team_num) throws Exception {
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
			if (tboard.getTb_file() != null && !"".equals(tboard.getTb_file())) {
				sub_sql += ", tb_file=?";
			}
			sql = "UPDATE team_board SET tb_title=?,tb_content=?,tb_modify_date=SYSDATE, tb_auth=?" + sub_sql
					+ " WHERE tb_num=? AND team_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, tboard.getTb_title());
			pstmt.setString(++cnt, tboard.getTb_content());
			pstmt.setInt(++cnt, tboard.getTb_auth());
			if (tboard.getTb_file() != null && !"".equals(tboard.getTb_file())) {
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
	public void deleteTboard(int tb_num, int team_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			// 댓글 삭제
			sql = "DELETE FROM team_comment WHERE tb_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, tb_num);
			pstmt2.executeUpdate();

			// 글 삭제
			sql = "DELETE FROM team_board WHERE tb_num=? AND team_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tb_num);
			pstmt.setInt(2, team_num);
			pstmt.executeUpdate();

			// 예외 발생 없이 정상적으로 SQL문 실행
			conn.commit();
		} catch (Exception e) {
			// 예외 발생
			conn.rollback();
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 댓글 등록
	public void insertCommentTboard(TboardCommentVO tcomment) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO team_comment(tc_num, mem_num, tb_num, tc_content) "
					+ "VALUES(team_comment_seq.nextval,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tcomment.getMem_num());
			pstmt.setInt(2, tcomment.getTb_num());
			pstmt.setString(3, StringUtil.useBrNoHTML(tcomment.getTc_content()));
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 개별 댓글vo 구하기
	public TboardCommentVO getTcomment(int tc_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TboardCommentVO tcomment = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM team_comment WHERE tc_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tc_num);

			rs = pstmt.executeQuery();

			tcomment = new TboardCommentVO();
			if (rs.next()) {
				tcomment.setTc_num(rs.getInt("tc_num"));
				tcomment.setTc_content(rs.getString("tc_content"));
				tcomment.setMem_num(rs.getInt("mem_num"));
			}

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return tcomment;
	}

	// 댓글 목록
	public List<TboardCommentVO> getListCommentTboard(int tb_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TboardCommentVO> list = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT t.*, m.mem_nickname, m.mem_photo FROM team_comment t "
					+ "LEFT OUTER JOIN member_detail m ON(t.mem_num = m.mem_num) "
					+ "WHERE tb_num=? ORDER BY tc_num DESC";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tb_num);

			rs = pstmt.executeQuery();

			list = new ArrayList<TboardCommentVO>();
			while (rs.next()) {
				TboardCommentVO tbcomment = new TboardCommentVO();
				tbcomment.setTb_num(rs.getInt("tb_num"));
				tbcomment.setTc_num(rs.getInt("tc_num"));
				tbcomment.setMem_num(rs.getInt("mem_num"));
				tbcomment.setTc_content(rs.getString("tc_content"));
				tbcomment.setMem_photo(rs.getString("mem_photo"));
				tbcomment.setMem_nickname(rs.getString("mem_nickname"));
				tbcomment.setTc_reg_date(DurationFromNow.getTimeDiffLabel(rs.getString("tc_reg_date")));
				if (rs.getString("tc_modify_date") != null) {
					tbcomment.setTc_modify_date(DurationFromNow.getTimeDiffLabel(rs.getString("tc_modify_date")));
				}

				list.add(tbcomment);
			}
		} catch (Exception e) {
			throw new Exception();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return list;
	}

	// 댓글 수정
	public void modifyTcomment(TboardCommentVO tcomment) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE team_comment SET tc_content=?,tc_modify_date=SYSDATE WHERE tc_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, StringUtil.useBrNoHTML(tcomment.getTc_content()));
			pstmt.setInt(2, tcomment.getTc_num());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

//	댓글 삭제
	public void deleteTcomment(int tc_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM team_comment WHERE tc_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tc_num);

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	
	// 회원탈퇴 시 팀에서 삭제하기 - 예영작성
		public void deleteTeamMember(int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt5= null;
			PreparedStatement pstmt4= null;
			PreparedStatement pstmt3= null;
			PreparedStatement pstmt2= null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			String sql = null;
			int tb_num = 0;
			try {
				// 커넥션 풀로부터 커넥션 할당시키기
				conn = DBUtil.getConnection();
				// 오토 커밋 해제
				conn.setAutoCommit(false);

				//mem_id와 같은 mem_num을 가진 tb_num을 내놓음
				sql = "SELECT tb_num FROM team_board WHERE mem_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mem_num);
				rs = pstmt.executeQuery();

				while(rs.next()) {//tb_num과 동일한 tb_num을 가진 team_comment를 전부 삭제 -> mem_id의 team_comment를 전부 삭제?
					tb_num = rs.getInt("tb_num");
					sql = "DELETE FROM team_comment "
							+ "WHERE tb_num IN (SELECT tb_num FROM team_board WHERE tb_num = ?)";
					pstmt2 = conn.prepareStatement(sql);
					pstmt2.setInt(1, tb_num);
					pstmt2.executeUpdate();
				}


				// 해당 멤버가 작성한 댓글 삭제
				sql="DELETE FROM team_comment WHERE mem_num=?"; 
				pstmt3 = conn.prepareStatement(sql); 
				pstmt3.setInt(1, mem_num);
				pstmt3.executeUpdate();

				// 해당 멤버가 작성한 글 삭제
				sql="DELETE FROM team_board WHERE mem_num=?";
				pstmt4 = conn.prepareStatement(sql);
				pstmt4.setInt(1, mem_num);
				pstmt4.executeUpdate();

				// team_member에서 해당 멤버 삭제하기
				sql="DELETE FROM team_member WHERE mem_num=?";
				pstmt5 = conn.prepareStatement(sql);
				pstmt5.setInt(1, mem_num);
				pstmt5.executeUpdate();

				// 모든 sql문이 성공한다면 커밋을 시킨다
				conn.commit();

			} catch (Exception e) {
				conn.rollback();
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(null, pstmt5, null);
				DBUtil.executeClose(null, pstmt4, null);
				DBUtil.executeClose(null, pstmt3, null);
				if(tb_num!=0) {
					DBUtil.executeClose(null, pstmt2, null);
				}
				DBUtil.executeClose(rs, pstmt, conn);
			}

		}

		// 회원탈퇴 전 회원의 등급 구하기 - 예영작성 (등급을 반환해 DeleteUserAction에서 팀장등급일 경우 알림창을 띄울거임!)
		public TmemberVO getTmemberAuth (int mem_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			TmemberVO auth = null;
			try {
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM team_member WHERE mem_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mem_num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					auth = new TmemberVO();
					auth.setTm_auth(rs.getInt("tm_auth"));
				}
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return auth;
		}

}
