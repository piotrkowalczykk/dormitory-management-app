INSERT INTO roles (name)
SELECT 'USER'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'USER');

INSERT INTO roles (name)
SELECT 'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ADMIN');

INSERT INTO users (created_at, date_of_birth, email_verified, email_verification_code_expiry_date, password_reset_code_expiry_date, academy,
                   email, email_verification_code, first_name, gender, last_name, password, password_reset_code)
SELECT null, null, true, null, null, null, 'admin@dormitory.com', null, 'admin', 'MALE', 'admin',
       '$2a$10$7pj6pB5KyynD20aQM5.sGeAq3eUKOxhvz04mTNfjaHeYYAZbeTWf6', null
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'admin@dormitory.com');

INSERT INTO user_roles (user_id, role_id)
SELECT users.id, roles.id
FROM users, roles
WHERE users.email = 'admin@dormitory.com'
  AND (roles.name = 'USER' OR roles.name = 'ADMIN')
  AND NOT EXISTS (
      SELECT 1
      FROM user_roles ur
      WHERE ur.user_id = users.id AND ur.role_id = roles.id
  );