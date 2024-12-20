import { Aluno } from "../interfaces/interfaces.ts";

interface TabelaAlunosProps {
    alunos: Aluno[];
}

export default function TabelaAlunos({ alunos }: TabelaAlunosProps) {
    return (
        <div className="overflow-x-auto">
            <table className="min-w-full bg-white border border-gray-200">
                <thead>
                <tr>
                    <th className="py-2 px-4 border-b">Matrícula</th>
                    <th className="py-2 px-4 border-b">Nome</th>
                    <th className="py-2 px-4 border-b">CPF</th>
                    <th className="py-2 px-4 border-b">Endereço</th>
                </tr>
                </thead>
                <tbody>
                {alunos.map((aluno, index) => (
                    <tr key={index} className="hover:bg-gray-100">
                        <td className="py-2 px-4 border-b">{aluno.matricula}</td>
                        <td className="py-2 px-4 border-b">{aluno.nome}</td>
                        <td className="py-2 px-4 border-b">{aluno.cpf}</td>
                        <td className="py-2 px-4 border-b">{aluno.endereco}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}