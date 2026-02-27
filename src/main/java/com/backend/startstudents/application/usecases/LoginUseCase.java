package com.backend.startstudents.application.usecases;

import com.backend.startstudents.adaters.inbound.dto.UsuarioLoginDTO;
import com.backend.startstudents.adaters.ports.UsuarioRepositoryPort;
import com.backend.startstudents.domain.entities.Usuario;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginUseCase {

    private final UsuarioRepositoryPort usuarioRepository;

    public LoginUseCase(UsuarioRepositoryPort usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean executar(UsuarioLoginDTO loginDTO) {
        Optional<Usuario> usuario = usuarioRepository.buscarPorUsername(loginDTO.getUsername());

        if (usuario.isEmpty()) {
            return false;
        }

        return usuario.get().getSenha().equals(loginDTO.getSenha());
    }
}
