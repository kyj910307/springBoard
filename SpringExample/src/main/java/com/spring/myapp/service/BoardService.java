package com.spring.myapp.service;

import java.util.List;
import java.util.Map;

import com.spring.myapp.vo.BoardReplyVO;
import com.spring.myapp.vo.BoardVO;

public interface BoardService {
	
	int regContent(Map<String, Object> paramMap);

	int modifyContent(Map<String, Object> paramMap);

	int getContentCnt(Map<String, Object> paramMap);

	List<BoardVO> getContentList(Map<String, Object> paramMap);

	BoardVO getContentView(Map<String, Object> paramMap);
	
	int getBoardCheck(Map<String, Object>paramMap);
	
	int delBoard(Map<String,Object>paramMap);
	
	// 댓글관련
	
	List<BoardReplyVO> getReplyList(Map<String,Object>paramMap);
	
	int regReply(Map<String, Object> paramMap);
	
	boolean updateReply(Map<String, Object> paramMap);
	
	boolean checkReply(Map<String,Object> paramMap);
	
	int delReply(Map<String, Object> paramMap);
}
