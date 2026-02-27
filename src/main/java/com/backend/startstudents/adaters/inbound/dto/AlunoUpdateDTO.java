package com.backend.startstudents.adaters.inbound.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlunoUpdateDTO {

    @Email(message = "Email deve ser válido")
    private String email;

    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$", message = "Telefone inválido")
    private String telefone;

    private String foto;

    private Boolean ativo;
}
