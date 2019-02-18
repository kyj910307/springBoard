package com.spring.myapp.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.myapp.member.dao.MemberDAO;
import com.spring.myapp.member.vo.MemberVO;

@Service // 현재 클래스를 Service bean 으도 등록
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO memberDAO;
	
	// 회원목록
	@Override
	public List<MemberVO> memberList() {
		return memberDAO.memberList();
	}
	
	// 회원등록
	@Override
	public void insertMember(MemberVO memberVO) {
		memberDAO.insertMember(memberVO);
	}
	
	// 회원상세정보
	@Override
	public MemberVO viewMember(String userId) {
		return memberDAO.viewMember(userId);
	}
	
	// 정보수정
	@Override
	public void updateMmeber(MemberVO memberVO) {
		memberDAO.updateMmeber(memberVO);
		
	}
	
	// 정보 수정,삭제를 위한 비밀번호 체크
	@Override
	public boolean checkPw(String userId, String userPw) {
		return memberDAO.checkPw(userId, userPw);
	}
	
	// 회원정보삭제
	@Override
	public void deleteMember(String userId) {
		memberDAO.deleteMember(userId);
		
	}
	// 로그인체크
	@Override
	public boolean loginCheck(MemberVO memberVO, HttpSession session) {
		boolean result = memberDAO.loginCheck(memberVO);
		
		if(result){
			// true 경우 세션에 등록
			// 세션에 변수등록
			session.setAttribute("userId", memberVO.getUserId());
		}
		
		return result;
	}
	
	// 로그아웃
	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}
}
