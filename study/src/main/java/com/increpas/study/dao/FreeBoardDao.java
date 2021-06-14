package com.increpas.study.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.increpas.study.util.*;
import com.increpas.study.vo.*;

/**
 * 이 클래스는 자유게시판 관련 데이터베이스 작업을 전담해서 처리할 클래스
 * @author	조경국
 * @since	2021.06.01
 * @version	v.1.0
 * @see		
 * 			작업이력 ]
 * 				2021.06.01	- 	담당자		:	조경국
 *								작업내용	:	클래스제작, 자유게시판 총 게시글 수 조회, 상세보기, 댓글 조회, 댓글 등록
 *				2021.06.02	-	담장자		:	조경국
 *								작업내용	:	댓글 수 조회
 *
 */

public class FreeBoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 자유게시판 리스트 조회 데이터베이스 전담 처리함수
	public List freeBRDList(PageUtil page) {
		return sqlSession.selectList("fSQL.freeBRDList", page);
	}
	
	// 자유게시판 총 게시글 수 조회 데이터베이스 전담 처리함수
	public int getTotal() {
		return sqlSession.selectOne("fSQL.getTotal");
	}
	
	// 댓글 수 조회 데이터베이스 전담 처리함수
	public int replyCnt(int frbno) {
		return sqlSession.selectOne("fSQL.replyCnt", frbno);
	}
	
	// 자유게시판 상세보기 데이터베이스 전담 처리함수
	public BoardVO freeBRDDetail(int frbno) {
		return sqlSession.selectOne("fSQL.freeBRDDetail", frbno);
	}
	
	// 자유게시판 댓글 조회 데이터베이스 전담 처리함수
	public List freeBRDReply(PageUtil page) {
		return sqlSession.selectList("fSQL.freeBRDReply", page);
	}
	
	// 자유게시판 댓글 등록 데이터베이스 전담 처리함수
	public int freeBRDReplyProc(BoardVO bVO) {
		return sqlSession.insert("fSQL.freeBRDReplyProc", bVO);
	}
	
	// 자유게시판 대댓글 등록 데이터베이스 전담 처리함수
	public int freeBRDReplyProc2(BoardVO bVO) {
		return sqlSession.insert("fSQL.freeBRDReplyProc2", bVO);
	}
	
	// 자유게시판 글 등록 데이터베이스 전담 처리함수
	public int freeBRDWriteProc(BoardVO bVO) {
		return sqlSession.insert("fSQL.freeBRDWriteProc", bVO);
	}
	
	// 자유게시판 글 삭제 데이터베이스 전담 처리함수
	public int freeBRDDel(int frbno) {
		return sqlSession.update("fSQL.freeBRDDel", frbno);
	}
	
	// 자유게시판 글 수정 데이터베이스 전담 처리함수
	public int freeBRDEditProc(BoardVO bVO) {
		return sqlSession.update("fSQL.freeBRDEditProc", bVO);
	}
	
	// 자유게시판 댓글 수정 데이터베이스 전담 처리함수
	public int freeBRDReplyEditProc(BoardVO bVO) {
		return sqlSession.update("fSQL.freeBRDReplyEditProc", bVO);
	}
	
	// 자유게시판 댓글 삭제 데이터베이스 전담 처리함수
	public int freeBRDReplyDelProc(BoardVO bVO) {
		return sqlSession.update("fSQL.freeBRDReplyDelProc", bVO);
	}
	
	// 자유게시판 글 삭제시 댓글 삭제 데이터베이스 전담 처리함수
	public int delBRDReply(int frbno) {
		return sqlSession.update("fSQL.delBRDReply", frbno);
	}
}