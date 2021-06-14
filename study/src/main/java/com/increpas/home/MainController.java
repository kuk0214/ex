package com.increpas.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.increpas.study.dao.*;
import com.increpas.study.vo.*;

@Controller
public class MainController {
	@Autowired
	GroupDao gDao;
	
	@RequestMapping("/")
	public ModelAndView main(ModelAndView mv) {
		List<GroupVO> list = gDao.getLoc();
		Gson gson = new Gson(); 
	    String str = gson.toJson(list);
		mv.addObject("STR", str);
		mv.setViewName("main");
		return mv;
	}
}
