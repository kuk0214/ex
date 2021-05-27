package com.increpas.study.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.increpas.study.dao.FreeBoardDao;
import com.increpas.study.util.PageUtil;
import com.increpas.study.vo.BoardVO;

@Controller
@RequestMapping("/freeboard")
public class FreeBoard {
	@Autowired
	FreeBoardDao fDao;
	
	@RequestMapping("/freeBoardList.man")
	public ModelAndView freeboardList(ModelAndView mv, PageUtil page) {
		int nowPage = page.getNowPage();
		if(nowPage == 0) {
			nowPage = 1;
		}
		int total = fDao.getTotal();
		page.setPage(nowPage, total, 10, 5);
		List list = fDao.freeBRDList(page);
		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		return mv;
	}
	
	@RequestMapping("/freeBoardDetail.man")
	public ModelAndView freeboardDetail(ModelAndView mv, PageUtil page, int frbno) {
		int nowPage = page.getNowPage();
		if(nowPage == 0) {
			nowPage = 1;
		}
		int total = fDao.getTotal();
		page.setPage(nowPage, total, 10, 5);
		List list = fDao.freeBRDReply(page);
		BoardVO bVO = fDao.freeBRDDetail(frbno);
		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		mv.addObject("DATA", bVO);
		
		return mv;
	}
	
	@RequestMapping(value="/freeBoardReplyProc.man", method=RequestMethod.POST, params="frbno")
	@ResponseBody
	public String freeBRDReplyProc(int frbno) {
		int cnt = fDao.freeBRDReplyProc(frbno);
		String result = "NO";
		if(cnt == 1) {
			result = "YES";
		}
		return result;
	}
}
