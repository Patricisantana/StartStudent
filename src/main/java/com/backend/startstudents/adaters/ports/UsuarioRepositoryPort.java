package com.backend.startstudents.adaters.ports;

import com.backend.startstudents.domain.entities.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {

    Usuario salvar(Usuario usuario);

    Optional<Usuario> buscarPorUsername(String username);

    boolean existeUsername(String username);
}}
