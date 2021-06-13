package com.increpas.study.service;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.*;

import com.increpas.study.dao.*;
import com.increpas.study.vo.*;

public class OtoReplyService {
	@Autowired
	OtoBoardDao oDao;
	
	// 1대1 문의 게시글 답변 서비스 함수
	@Transactional
	public void addotoReplyService(BoardVO bVO, ModelAndView mv, RedirectView rv) {
		int cnt = oDao.otoReplyWriteProc(bVO);
		if(cnt == 1) {
			int no = oDao.otoProcess(bVO.getUpno());
			if(no != 1){
				// 답글 등록 후 상태변경 실패
				System.out.println("### 상태 변경 실패###");
			}
			System.out.println("### 상태 변경 성공###");
		} else {
			// 답글 등록 실패
		}
		rv.setUrl("/study/otoboard/otoBoardList.mentor");
		mv.setView(rv);		
	}
}
