package com.increpas.study.dao;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.increpas.study.util.PageUtil;
import com.increpas.study.vo.GroupVO;

public class GroupDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 스터디 생성 데이터베이스 전담 처리함수
	public int addGroup(GroupVO gVO) {
		return sqlSession.insert("gSQL.addGroup", gVO);
	}
	
	// 스터디 그룹장 가입 데이터베이스 전담 처리함수
	public int addGroupLeader(GroupVO gVO) {
		return sqlSession.insert("gSQL.addGroupLeader", gVO);
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
}
