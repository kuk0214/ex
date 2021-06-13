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
		int cnt13 = 0;
		int cnt14 = 0;
		int cnt15 = 0;
		int cnt16 = 0;
		int cnt17 = 0;
		String[] loc = {"서울 강남구", "서울 강동구", "서울 강북구", "서울 강서구", "서울 관악구", "서울 광진구", "서울 구로구", "서울 금천구",
						"서울 노원구", "서울 도봉구", "서울 동대문구", "서울 동작구", "서울 마포구", "서울 서대문구", "서울 서초구", "서울 성동구",
						"서울 성북구", "서울 송파구", "서울 양천구", "서울 영등포구", "서울 용산구", "서울 은평구", "서울 종로구", "서울 중구", "서울 중랑구"};
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
		cnt17 = dbDao.add1stGroup();
		for(int no = 2 ; no < 6; no++ ) {
			cnt5 = dbDao.addGroup(no);
		}
		for(int no = 1 ; no < 5; no++ ) {
			cnt6 = dbDao.addGroupMember(no);
		}
		for(int no = 10 ; no < 13; no++ ) {
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
		for(int no = 2 ; no < 6; no++ ) {
			cnt13 = dbDao.addGroupLeader(no);
		}
		for(int no = 10 ; no < 13; no++ ) {
			cnt14 = dbDao.reviewMentor(no);
		}
		for(int no = 2 ; no < 5; no++ ) {
			cnt15 = dbDao.reviewGroup(no);
		}
		for(int j = 1; j < 5; j++ ) {			
			for(int i = 0 ; i < 25 ; i++ ) {
				cnt16 = dbDao.addLocGroup(loc[i]);
			}
		}
		
		if(cnt1 == 1 && cnt2 == 1 && cnt3 ==1 && cnt4 == 1 && cnt5 ==1 && cnt6 == 1 && cnt7 == 1 &&
				cnt8 == 1 && cnt9 == 1 && cnt10 == 1 && cnt11 == 1 && cnt12 == 1 && cnt13 == 1 && 
				cnt14 == 1 && cnt15 == 1 && cnt16 == 1 && cnt17 == 1) {
			mv.addObject("MSG", "succeess");
		}
		
		return mv;
	}
}
