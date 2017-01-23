CREATE TABLE role (
  role_id   BIGINT      NOT NULL AUTO_INCREMENT,
  role_type VARCHAR(10) NOT NULL,
  PRIMARY KEY (role_id)
);

CREATE TABLE oauth (
  oauth_id                   BIGINT       NOT NULL AUTO_INCREMENT,
  is_account_non_expired     BIT          NOT NULL,
  is_account_non_locked      BIT          NOT NULL,
  is_credentials_non_expired BIT          NOT NULL,
  is_enabled                 BIT          NOT NULL,
  password                   VARCHAR(255) NOT NULL,
  user_name                  VARCHAR(255),
  PRIMARY KEY (oauth_id)
);

CREATE TABLE user_role (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, role_id)
);

ALTER TABLE oauth
  ADD CONSTRAINT UK_kghivrmr1fqpfjf26ow64hhj6 UNIQUE (user_name);
ALTER TABLE user_role
  ADD CONSTRAINT FKa68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES role (role_id);
ALTER TABLE user_role
  ADD CONSTRAINT FKht6601gagsges6kbl4totw89b FOREIGN KEY (user_id) REFERENCES oauth (oauth_id);
