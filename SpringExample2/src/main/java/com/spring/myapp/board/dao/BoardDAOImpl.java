package com.spring.myapp.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.myapp.board.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	// 글목록
	@Override
	public List<BoardVO> boardList(String searchOption,String keyWord,int start, int end) {
		// 검색옵션,키워드 맵에 저장
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("searchOption",searchOption);
		map.put("keyWord", keyWord);
		// BETWEEN #{start} AND #{end} 의 입력될값을 map 에 저장
		map.put("start",start);
		map.put("end",end);
		return sqlSession.selectList("board.boardList",map);
	}
	
	// 글작성
	@Override
	public void insertBoard(BoardVO boardVO) {
		sqlSession.insert("board.boardInsert",boardVO);
	}
	// 글상세보기
	@Override
	public BoardVO boardView(int bno) {
		return sqlSession.selectOne("board.boardView",bno);
	}
	// 조회수증가
	@Override
	public void increaseViewcnt(int bno, HttpSession session) {
		sqlSession.update("board.increaseViewcnt",bno);
	}
	// 게시글 수정
	@Override
	public void boardUpdate(BoardVO boardVO) {
		sqlSession.update("board.boardUpdate",boardVO);
		
	}
	// 게시글 삭제
	@Override
	public void boardDelete(int bno) {
		sqlSession.delete("board.boardDelete",bno);
	}
	// 레코드 개수
	@Override
	public int countArticle(String searchOption, String keyWord) {
		// 검색옵션,키워드 맵에 저장
		Map<String,String> map = new HashMap<String,String>();
		map.put("searchOption",searchOption);
		map.put("keyWord", keyWord);
		return sqlSession.selectOne("board.countArticle",map);
	}
}
