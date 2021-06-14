package com.increpas.study.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.servlet.view.*;

import com.increpas.study.dao.*;
import com.increpas.study.vo.*;

public class GroupService {
	@Autowired
	GroupDao gDao;
	
	@Transactional
	public boolean addGroup(GroupVO gVO, RedirectView rv) {
		boolean bool = false;
		int cnt = gDao.addGroup(gVO);
		gDao.addGroupMember(gVO);
		if(cnt == 1) {
			rv.setUrl("/study/group/studyBoard.mentor");
			bool = true;
		} else {
			rv.setUrl("/study/group/addGroup.mentor");
		}
		return bool;
	}
	
	@Transactional
	public boolean requestAccept(GroupVO gVO) {
		boolean bool = false;
		int sno = gVO.getSno();
		int cnt = gDao.reentrance(gVO);
		if(cnt != 1) {
			gDao.addGroupMember(gVO);
		} else {
			gDao.reJoin(gVO);
		}
		gDao.requestResponse(gVO);			
		gDao.addNowCnt(sno);
		bool = true;
		return bool;
	}
	
}
