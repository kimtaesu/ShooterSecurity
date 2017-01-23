insert into role (role_id, role_type) values (null, 'ROLE_ADMIN');
insert into role (role_id, role_type) values (null, 'ROLE_USER');

insert into oauth (
  oauth_id, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password, user_name)
  values (null, 1, 1, 1, 1, 'test', 'test1');
insert into user_role (user_id, role_id) values (1, 2);