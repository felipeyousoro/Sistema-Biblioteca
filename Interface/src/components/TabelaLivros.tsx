import { Livro } from "../interfaces/interfaces.ts";

interface TabelaLivrosProps {
    livros: Livro[];
    ids: string[];
    onCheckboxChange: (id: string, checked: boolean) => void;
    enableCheckIndisponivel?: boolean;
}

export default function TabelaLivros({ livros, ids, onCheckboxChange, enableCheckIndisponivel = false }: TabelaLivrosProps) {
    return (
        <div className="overflow-x-auto">
            <table className="min-w-full bg-white border border-gray-200">
                <thead>
                <tr>
                    <th className="py-2 px-4 border-b"></th>
                    <th className="py-2 px-4 border-b">ID</th>
                    <th className="py-2 px-4 border-b">Título</th>
                    <th className="py-2 px-4 border-b">Prazo de Devolução</th>
                    <th className="py-2 px-4 border-b">Exemplar</th>
                    <th className="py-2 px-4 border-b">Autores</th>
                    <th className="py-2 px-4 border-b">Áreas</th>
                    <th className="py-2 px-4 border-b">Disponível</th>
                </tr>
                </thead>
                <tbody>
                {livros.map((livro, index) => (
                    <tr key={index} className="hover:bg-gray-100">
                        <td className="py-2 px-4 border-b text-center">
                            <input
                                type="checkbox"
                                disabled={!livro.disponivel && !enableCheckIndisponivel}
                                checked={ids.includes(livro.id.toString())}
                                onChange={(e) =>
                                    onCheckboxChange(livro.id.toString(), e.target.checked)
                                }
                                className="h-5 w-5"
                            />
                        </td>
                        <td className="py-2 px-4 border-b">{livro.id}</td>
                        <td className="py-2 px-4 border-b">
                            {livro.titulo.nome} - {livro.titulo.edicao} ({livro.titulo.ano})
                        </td>
                        <td className="py-2 px-4 border-b">{livro.titulo.prazo}</td>
                        <td className="py-2 px-4 border-b">
                            {livro.exemplarBiblioteca ? "Sim" : "Não"}
                        </td>
                        <td className="py-2 px-4 border-b">
                            {livro.titulo.autores.map((autor, idx) => (
                                <div key={idx}>
                                    {autor.nome} {autor.sobrenome} ({autor.titulacao})
                                </div>
                            ))}
                        </td>
                        <td className="py-2 px-4 border-b">
                            {livro.titulo.areas.map((area, idx) => (
                                <div key={idx}>{area.nome}</div>
                            ))}
                        </td>
                        <td
                            className={`py-2 px-4 border-b ${
                                livro.disponivel ? "text-green-500" : "text-red-500"
                            }`}
                        >
                            {livro.disponivel ? "Sim" : "Não"}
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}
