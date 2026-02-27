package com.backend.startstudents.adaters.inbound.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioLoginDTO {

    @NotBlank(message = "Usuário é obrigatório")
    @Size(min = 8, message = "Usuário deve ter no mínimo 8 caracteres")
    private String username;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    private String senha;
}
