package com.spring.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) {
		// model : 데이터를 담는 그릇 , map 구조로 저장된다
		// model.addAttribute("변수명",value);
		model.addAttribute("msg","홈페이지방문을 환영합니다");
		
		return "main"; // main.jsp 로 포워딩
	}
	
	// url mapping
	// 기본 루트페이지로 => home 메서드 호출
	@RequestMapping(value="home.do",method=RequestMethod.GET)
	public String home(Locale locale, Model model){
		logger.info("Welcome home! The client locale is{}.",locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.LONG,locale);
		
		String formattedDate = dateFormat.format(date);
		
		// 모델 (서블릿의 request 객체를 대체한것)
		model.addAttribute("serverTime",formattedDate);
		
		return "home";
	}
}
