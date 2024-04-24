CREATE DATABASE IF NOT EXISTS desafiodb;

\c desafiodb;

CREATE TABLE IF NOT EXISTS profissionais (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cargo VARCHAR(255),
    nascimento DATE,
    created_date DATE
);

CREATE TABLE IF NOT EXISTS contatos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    contato VARCHAR(255) NOT NULL,
    created_date DATE,
    profissional_id INT,
    FOREIGN KEY (profissional_id) REFERENCES profissionais(id)
);
