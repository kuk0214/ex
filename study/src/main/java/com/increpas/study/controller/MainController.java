package com.increpas.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.increpas.study.dao.MemberDao;
import com.increpas.study.vo.MemberVO;

@Controller
public class MainController {
	@Autowired
	MemberDao mDao;
	
	@RequestMapping("/main.mentor")
	public ModelAndView main( ModelAndView mv) {
		List<MemberVO> list = mDao.getLoc();
		
		mv.addObject("LIST", list);			
		return mv;
	}
}
