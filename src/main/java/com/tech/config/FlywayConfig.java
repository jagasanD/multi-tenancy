package com.tech.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tech.repository.TenantRepository;

@Configuration
public class FlywayConfig {
	
	 public static String DEFAULT_SCHEMA = "multiten";// default database setup

	    private Logger logger = LoggerFactory.getLogger(getClass());

	   @Bean
	    public Flyway flyway(DataSource dataSource) {
	        logger.info("----------Migrating default flyway  schema------------ ");
	        Flyway flyway = new Flyway();
	        flyway.setBaselineOnMigrate(true);
	        flyway.setLocations("db/migration/default");
	        //flyway.setLocations("com.tech");
	        flyway.setDataSource(dataSource);
	        flyway.setSchemas(DEFAULT_SCHEMA);
	        flyway.migrate();
	        return flyway;
	    }

	    @Bean
	    public Boolean tenantsFlyway(TenantRepository repository, DataSource dataSource){
	    	 logger.info("----------tenantsFlyway default flyway  schema------------ ");
	        repository.findAll().forEach(tenant -> {
	            String schema = tenant.getSchemaName();
	            Flyway flyway = new Flyway();
	            flyway.setBaselineOnMigrate(true);
	            flyway.setLocations("db/migration/tenants");
	          //  flyway.setLocations("com.tech.model");
	            flyway.setDataSource(dataSource);
	            flyway.setSchemas(schema);
	            flyway.migrate();
	        });
	        return true;
	    }

}
