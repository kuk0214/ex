package com.increpas.study.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.increpas.study.util.*;
import com.increpas.study.vo.*;
import com.increpas.study.vo.*;

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
	
	// 자유게시판 상세보기 데이터베이스 전담 처리함수
	public BoardVO freeBRDDetail(int frbno) {
		return sqlSession.selectOne("fSQL.freeBRDDetail", frbno);
	}
	
	// 자유게시판 댓글 조회 데이터베이스 전담 처리함수
	public List freeBRDReply(PageUtil page) {
		return sqlSession.selectList("fSQL.freeBRDReply", page);
	}
	
	// 자유게시판 댓글 등록 전담 처리함수
	public int freeBRDReplyProc(int frbno) {
		return sqlSession.insert("fSQL.freeBRDReplyProc", frbno);
	}
}