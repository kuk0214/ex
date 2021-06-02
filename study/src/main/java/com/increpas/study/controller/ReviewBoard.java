package com.increpas.study.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.*;

import com.increpas.study.util.*;
import com.increpas.study.vo.*;
import com.increpas.study.dao.*;

/**
 * 이 클래스는 후기게시판 관련 요청 처리할 클래스
 * @author	조경국
 * @since	2021.06.01
 * @version v.1.0
 * @see
 * 			작업이력 ]
 * 					2021.06.01	-	담당자		:	조경국
 * 									작업내용	:	클래스제작
 * 					2021.06.02	-	담당자		:	조경국
 * 									작업내용	:	리뷰게시판 리스트 보기, 상세보기, 글 등록, 수정, 삭제
 *
 */

@Controller
@RequestMapping("/reviewboard")
public class ReviewBoard {
	@Autowired
	ReviewBoardDao rvDao;
	
	// 리뷰게시판 리스트 보기 요청 처리함수
	@RequestMapping("/reviewBoardList.mentor")
	public ModelAndView reviewBRDList(ModelAndView mv, PageUtil page) {
		int nowPage = page.getNowPage();
		if(nowPage == 0) {
			nowPage = 1;
		}
		int total = rvDao.totalCnt();
		page.setPage(nowPage, total, 10, 5);
		List list = rvDao.reviewBRDList(page);
		
		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		return mv;
	}
	
	// 리뷰게시판 글쓰기 폼보기 요청 처리함수
	@RequestMapping("/reviewBoardWrite.mentor")
	public ModelAndView reviewBRDWrite(ModelAndView mv, HttpSession session, RedirectView rv, int nowPage) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);
			return mv;
		}
		mv.addObject("nowPage", nowPage);
		return mv;
	}
	
	// 리뷰게시판 글 등록 요청 처리함수
	@RequestMapping("/reviewBoardWriteProc.mentor")
	public ModelAndView reviewBRDWriteProc(ModelAndView mv, HttpSession session, RedirectView rv, BoardVO bVO) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);
			return mv;
		}
		bVO.setWid(sid);
		int cnt = rvDao.reviewBRDWriteProc(bVO);
		if(cnt == 1) {
			rv.setUrl("/study/reviewboard/reviewBoardList.mentor");
		} else {
			rv.setUrl("/sutdy/reviewboard/reviewBoardWrite.mentor");
		}
		mv.setView(rv);
		return mv;
	}
	
	// 리뷰게시판 상세보기 요청 처리함수
	@RequestMapping("/reviewBoardDetail.mentor")
	public ModelAndView reviewBRDDetail(ModelAndView mv, int rvbno, int nowPage) {
		rvDao.plusClick(rvbno);
		BoardVO bVO = rvDao.reviewBRDDetail(rvbno);
		mv.addObject("DATA", bVO);
		mv.addObject("nowPage", nowPage);
		return mv;
	}
	
	// 리뷰게시판 수정 폼보기 요청 처리함수
	@RequestMapping("/reviewBoardEdit.mentor")
	public ModelAndView reviewBRDEdit(ModelAndView mv, HttpSession session, RedirectView rv, int rvbno, int nowPage) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);
			return mv;
		}
		BoardVO bVO = rvDao.reviewBRDDetail(rvbno);
		mv.addObject("DATA", bVO);
		mv.addObject("nowPage", nowPage);
		return mv;
	}
	
	// 리뷰게시판 수정 요청 처리함수
	@RequestMapping("/reviewBoardEditProc.mentor")
	public ModelAndView reviewBRDEditProc(ModelAndView mv, HttpSession session, RedirectView rv, BoardVO bVO, int nowPage) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);
			return mv;
		}
		int cnt = rvDao.reviewBRDEditProc(bVO);
		mv.addObject("RVBNO", bVO.getRvbno());
		mv.addObject("nowPage", nowPage);
		if(cnt == 1) {
			mv.addObject("PATH", "/study/reviewboard/reviewBoardDetail.mentor");
		} else {
			mv.addObject("PATH", "/study/reviewboard/reviewBoardEdit.mentor");
		}
		mv.setViewName("reviewboard/redirectView");
		return mv;
	}
	
	// 리뷰게시판 삭제 요청 처리함수
	@RequestMapping("/reviewBoardDel.mentor")
	public ModelAndView reviewBRDDel(ModelAndView mv, HttpSession session, RedirectView rv, int rvbno) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);
			return mv;
		}
		rvDao.reviewBRDDel(rvbno);
		rv.setUrl("/study/reviewboard/reviewBoardList.mentor");
		mv.setView(rv);
		return mv;
	}
}
