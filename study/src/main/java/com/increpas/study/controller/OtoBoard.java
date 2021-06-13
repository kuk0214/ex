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
import com.increpas.study.service.OtoReplyService;

@Controller
@RequestMapping("/otoboard")
public class OtoBoard {
	@Autowired
	OtoBoardDao oDao;
	@Autowired
	OtoReplyService ORSrvc;
	
	// 1대1 문의 게시판 리스트 보기 요청 처리함수
	@RequestMapping("/otoBoardList.mentor")
	public ModelAndView reviewBRDList(PageUtil page, ModelAndView mv, BoardVO bVO) {
		int nowPage = page.getNowPage();
		if(nowPage == 0) {
			nowPage = 1;
		}
		int total = oDao.totalCnt();
		page.setPage(nowPage, total, 10, 5);
		bVO.setPage(page);
		List<BoardVO> list = oDao.getOtoList(bVO);

		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		mv.addObject("option", bVO.getOption());
		return mv;
	}

	
	// 1대1 문의 게시판 상세보기 요청 처리함수
	@RequestMapping("/otoBoardDetail.mentor")
	public ModelAndView otoDetail(ModelAndView mv, int askno, int nowPage) {
		oDao.plusClick(askno);
		List<BoardVO> list = oDao.otoDetail(askno);

		mv.addObject("LIST", list);
		mv.addObject("nowPage", nowPage);
		return mv;
	}
	
	// 1대1 문의 게시판 글쓰기 폼보기 요청 처리함수
	@RequestMapping("/otoBoardWrite.mentor")
	public ModelAndView otoWrite(ModelAndView mv, HttpSession session, RedirectView rv, int nowPage) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);
			return mv;
		}
		
		mv.addObject("nowPage", nowPage);
		return mv;
	}
	
	// 1대1 문의 게시판 글 등록 요청 처리함수
	@RequestMapping("/otoBoardWriteProc.mentor")
	public ModelAndView otoWriteProc(ModelAndView mv, HttpSession session, RedirectView rv, BoardVO bVO) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);
			return mv;
		}
		bVO.setWid(sid);
		int cnt = oDao.otoWriteProc(bVO);
		if(cnt == 1) {
			rv.setUrl("/study/otoboard/otoBoardList.mentor");
		} else {
			rv.setUrl("/sutdy/otoboard/otoBoardWrite.mentor");
		}
		mv.setView(rv);
		return mv;
	}

	
	// 1대1 문의 게시판 수정 폼보기 요청 처리함수
	@RequestMapping("/otoBoardEdit.mentor")
	public ModelAndView otoEdit(ModelAndView mv, HttpSession session, RedirectView rv, int askno, int nowPage) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);
			return mv;
		}
		List<BoardVO> list = oDao.otoDetail(askno);

		mv.addObject("LIST", list);
		mv.addObject("nowPage", nowPage);
		return mv;
	}
	
	// 1대1 문의 게시판 수정 요청 처리함수
	@RequestMapping("/otoBoardEditProc.mentor")
	public ModelAndView otoEditProc(ModelAndView mv, HttpSession session, RedirectView rv, BoardVO bVO, int nowPage) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);
			return mv;
		}
		int cnt = oDao.otoEditProc(bVO);
		mv.addObject("ASKNO", bVO.getAskno());
		mv.addObject("nowPage", nowPage);
		if(cnt == 1) {
			mv.addObject("PATH", "/study/otoboard/otoBoardDetail.mentor");
		} else {
			mv.addObject("PATH", "/study/otoboard/otoBoardEdit.mentor");
		}
		mv.setViewName("otoboard/redirectView");
		return mv;
	}
	
	// 1대1 문의 게시판 삭제 요청 처리함수
	@RequestMapping("/otoBoardDel.mentor")
	public ModelAndView otoDel(ModelAndView mv, HttpSession session, RedirectView rv, int askno) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);
			return mv;
		}
		int cnt = oDao.otoDel(askno);
		rv.setUrl("/study/otoboard/otoBoardList.mentor");
		mv.setView(rv);
		return mv;
	}
	
	
	// 1대1 문의 게시판 답글 처리 요청 처리함수
	@RequestMapping("/otoBoardReply.mentor")
	public ModelAndView otoReplyProc(BoardVO bVO, ModelAndView mv, HttpSession session, RedirectView rv) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);
			return mv;
		}
		ORSrvc.addotoReplyService(bVO, mv,rv);
/*
		int cnt = oDao.otoReplyWriteProc(bVO);
		if(cnt == 1) {
			int no = oDao.otoProcess(bVO.getUpno());
			if(no != 1){
				// 답글 등록 후 상태변경 실패
				System.out.println("### 상태 변경 실패###");
			}
			System.out.println("### 상태 변경 성공###");
		} else {
			// 답글 등록 실패
		}
		
		rv.setUrl("/study/otoboard/otoBoardList.mentor");
		mv.setView(rv);
*/
		return mv;
	}
	

}
