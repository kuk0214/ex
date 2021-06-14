package com.increpas.study.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.increpas.study.vo.MemberVO;

public class MemberDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int getIdCnt(String sid) {
		return sqlSession.selectOne("mSQL.idCheck", sid);
	}
	
	// 비밀번호 검사 처리함수
	public int getPwCnt(MemberVO mVO) {
		return sqlSession.selectOne("mSQL.pwCheck", mVO);
	}
	
	// 로그인 처리
	public int getLogin(MemberVO mVO) {
		return sqlSession.selectOne("mSQL.login", mVO);
	}
	
	// 내정보조회 전담 처리함수
	public MemberVO getMyInfo(String id) {
		MemberVO mVO = sqlSession.selectOne("mSQL.memberIdInfo", id);
		mVO.setGen((mVO.getGen().equals("M")) ? "남자" : "여자");
		return mVO;
	}
	
	// 내정보 수정 전담 처리함수
	public int updateInfo(MemberVO mVO) {		
		return sqlSession.update("mSQL.editMyInfo", mVO);
	}
	
	// 회원정보 입력 전담 처리함수
	 public int addMember(MemberVO mVO) {
		 return sqlSession.insert("mSQL.addMember", mVO);
	 }
	 
	 // 회원 메일 조회 전담 처리함수
	 public String getMail(String id) {
		 return sqlSession.selectOne("mSQL.getMail", id);
	 }
	 
	 // 회원 가입 승인 처리 전담 처리함수
	 public int getIsCnt(String id) {
		 return sqlSession.update("mSQL.editIsshow", id);
	 }
	 
	 // 회원 탈퇴 처리 전담 처리함수
	 public int delUser(String id) {
		 return sqlSession.update("mSQL.delUser", id);
	 }
	 
	 // 패스워드 변경 처리 전담 처리함수
	 public int editPass(MemberVO mVO) {
		 return sqlSession.update("mSQL.editPass", mVO);
	 }
	 
	 // 포인트 충전 처리 전담 처리함수
	 public int pointCharge(MemberVO mVO) {
		 return sqlSession.update("mSQL.pointCharge", mVO);
	 }
}
