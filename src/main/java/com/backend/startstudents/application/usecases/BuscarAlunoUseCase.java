package com.backend.startstudents.application.usecases;

@Component
public class BuscarAlunoUseCase {

    private final AlunoRepositoryPort alunoRepository;

    public BuscarAlunoUseCase(AlunoRepositoryPort alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoResponseDTO executarPorId(Long id) {
        Aluno aluno = alunoRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        return converterParaDTO(aluno);
    }

    public AlunoResponseDTO executarPorMatricula(String matricula) {
        Aluno aluno = alunoRepository.buscarPorMatricula(matricula)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        return converterParaDTO(aluno);
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
