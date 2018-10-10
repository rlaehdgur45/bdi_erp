package com.bdi.sp.dao;

import java.util.List;

import com.bdi.sp.vo.Board;

public interface BoardDAO {

	public List<Board> getBoardList(Board b);
	
	public int deleteBoard(int binum);
	
	public int updateBoard(Board b);
	
	public Board getBoard(int binum);
	
	public String insertBoard(Board bd);
	
}
