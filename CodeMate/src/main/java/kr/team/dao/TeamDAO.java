package kr.team.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.rboard.vo.RboardVO;
import kr.team.vo.TeamVO;
import kr.tmember.vo.TmemberVO;
import kr.util.DBUtil;

public class TeamDAO {
	private static TeamDAO instance = new TeamDAO();

	public static TeamDAO getInstance() {
		return instance;
	}

	private TeamDAO() {
	}
	
	
    

}
