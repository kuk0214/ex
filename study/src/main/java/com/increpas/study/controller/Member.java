package com.increpas.study.controller;



import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import org.slf4j.*;

/*
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.mail.javamail.JavaMailSender;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
*/
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.*;

import com.increpas.study.dao.*;
import com.increpas.study.util.PageUtil;
import com.increpas.study.vo.*;



@Controller	
@RequestMapping("/member")	


public class Member {
	@Autowired
	MemberDao mDao;
	@Autowired
	OtoBoardDao oDao;
	/*
	  @Autowired 
	  EmailSender emailSender;
	 */
	
	private static final Logger log1 = LoggerFactory.getLogger(Member.class);
    
	// 로그인 폼보기 요청
	@RequestMapping("/login.mentor")
	public ModelAndView getLogin(HttpSession session, ModelAndView mv, RedirectView rv) {
		if(isLogin(session)) {
			rv.setUrl("/study/main.mentor");
			mv.setView(rv);
		} else {
			String view = "member/login";
			mv.setViewName(view);
		}
		
		return mv;
	}
	
	// 로그인 처리 요청
	@RequestMapping("/loginProc.mentor")
	public ModelAndView loginProc( MemberVO mVO, ModelAndView mv, 
										HttpSession session, RedirectView rv) {
		String view = "/study/main.mentor";
		if(isLogin(session)) {
		} else {

			int cnt = mDao.getLogin(mVO);
			if(cnt == 1) {
				session.setAttribute("SID", mVO.getId());
			} else {
				view = "/study/member/login.mentor";
			}
		}

		rv.setUrl(view); 
		
		mv.setView(rv);

		return mv;
	}
	
	// 아이디 체크 처리 요청
	@RequestMapping(value="/idCheck.mentor", params="id", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> idCheck(String id) {
		int cnt = mDao.getIdCnt(id);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", "NO");
		if(cnt != 1) {
			map.put("result", "OK");
		}
		return map;
	}

	// 비밀번호 체크 처리 요청
	@RequestMapping(value="/pwCheck.mentor", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> pwCheck(MemberVO mVO) {
		int cnt = mDao.getPwCnt(mVO);
	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", "NO");
		if(cnt == 1) {
			map.put("result", "OK");
		}
		return map;
	}
	
	// 로그아웃 처리 요청
	@RequestMapping("/logout.mentor")
	public ModelAndView logout(HttpSession session, ModelAndView mv, RedirectView rv) {
		session.removeAttribute("SID");
		rv.setUrl("/study/main.mentor");
		mv.setView(rv);		
		return mv;
	}
	
	// 회원가입 폼보기 함수
	@RequestMapping("/join.mentor")
	public ModelAndView joinForm( ModelAndView mv, HttpSession session, RedirectView rv) {
		
		if(isLogin(session)) {
			rv.setUrl("/study/");
			mv.setView(rv);
		} 
		
		String view ="member/join";		
		mv.setViewName(view);
		return mv;
	}
	
	// 회원가입 처리 함수
	@RequestMapping("/joinProc.mentor")
	public ModelAndView joinProc( ModelAndView mv, HttpSession session, MemberVO mVO, RedirectView rv) {
		
		if(isLogin(session)) {
			rv.setUrl("/study/main.mentor");
			mv.setView(rv);
			return mv;
		} 
		
		int cnt = mDao.addMember(mVO);
		
		if(cnt == 1) {
			mv.addObject("MSG", "메일 인증 후 로그인하세요");
			mv.addObject("PATH", "/study/main.mentor");
			mv.setViewName("redirectView");
			return mv;
		}else {
			rv.setUrl("/study/member/join.mentor");		
		}
		mv.setView(rv);			
		return mv;
	}
	
	// 회원탈퇴 폼보기 요청
	@RequestMapping("/userDel.mentor")
	public ModelAndView delMember(ModelAndView mv, HttpSession session, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);
			return mv;
		}
		mv.setViewName("member/userDel");	
		return mv;
	}
	
