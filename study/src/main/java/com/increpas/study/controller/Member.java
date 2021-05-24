package com.increpas.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member")
public class Member {
	
	@RequestMapping("/userInfo.man")
	public ModelAndView userInfo(ModelAndView mv) {
		return mv;
	}
}
