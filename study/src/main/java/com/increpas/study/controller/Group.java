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
 * 					2021.06.09	-	담당자		:	조경국
 * 									작업내용	:	스터디 그룹 상세보기, 그룹원 추방, 그룹 탈퇴, 그룹 정보 수정, 그룹 해체
 * 					2021.06.10	-	담당자		:	조경국
 * 									작업내용	:	스터디원 모집 글 삭제, 스터디원 모집 글 수정
 * 					2021.06.14	-	담당자		:	조경국
 * 									작업내용	:	스터디원 모집 글 조회수 증가
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
	public ModelAndView studyBRDWrite(int nowPage, ModelAndView mv, HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		List list = gDao.myGroupList(sid);
		mv.addObject("LIST", list);
		mv.addObject("nowPage", nowPage);
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
		} else {
			rv.setUrl("/study/group/studyBoardWrite.mentor");
		}
		mv.setView(rv);
		return mv;
	}
	
	// 스터디원 모집 글 상세보기 요청 처리함수
	@RequestMapping("/studyBoardDetail.mentor")
	public ModelAndView studyBRDDetail(GroupVO gVO, int nowPage, ModelAndView mv) {
		gDao.plusClick(gVO.getSbno());
		int cnt = gDao.rqJoinCheck(gVO);
		gVO = gDao.studyBRDDetail(gVO);
		mv.addObject("DATA", gVO);
		mv.addObject("CNT", cnt);
		mv.addObject("nowPage", nowPage);
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
		int cnt = gDao.reentrance(gVO);
		if(cnt != 1) {
			gDao.addGroupMember(gVO);
		} else {
			gDao.reJoin(gVO);
		}
		gDao.requestResponse(gVO);			
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
	
	// 그룹 상세보기 요청 처리함수
	@RequestMapping("/groupDetail.mentor")
	public ModelAndView groupDetail(int sno, ModelAndView mv) {
		GroupVO gVO = gDao.groupInfo(sno);
		List list = gDao.groupMemberList(sno);
		
		mv.addObject("DATA", gVO);
		mv.addObject("LIST", list);
		return mv;
	}
	
	// 스터디 그룹원 추방 요청 처리함수
	@RequestMapping("/expelGroupMember.mentor")
	public ModelAndView expelGroupMember(int sno, String id, ModelAndView mv, RedirectView rv) {
		gDao.groupMemberOut(id);
		gDao.deNowCnt(sno);
		mv.addObject("SNO", sno);
		mv.addObject("PATH", "/study/group/groupDetail.mentor");
		mv.setViewName("group/redirectView");
		return mv;
	}
	
	// 스터디 그룹 탈퇴 요청 처리함수
	@RequestMapping("/groupMemberOut.mentor")
	public ModelAndView groupMemberOut(int sno, String id, ModelAndView mv, RedirectView rv) {
		int cnt = gDao.groupMemberOut(id);
		if(cnt != 1) {
			mv.addObject("SNO", sno);
			mv.addObject("PATH", "/study/group/groupDetail.mentor");
			mv.setViewName("group/redirectView");
			return mv;
		}
		gDao.deNowCnt(sno);
		rv.setUrl("/study/group/myGroup.memtor");
		mv.setView(rv);
		return mv;
	}
	
	// 스터디 그룹 수정 폼보기 요청 처리함수
	@RequestMapping("/groupEdit.mentor")
	public ModelAndView groupEdit(int sno, ModelAndView mv) {
		GroupVO gVO = gDao.groupInfo(sno);
		mv.addObject("DATA", gVO);
		return mv;
	}
	
	// 스터디 그룹 수정 요청 처리함수
	@RequestMapping("/groupEditProc.mentor")
	public ModelAndView groupEditProc(GroupVO gVO, ModelAndView mv, RedirectView rv) {
		int cnt = gDao.groupEditProc(gVO);
		mv.addObject("SNO", gVO.getSno());
		if(cnt == 1) {
			mv.addObject("PATH", "/study/group/groupDetail.mentor");
		} else {
			mv.addObject("PATH", "/study/group/groupEdit.mentor");
		}
		mv.setViewName("group/redirectView");
		return mv;
	}
	
	// 스터디 그룹 해체 요청 처리함수
	@RequestMapping("/groupDel.mentor")
	public ModelAndView groupDel(int sno, String id, ModelAndView mv, RedirectView rv) {
		int cnt = gDao.groupDel(sno);
		gDao.groupMemberOut(id);
		gDao.studyBRDDel2(sno);
		if(cnt == 1) {
			rv.setUrl("/study/group/myGroup.mentor");
			mv.setView(rv);
			return mv;
		}
		mv.addObject("PATH", "/study/group/groupDetail.mentor");
		mv.addObject("SNO", sno);
		mv.setViewName("group/redirectView");
		return mv;
	}
	
	// 스터디원 모집 글 삭제 요청 처리함수
	@RequestMapping("/studyBoardDel.mentor")
	public ModelAndView studyBRDDel(int sbno, int sno, ModelAndView mv, RedirectView rv) {
		int cnt = gDao.studyBRDDel(sbno);
		if(cnt == 1) {
			rv.setUrl("/study/group/studyBoard.mentor");
			mv.setView(rv);
			return mv;
		}
		mv.addObject("PATH", "/study/group/studyBoardDetail.mentor");
		mv.addObject("SBNO", sbno);
		mv.addObject("SNO", sno);
		mv.setViewName("group/redirectView");
		return mv;
	}
	
	// 스터디원 모집 글 수정 폼보기 요청 처리함수
	@RequestMapping("/studyBoardEdit.mentor")
	public ModelAndView studyBRDEdit(GroupVO gVO, int nowPage, ModelAndView mv) {
		gVO = gDao.studyBRDDetail(gVO);
		mv.addObject("DATA", gVO);
		mv.addObject("nowPage", nowPage);

		return mv;
	}
	
	// 스터디원 모집 글 수정 요청 처리함수
	@RequestMapping("/studyBoardEditProc.mentor")
	public ModelAndView studyBRDEditProc(GroupVO gVO, ModelAndView mv) {
		int cnt = gDao.studyBRDEditProc(gVO);
		mv.addObject("SBNO", gVO.getSbno());
		mv.addObject("SNO", gVO.getSno());
		if(cnt == 1) {
			mv.addObject("PATH", "/study/group/studyBoardDetail.mentor");
		} else {
			mv.addObject("PATH", "/study/group/studyBoardEdit.mentor");
		}
		mv.setViewName("group/redirectView");
		return mv;
	}

}
