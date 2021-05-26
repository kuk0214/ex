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
	
	public List freeBRDList(PageUtil page) {
		return sqlSession.selectList("fSQL.freeBRDList", page);
	}
	
	public int getTotal() {
		return sqlSession.selectOne("fSQL.getTotal");
	}
}