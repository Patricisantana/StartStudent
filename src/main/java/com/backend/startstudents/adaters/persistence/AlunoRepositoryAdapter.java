package com.backend.startstudents.adaters.persistence;

import com.backend.startstudents.adaters.ports.AlunoRepositoryPort;
import com.backend.startstudents.domain.Aluno;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AlunoRepositoryAdapter implements AlunoRepositoryPort {

    private final AlunoJpaRepository alunoJpaRepository;

    public AlunoRepositoryAdapter(AlunoJpaRepository alunoJpaRepository) {
        this.alunoJpaRepository = alunoJpaRepository;
    }

    @Override
    public Aluno salvar(Aluno aluno) {
        return alunoJpaRepository.save(aluno);
    }

    @Override
    public void deletar(Long id) {
        alunoJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Aluno> buscarPorId(Long id) {
        return alunoJpaRepository.findById(id);
    }

    @Override
    public Optional<Aluno> buscarPorMatricula(String matricula) {
        return alunoJpaRepository.findByMatricula(matricula);
    }

    @Override
    public Optional<Aluno> buscarPorEmail(String email) {
        return alunoJpaRepository.findByEmail(email);
    }

    @Override
    public List<Aluno> listarTodos(int pagina, int tamanho) {
        long offset = (long) pagina * tamanho;
        return alunoJpaRepository.findAll().stream()
                .skip(offset)
                .limit(tamanho)
                .toList();
    }

    @Override
    public long contarTotal() {
        return alunoJpaRepository.count();
    }

    @Override
    public List<Aluno> buscarPorNome(String nome, int pagina, int tamanho) {
        long offset = (long) pagina * tamanho;
        return alunoJpaRepository.buscarPorNome(nome, offset, tamanho);
    }

    @Override
    public List<Aluno> buscarPorMatriculaBusca(String matricula, int pagina, int tamanho) {
        long offset = (long) pagina * tamanho;
        return alunoJpaRepository.buscarPorMatricula(matricula, offset, tamanho);
    }

    @Override
    public boolean existeEmail(String email, Long idExcluir) {
        return alunoJpaRepository.countEmailExcluindo(email, idExcluir) > 0;
    }

    @Override
    public boolean existeCpf(String cpf) {
        return alunoJpaRepository.existByCpf(cpf);
    }
}
