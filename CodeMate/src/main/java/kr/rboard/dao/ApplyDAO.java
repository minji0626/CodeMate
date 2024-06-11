package kr.rboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.cboard.vo.CboardVO;
import kr.mate.vo.MateVO;
import kr.rboard.vo.RapplyVO;
import kr.rboard.vo.RboardVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class ApplyDAO {
	private static ApplyDAO instance = new ApplyDAO();
	public static ApplyDAO getInstance() {
		return instance;
	}
	private ApplyDAO() {}

	// 신청글 정보
	public RapplyVO applyInfo(int ra_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RapplyVO rapply = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();

			sql = "SELECT * FROM r_apply WHERE ra_num = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ra_num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 신청글 정보를 가져와서 RapplyVO 객체에 설정
				rapply = new RapplyVO();
				rapply.setRa_num(rs.getInt("ra_num"));
				rapply.setRb_num(rs.getInt("rb_num"));
				rapply.setMem_num(rs.getInt("mem_num"));
				rapply.setRa_content(rs.getString("ra_content"));
				rapply.setRa_date(rs.getDate("ra_date"));
				rapply.setRa_pass(rs.getInt("ra_pass"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return rapply; 
	}

	//나의 모집글에 지원한 신청자 리스트 - 민재 + 주은 수정
	public List<RapplyVO> myRboardApplyListByRbNum(int rb_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RapplyVO> list = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();

			sql = "SELECT ra.*, md.*, rb.rb_title FROM r_apply ra JOIN r_board rb ON ra.rb_num = rb.rb_num JOIN member_detail md ON ra.mem_num = md.mem_num WHERE ra.rb_num = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rb_num);

			rs = pstmt.executeQuery();

			list = new ArrayList<RapplyVO>();
			while (rs.next()) {
				RapplyVO rapply = new RapplyVO();
				rapply.setRa_num(rs.getInt("ra_num"));
				rapply.setRb_num(rs.getInt("rb_num"));
				rapply.setRb_title(rs.getString("rb_title"));
				rapply.setMem_num(rs.getInt("mem_num"));
				rapply.setRa_content(rs.getString("ra_content"));
				rapply.setRa_date(rs.getDate("ra_date"));
				rapply.setRa_pass(rs.getInt("ra_pass"));
				rapply.setMem_nickname(rs.getString("mem_nickname"));
				rapply.setMem_photo(rs.getString("mem_photo"));

				list.add(rapply);

			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return list;
	}

	// 코메 신청자 합격시키기
	public void passMember(int ra_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();

			// ra_pass에 합격 값으로 업데이트
			sql = "UPDATE r_apply SET ra_pass=1 WHERE ra_num=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, ra_num);

			pstmt.executeUpdate();

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 코메 신청자 불합격 시키기
	public void unPassMember(int ra_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();

			// ra_pass에 합격 값으로 업데이트
			sql = "UPDATE r_apply SET ra_pass=0 WHERE ra_num=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, ra_num);

			pstmt.executeUpdate();

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 코메 합격자 수 세기
	public int howManyPass(int rb_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();

			// 게시글 번호로 신청자 중 합격한 사람 세기 
			sql = "SELECT COUNT(*) FROM r_apply WHERE rb_num=? AND ra_pass=1"; 
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, rb_num);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}

	// 코메 합격자 수와 모집 인원 같은지 확인
	public boolean passAndSize(int rb_num, int passCount) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String sql = null;
		int size = 0;
		boolean check = false;

		try {
			conn = DBUtil.getConnection();

			// 게시글 번호로 신청자 중 합격한 사람 세기 
			sql = "SELECT rb_teamsize FROM r_board WHERE rb_num=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rb_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				size = rs.getInt(1);
			}

			if(size == passCount) {
				check = true;
			}

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return check;
	}
	
	public boolean minimumTeamMember(int rb_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String sql = null;
		int size = 0;
		boolean check = false;

		try {
			conn = DBUtil.getConnection();

			// 게시글 번호로 신청자 중 합격한 사람 세기 
			sql = "SELECT * FROM r_apply WHERE rb_num=? AND ra_pass=1"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rb_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				check = true;
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return check;
	}
	
	// 팀 활성화 (모집 마감, 팀 활성화, 팀 멤버 추가)
	public void teamActivation(int rb_num) throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    PreparedStatement pstmt2 = null;
	    PreparedStatement pstmt3 = null;
	    PreparedStatement pstmt4 = null;
	    PreparedStatement pstmt5 = null;
	    ResultSet rs = null;
	    ResultSet rs2 = null;
	    String sql = null;
	    int leader_mem_num = 0;
	    int mem_num = 0;
	    try {
	        conn = DBUtil.getConnection();

	        // 팀 활성화
	        sql = "UPDATE team SET team_status=1 WHERE team_num=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, rb_num);
	        pstmt.executeUpdate();

	        // 팀장의 mem_num 받기
	        sql = "SELECT mem_num FROM r_board WHERE rb_num=?";
	        pstmt2 = conn.prepareStatement(sql);
	        pstmt2.setInt(1, rb_num);
	        rs = pstmt2.executeQuery();
	        if (rs.next()) {
	            leader_mem_num = rs.getInt(1);
	        }

	        // 팀 멤버 - 팀장 추가
	        sql = "INSERT INTO team_member (mem_num, team_num, tm_auth) VALUES (?, ?, 4)";
	        pstmt3 = conn.prepareStatement(sql);
	        pstmt3.setInt(1, leader_mem_num);
	        pstmt3.setInt(2, rb_num);
	        pstmt3.executeUpdate();

	        // 팀원의 mem_num 받기
	        sql = "SELECT mem_num FROM r_apply WHERE rb_num=? AND ra_pass=1";
	        pstmt4 = conn.prepareStatement(sql);
	        pstmt4.setInt(1, rb_num);
	        rs2 = pstmt4.executeQuery();
	        while (rs2.next()) {
	            mem_num = rs2.getInt(1);
	            // 팀 멤버 - 팀원 추가
	            sql = "INSERT INTO team_member (mem_num, team_num, tm_auth) VALUES(?, ?, 3)";
	            pstmt5 = conn.prepareStatement(sql);
	            pstmt5.setInt(1, mem_num);
	            pstmt5.setInt(2, rb_num);
	            pstmt5.executeUpdate();
	        }

	    } catch (Exception e) {
	        throw new Exception(e);
	    } finally {
	        DBUtil.executeClose(null, pstmt5, null);
	        DBUtil.executeClose(null, pstmt4, null);
	        DBUtil.executeClose(null, pstmt3, null);
	        DBUtil.executeClose(rs2, pstmt2, null);
	        DBUtil.executeClose(rs, pstmt, conn);
	    }
	}
	
	// rboard 목록 구하기
		public List<RboardVO> getRboardList(int start, int end) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<RboardVO> list = null;
			String sql = null;

			try {
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM ( " + "  SELECT a.*, rownum rnum FROM ( "
						+ "    SELECT r_board.*, hs_agg.hs_name, hs_agg.hs_photo, "
						+ "           f_agg.f_name, NVL(apply_agg.apply_count, 0) AS apply_count " + "    FROM r_board "
						+ "    LEFT OUTER JOIN (SELECT rb_num, LISTAGG(hs_name, ',') WITHIN GROUP (ORDER BY hs_name) hs_name, "
						+ "                            LISTAGG(hs_photo, ',') WITHIN GROUP (ORDER BY hs_name) hs_photo "
						+ "                     FROM r_skill JOIN hard_skill USING(hs_code) GROUP BY rb_num) hs_agg "
						+ "    ON r_board.rb_num = hs_agg.rb_num "
						+ "    LEFT OUTER JOIN (SELECT rb_num, LISTAGG(f_name, ',') WITHIN GROUP (ORDER BY f_name) f_name "
						+ "                     FROM r_field JOIN field_db USING(f_code) GROUP BY rb_num) f_agg "
						+ "    ON r_board.rb_num = f_agg.rb_num "
						+ "    LEFT OUTER JOIN (SELECT rb_num, COUNT(ra_num) AS apply_count FROM r_apply GROUP BY rb_num) apply_agg "
						+ "    ON r_board.rb_num = apply_agg.rb_num " + "    ORDER BY r_board.rb_num DESC " + "  ) a "
						+ ") WHERE rnum >= ? AND rnum <= ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);

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
					rboard.setRb_pj_title(rs.getString("rb_pj_title"));

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
		
		public boolean checkActivation(int rb_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			ResultSet rs = null;
			String sql = null;
			boolean check = false;

			try {
				conn = DBUtil.getConnection();

				// 게시글 번호로 신청자 중 합격한 사람 세기 
				sql = "SELECT team_num FROM team WHERE team_status=1 AND team_num=?"; 
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, rb_num);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					check = true;
				}


			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return check;
		}
		

		public boolean isTeamActive(int rb_num) throws Exception{
			 Connection conn = null;
		        PreparedStatement pstmt = null;
		        ResultSet rs = null;
		        boolean check = false;
		        try {
		            conn = DBUtil.getConnection();
		            String sql = "SELECT * FROM team WHERE team_num=? AND team_status=1";
		            pstmt = conn.prepareStatement(sql);
		            pstmt.setInt(1, rb_num);
		            rs = pstmt.executeQuery();
		            if (rs.next()) {
		                check = true;
		            }
		        } catch (Exception e) {
		            throw new Exception(e);
		        } finally {
		            DBUtil.executeClose(rs, pstmt, conn);
		        }
		        return check;
		}
}
