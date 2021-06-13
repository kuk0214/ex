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
import com.increpas.study.service.EmailSender;
import com.increpas.study.vo.*;



@Controller	
@RequestMapping("/mail")	


public class Mail {
	@Autowired
	MemberDao mDao;
   
	@Autowired
    EmailSender emailSender;
	
	private static final Logger log1 = LoggerFactory.getLogger(Mail.class);
    

	//메일 인증 보내기 요청 처리함수
    @RequestMapping(value="/gmailSendAction.mentor", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> sendEmailAction (String mail, String id){
 
        EmailVO email = new EmailVO();
        
        String host = "http://localhost/study/mail/";
        String code = SHA256(mail, "cos");
        String subject = "스터디 회원가입을 위한 이메일 인증 메일입니다.";
		String content = "다음 링크에 접속하여 이메일 인증을 진행해주세요. " 
		        + "<a href='" + host + "gmailCheckAction.mentor?code=" + code 
				+ "&id=" + id + "'>이메일 인증하기</a>";
        
        email.setReciver(mail);
        email.setSubject(subject);
        email.setContent(content);      
        
        HashMap<String, String> map = new HashMap<String, String>();
        try {
        	emailSender.SendEmail(email);
        	map.put("result", "OK");
        }  catch (Exception e) {
			e.printStackTrace();
			map.put("result", "NO");
        }
        
		return map;
    }
    
    //forgot password 매일 보내기 처리함수
    @RequestMapping(value="/fpmailSendAction.mentor", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> FPMail (MemberVO mVO){
    	HashMap<String, String> map = new HashMap<String, String>();
    	String tmail = mDao.getMail(mVO.getId());
    	
    	if(!(mVO.getMail().equals(tmail))) {
			
			 map.put("result", "FAIL");
			 return map;

    	}
    	   	
    	// 임시비번 생성
        String pw = UUID.randomUUID().toString().replaceAll("-", "");
        pw = pw.substring(0, 10);
        
        mVO.setPw(pw);
        int cnt = mDao.editPass(mVO);
        
        if(cnt != 1) {
        	System.out.println("비번 변경 실패");
        	map.put("result", "NO");
        	return map;
        }
        
		String subject = "스터디 임시 비밀번호 입니다.";
		String content = "임시 비밀번호 :  " + pw  + "<br>" +
		"다음 링크에 접속하여 다시 로그인을 진행해주세요. " 
		+ "<a href='http://localhost/study/member/login.mentor'>로그인하기</a>";
    	
		EmailVO email = new EmailVO();
		
    	email.setReciver(mVO.getMail());
    	email.setSubject(subject);
    	email.setContent(content);      
    	
    	try {
    		emailSender.SendEmail(email);
    		map.put("result", "OK");
    	}  catch (Exception e) {
    		e.printStackTrace();
    		map.put("result", "NO");
    	}
    	
    	return map;
    }
    
    // 메일인증 회원 가입 승인 처리 요청 처리함수
 	@RequestMapping("/gmailCheckAction.mentor")
 	public ModelAndView gmailCheckAction(String code, String id, ModelAndView mv, HttpSession session, RedirectView rv) {

		String mail = mDao.getMail(id);
				
		boolean rightCode = 
				SHA256(mail, "cos").equals(code) ? true : false;
		String view ="redirectView";
		mv.setViewName(view);

		if(rightCode == true){
			System.out.println("인증 성공");
			int cnt = mDao.getIsCnt(id);
			
			if(cnt != 1) {
				System.out.println("인증 실패");			
				mv.addObject("MSG", "메일인증 실패");
				return mv;
			}
			mv.addObject("MSG", "메일인증 성공");
			session.setAttribute("SID", id);
		} else{
			System.out.println("인증 실패");			
			mv.addObject("MSG", "인증 실패");
		} 
		mv.addObject("PATH", "/study/main.mentor");
		return mv;
	}
    
    
    // 복호화 처리 함수
    public String SHA256(String rawPassword, String salt) {
		String result = "";
		byte[] b = (rawPassword + salt).getBytes();	
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(b);			
			byte[] bResult = md.digest();
			
			for (byte data : bResult) {
				System.out.print(data + " ");
			}
			System.out.println();
			
			StringBuffer sb = new StringBuffer();
			for (byte data : bResult) {
				sb.append(Integer.toString(data & 0xff, 16));
			}
			
			result = sb.toString();
			System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
 	
}
