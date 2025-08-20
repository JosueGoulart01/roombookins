create database RoomBookings;
use RoomBookings;
create table Usuario(
cpf char(14) primary key,
nome char(30),
cep char(10),
rua char(40),
numero char(10),
cidade char(30),
pais char(30),
corporativo boolean
);
INSERT INTO Usuario VALUES ('123.456.789-00', 'Alice Souza', '30110-020', 'Rua A', '100', 'Belo Horizonte', 'Brasil', TRUE);
INSERT INTO Usuario VALUES ('234.567.890-11', 'Bruno Lima', '30120-030', 'Rua B', '200', 'São Paulo', 'Brasil', FALSE);
INSERT INTO Usuario VALUES ('345.678.901-22', 'Camila Rocha', '30130-040', 'Rua C', '300', 'Rio de Janeiro', 'Brasil', TRUE);
INSERT INTO Usuario VALUES ('456.789.012-33', 'Diego Freitas', '30140-050', 'Rua D', '400', 'Curitiba', 'Brasil', FALSE);
INSERT INTO Usuario VALUES ('567.890.123-44', 'Eduarda Martins', '30150-060', 'Rua E', '500', 'Salvador', 'Brasil', TRUE);
INSERT INTO Usuario VALUES ('678.901.234-55', 'Felipe Araújo', '30160-070', 'Rua F', '600', 'Fortaleza', 'Brasil', FALSE);
INSERT INTO Usuario VALUES ('789.012.345-66', 'Gabriela Costa', '30170-080', 'Rua G', '700', 'Recife', 'Brasil', TRUE);
INSERT INTO Usuario VALUES ('890.123.456-77', 'Henrique Melo', '30180-090', 'Rua H', '800', 'Manaus', 'Brasil', FALSE);
INSERT INTO Usuario VALUES ('901.234.567-88', 'Isabela Nunes', '30190-100', 'Rua I', '900', 'Porto Alegre', 'Brasil', TRUE);
INSERT INTO Usuario VALUES ('012.345.678-99', 'João Pedro', '30200-110', 'Rua J', '1000', 'Florianópolis', 'Brasil', FALSE);
INSERT INTO Usuario VALUES ('111.222.333-44', 'Karina Almeida', '30210-120', 'Rua K', '1100', 'Vitória', 'Brasil', TRUE);
INSERT INTO Usuario VALUES ('222.333.444-55', 'Leonardo Pires', '30220-130', 'Rua L', '1200', 'Campo Grande', 'Brasil', FALSE);
INSERT INTO Usuario VALUES ('333.444.555-66', 'Marina Duarte', '30230-140', 'Rua M', '1300', 'Maceió', 'Brasil', TRUE);
INSERT INTO Usuario VALUES ('444.555.666-77', 'Nicolas Ribeiro', '30240-150', 'Rua N', '1400', 'João Pessoa', 'Brasil', FALSE);
INSERT INTO Usuario VALUES ('555.666.777-88', 'Olívia Fernandes', '30250-160', 'Rua O', '1500', 'Teresina', 'Brasil', TRUE);
INSERT INTO Usuario VALUES ('666.777.888-99', 'Paulo Henrique', '30260-170', 'Rua P', '1600', 'Natal', 'Brasil', FALSE);
INSERT INTO Usuario VALUES ('777.888.999-00', 'Queila Moreira', '30270-180', 'Rua Q', '1700', 'Aracaju', 'Brasil', TRUE);
INSERT INTO Usuario VALUES ('888.999.000-11', 'Rodrigo Leal', '30280-190', 'Rua R', '1800', 'Belém', 'Brasil', FALSE);
INSERT INTO Usuario VALUES ('999.000.111-22', 'Sabrina Teixeira', '30290-200', 'Rua S', '1900', 'Palmas', 'Brasil', TRUE);
INSERT INTO Usuario VALUES ('000.111.222-33', 'Thiago Castro', '30300-210', 'Rua T', '2000', 'Macapá', 'Brasil', FALSE);


create table TipoSala(
tipo int primary key,
nome char(30)
);
INSERT INTO TipoSala (tipo, nome) VALUES (1, 'Premium');
INSERT INTO TipoSala (tipo, nome) VALUES (2, 'Vip');
INSERT INTO TipoSala (tipo, nome) VALUES (3, 'Standard');


