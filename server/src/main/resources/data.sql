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
SELECT null, null, true, null, null, 1, 'agh@dormitory.com', null, 'AGH', null, '[Academy]',
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

--DORMITORIES
INSERT INTO dormitories (name, address, phone, academy_id)
SELECT 'Olimp', 'Ul. Rostafińskiego 9', '111 111 111', 1
WHERE NOT EXISTS (SELECT 1 FROM dormitories WHERE name = 'Olimp');

INSERT INTO dormitories (name, address, phone, academy_id)
SELECT 'Kapitol', 'Ul. Budryka 2', '222 222 222', 1
WHERE NOT EXISTS (SELECT 1 FROM dormitories WHERE name = 'Kapitol');

INSERT INTO dormitories (name, address, phone, academy_id)
SELECT 'Odyseja', 'Ul. Tokarskiego 4', '333 333 333', 1
WHERE NOT EXISTS (SELECT 1 FROM dormitories WHERE name = 'Odyseja');

--ROOMS

INSERT INTO rooms (dormitory_id, number, capacity, floor, type)
SELECT 1, '1002A', 2, 10, 'comfort'
WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE number = '1002A');

INSERT INTO rooms (dormitory_id, number, capacity, floor, type)
SELECT 1, '1003B', 2, 10, 'comfort'
WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE number = '1003B');

INSERT INTO rooms (dormitory_id, number, capacity, floor, type)
SELECT 2, '312B', 2, 3, 'comfort'
WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE number = '312B');

--STUDENTS
INSERT INTO students (email, student_number, academy_id, room_id)
SELECT 'peter@student.agh.edu', '#121212', 1, 1
WHERE NOT EXISTS (SELECT 1 FROM students WHERE email = 'peter@student.agh.edu');

INSERT INTO students (email, student_number, academy_id, room_id)
SELECT 'lily@student.agh.edu', '#212121', 1, 2
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
SELECT null, null, true, null, null, 2, 'pk@dormitory.com', null, 'PK', null, '[Academy]',
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

--DORMITORIES
INSERT INTO dormitories (name, address, phone, academy_id)
SELECT 'Bartek', 'Ul. Skarżyńskiego 7,', '126 482 554', 2
WHERE NOT EXISTS (SELECT 1 FROM dormitories WHERE name = 'Bartek');

INSERT INTO dormitories (name, address, phone, academy_id)
SELECT 'Balon', 'Ul. Skarżyńskiego 9,', '126 470 813', 2
WHERE NOT EXISTS (SELECT 1 FROM dormitories WHERE name = 'Balon');

--ROOMS

INSERT INTO rooms (dormitory_id, number, capacity, floor, type)
SELECT 5, '202A', 4, 2, 'standard'
WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE number = '202A');

INSERT INTO rooms (dormitory_id, number, capacity, floor, type)
SELECT 4, '314B', 4, 3, 'standard'
WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE number = '314B');


--STUDENTS
INSERT INTO students (email, student_number, academy_id, room_id)
SELECT 'jake@student.pk.edu', '#313131', 2, 4
WHERE NOT EXISTS (SELECT 1 FROM students WHERE email = 'jake@student.pk.edu');

INSERT INTO students (email, student_number, academy_id, room_id)
SELECT 'emily@student.pk.edu', '#131313', 2, 4
WHERE NOT EXISTS (SELECT 1 FROM students WHERE email = 'emily@student.pk.edu');