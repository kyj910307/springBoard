package com.spring.myapp.reply.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myapp.reply.service.ReplyService;
import com.spring.myapp.reply.vo.ReplyVO;
import com.spring.myapp.util.BoardPager;

/*
REST: Representational State Transfer
하나의 URI가 하나의 고유한 리소스를 대표하도록 설계된 개념
Controller/RestController 차이는 메서드가 종료되면 화면전환 유무
*/
@RestController
@Controller
@RequestMapping("/reply/*")
public class ReplyController {
	@Autowired
	ReplyService replyService;
	
	// 댓글입력
	@RequestMapping("insert.do")
	public void replyInsert(@ModelAttribute ReplyVO replyVO,HttpSession session){
		String userId = (String) session.getAttribute("userId");
		replyVO.setReplyer(userId);
		replyService.replyInsert(replyVO);
	}
	
	// 댓글 목록
	@RequestMapping(value="list.do", method=RequestMethod.GET)
	public ModelAndView replyList(@RequestParam int bno, ModelAndView mav, @RequestParam (defaultValue="1") int curPage,HttpSession session){
		// 댓글 페이징 처리
		int count = replyService.replyCount(bno); // 댓글 갯수
		BoardPager replyPager = new BoardPager(count, curPage);
		int start = replyPager.getPageBegin();
		int end = replyPager.getPageEnd();
		
		List<ReplyVO> list = replyService.replyList(bno,start,end,session);
		// 뷰이름 저장
		mav.setViewName("board/replyList");
		// 데이터 저장
		mav.addObject("list",list);
		mav.addObject("replyPager",replyPager);
		return mav;
	}
	/*
	// 댓글목록 JSON방식으로 데이터 리턴
	@RequestMapping("listJson.do")
	@ResponseBody
	public List<ReplyVO> listJson(@RequestParam int bno){
		List<ReplyVO> list = replyService.replyList(bno);
		
		return list;
	}*/
	
	// 댓글상세보기 
	@RequestMapping(value="/detail/{rno}", method=RequestMethod.GET)
	public ModelAndView replyDetail(@PathVariable("rno") Integer rno, ModelAndView mav){
		ReplyVO replyVO = replyService.replyDetail(rno);
		// 뷰이름저장
		mav.setViewName("board/replyDetail");
		// 데이터저장
		mav.addObject("replyVO",replyVO);
		return mav;
	}
	
	// 댓글 수정
	// RequestMethod 를 여러 방식으로 설정할 경우 {} 안에 작성
	// put : 전체수정 ,
	// patch : 일부수정
	@RequestMapping(value="/update/{rno}",method={RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> replyUpdate(@PathVariable ("rno") Integer rno,@RequestBody ReplyVO replyVO ){
		
		ResponseEntity<String> entity = null;
		try{
			replyVO.setRno(rno);
			replyService.replyUpdate(replyVO);
			// 댓글 수정이 성공하면 성공 상태메세지 저장
			
			entity = new ResponseEntity<String>("success",HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			// 댓글 수정이 실패하면 실패 상매메세지 저장
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		// 수정처리 HTTP 상태 메세지 리턴
		return entity;
		
	}
}