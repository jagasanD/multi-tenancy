package com.tech.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TenantConnectionProvider implements MultiTenantConnectionProvider {
	
	private static final long serialVersionUID = 1348353870772468815L;
	private static Logger logger = LoggerFactory.getLogger(TenantConnectionProvider.class);
	private String DEFAULT_TENANT = FlywayConfig.DEFAULT_SCHEMA;
	private DataSource datasource;
	
	public TenantConnectionProvider(DataSource dataSource) {
		this.datasource = dataSource;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		return datasource.getConnection();
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		String DEFAULT_TENANT_ID="multiten";
		connection.createStatement().execute( "USE " + DEFAULT_TENANT_ID );
		connection.close();
	}

	@Override
	public Connection getConnection(String tenantIdentifier)
			throws SQLException {
		String DEFAULT_TENANT_ID="multiten";
		logger.debug("Get connection for tenant-----------1 "+tenantIdentifier);
		 Connection connection = getAnyConnection();
		 if(tenantIdentifier!=null)
		 connection.setSchema(tenantIdentifier);
		logger.debug("get   schema -----------2 "+connection.getSchema());
		logger.debug("get   metadata -----------3 "+connection.getMetaData().getURL());
		//logger.debug("get   metadata -----------3 "+connection.getM);
		
		 try {
	            if (tenantIdentifier != null) {
	                connection.createStatement().execute("USE " + tenantIdentifier);
	            } else {
	                connection.createStatement().execute("USE " + DEFAULT_TENANT_ID);
	            }
	        }
	        catch ( SQLException e ) {
	            e.printStackTrace();
	        }
		
		
		return connection;
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection)
			throws SQLException {
		logger.debug("Release connection for tenant-----", tenantIdentifier);
		connection.setSchema(DEFAULT_TENANT);
		releaseAnyConnection(connection);
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isUnwrappableAs(Class unwrapType) {
		return false;

	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		return null;
	}

}
