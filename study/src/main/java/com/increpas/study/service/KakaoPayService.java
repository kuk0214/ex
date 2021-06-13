package com.increpas.study.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.*;
import org.springframework.web.servlet.view.*;

import com.google.gson.Gson;
import com.increpas.study.vo.AjaxVO;

public class KakaoPayService {

	/*
	   카카오페이 결제 처리 함수
	 */
	public String KakaoPay(AjaxVO aVO, HttpSession session, HttpURLConnection con) {
		String sdata ="";
		try {
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "KakaoAK c49fa2b260f4011df1d018ddaa499dc8");
			con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			con.setDoOutput(true);
			OutputStream outSt = con.getOutputStream();
			DataOutputStream dOut = new DataOutputStream(outSt);
			dOut.writeBytes(aVO.toString());
			dOut.close();
//			System.out.println("~~~" + aVO.toString());			
			int result = con.getResponseCode();			
			InputStream inSt;
			if(result == 200) {
				inSt = con.getInputStream();	
				
				InputStreamReader reader = new InputStreamReader(inSt);
				BufferedReader buff = new BufferedReader(reader);
				sdata = buff.readLine();
//				System.out.println(sdata);
			}else {
				inSt = con.getErrorStream();
			}
			return sdata;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  	
    	return sdata;
	}
}
