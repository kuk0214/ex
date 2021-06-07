package com.increpas.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.increpas.study.dao.DBDao;

@Controller
@RequestMapping("/db")
public class DBSet {
	@Autowired
	DBDao dbDao;
	
	// db세팅 메인
	@RequestMapping("/DBSet.mentor")
	public String DBSet() {
		return "db/DBSet";
	}
	
	// 데이터 등록 요청 전담 처리함수
	@RequestMapping("/DBDataSet.mentor")
	public ModelAndView dbDataSet(ModelAndView mv) {
		int cnt1 = 0;
		int cnt2 = 0;
		int cnt3 = 0;
		int cnt4 = 0;
		int cnt5 = 0;
		int cnt6 = 0;
		int cnt7 = 0;
		int cnt8 = 0;
		int cnt9 = 0;
		int cnt10 = 0;
		int cnt11 = 0;
		int cnt12 = 0;
		
		for(int no = 1 ; no < 10 ; no++ ) {
			cnt1 = dbDao.memberGeneral(no);			
		}
		for(int no = 10 ; no < 15 ; no++ ) {
			cnt2 = dbDao.memberMentor(no);			
		}
		for(int no = 15 ; no < 19 ; no++ ) {
			cnt3 = dbDao.memberBusiness(no);			
		}
		for(int no = 1 ; no < 4 ; no++ ) {
			cnt4 = dbDao.memberAdmin(no);			
		}
		for(int no = 1 ; no < 6; no++ ) {
			cnt5 = dbDao.addGroup(no);
		}
		for(int no = 2 ; no < 5; no++ ) {
			cnt6 = dbDao.addGroupMember(no);
		}
		for(int no = 10 ; no < 15; no++ ) {
			cnt7 = dbDao.addMentor(no);
		}
		for(int no = 2 ; no < 5; no++ ) {
			cnt8 = dbDao.studyBRD(no);
		}
		for(int i = 0 ; i < 51; i++ ) {
			cnt9 = dbDao.freeBRD();
		}
		for(int no = 2 ; no < 9; no++ ) {
			cnt10 = dbDao.freeBRDReply(no);
		}
		cnt11 = dbDao.freeBRDReReply();
		cnt12 = dbDao.notice();
		
		if(cnt1 == 1 && cnt2 == 1 && cnt3 ==1 && cnt4 == 1 && cnt5 ==1 && cnt6 == 1 && cnt7 == 1 &&
				cnt8 == 1 && cnt9 == 1 && cnt10 == 1 && cnt11 == 1 && cnt12 == 1) {
			mv.addObject("MSG", "succeess");
		}
		return mv;
	}
}
