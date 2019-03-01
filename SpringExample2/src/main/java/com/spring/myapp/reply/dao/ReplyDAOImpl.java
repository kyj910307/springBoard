package com.spring.myapp.reply.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.myapp.reply.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	@Autowired
	SqlSession sqlSession;
	
	// 댓글 입력
	@Override
	public void replyInsert(ReplyVO replyVO){
		sqlSession.insert("reply.replyInsert",replyVO);
	}
	// 댓글목록
	@Override
	public List<ReplyVO> replyList(Integer bno,int start,int end) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bno",bno);
		map.put("start",start);
		map.put("end",end);
		
		return sqlSession.selectList("reply.replyList",map);
	}
	// 댓글 갯수
	@Override
	public int replyCount(int bno) {
		return sqlSession.selectOne("reply.replyCount",bno);
	}
	// 댓글 상세
	@Override
	public ReplyVO replyDetail(Integer rno) {
		return sqlSession.selectOne("reply.replyDetail",rno);
	}
	// 댓글 수정
	@Override
	public void replyUpdate(ReplyVO replyVO) {
		 sqlSession.update("reply.replyUpdate",replyVO);
	}
	// 댓글 삭제
	@Override
	public void replyDelete(Integer rno) {
		sqlSession.delete("reply.replyDelete",rno);
	}		
}