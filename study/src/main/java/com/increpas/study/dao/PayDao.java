package com.increpas.study.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;

import com.increpas.study.vo.*;

public class PayDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 결제 입력 정보 등록 전담 처리함수
	public int addPayInfo(AjaxVO aVO) {
		return sqlSession.insert("pSQL.addPayInfo", aVO);
	}
	
	// 결제 입력 정보 조회 전담 처리함수
	public AjaxVO getPayInfo(String oid) {
		return sqlSession.selectOne("pSQL.getPayInfo", oid);
	}
	
	// 결제 정보 승인 전담 처리함수
	public int pointApprove(KakaoPayVO kVO) {
		return sqlSession.update("pSQL.pointApprove", kVO);
	}
	
	// 포인트 충전  전담 처리함수
	public int chargePoint(KakaoPayVO kVO) {
		return sqlSession.update("pSQL.chargePoint", kVO);
	}
	
	
}
