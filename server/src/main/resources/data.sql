INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('ADMIN');

INSERT INTO users (created_at, date_of_birth, email_verified, email_verification_code_expiry_date, password_reset_code_expiry_date, academy,
 email, email_verification_code, first_name, gender, last_name, password, password_reset_code)
VALUES (null, null, true, null, null, null, 'admin@dormitory.com', null, 'admin', 'MALE', 'admin', '$2a$10$7pj6pB5KyynD20aQM5.sGeAq3eUKOxhvz04mTNfjaHeYYAZbeTWf6', null);

INSERT INTO user_roles (user_id, role_id) VALUES ('1', '1');
INSERT INTO user_roles (user_id, role_id) VALUES ('1', '2');