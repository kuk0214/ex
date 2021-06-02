package com.increpas.study.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.*;

import com.increpas.study.dao.*;
import com.increpas.study.util.*;
import com.increpas.study.vo.*;

/**
 * 이 클래스는 공지사항 관련 요청 처리할 클래스
 * @author	조경국
 * @since	2021.06.01
 * @version v.1.0
 * @see
 * 			작업이력 ]
 * 					2021.06.01	-	담당자		:	조경국
 * 									작업내용	:	클래스제작
 * 					2021.06.02	-	담당자		:	조경국
 * 									작업내용	:	공지사항 리스트 보기, 상세보기, 글쓰기, 수정, 삭제
 *
 */

@Controller
@RequestMapping("/notice")
public class Notice {
	@Autowired
	NoticeDao nDao;
	
	// 공시사항 리스트 보기 요청 처리함수
	@RequestMapping("/noticeList.mentor")
	public ModelAndView noticeList(ModelAndView mv, PageUtil page) {
		int nowPage = page.getNowPage();
		if(nowPage == 0) {
			nowPage = 1;
		}
		int total = nDao.totalCnt();
		
		page.setPage(nowPage, total, 3, 5);
		List list = nDao.noticeList(page);
		
		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		return mv;
	}
	
	// 공지사항 상세보기 요청 처리함수
	@RequestMapping("/noticeDetail.mentor")
	public ModelAndView noticeDetail(ModelAndView mv, int no, int nowPage) {
		nDao.plusClick(no);
		BoardVO bVO = nDao.noiceDetail(no);
		mv.addObject("DATA", bVO);
		mv.addObject("nowPage", nowPage);
		return mv;
	}
	
	// 공지사항 글쓰기 폼보기 요청 처리함수
	@RequestMapping("/noticeWrite.mentor")
	public ModelAndView noticeWrite(ModelAndView mv, RedirectView rv, HttpSession session, int nowPage) {
		String sid = (String) session.getAttribute("SID");
		if(!(sid.equals("admin1"))) {
			rv.setUrl("/study/notice/noticeList.mentor");
			mv.setView(rv);
			return mv;
		}
		mv.addObject("nowPage", nowPage);
		return mv;
	}
	
	// 공지사항 글 등록 요청 처리함수
	@RequestMapping("/noticeWriteProc.mentor")
	public ModelAndView noticeWriteProc(ModelAndView mv, BoardVO bVO, RedirectView rv, HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		rv.setUrl("/study/notice/noticeList.mentor");
		if(!(sid.equals("admin1"))) {
			mv.setView(rv);
			return mv;
		}
		bVO.setWid(sid);
		int cnt = nDao.noticeWriteProc(bVO);
		if(cnt != 1) {
			rv.setUrl("/study/notice/noticeWrite.mentor");
		}
		mv.setView(rv);
		return mv;
	}
	
	// 공지사항 수정 폼보기 요청 처리함수
	@RequestMapping("/noticeEdit.mentor")
	public ModelAndView noticeEdit(ModelAndView mv, RedirectView rv, HttpSession session, BoardVO bVO, int nowPage) {
		String sid = (String) session.getAttribute("SID");
		if(!(sid.equals("admin1"))) {
			rv.setUrl("/study/notice/noticeList.mentor");
			mv.setView(rv);
			return mv;
		}
		bVO = nDao.noiceDetail(bVO.getNo());
		mv.addObject("DATA", bVO);
		mv.addObject("nowPage", nowPage);
		return mv;
	}
	
	// 공지사항 수정 요청 처리함수
	@RequestMapping("/noticeEditProc.mentor")
	public ModelAndView noticeEditProc(ModelAndView mv, RedirectView rv, HttpSession session, BoardVO bVO, int nowPage) {
		String sid = (String) session.getAttribute("SID");
		if(!(sid.equals("admin1"))) {
			rv.setUrl("/study/notice/noticeList.mentor");
			mv.setView(rv);
			return mv;
		}
		int cnt = nDao.noticeEditProc(bVO);
		mv.addObject("NO", bVO.getNo());
		mv.addObject("nowPage", nowPage);
		if(cnt == 1) {
			mv.addObject("PATH", "/study/notice/noticeDetail.mentor");
		} else {
			mv.addObject("PATH", "/study/notice/noticeEdit.mentor");
		}
		mv.setViewName("notice/redirectView");
		return mv;
	}
	
	// 공지사항 글 삭제 요청 처리함수
	@RequestMapping("/noticeDel.mentor")
	public ModelAndView noticeDel(ModelAndView mv, RedirectView rv, HttpSession session, int no) {
		String sid = (String) session.getAttribute("SID");
		rv.setUrl("/study/notice/noticeList.mentor");
		if(!(sid.equals("admin1"))) {
			mv.setView(rv);
			return mv;
		}
		int cnt = nDao.noticeDel(no);
		mv.setView(rv);
		return mv;
	}
}
