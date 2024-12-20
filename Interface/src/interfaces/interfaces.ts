export type Aluno = {
    nome: string;
    matricula: number;
    cpf: string;
    endereco: string;
}

export type Livro = {
    id: number;
    disponivel: boolean;
    exemplarBiblioteca: boolean;
    titulo: Titulo;
};

export type Autor = {
    nome: string;
    sobrenome: string;
    titulacao: string;
};

export type Area = {
    nome: string;
    descricao: string; 
};

export type Titulo = {
    nome: string;
    edicao: number; 
    ano: number;
    prazo: number;
    isbn: string;
    autores: Autor[]; 
    areas: Area[]; 
};


export type Item = {
    livro: Livro;
    dataDevolucao: string;
}

export type Emprestimo = {
    aluno: Aluno;
    itens: Item[];
    dataEmprestimo: string;
    dataPrevista: string;
}