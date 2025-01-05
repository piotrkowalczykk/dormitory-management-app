-- ROLES

INSERT INTO roles (name)
SELECT 'USER'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'USER');

INSERT INTO roles (name)
SELECT 'CUSTOMER'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'CUSTOMER');

INSERT INTO roles (name)
SELECT 'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ADMIN');

-- ADMIN ACCOUNT

INSERT INTO users (created_at, date_of_birth, email_verified, email_verification_code_expiry_date, password_reset_code_expiry_date, academy_id,
                   email, email_verification_code, first_name, gender, last_name, password, password_reset_code)
SELECT null, null, true, null, null, null, 'admin@dormitory.com', null, 'admin', null, null, '$2a$10$7pj6pB5KyynD20aQM5.sGeAq3eUKOxhvz04mTNfjaHeYYAZbeTWf6', null
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

-----------------
-- CUSTOMER (AGH)
-----------------

--INFO
INSERT INTO academies (address, city, country, description, email, logo, name, phone, postal_code, website)
SELECT null, null, null, null, 'agh@dormitory.com', null, 'agh', null, null, null
WHERE NOT EXISTS (SELECT 1 FROM academies WHERE email = 'agh@dormitory.com');

--ACCOUNT
INSERT INTO users (created_at, date_of_birth, email_verified, email_verification_code_expiry_date, password_reset_code_expiry_date, academy_id,
                   email, email_verification_code, first_name, gender, last_name, password, password_reset_code)
SELECT null, null, true, null, null, 1, 'agh@dormitory.com', null, 'agh', null, null,
       '$2a$10$hBrh.x5Hl6YV5LMfYLI9tu6nkslLk2Jv2Pts2It3S1xHXl/O4.q0y', null
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'agh@dormitory.com');

--ROLES
INSERT INTO user_roles (user_id, role_id)
SELECT users.id, roles.id
FROM users, roles
WHERE users.email = 'agh@dormitory.com'
  AND (roles.name = 'USER' OR roles.name = 'CUSTOMER')
  AND NOT EXISTS (
      SELECT 1
      FROM user_roles ur
      WHERE ur.user_id = users.id AND ur.role_id = roles.id
  );

--STUDENTS
INSERT INTO students (email, student_number, academy_id)
SELECT 'peter@student.agh.edu', '#121212', 1
WHERE NOT EXISTS (SELECT 1 FROM students WHERE email = 'peter@student.agh.edu');

INSERT INTO students (email, student_number, academy_id)
SELECT 'lily@student.agh.edu', '#212121', 1
WHERE NOT EXISTS (SELECT 1 FROM students WHERE email = 'lily@student.agh.edu');

-----------------
-- CUSTOMER (PK)
-----------------

--INFO
INSERT INTO academies (address, city, country, description, email, logo, name, phone, postal_code, website)
SELECT null, null, null, null, 'pk@dormitory.com', null, 'pk', null, null, null
WHERE NOT EXISTS (SELECT 1 FROM academies WHERE email = 'pk@dormitory.com');

--ACCOUNT
INSERT INTO users (created_at, date_of_birth, email_verified, email_verification_code_expiry_date, password_reset_code_expiry_date, academy_id,
                   email, email_verification_code, first_name, gender, last_name, password, password_reset_code)
SELECT null, null, true, null, null, 2, 'pk@dormitory.com', null, 'pk', null, null,
       '$2a$10$zqIHCI6sq74IpOS9R2RCEeTIJnMc9Cmq5sCKy.pXeuavCO5uwBd1.', null
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'pk@dormitory.com');

--ROLES
INSERT INTO user_roles (user_id, role_id)
SELECT users.id, roles.id
FROM users, roles
WHERE users.email = 'pk@dormitory.com'
  AND (roles.name = 'USER' OR roles.name = 'CUSTOMER')
  AND NOT EXISTS (
      SELECT 1
      FROM user_roles ur
      WHERE ur.user_id = users.id AND ur.role_id = roles.id
  );

--STUDENTS
INSERT INTO students (email, student_number, academy_id)
SELECT 'jake@student.pk.edu', '#313131', 2
WHERE NOT EXISTS (SELECT 1 FROM students WHERE email = 'jake@student.pk.edu');

INSERT INTO students (email, student_number, academy_id)
SELECT 'emily@student.pk.edu', '#131313', 2
WHERE NOT EXISTS (SELECT 1 FROM students WHERE email = 'emily@student.pk.edu');