CREATE TABLE tenant(
  uuid int(11) NOT NULL AUTO_INCREMENT,
  tenant_name varchar(45) DEFAULT NULL,
  schema_name varchar(45) DEFAULT NULL,
  created_at timestamp NULL DEFAULT NULL,
  updated_at timestamp NULL DEFAULT NULL,
  PRIMARY KEY (uuid),
  UNIQUE KEY schema_name_UNIQUE (schema_name),
  UNIQUE KEY tenant_name_UNIQUE (tenant_name)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
