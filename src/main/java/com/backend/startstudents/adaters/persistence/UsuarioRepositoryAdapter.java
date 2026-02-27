package com.backend.startstudents.adaters.persistence;

import com.backend.startstudents.adaters.ports.UsuarioRepositoryPort;
import com.backend.startstudents.domain.entities.Usuario;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository usuarioJpaRepository;

    public UsuarioRepositoryAdapter(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        return usuarioJpaRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioJpaRepository.findByUsername(username);
    }

    @Override
    public boolean existeUsername(String username) {
        return usuarioJpaRepository.existsByUsername(username);
    }
}
