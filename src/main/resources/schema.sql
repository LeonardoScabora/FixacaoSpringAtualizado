CREATE TABLE Professor (
                           ID_prof LONG auto_increment PRIMARY KEY ,
                           NOME_prof Varchar(60),
                           DataNASC_prof DATE,
                           SALARIO Decimal (10, 2),
                           CARGO ENUM('FIXO','SUBSTITUTO'),
                           DATAREGISTRO TIMESTAMP);


CREATE TABLE Turma (
                       ID_turma LONG auto_increment PRIMARY KEY ,
                       SERIE_turma varchar(10),
                       PROF_id LONG,

                       CONSTRAINT FK_Professor FOREIGN KEY (PROF_id) REFERENCES Professor(ID_prof));

CREATE TABLE Endereco (
                         ID_endereco LONG auto_increment PRIMARY KEY,
                         RUA varchar(150));

CREATE TABLE Aluno (

                       ID_aluno LONG PRIMARY KEY,
                       NOME_aluno varchar(60),
                       DATANASCIMENTO DATE,
                       DATAREGISTRO TIMESTAMP,
                       TURMA_id LONG,
                       Endereco_id LONG,

                       CONSTRAINT FK_Endereco FOREIGN KEY (ID_aluno) REFERENCES Endereco(ID_endereco),
                       CONSTRAINT FK_Turma FOREIGN KEY (TURMA_id) REFERENCES Turma(ID_turma));

-- CREATE TABLE Aluno_Turma (
--                              aluno_id integer,
--                              turma_id integer,
--                              Presensa ENUM('FALTA','PRESENCA'),
--
--                              PRIMARY KEY (aluno_id, turma_id),
--                              FOREIGN KEY (aluno_id) REFERENCES Aluno(ID_aluno),
--                              FOREIGN KEY (turma_id) REFERENCES Turma(ID_turma));

CREATE TABLE Disciplina (
                      ID_disciplina LONG auto_increment PRIMARY KEY,
                      Nome_disciplina varchar(160),
                      turma_id LONG,

                      CONSTRAINT FK_Prof FOREIGN KEY (turma_id) REFERENCES Turma(ID_turma));


INSERT INTO Professor (ID_prof, NOME_prof, DataNASC_prof, SALARIO, CARGO, DATAREGISTRO)
VALUES (1, 'João da Silva', '1980-05-15', 5000.00, 'FIXO', CURRENT_TIMESTAMP);

INSERT INTO Professor (ID_prof, NOME_prof, DataNASC_prof, SALARIO, CARGO, DATAREGISTRO)
VALUES (2, 'Maria dos Santos', '1978-03-22', 5500.50, 'SUBSTITUTO', CURRENT_TIMESTAMP);

INSERT INTO Professor (ID_prof, NOME_prof, DataNASC_prof, SALARIO, CARGO, DATAREGISTRO)
VALUES (3, 'Carlos Oliveira', '1985-11-09', 4800.75, 'FIXO', CURRENT_TIMESTAMP);

INSERT INTO Professor (ID_prof, NOME_prof, DataNASC_prof, SALARIO, CARGO, DATAREGISTRO)
VALUES (4, 'Ana Souza', '1990-08-30', 4700.80, 'SUBSTITUTO', CURRENT_TIMESTAMP);

INSERT INTO Professor (ID_prof, NOME_prof, DataNASC_prof, SALARIO, CARGO, DATAREGISTRO)
VALUES (5, 'Pedro Pereira', '1975-12-10', 6000.00, 'FIXO', CURRENT_TIMESTAMP);

