package com.spring.myapp.reply.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.spring.myapp.reply.vo.ReplyVO;

public interface ReplyService {
	
	// 댓글입력
	public void replyInsert(ReplyVO replyVO);
	
	// 댓글목록
	// 로그인 여부를 확인하기 위해 session 추가
	public List<ReplyVO> replyList(Integer bno,int start,int end, HttpSession session);
	
	// 댓글 갯수
	public int replyCount(int bno);
	
	// 댓글 상세
	public ReplyVO replyDetail(Integer rno);
	
	// 댓글수정
	public void replyUpdate (ReplyVO replyVO);
	
	// 댓글 삭제
	public void replyDelete(Integer rno);
}
