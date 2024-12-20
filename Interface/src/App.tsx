import {useDevolver, useEmprestar, useGetLivros} from "./api/Service.ts";
import TabelaLivros from "./components/TabelaLivros.tsx";
import {useEffect, useState} from "react";
import {AlunoCRUD} from "./components/AlunoCRUD.tsx";
import {CriarLivros} from "./components/CriarLivros.tsx";

export default function App() {
    const {livros, refetch} = useGetLivros();
    const livrosOrdenados = livros.sort((a, b) => {
        if (a.disponivel === b.disponivel) {
            return a.id - b.id;
        }
        return a.disponivel ? -1 : 1;
    });

    const {emprestar} = useEmprestar();
    const {devolver} = useDevolver();

    const [matricula, setMatricula] = useState("");
    const [dataDevolucao, setDataDevolucao] = useState("");
    const [ids, setIds] = useState<string[]>([]);

    const [tab, setTab] = useState(0);

    useEffect(() => {
        setIds([]);
        setMatricula("");
        setDataDevolucao("");
    }, [tab]);

    const handleCheckboxChange = (id: string, checked: boolean) => {
        if (checked) {
            setIds((prevIds) => [...prevIds, id]);
        } else {
            setIds((prevIds) => prevIds.filter((item) => item !== id));
        }
    };

    function handleEmprestar() {
        emprestar(
            {
                alunoMatricula: parseInt(matricula),
                livrosIds: ids.map((id) => parseInt(id)),
            },
            {
                onSuccess: () => {
                    setMatricula("");
                    setIds([]);
                    refetch();
                    alert("Empréstimo realizado com sucesso!");
                },
            }
        );
    }

    function handleDevolver() {
        devolver(
            {
                dataDevolucao: dataDevolucao,
                livrosIds: ids.map((id) => parseInt(id)),
            },
            {
                onSuccess: () => {
                    setIds([]);
                    setDataDevolucao("");
                    refetch();
                    alert("Devolução realizada com sucesso!");
                },
            }
        );
    }

    return (
        <div className="p-6 bg-gray-100 min-h-screen">
            <div className="sticky top-0 bg-white shadow-md z-10">
                <div className="flex space-x-4 mb-4 border-b">
                    <button
                        onClick={() => setTab(0)}
                        className={`py-2 px-4 ${tab === 0 ? "border-b-2 border-blue-500 text-blue-600 font-bold" : "text-gray-500"}`}
                    >
                        Emprestar Livros
                    </button>
                    <button
                        onClick={() => setTab(1)}
                        className={`py-2 px-4 ${tab === 1 ? "border-b-2 border-blue-500 text-blue-600 font-bold" : "text-gray-500"}`}
                    >
                        Devolver Livros
                    </button>
                    <button
                        onClick={() => setTab(2)}
                        className={`py-2 px-4 ${tab === 2 ? "border-b-2 border-blue-500 text-blue-600 font-bold" : "text-gray-500"}`}
                    >
                        CRUD Alunos
                    </button>
                    <button
                        onClick={() => setTab(3)}
                        className={`py-2 px-4 ${tab === 3 ? "border-b-2 border-blue-500 text-blue-600 font-bold" : "text-gray-500"}`}
                    >
                        Criar Livros
                    </button>
                </div>
            </div>

            {tab === 0 && (
                <div className="max-w-4xl mx-auto">
                    <div className="mb-8">
                        <TabelaLivros livros={livrosOrdenados} ids={ids} onCheckboxChange={handleCheckboxChange}/>
                    </div>
                    <div className="flex justify-end items-center space-x-4">
                        <input
                            type="text"
                            placeholder="Matrícula do aluno"
                            value={matricula}
                            onChange={(e) => setMatricula(e.target.value)}
                            className="border rounded-md p-2 w-64"
                        />
                        <button
                            onClick={handleEmprestar}
                            className="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded"
                            disabled={!matricula || ids.length === 0}
                        >
                            Emprestar
                        </button>
                    </div>
                </div>
            )}

            {tab === 1 && (
                <div className="max-w-4xl mx-auto">
                    <div className="mb-8">
                        <TabelaLivros
                            livros={livrosOrdenados.filter((livro) => !livro.disponivel)}
                            ids={ids}
                            enableCheckIndisponivel={true}
                            onCheckboxChange={handleCheckboxChange}
                        />
                    </div>
                    <div className="flex justify-end">
                        <input
                            type="date"
                            value={dataDevolucao}
                            onChange={(e) => setDataDevolucao(e.target.value)}
                            className="border rounded-md p-2 w-64"
                        />
                        <button
                            onClick={handleDevolver}
                            className="bg-green-500 hover:bg-green-600 text-white py-2 px-4 rounded"
                            disabled={ids.length === 0}
                        >
                            Devolver
                        </button>
                    </div>
                </div>
            )}

            {tab === 2 && (
                <div className="max-w-4xl mx-auto">
                    <AlunoCRUD />
                </div>
            )}

            {tab === 3 && (
                <div className="max-w-4xl mx-auto">
                    <CriarLivros />
                </div>
            )}
        </div>
    );
}
