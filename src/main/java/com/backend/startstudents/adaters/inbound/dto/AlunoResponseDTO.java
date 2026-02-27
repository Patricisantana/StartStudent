package com.backend.startstudents.adaters.inbound.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlunoResponseDTO {

    private Long id;
    private String matricula;
    private String nomeCompleto;
    private String email;
    private String cpf;
    private String telefone;
    private String foto;
    private Boolean ativo;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}
