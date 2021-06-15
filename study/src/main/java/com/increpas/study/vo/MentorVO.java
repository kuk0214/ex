package com.increpas.study.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MentorVO {
	private int no;
	private String name, mtid, subject, title, pr, isShow, sdate, sdate1, sdate2;
	private Date sysdate, wdate;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMtid() {
		return mtid;
	}
	public void setMtid(String mtid) {
		this.mtid = mtid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPr() {
		return pr;
	}
	public void setPr(String pr) {
		this.pr = pr;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		sdate = form.format(wdate);
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getSdate1() {
		return sdate1;
	}
	public void setSdate1() {
		SimpleDateFormat form = new SimpleDateFormat("MM.dd");
		sdate1 = form.format(wdate);
	}
	public String getSdate2() {
		return sdate2;
	}
	public void setSdate2() {
		SimpleDateFormat form = new SimpleDateFormat("HH:mm");
		sdate2 = form.format(wdate);
	}
	public Date getSysdate() {
		return sysdate;
	}
	public void setSysdate(Date sysdate) {
		this.sysdate = sysdate;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
		setSdate();
		setSdate1();
		setSdate2();
	}
	
	
}
