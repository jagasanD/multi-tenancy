package com.tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.model.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Integer>{
	
	
	Tenant findBySchemaName(String schemaName);

}
