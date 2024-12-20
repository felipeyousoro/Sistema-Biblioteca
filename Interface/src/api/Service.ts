import {api} from "./api";
import {useState} from "react";
import {Aluno, Livro} from "../interfaces/interfaces.ts";
import {useMutation, useQuery} from "react-query";


export function useGetLivros() {
    const [livros, setLivros] = useState<Livro[]>([]);

    const {isLoading, refetch} = useQuery<Livro[]>({
        queryKey: "livros",
        queryFn: async () => {
            const response = await api.get("/livro/");
            return response.data;
        },
        onSuccess: (data) => {
            setLivros(data);
        }
    });

    return {livros, isLoading, refetch};
}

type EmprestarProps = {
    alunoMatricula: number;
    livrosIds: number[];
}

export function useEmprestar() {
    const {mutate} = useMutation({
        mutationKey: "emprestar",
        mutationFn: async (props: EmprestarProps) => {
            await api.post("/emprestimo/emprestar", props);
        }
    });

    return {emprestar: mutate};
}

type DevolverProps = {
    dataDevolucao: string;
    livrosIds: number[];
}

export function useDevolver() {
    const {mutate} = useMutation({
        mutationKey: "devolver",
        mutationFn: async (props: DevolverProps) => {
            await api.post("/emprestimo/devolver", props);
        }
    });

    return {devolver: mutate};
}

export function useGetAlunos() {
    const [alunos, setAlunos] = useState<Aluno[]>([]);

    const { isLoading, refetch } = useQuery<Aluno[]>({
        queryKey: "alunos",
        queryFn: async () => {
            const response = await api.get("/aluno/");
            return response.data;
        },
        onSuccess: (data) => {
            setAlunos(data);
        }
    });

    return { alunos, isLoading, refetch };
}

export function useCriarAlunos() {
    const { mutate, isLoading } = useMutation({
        mutationKey: "criarAluno",
        mutationFn: async (aluno: Aluno) => {
            await api.post("/aluno/", aluno);
        }
    });

    return { criarAluno: mutate, isLoading };
}

export function useCriarLivros() {
    const { mutate, isLoading } = useMutation({
        mutationKey: "criarLivro",
        mutationFn: async (livro: Omit<Livro, "id" | "disponivel">) => {
            await api.post("/livro/", livro);
        },
    });

    return { criarLivro: mutate, isLoading };
}