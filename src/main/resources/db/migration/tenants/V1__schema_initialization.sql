CREATE TABLE message (
  uuid INT NOT NULL AUTO_INCREMENT,
  message VARCHAR(45) NULL,
  createdAt TIMESTAMP NULL,
  updatedAt TIMESTAMP NULL,
  PRIMARY KEY (uuid));
