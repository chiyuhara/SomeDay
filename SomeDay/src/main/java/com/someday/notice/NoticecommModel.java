package com.someday.notice;

import java.util.Date;

import oracle.sql.TIMESTAMP;

public class NoticecommModel {
	
	private int idx;
	private int originidx;
	private String writer;
	private String content;
	private Date times;
	

	public Date getTimes() {
		return times;
	}
	public void setTimes(Date times) {
		this.times = times;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getOriginidx() {
		return originidx;
	}
	public void setOriginidx(int originidx) {
		this.originidx = originidx;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
