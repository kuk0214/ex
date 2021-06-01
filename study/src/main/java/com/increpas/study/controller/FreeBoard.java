package com.increpas.study.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.increpas.study.dao.*;
import com.increpas.study.util.*;
import com.increpas.study.vo.*;

/**
 * 이 클래스는 자유게시판 관련 요청을 처리할 클래스
 * @author	조경국
 * @since	2021.06.01
 * @version v.1.0
 * @see
 * 			작업이력 ]
 * 					2021.06.01	-	담당자		: 조경국
 * 									작업내용	: 클래스제작
 * 												  자유게시판 리스트, 상세보기, 자유게시판 댓글 등록 처리
 * 												  
 *
 */

@Controller
@RequestMapping("/freeboard")
public class FreeBoard {
	@Autowired
	FreeBoardDao fDao;
	
	@RequestMapping("/freeBoardList.mentor")
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
	
	@RequestMapping("/freeBoardDetail.mentor")
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
	
	@RequestMapping(value="/freeBoardReplyProc.mentor", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> freeBRDReplyProc(BoardVO bVO) {
		int cnt = fDao.freeBRDReplyProc(bVO);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", "NO");
		
		if(cnt == 1) {
			map.put("result", "OK");
		}
		
		return map;
	}
}
