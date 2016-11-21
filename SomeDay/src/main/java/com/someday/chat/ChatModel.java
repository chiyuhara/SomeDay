package com.someday.chat;

import java.util.Date;

public class ChatModel {
	
	private int to_idx;
	private int from_idx;
	private String content;
	private int couple_idx;
	private String nick;
	private Date times;
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getTo_idx() {
		return to_idx;
	}
	public void setTo_idx(int to_idx) {
		this.to_idx = to_idx;
	}
	public int getFrom_idx() {
		return from_idx;
	}
	public void setFrom_idx(int from_idx) {
		this.from_idx = from_idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCouple_idx() {
		return couple_idx;
	}
	public void setCouple_idx(int couple_idx) {
		this.couple_idx = couple_idx;
	}
	public Date getTimes() {
		return times;
	}
	public void setTimes(Date times) {
		this.times = times;
	}

}