create table Sala(
codigoSala char(6) primary key,
capacidade int,
cep char(10),
rua char(40),
numero char(10),
cidade char(30),
pais char(30),
tipo int,
foreign key (tipo) references TipoSala(tipo)
);
INSERT INTO Sala VALUES ('PRE001', 100, '30110-001', 'Rua das Flores', '101', 'Belo Horizonte', 'Brasil', 1);
INSERT INTO Sala VALUES ('VIP002', 80, '30120-002', 'Rua dos Lírios', '202', 'São Paulo', 'Brasil', 2);
INSERT INTO Sala VALUES ('STD003', 60, '30130-003', 'Rua das Acácias', '303', 'Rio de Janeiro', 'Brasil', 3);
INSERT INTO Sala VALUES ('PRE004', 120, '30140-004', 'Rua das Palmeiras', '404', 'Curitiba', 'Brasil', 1);
INSERT INTO Sala VALUES ('VIP005', 90, '30150-005', 'Rua das Hortênsias', '505', 'Salvador', 'Brasil', 2);
INSERT INTO Sala VALUES ('STD006', 70, '30160-006', 'Rua dos Ipês', '606', 'Fortaleza', 'Brasil', 3);
INSERT INTO Sala VALUES ('PRE007', 110, '30170-007', 'Rua das Oliveiras', '707', 'Recife', 'Brasil', 1);
INSERT INTO Sala VALUES ('VIP008', 85, '30180-008', 'Rua dos Cedros', '808', 'Manaus', 'Brasil', 2);
INSERT INTO Sala VALUES ('STD009', 65, '30190-009', 'Rua das Orquídeas', '909', 'Porto Alegre', 'Brasil', 3);
INSERT INTO Sala VALUES ('PRE010', 130, '30200-010', 'Rua das Rosas', '010', 'Florianópolis', 'Brasil', 1);
INSERT INTO Sala VALUES ('VIP011', 95, '30210-011', 'Rua das Violetas', '111', 'Vitória', 'Brasil', 2);
INSERT INTO Sala VALUES ('STD012', 75, '30220-012', 'Rua dos Girassóis', '212', 'Campo Grande', 'Brasil', 3);
INSERT INTO Sala VALUES ('PRE013', 115, '30230-013', 'Rua das Magnólias', '313', 'Maceió', 'Brasil', 1);
INSERT INTO Sala VALUES ('VIP014', 88, '30240-014', 'Rua dos Jacarandás', '414', 'João Pessoa', 'Brasil', 2);
INSERT INTO Sala VALUES ('STD015', 68, '30250-015', 'Rua das Bromélias', '515', 'Teresina', 'Brasil', 3);
INSERT INTO Sala VALUES ('PRE016', 105, '30260-016', 'Rua das Cerejeiras', '616', 'Natal', 'Brasil', 1);
INSERT INTO Sala VALUES ('VIP017', 92, '30270-017', 'Rua dos Flamboyants', '717', 'Aracaju', 'Brasil', 2);
INSERT INTO Sala VALUES ('STD018', 72, '30280-018', 'Rua dos Manacás', '818', 'Belém', 'Brasil', 3);
INSERT INTO Sala VALUES ('PRE019', 125, '30290-019', 'Rua das Camélias', '919', 'Palmas', 'Brasil', 1);
INSERT INTO Sala VALUES ('VIP020', 78, '30300-020', 'Rua dos Cravos', '020', 'Macapá', 'Brasil', 2);


create table Recurso(
id_Recurso int auto_increment primary key,
nome char(50),
categoria char(50)
);
INSERT INTO Recurso (nome, categoria) VALUES ('Ar-condicionado', 'Conforto');
INSERT INTO Recurso (nome, categoria) VALUES ('Poltronas VIP', 'Conforto');
INSERT INTO Recurso (nome, categoria) VALUES ('Sistema de Som', 'Tecnologia');
INSERT INTO Recurso (nome, categoria) VALUES ('Projetor 4K', 'Tecnologia');
INSERT INTO Recurso (nome, categoria) VALUES ('Tela Retrátil', 'Tecnologia');
INSERT INTO Recurso (nome, categoria) VALUES ('Lousa Interativa', 'Educação');
INSERT INTO Recurso (nome, categoria) VALUES ('Microfone Sem Fio', 'Tecnologia');
INSERT INTO Recurso (nome, categoria) VALUES ('Iluminação LED', 'Conforto');
INSERT INTO Recurso (nome, categoria) VALUES ('Mesa de Som', 'Tecnologia');
INSERT INTO Recurso (nome, categoria) VALUES ('Cadeiras Ergonômicas', 'Conforto');
INSERT INTO Recurso (nome, categoria) VALUES ('Internet Wi-Fi', 'Tecnologia');
INSERT INTO Recurso (nome, categoria) VALUES ('Painel Solar', 'Sustentabilidade');
INSERT INTO Recurso (nome, categoria) VALUES ('Filtro de Ar', 'Conforto');
INSERT INTO Recurso (nome, categoria) VALUES ('Sistema de Videoconferência', 'Tecnologia');
INSERT INTO Recurso (nome, categoria) VALUES ('Refrigeração Silenciosa', 'Conforto');
INSERT INTO Recurso (nome, categoria) VALUES ('Espaço para Coffee Break', 'Serviços');
INSERT INTO Recurso (nome, categoria) VALUES ('Mesa Redonda para Reuniões', 'Conforto');
INSERT INTO Recurso (nome, categoria) VALUES ('Cortina Blackout', 'Conforto');
INSERT INTO Recurso (nome, categoria) VALUES ('Controle de Iluminação por App', 'Tecnologia');
INSERT INTO Recurso (nome, categoria) VALUES ('Tomadas USB nas cadeiras', 'Tecnologia');


