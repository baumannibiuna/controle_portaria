package com.controleportaria.controller;

import com.controleportaria.model.Morador;
import com.controleportaria.repository.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/moradores")
public class MoradorController {

    @Autowired
    private MoradorRepository moradorRepository;

    /**
     * Retorna todos os moradores cadastrados
     * @return Lista de moradores
     */
    @GetMapping
    public List<Morador> listarTodos() {
        return moradorRepository.findAll();
    }

    /**
     * Busca um morador pelo ID
     * @param id ID do morador
     * @return Morador encontrado ou 404 se não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<Morador> buscarPorId(@PathVariable Long id) {
        Optional<Morador> morador = moradorRepository.findById(id);
        return morador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Cadastra um novo morador
     * @param morador Dados do morador
     * @return Morador cadastrado
     */
    @PostMapping
    public Morador cadastrar(@RequestBody Morador morador) {
        return moradorRepository.save(morador);
    }

    /**
     * Atualiza os dados de um morador existente
     * @param id ID do morador
     * @param moradorAtualizado Dados atualizados do morador
     * @return Morador atualizado ou 404 se não existir
     */
    @PutMapping("/{id}")
    public ResponseEntity<Morador> atualizar(@PathVariable Long id, @RequestBody Morador moradorAtualizado) {
        return moradorRepository.findById(id)
                .map(morador -> {
                    morador.setNome(moradorAtualizado.getNome());
                    morador.setCpf(moradorAtualizado.getCpf());
                    morador.setApartamento(moradorAtualizado.getApartamento());
                    morador.setBloco(moradorAtualizado.getBloco());
                    morador.setEntradaSaida(moradorAtualizado.getEntradaSaida());
                    morador.setDataEntradaSaida(moradorAtualizado.getDataEntradaSaida());
                    return ResponseEntity.ok(moradorRepository.save(morador));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Registra entrada ou saída de um morador
     * @param id ID do morador
     * @param entrada true para entrada, false para saída
     * @return Morador atualizado ou 404 se não existir
     */
    @PatchMapping("/{id}/registrar-movimentacao")
    public ResponseEntity<Morador> registrarMovimentacao(@PathVariable Long id, @RequestParam Boolean entrada) {
        return moradorRepository.findById(id)
                .map(morador -> {
                    morador.setEntradaSaida(entrada);
                    morador.setDataEntradaSaida(LocalDateTime.now());
                    return ResponseEntity.ok(moradorRepository.save(morador));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Remove um morador
     * @param id ID do morador
     * @return 200 se removido com sucesso, 404 se não existir
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (moradorRepository.existsById(id)) {
            moradorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}