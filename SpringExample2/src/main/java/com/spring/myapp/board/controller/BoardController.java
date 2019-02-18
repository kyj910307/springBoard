package com.spring.myapp.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myapp.board.service.BoardService;
import com.spring.myapp.board.vo.BoardVO;
import com.spring.myapp.util.BoardPager;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	// 01. 게시글 목록
	@RequestMapping("list.do")
	public ModelAndView boardList(@RequestParam(defaultValue="title") String searchOption,
			@RequestParam(defaultValue="")String keyWord, @RequestParam(defaultValue="1")int curPage){
		
		// 레코드의 개수
		int count = boardService.countArticle(searchOption,keyWord);
		// 페이지 나누기 관련
		BoardPager boardPager = new BoardPager(count,curPage);
		// 시작 페이지
		int start = boardPager.getPageBegin();
		// 끝페이지
		int end = boardPager.getPageEnd();
		
		List<BoardVO> list = boardService.boardList(searchOption,keyWord,start,end);
		
		// 데이터를 맵에 저장
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list); // list 라는 이름 으로 저장
		map.put("count", count); // 레코드 개수
		map.put("searchOption",searchOption); // 검색 옵션
		map.put("keyWord",keyWord); // 검색 키워드
		map.put("boardPager",boardPager);
		
		// ModelAndView - 데이터와 뷰페이지 저장
		ModelAndView mav = new ModelAndView();
		// 데이터 저장
		mav.addObject("map",map); // map 라는 이름 으로 저장
		// 뷰페이지 저장
		mav.setViewName("board/list");
		return mav;
	}
	
	// 02. 게시글 작성 화면 이동
	@RequestMapping(value="write.do", method=RequestMethod.GET)
	public String write(){
		return "board/write";
	}
	
	// 02_02. 게시글 작성 처리
	@RequestMapping(value="insert.do", method=RequestMethod.POST)
	public String insert(@ModelAttribute BoardVO boardVO,HttpSession session){
		// session 에 저장된 userId를 writer 에 저장
		String writer = (String)session.getAttribute("userId");
		boardVO.setWriter(writer);
		boardService.insertBoard(boardVO);
		
		return "redirect:list.do";
	}
	
	// 03. 게시글 상세보기
	@RequestMapping(value="view.do", method=RequestMethod.GET)
	public ModelAndView boardView(@RequestParam int bno, HttpSession session){
		// 조회수 증가처리
		boardService.increaseViewcnt(bno,session);
		// ModelAndView 객체
		ModelAndView mav = new ModelAndView();
		
		// view 페이지 저장
		mav.setViewName("board/view");
		
		// Model 데이터저장
		mav.addObject("dto",boardService.boardView(bno));
		return mav;
	}
	// 04. 게시글 수정
	@RequestMapping(value="update.do", method=RequestMethod.POST)
	// form 에서 전달된 데이터 @ModelAttribute boardVO 에 전달됨 
	public String boardUpdate(@ModelAttribute BoardVO boardVO){
		
		boardService.boardUpdate(boardVO);
		
		return "redirect:list.do";
	}
	// 05. 게시글 삭제
	@RequestMapping(value="delete.do")
	public String delete(@RequestParam int bno){
		boardService.boardDelete(bno);
		
		return "redirect:list.do";
	}
}
