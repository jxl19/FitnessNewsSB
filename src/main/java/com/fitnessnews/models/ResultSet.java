package com.fitnessnews.models;

public class ResultSet {
	
	private Integer userID;
	private String email;
	private Boolean wantsMail;
	
	public ResultSet() {}
	
	public ResultSet(Integer userID, String email, Boolean wantsMail) {
		super();
		this.userID = userID;
		this.email = email;
		this.wantsMail = wantsMail;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		ResultSet other = (ResultSet) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
		return "ResultSet [userID=" + userID + ", email=" + email + ", wantsMail=" + wantsMail + "]";
	}
	
	

}
