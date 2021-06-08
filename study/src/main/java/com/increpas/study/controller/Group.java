package com.increpas.study.controller;

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

/**
 * 이 클래스는 스터디 그룹 관련 요청 처리할 클래스
 * @author	조경국
 * @since	2021.06.08
 * @version v.1.0
 * @see
 * 			작업이력 ]
 * 					2021.06.08	-	담당자		:	조경국
 * 									작업내용	:	클래스제작, 스터디 그룹 관련(생성, 가입 등), 스터디원 모집관련(모집글 리스트, 등록 등) 
 *
 */

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
		gDao.addGroupMember(gVO);
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
	
	// 스터디원 모집 글 상세보기 요청 처리함수
	@RequestMapping("/studyBoardDetail.mentor")
	public ModelAndView studyBRDDetail(GroupVO gVO, ModelAndView mv, HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		gVO = gDao.studyBRDDetail(gVO);
		gVO.setSid(sid);
		int cnt = gDao.rqJoinCheck(gVO);
		mv.addObject("DATA", gVO);
		mv.addObject("CNT", cnt);
		return mv;
	}
	
	// 스터디 가입 요청 처리함수
	@RequestMapping("/groupRequestJoin.mentor")
	public ModelAndView requestJoin(GroupVO gVO, ModelAndView mv, HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		int cnt = gDao.requestJoin(gVO);
		int cnt2 = gDao.rqJoinCheck(gVO);
		gVO = gDao.studyBRDDetail(gVO);
		gVO.setSid(sid);
		mv.addObject("DATA", gVO);
		mv.addObject("CNT", cnt2);
		mv.setViewName("group/studyBoardDetail");
		return mv;
	}
	
	// 내 스터디 관리 보기 요청 처리함수
	@RequestMapping("/myGroup.mentor")
	public ModelAndView myGroup(ModelAndView mv, HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		List list = gDao.myGroupList(sid);
		mv.addObject("LIST", list);
		return mv;
	}
	
	// 가입 요청 리스트 보기 처리함수
	@RequestMapping("/requestJoinList.mentor")
	public ModelAndView requestJoinList(int sno, ModelAndView mv) {
		List list = gDao.requestJoinList(sno);
		mv.addObject("LIST", list);
		return mv;
	}
	
	// 가입 요청 수락 처리함수
	@RequestMapping("/requestAccept.mentor")
	public ModelAndView requestAccept(GroupVO gVO, ModelAndView mv) {
		int sno = gVO.getSno();
		gDao.requestResponse(gVO);
		gDao.addGroupMember(gVO);
		gDao.addNowCnt(sno);
		List list = gDao.requestJoinList(sno);
		mv.addObject("LIST", list);
		mv.setViewName("group/requestJoinList");
		return mv;
	}
	
	// 가입 요청 거절 처리함수
	@RequestMapping("/requestDeny.mentor")
	public ModelAndView requestDeny(GroupVO gVO, ModelAndView mv) {
		gDao.requestResponse(gVO);
		List list = gDao.requestJoinList(gVO.getSno());
		mv.addObject("LIST", list);
		mv.setViewName("group/requestJoinList");
		return mv;
	}
}
