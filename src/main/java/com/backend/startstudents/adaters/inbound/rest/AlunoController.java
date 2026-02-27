package com.backend.startstudents.adaters.inbound.rest;

import com.backend.startstudents.adaters.inbound.dto.AlunoRequestDTO;
import com.backend.startstudents.adaters.inbound.dto.AlunoResponseDTO;
import com.backend.startstudents.application.usecases.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alunos")
@CrossOrigin(origins = "*")
public class AlunoController {

    private final CadastrarAlunoUseCase cadastrarAlunoUseCase;
    private final ListarAlunosUseCase listarAlunosUseCase;
    private final BuscarAlunoUseCase buscarAlunoUseCase;
    private final EditarAlunoUseCase editarAlunoUseCase;
    private final ExcluirAlunoUseCase excluirAlunoUseCase;
    private final BuscarAlunosPorNomeUseCase buscarAlunosPorNomeUseCase;

    public AlunoController(
            CadastrarAlunoUseCase cadastrarAlunoUseCase,
            ListarAlunosUseCase listarAlunosUseCase,
            BuscarAlunoUseCase buscarAlunoUseCase,
            EditarAlunoUseCase editarAlunoUseCase,
            ExcluirAlunoUseCase excluirAlunoUseCase,
            BuscarAlunosPorNomeUseCase buscarAlunosPorNomeUseCase) {
        this.cadastrarAlunoUseCase = cadastrarAlunoUseCase;
        this.listarAlunosUseCase = listarAlunosUseCase;
        this.buscarAlunoUseCase = buscarAlunoUseCase;
        this.editarAlunoUseCase = editarAlunoUseCase;
        this.excluirAlunoUseCase = excluirAlunoUseCase;
        this.buscarAlunosPorNomeUseCase = buscarAlunosPorNomeUseCase;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody AlunoRequestDTO requestDTO) {
        try {
            AlunoResponseDTO aluno = cadastrarAlunoUseCase.executar(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RespostaErro(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RespostaErro("Erro ao cadastrar aluno"));
        }
    }

    @GetMapping
    public ResponseEntity<?> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        try {
            ListarAlunosUseCase.ListaAlunosResultado resultado = listarAlunosUseCase.executar(pagina, tamanho);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RespostaErro("Erro ao listar alunos"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            AlunoResponseDTO aluno = buscarAlunoUseCase.executarPorId(id);
            return ResponseEntity.ok(aluno);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RespostaErro(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RespostaErro("Erro ao buscar aluno"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody AlunoUpdateDTO updateDTO) {
        try {
            AlunoResponseDTO aluno = editarAlunoUseCase.executar(id, updateDTO);
            return ResponseEntity.ok(aluno);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new RespostaErro(e.getMessage()));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RespostaErro(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RespostaErro("Erro ao editar aluno"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            excluirAlunoUseCase.executar(id);
            return ResponseEntity.noContent().build();
        }
    }
}
