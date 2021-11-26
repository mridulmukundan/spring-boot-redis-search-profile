package com.cts.searchprofile.data;

public class Skill {

	private String name;
	private Integer expertise;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getExpertise() {
		return expertise;
	}

	public void setExpertise(Integer expertise) {
		this.expertise = expertise;
	}

	@Override
	public String toString() {
		return "Skill [name=" + name + ", expertise=" + expertise + "]";
	}

}
