package com.controleportaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "moradores")
public class Morador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false, unique = true)
    private String cpf;
    
    @Column(nullable = false)
    private String apartamento;
    
    @Column(nullable = false)
    private String bloco;
    
    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;
    
    @Column(name = "ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao;
    
    @Column(name = "entrada_saida")
    private Boolean entradaSaida; // true para entrada, false para saída
    
    @Column(name = "data_entrada_saida")
    private LocalDateTime dataEntradaSaida;

    // Método executado antes de persistir a entidade
    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDateTime.now();
        ultimaAtualizacao = LocalDateTime.now();
    }
    
    // Método executado antes de atualizar a entidade
    @PreUpdate
    protected void onUpdate() {
        ultimaAtualizacao = LocalDateTime.now();
    }
}