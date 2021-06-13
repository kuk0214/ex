package com.increpas.study.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.increpas.study.util.*;
import com.increpas.study.vo.*;


public class OtoBoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 1대1 문의 게시판 리스트 보기 데이터베이스 전담 처리함수
	public List<BoardVO> getOtoList(BoardVO bVO) {
		return sqlSession.selectList("oSQL.otoList", bVO);
	}
	
	// 1대1 문의 게시판 상세보기 데이터베이스 전담 처리함수
	public List<BoardVO> otoDetail(int askno) {
		return sqlSession.selectList("oSQL.otoDetail", askno);
	}
	
	// 1대1 문의 게시판 총 게시글 수 조회 데이터베이스 전담 처리함수
	public int totalCnt() {
		return sqlSession.selectOne("oSQL.totalCnt");
	}
	
	// 1대1 문의 게시판 글 등록 데이터베이스 전담 처리함수
	public int otoWriteProc(BoardVO bVO) {
		return sqlSession.insert("oSQL.otoWriteProc", bVO);
	}
	

	
	// 1대1 문의 게시판 수정 데이터베이스 전담 처리함수
	public int otoEditProc(BoardVO bVO) {
		return sqlSession.update("oSQL.otoEditProc", bVO);
	}
	
	// 1대1 문의 게시판 삭제 데이터베이스 전담 처리함수
	public int otoDel(int askno) {
		return sqlSession.update("oSQL.otoDel", askno);
	}
	
	// 1대1 문의 게시판 조회수 증가 데이터베이스 전담 처리함수
	public int plusClick(int askno) {
		return sqlSession.update("oSQL.plusClick", askno);
	}

	// 1대1 문의 게시판 내 총 게시글 수 조회 전담 처리함수
	public int mytotalCnt(String id) {
		return sqlSession.selectOne("oSQL.getMyTotalCnt", id);
	}
	
	// 1대1 문의 게시판 답변 등록 처리 전담 처리함수
	public int otoReplyWriteProc(BoardVO bVO) {
		return sqlSession.insert("oSQL.otoReplyWriteProc", bVO);
	}

	// 1대1 문의 게시판 상태변경 처리 전담 처리함수
	public int otoProcess(int upno) {
		return sqlSession.update("oSQL.otoProcess", upno);
	}
}
