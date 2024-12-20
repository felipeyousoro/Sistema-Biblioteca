import {useCriarAlunos, useGetAlunos} from "../api/Service.ts";
import {useState} from "react";
import TabelaAlunos from "./TabelaAlunos.tsx";

export function AlunoCRUD() {
    const {alunos, refetch} = useGetAlunos();
    const {criarAluno} = useCriarAlunos();

    const [nome, setNome] = useState("");
    const [cpf, setCpf] = useState("");
    const [endereco, setEndereco] = useState("");
    const [matricula, setMatricula] = useState("");

    const handleCreateAluno = () => {
        criarAluno(
            {matricula: Number(matricula), nome, cpf, endereco},
            {
                onSuccess: () => {
                    setNome("");
                    setCpf("");
                    setEndereco("");
                    setMatricula("");
                    refetch();
                    alert("Aluno criado com sucesso!");
                },
            }
        );
    };

    return (
        <div className="flex space-x-8 p-6 bg-gray-100">
            <div className="w-1/3 bg-white p-4 shadow rounded">
                <h2 className="text-lg font-bold mb-4">Criar Aluno</h2>
                <div className="mb-4">
                    <label className="block mb-2 text-sm font-medium">Nome</label>
                    <input
                        type="text"
                        value={nome}
                        onChange={(e) => setNome(e.target.value)}
                        className="border rounded-md p-2 w-full"
                    />
                </div>
                <div className="mb-4">
                    <label className="block mb-2 text-sm font-medium">CPF</label>
                    <input
                        type="text"
                        value={cpf}
                        onChange={(e) => setCpf(e.target.value)}
                        className="border rounded-md p-2 w-full"
                    />
                </div>
                <div className="mb-4">
                    <label className="block mb-2 text-sm font-medium">Endereço</label>
                    <input
                        type="text"
                        value={endereco}
                        onChange={(e) => setEndereco(e.target.value)}
                        className="border rounded-md p-2 w-full"
                    />
                </div>
                <div className="mb-4">
                    <label className="block mb-2 text-sm font-medium">Matrícula</label>
                    <input
                        type="text"
                        value={matricula}
                        onChange={(e) => setMatricula(e.target.value)}
                        className="border rounded-md p-2 w-full"
                    />
                </div>
                <button
                    onClick={handleCreateAluno}
                    className="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded w-full"
                    disabled={!nome || !cpf || !endereco || !matricula}
                >
                    Criar Aluno
                </button>
            </div>
            <div className="flex-1">
                <TabelaAlunos alunos={alunos}/>
            </div>
        </div>
    );
}
