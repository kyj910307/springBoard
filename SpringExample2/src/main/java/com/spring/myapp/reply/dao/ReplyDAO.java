package com.spring.myapp.reply.dao;

import java.util.List;

import com.spring.myapp.reply.vo.ReplyVO;

public interface ReplyDAO {
	
	// 댓글등록
	public void replyInsert(ReplyVO replyVO);
	
	// 댓글 목록
	public List<ReplyVO> replyList(Integer bno,int start,int end);
	
	// 댓글갯수
	public int replyCount (int bno);
	
	// 댓글 상세
	public ReplyVO replyDetail(Integer rno);
	
	// 댓글 수정
	public void replyUpdate(ReplyVO replyVO);
	
	// 댓글 삭제
	public void replyDelete(Integer rno);
}
