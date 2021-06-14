package com.increpas.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.increpas.study.dao.MentorDao;

@Controller
@RequestMapping("/mentor")
public class Mentor {
	@Autowired
	MentorDao mtDao;
	
	// 멘토 등록 폼보기 요청 처리함수
	@RequestMapping("/RMentor.mentor")
	public ModelAndView RMentor(ModelAndView mv) {
		return mv;
	}
}
