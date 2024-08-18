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
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE empresa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    CNPJ CHAR(14) NOT NULL UNIQUE,
    descricao TEXT,
    cidade VARCHAR(100),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE vaga (
    id INT AUTO_INCREMENT PRIMARY KEY,
    empresa_id INT NOT NULL,
    descricao TEXT NOT NULL,
    remuneracao DECIMAL(10, 2),
    data_limite_inscricao DATE NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (empresa_id) REFERENCES empresa(id) ON DELETE CASCADE
);

CREATE TABLE candidatura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vaga_id INT NOT NULL,
    profissional_id INT NOT NULL,
    arquivo_curriculo VARCHAR(256) NOT NULL,
    data_candidatura TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status INT NOT NULL DEFAULT 0,
    UNIQUE KEY unique_candidatura (vaga_id, profissional_id),
    FOREIGN KEY (vaga_id) REFERENCES vaga(id) ON DELETE CASCADE,
    FOREIGN KEY (profissional_id) REFERENCES profissional(id) ON DELETE CASCADE
);

INSERT INTO usuario (email, nome, senha, is_admin) VALUES 
('admin@gmail.com', 'Admin User', 'teste', 1),
('user1@gmail.com', 'Profissional 1', 'teste', 0),
('user2@gmail.com', 'Profissiona 2', 'teste', 0),
('user3@gmail.com', 'Empresa 1', 'teste', 0),
('user4@gmail.com', 'Empresa 2', 'teste', 0);

INSERT INTO profissional (id_usuario, CPF, telefone, sexo, data_nascimento) VALUES
(2, '12345678901', '96555-1234', 1, '1990-01-01'),
(3, '09876543210', '96555-5678', 2, '1992-02-02');

INSERT INTO empresa (id_usuario, CNPJ, descricao, cidade) VALUES
(4, '11222333444455', 'Empresa de Tecnologia', 'São Paulo'),
(5, '22333444555666', 'Consultoria Financeira', 'Rio de Janeiro');

INSERT INTO vaga (empresa_id, descricao, remuneracao, data_limite_inscricao) VALUES
(1, 'Desenvolvedor Java', 5000.00, '2024-08-30'),
(1, 'Analista de Dados', 4500.00, '2024-09-15'),
(2, 'Consultor Financeiro', 6000.00, '2024-08-25');