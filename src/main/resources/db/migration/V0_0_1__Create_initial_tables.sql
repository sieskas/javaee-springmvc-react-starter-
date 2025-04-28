-- V0_0_1__Create_initial_tables.sql pour SQL Server

CREATE TABLE contacts (
                          id BIGINT IDENTITY(1,1) PRIMARY KEY,
                          email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE persons (
                         id BIGINT IDENTITY(1,1) PRIMARY KEY,
                         first_name VARCHAR(255),
                         last_name VARCHAR(255),
                         contact_id BIGINT,
                         FOREIGN KEY (contact_id) REFERENCES contacts(id)
);

CREATE TABLE roles (
                       id BIGINT IDENTITY(1,1) PRIMARY KEY,
                       name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE users (
                       id BIGINT IDENTITY(1,1) PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       person_id BIGINT,
                       FOREIGN KEY (person_id) REFERENCES persons(id)
);

CREATE TABLE user_roles (
                            user_id BIGINT,
                            role_id BIGINT,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(id),
                            FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Activation de l'insertion avec IDENTITY_INSERT pour forcer l'insertion d'IDs spécifiques
SET IDENTITY_INSERT roles ON;
INSERT INTO roles (id, name) VALUES
                                 (1, 'ROLE_ADMIN'),
                                 (2, 'ROLE_USER');
SET IDENTITY_INSERT roles OFF;

SET IDENTITY_INSERT contacts ON;
INSERT INTO contacts (id, email) VALUES
                                     (1, 'admin@example.com'),
                                     (2, 'user1@example.com'),
                                     (3, 'user2@example.com');
SET IDENTITY_INSERT contacts OFF;

SET IDENTITY_INSERT persons ON;
INSERT INTO persons (id, first_name, last_name, contact_id) VALUES
                                                                (1, 'Admin', 'Admin', 1),
                                                                (2, 'User', 'One', 2),
                                                                (3, 'User', 'Two', 3);
SET IDENTITY_INSERT persons OFF;

SET IDENTITY_INSERT users ON;
INSERT INTO users (id, username, password, person_id) VALUES
                                                          (1, 'admin', '$2a$10$UH7MfCwu/KsUh3xVzOtfzu0yWqDVZdoYm6u9cLd21bFyg9oHEyuom', 1),
                                                          (2, 'user1', '$2a$10$hUreO.z3cjHjXEyabdO6eOJl8DaRTGTpxYn5lJw2x99GPw6JNw/3G', 2),
                                                          (3, 'user2', '$2a$10$8fVj4.4PyZwXeafzkLg8puMsIrMz9csCE0g.02SMsVRbFV4/55xWu', 3);
SET IDENTITY_INSERT users OFF;

-- Attribution des rôles
INSERT INTO user_roles (user_id, role_id) VALUES
                                              (1, 1), -- admin -> ROLE_ADMIN
                                              (1, 2), -- admin -> ROLE_USER
                                              (2, 2), -- user1 -> ROLE_USER
                                              (3, 2); -- user2 -> ROLE_USER
