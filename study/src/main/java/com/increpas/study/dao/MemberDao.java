package com.increpas.study.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.increpas.study.vo.*;

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
		return sqlSession.selectOne("mSQL.memberIdInfo", id);
	}
	
	// 내정보 수정 전담 처리함수
	public int updateInfo(MemberVO mVO) {		
		return sqlSession.update("mSQL.editMyInfo", mVO);
	}
	
	// 회원정보 입력 전담 처리함수
	 public int addMember(MemberVO mVO) {
		 return sqlSession.insert("mSQL.addMember", mVO);
	 }
	
	 public List<MemberVO> getLoc() {
			return sqlSession.selectList("mSQL.getLoc");
		}
}
