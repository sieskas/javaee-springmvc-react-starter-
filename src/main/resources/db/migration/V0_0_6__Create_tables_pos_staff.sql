-- Créer d'abord les tables indépendantes
CREATE TABLE schedule_types
(
    id   INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO schedule_types (name)
VALUES ('STAFF'),
       ('DEVICE'),
       ('MAINTENANCE');

CREATE TABLE schedules
(
    id               BIGINT IDENTITY(1,1) PRIMARY KEY,
    work_date        DATE     NOT NULL,
    start_time       DATETIME NOT NULL,
    end_time         DATETIME NOT NULL,
    schedule_type_id INT      NOT NULL,
    description      VARCHAR(255),
    FOREIGN KEY (schedule_type_id) REFERENCES schedule_types (id)
);

CREATE TABLE staff
(
    id          BIGINT IDENTITY(1,1) PRIMARY KEY,
    person_id   BIGINT NOT NULL UNIQUE,
    location_id BIGINT NOT NULL,
    start_date  DATE,
    end_date    DATE,
    FOREIGN KEY (person_id) REFERENCES persons (id),
    FOREIGN KEY (location_id) REFERENCES locations (id)
);

-- Maintenant créer staff_schedules qui dépend de staff et schedules
CREATE TABLE staff_schedules
(
    id          BIGINT IDENTITY(1,1) PRIMARY KEY,
    staff_id    BIGINT NOT NULL,
    schedule_id BIGINT NOT NULL,
    FOREIGN KEY (staff_id) REFERENCES staff (id),
    FOREIGN KEY (schedule_id) REFERENCES schedules (id),
    UNIQUE (staff_id, schedule_id) -- éviter les doublons
);

CREATE TABLE staff_hours_sources
(
    id   INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO staff_hours_sources (name)
VALUES ('SCHEDULE'),
       ('POS'),
       ('MANUAL');

CREATE TABLE location_staff_hours
(
    id          BIGINT IDENTITY(1,1) PRIMARY KEY,
    location_id BIGINT        NOT NULL,
    work_date   DATE          NOT NULL,
    source_id   INT           NOT NULL,
    total_hours DECIMAL(5, 2) NOT NULL,
    created_at  DATETIME DEFAULT GETDATE(),
    UNIQUE (location_id, work_date, source_id),
    FOREIGN KEY (location_id) REFERENCES locations (id),
    FOREIGN KEY (source_id) REFERENCES staff_hours_sources (id)
);

CREATE TABLE sales
(
    id            BIGINT IDENTITY(1,1) PRIMARY KEY,
    location_id   BIGINT         NOT NULL,
    sale_datetime DATETIME DEFAULT GETDATE(),
    total_amount  DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (location_id) REFERENCES locations (id)
);


CREATE TABLE transaction_statuses
(
    id    INT IDENTITY(1,1) PRIMARY KEY,
    code  VARCHAR(50)  NOT NULL UNIQUE
);

-- Insérer les valeurs de base pour les statuts de transaction
INSERT INTO transaction_statuses (code)
VALUES ('PENDING'),
       ('COMPLETED'),
       ('FAILED'),
       ('CANCELLED'),
       ('REFUNDED'),
       ('PARTIALLY_REFUNDED');

-- Définition modifiée de la table transactions avec référence au statut
CREATE TABLE transactions
(
    id                    BIGINT IDENTITY(1,1) PRIMARY KEY,
    sale_id               BIGINT NOT NULL,
    transaction_datetime  DATETIME DEFAULT GETDATE(),
    transaction_reference VARCHAR(100),
    status_id             INT    NOT NULL,
    FOREIGN KEY (sale_id) REFERENCES sales (id),
    FOREIGN KEY (status_id) REFERENCES transaction_statuses (id)
);

CREATE TABLE payment_types
(
    id   INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO payment_types (name)
VALUES ('CASH'),
       ('CARD'),
       ('GIFT');

-- Mise à jour de la table payments pour référencer la table transactions
CREATE TABLE payments
(
    id              BIGINT IDENTITY(1,1) PRIMARY KEY,
    transaction_id  BIGINT         NOT NULL,
    payment_type_id INT            NOT NULL,
    amount          DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (transaction_id) REFERENCES transactions (id),
    FOREIGN KEY (payment_type_id) REFERENCES payment_types (id)
);