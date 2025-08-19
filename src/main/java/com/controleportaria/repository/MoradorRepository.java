package com.controleportaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controleportaria.model.Morador;

@Repository
public interface MoradorRepository extends JpaRepository<Morador, Long>{
    // Métodos personalizados podem ser adicionados aqui
    Morador findByCpf(String cpf);
	
}
