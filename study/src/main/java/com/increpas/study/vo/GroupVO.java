package com.increpas.study.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GroupVO {
	private int no, sno, sbno, nowcnt, maxcnt, mno, click, cnt;
	private String mname, sname, mentorid, id, sid, title, body, loc, sdate, sdate1, sdate2, sdate3;
	private Date wdate, sysdate;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getSbno() {
		return sbno;
	}
	public void setSbno(int sbno) {
		this.sbno = sbno;
	}
	public int getNowcnt() {
		return nowcnt;
	}
	public void setNowcnt(int nowcnt) {
		this.nowcnt = nowcnt;
	}
	public int getMaxcnt() {
		return maxcnt;
	}
	public void setMaxcnt(int maxcnt) {
		this.maxcnt = maxcnt;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getMentorid() {
		return mentorid;
	}
	public void setMentorid(String mentorid) {
		this.mentorid = mentorid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		sdate = form.format(wdate);
	}
	public void setSdate(Date wDate) {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy/MM/dd");
		sdate = form1.format(wdate);
	}
	public String getSdate1() {
		return sdate1;
	}
	public void setSdate1() {
		SimpleDateFormat form = new SimpleDateFormat("MM.dd");
		sdate1 = form.format(wdate);
	}
	public void setSdate1(String sdate1) {
		this.sdate1 = sdate1;
	}
	public String getSdate2() {
		return sdate2;
	}
	public void setSdate2() {
		SimpleDateFormat form = new SimpleDateFormat("HH:mm");
		sdate2 = form.format(wdate);
	}
	public void setSdate2(String sdate2) {
		this.sdate2 = sdate2;
	}
	public String getSdate3() {
		return sdate3;
	}
	public void setSdate3() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy.MM.dd");
		sdate3 = form.format(wdate);
	}
	public void setSdate3(String sdate3) {
		this.sdate3 = sdate3;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
		setSdate();
		setSdate1();
		setSdate2();
		setSdate3();
	}
	public Date getSysdate() {
		return sysdate;
	}
	public void setSysdate(Date sysdate) {
		this.sysdate = sysdate;
	}
	
}
