-- Création des tables pour le module de chat en SQL Server

CREATE TABLE chat_sessions (
                               id BIGINT IDENTITY(1,1) PRIMARY KEY,
                               user_id BIGINT,
                               started_at DATETIME DEFAULT GETDATE(),
                               FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE chat_sender_types (
                                   id INT IDENTITY(1,1) PRIMARY KEY,
                                   name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO chat_sender_types (name) VALUES
                                         ('USER'), ('BOT');

CREATE TABLE chat_messages (
                               id BIGINT IDENTITY(1,1) PRIMARY KEY,
                               session_id BIGINT NOT NULL,
                               sender_type_id INT NOT NULL,     -- USER ou BOT
                               sender_id BIGINT NULL,           -- NULL si BOT, sinon FK vers users
                               content NVARCHAR(MAX) NOT NULL,
                               timestamp DATETIME DEFAULT GETDATE(),
                               FOREIGN KEY (session_id) REFERENCES chat_sessions(id),
                               FOREIGN KEY (sender_id) REFERENCES users(id),
                               FOREIGN KEY (sender_type_id) REFERENCES chat_sender_types(id)
);