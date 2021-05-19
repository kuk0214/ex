package com.guk.ex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("/main.man")
	public ModelAndView getIndex(ModelAndView mv) {
		return mv;
	}
}
