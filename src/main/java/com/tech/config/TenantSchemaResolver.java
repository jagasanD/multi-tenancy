package com.tech.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantSchemaResolver implements CurrentTenantIdentifierResolver{
	
//	private String defaultTenant ="DEFAULT_SCHEMA";
	private String defaultTenant ="multiten";// default database setup
	

    @Override
	public String resolveCurrentTenantIdentifier() {
		String t =  TenantContext.getTenantSchema();
		System.out.println("---------current schema changes-----"+t);
		if(t!=null){
			return t;
		} else {
			return defaultTenant;
		}
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}
