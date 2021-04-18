package com.fitnessnews.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "login", schema = "fitness")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userID")
	private Integer userID;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "superUser")
	private Boolean superUser;
	
	@Column(name = "resetPasswordToken")
	private String resetPasswordToken;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "users", fetch=FetchType.EAGER)
	private PersonalInfo personalInfo;
	
	public Users() {}

	public Users(Integer userID, String email, String password, Boolean superUser, String resetPasswordToken,
			PersonalInfo personalInfo) {
		super();
		this.userID = userID;
		this.email = email;
		this.password = password;
		this.superUser = superUser;
		this.resetPasswordToken = resetPasswordToken;
		this.personalInfo = personalInfo;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getSuperUser() {
		return superUser;
	}

	public void setSuperUser(Boolean superUser) {
		this.superUser = superUser;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((personalInfo == null) ? 0 : personalInfo.hashCode());
		result = prime * result + ((resetPasswordToken == null) ? 0 : resetPasswordToken.hashCode());
		result = prime * result + ((superUser == null) ? 0 : superUser.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
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
		Users other = (Users) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (personalInfo == null) {
			if (other.personalInfo != null)
				return false;
		} else if (!personalInfo.equals(other.personalInfo))
			return false;
		if (resetPasswordToken == null) {
			if (other.resetPasswordToken != null)
				return false;
		} else if (!resetPasswordToken.equals(other.resetPasswordToken))
			return false;
		if (superUser == null) {
			if (other.superUser != null)
				return false;
		} else if (!superUser.equals(other.superUser))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Users [userID=" + userID + ", email=" + email + ", password=" + password + ", superUser=" + superUser
				+ ", resetPasswordToken=" + resetPasswordToken + ", personalInfo=" + personalInfo + "]";
	}

	
	
}