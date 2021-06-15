package com.increpas.study.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.RedirectView;

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
 * 					2021.06.01	-	담당자		:	조경국
 * 									작업내용	:	클래스제작
 * 												 	자유게시판 리스트, 상세보기, 댓글 등록
 * 					2021.06.14	-	담당자		:	조경국
 * 									작업내용	:	자유게시판 댓글 삭제, 수정, 대댓글 등록, 글 등록, 삭제, 수정
 * 												  
 */

@Controller
@RequestMapping("/freeboard")
public class FreeBoard {
	@Autowired
	FreeBoardDao fDao;
	
	// 자유게시판 리스트 보기 요청 처리함수
	@RequestMapping("/freeBoardList.mentor")
	public ModelAndView freeBRDList(ModelAndView mv, PageUtil page) {
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
	
	// 자유게시판 상세보기 요청 처리함수
	@RequestMapping("/freeBoardDetail.mentor")
	public ModelAndView freeBRDDetail(ModelAndView mv, PageUtil page, int frbno) {
		fDao.plusclick(frbno);
		int nowPage = page.getNowPage();
		if(nowPage == 0) {
			nowPage = 1;
		}
		int total = fDao.getTotal();
		page.setPage(nowPage, total, 10, 5);
		List list = fDao.freeBRDReply(page);
		BoardVO bVO = fDao.freeBRDDetail(frbno);
		int cnt = fDao.replyCnt(frbno);
		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		mv.addObject("DATA", bVO);
		mv.addObject("CNT", cnt);
		
		return mv;
	}
	
	// 자유게시판 댓글 등록 요청 처리함수
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
	
	// 자유게시판 대댓글 등록 요청 처리함수
	@RequestMapping(value="/freeBoardReplyProc2.mentor", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> freeBRDReplyProc2(BoardVO bVO) {
		int cnt = fDao.freeBRDReplyProc2(bVO);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", "NO");
		
		if(cnt == 1) {
			map.put("result", "OK");
		}
		
		return map;
	}
	
	// 자유게시판 글 작성 폼보기 요청 처리함수
	@RequestMapping("/freeBoardWrite.mentor")
	public ModelAndView freeBRDWrite(int nowPage, ModelAndView mv) {
		mv.addObject("nowPage", nowPage);
		return mv;
	}
	
	// 자유게시판 글 등록 요청 처리함수
	@RequestMapping("/freeBoardWriteProc.mentor")
	public ModelAndView freeBRDWriteProc(BoardVO bVO, ModelAndView mv, HttpSession session, RedirectView rv) {
		String sid = (String) session.getAttribute("SID");
		bVO.setWid(sid);
		int cnt = fDao.freeBRDWriteProc(bVO);
		if(cnt == 1) {
			rv.setUrl("/study/freeboard/freeBoardList.mentor");
		} else {
			rv.setUrl("/study/freeboard/freeBoardWrite.mentor");
		}
		mv.setView(rv);
		return mv;
	}
	
	// 자유게시판 글 삭제 요청 처리함수
	@RequestMapping("/freeBoardDel.mentor")
	public ModelAndView freeBRDDel(int frbno, ModelAndView mv, RedirectView rv) {
		fDao.freeBRDDel(frbno);
		fDao.delBRDReply(frbno);
		rv.setUrl("/study/freeboard/freeBoardList.mentor");
		mv.setView(rv);
		return mv;
	}
	
	// 자유게시판 글 수정 폼보기 요청 처리함수
	@RequestMapping("/freeBoardEdit.mentor")
	public ModelAndView freeBRDEdit(int frbno, ModelAndView mv) {
		BoardVO bVO = fDao.freeBRDDetail(frbno);
		mv.addObject("DATA", bVO);
		return mv;
	}
	
	// 자유게시판 글 수정 요청 처리함수
	@RequestMapping("/freeBoardEditProc.mentor")
	public ModelAndView freeBRDEditProc(BoardVO bVO, ModelAndView mv, RedirectView rv) {
		int cnt = fDao.freeBRDEditProc(bVO);
		mv.addObject("FRBNO", bVO.getFrbno());
		if(cnt == 1) {
			mv.addObject("PATH", "/study/freeboard/freeBoardDetail.mentor");
		} else {
			mv.addObject("PATH", "/study/freeboard/freeBoardEdit.mentor");
		}
		mv.setViewName("freeboard/redirectView");
		return mv;
	}
	
	// 자유게시판 댓글 수정 요청 처리함수
	@RequestMapping(value="/freeBoardReplyEditProc.mentor", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> freeBRDReplyEditProc(BoardVO bVO) {
		int cnt = fDao.freeBRDReplyEditProc(bVO);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", "NO");
		
		if(cnt == 1) {
			map.put("result", "OK");
		}
		
		return map;
	}
	
	// 자유게시판 댓글 삭제 요청 처리함수
	@RequestMapping(value="/freeBoardReplyDelProc.mentor", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> freeBRDReplyDelProc(BoardVO bVO) {
		int cnt = fDao.freeBRDReplyDelProc(bVO);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", "NO");
		
		if(cnt == 1) {
			map.put("result", "OK");
		}
		
		return map;
	}
}
