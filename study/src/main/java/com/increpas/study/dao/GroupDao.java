package com.increpas.study.dao;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.increpas.study.util.PageUtil;
import com.increpas.study.vo.GroupVO;

/**
 * 이 클래스는 스터디 그룹 관련 데이터베이스 작업을 전담해서 처리할 클래스
 * @author	조경국
 * @since	2021.06.08
 * @version	v.1.0
 * @see		
 * 			작업이력 ]
 * 				2021.06.08	- 	담당자		: 조경국
 *								작업내용	: 클래스제작, 스터디 그룹 관련, 스터디원 모집 관련
 */

public class GroupDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 스터디 생성 데이터베이스 전담 처리함수
	public int addGroup(GroupVO gVO) {
		return sqlSession.insert("gSQL.addGroup", gVO);
	}
	
	// 스터디원 가입 데이터베이스 전담 처리함수
	public int addGroupMember(GroupVO gVO) {
		return sqlSession.insert("gSQL.addGroupMember", gVO);
	}
	
	// 스터디원 가입시 인원수 증가 데이터베이스 전담 처리함수
	public int addNowCnt(int sno) {
		return sqlSession.update("gSQL.addNowCnt", sno);
	}
	
	// 나의 스터디 리스트 보기 데이터베이스 전담 처리함수
	public List myGroupList(String id) {
		return sqlSession.selectList("gSQL.myGroupList", id);
	}
	
	// 스터디원 모집 리스트 보기 데이터베이스 전담 처리함수
	public List studyBRDList(PageUtil page) {
		return sqlSession.selectList("gSQL.studyBRDList", page);
	}
	
	// 스터디원 모집 총 게시글 수 조회 데이터베이스 전담 처리함수
	public int getTotal() {
		return sqlSession.selectOne("gSQL.getTotal");
	}
	
	// 스터디원 모집 글 등록 데이터베이스 전담 처리함수
	public int studyBRDWriteProc(GroupVO gVO) {
		return sqlSession.insert("gSQL.studyBRDWriteProc", gVO);
	}
	
	// 스터디 그룹 정보 조회 데이터베이스 전담 처리함수
	public GroupVO groupInfo(int sno) {
		return sqlSession.selectOne("gSQL.groupInfo", sno);
	}
	
	// 스터디원 모집글 상세보기 데이터베이스 전담 처리함수
	public GroupVO studyBRDDetail(GroupVO gVO) {
		return sqlSession.selectOne("gSQL.studyBRDDetail", gVO);
	}
	
	// 스터디 가입 요청 데이터베이스 전담 처리함수
	public int requestJoin(GroupVO gVO) {
		return sqlSession.insert("gSQL.requestJoin", gVO);
	}
	
	// 스터디 가입 요청 여부 확인 데이터베이스 전담 처리함수
	public int rqJoinCheck(GroupVO gVO) {
		return sqlSession.selectOne("gSQL.rqJoinCheck", gVO);
	}
	
	// 가입요청 리스트 보기 데이터베이스 전담 처리함수
	public List requestJoinList(int sno) {
		return sqlSession.selectList("gSQL.requestJoinList", sno);
	}
	
	// 가입요청 응답처리 데이터베이스 전담 처리함수
	public int requestResponse(GroupVO gVO) {
		return sqlSession.update("gSQL.requestResponse", gVO);
	}
}
