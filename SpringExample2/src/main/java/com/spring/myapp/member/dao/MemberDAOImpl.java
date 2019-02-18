package com.spring.myapp.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.myapp.member.vo.MemberVO;

@Repository // 현재클래스를 dao bean 으로 등록
public class MemberDAOImpl implements MemberDAO {
	
	// SqlSession 객체를 스프링에서 생성하여 주입시켜준다.
	// 의존관계주입 (Dependency Injection , DI)
	// 느슨한 결합도
	// Ioc (제어의 역전)
	@Autowired
	SqlSession sqlSession;
	
	// 회원목록
	@Override
	public List<MemberVO> memberList() {
		return sqlSession.selectList("member.memberList");
	}
	// 회원등록
	@Override
	public void insertMember(MemberVO memberVO) {
		sqlSession.insert("member.insertMember",memberVO);
	}
	// 회원상세정보
	@Override
	public MemberVO viewMember(String userId) {
		return sqlSession.selectOne("member.viewMember",userId);
	}
	// 정보 수정
	@Override
	public void updateMmeber(MemberVO memberVO) {
		sqlSession.update("member.updateMmeber",memberVO);
		
	}
	// 회원 수정,삭제를 위한 비밀번호 체크
	@Override
	public boolean checkPw(String userId, String userPw) {
		boolean result = false;
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", userId);
		map.put("userPw", userPw);
		int count = sqlSession.selectOne("member.checkPw",map);
		
		if(count == 1){
			result = true;
		}
		return result;
	}
	// 회원정보 삭제
	@Override
	public void deleteMember(String userId) {
		sqlSession.delete("member.deleteMember",userId);
		
	}
	// 로그인 체크
	@Override
	public boolean loginCheck(MemberVO memberVO) {
		
		String name = sqlSession.selectOne("member.loginCheck",memberVO);
		
		return (name == null)? false:true;
	}
	// 로그아웃
	@Override
	public void logout(HttpSession session) {
	}
}
