package com.backend.startstudents.application.usecases;

import com.backend.startstudents.adaters.inbound.dto.AlunoResponseDTO;
import com.backend.startstudents.adaters.ports.AlunoRepositoryPort;
import com.backend.startstudents.domain.Aluno;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuscarAlunosPorNomeUseCase {

    private final AlunoRepositoryPort alunoRepository;

    public BuscarAlunosPorNomeUseCase(AlunoRepositoryPort alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public BuscaResultado executarPorNome(String nome, int pagina, int tamanho) {
        List<Aluno> alunos = alunoRepository.buscarPorNome(nome, pagina, tamanho);

        List<AlunoResponseDTO> dtos = alunos.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        return new BuscaResultado(dtos, alunos.size());
    }

    public BuscaResultado executarPorMatricula(String matricula, int pagina, int tamanho) {
        List<Aluno> alunos = alunoRepository.buscarPorMatriculaBusca(matricula, pagina, tamanho);

        List<AlunoResponseDTO> dtos = alunos.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        return new BuscaResultado(dtos, alunos.size());
    }

    public static class BuscaResultado {
        public List<AlunoResponseDTO> alunos;
        public int total;

        public BuscaResultado(List<AlunoResponseDTO> alunos, int total) {
            this.alunos = alunos;
            this.total = total;
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

