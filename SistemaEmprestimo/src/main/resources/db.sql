CREATE TABLE Aluno
(
    matricula INT PRIMARY KEY,
    nome      VARCHAR(100) NOT NULL,
    cpf       VARCHAR(11)  NOT NULL,
    endereco  VARCHAR(100) NOT NULL
);

CREATE TABLE Debito
(
    alunoMatricula INT  NOT NULL,
    valor     INT  NOT NULL,
    data      DATE NOT NULL,
    FOREIGN KEY (alunoMatricula) REFERENCES Aluno (matricula)
);

CREATE TABLE Area
(
    nome      VARCHAR(100) PRIMARY KEY,
    descricao VARCHAR(100) NOT NULL
);

CREATE TABLE Autor
(
    nome      VARCHAR(100) PRIMARY KEY,
    sobrenome VARCHAR(100) NOT NULL,
    titulacao VARCHAR(100) NOT NULL
);

CREATE TABLE Titulo
(
    isbn   VARCHAR(13) PRIMARY KEY,
    nome   VARCHAR(100) NOT NULL,
    edicao INT          NOT NULL,
    ano    INT          NOT NULL,
    prazo  INT          NOT NULL
);

CREATE TABLE TituloAreas
(
    isbn VARCHAR(13) NOT NULL,
    area VARCHAR(100) NOT NULL,
    PRIMARY KEY (isbn, area),
    FOREIGN KEY (isbn) REFERENCES Titulo (isbn),
    FOREIGN KEY (area) REFERENCES Area (nome)
);

CREATE TABLE TituloAutores
(
    isbn VARCHAR(13) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    PRIMARY KEY (isbn, autor),
    FOREIGN KEY (isbn) REFERENCES Titulo (isbn),
    FOREIGN KEY (autor) REFERENCES Autor (nome)
);

CREATE TABLE Livro
(
    id SERIAL PRIMARY KEY,
    isbn VARCHAR(13) NOT NULL,
    exemplarBiblioteca BOOLEAN NOT NULL,
    disponivel BOOLEAN NOT NULL,
    FOREIGN KEY (isbn) REFERENCES Titulo (isbn)
);

CREATE TABLE Emprestimo
(
    id SERIAL PRIMARY KEY,
    alunoMatricula INT NOT NULL,
    dataEmprestimo DATE NOT NULL,
    dataPrevistaEntrega DATE NOT NULL,
    multa INT NOT NULL DEFAULT 0,
    FOREIGN KEY (alunoMatricula) REFERENCES Aluno (matricula)
);

CREATE TABLE ItemEmprestimo
(
    idEmprestimo INT NOT NULL,
    idLivro INT NOT NULL,
    dataDevolucao DATE,
    dataPrevistaEntrega DATE NOT NULL,
    PRIMARY KEY (idEmprestimo, idLivro),
    FOREIGN KEY (idEmprestimo) REFERENCES Emprestimo (id),
    FOREIGN KEY (idLivro) REFERENCES Livro (id)
);