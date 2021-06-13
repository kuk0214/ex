package com.increpas.study.controller;



import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.increpas.study.dao.*;
import com.increpas.study.service.KakaoPayService;
import com.increpas.study.vo.*;



@Controller	
@RequestMapping("/pay")	


public class KakaoPay {
	@Autowired
	PayDao pDao;
	@Autowired
	KakaoPayService kSrvc;
   

	
	private static final Logger log1 = LoggerFactory.getLogger(KakaoPay.class);
    
	
	// 결제 처리 요청 함수
	@RequestMapping(value="/pointCharge.mentor", method=RequestMethod.POST)
	@ResponseBody
	@SuppressWarnings("unchecked")
    public HashMap<String, Object> pointCharge(AjaxVO aVO, HttpSession session){	   
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("result", "NO"); 
		try {
			URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			String resp = kSrvc.KakaoPay(aVO, session, con);
			Gson gson = new Gson(); 
			map = gson.fromJson(resp, HashMap.class);			
			
			aVO.setTid((String)map.get("tid"));
			int cnt = pDao.addPayInfo(aVO);
			if(cnt != 1) {
				return map;
			}
			map.put("result", "OK");
			return map;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return map;
    }
	
	// 결제 처리 승인 요청 함수
	@RequestMapping(value="/pointSusses.mentor")
	public ModelAndView pointSusses(AjaxVO aVO, HttpSession session, ModelAndView mv){

		try {
			URL url = new URL("https://kapi.kakao.com/v1/payment/approve");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();		
			String token = aVO.getPg_token();
			aVO = pDao.getPayInfo(aVO.getPartner_order_id());
			aVO.setPg_token(token);
			
			String resp = kSrvc.KakaoPay(aVO, session, con);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
			KakaoPayVO kVO = gson.fromJson(resp, KakaoPayVO.class);
			
			int no = pDao.pointApprove(kVO);
			mv.setViewName("member/fail");
			if(no != 1) {	
				// payinfo 데이터베이스 결재승인 변경 오류
				return mv;
			}
			int cnt = pDao.chargePoint(kVO);
			if(cnt != 1) {
				// member 테이블 포인트 충전 오류
				return mv;
			}
			mv.addObject("INFO", kVO);
			mv.setViewName("member/susses");

			session.setAttribute("PAYRESULT", "OK");
			return mv;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		mv.setViewName("main");
		return mv;		
	}
}
