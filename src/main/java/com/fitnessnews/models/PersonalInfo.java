package com.fitnessnews.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="userinfo", schema = "fitness")
public class PersonalInfo {
	
	@Id
	@Column(name = "userID")
	private Integer userID;
	
	@Column
	private String fName;
	
	@Column
	private String lName;
	
	@Column
	private Boolean wantsMail;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userID")
	private Users users;
	
	public PersonalInfo() {}

	public PersonalInfo(Integer userID, String fName, String lName, Boolean wantsMail) {
		super();
		this.userID = userID;
		this.fName = fName;
		this.lName = lName;
		this.wantsMail = wantsMail;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public Boolean getWantsMail() {
		return wantsMail;
	}

	public void setWantsMail(Boolean wantsMail) {
		this.wantsMail = wantsMail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		result = prime * result + ((wantsMail == null) ? 0 : wantsMail.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonalInfo other = (PersonalInfo) obj;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		if (wantsMail == null) {
			if (other.wantsMail != null)
				return false;
		} else if (!wantsMail.equals(other.wantsMail))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PersonalInfo [userID=" + userID + ", fName=" + fName + ", lName=" + lName + ", wantsMail=" + wantsMail
				+ "]";
	}

}