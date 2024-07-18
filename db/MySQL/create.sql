CREATE DATABASE IF NOT EXISTS Estagio;
USE Estagio;

CREATE TABLE usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(128) NOT NULL UNIQUE,
    nome VARCHAR(256) NOT NULL,
    senha VARCHAR(128) NOT NULL,
    is_admin TINYINT(1) NOT NULL
);

CREATE TABLE profissional (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    CPF CHAR(11) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    sexo INT NOT NULL,
    data_nascimento DATE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE empresa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    CNPJ CHAR(14) NOT NULL UNIQUE,
    descricao TEXT,
    cidade VARCHAR(100),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE vaga (
    id INT AUTO_INCREMENT PRIMARY KEY,
    empresa_id INT NOT NULL,
    descricao TEXT NOT NULL,
    remuneracao DECIMAL(10, 2),
    data_limite_inscricao DATE NOT NULL,
    status INT NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

CREATE TABLE candidatura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vaga_id INT NOT NULL,
    profissional_id INT NOT NULL,
    arquivo_curriculo VARCHAR(256) NOT NULL,
    data_candidatura TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status INT NOT NULL DEFAULT 0,
    UNIQUE KEY unique_candidatura (vaga_id, profissional_id),
    FOREIGN KEY (vaga_id) REFERENCES vaga(id),
    FOREIGN KEY (profissional_id) REFERENCES profissional(id)
);

INSERT INTO usuario (email, nome, senha, is_admin) VALUES ('teste@gmail.com', 'teste', 'teste', 1);
