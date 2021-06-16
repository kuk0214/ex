package com.increpas.study.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.study.dao.*;
import com.increpas.study.util.PageUtil;
import com.increpas.study.vo.*;

@Controller
@RequestMapping("/mentor")
public class Mentor {
	@Autowired
	MentorDao mtDao;
	@Autowired
	GroupDao gDao;
	
	// 멘토 리스트 보기 요청 처리함수
	@RequestMapping("/mentorList.mentor")
	public ModelAndView mentorList(PageUtil page, ModelAndView mv, HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		int cnt = mtDao.mentorCheck(sid);
		int cnt2 = mtDao.leaderCheck(sid);
		int nowPage = page.getNowPage();
		if(nowPage == 0) {
			nowPage = 1;
		}
		int total = mtDao.getTotal();
		page.setPage(nowPage, total, 3, 5);
		List list = mtDao.mentorList(page);
		List list2 = mtDao.mentorGroupList(sid);
		List list3 = mtDao.requestMentorList(sid);
		String keyword = page.getKeyword();
		String option = page.getOption();
		mv.addObject("LIST", list);
		mv.addObject("LIST2", list2);
		mv.addObject("LIST3", list3);
		mv.addObject("PAGE", page);
		mv.addObject("keyword", keyword);
		mv.addObject("option", option);
		mv.addObject("CNT", cnt);
		mv.addObject("CNT2", cnt2);
		return mv;
	}
	
	// 멘토 소개 등록 폼보기 요청 처리함수
	@RequestMapping("/mentorWrite.mentor")
	public ModelAndView mentorWrite(ModelAndView mv) {
		return mv;
	}
	
	// 멘토 소개 등록 요청 처리함수
	@RequestMapping("/mentorWriteProc.mentor")
	public ModelAndView mentorWriteProc(MentorVO mtVO, ModelAndView mv, HttpSession session, RedirectView rv) {
		String mtid = (String) session.getAttribute("SID");
		mtVO.setMtid(mtid);
		int cnt = mtDao.mentorWriteProc(mtVO);
		if(cnt == 1) {
			rv.setUrl("/study/mentor/mentorList.mentor");
		} else {
			rv.setUrl("/study/mentor/mentorWrite.mentor");
		}
		mv.setView(rv);
		return mv;
	}
	
	// 멘토 자기소개 보기 요청 처리함수
	@RequestMapping(value="/mentorPr.mentor", method=RequestMethod.POST)
	@ResponseBody
	public Object mentorPr(int no, HttpSession session) {
		MentorVO mtVO = mtDao.mentorPr(no);
		String sid = (String) session.getAttribute("SID");
		mtVO.setSid(sid);
		int cnt = mtDao.requestCheck(mtVO);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", mtVO.getName());
        map.put("pr", mtVO.getPr());
        map.put("mtid", mtVO.getMtid());
        map.put("cnt", cnt);
        return map;
	}
	
	// 멘토 삭제 요청 처리함수
	@RequestMapping("/mentorDel.mentor")
	public ModelAndView mentorDel(int no, ModelAndView mv, RedirectView rv) {
		mtDao.mentorDel(no);
		rv.setUrl("/study/mentor/mentorList.mentor");
		mv.setView(rv);
		return mv;
	}
	
	// 멘토 수정 폼보기 요청 처리함수
	@RequestMapping("/mentorEdit.mentor")
	public ModelAndView mentorEdit(int no, int nowPage, ModelAndView mv) {
		MentorVO mtVO = mtDao.mentorEdit(no);
		mv.addObject("DATA", mtVO);
		mv.addObject("nowPage", nowPage);
		return mv;
	}
	
	// 멘토 수정 요청 처리함수
	@RequestMapping("/mentorEditProc.mentor")
	public ModelAndView mentorEditProc(MentorVO mtVO, int nowPage, ModelAndView mv) {
		int cnt = mtDao.mentorEditProc(mtVO);
		mv.addObject("nowPage", nowPage);
		if(cnt == 1) {
			mv.addObject("PATH", "/study/mentor/mentorList.mentor");
		} else {
			mv.addObject("PATH", "/study/mentor/mentorEdit.mentor");
			mv.addObject("NO", mtVO.getNo());
		}
		mv.setViewName("mentor/redirectView");
		return mv;
	}
	
	// 멘토 가입 요청 처리함수
	@RequestMapping("/requestMentor.mentor")
	public ModelAndView requestMentor(MentorVO mtVO, int nowPage, ModelAndView mv) {
		mtDao.requsetMentor(mtVO);
		mv.addObject("PATH", "/study/mentor/mentorList.mentor");
		mv.addObject("nowPage", nowPage);
		mv.setViewName("mentor/redirectView");
		return mv;
	}
	
	// 멘토 가입 취소 요청 처리함수
	@RequestMapping("/requestCancle.mentor")
	public ModelAndView requestCancle(MentorVO mtVO, int nowPage, ModelAndView mv) {
		mtDao.requestCancle(mtVO);
		mv.addObject("PATH", "/study/mentor/mentorList.mentor");
		mv.addObject("nowPage", nowPage);
		mv.setViewName("mentor/redirectView");
		return mv;
	}
	
	// 멘토 가입 수락 요청 처리함수
	@RequestMapping("/requestAccept.mentor")
	public ModelAndView requestAccept(MentorVO mtVO, GroupVO gVO, int nowPage, ModelAndView mv, HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		int cnt = mtDao.requestAccept(mtVO);
		if(cnt == 1) {
			gVO.setId(sid);
			gVO.setSno(mtVO.getSno2());
			gDao.addGroupMember(gVO);
			mv.addObject("PATH", "/study/group/groupDetail.mentor");
			mv.addObject("SNO", mtVO.getSno2());
		} else {
			mv.addObject("PATH", "/study/mentor/mentorList.mentor");
			mv.addObject("nowPage", nowPage);
		}
		mv.setViewName("mentor/redirectView");
		return mv;
	}
}
