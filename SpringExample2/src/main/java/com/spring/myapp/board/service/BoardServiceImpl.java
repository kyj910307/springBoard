package com.spring.myapp.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.myapp.board.dao.BoardDAO;
import com.spring.myapp.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDAO boardDAO;
	
	// 게시글 목록
	@Override
	public List<BoardVO> boardList(String searchOption,String keyWord,int start, int end) {
		return boardDAO.boardList(searchOption,keyWord,start, end);
	}
	
	// 게시글 작성
	@Override
	public void insertBoard(BoardVO boardVO) {
		
		String title = boardVO.getTitle();
		String content = boardVO.getContent();
		String writer = boardVO.getWriter();
		
		// replace 태그문자 처리
		title = title.replace("<", "&lt;");
		title = title.replace("<","&gt;");
		writer = writer.replace("<", "&lt;");
		writer = writer.replace("<","&gt;");
		
		// replace 공백처리
		title =title.replace("  ","&nbsp;&nbsp;");
		writer = writer.replace("  ", "&nbsp;&nbsp;");
		
		// replace 줄바꿈처리
		content = content.replace("/n","<br>");
		boardVO.setTitle(title);
		boardVO.setContent(content);
		boardVO.setWriter(writer);
		boardDAO.insertBoard(boardVO);
	}
	
	// 게시글 상세보기
	@Override
	public BoardVO boardView(int bno) {
		return boardDAO.boardView(bno);
	}

	@Override
	public void increaseViewcnt(int bno, HttpSession session) {
		
		long update_time = 0;
		// 세션에 저장된 조회시간 검색
		// 최초로 조회할경우 세션에 저장된값이 없기 때문에 if 문을 타지않는다
		if(session.getAttribute(("update_time_" + bno)) != null){
			// 세션에서 값 읽어오기
			//update_time = (long)session.getAttribute("update_time_"+bno);
			update_time = Long.valueOf(String.valueOf(session.getAttribute("update_time_" + bno)));
		}
		// 시스템의 현재시간을 current_time 에저장
		long current_time =  System.currentTimeMillis();
		// 일정시간이 지난후 조회수 증가 처리 24*60*60*1000(24시간)
		// 시스템 현재 시간 - 열람시간 > 일정시간 (조회수증가 가 가능하도록 지정한시간)
		if(current_time - update_time > 5*1000){
			boardDAO.increaseViewcnt(bno, session);
			// 세션에 시간을 저장 : update_time_ + bno 는 다른변수와중복되지 않게 명명
			session.setAttribute("update_time_"+bno,current_time);
		}
	}
	// 게시글 수정
	@Override
	public void boardUpdate(BoardVO boardVO) {
		boardDAO.boardUpdate(boardVO);
	}
	// 게시글 삭제
	@Override
	public void boardDelete(int bno) {
		boardDAO.boardDelete(bno);
	}

	@Override
	public int countArticle(String searchOption, String keyWord) {
		return boardDAO.countArticle(searchOption, keyWord);
	}
	
}