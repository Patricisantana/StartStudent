package com.backend.startstudents.application.usecases;

@Component
public class CadastrarAlunoUseCase {

    private final AlunoRepositoryPort alunoRepository;

    public CadastrarAlunoUseCase(AlunoRepositoryPort alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoResponseDTO executar(AlunoRequestDTO requestDTO) {
        if (alunoRepository.buscarPorEmail(requestDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        if (alunoRepository.existeCpf(requestDTO.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        String matricula = gerarMatricula();

        Aluno aluno = Aluno.builder()
                .matricula(matricula)
                .nomeCompleto(requestDTO.getNomeCompleto())
                .email(requestDTO.getEmail())
                .cpf(requestDTO.getCpf())
                .telefone(requestDTO.getTelefone())
                .foto(requestDTO.getFoto())
                .ativo(requestDTO.getAtivo() != null ? requestDTO.getAtivo() : true)
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .build();

        Aluno alunoCadastrado = alunoRepository.salvar(aluno);

        return converterParaDTO(alunoCadastrado);
    }

    private String gerarMatricula() {
        return String.valueOf(System.currentTimeMillis() % 10000000);
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
