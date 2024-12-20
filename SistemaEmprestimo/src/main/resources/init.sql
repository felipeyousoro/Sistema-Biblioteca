INSERT INTO Aluno (matricula, cpf, nome, endereco)
VALUES (1, '12345678901', 'João', 'UEL'),
       (2, '23456789012', 'Maria', 'UEM'),
       (3, '34567890123', 'José', 'UTFPR'),
       (4, '45678901234', 'Ana', 'UENP'),
       (5, '56789012345', 'Pedro', 'UFPR');

INSERT INTO Area (nome, descricao)
VALUES ('Computação', 'Livros relacionados à área de computação'),
       ('Matemática', 'Livros de matemática e áreas correlatas'),
       ('Física', 'Livros de física e ciências naturais'),
       ('História', 'Livros sobre história e cultura geral'),
       ('Literatura', 'Livros de literatura clássica e moderna');

INSERT INTO Autor (nome, sobrenome, titulacao)
VALUES ('Alan', 'Turing', 'PhD'),
       ('Isaac', 'Newton', 'PhD'),
       ('Albert', 'Einstein', 'PhD'),
       ('William', 'Shakespeare', 'BA'),
       ('Jane', 'Austen', 'MA'),
       ('Carl', 'Gauss', 'PhD'),
       ('Charles', 'Darwin', 'PhD');

INSERT INTO Titulo (isbn, nome, edicao, ano, prazo)
VALUES ('9780131103627', 'The C Programming Language', 2, 1988, 1),
       ('9780321751041', 'Introduction to Algorithms', 3, 2009, 1),
       ('9780262033848', 'Artificial Intelligence: A Modern Approach', 3, 2015, 1),
       ('9780198526636', 'Principia Mathematica', 1, 1687, 2),
       ('9780195004617', 'Relativity: The Special and General Theory', 1, 1916, 2),
       ('9780743273565', 'Pride and Prejudice', 1, 1813, 2),
       ('9780141439600', 'Hamlet', 1, 1609, 3);

INSERT INTO TituloAreas (isbn, area)
VALUES ('9780131103627', 'Computação'),
       ('9780321751041', 'Computação'),
       ('9780262033848', 'Computação'),
       ('9780198526636', 'Matemática'),
       ('9780195004617', 'Física'),
       ('9780743273565', 'Literatura'),
       ('9780141439600', 'Literatura');

INSERT INTO TituloAutores (isbn, autor)
VALUES ('9780131103627', 'Alan'),
       ('9780321751041', 'Alan'),
       ('9780262033848', 'Alan'),
       ('9780198526636', 'Isaac'),
       ('9780195004617', 'Albert'),
       ('9780743273565', 'Jane'),
       ('9780141439600', 'William');

-- Insert into Livro
INSERT INTO Livro (isbn, exemplarBiblioteca, disponivel)
VALUES ('9780131103627', FALSE, TRUE),
       ('9780131103627', FALSE, TRUE),
       ('9780131103627', FALSE, TRUE),
       ('9780321751041', FALSE, TRUE),
       ('9780321751041', TRUE, TRUE),
       ('9780321751041', FALSE, TRUE),
       ('9780262033848', FALSE, TRUE),
       ('9780262033848', TRUE, TRUE),
       ('9780262033848', FALSE, TRUE),
       ('9780198526636', FALSE, TRUE),
       ('9780198526636', FALSE, TRUE),
       ('9780198526636', FALSE, TRUE),
       ('9780195004617', TRUE, TRUE),
       ('9780195004617', FALSE, TRUE),
       ('9780743273565', FALSE, TRUE),
       ('9780743273565', FALSE, TRUE),
       ('9780743273565', FALSE, TRUE),
       ('9780141439600', FALSE, TRUE),
       ('9780141439600', FALSE, TRUE),
       ('9780141439600', FALSE, TRUE);
