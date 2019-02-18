package com.spring.myapp.board.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.spring.myapp.board.vo.BoardVO;

public interface BoardDAO {
	
	// 게시글 목록
	public List<BoardVO> boardList(String searchOption,String keyWord,int start, int end);
	
	// 게시글 작성
	public void insertBoard(BoardVO boardVO);
	
	// 게시글 상세
	public BoardVO boardView(int bno);
	
	// 조회수 증가
	public void increaseViewcnt(int bno, HttpSession session);
	
	// 게시글 수정 
	public void boardUpdate(BoardVO boardVO);
	
	// 게시글삭제
	public void boardDelete(int bno);
	
	// 레코드 개수
	public int countArticle(String searchOption,String keyWord);
}
