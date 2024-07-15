CREATE DATABASE IF NOT EXISTS Estagio;
USE Estagio;

CREATE TABLE Profissional (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    CPF CHAR(11) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    sexo INT NOT NULL,
    data_nascimento DATE
);

CREATE TABLE Empresa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    CNPJ CHAR(14) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    cidade VARCHAR(100)
);

CREATE TABLE Vaga (
    id INT AUTO_INCREMENT PRIMARY KEY,
    empresa_id INT NOT NULL,
    FOREIGN KEY (empresa_id) REFERENCES Empresa(id),
    descricao TEXT NOT NULL,
    remuneracao DECIMAL(10, 2),
    data_limite_inscricao DATE NOT NULL,
    status INT NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Candidatura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vaga_id INT NOT NULL,
    profissional_id INT NOT NULL,
    arquivo_curriculo MEDIUMBLOB,
    data_candidatura TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status INT NOT NULL DEFAULT 0,
    UNIQUE KEY unique_candidatura (vaga_id, profissional_id),
    FOREIGN KEY (vaga_id) REFERENCES Vaga(id),
    FOREIGN KEY (profissional_id) REFERENCES Profissional(id)
);
