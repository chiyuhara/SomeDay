package com.someday.today;

import java.util.Date;

import oracle.sql.TIMESTAMP;

public class TodayModel {
	
	private int idx;
	private int female_idx;
	private int male_idx;
	private int female_like;
	private int male_like;
	private int female_score;
	private int male_score;
	private String female_msg;
	private String male_msg;
	private Date times;
	private int Score;
	
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getFemale_idx() {
		return female_idx;
	}
	public void setFemale_idx(int female_idx) {
		this.female_idx = female_idx;
	}
	public int getMale_idx() {
		return male_idx;
	}
	public void setMale_idx(int male_idx) {
		this.male_idx = male_idx;
	}
	public int getFemale_like() {
		return female_like;
	}
	public void setFemale_like(int female_like) {
		this.female_like = female_like;
	}
	public int getMale_like() {
		return male_like;
	}
	public void setMale_like(int male_like) {
		this.male_like = male_like;
	}
	public int getFemale_score() {
		return female_score;
	}
	public void setFemale_score(int female_score) {
		this.female_score = female_score;
	}
	public int getMale_score() {
		return male_score;
	}
	public void setMale_score(int male_score) {
		this.male_score = male_score;
	}
	public String getFemale_msg() {
		return female_msg;
	}
	public void setFemale_msg(String female_msg) {
		this.female_msg = female_msg;
	}
	public String getMale_msg() {
		return male_msg;
	}
	public void setMale_msg(String male_msg) {
		this.male_msg = male_msg;
	}
	public Date getTimes() {
		return times;
	}
	public void setTimes(Date times) {
		this.times = times;
	}
}
