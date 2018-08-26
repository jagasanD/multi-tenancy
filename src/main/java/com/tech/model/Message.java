package com.tech.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="message")
public class Message {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uuid;

	private String message;

	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name="createdAt")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name="updatedAt")
	private Date updatedAt;

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}


