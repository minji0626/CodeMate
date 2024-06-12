package kr.rboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.rboard.vo.RapplyVO;
import kr.rboard.vo.RboardVO;
import kr.rboard.vo.RbookmarkVO;
import kr.rboard.vo.RcommentVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class RboardDAO {
	private static RboardDAO instance = new RboardDAO();

	public static RboardDAO getInstance() {
		return instance;
	}

	private RboardDAO() {
	};

	// rboard 등록
	public void insertRboard(RboardVO rboard) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		ResultSet rs = null;
		String sql = null;
		int rb_num = 0;

		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			// rboard seq 생성
			sql = "SELECT r_board_seq.nextval FROM DUAL";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				rb_num = rs.getInt(1);
			}

			// r_board에 추가
			sql = "INSERT INTO r_board (rb_num,mem_num,rb_category,rb_meet,rb_teamsize,rb_period,rb_start,"
					+ "rb_title,rb_content,rb_endrecruit,rb_pj_title) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, rb_num);
			pstmt2.setInt(2, rboard.getMem_num());
			pstmt2.setInt(3, rboard.getRb_category());
			pstmt2.setInt(4, rboard.getRb_meet());
			pstmt2.setInt(5, rboard.getRb_teamsize());
			pstmt2.setInt(6, rboard.getRb_period());
			pstmt2.setString(7, rboard.getRb_start());
			pstmt2.setString(8, rboard.getRb_title());
			pstmt2.setString(9, rboard.getRb_content());
			pstmt2.setString(10, rboard.getRb_endRecruit());
			pstmt2.setString(11, rboard.getRb_pj_title());

			pstmt2.executeUpdate();

			// r_skill에 추가
			sql = "INSERT INTO r_skill (rs_num,rb_num,hs_code) VALUES(r_skill_seq.nextval,?,?)";

			for (String skill : rboard.getR_skills()) {
				int intSkill = Integer.parseInt(skill);
				pstmt3 = conn.prepareStatement(sql);
				pstmt3.setInt(1, rb_num);
				pstmt3.setInt(2, intSkill);
				pstmt3.executeUpdate();
			}

			// r_field에 추가
			sql = "INSERT INTO r_field (rf_num,rb_num,f_code) VALUES(r_field_seq.nextval,?,?)";

			for (String field : rboard.getR_fields()) {
				int intField = Integer.parseInt(field);
				pstmt4 = conn.prepareStatement(sql);
				pstmt4.setInt(1, rb_num);
				pstmt4.setInt(2, intField);
				pstmt4.executeUpdate();
			}

			// team 테이블에 추가
			sql = "INSERT INTO team (team_num) VALUES(?)";

			pstmt5 = conn.prepareStatement(sql);
			pstmt5.setInt(1, rb_num);
			pstmt5.executeUpdate();

			// 전체 커밋
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt5, null);
			DBUtil.executeClose(null, pstmt4, null);
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}

	// rboard 수정
	public void modifyRboard(RboardVO rboard) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null; // 글
		PreparedStatement pstmt2 = null; // 모집스킬 삭제
		PreparedStatement pstmt3 = null; // 모집스킬 재등록
		PreparedStatement pstmt4 = null; // 모집필드 삭제
		PreparedStatement pstmt5 = null; // 모집필드 재등록
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			// r_board 수정
			sql = "UPDATE r_board SET rb_category=?,rb_teamsize=?,rb_meet=?,rb_start=?,rb_period=?,"
					+ "rb_endRecruit=?,rb_title=?,rb_content=?,rb_pj_title=?,rb_modify_date=SYSDATE WHERE rb_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rboard.getRb_category());
			pstmt.setInt(2, rboard.getRb_teamsize());
			pstmt.setInt(3, rboard.getRb_meet());
			pstmt.setString(4, rboard.getRb_start());
			pstmt.setInt(5, rboard.getRb_period());
			pstmt.setString(6, rboard.getRb_endRecruit());
			pstmt.setString(7, rboard.getRb_title());
			pstmt.setString(8, rboard.getRb_content());
			pstmt.setString(9, rboard.getRb_pj_title());
			pstmt.setInt(10, rboard.getRb_num());
			pstmt.executeUpdate();

			// r_skill에 저장된 기존 데이터 삭제
			sql = "DELETE FROM r_skill WHERE rb_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, rboard.getRb_num());
			pstmt2.executeUpdate();

			// r_skill에 데이터 추가
			sql = "INSERT INTO r_skill (rs_num,rb_num,hs_code) VALUES(r_skill_seq.nextval,?,?)";

			for (String skill : rboard.getR_skills()) {
				int intSkill = Integer.parseInt(skill);
				pstmt3 = conn.prepareStatement(sql);
				pstmt3.setInt(1, rboard.getRb_num());
				pstmt3.setInt(2, intSkill);
				pstmt3.executeUpdate();
			}

			// r_field에 저장된 기존 데이터 삭제
			sql = "DELETE FROM r_field WHERE rb_num=?";
			pstmt4 = conn.prepareStatement(sql);
			pstmt4.setInt(1, rboard.getRb_num());
			pstmt4.executeUpdate();

			// r_field에 데이터 추가
			sql = "INSERT INTO r_field (rf_num,rb_num,f_code) VALUES(r_field_seq.nextval,?,?)";

			for (String field : rboard.getR_fields()) {
				int intField = Integer.parseInt(field);
				pstmt5 = conn.prepareStatement(sql);
				pstmt5.setInt(1, rboard.getRb_num());
				pstmt5.setInt(2, intField);
				pstmt5.executeUpdate();
			}

			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt5, null);
			DBUtil.executeClose(null, pstmt4, null);
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// rboard 삭제
	public void deleteRboard(int rb_num) throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null; // 북마크
	    PreparedStatement pstmt2 = null; // 댓글
	    PreparedStatement pstmt3 = null; // 모집스킬
	    PreparedStatement pstmt4 = null; // 모집필드
	    PreparedStatement pstmt5 = null; // 팀 (마감안된글은 팀도 지우고, 마감된 글은 팀 안지우기)
	    PreparedStatement pstmt6 = null; // apply 삭제
	    PreparedStatement pstmt7 = null; // 모집글

	    String sql = null;

	    try {
	        conn = DBUtil.getConnection();
	        conn.setAutoCommit(false);


	        // 북마크 삭제
	        sql = "DELETE FROM r_bookmark WHERE rb_num=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, rb_num);
	        pstmt.executeUpdate();

	        // 댓글 삭제
	        sql = "DELETE FROM r_comment WHERE rb_num=?";
	        pstmt2 = conn.prepareStatement(sql);
	        pstmt2.setInt(1, rb_num);
	        pstmt2.executeUpdate();

	        // 모집스킬 삭제
	        sql = "DELETE FROM r_skill WHERE rb_num=?";
	        pstmt3 = conn.prepareStatement(sql);
	        pstmt3.setInt(1, rb_num);
	        pstmt3.executeUpdate();

	        // 모집필드 삭제
	        sql = "DELETE FROM r_field WHERE rb_num=?";
	        pstmt4 = conn.prepareStatement(sql);
	        pstmt4.setInt(1, rb_num);
	        pstmt4.executeUpdate();

	        // apply 삭제
	        sql = "DELETE FROM r_apply WHERE rb_num=?";
	        pstmt5 = conn.prepareStatement(sql);
	        pstmt5.setInt(1, rb_num);
	        pstmt5.executeUpdate();

	        // team_status가 1이 아니고 3이 아닐 때만 rboard 삭제
	        sql = "DELETE FROM r_board WHERE rb_num IN (" +
	                "SELECT r.rb_num FROM r_board r "
	                + "JOIN team t ON r.rb_num = t.team_num WHERE r.rb_num = ? "
	                + "AND (t.team_status != 1 AND t.team_status != 3))";
	        pstmt6 = conn.prepareStatement(sql);
	        pstmt6.setInt(1, rb_num);
	        pstmt6.executeUpdate();
	        
	        // 모집이 마감되지 않은 글(team_status가 0인글)은 team테이블에서도 삭제
	        sql = "DELETE FROM team WHERE team_num=? AND team_status=0";
	        pstmt7 = conn.prepareStatement(sql);
	        pstmt7.setInt(1, rb_num);
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


	// 회원 탈퇴 시 rbaord 관련 정보 삭제 - 예영작성
	public void deleteUserRboard(int mem_num) throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null; // 북마크
	    PreparedStatement pstmt2 = null; // 댓글
	    PreparedStatement pstmt3 = null;
	    PreparedStatement pstmt4 = null;
	    PreparedStatement pstmt5 = null;
	    PreparedStatement pstmt6 = null;
	    PreparedStatement pstmt7 = null;
	    PreparedStatement pstmt8 = null;
	    PreparedStatement pstmt9 = null;
	    PreparedStatement pstmt10 = null;
	    PreparedStatement pstmt11 = null;
	    ResultSet rs = null;
	    List<RboardVO> list = null;

	    String sql = null;

	    try {
	        conn = DBUtil.getConnection();
	        conn.setAutoCommit(false);

	        // 북마크 삭제
	        sql = "DELETE FROM r_bookmark WHERE mem_num=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, mem_num);
	        pstmt.executeUpdate();

	        // 댓글 삭제
	        sql = "DELETE FROM r_comment WHERE mem_num=?";
	        pstmt2 = conn.prepareStatement(sql);
	        pstmt2.setInt(1, mem_num);
	        pstmt2.executeUpdate();
	        
	        // rapply 삭제
	        sql = "DELETE FROM r_apply WHERE mem_num=?";
	        pstmt3 = conn.prepareStatement(sql);
	        pstmt3.setInt(1, mem_num);
	        pstmt3.executeUpdate();
	        
	        // 회원의 rboard 글 불러오기
	        sql = "SELECT * FROM r_board r JOIN team t ON  r.rb_num = t.team_num WHERE mem_num=?";
	        pstmt4 = conn.prepareStatement(sql);
	        pstmt4.setInt(1, mem_num);

	        rs = pstmt4.executeQuery();

	        list = new ArrayList<RboardVO>();
	        while (rs.next()) {
	            RboardVO rboard = new RboardVO();
	            rboard.setRb_num(rs.getInt("rb_num"));
	            rboard.setRb_title(rs.getString("rb_title"));
	            rboard.setRb_pj_title(rs.getString("rb_pj_title"));
	            rboard.setRb_teamsize(rs.getInt("rb_teamsize"));
	            rboard.setRb_endRecruit(rs.getString("rb_endRecruit"));
	            rboard.setTeam_status(rs.getInt("team_status"));

	            list.add(rboard);
	        }

	        // 회원 rboard 관련 데이터 모두 삭제
	        // 미리 PreparedStatement를 생성
	        String sqlBookmark = "DELETE FROM r_bookmark WHERE rb_num=?";
	        String sqlComment = "DELETE FROM r_comment WHERE rb_num=?";
	        String sqlSkill = "DELETE FROM r_skill WHERE rb_num=?";
	        String sqlField = "DELETE FROM r_field WHERE rb_num=?";
	        String sqlApply = "DELETE FROM r_apply WHERE rb_num=?";
	        String sqlRboard = "DELETE FROM r_board WHERE rb_num IN (" +
	                           "SELECT r.rb_num FROM r_board r JOIN team t ON r.rb_num = t.team_num " +
	                           "WHERE r.rb_num = ? AND (t.team_status != 1 AND t.team_status != 3))";
	        String sqlTeam = "DELETE FROM team WHERE team_num=? AND team_status=0";

	        pstmt5 = conn.prepareStatement(sqlBookmark);
	        pstmt6 = conn.prepareStatement(sqlComment);
	        pstmt7 = conn.prepareStatement(sqlSkill);
	        pstmt8 = conn.prepareStatement(sqlField);
	        pstmt9 = conn.prepareStatement(sqlApply);
	        pstmt10 = conn.prepareStatement(sqlRboard);
	        pstmt11 = conn.prepareStatement(sqlTeam);

	        // 각 배치에 추가
	        for (RboardVO rboard : list) {
	            int rb_num = rboard.getRb_num();

	            pstmt5.setInt(1, rb_num);
	            pstmt5.addBatch();

	            pstmt6.setInt(1, rb_num);
	            pstmt6.addBatch();

	            pstmt7.setInt(1, rb_num);
	            pstmt7.addBatch();

	            pstmt8.setInt(1, rb_num);
	            pstmt8.addBatch();

	            pstmt9.setInt(1, rb_num);
	            pstmt9.addBatch();

	            pstmt10.setInt(1, rb_num);
	            pstmt10.addBatch();

	            pstmt11.setInt(1, rb_num);
	            pstmt11.addBatch();
	        }

	        // 배치 실행
	        pstmt5.executeBatch();
	        pstmt6.executeBatch();
	        pstmt7.executeBatch();
	        pstmt8.executeBatch();
	        pstmt9.executeBatch();
	        pstmt10.executeBatch();
	        pstmt11.executeBatch();

	        conn.commit();

	    } catch (Exception e) {
	        conn.rollback();
	        throw new Exception(e);
	    } finally {
	        DBUtil.executeClose(null, pstmt11, null);
	        DBUtil.executeClose(null, pstmt10, null);
	        DBUtil.executeClose(null, pstmt9, null);
	        DBUtil.executeClose(null, pstmt8, null);
	        DBUtil.executeClose(null, pstmt7, null);
	        DBUtil.executeClose(null, pstmt6, null);
	        DBUtil.executeClose(null, pstmt5, null);
	        DBUtil.executeClose(null, pstmt4, null);
	        DBUtil.executeClose(null, pstmt3, null);
	        DBUtil.executeClose(null, pstmt2, null);
	        DBUtil.executeClose(rs, pstmt, conn);
	    }
	}


	// rboard 개수 구하기
	public int getRboardCount(String[] r_skills, String rb_category, String r_fields, String rb_meet,
			String search_key, boolean recruiting_filter) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = null;
		String sub_sql = "";
		List<String> conditions = null;

		try {
			conn = DBUtil.getConnection();

			// 조건을 배열이나 리스트로 관리하여 반복문으로 처리
			conditions = new ArrayList<>();

			if (r_skills != null && r_skills.length != 0) {
				String r_skills_string = "";
				for (int i = 0; i < r_skills.length; i++) {
					r_skills_string += "REGEXP_LIKE(hs_name, '(^|,)" + r_skills[i] + "($|,)')";
					if (i != r_skills.length - 1) {
						r_skills_string += " AND ";
					}
				}
				r_skills_string += " OR hs_name = '" + String.join("", r_skills) + "'";
				conditions.add(r_skills_string);

			}
			if (rb_category != null && rb_category != "" && !rb_category.equals("2")) {
				conditions.add("rb_category = " + rb_category);
			}
			if (r_fields != null && r_fields != "") {
				conditions.add("f_name LIKE '%'||'" + r_fields + "'||'%'");
			}
			if (rb_meet != null && rb_meet != "") {
				conditions.add("rb_meet = " + rb_meet);
			}
			if (search_key != null && search_key != "") {
				conditions.add("rb_title LIKE '%'||'" + search_key + "'||'%'");
			}
			if (recruiting_filter) {
				conditions.add("rb_endRecruit >= TO_CHAR(sysdate, 'YYYY-MM-DD') AND team_status != 1");
			}

			// 조건이 있을 경우에만 WHERE 추가
			if (!conditions.isEmpty()) {
				sub_sql += "AND " + String.join(" AND ", conditions);
			}


			sql = "SELECT COUNT(*) FROM ( " +
		          "    SELECT r_board.rb_num " +
		          "    FROM r_board " +
		          "    LEFT OUTER JOIN ( " +
		          "        SELECT rb_num, LISTAGG(hs_name, ',') WITHIN GROUP (ORDER BY hs_name) hs_name, " +
		          "               LISTAGG(hs_photo, ',') WITHIN GROUP (ORDER BY hs_name) hs_photo " +
		          "        FROM r_skill " +
		          "        JOIN hard_skill USING(hs_code) " +
		          "        GROUP BY rb_num " +
		          "    ) hs_agg ON r_board.rb_num = hs_agg.rb_num " +
		          "    LEFT OUTER JOIN ( " +
		          "        SELECT rb_num, LISTAGG(f_name, ',') WITHIN GROUP (ORDER BY f_name) f_name " +
		          "        FROM r_field " +
		          "        JOIN field_db USING(f_code) " +
		          "        GROUP BY rb_num " +
		          "    ) f_agg ON r_board.rb_num = f_agg.rb_num " +
		          "    LEFT OUTER JOIN ( " +
		          "        SELECT rb_num, COUNT(ra_num) AS apply_count " +
		          "        FROM r_apply " +
		          "        GROUP BY rb_num " +
		          "    ) apply_agg ON r_board.rb_num = apply_agg.rb_num " +
		          "    LEFT OUTER JOIN TEAM ON r_board.rb_num = team.team_num " +
		          "    LEFT OUTER JOIN member ON r_board.mem_num = member.mem_num " +
		          "    WHERE mem_auth != 0 " + sub_sql +
		          ")";

			pstmt = conn.prepareStatement(sql);

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

	// rboard 목록 구하기
	public List<RboardVO> getRboardList(int start, int end, String[] r_skills, String rb_category, String r_fields,
			String rb_meet, String search_key, boolean recruiting_filter) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RboardVO> list = null;
		String sql = null;
		String sub_sql = "";
		List<String> conditions = null;

		try {
			// 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();

			// 조건을 배열이나 리스트로 관리하여 반복문으로 처리
			conditions = new ArrayList<>();

			// 조건 설정
			if (r_skills != null && r_skills.length != 0) {
				String r_skills_string = "(";
				for (int i = 0; i < r_skills.length; i++) {
					r_skills_string += "REGEXP_LIKE(hs_name, '(^|,)" + r_skills[i] + "($|,)')";
					if (i != r_skills.length - 1) {
						r_skills_string += " AND ";
					}
				}
				r_skills_string += " OR hs_name = '" + String.join("", r_skills) + "')";
				conditions.add(r_skills_string);
			}
			if (rb_category != null && rb_category != "" && !rb_category.equals("2")) {
				conditions.add("rb_category = " + rb_category);
			}
			if (r_fields != null && r_fields != "") {
				conditions.add("f_name LIKE '%'||'" + r_fields + "'||'%'");
			}
			if (rb_meet != null && rb_meet != "") {
				conditions.add("rb_meet = " + rb_meet);
			}
			if (search_key != null && search_key != "") {
				conditions.add("rb_title LIKE '%'||'" + search_key + "'||'%'");
			}
			if (recruiting_filter) {
				conditions.add("rb_endRecruit >= TO_CHAR(sysdate, 'YYYY-MM-DD') AND team_status != 1");
			}

			// 조건이 있을 경우에만 WHERE 추가
			if (!conditions.isEmpty()) {
				sub_sql += "WHERE " + String.join(" AND ", conditions);
			}

			// SQL문 작성
			sql = "SELECT * FROM ( " + "  SELECT a.*, rownum rnum FROM ( "
					+ "    SELECT r_board.*, team.*, member.mem_auth, hs_agg.hs_name, hs_agg.hs_photo, "
					+ "           f_agg.f_name, NVL(apply_agg.apply_count, 0) AS apply_count " + "    FROM r_board "
					+ "    LEFT OUTER JOIN (SELECT rb_num, LISTAGG(hs_name, ',') WITHIN GROUP (ORDER BY hs_name) hs_name, "
					+ "                            LISTAGG(hs_photo, ',') WITHIN GROUP (ORDER BY hs_name) hs_photo "
					+ "                     FROM r_skill JOIN hard_skill USING(hs_code) GROUP BY rb_num) hs_agg "
					+ "    ON r_board.rb_num = hs_agg.rb_num "
					+ "    LEFT OUTER JOIN (SELECT rb_num, LISTAGG(f_name, ',') WITHIN GROUP (ORDER BY f_name) f_name "
					+ "                     FROM r_field JOIN field_db USING(f_code) GROUP BY rb_num) f_agg "
					+ "    ON r_board.rb_num = f_agg.rb_num "
					+ "    LEFT OUTER JOIN (SELECT rb_num, COUNT(ra_num) AS apply_count FROM r_apply GROUP BY rb_num) apply_agg "
					+ "    ON r_board.rb_num = apply_agg.rb_num "
					+ "	   LEFT OUTER JOIN TEAM ON r_board.rb_num = team.team_num"
					+ "	   LEFT OUTER JOIN member ON r_board.mem_num = member.mem_num WHERE mem_auth !=0"
					+ "    ORDER BY r_board.rb_num DESC) a " + sub_sql + ") WHERE rnum >= ? AND rnum <= ?";

			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			// SQL문 실행
			rs = pstmt.executeQuery();

			list = new ArrayList<RboardVO>();
			while (rs.next()) {
				RboardVO rboard = new RboardVO();
				rboard.setRb_num(rs.getInt("rb_num"));
				rboard.setRb_reg_date(rs.getDate("rb_reg_date"));
				rboard.setRb_category(rs.getInt("rb_category"));
				rboard.setRb_meet(rs.getInt("rb_meet"));
				rboard.setRb_teamsize(rs.getInt("rb_teamsize"));
				rboard.setRb_period(rs.getInt("rb_period"));
				rboard.setRb_start(rs.getString("rb_start"));
				rboard.setRb_title(rs.getString("rb_title"));
				rboard.setRb_endRecruit(rs.getString("rb_endRecruit"));
				rboard.setRb_hit(rs.getInt("rb_hit"));
				rboard.setRb_pj_title(rs.getString("rb_pj_title"));
				rboard.setRb_apply_count(rs.getInt("apply_count"));
				rboard.setTeam_status(rs.getInt("team_status"));

				// 요구기술, 모집필드 배열로 저장
				rboard.setHs_name_arr(rs.getString("hs_name").split(","));
				rboard.setHs_photo_arr(rs.getString("hs_photo").split(","));
				rboard.setF_name_arr(rs.getString("f_name").split(","));

				list.add(rboard);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}

	// 인기글 슬라이드 rboard 목록 띄우기 - 예영 작성
	public List<RboardVO> getSlideList() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RboardVO> list = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.* FROM ( "
					+ "    SELECT r_board.*, team.*, hs_agg.hs_name, hs_agg.hs_photo, "
					+ "           f_agg.f_name, NVL(apply_agg.apply_count, 0) AS apply_count " + " FROM r_board "
					+ "    LEFT OUTER JOIN (SELECT rb_num, LISTAGG(hs_name, ',') WITHIN GROUP (ORDER BY hs_name) hs_name, "
					+ "                            LISTAGG(hs_photo, ',') WITHIN GROUP (ORDER BY hs_name) hs_photo "
					+ "                     FROM r_skill JOIN hard_skill USING(hs_code) GROUP BY rb_num) hs_agg "
					+ "    ON r_board.rb_num = hs_agg.rb_num "
					+ "    LEFT OUTER JOIN (SELECT rb_num, LISTAGG(f_name, ',') WITHIN GROUP (ORDER BY f_name) f_name "
					+ "                     FROM r_field JOIN field_db USING(f_code) GROUP BY rb_num) f_agg "
					+ "    ON r_board.rb_num = f_agg.rb_num "
					+ "    LEFT OUTER JOIN (SELECT rb_num, COUNT(ra_num) AS apply_count FROM r_apply GROUP BY rb_num) apply_agg "
					+ "    ON r_board.rb_num = apply_agg.rb_num "
					+ "	   LEFT OUTER JOIN TEAM ON r_board.rb_num = team.team_num WHERE team.team_status= 0 AND rb_endrecruit > SYSDATE"
					+ "    ORDER BY apply_count DESC " + "  ) a " + ") WHERE ROWNUM <= 8";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			list = new ArrayList<RboardVO>();
			while (rs.next()) {
				RboardVO rboard = new RboardVO();
				rboard.setRb_num(rs.getInt("rb_num"));
				rboard.setRb_reg_date(rs.getDate("rb_reg_date"));
				rboard.setRb_category(rs.getInt("rb_category"));
				rboard.setRb_meet(rs.getInt("rb_meet"));
				rboard.setRb_teamsize(rs.getInt("rb_teamsize"));
				rboard.setRb_period(rs.getInt("rb_period"));
				rboard.setRb_start(rs.getString("rb_start"));
				rboard.setRb_title(rs.getString("rb_title"));
				rboard.setRb_endRecruit(rs.getString("rb_endRecruit"));
				rboard.setRb_hit(rs.getInt("rb_hit"));
				rboard.setRb_apply_count(rs.getInt("apply_count"));
				rboard.setTeam_status(rs.getInt("team_status"));

				// 요구기술, 모집필드 배열로 저장
				rboard.setHs_name_arr(rs.getString("hs_name").split(","));
				rboard.setHs_photo_arr(rs.getString("hs_photo").split(","));
				rboard.setF_name_arr(rs.getString("f_name").split(","));

				list.add(rboard);
			}

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return list;
	}

	// 작성자별 rboard 목록 구하기
	public List<RboardVO> getRboardListByMemNum(int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		List<RboardVO> list = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM r_board WHERE mem_num=? ORDER BY rb_reg_date DESC";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);

			rs = pstmt.executeQuery();

			list = new ArrayList<RboardVO>();
			while (rs.next()) {
				RboardVO rboard = new RboardVO();
				int rb_num =rs.getInt("rb_num");
				rboard.setRb_num(rs.getInt("rb_num"));
				rboard.setRb_title(rs.getString("rb_title"));
				rboard.setRb_pj_title(rs.getString("rb_pj_title"));
				rboard.setRb_teamsize(rs.getInt("rb_teamsize"));
				rboard.setRb_endRecruit(rs.getString("rb_endRecruit"));

				sql = "SELECT team_status FROM team WHERE team_num=?";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, rboard.getRb_num());
				rs2 = pstmt2.executeQuery();
				if (rs2.next()) {
					rboard.setTeam_status(rs2.getInt("team_status"));
					// rboard.setTeam_num(rs2.getInt("team_num"));
				}
				
				sql = "SELECT COUNT(*) FROM r_apply WHERE rb_num=?";
				pstmt3 = conn.prepareStatement(sql);
				pstmt3.setInt(1, rb_num);
				rs3 = pstmt3.executeQuery();
				if(rs3.next()) {
					rboard.setApply_count(rs3.getInt(1));
				}
				
				sql = "SELECT COUNT(*) FROM r_apply WHERE rb_num=? AND ra_pass=1";
				pstmt4 = conn.prepareStatement(sql);
				pstmt4.setInt(1, rb_num);
				rs4 = pstmt4.executeQuery();
				if(rs4.next()) {
					rboard.setPass_count(rs4.getInt(1));
				}
				
				
				list.add(rboard);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs4, pstmt4, null);
			DBUtil.executeClose(rs3, pstmt3, null);
			DBUtil.executeClose(rs2, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}

	// rboard detail 구하기
	public RboardVO getrboard(int rb_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RboardVO rboard = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM member JOIN member_detail USING(mem_num) JOIN r_board USING(mem_num)"
					+ " JOIN TEAM ON r_board.rb_num = team.team_num JOIN"
					+ " (SELECT rb_num, LISTAGG(hs_name,',') within group ( order by hs_name) hs_name ,"
					+ " LISTAGG(hs_photo,',') within group ( order by hs_name)  hs_photo"
					+ " FROM r_skill JOIN hard_skill USING(hs_code) group by rb_num) USING(rb_num) JOIN"
					+ " (SELECT rb_num, LISTAGG(f_name,',') within group ( order by f_name) f_name"
					+ " FROM r_field JOIN field_db USING(f_code) group by rb_num) USING(rb_num)" + " WHERE rb_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rb_num);
			rs = pstmt.executeQuery();

			rboard = new RboardVO();
			if (rs.next()) {
				rboard.setMem_num(rs.getInt("mem_num"));
				rboard.setRb_num(rs.getInt("rb_num"));
				rboard.setMem_nickname(rs.getString("mem_nickname"));
				rboard.setMem_photo(rs.getString("mem_photo"));
				rboard.setRb_reg_date(rs.getDate("rb_reg_date"));
				rboard.setRb_category(rs.getInt("rb_category"));
				rboard.setRb_start(rs.getString("rb_start"));
				rboard.setRb_meet(rs.getInt("rb_meet"));
				rboard.setRb_period(rs.getInt("rb_period"));
				rboard.setRb_teamsize(rs.getInt("rb_teamsize"));
				rboard.setRb_title(rs.getString("rb_title"));
				rboard.setRb_content(rs.getString("rb_content"));
				rboard.setRb_endRecruit(rs.getString("rb_endrecruit"));
				rboard.setRb_pj_title(rs.getString("rb_pj_title"));
				rboard.setRb_hit(rs.getInt("rb_hit"));
				rboard.setTeam_status(rs.getInt("team_status"));

				// 요구기술, 모집필드 배열로 저장
				rboard.setHs_name_arr(rs.getString("hs_name").split(","));
				rboard.setHs_photo_arr(rs.getString("hs_photo").split(","));
				rboard.setF_name_arr(rs.getString("f_name").split(","));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return rboard;
	}

	// rboard 지원하기(apply)
	public void applyToRboard(RapplyVO rapply) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO r_apply (ra_num, rb_num, mem_num, ra_content) VALUES(r_apply_seq.nextval,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rapply.getRb_num());
			pstmt.setInt(2, rapply.getMem_num());
			pstmt.setString(3, rapply.getRa_content());

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// rboard 지원취소 - 민재
	public void deleteApply(int ra_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			// 커넥션풀에 커넥션 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "DELETE FROM r_apply WHERE ra_num=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1, ra_num);
			// SQL문 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 나의 모집글에 지원한 신청자 수
	public int myRboardApplyCountByRbNum(int rb_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(*) FROM r_apply ra JOIN r_board rb ON ra.rb_num = rb.rb_num WHERE ra.rb_num =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rb_num);

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

	// 나의 모집글에 지원한 신청자 리스트 - 민재
	public List<RapplyVO> myRboardApplyListByRbNum(int rb_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RapplyVO> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();

			String sql = "SELECT ra.* FROM r_apply ra JOIN r_board rb ON ra.rb_num = rb.rb_num WHERE ra.rb_num =?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rb_num);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				RapplyVO rapply = new RapplyVO();
				rapply.setRa_num(rs.getInt("ra_num"));
				rapply.setRb_num(rs.getInt("rb_num"));
				rapply.setMem_num(rs.getInt("mem_num"));
				rapply.setRa_content(rs.getString("ra_content"));
				rapply.setRa_date(rs.getDate("ra_date"));
				rapply.setRa_pass(rs.getInt("ra_pass"));

				list.add(rapply);

			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return list;
	}

	// 나의 모집글에 지원한 신청자 삭제-민재
	public void deleteMyRboardApply(int rb_num, int ra_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM r_apply WHERE rb_num = ? AND ra_num = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rb_num);
			pstmt.setInt(2, ra_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}

	}

	// 회원별 코메구하기 모집글 댓글 리스트 - 마이페이지
	public List<RcommentVO> getRcommentListByMemNum(int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RcommentVO> list = new ArrayList<>();
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "select b.rb_title, c.* from r_board b JOIN r_comment c ON b.rb_num = c.rb_num WHERE c.mem_num=? ORDER BY rc_reg_date DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				RcommentVO rcomment = new RcommentVO();
				rcomment.setRb_num(rs.getInt("rb_num"));
				rcomment.setRc_num(rs.getInt("rc_num"));
				rcomment.setRc_content(rs.getString("rc_content"));
				rcomment.setRb_title(rs.getString("rb_title"));
				// 리스트 추가
				list.add(rcomment);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}

	// 회원별 코메신청 리스트 - 마이페이지의 '나의코메신청'에서 불러옴.
	public List<RboardVO> getAppliedBoardListByMemNum(int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RboardVO> list = new ArrayList<>();
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM r_apply ra JOIN r_board rb USING(rb_num) WHERE ra.mem_num=? ORDER BY ra_num DESC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				RboardVO rboard = new RboardVO();
				// 코메신청 정보 설정
				rboard.setMem_num(rs.getInt("mem_num"));
				rboard.setRa_num(rs.getInt("ra_num"));
				rboard.setRb_num(rs.getInt("rb_num"));
				rboard.setRa_pass(rs.getInt("ra_pass"));
				rboard.setRb_category(rs.getInt("rb_category"));
				rboard.setRb_title(rs.getString("rb_title"));
				rboard.setRb_pj_title(rs.getString("rb_pj_title"));
				rboard.setRb_apply_count(myRboardApplyCountByRbNum(rs.getInt("rb_num")));
				rboard.setRb_teamsize(rs.getInt("rb_teamsize"));
				rboard.setRb_start(rs.getString("rb_start"));
				rboard.setRb_period(rs.getInt("rb_period"));
				rboard.setRb_endRecruit(rs.getString("rb_endrecruit"));
				// 리스트에 추가
				list.add(rboard);
			}

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return list;
	}

	// 이미 신청한 글인지 확인
	public boolean alreadyApplied(int rb_num, int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean alreadyApplied = false;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT ra_num FROM r_apply WHERE rb_num=? AND mem_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rb_num);
			pstmt.setInt(2, mem_num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				alreadyApplied = true;
			}

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return alreadyApplied;
	}

	// 댓글 등록
	public void writeRcomment(RcommentVO rcomment) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO r_comment(rc_num,mem_num,rb_num,rc_content) VALUES(r_comment_seq.nextval,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rcomment.getMem_num());
			pstmt.setInt(2, rcomment.getRb_num());
			pstmt.setString(3, StringUtil.useBrNoHTML(rcomment.getRc_content()));

			pstmt.executeUpdate();

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 댓글 목록
	public List<RcommentVO> getRcommentList(int rb_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RcommentVO> list = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT c.*, m.mem_nickname, m.mem_photo FROM r_comment c LEFT OUTER JOIN"
					+ " member_detail m ON(c.mem_num = m.mem_num) WHERE rb_num=? ORDER BY rc_num DESC";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rb_num);

			rs = pstmt.executeQuery();

			list = new ArrayList<RcommentVO>();
			while (rs.next()) {
				RcommentVO rcomment = new RcommentVO();
				rcomment.setRc_num(rs.getInt("rc_num"));
				rcomment.setRb_num(rs.getInt("rb_num"));
				rcomment.setMem_num(rs.getInt("mem_num"));
				rcomment.setRc_content(rs.getString("rc_content"));
				rcomment.setMem_photo(rs.getString("mem_photo"));
				rcomment.setMem_nickname(rs.getString("mem_nickname"));

				rcomment.setReg_date_string(DurationFromNow.getTimeDiffLabel(rs.getDate("rc_reg_date")));

				if (rs.getDate("rc_modify_date") != null) {
					rcomment.setModify_date_string(DurationFromNow.getTimeDiffLabel(rs.getDate("rc_modify_date")));
				}

				list.add(rcomment);
			}
		} catch (Exception e) {
			throw new Exception();
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return list;
	}

	// 개별 댓글vo 구하기
	public RcommentVO getRcomment(int rc_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RcommentVO rcomment = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM r_comment WHERE rc_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rc_num);

			rs = pstmt.executeQuery();

			rcomment = new RcommentVO();
			if (rs.next()) {
				rcomment.setRc_num(rs.getInt("rc_num"));
				rcomment.setRc_content(rs.getString("rc_content"));
				rcomment.setMem_num(rs.getInt("mem_num"));
			}

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return rcomment;
	}

	// 댓글 수정
	public void modifyRcomment(RcommentVO rcomment) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE r_comment SET rc_content=?,rc_modify_date=SYSDATE WHERE rc_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, StringUtil.useBrNoHTML(rcomment.getRc_content()));
			pstmt.setInt(2, rcomment.getRc_num());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 댓글 삭제
	public void deleteRcomment(int rc_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM r_comment WHERE rc_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rc_num);

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 조회수 증가
	public void updateReadCount(int rb_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE r_board SET rb_hit=rb_hit+1 WHERE rb_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rb_num);

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 북마크 추가
	public void insertRbookmark(RbookmarkVO rbookmark) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO r_bookmark(rb_num, mem_num) VALUES(?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rbookmark.getRb_num());
			pstmt.setInt(2, rbookmark.getMem_num());

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 북마크 삭제
	public void deleteRbookmark(RbookmarkVO rbookmark) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM r_bookmark WHERE rb_num=? AND mem_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rbookmark.getRb_num());
			pstmt.setInt(2, rbookmark.getMem_num());

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}

	}

	// 회원별 북마크 리스트 - 마이페이지 북마크-민재 작성
	public List<RboardVO> getBookMarkBoardListByMemNum(int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RboardVO> list = new ArrayList<>();
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM r_bookmark ra JOIN r_board rb USING(rb_num) WHERE ra.mem_num=? ORDER BY rb_endrecruit ASC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				RboardVO rboard = new RboardVO();
				// 코메신청 정보 설정
				rboard.setRb_num(rs.getInt("rb_num"));
				rboard.setRb_category(rs.getInt("rb_category"));
				rboard.setRb_pj_title(rs.getString("rb_pj_title"));
				rboard.setRb_teamsize(rs.getInt("rb_teamsize"));
				rboard.setRb_start(rs.getString("rb_start"));
				rboard.setRb_period(rs.getInt("rb_period"));
				rboard.setRb_title(rs.getString("rb_title"));
				rboard.setRb_endRecruit(rs.getString("rb_endrecruit"));
				// 리스트에 추가
				list.add(rboard);
			}

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return list;
	}

	// 북마크 하나 가져오기
	public RbookmarkVO getRbookmark(RbookmarkVO rbookmark) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM r_bookmark WHERE rb_num=? AND mem_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rbookmark.getRb_num());
			pstmt.setInt(2, rbookmark.getMem_num());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rbookmark;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

	}

	// 북마크 개수 가져오기
	public int getRbookmarkCount(int rb_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM r_bookmark WHERE rb_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rb_num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return cnt;
	}

	public int getRboardCountManage(String keyfield, String keyword) throws Exception {
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
					sub_sql += "WHERE  rb_title LIKE '%' || ? || '%'";
				} else if (keyfield.equals("2")) {
					sub_sql += "WHERE  mem_nickname LIKE '%' || ? || '%'";
				}
			}

			sql = "SELECT COUNT(*) FROM r_board JOIN member_detail USING(mem_num) " + sub_sql;

			pstmt = conn.prepareStatement(sql);
			if (keyword != null && !"".equals(keyword)) {
				pstmt.setString(1, keyword);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 컬럼 인덱스
				count = rs.getInt(1);
				// count(*)처럼 기호가 있으면 안에 쓰지 않고 알리아스를 명시해주는 것도 번거롭기 때문에 인덱스 사용
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return count;
	}

	// 게시판 목록 가져오기
	public List<RboardVO> getListBoardManage(int start, int end, String keyfield, String keyword) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RboardVO> list = null;
		String sql = null;
		String sub_sql = ""; // 이 부분은 필요 없을 것 같습니다.
		int cnt = 0;

		try {
			conn = DBUtil.getConnection();

			if (keyword != null && !"".equals(keyword)) {
				if (keyfield.equals("1")) {
					sub_sql += "WHERE  rb_title LIKE '%' || ? || '%'";
				} else if (keyfield.equals("2")) {
					sub_sql += "WHERE  mem_nickname LIKE '%' || ? || '%'";
				}
			}

			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM r_board LEFT OUTER JOIN member_detail USING(mem_num)  "
					+ sub_sql + " ORDER BY rb_num DESC) a) WHERE rnum >= ? AND rnum <= ?";

			pstmt = conn.prepareStatement(sql);

			// 검색어가 있을 경우에만 바인딩
			if (keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%" + keyword + "%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);

			rs = pstmt.executeQuery();

			list = new ArrayList<RboardVO>();
			while (rs.next()) {
				RboardVO board = new RboardVO();
				board.setRb_num(rs.getInt("rb_num"));
				board.setRb_title(StringUtil.useNoHTML(rs.getString("rb_title")));
				board.setMem_nickname(rs.getString("mem_nickname"));
				board.setRb_reg_date(rs.getDate("Rb_reg_date"));
				board.setMem_num(rs.getInt("mem_num"));
				list.add(board);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return list;
	}

}
