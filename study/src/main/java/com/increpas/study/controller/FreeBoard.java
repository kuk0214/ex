package com.increpas.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/freeboard")
public class FreeBoard {
	
	@RequestMapping("/freeBoardList.man")
	public ModelAndView freeboardList(ModelAndView mv) {
		return mv;
	}
}
