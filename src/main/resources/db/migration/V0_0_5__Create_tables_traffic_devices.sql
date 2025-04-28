-- D'abord, créer la table traffic_directions qui est référencée
CREATE TABLE traffic_directions
(
    id   INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

-- Insérer quelques directions de base
INSERT INTO traffic_directions (name)
VALUES ('IN'),
       ('OUT');

-- Ensuite, créer les autres tables
CREATE TABLE devices
(
    id            BIGINT IDENTITY(1,1) PRIMARY KEY,
    user_id       BIGINT              NOT NULL,
    name          VARCHAR(100),
    serial_number VARCHAR(100) UNIQUE NOT NULL,
    description   VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE traffic_events
(
    id              BIGINT IDENTITY(1,1) PRIMARY KEY,
    device_id       BIGINT NOT NULL,
    location_id     BIGINT NOT NULL,
    direction_id    INT    NOT NULL,
    event_timestamp DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (device_id) REFERENCES devices (id),
    FOREIGN KEY (location_id) REFERENCES locations (id),
    FOREIGN KEY (direction_id) REFERENCES traffic_directions (id)
);

CREATE TABLE device_location_assignment
(
    id            BIGINT IDENTITY(1,1) PRIMARY KEY,
    device_id     BIGINT   NOT NULL,
    location_id   BIGINT   NOT NULL,
    assigned_from DATETIME NOT NULL,
    assigned_to   DATETIME NULL,
    FOREIGN KEY (device_id) REFERENCES devices (id),
    FOREIGN KEY (location_id) REFERENCES locations (id)
);