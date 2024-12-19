
-- Create db and table
CREATE DATABASE IF NOT EXISTS rubrica_db;
USE rubrica_db;

CREATE TABLE contatti (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    indirizzo VARCHAR(100),
    telefono VARCHAR(20) NOT NULL,
    eta INT
);