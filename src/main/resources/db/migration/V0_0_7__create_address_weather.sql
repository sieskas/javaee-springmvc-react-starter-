CREATE TABLE addresses (
                           id BIGINT IDENTITY(1,1) PRIMARY KEY,
                           address_line VARCHAR(255) NOT NULL,
                           city VARCHAR(100),
                           province VARCHAR(100),
                           postal_code VARCHAR(20),
                           country VARCHAR(100),
                           latitude DECIMAL(9,6),
                           longitude DECIMAL(9,6)
);

ALTER TABLE contacts
    ADD address_id BIGINT;

ALTER TABLE contacts
    ADD CONSTRAINT FK_contacts_address
        FOREIGN KEY (address_id) REFERENCES addresses(id);

ALTER TABLE locations
    ADD contact_id BIGINT;

ALTER TABLE locations
    ADD CONSTRAINT FK_locations_contact
        FOREIGN KEY (contact_id) REFERENCES contacts(id);


CREATE TABLE frequencies (
                             id INT IDENTITY(1,1) PRIMARY KEY,
                             code VARCHAR(20) NOT NULL UNIQUE -- e.g., HOURLY, DAILY, MONTHLY
);

INSERT INTO frequencies (code) VALUES
                                   ('HOURLY'),
                                   ('DAILY'),
                                   ('WEEKLY'),
                                   ('MONTHLY'),
                                   ('YEARLY');

CREATE TABLE weather (
                         id BIGINT IDENTITY(1,1) PRIMARY KEY,
                         location_id BIGINT NOT NULL,
                         timestamp DATETIME NOT NULL,
                         frequency_id INT NOT NULL,
                         temperature DECIMAL(5,2),
                         humidity DECIMAL(5,2),
                         wind_speed DECIMAL(5,2),
                         weather_code VARCHAR(50),
                         precipitation DECIMAL(5,2),
                         created_at DATETIME DEFAULT GETDATE(),

                         FOREIGN KEY (location_id) REFERENCES locations(id),
                         FOREIGN KEY (frequency_id) REFERENCES frequencies(id),
                         UNIQUE (location_id, timestamp, frequency_id)
);