	// 회원탈퇴 처리 요청
	@RequestMapping("/userDelProc.mentor")
	public ModelAndView delMemberProc(String id, ModelAndView mv, HttpSession session, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);
			return mv;
		}
		int cnt = mDao.delUser(id);
		if(cnt != 1) {
			 mv.addObject("MSG", "회원 탈퇴 실패");
			 mv.addObject("PATH", "/study/member/userInfo.mentor");
			 mv.setViewName("redirectView");
			 return mv;
		}		
		session.removeAttribute("SID");
		rv.setUrl("/study/main.mentor");
		mv.setView(rv);	
		return mv;			

	}
	
	// 내정보조회 요청 처리함수
	@RequestMapping("/userInfo.mentor")
	public ModelAndView myInfo(ModelAndView mv, HttpSession session, RedirectView rv) {
		if(isLogin(session)) {
			String sid = (String) session.getAttribute("SID");
			
			MemberVO mVO = mDao.getMyInfo(sid);
			mv.addObject("DATA", mVO);
			mv.setViewName("member/userInfo");
		} else {

			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);	
		}
		return mv;			
	}
	
	// 내 정보 수정 폼보기요청 처리함수
	@RequestMapping("/userInfoEdit.mentor")
	public ModelAndView memberEdit(ModelAndView mv, HttpSession session, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);
			return mv;
		} 
		String sid = (String)session.getAttribute("SID");
		MemberVO mVO = mDao.getMyInfo(sid);
		mv.addObject("DATA", mVO);
		mv.setViewName("member/userInfoEdit");			
		return mv;
		
	}
	
	// 내 정보 수정 처리 요청 처리함수
	@RequestMapping("/userInfoEditProc.mentor")
	public ModelAndView memberEditProc(MemberVO mVO, ModelAndView mv, HttpSession session, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);
			return mv;
		} 
		
		int cnt = mDao.updateInfo(mVO);
		String view ="/study/member/userInfo.mentor";
		if(cnt != 1) {
			  mv.addObject("MSG", "회원 정보 수정 실패");
			  mv.addObject("PATH", view);
			  mv.setViewName("redirectView");
			  return mv;
		}
		rv.setUrl(view);
		mv.setView(rv);
		return mv;
	}
	
	// 결제창 폼보기 요청
	@RequestMapping("/point.mentor")
	public ModelAndView point( ModelAndView mv, HttpSession session, RedirectView rv) {
		if(!isLogin(session)) {
			rv.setUrl("/study/member/login.mentor");
			mv.setView(rv);
			return mv;
		} 
		String sid = (String) session.getAttribute("SID");
		MemberVO mVO = mDao.getMyInfo(sid);
		mv.addObject("DATA", mVO);
		mv.setViewName("member/cash");
		return mv;
	}
	
	// 나의 문의글 폼보기 요청
	@RequestMapping("/myoto.mentor")
	public ModelAndView myoto( PageUtil page, HttpSession session, ModelAndView mv, BoardVO bVO) {
		int nowPage = page.getNowPage();
		if(nowPage == 0) {
			nowPage = 1;
		}
		String sid = (String)session.getAttribute("SID");
		int total = oDao.mytotalCnt(sid);
		page.setPage(nowPage, total, 10, 5);
		bVO.setPage(page);
		bVO.setOption("swid");
		bVO.setKeyword(sid);
		List<BoardVO> list = oDao.getOtoList(bVO);

		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		mv.addObject("option", bVO.getOption());
		return mv;
	}
	
	
	
/*
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
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "KakaoAK c49fa2b260f4011df1d018ddaa499dc8");
			con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			con.setDoOutput(true);
			OutputStream outSt = con.getOutputStream();
			DataOutputStream dOut = new DataOutputStream(outSt);
			dOut.writeBytes(aVO.toString());
			dOut.close();
			System.out.println("~~~" + aVO.toString());			
			int result = con.getResponseCode();			
			InputStream inSt;
			if(result == 200) {
				inSt = con.getInputStream();				
				map.put("result", "OK");
			}else {
				inSt = con.getErrorStream();				
				map.put("result", "NO");
			}
			InputStreamReader reader = new InputStreamReader(inSt);
			BufferedReader buff = new BufferedReader(reader);
			String sdata = buff.readLine();
			System.out.println(sdata);
			Gson gson = new Gson(); 
			map = gson.fromJson(sdata, HashMap.class);
			session.setAttribute("TID", map.get("tid"));
			
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
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "KakaoAK c49fa2b260f4011df1d018ddaa499dc8");
			con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			con.setDoOutput(true);
			
			aVO.setCid("TC0ONETIME");
			aVO.setTid((String)session.getAttribute("TID"));
			aVO.setPartner_user_id((String)session.getAttribute("SID"));
			aVO.setTotal_amount(1);
			OutputStream outSt = con.getOutputStream();
			DataOutputStream dOut = new DataOutputStream(outSt);
			dOut.writeBytes(aVO.toString());
			dOut.close();
			System.out.println(aVO.toString());
			
			int result = con.getResponseCode();			
			InputStream inSt;	
			if(result == 200) {
				inSt = con.getInputStream();
				InputStreamReader reader = new InputStreamReader(inSt);
				BufferedReader buff = new BufferedReader(reader);
//				System.out.println(buff.readLine());
				String sdata = buff.readLine();
//				Gson gson = new Gson(); 
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
				System.out.println("sdata : \n\t" + sdata);
				KakaoPayVO kVO = gson.fromJson(sdata, KakaoPayVO.class);
				System.out.println("#############################################################");
				System.out.println(kVO);

				mv.addObject("INFO", kVO);
			}else {
				inSt = con.getErrorStream();
				System.out.println("2222");
			}
			mv.setViewName("member/susses");
			return mv;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mv.setViewName("main");
		return mv;		
	}
*/
	
/*
	//메일 인증 보내기 요청 처리함수
    @RequestMapping(value="/gmailSendAction.mentor", method=RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> sendEmailAction (String mail, String id){
 
        EmailVO email = new EmailVO();
        
        String host = "http://localhost/study/member/";
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
		if(isLogin(session)) {
			rv.setUrl("/study/main.mentor");
			mv.setView(rv);
			return mv;
		} 

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
*/
	
	// 로그인 검사
	public boolean isLogin(HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		
		return (sid == null) ? false : true;
	}
}
