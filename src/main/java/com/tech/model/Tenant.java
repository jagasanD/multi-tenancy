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
@Table(name="tenant")
public class Tenant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uuid;
	@Column(name="tenant_name")
	private String tenantName;
	@Column(name="schema_name")
	private String schemaName;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name="updated_at")
	private Date updatedAt;
	
	
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
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
