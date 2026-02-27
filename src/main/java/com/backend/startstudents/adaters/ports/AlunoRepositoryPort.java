package com.backend.startstudents.adaters.ports;

import com.backend.startstudents.domain.Aluno;

import java.util.List;
import java.util.Optional;

public interface AlunoRepositoryPort {

    Aluno salvar(Aluno aluno);

    void deletar(Long id);

    Optional<Aluno> buscarPorId(Long id);

    Optional<Aluno> buscarPorMatricula(String matricula);

    Optional<Aluno> buscarPorEmail(String email);

    List<Aluno> listarTodos(int pagina, int tamanho);

    long contarTotal();

    List<Aluno> buscarPorNome(String nome, int pagina, int tamanho);

    List<Aluno> buscarPorMatriculaBusca(String matricula, int pagina, int tamanho);

    boolean existeEmail(String email, Long idExcluir);

    boolean existeCpf(String cpf);
}
