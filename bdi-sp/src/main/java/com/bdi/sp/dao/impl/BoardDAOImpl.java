package com.bdi.sp.dao.impl;

import java.util.List;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bdi.sp.dao.BoardDAO;
import com.bdi.sp.vo.Board;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession ss;
	
	@Override
	public List<Board> getBoardList(Board b) {
		return ss.selectList("SQL.Board.selectBoardList",b);
	}

	@Override
	public int deleteBoard(int binum) {
		return ss.delete("SQL.Board.DeleteBoard", binum);
	}

	@Override
	public int updateBoard(Board b) {
		return ss.update("SQL.Board.UpdateBoard", b);
	}

	@Override
	public Board getBoard(int binum) {
		return ss.selectOne("SQL.Board.selectBoard", binum);
	}

	@Override
	public String insertBoard(Board bd) {
		return ss.insert("SQL.Board.insertBoard", bd)+"";
	}

}
