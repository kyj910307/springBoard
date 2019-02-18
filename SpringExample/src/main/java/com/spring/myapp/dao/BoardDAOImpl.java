package com.spring.myapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.myapp.vo.BoardReplyVO;
import com.spring.myapp.vo.BoardVO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	SqlSession sqlsession;

	// sqlsession setter
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}

	// 글등록
	@Override
	public int regContent(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlsession.insert("insertContent",paramMap);
	}
	
	// 글수정
	@Override
	public int modifyContent(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlsession.update("updateContent",paramMap);
	}
	
	@Override
	public int getContentCnt(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("selectContentCnt",paramMap);
	}
	
	// 게시글 리스트
	@Override
	public List<BoardVO> getContentList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlsession.selectList("selectContent",paramMap);
	}
	
	// 게시글 보기
	@Override
	public BoardVO getContentView(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne("selectContentView",paramMap);
	}
	// password check
	@Override
	public int getBoardCheck(Map<String, Object>paramMap){
		return sqlsession.selectOne("selectBoardCnt",paramMap);
	}
	// 게시글 삭제
	@Override
	public int delBoard(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlsession.delete("deleteBoard",paramMap);
	}
	
	// 댓글 관련
	
	// 댓글 리스트
	@Override
	public List<BoardReplyVO> getReplyList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlsession.selectList("selectBoardReplyList",paramMap);
	}	
	// 댓글등록
	@Override
	public int regReply(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlsession.insert("insertBoardReply",paramMap);
	}
	// 댓글수정
	@Override
	public boolean updateReply(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		int result = sqlsession.update("updateReply",paramMap);
		
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}
	// 댓글 비밀번호 체크
	@Override
	public boolean checkReply(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		int result = sqlsession.selectOne("selectReplyPassword",paramMap);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}
	// 댓글 삭제
	@Override
	public int delReply(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		if(paramMap.get("r_type").equals("main")){
			// 부모부터 하위까지 다지움
			System.out.println("DaoImpl : deleteBoardReplyAll");
			return sqlsession.delete("deleteBoardReplyAll",paramMap);
		}else{
			// 자기자신만 지움
			System.out.println("DaoImpl : deleteBoardReply");
			return sqlsession.delete("deleteBoardReply",paramMap);
		}
	}
}
