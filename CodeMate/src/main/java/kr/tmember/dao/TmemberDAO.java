package kr.tmember.dao;

import kr.tmember.vo.TmemberVO;

public class TmemberDAO {
	private static TmemberDAO instance = new TmemberDAO();

	public static TmemberDAO getInstance() {
		return instance;
	}

	private TmemberDAO() { }
	
	// 팀 멤버 불러오기
	public TmemberVO getMember(int mem_num) throws Exception {
		TmemberVO tmember = null;
		
		return tmember;
	}
	
	// 팀 목록 가져오기
	  
}