create table RecursoSala(
id_recursoSala int auto_increment primary key,
codigoSala char(6),
id_Recurso int,
foreign key (codigoSala) references Sala(codigoSala),
foreign key (id_Recurso) references Recurso(id_Recurso)
);
-- Premium (tipo 1): Projetor 4K (4) e Ar-condicionado (1)
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('PRE001', 1);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('PRE001', 4);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('PRE004', 1);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('PRE004', 4);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('PRE007', 1);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('PRE007', 4);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('PRE010', 1);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('PRE010', 4);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('PRE013', 1);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('PRE013', 4);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('PRE016', 1);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('PRE016', 4);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('PRE019', 1);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('PRE019', 4);

-- Vip (tipo 2): Todos os recursos (1 a 20)
-- Exemplo para VIP002
INSERT INTO RecursoSala (codigoSala, id_Recurso)
SELECT 'VIP002', id_Recurso FROM Recurso;
-- Repetir para os demais VIPs:
INSERT INTO RecursoSala (codigoSala, id_Recurso) SELECT 'VIP005', id_Recurso FROM Recurso;
INSERT INTO RecursoSala (codigoSala, id_Recurso) SELECT 'VIP008', id_Recurso FROM Recurso;
INSERT INTO RecursoSala (codigoSala, id_Recurso) SELECT 'VIP011', id_Recurso FROM Recurso;
INSERT INTO RecursoSala (codigoSala, id_Recurso) SELECT 'VIP014', id_Recurso FROM Recurso;
INSERT INTO RecursoSala (codigoSala, id_Recurso) SELECT 'VIP017', id_Recurso FROM Recurso;
INSERT INTO RecursoSala (codigoSala, id_Recurso) SELECT 'VIP020', id_Recurso FROM Recurso;

-- Standard (tipo 3): Básicos: Cadeiras (10), Wi-Fi (11)
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('STD003', 10);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('STD003', 11);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('STD006', 10);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('STD006', 11);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('STD009', 10);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('STD009', 11);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('STD012', 10);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('STD012', 11);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('STD015', 10);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('STD015', 11);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('STD018', 10);
INSERT INTO RecursoSala (codigoSala, id_Recurso) VALUES ('STD018', 11);

