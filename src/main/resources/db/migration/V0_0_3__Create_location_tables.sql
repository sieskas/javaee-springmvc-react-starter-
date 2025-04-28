-- V0_0_3__Create_location_tables.sql pour SQL Server

CREATE TABLE location_types (
                                id BIGINT IDENTITY(1,1) PRIMARY KEY,
                                name VARCHAR(50) UNIQUE NOT NULL
);

-- Table principale des locations
CREATE TABLE locations (
                           id BIGINT IDENTITY(1,1) PRIMARY KEY,
                           label VARCHAR(255) NOT NULL,
                           type_id BIGINT NOT NULL,
                           parent_id BIGINT NULL,
                           FOREIGN KEY (type_id) REFERENCES location_types(id),
                           FOREIGN KEY (parent_id) REFERENCES locations(id)
);

-- Ajout de la colonne root_location_id à la table users
ALTER TABLE users
    ADD root_location_id BIGINT;

ALTER TABLE users
    ADD CONSTRAINT FK_users_root_location
        FOREIGN KEY (root_location_id) REFERENCES locations(id);

-- Insertion des types de base
SET IDENTITY_INSERT location_types ON;

INSERT INTO location_types (id, name) VALUES
                                          (1, 'ROOT'),
                                          (2, 'COUNTRY'),
                                          (3, 'REGION'),
                                          (4, 'DISTRICT'),
                                          (5, 'STORE');

SET IDENTITY_INSERT location_types OFF;
