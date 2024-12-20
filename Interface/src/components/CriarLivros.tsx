import {useState} from "react";
import {useCriarLivros} from "../api/Service.ts";
import {Area, Autor} from "../interfaces/interfaces.ts";

export function CriarLivros() {
    const {criarLivro} = useCriarLivros();

    const [exemplarBiblioteca, setExemplarBiblioteca] = useState(false);
    const [titulo, setTitulo] = useState<{
        nome: string;
        edicao: number;
        ano: number;
        prazo: number;
        isbn: string;
        autores: Autor[];
        areas: Area[];
    }>({
        nome: "",
        edicao: 0,
        ano: 0,
        prazo: 0,
        isbn: "",
        autores: [],
        areas: [],
    });

    const [autorNome, setAutorNome] = useState("");
    const [autorSobrenome, setAutorSobrenome] = useState("");
    const [autorTitulacao, setAutorTitulacao] = useState("");

    const [areaNome, setAreaNome] = useState("");
    const [areaDescricao, setAreaDescricao] = useState("");

    const handleAddAutor = () => {
        setTitulo((prev) => ({
            ...prev,
            autores: [
                ...prev.autores,
                {nome: autorNome, sobrenome: autorSobrenome, titulacao: autorTitulacao},
            ],
        }));
        setAutorNome("");
        setAutorSobrenome("");
        setAutorTitulacao("");
    };

    const handleAddArea = () => {
        setTitulo((prev) => ({
            ...prev,
            areas: [...prev.areas, {nome: areaNome, descricao: areaDescricao}],
        }));
        setAreaNome("");
        setAreaDescricao("");
    };

    const handleCreateLivro = () => {
        criarLivro(
            {
                exemplarBiblioteca,
                titulo,
            },
            {
                onSuccess: () => {
                    setExemplarBiblioteca(false);
                    setTitulo({
                        nome: "",
                        edicao: 0,
                        ano: 0,
                        prazo: 0,
                        isbn: "",
                        autores: [],
                        areas: [],
                    });
                    alert("Livro criado com sucesso!");
                },
            }
        );
    };

    return (
        <div className="flex justify-center items-center min-h-screen bg-gray-100 p-8">
            <div className="w-full max-w-5xl bg-white p-8 shadow rounded-lg">
                <h2 className="text-2xl font-bold mb-6">Criar Livro</h2>
                <div className="">
                    <div className="flex-1 min-w-[300px]">
                        <h3 className="text-md font-bold mb-4">Título</h3>
                        <div className="mb-4">
                            <label className="block mb-2 text-sm font-medium">Nome</label>
                            <input
                                type="text"
                                value={titulo.nome}
                                onChange={(e) =>
                                    setTitulo((prev) => ({...prev, nome: e.target.value}))
                                }
                                className="border rounded-md p-2 w-full"
                            />
                        </div>
                        <div className="mb-4 flex gap-4 items-center">
                            <label className="text-sm font-medium">Exemplar Biblioteca</label>
                            <input
                                type="checkbox"
                                checked={exemplarBiblioteca}
                                onChange={(e) => setExemplarBiblioteca(e.target.checked)}
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block mb-2 text-sm font-medium">Edição</label>
                            <input
                                type="number"
                                value={titulo.edicao}
                                onChange={(e) =>
                                    setTitulo((prev) => ({...prev, edicao: Number(e.target.value)}))
                                }
                                className="border rounded-md p-2 w-full"
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block mb-2 text-sm font-medium">Ano</label>
                            <input
                                type="number"
                                value={titulo.ano}
                                onChange={(e) =>
                                    setTitulo((prev) => ({...prev, ano: Number(e.target.value)}))
                                }
                                className="border rounded-md p-2 w-full"
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block mb-2 text-sm font-medium">ISBN</label>
                            <input
                                type="text"
                                value={titulo.isbn}
                                onChange={(e) =>
                                    setTitulo((prev) => ({...prev, isbn: e.target.value}))
                                }
                                className="border rounded-md p-2 w-full"
                            />
                        </div>
                    </div>

                    {/* Add Author */}
                    <div className="flex-1 min-w-[300px]">
                        <h3 className="text-md font-bold mb-4">Autores</h3>
                        <div className="mb-4">
                            <label className="block mb-2 text-sm font-medium">Nome</label>
                            <input
                                type="text"
                                value={autorNome}
                                onChange={(e) => setAutorNome(e.target.value)}
                                className="border rounded-md p-2 w-full"
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block mb-2 text-sm font-medium">Sobrenome</label>
                            <input
                                type="text"
                                value={autorSobrenome}
                                onChange={(e) => setAutorSobrenome(e.target.value)}
                                className="border rounded-md p-2 w-full"
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block mb-2 text-sm font-medium">Titulação</label>
                            <input
                                type="text"
                                value={autorTitulacao}
                                onChange={(e) => setAutorTitulacao(e.target.value)}
                                className="border rounded-md p-2 w-full"
                            />
                        </div>
                        <button
                            onClick={handleAddAutor}
                            className="bg-green-500 hover:bg-green-600 text-white py-2 px-4 rounded mt-4"
                        >
                            Adicionar Autor
                        </button>
                        <ul className="mt-4">
                            {titulo.autores.map((autor, index) => (
                                <li key={index}>
                                    {autor.nome} {autor.sobrenome} ({autor.titulacao})
                                </li>
                            ))}
                        </ul>
                    </div>

                    {/* Add Area */}
                    <div className="flex-1 min-w-[300px]">
                        <h3 className="text-md font-bold mb-4">Áreas</h3>
                        <div className="mb-4">
                            <label className="block mb-2 text-sm font-medium">Nome</label>
                            <input
                                type="text"
                                value={areaNome}
                                onChange={(e) => setAreaNome(e.target.value)}
                                className="border rounded-md p-2 w-full"
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block mb-2 text-sm font-medium">Descrição</label>
                            <input
                                type="text"
                                value={areaDescricao}
                                onChange={(e) => setAreaDescricao(e.target.value)}
                                className="border rounded-md p-2 w-full"
                            />
                        </div>
                        <button
                            onClick={handleAddArea}
                            className="bg-green-500 hover:bg-green-600 text-white py-2 px-4 rounded mt-4"
                        >
                            Adicionar Área
                        </button>
                        <ul className="mt-4">
                            {titulo.areas.map((area, index) => (
                                <li key={index}>
                                    {area.nome}: {area.descricao}
                                </li>
                            ))}
                        </ul>
                    </div>
                </div>
                <button
                    onClick={handleCreateLivro}
                    className="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded mt-6 w-full"
                    disabled={!titulo.nome || !titulo.isbn}
                >
                    Criar Livro
                </button>
            </div>
        </div>
    );
}