create table Reserva(
id int auto_increment primary key,
dataInicio datetime,
dataFim datetime,
cpf char(14),
codigoSala char(6),
foreign key (cpf) references Usuario (cpf),
foreign key (codigoSala) references Sala (codigoSala)
);
INSERT INTO Reserva VALUES (1,  '2025-06-10 08:00:00', '2025-06-10 10:00:00', '123.456.789-00', 'PRE001');
INSERT INTO Reserva VALUES (2,  '2025-06-11 14:00:00', '2025-06-11 16:00:00', '234.567.890-11', 'VIP002');
INSERT INTO Reserva VALUES (3,  '2025-06-12 09:00:00', '2025-06-12 11:00:00', '345.678.901-22', 'STD003');
INSERT INTO Reserva VALUES (4,  '2025-06-13 13:00:00', '2025-06-13 15:00:00', '456.789.012-33', 'PRE004');
INSERT INTO Reserva VALUES (5,  '2025-06-14 10:00:00', '2025-06-14 12:00:00', '567.890.123-44', 'VIP005');
INSERT INTO Reserva VALUES (6,  '2025-06-15 08:30:00', '2025-06-15 10:30:00', '678.901.234-55', 'STD006');
INSERT INTO Reserva VALUES (7,  '2025-06-16 17:00:00', '2025-06-16 19:00:00', '789.012.345-66', 'PRE007');
INSERT INTO Reserva VALUES (8,  '2025-06-17 09:30:00', '2025-06-17 11:30:00', '890.123.456-77', 'VIP008');
INSERT INTO Reserva VALUES (9,  '2025-06-18 15:00:00', '2025-06-18 17:00:00', '901.234.567-88', 'STD009');
INSERT INTO Reserva VALUES (10, '2025-06-19 08:00:00', '2025-06-19 10:00:00', '012.345.678-99', 'PRE010');
INSERT INTO Reserva VALUES (11, '2025-06-20 13:00:00', '2025-06-20 15:00:00', '111.222.333-44', 'VIP011');
INSERT INTO Reserva VALUES (12, '2025-06-21 14:30:00', '2025-06-21 16:30:00', '222.333.444-55', 'STD012');
INSERT INTO Reserva VALUES (13, '2025-06-22 09:00:00', '2025-06-22 11:00:00', '333.444.555-66', 'PRE013');
INSERT INTO Reserva VALUES (14, '2025-06-23 10:00:00', '2025-06-23 12:00:00', '444.555.666-77', 'VIP014');
INSERT INTO Reserva VALUES (15, '2025-06-24 16:00:00', '2025-06-24 18:00:00', '555.666.777-88', 'STD015');
INSERT INTO Reserva VALUES (16, '2025-06-25 11:00:00', '2025-06-25 13:00:00', '666.777.888-99', 'PRE016');
INSERT INTO Reserva VALUES (17, '2025-06-26 14:00:00', '2025-06-26 16:00:00', '777.888.999-00', 'VIP017');
INSERT INTO Reserva VALUES (18, '2025-06-27 09:00:00', '2025-06-27 11:00:00', '888.999.000-11', 'STD018');
INSERT INTO Reserva VALUES (19, '2025-06-28 15:30:00', '2025-06-28 17:30:00', '999.000.111-22', 'PRE019');
INSERT INTO Reserva VALUES (20, '2025-06-29 12:00:00', '2025-06-29 14:00:00', '000.111.222-33', 'VIP020');

-- 1. Listar todas as reservas com nome do usuário e tipo da sala
SELECT r.id, u.nome AS usuario, ts.nome AS tipoSala, r.dataInicio, r.dataFim
FROM Reserva r
JOIN Usuario u ON r.cpf = u.cpf
JOIN Sala s ON r.codigoSala = s.codigoSala
JOIN TipoSala ts ON s.tipo = ts.tipo;

-- 2. Ver todas as salas e seus respectivos tipos
SELECT s.codigoSala, s.capacidade, ts.nome AS tipoSala
FROM Sala s
JOIN TipoSala ts ON s.tipo = ts.tipo;

-- 3. Reservas realizadas entre 15 e 25 de junho de 2025
SELECT * 
FROM Reserva
WHERE dataInicio BETWEEN '2025-06-15' AND '2025-06-25';

-- 4. Contar quantas reservas cada sala recebeu
SELECT codigoSala, COUNT(*) AS totalReservas
FROM Reserva
GROUP BY codigoSala;

-- 5. Listar nomes e CPFs de usuários que reservaram salas do tipo Premium
SELECT DISTINCT u.nome, u.cpf
FROM Usuario u
JOIN Reserva r ON u.cpf = r.cpf
JOIN Sala s ON r.codigoSala = s.codigoSala
WHERE s.tipo = 1;

-- 6. Listar salas com seus respectivos recursos cadastrados
SELECT s.codigoSala, r.nome AS recurso
FROM Sala s
JOIN RecursoSala rs ON s.codigoSala = rs.codigoSala
JOIN Recurso r ON rs.id_Recurso = r.id_Recurso
ORDER BY s.codigoSala;

-- 7. Contar quantas salas existem por tipo de sala
SELECT ts.nome AS tipoSala, COUNT(*) AS total
FROM Sala s
JOIN TipoSala ts ON s.tipo = ts.tipo
GROUP BY ts.nome;

-- 8. Listar todas as reservas feitas por um usuário específico (ex: CPF '123.456.789-00')
SELECT r.id, r.dataInicio, r.dataFim, r.codigoSala
FROM Reserva r
WHERE cpf = '123.456.789-00';

-- 9. Listar salas com capacidade superior a 100 lugares
SELECT codigoSala, capacidade
FROM Sala
WHERE capacidade > 100;

-- 10. Listar reservas com duração maior que 2 horas (usando TIMESTAMPDIFF)
SELECT id, codigoSala, dataInicio, dataFim,
       TIMESTAMPDIFF(HOUR, dataInicio, dataFim) AS duracao_horas
FROM Reserva
WHERE TIMESTAMPDIFF(HOUR, dataInicio, dataFim) > 2;