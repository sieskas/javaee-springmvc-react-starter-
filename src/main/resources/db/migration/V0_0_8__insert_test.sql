-- CHAINE
SET
IDENTITY_INSERT locations ON;
INSERT INTO locations (id, label, type_id, parent_id)
VALUES (100, 'Head Office', 1, NULL);
-- CHAIN

-- REGION sous Head Office
INSERT INTO locations (id, label, type_id, parent_id)
VALUES (101, 'Region 1', 2, 100);

-- DISTRICT sous Region 1
INSERT INTO locations (id, label, type_id, parent_id)
VALUES (102, 'District A', 3, 101);

-- STORES sous District A
INSERT INTO locations (id, label, type_id, parent_id)
VALUES (103, 'Store A', 4, 102),
       (104, 'Store B', 4, 102);
SET
IDENTITY_INSERT locations OFF;

-- Mise à jour des users pour leur assigner une location racine
UPDATE users
SET root_location_id = 100
WHERE id = 1; -- admin
UPDATE users
SET root_location_id = 103
WHERE id = 2; -- user1
UPDATE users
SET root_location_id = 104
WHERE id = 3;
-- user2

-- STAFF lié aux persons existants
SET
IDENTITY_INSERT staff ON;
INSERT INTO staff (id, person_id, location_id, start_date)
VALUES (1, 2, 103, '2024-01-01'), -- user1 -> Store A
       (2, 3, 104, '2024-01-01'); -- user2 -> Store B
SET
IDENTITY_INSERT staff OFF;

-- HOURS
INSERT INTO location_staff_hours (location_id, work_date, source_id, total_hours)
VALUES (103, '2024-04-15', 1, 8.0), -- Store A
       (104, '2024-04-15', 1, 6.0);
-- Store B

SET IDENTITY_INSERT devices ON;
INSERT INTO devices (id, user_id, name, serial_number, description) VALUES
                                                                        (1, 1, 'Device A', 'SN-A12345', 'Device for Store A'),
                                                                        (2, 1, 'Device B', 'SN-B12345', 'Device for Store B');
SET IDENTITY_INSERT devices OFF;

-- TRAFFIC EVENTS - Si cette table existe, ajoutez SET IDENTITY_INSERT si nécessaire
INSERT INTO traffic_events (device_id, location_id, direction_id, event_timestamp)
VALUES (1, 103, 1, '2024-04-15T08:00:00'), -- Entrée Store A
       (2, 104, 2, '2024-04-15T10:00:00');
-- Sortie Store B

INSERT INTO device_location_assignment (device_id, location_id, assigned_from) VALUES
                                                                                   (1, 103, '2024-01-01'),
                                                                                   (2, 104, '2024-01-01');

-- SALES
SET
IDENTITY_INSERT sales ON;
INSERT INTO sales (id, location_id, sale_datetime, total_amount)
VALUES (1, 103, '2024-04-15T09:00:00', 150.00), -- Store A
       (2, 104, '2024-04-15T10:00:00', 250.00), -- Store B
       (3, 103, '2024-04-15T14:30:00', 75.50); -- Store A, transaction en attente
SET
IDENTITY_INSERT sales OFF;

-- Créer d'abord les transactions requises
SET
IDENTITY_INSERT transactions ON;
INSERT INTO transactions (id, sale_id, transaction_datetime, status_id)
VALUES (1, 1, '2024-04-15T09:00:00', 2), -- COMPLETED (id 2)
       (2, 2, '2024-04-15T10:00:00', 2), -- COMPLETED (id 2)
       (3, 3, '2024-04-15T14:30:00', 1); -- PENDING (id 1)
SET
IDENTITY_INSERT transactions OFF;

-- Puis mettre à jour les payments pour utiliser transaction_id au lieu de sale_id
INSERT INTO payments (transaction_id, payment_type_id, amount)
VALUES (1, 1, 100.00), -- cash
       (1, 2, 50.00),  -- card
       (2, 1, 200.00),
       (2, 2, 50.00),
       (3, 2, 30.00);
-- paiement partiel par carte pour transaction en attente

-- Adresses (inclut latitude / longitude)
SET
IDENTITY_INSERT addresses ON;
INSERT INTO addresses (id, address_line, city, province, postal_code, country, latitude, longitude)
VALUES (1, '1 Main St', 'Montreal', 'QC', 'H2X 1Y4', 'Canada', 45.5017, -73.5673),         -- Head Office
       (2, '10 Rue Nord', 'Quebec', 'QC', 'G1K 4H4', 'Canada', 46.8139, -71.2082),         -- Region 1
       (3, '22 Centre Ave', 'Laval', 'QC', 'H7V 3Z4', 'Canada', 45.6066, -73.7124),        -- District A
       (4, '300 Store A Blvd', 'Longueuil', 'QC', 'J4H 1E7', 'Canada', 45.5312, -73.5181), -- Store A
       (5, '350 Store B Blvd', 'Sherbrooke', 'QC', 'J1H 2E5', 'Canada', 45.4000, -71.9000); -- Store B
SET
IDENTITY_INSERT addresses OFF;

-- Météo horaire : location_id, timestamp, frequency_id = 1 (HOURLY)
INSERT INTO weather (location_id, timestamp, frequency_id, temperature, humidity, wind_speed, weather_code,
                     precipitation)
VALUES
    -- Store A (103)
    (103, '2024-04-15T08:00:00', 1, 15.2, 65, 10.5, 'Clear', 0.0),
    (103, '2024-04-15T09:00:00', 1, 16.0, 60, 12.0, 'Cloudy', 0.1),
    -- Store B (104)
    (104, '2024-04-15T08:00:00', 1, 14.0, 70, 9.0, 'Fog', 0.2),
    (104, '2024-04-15T10:00:00', 1, 17.3, 55, 9.5, 'Rain', 2.5);

-- Création des contacts liés aux locations
SET
IDENTITY_INSERT contacts ON;
INSERT INTO contacts (id, email, address_id)
VALUES (10, 'headoffice@store.com', 1),
       (11, 'region1@store.com', 2),
       (12, 'districtA@store.com', 3),
       (13, 'storeA@store.com', 4),
       (14, 'storeB@store.com', 5);
SET
IDENTITY_INSERT contacts OFF;

-- Mise à jour des locations avec contact_id
UPDATE locations
SET contact_id = 10
WHERE id = 100; -- Head Office
UPDATE locations
SET contact_id = 11
WHERE id = 101; -- Region 1
UPDATE locations
SET contact_id = 12
WHERE id = 102; -- District A
UPDATE locations
SET contact_id = 13
WHERE id = 103; -- Store A
UPDATE locations
SET contact_id = 14
WHERE id = 104; -- Store B