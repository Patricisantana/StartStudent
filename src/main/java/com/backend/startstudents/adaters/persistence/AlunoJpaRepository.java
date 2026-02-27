package com.backend.startstudents.adaters.persistence;

import com.backend.startstudents.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoJpaRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByMatricula(String matricula);

    Optional<Aluno> findByEmail(String email);

    boolean existByCpf(String cpf);

    @Query(value = "SELECT * FROM alunos WHERE LOWER(nome_completo) LIKE LOWER(CONCAT('%', :nome, '%')) ORDER BY nome_completo ASC LIMIT :tamanho OFFSET :offset", nativeQuery = true)
    List<Aluno> buscarPorNome(@Param("nome") String nome, @Param("offset") long offset, @Param("tamanho") int tamanho);

    @Query(value = "SELECT * FROM alunos WHERE matricula LIKE CONCAT(:matricula, '%') ORDER BY matricula ASC LIMIT :tamanho OFFSET :offset", nativeQuery = true)
    List<Aluno> buscarPorMatricula(@Param("matricula") String matricula, @Param("offset") long offset, @Param("tamanho") int tamanho);

    @Query(value = "SELECT COUNT(*) FROM alunos WHERE email = :email AND id != :idExcluir", nativeQuery = true)
    int countEmailExcluindo(@Param("email") String email, @Param("idExcluir") Long idExcluir);
}
