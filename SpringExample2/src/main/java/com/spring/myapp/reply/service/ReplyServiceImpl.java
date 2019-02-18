package com.spring.myapp.reply.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.myapp.reply.dao.ReplyDAO;
import com.spring.myapp.reply.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	ReplyDAO replyDAO;
	
	// 댓글작성
	@Override
	public void replyInsert(ReplyVO replyVO){
		replyDAO.replyInsert(replyVO);
	}
	// 댓글목록
	@Override
	public List<ReplyVO> replyList(Integer bno,int start,int end,HttpSession session) {
		List<ReplyVO> items = replyDAO.replyList(bno,start,end);
		// 세션에서 현재 사용자 id 값저장
		String userId = (String)session.getAttribute("userId");
		for(ReplyVO vo : items){
			if(vo.getSecretReply().equals("y")){
				// 댓글 목록중에 비밀댓글이 있을경우
				if(userId == null){
					// 비로그인 상태이면 비밀댓글로 처리
					vo.setReplytext("비밀댓글입니다!!!");
				}else{// 로그인 상태일 경우
					String writer = vo.getWriter(); // 게시물작성자 저장
					String replyer = vo.getReplyer(); //댓글작성자 저장
					// 로그인한 사용자 가 게시물의 작성자가 아닐경우 또는 댓글 작성자도 아닐경우 비밀댓글로 처리
					if(!userId.equals(writer) && !userId.equals(replyer)){
						vo.setReplytext("비밀댓글입니다!!!");
					}
				}
			}
		}// for end
		return items;
	}
	// 댓글 갯수
	@Override
	public int replyCount(int bno) {
		return replyDAO.replyCount(bno);
	}
	// 댓글상세보기
	@Override
	public ReplyVO replyDetail(Integer rno) {
		return replyDAO.replyDetail(rno);
	}
	// 댓글수정
	@Override
	public void replyUpdate(ReplyVO replyVO) {
		replyDAO.replyUpdate(replyVO);
	}
}
