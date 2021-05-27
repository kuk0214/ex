package com.increpas.study.vo;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class BoardVO {
	private int frbno, frreno, no, qnabno, rvbno, sbno, askno, upno, mtno, click, cnt, groupno;
	private String id, wid, upid, title, body, category, loc, juje, sdate, sdate1;
	private Date wdate, sysdate;
	private Time wtime;
	public int getFrbno() {
		return frbno;
	}
	public void setFrbno(int frbno) {
		this.frbno = frbno;
	}
	public int getFrreno() {
		return frreno;
	}
	public void setFrreno(int frreno) {
		this.frreno = frreno;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getQnabno() {
		return qnabno;
	}
	public void setQnabno(int qnabno) {
		this.qnabno = qnabno;
	}
	public int getRvbno() {
		return rvbno;
	}
	public void setRvbno(int rvbno) {
		this.rvbno = rvbno;
	}
	public int getSbno() {
		return sbno;
	}
	public void setSbno(int sbno) {
		this.sbno = sbno;
	}
	public int getAskno() {
		return askno;
	}
	public void setAskno(int askno) {
		this.askno = askno;
	}
	public int getUpno() {
		return upno;
	}
	public void setUpno(int upno) {
		this.upno = upno;
	}
	public int getMtno() {
		return mtno;
	}
	public void setMtno(int mtno) {
		this.mtno = mtno;
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
	public int getGroupno() {
		return groupno;
	}
	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public String getUpid() {
		return upid;
	}
	public void setUpid(String upid) {
		this.upid = upid;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getJuje() {
		return juje;
	}
	public void setJuje(String juje) {
		this.juje = juje;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate() {
		
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy.MM.dd");
		SimpleDateFormat form2 = new SimpleDateFormat(" HH:mm");
		sdate = form1.format(wdate) + form2.format(wtime);
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
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
	}
	public Time getWtime() {
		return wtime;
	}
	public void setWtime(Time wtime) {
		this.wtime = wtime;
		setSdate();
	}
	
}
