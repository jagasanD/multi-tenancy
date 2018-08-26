package com.tech.controller;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tech.model.Tenant;
import com.tech.repository.TenantRepository;

@RestController
@RequestMapping(value = "/tenants")
@CrossOrigin
public class TenantController {
	@Autowired
	private TenantRepository repository;

	@Autowired
	private DataSource dataSource;

	/*
	 * public TenantController(TenantRepository repository, DataSource dataSource) {
	 * this.repository = repository; this.dataSource = dataSource; }
	 */

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public Tenant createTenant(@RequestBody Tenant tenant) {
		tenant = repository.save(tenant);
		String schema = tenant.getSchemaName();
		if (schema == null)
			throw new RuntimeException("schema is null");
		Flyway flyway = new Flyway();
		flyway.setLocations("db/migration/tenants");
		//flyway.setLocations("com.tech.model");
		flyway.setDataSource(dataSource);
		
		flyway.setSchemas(schema);
		flyway.migrate();
		//flyway.setL
		return tenant;
	}

	@DeleteMapping("/delete/{uuid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTenant(@RequestParam Integer uuid) {
		repository.deleteById(uuid);
	}

	@GetMapping
	public Page<Tenant> getTenants(Pageable pageable) {
		return repository.findAll(pageable);
	}
}
