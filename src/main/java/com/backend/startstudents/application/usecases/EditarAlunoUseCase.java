package com.backend.startstudents.application.usecases;

import com.backend.startstudents.adaters.inbound.dto.AlunoResponseDTO;
import com.backend.startstudents.adaters.inbound.dto.AlunoUpdateDTO;
import com.backend.startstudents.adaters.ports.AlunoRepositoryPort;
import com.backend.startstudents.domain.Aluno;
import org.springframework.stereotype.Component;

@Component
public class EditarAlunoUseCase {

    private final AlunoRepositoryPort alunoRepository;

    public EditarAlunoUseCase(AlunoRepositoryPort alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoResponseDTO executar(Long id, AlunoUpdateDTO updateDTO) {
        Aluno aluno = alunoRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        if (updateDTO.getEmail() != null && !updateDTO.getEmail().isEmpty()) {
            if (!updateDTO.getEmail().equals(aluno.getEmail())) {
                if (alunoRepository.existeEmail(updateDTO.getEmail(), id)) {
                    throw new RuntimeException("Email já cadastrado");
                }
                aluno.setEmail(updateDTO.getEmail());
            }
        }

        if (updateDTO.getTelefone() != null && !updateDTO.getTelefone().isEmpty()) {
            aluno.setTelefone(updateDTO.getTelefone());
        }

        if (updateDTO.getFoto() != null && !updateDTO.getFoto().isEmpty()) {
            aluno.setFoto(updateDTO.getFoto());
        }

        if (updateDTO.getAtivo() != null) {
            aluno.setAtivo(updateDTO.getAtivo());
        }

        aluno.setAtualizadoEm(LocalDateTime.now());

        Aluno alunoAtualizado = alunoRepository.salvar(aluno);

        return converterParaDTO(alunoAtualizado);
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
       }           .build();
    }
}
