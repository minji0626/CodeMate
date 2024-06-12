package kr.tmember.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import kr.member.vo.TeamVO;
import kr.tmember.vo.MateReviewVO;
import kr.tmember.vo.TmemberVO;
import kr.util.DBUtil;
import oracle.jdbc.OracleConnection.CommitOption;

public class TmemberDAO {
	private static TmemberDAO instance = new TmemberDAO();

	public static TmemberDAO getInstance() {
		return instance;
	}

	private TmemberDAO() { }

	// team_status 3으로 변경시키기
	public void projectDone (int team_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE team SET team_status = 3 WHERE team_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, team_num);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	/*
	 * // team_status가 3이고 tm_review_status가 0인 멤버들 선택하기 public void noReviewMem
	 * (int team_num) throws Exception { Connection conn = null; PreparedStatement }
	 */

	// team_setting에서 사용되는 팀멤버 count 하기
	public int getTmemberCount (int team_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM team_member JOIN member USING(mem_num) JOIN member_detail USING(mem_num) WHERE team_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, team_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}


	// team_setting에 쓸 목록 가져오기
	public List<TmemberVO> getTeamMembers(int team_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<TmemberVO> list = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM team_member JOIN member USING(mem_num) JOIN member_detail USING(mem_num) JOIN team USING(team_num) WHERE team_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,team_num);
			rs = pstmt.executeQuery();

			list = new ArrayList<TmemberVO>();
			while (rs.next()) {
				TmemberVO tmember = new TmemberVO();
				tmember.setMem_photo(rs.getString("mem_photo"));
				tmember.setMem_num(rs.getInt("mem_num"));
				tmember.setMem_id(rs.getString("mem_id"));
				tmember.setMem_auth(rs.getInt("mem_auth"));
				tmember.setMem_level(rs.getInt("mem_level"));
				tmember.setTeam_num(rs.getInt("team_num"));
				tmember.setMem_nickname(rs.getString("mem_nickname"));
				tmember.setTm_auth(rs.getInt("tm_auth"));
				tmember.setTm_review_status(rs.getInt("tm_review_status"));
				tmember.setTeam_status(rs.getInt("team_status"));

				list.add(tmember);
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}

	// 팀장 위임 및 팀원으로 변경하기
	public void modifyTeamLeaderAndChangeMember(int team_num, int current_leader_num, int new_leader_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false); // 트랜잭션 시작

			// 팀원으로 변경 쿼리
			sql = "UPDATE team_member SET tm_auth = 3 WHERE team_num = ? AND mem_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, team_num);
			pstmt.setInt(2, current_leader_num); // 기존 팀장 회원 번호
			pstmt.executeUpdate();
			pstmt.close();

			// 팀장 위임 쿼리
			sql = "UPDATE team_member SET tm_auth = 4 WHERE team_num = ? AND mem_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, team_num);
			pstmt.setInt(2, new_leader_num); // 새로운 팀장 회원 번호
			pstmt.executeUpdate();

