package com.backend.startstudents.adaters.inbound.rest;

import com.backend.startstudents.adaters.inbound.dto.UsuarioLoginDTO;
import com.backend.startstudents.application.usecases.LoginUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final LoginUseCase loginUseCase;

    public UsuarioController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody UsuarioLoginDTO loginDTO) {
        try {
            boolean autenticado = loginUseCase.executar(loginDTO);

            if (autenticado) {
                return ResponseEntity.ok(new RespostaLogin(true, "Login realizado com sucesso"));
            }

            return ResponseEntity.status(401).body(new RespostaLogin(false, "Usuário ou senha inválidos"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new RespostaLogin(false, "Erro no servidor: " + e.getMessage()));
        }
    }

    public static class RespostaLogin {
        public boolean sucesso;
        public String mensagem;

        public RespostaLogin(boolean sucesso, String mensagem) {
            this.sucesso = sucesso;
            this.mensagem = mensagem;
        }
    }
}
