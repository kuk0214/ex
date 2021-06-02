package com.increpas.study.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.increpas.study.util.*;
import com.increpas.study.vo.BoardVO;

/**
 * 이 클래스는 공지사항 관련 데이터베이스 작업을 전담해서 처리할 클래스
 * @author	조경국
 * @since	2021.06.02
 * @version	v.1.0
 * @see		
 * 			작업이력 ]
 * 				2021.06.02	- 	담당자		: 조경국
 *								작업내용	: 클래스제작, 공지사항 총 게시글 수 조회, 리스트 보기, 상세보기, 글 등록, 수정, 삭제, 조회수 증가
 */

public class NoticeDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 공지사항 리스트 보기 데이터베이스 전담 처리함수
	public List noticeList(PageUtil page) {
		return sqlSession.selectList("nSQL.noticeList", page);
	}
	
	// 공지사항 총 게시글 수 조회 데이터베이스 전담 처리함수
	public int totalCnt() {
		return sqlSession.selectOne("nSQL.totalCnt");
	}
	
	// 공지사항 상세보기 데이터베이스 전담 처리함수
	public BoardVO noiceDetail(int no) {
		return sqlSession.selectOne("nSQL.noticeDetail", no);
	}
	
	// 공지사항 글 등록 데이터베이스 전담 처리함수
	public int noticeWriteProc(BoardVO bVO) {
		return sqlSession.insert("nSQL.noticeWriteProc", bVO);
	}
	
	// 공지사항 글 수정 데이터베이스 전담 처리함수
	public int noticeEditProc(BoardVO bVO) {
		return sqlSession.update("nSQL.noticeEditProc", bVO);
	}
	
	// 공지사항 글 삭제 데이터베이스 전담 처리함수
	public int noticeDel(int no) {
		return sqlSession.update("nSQL.noticeDel", no);
	}
	
	// 공지사항 조회수 증가 데이터베이스 전담 처리함수
	public int plusClick(int no) {
		return sqlSession.update("nSQL.plusClick", no);
	}
}
