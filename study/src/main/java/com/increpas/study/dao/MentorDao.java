package com.increpas.study.dao;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.increpas.study.util.*;
import com.increpas.study.vo.*;

public class MentorDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 멘토 리스트 보기 데이터베이스 전담 처리함수
	public List mentorList(PageUtil page) {
		return sqlSession.selectList("mtSQL.mentorList", page);
	}
	
	// 등록된 멘토 총 수 조회 데이터베이스 전담 처리함수
	public int getTotal() {
		return sqlSession.selectOne("mtSQL.getTotal");
	}
	
	// 멘토 소개 등록 데이터베이스 전담 처리함수
	public int mentorWriteProc(MentorVO mtVO) {
		return sqlSession.insert("mtSQL.mentorWriteProc", mtVO);
	}
	
	// 멘토 확인 데이터베이스 전담 처리함수
	public int mentorCheck(String sid) {
		return sqlSession.selectOne("mtSQL.mentorCheck", sid);
	}
	
	// 멘토 자기소개 보기 데이터베이스 전담 처리함수
	public MentorVO mentorPr(int no) {
		return sqlSession.selectOne("mtSQL.mentorPr", no);
	}
}
