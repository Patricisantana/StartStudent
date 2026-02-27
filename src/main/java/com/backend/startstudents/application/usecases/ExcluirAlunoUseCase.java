package com.backend.startstudents.application.usecases;

import com.backend.startstudents.adaters.ports.AlunoRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class ExcluirAlunoUseCase {

    private final AlunoRepositoryPort alunoRepository;

    public ExcluirAlunoUseCase(AlunoRepositoryPort alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void executar(Long id) {
        if (alunoRepository.buscarPorId(id).isEmpty()) {
            throw new RuntimeException("Aluno não encontrado");
        }

        alunoRepository.deletar(id);
    }
}
