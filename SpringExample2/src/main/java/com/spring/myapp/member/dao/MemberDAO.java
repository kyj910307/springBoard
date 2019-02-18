package com.spring.myapp.member.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.spring.myapp.member.vo.MemberVO;

public interface MemberDAO {
	
	public List<MemberVO> memberList();
	
	public void insertMember(MemberVO memberVO);
	
	public MemberVO viewMember(String userId);
	
	public void updateMmeber(MemberVO memberVO);
	
	public boolean checkPw(String userId, String userPw);
	
	public void deleteMember(String userId);
	
	public boolean loginCheck(MemberVO memberVO);
	
	public void logout(HttpSession session);
}
