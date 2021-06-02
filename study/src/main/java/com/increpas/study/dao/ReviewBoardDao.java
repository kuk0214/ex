package com.increpas.study.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.increpas.study.util.*;
import com.increpas.study.vo.*;

/**
 * 이 클래스는 리뷰게시판 관련 데이터베이스 작업을 전담해서 처리할 클래스
 * @author	조경국
 * @since	2021.06.02
 * @version	v.1.0
 * @see		
 * 			작업이력 ]
 * 				2021.06.02	- 	담당자		: 조경국
 *								작업내용	: 클래스제작, 리뷰게시판 리스트 보기, 상세보기, 글 등록, 수정, 삭제, 조회수 증가
 */

public class ReviewBoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 리뷰게시판 리스트 보기 데이터베이스 전담 처리함수
	public List reviewBRDList(PageUtil page) {
		return sqlSession.selectList("rvSQL.reviewBRDList", page);
	}
	
	// 리뷰게시판 총 게시글 수 조회 데이터베이스 전담 처리함수
	public int totalCnt() {
		return sqlSession.selectOne("rvSQL.totalCnt");
	}
	
	// 리뷰게시판 글 등록 데이터베이스 전담 처리함수
	public int reviewBRDWriteProc(BoardVO bVO) {
		return sqlSession.insert("rvSQL.reviewBRDWriteProc", bVO);
	}
	
	// 리뷰게시판 상세보기 데이터베이스 전담 처리함수
	public BoardVO reviewBRDDetail(int rvbno) {
		return sqlSession.selectOne("rvSQL.reviewBRDDetail", rvbno);
	}
	
	// 리뷰게시판 수정 데이터베이스 전담 처리함수
	public int reviewBRDEditProc(BoardVO bVO) {
		return sqlSession.update("rvSQL.reviewBRDEditProc", bVO);
	}
	
	// 리뷰게시판 삭제 데이터베이스 전담 처리함수
	public int reviewBRDDel(int rvbno) {
		return sqlSession.update("rvSQL.reviewBRDDel", rvbno);
	}
	
	// 리뷰게시판 조회수 증가 데이터베이스 전담 처리함수
	public int plusClick(int rvbno) {
		return sqlSession.update("rvSQL.plusClick", rvbno);
	}
}
