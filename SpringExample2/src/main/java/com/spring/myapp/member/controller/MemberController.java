package com.spring.myapp.member.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myapp.member.service.MemberService;
import com.spring.myapp.member.vo.MemberVO;

@Controller // 현재의 클래스를 controller bean 에 등록시킴
public class MemberController {
	// MemberService 객체를 스프링에서 생성하여 주입시킴
	@Autowired
	MemberService memberService;
	
	// 01 회원목록
	// url pattern mapping
	@RequestMapping(value="member/list.do")
	public String memberList(Model model){
		// controller => service = > dao 요청
		List<MemberVO> list = memberService.memberList();
		model.addAttribute("list",list);
		
		return "member_list";
	}
	
	// 02-01 회원등록 페이지 이동
	@RequestMapping(value="member/write.do")
	public String memberWrite(){
		return "member_write";
	}
	
	// 02-02 회원등록처리후 - 회원목록으로 페이지 리다이렉트
	// @ModelAttribute 에 폼에서 등록한 데이터가 저장된다
	@RequestMapping(value="member/insert.do")
	public String memberInsert(@ModelAttribute MemberVO memberVO){
		// 테이블에 레코드 등록
		memberService.insertMember(memberVO);
		System.out.println("input :" +memberVO);
		return "redirect:/member/list.do";
	}
	
	// 03 상세글 보기
	@RequestMapping(value="member/view.do")
	public String memberView(String userId,String userName, Model model){
		
		// 회원정보를 Model 에 저장
		model.addAttribute("dto",memberService.viewMember(userId));
		//model.addAttribute("dto",memberService.viewMember(userName));
		// userId => 클릭한 아이디 정보를 확인
		return "member_view";
	}
	
	// 04 정보 수정
	@RequestMapping(value="member/update.do")
	public String memberUpdate(@ModelAttribute MemberVO memberVO, Model model){
		
		boolean result = memberService.checkPw(memberVO.getUserId(),memberVO.getUserPw());
		if(result){
			// 비밀번호 일치시 정보 수정
			// list 페이지로 리다이렉트
			memberService.updateMmeber(memberVO);
			
			return "redirect:/member/list.do";
		}else{
			// 비밀번호 불일치시 view.jsp 로 포워드
			// 가입일자 수정일자 저장
			MemberVO vo = memberService.viewMember(memberVO.getUserId()); 
			vo.setUserRegdate(vo.getUserRegdate());
			vo.setUserUpdateddate(vo.getUserUpdateddate());
			model.addAttribute("dto",vo);
			model.addAttribute("message","비밀번호불일치");
			
			return "member_view";
		}
	}
	// 05 회원정보삭제
	@RequestMapping(value="member/delete.do")
	public String deleteMember(@RequestParam String userId, String userPw,Model model){
		
		// 비밀번호 체크
		boolean result = memberService.checkPw(userId, userPw);
		
		if(result){
			// 비밀번호가 일치하면 삭제
			// 삭제완료후 list페이지로 리다이렉트
			memberService.deleteMember(userId); // 해당 userId 삭제를 위해
			return "redirect:/member/list.do";
		}else{
			// 비밀번호 불일치시 메세지 전달
			// view 페이지 로 이동
			model.addAttribute("message","비밀번호가 일치 하시않습니다.");
			model.addAttribute("dto",memberService.viewMember(userId)); // 일치하는 userId 로 상세보기를 보여주기위해
			
			return "member_view";
		}
	}
	// 로그인 관련 
	
	// 로그인 화면 으로이동
	@RequestMapping(value="member/login.do")
	public String login(){
		return "login";
	}
	
	// 로그인 처리
	@RequestMapping(value="member/loginCheck.do")
	public ModelAndView loginCheck(@ModelAttribute MemberVO memberVO, HttpSession session){
		
		boolean result = memberService.loginCheck(memberVO,session);
		ModelAndView mav = new ModelAndView();
		
		if(result == true){
			// 로그인성공
			mav.setViewName("home");
			mav.addObject("msg","success");
		}else{
			// 로그인 실패
			mav.setViewName("login");
			mav.addObject("msg","fail");
		}
		return mav;
	}
	// 로그아웃
	@RequestMapping(value="member/logout.do")
	public ModelAndView logout(HttpSession session){
		memberService.logout(session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		mav.addObject("msg","로그아웃되었습니다.");
		
		return mav;
	}
}