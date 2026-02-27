package com.backend.startstudents.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "alunos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String matricula;

    @Column(nullable = false, length = 150)
    private String nomeCompleto;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 14)
    private String cpf;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(length = 255)
    private String foto;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean ativo = true;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @PrePersist
    protected void onInsert() {
        this.criadoEm = LocalDateTime.now();
        this.atualizadoEm = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }
}
