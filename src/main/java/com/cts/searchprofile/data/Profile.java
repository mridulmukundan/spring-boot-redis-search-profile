package com.cts.searchprofile.data;

import java.util.ArrayList;
import java.util.List;

public class Profile {

	private String userId;
	private String associateId;
	private String name;
	private String email;
	private Integer mobile;
	private String updatedDate;

	private List<Skill> skills = new ArrayList<>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAssociateId() {
		return associateId;
	}

	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "Profile [userId=" + userId + ", associateId=" + associateId + ", name=" + name + ", email=" + email
				+ ", mobile=" + mobile + ", updatedDate=" + updatedDate + ", skills=" + skills + "]";
	}

}
