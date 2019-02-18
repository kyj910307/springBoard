package com.spring.myapp.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.spring.myapp.member.vo.MemberVO;

public interface MemberService {
	
	// 회원목록
	public List<MemberVO> memberList();
	
	// 회원등록
	public void insertMember(MemberVO memberVO);
	
	// 회원 상세
	public MemberVO viewMember(String userId);
	
	// 정보수정
	public void updateMmeber(MemberVO memberVO);
	
	// 회정정보 수정,삭제를 위한 비밀번호 체크
	public boolean checkPw(String userId, String userPw);
	
	// 회원정보 삭제
	public void deleteMember(String userId);
	
	// 로그인 체크
	public boolean loginCheck(MemberVO memberVO, HttpSession session);
	
	// 로그아웃
	public void logout(HttpSession session);
}
