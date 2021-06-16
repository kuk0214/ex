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
	
	// 멘토 삭제 데이터베이스 전담 처리함수
	public int mentorDel(int no) {
		return sqlSession.update("mtSQL.mentorDel", no);
	}
	
	// 멘토 수정 폼보기 데이터베이스 전담 처리함수
	public MentorVO mentorEdit(int no) {
		return sqlSession.selectOne("mtSQL.mentorEdit", no);
	}
	
	// 멘토 수정 데이터베이스 전담 처리함수
	public int mentorEditProc(MentorVO mtVO) {
		return sqlSession.update("mtSQL.mentorEditProc", mtVO);
	}
	
	// 그룹장 확인 데이터베이스 전담 처리함수
	public int leaderCheck(String sid) {
		return sqlSession.selectOne("mtSQL.leaderCheck", sid);
	}
	
	// 멘토 요청 그룹 리스트 조회 데이터베이스 전담 처리함수
	public List mentorGroupList(String sid) {
		return sqlSession.selectList("mtSQL.mentorGroupList", sid);
	}
	
	// 멘토 가입 요청 데이터베이스 전담 처리함수
	public int requsetMentor(MentorVO mtVO) {
		return sqlSession.insert("mtSQL.requestMentor", mtVO);
	}
	
	// 멘토 가입 요청 여부 확인 데이터베이스 전담 처리함수
	public int requestCheck(MentorVO mtVO) {
		return sqlSession.selectOne("mtSQL.requestCheck", mtVO);
	}
	
	// 멘토 가입 요청 취소 데이터베이스 전담 처리함수
	public int requestCancle(MentorVO mtVO) {
		return sqlSession.update("mtSQL.requestCancle", mtVO);
	}
	
	// 멘토 가입 요청 리스트 보기 데이터베이스 전담 처리함수
	public List requestMentorList(String sid) {
		return sqlSession.selectList("mtSQL.requestMentorList", sid);
	}
	
	// 멘토 가입 수락 데이터베이스 전담 처리함수
	public int requestAccept(MentorVO mtVO) {
		return sqlSession.update("mtSQL.requestAccept", mtVO);
	}
}
