CREATE TABLE Professor (
                           ID_prof LONG auto_increment PRIMARY KEY not null,
                           NOME_prof Varchar(60),
                           SOBRENOME_prof Varchar(60),
                           DataNASC_prof DATE,
                           SALARIO Decimal (10, 2),
                           CARGO ENUM('FIXO','SUBSTITUTO'),
                           DATAREGISTRO TIMESTAMP);


CREATE TABLE Turma (
                       ID_turma LONG auto_increment PRIMARY KEY not null,
                       SERIE_turma varchar(10),
                       DATAREGISTRO TIMESTAMP,
                       PROF_id LONG,

                       CONSTRAINT FK_Professor FOREIGN KEY (PROF_id) REFERENCES Professor(ID_prof));

CREATE TABLE Endereco (
                         ID_endereco LONG auto_increment PRIMARY KEY not null,
                         RUA varchar(150),
                         BAIRRO varchar(150),
                         MORADIA ENUM('CASA', 'APARTAMENTO'),
                         DATAREGISTRO TIMESTAMP);

CREATE TABLE Aluno (

                       ID_aluno LONG auto_increment PRIMARY KEY not null ,
                       NOME_aluno varchar(60),
                       SOBRENOME_aluno varchar(60),
                       DATANASCIMENTO DATE,
                       DATAREGISTRO TIMESTAMP,
                       Endereco_id LONG UNIQUE,

                       CONSTRAINT FK_Endereco FOREIGN KEY (Endereco_id) REFERENCES Endereco(ID_endereco));

CREATE TABLE Disciplina (
                      ID_disciplina LONG auto_increment not null,
                      ID_SEGUNDARIO varchar(120),
                      Nome_disciplina varchar(160),
                      SIMBOLO varchar(50),
                      ESTAGIO ENUM('INICIO','AVANÇADO'),
                      DATAREGISTRO TIMESTAMP,
                      turma_id LONG,

                      PRIMARY KEY (ID_disciplina, ID_SEGUNDARIO),
                      CONSTRAINT FK_Prof FOREIGN KEY (turma_id) REFERENCES Turma(ID_turma));


-- PROFESSOR
INSERT INTO Professor (ID_prof, NOME_prof, SOBRENOME_prof, DataNASC_prof, SALARIO, CARGO, DATAREGISTRO)
VALUES (1, 'João', 'Silva', '1980-05-15', 5000.00, 'FIXO', CURRENT_TIMESTAMP);

INSERT INTO Professor (ID_prof, NOME_prof, SOBRENOME_prof, DataNASC_prof, SALARIO, CARGO, DATAREGISTRO)
VALUES (2, 'Maria', 'Santos', '1978-03-22', 5500.50, 'SUBSTITUTO', CURRENT_TIMESTAMP);

INSERT INTO Professor (ID_prof, NOME_prof, SOBRENOME_prof, DataNASC_prof, SALARIO, CARGO, DATAREGISTRO)
VALUES (3, 'Carlos', 'Oliveira','1985-11-09', 4800.75, 'FIXO', CURRENT_TIMESTAMP);

INSERT INTO Professor (ID_prof, NOME_prof, SOBRENOME_prof, DataNASC_prof, SALARIO, CARGO, DATAREGISTRO)
VALUES (4, 'Ana', 'Souza','1990-08-30', 4700.80, 'SUBSTITUTO', CURRENT_TIMESTAMP);

INSERT INTO Professor (ID_prof, NOME_prof, SOBRENOME_prof, DataNASC_prof, SALARIO, CARGO, DATAREGISTRO)
VALUES (5, 'Pedro', ' Pereira', '1975-12-10', 6000.00, 'FIXO', CURRENT_TIMESTAMP);

-- ENDERECO
INSERT INTO Endereco(ID_endereco, RUA, BAIRRO, MORADIA, DATAREGISTRO)
VALUES ( 1, 'Algusta','Vila Mimosa', 'CASA', CURRENT_TIMESTAMP);

INSERT INTO Endereco(ID_endereco, RUA, BAIRRO, MORADIA, DATAREGISTRO)
VALUES ( 2, 'Ipiranga','Vila Gloria', 'APARTAMENTO', CURRENT_TIMESTAMP);

INSERT INTO Endereco(ID_endereco, RUA, BAIRRO, MORADIA, DATAREGISTRO)
VALUES ( 3, 'Faber','Vila Ribeiro', 'CASA', CURRENT_TIMESTAMP);

INSERT INTO Endereco(ID_endereco, RUA, BAIRRO, MORADIA, DATAREGISTRO)
VALUES ( 4, 'Pedro Segundo','Vila Fabrica', 'APARTAMENTO', CURRENT_TIMESTAMP);

-- ALUNO
INSERT INTO Aluno(ID_aluno, NOME_aluno, SOBRENOME_aluno, DATANASCIMENTO, DATAREGISTRO, Endereco_id)
VALUES ( 1, 'Carlos', 'Silva','2003-10-20', CURRENT_TIMESTAMP, 1);

INSERT INTO Aluno(ID_aluno, NOME_aluno, SOBRENOME_aluno, DATANASCIMENTO, DATAREGISTRO, Endereco_id)
VALUES ( 2, 'Felipe', 'Silva','2003-10-20', CURRENT_TIMESTAMP, 2);

INSERT INTO Aluno(ID_aluno, NOME_aluno, SOBRENOME_aluno, DATANASCIMENTO, DATAREGISTRO, Endereco_id)
VALUES ( 3, 'Antonio', 'Ferreira','2003-10-20', CURRENT_TIMESTAMP, 3);

INSERT INTO Aluno(ID_aluno, NOME_aluno, SOBRENOME_aluno, DATANASCIMENTO, DATAREGISTRO, Endereco_id)
VALUES ( 4, 'Fernando', 'Santos','2003-10-20', CURRENT_TIMESTAMP, 4);

