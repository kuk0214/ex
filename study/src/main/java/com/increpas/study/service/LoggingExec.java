package com.increpas.study.service;

import javax.servlet.http.*;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.*;

import com.increpas.study.vo.*;

@Service
@Aspect
public class LoggingExec {

	private static Logger log1 = LoggerFactory.getLogger(LoggingExec.class);

	@Pointcut("execution(* com.increpas.study.controller.KakaoPay.pointCharge(..))")
	public void recordPay() {
		System.out.println("결제 요청");
	}

	@After("recordPay()")
	public boolean recPay(JoinPoint join) {
		AjaxVO aVO = (AjaxVO) join.getArgs()[0];

		if (aVO.getTid() != null) {
			log1.info(aVO.getPartner_user_id() + " 님 ] " + aVO.getTotal_amount() + " 결제 요청 ***");
		}
		return true;
	}

	@Pointcut("execution(* com.increpas.study.controller.KakaoPay.pointSusses(..))")
	public void recordPaySusses() {
		System.out.println("결제 승인");
	}

	@After("recordPaySusses()")
	public boolean recPaySs(JoinPoint join) {
		HttpSession session = (HttpSession) join.getArgs()[1];
		
		if((session.getAttribute("PAYRESULT")).equals("OK") ) {
			  log1.info(session.getAttribute("SID") + " 님 ] 결제 승인 ***"); 
		}
		 
		return true;
	}
}
