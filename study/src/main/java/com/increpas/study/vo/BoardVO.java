package com.increpas.study.vo;

import java.util.Date;

import com.increpas.study.util.PageUtil;

import java.text.*;

public class BoardVO {
	private int frbno, frreno, no, qnabno, rvbno, sbno, askno, upno, mtno, click, cnt, groupno;
	private String id, wid, upid, title, body, category, loc, sdate, sdate1, sdate2, process, option, keyword;
	private Date wdate, sysdate;
	private PageUtil page;
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
	public String getSdate() {
		return sdate;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setSdate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		sdate = form.format(wdate);
	}
	public void setSdate(Date wdate) {
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
	public PageUtil getPage() {
		return page;
	}
	public void setPage(PageUtil page) {
		this.page = page;
	}
}
