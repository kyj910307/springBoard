package com.spring.myapp.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.spring.myapp.board.vo.BoardVO;

public interface BoardService {
	
	// 게시글 목록
	public List<BoardVO> boardList(String searchOption,String keyWord,int start, int end);
	
	// 게시글 작성
	public void insertBoard(BoardVO boardVO);
	
	// 게시글 상세보기
	public BoardVO boardView(int bno);
	
	// 조회수증가
	public void increaseViewcnt(int bno, HttpSession session);
	
	// 게시글 수정
	public void boardUpdate(BoardVO boardVO);
	
	// 게시글 삭제
	public void boardDelete(int bno);
	
	// 레코드 개수
	public int countArticle(String searchOption,String keyWord);
}
