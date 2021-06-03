package com.increpas.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/group")
public class Group {
	
	// 스터디 그룹 생성 폼보기 요청 처리함수
	@RequestMapping("/addGroup.mentor")
	public ModelAndView addGroup(ModelAndView mv) {
		return mv;
	}
}
