package com.increpas.study.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.increpas.study.dao.MemberDao;
import com.increpas.study.vo.MemberVO;

@Controller
public class MainController {
	@Autowired
	MemberDao mDao;
	
	@RequestMapping("/main.mentor")
	public ModelAndView main( ModelAndView mv) {
		List<MemberVO> list = mDao.getLoc();
		Gson gson = new Gson(); 
	    String str = gson.toJson(list);
		mv.addObject("STR", str);			
		return mv;
	}
	
	@RequestMapping("/chat")
	public String chat() {
		
		return "view_chat";
	}
}
