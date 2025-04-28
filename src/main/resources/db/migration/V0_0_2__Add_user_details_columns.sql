-- V0_0_2__Add_user_details_columns.sql pour SQL Server
ALTER TABLE users
    ADD account_non_expired BIT DEFAULT 1,
    account_non_locked BIT DEFAULT 1,
    credentials_non_expired BIT DEFAULT 1,
    enabled BIT DEFAULT 1;

GO

-- Mettre à jour les utilisateurs existants
UPDATE users
SET account_non_expired = 1,
    account_non_locked = 1,
    credentials_non_expired = 1,
    enabled = 1;