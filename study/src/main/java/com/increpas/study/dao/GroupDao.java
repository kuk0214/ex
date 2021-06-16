package com.increpas.study.dao;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.increpas.study.util.PageUtil;
import com.increpas.study.vo.GroupVO;
import com.increpas.study.vo.MemberVO;

/**
 * 이 클래스는 스터디 그룹 관련 데이터베이스 작업을 전담해서 처리할 클래스
 * @author	조경국
 * @since	2021.06.08
 * @version	v.1.0
 * @see		
 * 			작업이력 ]
 * 				2021.06.08	- 	담당자		: 조경국
 *								작업내용	: 클래스제작, 스터디 그룹 관련, 스터디원 모집 관련
 * 				2021.06.09	- 	담당자		: 조경국
 *								작업내용	: 그룹원 리스트 보기, 그룹원 감소, 그룹원 추방&탈퇴, 재가입, 그룹 해체
 * 				2021.06.10	- 	담당자		: 조경국
 *								작업내용	: 스터디원 모집 글 삭제, 수정
 * 				2021.06.14	- 	담당자		: 조경국
 *								작업내용	: 지역별 그룹수 조회, 스터디원 모집 글 조회수 증가, 가입요청 취소
 * 				2021.06.16	- 	담당자		: 조경국
 *								작업내용	: 스터디 그룹 멘토 탈퇴 & 추방
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
	
	// 그룹원 리스트 보기 데이터베이스 전담 처리함수
	public List groupMemberList(int sno) {
		return sqlSession.selectList("gSQL.groupMemberList", sno);
	}
	
	// 스터디 그룹 인원 감소 데이터베이스 전담 처리함수
	public int deNowCnt(int sno) {
		return sqlSession.update("gSQL.deNowCnt", sno);
	}
	
	// 스터디 그룹원 추방&탈퇴 데이터베이스 전담 처리함수
	public int groupMemberOut(GroupVO gVO) {
		return sqlSession.update("gSQL.groupMemberOut", gVO);
	}
	
	// 재가입 여부 확인 데이터베이스 전담 처리함수
	public int reentrance(GroupVO gVO) {
		return sqlSession.selectOne("gSQL.reentrance", gVO);
	}
	
	// 스터디 그룹 재가입 데이터베이스 전담 처리함수
	public int reJoin(GroupVO gVO) {
		return sqlSession.update("gSQL.reJoin", gVO);
	}
	
	// 스터디 그룹 수정 데이터베이스 전담 처리함수
	public int groupEditProc(GroupVO gVO) {
		return sqlSession.update("gSQL.groupEditProc", gVO);
	}
	
	// 스터디 그룹 해체 데이터베이스 전담 처리함수
	public int groupDel(int sno) {
		return sqlSession.update("gSQL.groupDel", sno);
	}
	
	// 스터디원 모집 글 삭제 데이터베이스 전담 처리함수
	public int studyBRDDel(int sbno) {
		return sqlSession.update("gSQL.studyBRDDel", sbno);
	}
	
	// 스터디 그룹 해체시 모집글 삭제 데이터베이스 전담 처리함수
	public int studyBRDDel2(int sno) {
		return sqlSession.update("gSQL.studyBRDDel2", sno);
	}
	
	// 스터디 모집 글 수정 데이터베이스 전담 처리함수
	public int studyBRDEditProc(GroupVO gVO) {
		return sqlSession.update("gSQL.studyBRDEditProc", gVO);
	}
	
	// 지역별 그룹 수 조회 데이터베이스 전담 처리함수
	public List<GroupVO> getLoc() {
		return sqlSession.selectList("gSQL.getLoc");
	}
	
	// 스터디원 모집 글 조회수 증가 데이터베이스 전담 처리함수
	public int plusClick(int sbno) {
		return sqlSession.update("gSQL.plusClick", sbno);
	}
	
	// 스터디그룹 가입요청 취소 데이터베이스 전담 처리함수
	public int requestJoinCancle(GroupVO gVO) {
		return sqlSession.update("gSQL.requestJoinCancle", gVO);
	}
	
	// 스터디그룹 멘토 탈퇴 & 추방 데이터베이스 전담 처리함수
	public int groupMentorOut(int sno) {
		return sqlSession.update("gSQL.groupMentorOut", sno);
	}
}
