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

import com.increpas.study.dao.MentorDao;
import com.increpas.study.util.PageUtil;
import com.increpas.study.vo.MentorVO;

@Controller
@RequestMapping("/mentor")
public class Mentor {
	@Autowired
	MentorDao mtDao;
	
	// 멘토 리스트 보기 요청 처리함수
	@RequestMapping("/mentorList.mentor")
	public ModelAndView mentorList(PageUtil page, ModelAndView mv, HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		int cnt = mtDao.mentorCheck(sid);
		int nowPage = page.getNowPage();
		if(nowPage == 0) {
			nowPage = 1;
		}
		int total = mtDao.getTotal();
		page.setPage(nowPage, total, 10, 5);
		List list = mtDao.mentorList(page);
		String keyword = page.getKeyword();
		String option = page.getOption();
		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		mv.addObject("keyword", keyword);
		mv.addObject("option", option);
		mv.addObject("CNT", cnt);
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
	public Object mentorPr(int no) {
		MentorVO mtVO = mtDao.mentorPr(no);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", mtVO.getName());
        map.put("pr", mtVO.getPr());
        return map;
	}
}
