package com.increpas.study.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.study.dao.*;
import com.increpas.study.util.PageUtil;
import com.increpas.study.vo.GroupVO;

@Controller
@RequestMapping("/group")
public class Group {
	@Autowired
	GroupDao gDao;
	
	// 스터디 그룹 생성 폼보기 요청 처리함수
	@RequestMapping("/addGroup.mentor")
	public ModelAndView addGroup(ModelAndView mv) {
		return mv;
	}
	
	// 스터디 그룹 생성 요청 처리함수
	@RequestMapping("/addGroupProc.mentor")
	public ModelAndView addGroupProc(GroupVO gVO, ModelAndView mv, RedirectView rv) {
		int cnt = gDao.addGroup(gVO);
		gDao.addGroupLeader(gVO);
		if(cnt == 1) {
			rv.setUrl("/study/group/studyBoard.mentor");
		} else {
			rv.setUrl("/study/group/addGroup.mentor");
		}
		mv.setView(rv);
		return mv;
	}
	
	// 나의 스터디 그룹 리스트 보기 요청 처리함수
	@RequestMapping("/myGroupList.mentor")
	public ModelAndView MyGroupList(ModelAndView mv, HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		List list = gDao.myGroupList(sid);
		return mv;
	}
	
	// 스터디원 모집 리스트 보기 요청 처리함수
	@RequestMapping("/studyBoard.mentor")
	public ModelAndView studyBRDList(PageUtil page, ModelAndView mv) {
		int nowPage = page.getNowPage();
		if(nowPage == 0) {
			nowPage = 1;
		}
		int total = gDao.getTotal();
		
		page.setPage(nowPage, total, 3, 5);
		List list = gDao.studyBRDList(page);
		String keyword = page.getKeyword();
		String option = page.getOption();
		
		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		mv.addObject("keyword", keyword);
		mv.addObject("option", option);
		return mv;
	}
	
	// 스터디원 모집 글쓰기 폼보기 요청 처리함수
	@RequestMapping("/studyBoardWrite.mentor")
	public ModelAndView studyBRDWrite(ModelAndView mv, HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		List list = gDao.myGroupList(sid);
		mv.addObject("LIST", list);
		return mv;
	}
	
	// 스터디원 모집 글 등록 요청 처리함수
	@RequestMapping("/studyBoardWriteProc.mentor")
	public ModelAndView studyBRDWriteProc(GroupVO gVO, ModelAndView mv, RedirectView rv) {
		int sno = gVO.getSno();
		GroupVO setGVO = gDao.groupInfo(sno);
		gVO.setLoc(setGVO.getLoc());
		gVO.setNowcnt(setGVO.getNowcnt());
		gVO.setMaxcnt(setGVO.getMaxcnt());
		gVO.setId(setGVO.getId());
		int cnt = gDao.studyBRDWriteProc(gVO);
		if(cnt == 1) {
			rv.setUrl("/study/group/studyBoard.mentor");
		}
		mv.setView(rv);
		return mv;
	}
}
