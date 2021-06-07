package com.increpas.study.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class DBDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// member 일반회원 데이터 등록 전담 처리함수
	public int memberGeneral(int no) {
		return sqlSession.insert("DBSQL.memberGeneral", no);
	}
	
	// member 멘토회원 데이터 등록 전담 처리함수
	public int memberMentor(int no) {
		return sqlSession.insert("DBSQL.memberMentor", no);
	}
	
	// member 업체회원 데이터 등록 전담 처리함수
	public int memberBusiness(int no) {
		return sqlSession.insert("DBSQL.memberBusiness", no);
	}
	
	// member 관리자 데이터 등록 전담 처리함수
	public int memberAdmin(int no) {
		return sqlSession.insert("DBSQL.memberAdmin", no);
	}
	
	// 스터디그룹 데이터 등록 전담 처리함수
	public int addGroup(int no) {
		return sqlSession.insert("DBSQL.addGroup", no);
	}
	
	// 스더디그룹 가입 데이터 등록 전담 처리함수
	public int addGroupMember(int no) {
		return sqlSession.insert("DBSQL.addGroupMember", no);
	}
	
	// 멘토 등록 데이터 전담 처리함수
	public int addMentor(int no) {
		return sqlSession.insert("DBSQL.addMentor", no);
	}
	
	// 스터디원 모집글 데이터 전담 처리함수
	public int studyBRD(int no) {
		return sqlSession.insert("DBSQL.studyBRD", no);
	}
	
	// 자유게시판 글 데이터 전담 처리함수
	public int freeBRD() {
		return sqlSession.insert("DBSQL.freeBRD");
	}
	
	// 자유게시판 댓글 데이터 전담 처리함수
	public int freeBRDReply(int no) {
		return sqlSession.insert("DBSQL.freeBRDReply", no);
	}
	
	// 자유게시판 대댓글 데이터 전담 처리함수
	public int freeBRDReReply() {
		return sqlSession.insert("DBSQL.freeBRDReReply");
	}
	
	// 공지사항 데이터 전담 처리함수
	public int notice() {
		return sqlSession.insert("DBSQL.notice");
	}
}
