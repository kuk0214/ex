package com.increpas.study.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.*;

import com.increpas.study.dao.*;
import com.increpas.study.vo.*;



@Controller	
@RequestMapping("/member")	
public class Member {
	@Autowired
	MemberDao mDao;
	
	@RequestMapping("/login.man")
	public ModelAndView getLogin(HttpSession session, ModelAndView mv, RedirectView rv) {
		if(isLogin(session)) {
			rv.setUrl("/study/main.man");
			mv.setView(rv);
		} else {
			String view = "member/login";
			mv.setViewName(view);
		}
		
		return mv;
	}
	
	@RequestMapping("/loginProc.man")
	public ModelAndView loginProc( MemberVO mVO, ModelAndView mv, 
										HttpSession session, RedirectView rv) {
		String view = "/study/main.man";
		if(isLogin(session)) {
		} else {

			int cnt = mDao.getLogin(mVO);
			if(cnt == 1) {
				session.setAttribute("SID", mVO.getId());
			} else {
				view = "/study/member/login.man";
			}
		}

		rv.setUrl(view); 
		
		mv.setView(rv);

		return mv;
	}
	
	@RequestMapping(value="/idCheck.man", params="id", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> idCheck(String id) {
		int cnt = mDao.getIdCnt(id);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", "NO");
		map.put("id", id);
		if(cnt != 1) {
			map.put("result", "OK");
		}
		return map;
	}

	

	@RequestMapping("/logout.man")
	public ModelAndView logout(HttpSession session, ModelAndView mv, RedirectView rv) {
		session.removeAttribute("SID");
		rv.setUrl("/study/main.man");
		mv.setView(rv);		
		return mv;
	}
	
	// 회원가입 폼보기 함수
	@RequestMapping("/join.man")
	public ModelAndView joinForm( ModelAndView mv, HttpSession session, RedirectView rv) {
		
		if(isLogin(session)) {
			rv.setUrl("/study/");
			mv.setView(rv);
		} 
		
		String view ="member/join";		
		mv.setViewName(view);
		return mv;
	}
	
	// 회원가입 처리 함수
	@RequestMapping("/joinProc.man")
	public ModelAndView joinProc( ModelAndView mv, HttpSession session, MemberVO mVO, RedirectView rv) {
		
		if(isLogin(session)) {
			rv.setUrl("/study/main.man");
			mv.setView(rv);
			return mv;
		} 
		
		int cnt = mDao.addMember(mVO);
		
		if(cnt == 1) {
			session.setAttribute("SID", mVO.getId());
			rv.setUrl("/study/");
		}else {
			rv.setUrl("/study/member/join.man");		
		}
		mv.setView(rv);			
		return mv;
	}
	

	
	// 내정보조회 요청 처리함수
	@RequestMapping("/userInfo.man")
	public ModelAndView myInfo(ModelAndView mv, HttpSession session, RedirectView rv) {
		if(isLogin(session)) {
			String sid = (String) session.getAttribute("SID");
			
			MemberVO mVO = mDao.getMyInfo(sid);
			mv.addObject("DATA", mVO);
			mv.setViewName("member/userInfo");
		} else {

			rv.setUrl("/study/member/login.man");
			mv.setView(rv);	
		}
		return mv;			
	}
	
	// 내 정보 수정 폼보기요청 처리함수
	@RequestMapping("/userInfoEdit.man")
	public ModelAndView memberEdit(ModelAndView mv, HttpSession session, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/study/member/login.man");
			mv.setView(rv);
			return mv;
		} 
		String sid = (String)session.getAttribute("SID");
		MemberVO mVO = mDao.getMyInfo(sid);
		
		mv.addObject("DATA", mVO);
		mv.setViewName("member/userInfoEdit");			
		return mv;
		
	}
	
	// 내 정보 수정 처리 요청 처리함수
	@RequestMapping("/myInfoEditProc.man")
	public ModelAndView memberEditProc(MemberVO mVO, ModelAndView mv, HttpSession session, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/study/member/login.man");
			mv.setView(rv);
			return mv;
		} 
		
		int cnt = mDao.updateInfo(mVO);
		String view ="/study/member/userInfo.man";
		if(cnt != 1) {
			view = "/study/member/userInfoEdit.man";
		}
		rv.setUrl(view);
		mv.setView(rv);
		return mv;
	}
	
	
	// 로그인 검사
	public boolean isLogin(HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		
		return (sid == null) ? false : true;
	}
	
}
