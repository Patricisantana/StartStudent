package com.backend.startstudents.application.usecases;

import com.backend.startstudents.adaters.inbound.dto.AlunoResponseDTO;
import com.backend.startstudents.adaters.ports.AlunoRepositoryPort;
import com.backend.startstudents.domain.Aluno;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListarAlunosUseCase {

    private final AlunoRepositoryPort alunoRepository;

    public ListarAlunosUseCase(AlunoRepositoryPort alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public ListaAlunosResultado executar(int pagina, int tamanho) {
        List<Aluno> alunos = alunoRepository.listarTodos(pagina, tamanho);
        long total = alunoRepository.contarTotal();

        List<AlunoResponseDTO> dtos = alunos.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        return new ListaAlunosResultado(dtos, total, pagina, tamanho);
    }

    public static class ListaAlunosResultado {
        public List<AlunoResponseDTO> alunos;
        public long total;
        public int pagina;
        public int tamanho;

        public ListaAlunosResultado(List<AlunoResponseDTO> alunos, long total, int pagina, int tamanho) {
            this.alunos = alunos;
            this.total = total;
            this.pagina = pagina;
            this.tamanho = tamanho;
        }

        public long getPaginasTotal() {
            return (total + tamanho - 1) / tamanho;
        }
    }

    private AlunoResponseDTO converterParaDTO(Aluno aluno) {
        return AlunoResponseDTO.builder()
                .id(aluno.getId())
                .matricula(aluno.getMatricula())
                .nomeCompleto(aluno.getNomeCompleto())
                .email(aluno.getEmail())
                .cpf(aluno.getCpf())
                .telefone(aluno.getTelefone())
                .foto(aluno.getFoto())
                .ativo(aluno.getAtivo())
                .criadoEm(aluno.getCriadoEm())
                .atualizadoEm(aluno.getAtualizadoEm())
                .build();
    }
}
