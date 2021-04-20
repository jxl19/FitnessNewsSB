package com.fitnessnews.models;

import java.util.Date;


public class NewsletterSet{
	private Long newsletterID;
	
	private Date datePublished;
	
	private String header;
	
	private String authFirstName;
	
	private String authLastName;
	
	private String content;
	
	private String footer;

	public NewsletterSet(Long newsletterID, Date datePublished, String header, String authFirstName,
			String authLastName, String content, String footer) {
		super();
		this.newsletterID = newsletterID;
		this.datePublished = datePublished;
		this.header = header;
		this.authFirstName = authFirstName;
		this.authLastName = authLastName;
		this.content = content;
		this.footer = footer;
	}

	public Long getNewsletterID() {
		return newsletterID;
	}

	public void setNewsletterID(Long newsletterID) {
		this.newsletterID = newsletterID;
	}

	public Date getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getAuthFirstName() {
		return authFirstName;
	}

	public void setAuthFirstName(String authFirstName) {
		this.authFirstName = authFirstName;
	}

	public String getAuthLastName() {
		return authLastName;
	}

	public void setAuthLastName(String authLastName) {
		this.authLastName = authLastName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authFirstName == null) ? 0 : authFirstName.hashCode());
		result = prime * result + ((authLastName == null) ? 0 : authLastName.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((datePublished == null) ? 0 : datePublished.hashCode());
		result = prime * result + ((footer == null) ? 0 : footer.hashCode());
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		result = prime * result + ((newsletterID == null) ? 0 : newsletterID.hashCode());
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
		NewsletterSet other = (NewsletterSet) obj;
		if (authFirstName == null) {
			if (other.authFirstName != null)
				return false;
		} else if (!authFirstName.equals(other.authFirstName))
			return false;
		if (authLastName == null) {
			if (other.authLastName != null)
				return false;
		} else if (!authLastName.equals(other.authLastName))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (datePublished == null) {
			if (other.datePublished != null)
				return false;
		} else if (!datePublished.equals(other.datePublished))
			return false;
		if (footer == null) {
			if (other.footer != null)
				return false;
		} else if (!footer.equals(other.footer))
			return false;
		if (header == null) {
			if (other.header != null)
				return false;
		} else if (!header.equals(other.header))
			return false;
		if (newsletterID == null) {
			if (other.newsletterID != null)
				return false;
		} else if (!newsletterID.equals(other.newsletterID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NewsletterSet [newsletterID=" + newsletterID + ", datePublished=" + datePublished + ", header=" + header
				+ ", authFirstName=" + authFirstName + ", authLastName=" + authLastName + ", content=" + content
				+ ", footer=" + footer + "]";
	}

	
	
	
}
