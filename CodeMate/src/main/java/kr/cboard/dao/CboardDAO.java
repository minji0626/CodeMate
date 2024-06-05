package kr.cboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
import kr.cboard.vo.CboardVO;
import kr.cboard.vo.CcommentVO;
import kr.cboard.vo.ClikeVO;
import kr.rboard.vo.RcommentVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
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
	public int getCboardCount(String keyfield, String keyword, int cb_type) throws Exception {
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
					sub_sql += "AND cb_title LIKE '%' || ? || '%'"; 
				} else if(keyfield.equals("2")) {
					sub_sql += "AND mem_nickname LIKE '%' || ? || '%'";
				} else if(keyfield.equals("3")) sub_sql += "AND cb_content LIKE '%' || ? || '%'";
			}

			sql = "SELECT COUNT(*) FROM c_board JOIN member_detail USING(mem_num) WHERE cb_type=? " + sub_sql;

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cb_type);
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(2, keyword);
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



	// 게시판 목록 가져오기
	public List<CboardVO> getListBoard(int start, int end, int cb_type, String keyfield, String keyword) throws Exception {
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
					sub_sql += "AND cb_title LIKE '%' || ? || '%'"; 
				} else if(keyfield.equals("2")) {
					sub_sql += "AND mem_nickname LIKE '%' || ? || '%'";
				} else if(keyfield.equals("3")) sub_sql += "AND cb_content LIKE '%' || ? || '%'";
			}

			sql =  "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM c_board LEFT OUTER JOIN member_detail USING(mem_num) WHERE cb_type=? " + sub_sql +" ORDER BY cb_num DESC) a) WHERE rnum >= ? AND rnum <= ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(++cnt, cb_type);
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
				cboard.setCb_hit(rs.getInt("cb_hit"));
				cboard.setMem_num(rs.getInt("mem_num"));
				list.add(cboard);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return list;
	}

	// 슬라이드 인기글 게시판 목록 가져오기 - 예영작성
		public List<CboardVO> getSlideListBoard() throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<CboardVO> list = null;
			String sql = null;

			try {
				conn = DBUtil.getConnection();

				sql =  "SELECT * FROM (SELECT a.* FROM "
						+ "(SELECT * FROM c_board LEFT OUTER JOIN member_detail USING(mem_num) " 
					    +" ORDER BY c_board.cb_hit DESC) a) WHERE ROWNUM <= 8 ";

				pstmt = conn.prepareStatement(sql);

				rs = pstmt.executeQuery();

				list = new ArrayList<CboardVO>();
				while (rs.next()) {
					CboardVO cboard = new CboardVO();
					cboard.setCb_num(rs.getInt("cb_num"));
					cboard.setCb_title(StringUtil.useNoHTML(rs.getString("cb_title")));
					cboard.setMem_nickname(rs.getString("mem_nickname"));
					cboard.setCb_reg_date(rs.getDate("cb_reg_date"));
					cboard.setCb_type(rs.getInt("cb_type"));
					cboard.setCb_hit(rs.getInt("cb_hit"));
					cboard.setMem_num(rs.getInt("mem_num"));
					cboard.setCb_content(rs.getString("cb_content"));
					cboard.setMem_nickname(rs.getString("mem_nickname"));
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

	
	//내가 쓴 글 목록 - 민재
	public List<CboardVO> getCboardListByMemNum(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CboardVO> list = null;
		String sql = null;
		
		conn = DBUtil.getConnection();
		sql = "SELECT * FROM c_board WHERE mem_num=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, mem_num);
		rs = pstmt.executeQuery();
		
		list = new ArrayList<CboardVO>();
		while(rs.next()) {
			CboardVO cboard = new CboardVO();
			cboard.setCb_num(rs.getInt("cb_num"));//게시글 번호
			cboard.setCb_title(rs.getString("cb_title"));
			cboard.setCb_like(rs.getInt("cb_like"));
			cboard.setCb_hit(rs.getInt("cb_hit"));
			cboard.setCb_type(rs.getInt("cb_type"));
			list.add(cboard);
		}
		try {
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.getConnection();
		}
		
		return list;
	}
	//댓글 목록 - 민재
	public List<CcommentVO> myPageApplyListByMemNum(int mem_num) throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<CcommentVO> list = new ArrayList<>();
	    String sql = "SELECT cc.*, cb.cb_title, cb.cb_type FROM c_comment cc JOIN c_board cb "
	    		+ "ON cc.cb_num = cb.cb_num WHERE cc.mem_num =?";

	    try {
	        conn = DBUtil.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, mem_num);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            CcommentVO comment = new CcommentVO();
	            comment.setCb_num(rs.getInt("cb_num"));
	            comment.setCc_num(rs.getInt("cc_num"));
	            comment.setCc_content(rs.getString("cc_content"));
	            comment.setCb_type(rs.getInt("cb_type"));
	            comment.setCb_title(rs.getString("cb_title"));
	            list.add(comment);
	        }
	    } catch (Exception e) {
	        throw new Exception(e);
	    } finally {
	        DBUtil.executeClose(rs, pstmt, conn);
	    }

	    return list;
	}
	
	
	
	//조회수 증가
	public void updateReadcount(int cb_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE c_board SET cb_hit=cb_hit+1 WHERE cb_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, cb_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	//파일 삭제
	public void deleteFile(int cb_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE c_board SET cb_file='' WHERE cb_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, cb_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	//글 수정
	public void updateBoard(CboardVO board)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();

			if(board.getCb_file()!=null 
					&& !"".equals(board.getCb_file())) {
				sub_sql += ",cb_file=?";
			}
			//SQL문 작성
			sql = "UPDATE c_board SET cb_title=?,cb_content=?,"
					+ "cb_modify_date=SYSDATE,cb_ip=?,cb_type=?" + sub_sql 
					+ " WHERE cb_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(++cnt, board.getCb_title());
			pstmt.setString(++cnt, board.getCb_content());
			pstmt.setString(++cnt, board.getCb_ip());
			pstmt.setInt(++cnt, board.getCb_type());
			if(board.getCb_file()!=null 
					&& !"".equals(board.getCb_file())) {
				pstmt.setString(++cnt, board.getCb_file());
			}
			pstmt.setInt(++cnt, board.getCb_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 댓글 등록
	public void writeCcomment(CcommentVO ccomment) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO c_comment(cc_num,mem_num,cb_num,cc_content) VALUES(c_comment_seq.nextval,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ccomment.getMem_num());
			pstmt.setInt(2, ccomment.getCb_num());
			pstmt.setString(3, StringUtil.useBrNoHTML(ccomment.getCc_content()));

			pstmt.executeUpdate();

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// 댓글 목록
		public List<CcommentVO> getCcommentList(int cb_num) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<CcommentVO> list = null;
			String sql = null;

			try {
				conn = DBUtil.getConnection();
				sql = "SELECT c.*, m.mem_nickname, m.mem_photo FROM c_comment c LEFT OUTER JOIN"
						+ " member_detail m ON(c.mem_num = m.mem_num) WHERE cb_num=? ORDER BY cc_num DESC";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cb_num);

				rs = pstmt.executeQuery();

				list = new ArrayList<CcommentVO>();
				while (rs.next()) {
					CcommentVO ccomment = new CcommentVO();
					ccomment.setCc_num(rs.getInt("cc_num"));
					ccomment.setCb_num(rs.getInt("cb_num"));
					ccomment.setMem_num(rs.getInt("mem_num"));
					ccomment.setCc_content(rs.getString("cc_content"));
					ccomment.setMem_photo(rs.getString("mem_photo"));
					ccomment.setMem_nickname(rs.getString("mem_nickname"));
					ccomment.setCc_reg_date(DurationFromNow.getTimeDiffLabel(rs.getString("cc_reg_date")));
					if(rs.getString("cc_modify_date") != null) {
						ccomment.setCc_modify_date(DurationFromNow.getTimeDiffLabel(rs.getString("cc_modify_date")));
						}
					list.add(ccomment);
				}
			} catch (Exception e) {
				throw new Exception();
			} finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}

			return list;
		}

		// 개별 댓글vo 구하기
		public CcommentVO getCcomment(int cc_num) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			CcommentVO ccomment = null;
			String sql = null;

			try {
				conn = DBUtil.getConnection();
				sql = "SELECT * FROM c_comment WHERE cc_num=?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cc_num);

				rs = pstmt.executeQuery();

				ccomment = new CcommentVO();
				if (rs.next()) {
					ccomment.setCc_num(rs.getInt("cc_num"));
					ccomment.setCc_content(rs.getString("cc_content"));
					ccomment.setMem_num(rs.getInt("mem_num"));
				}

			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}

			return ccomment;
		}
		

		// 댓글 수정
		public void modifyCcomment(CcommentVO ccomment) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;

			try {
				conn = DBUtil.getConnection();
				sql = "UPDATE c_comment SET cc_content=?,cc_modify_date=SYSDATE WHERE cc_num=?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, StringUtil.useBrNoHTML(ccomment.getCc_content()));
				pstmt.setInt(2, ccomment.getCc_num());
				pstmt.executeUpdate();
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}

		// 댓글 삭제
		public void deleteCcomment(int cc_num) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;

			try {
				conn = DBUtil.getConnection();
				sql = "DELETE FROM c_comment WHERE cc_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cc_num);

				pstmt.executeUpdate();
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		// 좋아요
		public void insertLike(ClikeVO clikeVO)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "INSERT INTO c_like (cb_num, mem_num) VALUES (?,?)";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, clikeVO.getCb_num());
				pstmt.setInt(2, clikeVO.getMem_num());
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		//좋아요 개수
		public int selectLikeCount(int cb_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int count = 0;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT COUNT(*) FROM c_like WHERE cb_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, cb_num);
				//SQL문 실행
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
		
		//회원번호와 게시물 번호를 이용한 좋아요 정보
		//(회원이 게시물을 호출했을 때 좋아요 선택 여부 표시)
		public ClikeVO selectLike(ClikeVO clikeVO)
		                                   throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ClikeVO like = null;
			String sql = null;
			try {
				//컨넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM c_like WHERE cb_num=? AND mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, clikeVO.getCb_num());
				pstmt.setInt(2, clikeVO.getMem_num());
				//SQL문 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					like = new ClikeVO();
					like.setCb_num(rs.getInt("cb_num"));
					like.setMem_num(rs.getInt("mem_num"));
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}		
			return like;
		}
		
		// 좋아요 확인
		public boolean memberLike(int cb_num, int mem_num) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			boolean check = false;
			try {
				//컨넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM c_like WHERE cb_num=? AND mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, cb_num);
				pstmt.setInt(2, mem_num);
				//SQL문 실행
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
		
		//좋아요 삭제
		public void deleteLike(ClikeVO clikeVO)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "DELETE FROM c_like WHERE cb_num=? AND mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, clikeVO.getCb_num());
				pstmt.setInt(2, clikeVO.getMem_num());
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		

		//글 삭제
		public void deleteCboard(int cb_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			PreparedStatement pstmt3 = null;
			String sql = null;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//오토커밋 해제
				conn.setAutoCommit(false);
				
				//좋아요 삭제
				sql = "DELETE FROM c_like WHERE cb_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cb_num);
				pstmt.executeUpdate();
				
				//댓글 삭제
				sql = "DELETE FROM c_comment WHERE cb_num=?";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, cb_num);
				pstmt2.executeUpdate();
				
				//부모글 삭제
				sql = "DELETE FROM c_board WHERE cb_num=?";
				pstmt3 = conn.prepareStatement(sql);
				pstmt3.setInt(1, cb_num);
				pstmt3.executeUpdate();
				
				//예외 발생 없이 정상적으로 SQL문 실행
				conn.commit();
			}catch(Exception e) {
				//예외 발생
				conn.rollback();
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt3, null);
				DBUtil.executeClose(null, pstmt2, null);
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		

		// 팀 게시판 총 글의 개수, 검색 개수
		public int getCCommentCount(String keyfield, String keyword) throws Exception {
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
						sub_sql += "WHERE cc_content LIKE '%' || ? || '%'"; 
					} else if(keyfield.equals("2")) {
						sub_sql += "WHERE mem_nickname LIKE '%' || ? || '%'";
					} else if(keyfield.equals("3")) sub_sql += "WHERE cb_type LIKE '%' || ? || '%'";
				}

				sql = "SELECT COUNT(*) FROM c_comment JOIN member_detail USING(mem_num) LEFT OUTER JOIN c_board USING(cb_num) " + sub_sql;

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
		

		// 게시판 목록 가져오기
		public List<CcommentVO> getListComment(int start, int end, String keyfield, String keyword) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<CcommentVO> list = null;
			String sql = null;
			String sub_sql = ""; 
			int cnt = 0;

			try {
				conn = DBUtil.getConnection();


				if(keyword!=null && !"".equals(keyword)) {
					if(keyfield.equals("1")) {
						sub_sql += " WHERE cc_content LIKE '%' || ? || '%'"; 
					} else if(keyfield.equals("2")) {
						sub_sql += " WHERE mem_nickname LIKE '%' || ? || '%'";
					} else if(keyfield.equals("3")) sub_sql += " WHERE cb_type LIKE '%' || ? || '%'";
				}

				sql ="SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM c_comment LEFT OUTER JOIN member_detail USING(mem_num) LEFT OUTER JOIN c_board USING(cb_num) " + sub_sql +" ORDER BY cb_num DESC) a) WHERE rnum>=? AND rnum<=?";

				pstmt = conn.prepareStatement(sql);

				// 검색어가 있을 경우에만 바인딩
				if (keyword != null && !"".equals(keyword)) {
					pstmt.setString(++cnt,  keyword);
				}
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);

				rs = pstmt.executeQuery();

				list = new ArrayList<CcommentVO>();
				while (rs.next()) {
					CcommentVO comment = new CcommentVO();
					comment.setCb_num(rs.getInt("cb_num"));
					comment.setCc_num(rs.getInt("cc_num"));
					comment.setCb_title(rs.getString("cb_title"));
					comment.setCb_type(rs.getInt("cb_type"));
					comment.setMem_nickname(rs.getString("mem_nickname"));
					comment.setCc_content(rs.getString("cc_content"));
					comment.setCc_reg_date(rs.getString("cc_reg_date"));
					comment.setMem_num(rs.getInt("mem_num"));
					list.add(comment);
				}
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}

			return list;
		}


}