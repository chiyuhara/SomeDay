package com.someday.meeting;

import java.util.Date;

public class MeetModel {
	
	private int idx;
	private int female_idx;
	private int male_idx;
	private int female_like;
	private int male_like;
	
	private String f_name;
	private String f_pic;
	private String m_name;
	private String m_pic;
	
	private Date times;

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

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getF_pic() {
		return f_pic;
	}

	public void setF_pic(String f_pic) {
		this.f_pic = f_pic;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_pic() {
		return m_pic;
	}

	public void setM_pic(String m_pic) {
		this.m_pic = m_pic;
	}

	public Date getTimes() {
		return times;
	}

	public void setTimes(Date times) {
		this.times = times;
	}
	
	
}
