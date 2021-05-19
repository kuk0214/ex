package com.guk.ex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class Member {
	
	@RequestMapping("/userInfo.man")
	public String userInfo() {
		return "member/userInfo";
	}
}