			conn.commit(); // 트랜잭션 커밋
		} catch (Exception e) {
			conn.rollback(); // 에러 발생 시 롤백
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 관리자가 팀장인 회원 삭제할 때 팀장 위임할 팀원 고르기
	public int pickNextLeader(int team_num, int delete_mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int mem_num = 0;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT mem_num FROM team_member JOIN member USING(mem_num) JOIN member_detail USING(mem_num) "
					+ "JOIN team USING(team_num) WHERE team_num=? and mem_num!=? and rownum= 1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, team_num);
			pstmt.setInt(2, delete_mem_num);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				mem_num = rs.getInt(1);
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return mem_num;
	}


	// 팀 멤버 삭제하기
	public void deleteTeamMember(int mem_num, int team_num) throws Exception{
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

			sql = "SELECT tb_num FROM team_board WHERE mem_num=? AND team_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, team_num);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				tb_num = rs.getInt(1);
				sql = "DELETE FROM team_comment WHERE tb_num IN (SELECT tb_num FROM team_board WHERE tb_num = ?)";
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
			sql="DELETE FROM team_board WHERE mem_num=? AND team_num=?";
			pstmt4 = conn.prepareStatement(sql);
			pstmt4.setInt(1, mem_num);
			pstmt4.setInt(2, team_num);
			pstmt4.executeUpdate();

			// team_member에서 해당 멤버 삭제하기
			sql="DELETE FROM team_member WHERE mem_num=? AND team_num=?";
			pstmt5 = conn.prepareStatement(sql);
			pstmt5.setInt(1, mem_num);
			pstmt5.setInt(2, team_num);
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

	public void insertMateReview(MateReviewVO mr) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO mate_review (mr_num, mr_writer, mr_receiver, mr_content, team_num) VALUES(mate_review_seq.nextval, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mr.getMr_writer());
			pstmt.setInt(2, mr.getMr_receiver());
			pstmt.setString(3, mr.getMr_content());
			pstmt.setInt(4, mr.getTeam_num());

			pstmt.executeUpdate();

		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 여태 쓴 리뷰 개수 구하기 (중복 제거)
	public int HowManyReview(int mr_writer, int team_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		int count=0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(DISTINCT mr_receiver) FROM mate_review WHERE mr_writer=? AND team_num=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, mr_writer);
			pstmt.setInt(2, team_num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}

	// 프로젝트 완료 -> 레벨 관련
	public void finishProject(int team_num, int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();

			sql = "UPDATE team_member SET tm_review_status=1 WHERE team_num = ? AND mem_num = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, team_num);
			pstmt.setInt(2, mem_num);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	public int currentTeamSize(int team_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		int count=0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(mem_num) FROM team_member WHERE team_num=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, team_num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	// 레벨 바꾸기
	public void updateLevel(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			sql = "SELECT COUNT(*) FROM team_member WHERE mem_num=? AND tm_review_status=1";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, mem_num);
			
			int finish = 0;
			rs = pstmt.executeQuery();
			if(rs.next()) {
				finish = rs.getInt(1);
			}
			
			int level = 1;
			if(finish>=3) {
				level = 1 + (finish/3);
			}
			sql = "UPDATE member_detail SET mem_level=? WHERE mem_num=?";
			
			pstmt2 = conn.prepareStatement(sql);
			
			pstmt2.setInt(1, level);
			pstmt2.setInt(2, mem_num);
			
			pstmt2.executeUpdate();
			
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	
	public int whoIsLeader(int team_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		int mem_num=0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT mem_num FROM team_member WHERE team_num=? AND tm_auth=4";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, team_num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mem_num = rs.getInt(1);
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return mem_num;
	}
	
	//활성화된 팀이 있을 때 false 반환, 활성화된 팀이 없으면 true 반환
	public boolean UserTeamActive(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		boolean check=true;
		int team_status = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "select team_status FROM team JOIN team_member USING(team_num) WHERE mem_num=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, mem_num);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				team_status = rs.getInt(1);
				if(team_status==1) {
					check=false;
				}
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return check;
	}
	
	//활성화된 팀이 있을 때 리스트 반환
	public List<TeamVO> getUserActiveTeams(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		List<TeamVO> teamList = null;
		try {
			conn = DBUtil.getConnection();
			sql = "select team_num,team_status FROM team JOIN team_member USING(team_num) WHERE mem_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			
			rs = pstmt.executeQuery();
			
			teamList = new ArrayList<TeamVO>();
			while(rs.next()) {
				TeamVO team = new TeamVO();
				team.setTeam_num(rs.getInt("team_num"));
				team.setTeam_status(rs.getInt("team_status"));
				
				teamList.add(team);
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return teamList;
	}
	
	//팀 삭제
	public void deleteTeamContent(int team_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			//team comment 삭제
			sql = "SELECT tb_num FROM team_board WHERE team_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, team_num);
			rs = pstmt.executeQuery();

			int tb_num = 0;
			while(rs.next()) {
				tb_num = rs.getInt(1);
				sql = "DELETE FROM team_comment WHERE tb_num IN (SELECT tb_num FROM team_board WHERE tb_num = ?)";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, tb_num);
				pstmt2.executeUpdate();
			}
			
			//team board 삭제
			sql = "DELETE FROM team_board WHERE team_num=?";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, team_num);
			pstmt3.executeUpdate();
			
			//team todo 삭제
			sql = "DELETE FROM team_todo WHERE team_num=?";
			pstmt4 = conn.prepareStatement(sql);
			pstmt4.setInt(1, team_num);
			pstmt4.executeUpdate();
			
			//team member 삭제
			sql = "DELETE FROM team_member WHERE team_num=?";
			pstmt5 = conn.prepareStatement(sql);
			pstmt5.setInt(1, team_num);
			pstmt5.executeUpdate();

			conn.commit();
		} catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt4, null);
			DBUtil.executeClose(null, pstmt4, null);
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
}
