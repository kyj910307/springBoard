package com.spring.myapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.myapp.dao.BoardDAO;
import com.spring.myapp.vo.BoardReplyVO;
import com.spring.myapp.vo.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDao;
	// 글등록
	@Override
	public int regContent(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		if(paramMap.get("id") == null){
			// 아이디가 없으면 글등록
			return boardDao.regContent(paramMap);
		}else{
			// 아이디가 있으면 글수정
			return boardDao.modifyContent(paramMap);
		}
	}
	// 글수정
	@Override
	public int modifyContent(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.modifyContent(paramMap);
	}
	
	@Override
	public int getContentCnt(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.getContentCnt(paramMap);
	}
	// 게시글목록
	@Override
	public List<BoardVO> getContentList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.getContentList(paramMap);
	}
	// 게시글가져오기
	@Override
	public BoardVO getContentView(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.getContentView(paramMap);
	}
	// password check
	@Override
	public int getBoardCheck(Map<String, Object>paramMap){
		return boardDao.getBoardCheck(paramMap);
	}
	// 게시글삭제
	@Override
	public int delBoard(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.delBoard(paramMap);
	}
	
	// 댓글관련
	
	// 댓글 리스트
	@Override
	public List<BoardReplyVO> getReplyList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		List<BoardReplyVO> boardReplyList = boardDao.getReplyList(paramMap);
		
		// 부모 댓글
		List<BoardReplyVO> boardReplyListParent = new ArrayList<BoardReplyVO>();
		// 자식 댓글
		List<BoardReplyVO> boardReplyListChild = new ArrayList<BoardReplyVO>();
		// 위두개를 통합
		List<BoardReplyVO> newBoardReplyList = new ArrayList<BoardReplyVO>();
		
		// 최상위 댓글과 하위 댓글 분리
		for(BoardReplyVO boardReplyVO : boardReplyList){
			if(boardReplyVO.getDepth().equals("0")){
				// depth 의 깊이가 0과 같다면[원글]
				boardReplyListParent.add(boardReplyVO); // 최상위 댓글에 추가
				System.out.println("Service : boardReplyParent");
			}else{
				boardReplyListChild.add(boardReplyVO); // 하위댓글로 추가
				System.out.println("Service : boardReplyChild");
			} // if end
		} // for end
		
		for(BoardReplyVO boardReplyParent : boardReplyListParent){
			// 최상위 글은 무조건 넣는다
			newBoardReplyList.add(boardReplyParent);
			for(BoardReplyVO boardReplyChild : boardReplyListChild){
				newBoardReplyList.add(boardReplyChild);
				// 최상위의 바로 하위계층 댓글만 넣는다.
				if(boardReplyParent.getReply_id().equals(boardReplyChild.getParent_id())){
					newBoardReplyList.add(boardReplyChild);
				}
			}// child for end
		} // parent for end
		// 정리된 리스트 newBoardReplyList 로 return
		return newBoardReplyList;
	}
	
	// 댓글 등록
	@Override
	public int regReply(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.regReply(paramMap);
	}
	// 댓글 수정
	@Override
	public boolean updateReply(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.updateReply(paramMap);
	}
	// 댓글 비밀번호 비교
	@Override
	public boolean checkReply(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.checkReply(paramMap);
	}
	// 댓글 삭제
	@Override
	public int delReply(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.delReply(paramMap);
	}

}
